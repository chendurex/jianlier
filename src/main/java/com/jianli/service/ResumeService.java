package com.jianli.service;

import com.jianli.domain.EduBackground;
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
    ResResult submitWorkExp(WorkExp exp);
    /**
     * 修改工作经历
     * @param exp
     * @return
     */
    ResResult modifyWorkExp(WorkExp exp);

    /**
     * 查询工作经历列表
     * @param resumeId
     * @return
     */
    ResResult listWorkExp(int resumeId);

    /**
     * 显示单个工作经历
     * @param id
     * @return
     */
    ResResult workExp(int id);

    /**
     * 新增教育背景
     * @param edu
     * @return
     */
    ResResult submitEduBackground(EduBackground edu);
    /**
     * 修改教育背景
     * @param edu
     * @return
     */
    ResResult modifyEduBackground(EduBackground edu);

    /**
     * 查询教育背景列表
     * @param resumeId
     * @return
     */
    ResResult listEduBackground(int resumeId);

    /**
     * 显示单个教育背景
     * @param id
     * @return
     */
    ResResult eduBackground(int id);
}
