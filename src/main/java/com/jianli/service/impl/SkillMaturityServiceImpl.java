package com.jianli.service.impl;

import com.jianli.advice.GlobalVariable;
import com.jianli.domain.SkillMaturity;
import com.jianli.dto.SkillMaturityInsertParam;
import com.jianli.dto.SkillMaturityUpdateParam;
import com.jianli.repo.ResumeRepo;
import com.jianli.repo.SkillRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.SkillMaturityService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class SkillMaturityServiceImpl implements SkillMaturityService {
    private final SkillRepo skilledRepo;
    private final ResumeRepo resumeRepo;
    public SkillMaturityServiceImpl(SkillRepo skilledRepo, ResumeRepo resumeRepo) {
        this.skilledRepo = skilledRepo;
        this.resumeRepo = resumeRepo;
    }


    @Override
    public ResResult submitSkillMaturity(SkillMaturityInsertParam param) {
        SkillMaturity skill = param.toSkillMaturity();
        skill.submit(GlobalVariable.uid());
        SkillMaturity saved = skilledRepo.save(skill);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifySkillMaturity(SkillMaturityUpdateParam param) {
        SkillMaturity skill = param.toSkillMaturity();
        skill.modify(GlobalVariable.uid());
        SkillMaturity modified = skilledRepo.save(skill);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    
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
    public ResResult removeSkillModule(int resumeId) {
        resumeRepo.removeSkill(resumeId);
        skilledRepo.removeSkillByResumeId(resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult querySkillMaturity(int id) {
        return ResUtils.data(skilledRepo.findById(id));
    }
}
