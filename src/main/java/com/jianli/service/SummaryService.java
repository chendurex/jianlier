package com.jianli.service;

import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface SummaryService {

    /**
     * 新增简历介绍
     * @param
     * @return
     */
    ResResult submitResumeSummary(int resumeId, String summary, Integer sort);
    /**
     * 修改简历介绍
     * @return
     */
    ResResult modifyResumeSummary(int id, String summary);

    /**
     * 删除简历介绍
     * @param id
     * @return
     */
    ResResult removeResumeSummary(int id);

    /**
     * 修改简介介绍标题
     * @param resumeId
     * @param sort
     * @param title
     * @return
     */
    ResResult modifyResumeSummaryTitle(int resumeId, int sort, String title);


}
