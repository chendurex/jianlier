package com.jianli.service;

import com.jianli.domain.WorkExp;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeService {
    ResResult workExpSubmit(WorkExp exp);
}
