package com.jianli.service;

import com.jianli.dto.ResumeModuleInsertParam;
import com.jianli.dto.ResumeModuleUpdateParam;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeModuleService {

    /**
     * 新增简历模块信息
     * @param param
     * @return
     */
    ResResult submitResumeModule(ResumeModuleInsertParam param);
    /**
     * 修改简历模块信息
     * @param param
     * @return
     */
    ResResult modifyResumeModule(ResumeModuleUpdateParam param);

    /**
     * 删除简历
     * @param id
     * @return
     */
    ResResult removeResumeModule(int id);

    /**
     * 查询简历模块信息
     * @param uid
     * @return
     */
    ResResult query(int uid);


}
