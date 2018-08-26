package com.jianli.service;

import com.jianli.dto.*;
import com.jianli.response.ResResult;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface WorkExpService {

    /**
     * 新增工作经历
     * @param param
     * @return
     */
    ResResult submitWorkExp(WorkExpInsertParam param);
    /**
     * 修改工作经历
     * @param param
     * @return
     */
    ResResult modifyWorkExp(WorkExpUpdateParam param);

    /**
     * 修改工作经历标题
     * @param resumeId
     * @param title
     * @return
     */
    ResResult modifyWorkExpTitle(int resumeId, int sort, String title);

    /**
     * 删除工作经历
     * @param id
     * @return
     */
    ResResult removeWorkExp(int id);

    /**
     * 删除工作经历模块
     * @param resumeId
     * @return
     */
    ResResult removeExpModule(int resumeId);

    /**
     * 显示单个工作经历
     * @param id
     * @return
     */
    ResResult queryWorkExp(int id);


}
