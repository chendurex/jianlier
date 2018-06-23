package com.jianli.service.impl;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import com.jianli.dto.*;
import com.jianli.repo.EduBackgroundRepo;
import com.jianli.repo.ResumeRepo;
import com.jianli.repo.SkillRepo;
import com.jianli.repo.WorkRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import org.springframework.stereotype.Service;

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
    public ResumeServiceImpl(WorkRepo workRepo, EduBackgroundRepo eduBackgroundRepo, SkillRepo skilledRepo, ResumeRepo resumeRepo) {
        this.workRepo = workRepo;
        this.eduBackgroundRepo = eduBackgroundRepo;
        this.skilledRepo = skilledRepo;
        this.resumeRepo = resumeRepo;
    }


    @Override
    public ResResult submitResume(ResumeInsertParam param) {
        Resume resume = BeanUtils.copy(param, Resume.class);
        resume.submit(param.getUid());
        Resume saved = resumeRepo.save(resume);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyResume(ResumeUpdateParam param) {
        Resume resume = BeanUtils.copy(param, Resume.class);
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
        vo.setEduBackgrounds(eduBackgroundRepo.listByResumeId(id));
        vo.setWorkExps(workRepo.listByResumeId(id));
        vo.setSkillMaturities(skilledRepo.listByResumeId(id));
        return ResUtils.data(vo);
    }

    @Override
    public ResResult submitWorkExp(WorkExpInsertParam param) {
        WorkExp exp = BeanUtils.copy(param, WorkExp.class);
        exp.submit(param.getUid());
        WorkExp saved = workRepo.save(exp);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyWorkExp(WorkExpUpdateParam param) {
        WorkExp exp = BeanUtils.copy(param, WorkExp.class);
        exp.modify(param.getUid());
        WorkExp modified = workRepo.save(exp);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeWorkExp(int id) {
        workRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult listWorkExp(int resumeId) {
        return ResUtils.data(workRepo.listByResumeId(resumeId));
    }

    @Override
    public ResResult queryWorkExp(int id) {
        return ResUtils.data(workRepo.findById(id));
    }

    @Override
    public ResResult submitEduBackground(EduBackgroundInsertParam param) {
        EduBackground edu = BeanUtils.copy(param, EduBackground.class);
        edu.submit(param.getUid());
        EduBackground saved = eduBackgroundRepo.save(edu);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyEduBackground(EduBackgroundUpdateParam param) {
        EduBackground edu = BeanUtils.copy(param, EduBackground.class);
        edu.modify(param.getUid());
        EduBackground modified = eduBackgroundRepo.save(edu);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }


    @Override
    public ResResult removeEduBackground(int id) {
        eduBackgroundRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult listEduBackground(int resumeId) {
        return ResUtils.data(eduBackgroundRepo.listByResumeId(resumeId));
    }

    @Override
    public ResResult queryEduBackground(int id) {
        return ResUtils.data(eduBackgroundRepo.findById(id));
    }



    @Override
    public ResResult submitSkillMaturity(SkillMaturityInsertParam param) {
        SkillMaturity skill = BeanUtils.copy(param, SkillMaturity.class);
        skill.submit(param.getUid());
        SkillMaturity saved = skilledRepo.save(skill);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifySkillMaturity(SkillMaturityUpdateParam param) {
        SkillMaturity skill = BeanUtils.copy(param, SkillMaturity.class);
        skill.modify(param.getUid());
        SkillMaturity modified = skilledRepo.save(skill);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeSkillMaturity(int id) {
        skilledRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult listSkillMaturity(int resumeId) {
        return ResUtils.data(skilledRepo.listByResumeId(resumeId));
    }

    @Override
    public ResResult querySkillMaturity(int id) {
        return ResUtils.data(skilledRepo.findById(id));
    }
}
