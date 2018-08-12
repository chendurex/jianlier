package com.jianli.service;

import com.jianli.dto.*;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface CustomResumeService {

    /**
     * 新增自定义简历描述
     * @param param
     * @return
     */
    ResResult submitCustomResumeDesc(CustomResumeDescInsertParam param);
    /**
     * 修改自定义简历描述
     * @param param
     * @return
     */
    ResResult modifyCustomResumeDesc(CustomResumeDescUpdateParam param);

    /**
     * 删除自定义简历描述
     * @param id
     * @return
     */
    ResResult removeCustomResumeDesc(int id);


    /**
     * 新增自定义工作经历标题
     * @param param
     * @return
     */
    ResResult submitCustomWorkExpSub(CustomWorkExpSubInsertParam param);
    /**
     * 修改自定义工作经历标题
     * @param param
     * @return
     */
    ResResult modifyCustomWorkExpSub(CustomWorkExpSubUpdateParam param);

    /**
     * 删除自定义工作经历标题
     * @param id
     * @return
     */
    ResResult removeCustomWorkExpSub(int id);
    /**
     * 新增自定义工作经历
     * @param param
     * @return
     */
    ResResult submitCustomWorkExp(CustomWorkExpInsertParam param);
    /**
     * 修改自定义工作经历
     * @param param
     * @return
     */
    ResResult modifyCustomWorkExp(CustomWorkExpUpdateParam param);

    /**
     * 删除自定义工作经历
     * @param id
     * @return
     */
    ResResult removeCustomWorkExp(int id);

}
