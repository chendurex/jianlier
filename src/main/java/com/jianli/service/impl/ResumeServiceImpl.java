package com.jianli.service.impl;

import com.jianli.advice.GlobalVariable;
import com.jianli.commons.BeanUtils;
import com.jianli.commons.FileUtils;
import com.jianli.commons.Html2PdfUtils;
import com.jianli.commons.UniqueSerials;
import com.jianli.component.MailSender;
import com.jianli.domain.CustomResumeDesc;
import com.jianli.domain.CustomWorkExp;
import com.jianli.domain.Resume;
import com.jianli.dto.*;
import com.jianli.repo.*;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    private final WorkRepo workRepo;
    private final EduBackgroundRepo eduBackgroundRepo;
    private final SkillRepo skilledRepo;
    private final ResumeRepo resumeRepo;
    private final CustomResumeDescRepo customResumeDescRepo;
    private final CustomWorkRepo customWorkRepo;
    private final CustomWorkSubRepo customWorkSubRepo;
    private final MailSender mailSender;
    @Value("${upload.pdf.filepath}")
    private String pdfFilepath;
    @Value("${upload.html.filepath}")
    private String htmlFilepath;
    public ResumeServiceImpl(WorkRepo workRepo, EduBackgroundRepo eduBackgroundRepo, SkillRepo skilledRepo,
                             ResumeRepo resumeRepo, CustomResumeDescRepo customResumeDescRepo,
                             CustomWorkRepo customWorkRepo, CustomWorkSubRepo customWorkSubRepo,
                             MailSender mailSender) {
        this.workRepo = workRepo;
        this.eduBackgroundRepo = eduBackgroundRepo;
        this.skilledRepo = skilledRepo;
        this.resumeRepo = resumeRepo;
        this.customWorkRepo = customWorkRepo;
        this.customResumeDescRepo = customResumeDescRepo;
        this.customWorkSubRepo = customWorkSubRepo;
        this.mailSender = mailSender;
    }

    @Override
    public void uploadHeadImg(String path, int resumeId) {
        resumeRepo.updateHeadImg(path, resumeId);
    }

    @Override
    public void uploadHtml(String txt, int resumeId, int uid) {
        FileUtils.writeTo(txt, UniqueSerials.assembleHtmlPath(htmlFilepath, uid, resumeId));
    }

    @Override
    public ResResult sendPdf(int resumeId, int uid) {
        String htmlPath = UniqueSerials.assembleHtmlPath(htmlFilepath, uid, resumeId);
        if (!FileUtils.exist(htmlPath)) {
            return ResUtils.fail("不存在HTML文档，请先生成HTML文档");
        }
        Optional<Resume> resume = resumeRepo.findById(resumeId);
        if (!resume.isPresent()) {
            return ResUtils.fail("当前用户不存在有效的简历");
        }
        String mail = resume.get().getEmail();
        String text = "\"text\":\"";
        int index = mail.indexOf(text);
        if (index != -1) {
            int end = mail.indexOf("\"", index+text.length());
            mail = mail.substring(index + text.length(),  end);
        }
        if (mail.isEmpty()) {
            return ResUtils.fail("未填写邮箱，无法发送简历到指定的邮箱");
        }
        String fileName = UniqueSerials.assemblePdfPath(pdfFilepath, uid, resumeId);
        Html2PdfUtils.writeTo(htmlPath, fileName);
        mailSender.send(mail, fileName);
        return ResUtils.suc();
    }

    @Transactional
    @Override
    public Integer getResumeIdByUid(Integer uid) {
        Integer resumeId = resumeRepo.getResumeIdByUid(uid);
        if (resumeId == null) {
            Resume resume = Resume.builder().address("").mobile("").email("")
                    .name("").wechat("").objectiveTitle("").build();
            resume.submit(uid);
            Resume saved = resumeRepo.save(resume);
            resumeId = saved.getId();
        }
        return resumeId;
    }

    @Override
    public ResResult submitResumeUserInfo(ResumeUserInsertParam param) {
        Resume resume = param.toResume();
        resume.submit(GlobalVariable.uid());
        Resume saved = resumeRepo.save(resume);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyResumeUserInfo(ResumeUserUpdateParam param) {
        Resume resume = param.toResume();
        resume.modify(GlobalVariable.uid());
        Resume modified = resumeRepo.save(resume);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeResume(int id) {
        // todo 还需要删除相应的其它信息
        resumeRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult modifyResumeInnerSort(List<ResumeInnerSortDTO> resumeSortDTOS) {
        for (ResumeInnerSortDTO dto : resumeSortDTOS) {
            if (dto.isEdu()) {
                eduBackgroundRepo.modifyEduSortById(dto.getSort(), dto.getId());
            } else if (dto.isExp()) {
                workRepo.modifyExpSortById(dto.getSort(), dto.getId());
            } else if (dto.isSkill()) {
                skilledRepo.modifySkillSortById(dto.getSort(), dto.getId());
            } else if (dto.isCusExp()) {
                customWorkSubRepo.modifyCusExpSubSortById(dto.getSort(), dto.getId());
            }
        }
        return ResUtils.suc();
    }

    @Override
    public ResResult queryResume(int id) {
        Optional<Resume> opt = resumeRepo.findById(id);
        if (!opt.isPresent()) {
            return ResUtils.fail("查询的数据不存在");
        }
        Resume resume = opt.get();

        Map<String, Integer> sorted = new TreeMap<>();
        Map<String, Object> maps = new HashMap<>(16);
        maps.put("id", resume.getId());
        maps.put("sorted", sorted);
        maps.put("userInfo", ResumeVo.createUserInfoVO(resume));

        sorted.put("userInfo", -1);

        ResumeVo.SummaryVO summaryVO = ResumeVo.createSummaryVO(resume);
        if (summaryVO.getExist()) {
            sorted.put("summary", summaryVO.getSort());
            maps.put("summary", summaryVO);
        }

        ResumeVo.EduBackgroundVO eduBackgroundVO = ResumeVo.createEduBackground(resume,
                eduBackgroundRepo.listByResumeId(id));
        if (eduBackgroundVO.getExist()) {
            sorted.put("eduBackground", eduBackgroundVO.getSort());
            maps.put("eduBackground", eduBackgroundVO);

        }

        ResumeVo.WorkExpVO expVO = ResumeVo.createWorkExp(resume, workRepo.listByResumeId(id));
        if (expVO.getExist()) {
            sorted.put("workExp", expVO.getSort());
            maps.put("workExp", expVO);
        }


        ResumeVo.SkillMaturityVO skillMaturityVO = ResumeVo.createSkillMaturity(resume, skilledRepo.listByResumeId(id));
        if (skillMaturityVO.getExist()) {
            sorted.put("skillMaturity", skillMaturityVO.getSort());
            maps.put("skillMaturity", skillMaturityVO);
        }


        Map<String, Object> copys = new HashMap<>(maps);

        List<CustomResumeDesc> customResumeDesc = customResumeDescRepo.listByResumeId(resume.getId());
        for (int i=0; i<customResumeDesc.size(); i++) {
            String key = "customDesc_"+i;
            CustomResumeDesc desc = customResumeDesc.get(i);
            sorted.put(key, desc.getSort());
            copys.put(key, desc);
        }

        List<CustomWorkExp> customWorkExps = customWorkRepo.listByResumeId(resume.getId());
        for (int i = 0; i < customWorkExps.size(); i++) {
            String key = "customExp_"+i;
            CustomWorkExp exp = customWorkExps.get(i);
            sorted.put(key, exp.getSort());

            CustomWorkExpParamVO vo = BeanUtils.copy(exp, CustomWorkExpParamVO.class);
            vo.setCustomWorkExpSubs(customWorkSubRepo.listBypid(vo.getId()));
            copys.put(key, vo);
        }
        return ResUtils.data(copys);
    }
}
