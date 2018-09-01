package com.jianli.service.impl;

import com.jianli.advice.GlobalVariable;
import com.jianli.domain.WorkExp;
import com.jianli.dto.WorkExpInsertParam;
import com.jianli.dto.WorkExpUpdateParam;
import com.jianli.repo.ResumeRepo;
import com.jianli.repo.WorkRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.WorkExpService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class WorkExpServiceImpl implements WorkExpService {
    private final WorkRepo workRepo;
    private final ResumeRepo resumeRepo;
    public WorkExpServiceImpl(WorkRepo workRepo, ResumeRepo resumeRepo) {
        this.workRepo = workRepo;
        this.resumeRepo = resumeRepo;
    }


    @Override
    public ResResult submitWorkExp(WorkExpInsertParam param) {
        WorkExp exp = param.toWorkExp();
        exp.submit(GlobalVariable.uid());
        WorkExp saved = workRepo.save(exp);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyWorkExp(WorkExpUpdateParam param) {
        WorkExp exp = param.toWorkExp();
        exp.modify(GlobalVariable.uid());
        WorkExp modified = workRepo.save(exp);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }


    
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
    public ResResult removeExpModule(int resumeId) {
        resumeRepo.removeExp(resumeId);
        workRepo.removeExpByResumeId(resumeId);
        return ResUtils.suc();
    }

    @Override
    public ResResult queryWorkExp(int id) {
        return ResUtils.data(workRepo.findById(id));
    }


}
