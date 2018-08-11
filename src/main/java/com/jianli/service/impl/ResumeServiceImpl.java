package com.jianli.service.impl;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import com.jianli.dto.*;
import com.jianli.repo.*;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public ResumeServiceImpl(WorkRepo workRepo, EduBackgroundRepo eduBackgroundRepo, SkillRepo skilledRepo,
                             ResumeRepo resumeRepo, CustomResumeDescRepo customResumeDescRepo, CustomWorkRepo customWorkRepo) {
        this.workRepo = workRepo;
        this.eduBackgroundRepo = eduBackgroundRepo;
        this.skilledRepo = skilledRepo;
        this.resumeRepo = resumeRepo;
        this.customWorkRepo = customWorkRepo;
        this.customResumeDescRepo = customResumeDescRepo;
    }


    @Override
    public ResResult submitResume(ResumeInsertParam param) {
        Resume resume = param.toResume();
        resume.submit(param.getUid());
        Resume saved = resumeRepo.save(resume);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyResume(ResumeUpdateParam param) {
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
        ResumeVo vo = BeanUtils.copy(resume, ResumeVo.class);

        vo.setEduBackground(resume.getEduTitle(), resume.getEduSort(), eduBackgroundRepo.listByResumeId(id));
        vo.setWorkExp(resume.getExpTitle(), resume.getExpSort(), workRepo.listByResumeId(id));
        vo.setSkillMaturity(resume.getSkillTitle(), resume.getSkillSort(), skilledRepo.listByResumeId(id));
        vo.setCustomResumeDesc(customResumeDescRepo.listByResumeId(id));
        vo.setCustomWorkExp(customWorkRepo.listByResumeId(id));
        return ResUtils.data(vo);
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
