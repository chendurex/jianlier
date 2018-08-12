package com.jianli.service.impl;

import com.google.common.collect.ImmutableMap;
import com.jianli.commons.BeanUtils;
import com.jianli.domain.*;
import com.jianli.dto.*;
import com.jianli.repo.*;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
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
    public ResumeServiceImpl(WorkRepo workRepo, EduBackgroundRepo eduBackgroundRepo, SkillRepo skilledRepo,
                             ResumeRepo resumeRepo, CustomResumeDescRepo customResumeDescRepo,
                             CustomWorkRepo customWorkRepo, CustomWorkSubRepo customWorkSubRepo) {
        this.workRepo = workRepo;
        this.eduBackgroundRepo = eduBackgroundRepo;
        this.skilledRepo = skilledRepo;
        this.resumeRepo = resumeRepo;
        this.customWorkRepo = customWorkRepo;
        this.customResumeDescRepo = customResumeDescRepo;
        this.customWorkSubRepo = customWorkSubRepo;
    }


    @Override
    public ResResult submitResumeUserInfo(ResumeUserInsertParam param) {
        Resume resume = param.toResume();
        resume.submit(param.getUid());
        Resume saved = resumeRepo.save(resume);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyResumeUserInfo(ResumeUserUpdateParam param) {
        Resume resume = param.toResume();
        resume.modify(param.getUid());
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
    public ResResult queryResume(int id) {
        Optional<Resume> opt = resumeRepo.findById(id);
        if (!opt.isPresent()) {
            return ResUtils.fail("查询的数据不存在");
        }
        Resume resume = opt.get();
        ResumeVo.SummaryVO summaryVO = new ResumeVo.SummaryVO(resume.getSummaryTitle(), resume.getSummary());

        ResumeVo.UserInfoVO  userInfoVO = ResumeVo.UserInfoVO.builder().address(resume.getAddress()).email(resume.getEmail()).mobile(resume.getMobile())
                .wechat(resume.getWechat()).name(resume.getName()).objectiveTitle(resume.getObjectiveTitle()).build();

        ResumeVo.EduBackgroundVO eduBackgroundVO = new ResumeVo.EduBackgroundVO(resume.getEduTitle(),
                eduBackgroundRepo.listByResumeId(id));


        ResumeVo.WorkExpVO expVO = new ResumeVo.WorkExpVO(resume.getExpTitle(), workRepo.listByResumeId(id));

        ResumeVo.SkillMaturityVO skillMaturityVO = new ResumeVo.SkillMaturityVO(resume.getSkillTitle(),
                skilledRepo.listByResumeId(id));

        Map<String, Integer> sorted = new TreeMap<>();
        sorted.put("userInfo", -1);
        sorted.put("summary", 1);
        sorted.put("eduBackground", resume.getEduSort());
        sorted.put("skillMaturity", resume.getSkillSort());
        sorted.put("workExp", resume.getExpSort());


        Map<String, Object> maps = ImmutableMap.<String, Object>builder()
                .put("id", resume.getId())
                .put("sorted", sorted)
                .put("userInfo", userInfoVO)
                .put("summary", summaryVO)
                .put("eduBackground", eduBackgroundVO)
                .put("skillMaturity", skillMaturityVO)
                .put("workExp", expVO).build();
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

    @Transactional
    @Override
    public ResResult submitResumeSummary(int resumeId, String summary) {
        resumeRepo.updateSummaryContent(summary, resumeId);
        return ResUtils.suc();
    }

    @Transactional
    @Override
    public ResResult modifyResumeSummary(int id, String summary) {
        resumeRepo.updateSummaryContent(summary, id);
        return ResUtils.suc();
    }

    @Transactional
    @Override
    public ResResult removeResumeSummary(int id) {
        resumeRepo.removeSummary(id);
        return ResUtils.suc();
    }

    @Transactional
    @Override
    public ResResult modifyResumeSummaryTitle(int resumeId, int sort, String title) {
        resumeRepo.updateSummaryTitle(title, sort, resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult submitWorkExp(WorkExpInsertParam param) {
        WorkExp exp = param.toWorkExp();
        exp.submit(param.getUid());
        WorkExp saved = workRepo.save(exp);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyWorkExp(WorkExpUpdateParam param) {
        WorkExp exp = param.toWorkExp();
        exp.modify(param.getUid());
        WorkExp modified = workRepo.save(exp);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }


    @Transactional
    @Override
    public ResResult modifyWorkExpTitle(int resumeId, int sort, String title) {
        resumeRepo.updateExpTitle(title, sort, resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult removeWorkExp(int id) {
        workRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult queryWorkExp(int id) {
        return ResUtils.data(workRepo.findById(id));
    }

    @Override
    public ResResult submitEduBackground(EduBackgroundInsertParam param) {
        EduBackground edu = param.toEduBackground();
        edu.submit(param.getUid());
        EduBackground saved = eduBackgroundRepo.save(edu);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyEduBackground(EduBackgroundUpdateParam param) {
        EduBackground edu = param.toEduBackground();
        edu.modify(param.getUid());
        EduBackground modified = eduBackgroundRepo.save(edu);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Transactional
    @Override
    public ResResult modifyEduBackgroundTitle(int resumeId, int sort, String title) {
        resumeRepo.updateEduTitle(title, sort, resumeId);
        return ResUtils.suc();
    }


    @Override
    public ResResult removeEduBackground(int id) {
        eduBackgroundRepo.deleteById(id);
        return ResUtils.suc();
    }


    @Override
    public ResResult queryEduBackground(int id) {
        return ResUtils.data(eduBackgroundRepo.findById(id));
    }



    @Override
    public ResResult submitSkillMaturity(SkillMaturityInsertParam param) {
        SkillMaturity skill = param.toSkillMaturity();
        skill.submit(param.getUid());
        SkillMaturity saved = skilledRepo.save(skill);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifySkillMaturity(SkillMaturityUpdateParam param) {
        SkillMaturity skill = param.toSkillMaturity();
        skill.modify(param.getUid());
        SkillMaturity modified = skilledRepo.save(skill);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Transactional
    @Override
    public ResResult modifySkillMaturityTitle(int resumeId,int sort, String title) {
        resumeRepo.updateSkillTitle(title, sort, resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult removeSkillMaturity(int id) {
        skilledRepo.deleteById(id);
        return ResUtils.suc();
    }


    @Override
    public ResResult querySkillMaturity(int id) {
        return ResUtils.data(skilledRepo.findById(id));
    }
}
