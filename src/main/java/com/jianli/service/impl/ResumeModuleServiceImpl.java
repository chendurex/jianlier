package com.jianli.service.impl;

import com.jianli.domain.ResumeModule;
import com.jianli.dto.ResumeModuleInsertParam;
import com.jianli.dto.ResumeModuleUpdateParam;
import com.jianli.repo.ResumeModuleRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeModuleService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-08-04 20:57
 */
@Service
public class ResumeModuleServiceImpl implements ResumeModuleService {
    private final ResumeModuleRepo resumeModuleRepo;

    public ResumeModuleServiceImpl(ResumeModuleRepo resumeModuleRepo) {
        this.resumeModuleRepo = resumeModuleRepo;
    }

    @Override
    public ResResult submitResumeModule(ResumeModuleInsertParam param) {
        ResumeModule resumeModule = param.toResumeModule();
        resumeModule.submit(param.getUid());
        ResumeModule saved = resumeModuleRepo.save(resumeModule);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyResumeModule(ResumeModuleUpdateParam param) {
        ResumeModule resumeModule = param.toResumeModule();
        resumeModule.modify(resumeModule.getUid());
        ResumeModule modified = resumeModuleRepo.save(resumeModule);
        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeResumeModule(int id) {
        resumeModuleRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult query(int uid) {
        return ResUtils.data(resumeModuleRepo.queryByUid(uid));
    }
}
