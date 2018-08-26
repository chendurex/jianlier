package com.jianli.service;

import com.jianli.dto.SkillMaturityInsertParam;
import com.jianli.dto.SkillMaturityUpdateParam;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface SkillMaturityService {


    /**
     * 新增技能成熟度
     * @param param
     * @return
     */
    ResResult submitSkillMaturity(SkillMaturityInsertParam param);
    /**
     * 修改技能成熟度
     * @param param
     * @return
     */
    ResResult modifySkillMaturity(SkillMaturityUpdateParam param);

    /**
     * 修改技能成熟度标题
     * @param resumeId
     * @param title
     * @return
     */
    ResResult modifySkillMaturityTitle(int resumeId, int sort, String title);

    /**
     * 删除技能数量度
     * @param id
     * @return
     */
    ResResult removeSkillMaturity(int id);

    /**
     * 删除技能熟练度模块
     * @param resumeId
     * @return
     */
    ResResult removeSkillModule(int resumeId);


    /**
     * 显示单个技能成熟度
     * @param id
     * @return
     */
    ResResult querySkillMaturity(int id);
}
