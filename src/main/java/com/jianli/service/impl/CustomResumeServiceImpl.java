package com.jianli.service.impl;

import com.jianli.domain.CustomResumeDesc;
import com.jianli.domain.CustomWorkExp;
import com.jianli.dto.CustomResumeDescInsertParam;
import com.jianli.dto.CustomResumeDescUpdateParam;
import com.jianli.dto.CustomWorkExpInsertParam;
import com.jianli.dto.CustomWorkExpUpdateParam;
import com.jianli.repo.CustomResumeDescRepo;
import com.jianli.repo.CustomWorkRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.CustomResumeService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-08-04 20:57
 */
@Service
public class CustomResumeServiceImpl implements CustomResumeService {
    private final CustomResumeDescRepo customResumeDescRepo;
    private final CustomWorkRepo customWorkRepo;

    public CustomResumeServiceImpl(CustomResumeDescRepo customResumeDescRepo,
                                   CustomWorkRepo customWorkRepo) {
        this.customResumeDescRepo = customResumeDescRepo;
        this.customWorkRepo = customWorkRepo;
    }

    @Override
    public ResResult submitCustomResumeDesc(CustomResumeDescInsertParam param) {
        CustomResumeDesc customResumeDesc = param.toCustomResumeDesc();
        customResumeDesc.submit(param.getUid());
        CustomResumeDesc saved = customResumeDescRepo.save(customResumeDesc);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyCustomResumeDesc(CustomResumeDescUpdateParam param) {
        CustomResumeDesc customResumeDesc = param.toCustomResumeDesc();
        customResumeDesc.modify(param.getUid());
        CustomResumeDesc modified = customResumeDescRepo.save(customResumeDesc);
        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeCustomResumeDesc(int id) {
        customResumeDescRepo.deleteById(id);
        return ResUtils.suc();
    }

    @Override
    public ResResult submitCustomWorkExp(CustomWorkExpInsertParam param) {
        CustomWorkExp customWorkExp = param.toCustomWorkExp();
        customWorkExp.submit(param.getUid());
        CustomWorkExp saved = customWorkRepo.save(customWorkExp);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult modifyCustomWorkExp(CustomWorkExpUpdateParam param) {
        CustomWorkExp customWorkExp = param.toCustomWorkExp();
        customWorkExp.modify(param.getUid());
        CustomWorkExp modified = customWorkRepo.save(customWorkExp);
        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }

    @Override
    public ResResult removeCustomWorkExp(int id) {
        customWorkRepo.deleteById(id);
        return ResUtils.suc();
    }
}
