package com.jianli.service;

import com.jianli.dto.*;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeService {

    /**
     * 新增简历信息
     * @param param
     * @return
     */
    ResResult submitResumeUserInfo(ResumeUserInsertParam param);
    /**
     * 修改简历信息
     * @param param
     * @return
     */
    ResResult modifyResumeUserInfo(ResumeUserUpdateParam param);

    /**
     * 删除简历
     * @param id
     * @return
     */
    ResResult removeResume(int id);

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

    /**
     * 显示简历信息
     * @param id
     * @return
     */
    ResResult queryResume(int id);

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
    ResResult modifyEduBackgroundTitle(int resumeId,int sort,  String title);

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
    ResResult modifySkillMaturityTitle(int resumeId,int sort,  String title);

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
