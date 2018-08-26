package com.jianli.service;

import com.jianli.dto.EduBackgroundInsertParam;
import com.jianli.dto.EduBackgroundUpdateParam;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface EduBackgroundService {

    /**
     * 新增教育背景
     * @param param
     * @return
     */
    ResResult submitEduBackground(EduBackgroundInsertParam param);

    /**
     * 修改教育背景
     * @param param
     * @return
     */
    ResResult modifyEduBackground(EduBackgroundUpdateParam param);

    /**
     * 修改教育背景标题
     * @param resumeId
     * @param title
     * @return
     */
    ResResult modifyEduBackgroundTitle(int resumeId, int sort, String title);


    /**
     * 删除教育背景
     * @param id
     * @return
     */
    ResResult removeEduBackground(int id);

    /**
     * 删除教育背景模块
     * @param resumeId
     * @return
     */
    ResResult removeEduModule(int resumeId);

    /**
     * 显示单个教育背景
     * @param id
     * @return
     */
    ResResult queryEduBackground(int id);

}
