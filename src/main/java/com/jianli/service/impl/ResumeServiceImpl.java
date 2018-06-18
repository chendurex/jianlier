package com.jianli.service.impl;

import com.jianli.domain.WorkExp;
import com.jianli.repo.WorkRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-06-18 13:57
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    private final WorkRepo workRepo;
    public ResumeServiceImpl(WorkRepo workRepo) {
        this.workRepo = workRepo;
    }
    @Override
    public ResResult workExpSubmit(WorkExp exp) {
        if (!exp.submit()) {
            return ResUtils.fail("请填写简历ID");
        }
        WorkExp saved = workRepo.save(exp);
        if (saved.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(saved.getId());
    }

    @Override
    public ResResult workExpModify(WorkExp exp) {
        if (!exp.modify()) {
            return ResUtils.fail("请填写工作经历ID");
        }
        WorkExp modified = workRepo.save(exp);

        if (modified.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(modified.getId());
    }
}
