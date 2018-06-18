package com.jianli.service;

import com.jianli.domain.WorkExp;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeService {
    /**
     * 新增工作经历
     * @param exp
     * @return
     */
    ResResult workExpSubmit(WorkExp exp);
    /**
     * 修改工作经历
     * @param exp
     * @return
     */
    ResResult workExpModify(WorkExp exp);
}
