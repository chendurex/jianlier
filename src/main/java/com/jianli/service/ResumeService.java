package com.jianli.service;

import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
import com.jianli.domain.WorkExp;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeService {

    /**
     * 新增简历信息
     * @param resume
     * @return
     */
    ResResult submitResume(Resume resume);
    /**
     * 修改简历信息
     * @param resume
     * @return
     */
    ResResult modifyResume(Resume resume);

    /**
     * 删除简历
     * @param id
     * @return
     */
    ResResult removeResume(int id);

    /**
     * 显示简历信息
     * @param id
     * @return
     */
    ResResult queryResume(int id);

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
     * 删除工作经历
     * @param id
     * @return
     */
    ResResult removeWorkExp(int id);

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
    ResResult queryWorkExp(int id);

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
     * 删除教育背景
     * @param id
     * @return
     */
    ResResult removeEduBackground(int id);
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
    ResResult queryEduBackground(int id);

    /**
     * 新增技能成熟度
     * @param edu
     * @return
     */
    ResResult submitSkillMaturity(SkillMaturity edu);
    /**
     * 修改技能成熟度
     * @param edu
     * @return
     */
    ResResult modifySkillMaturity(SkillMaturity edu);
    /**
     * 删除技能数量度
     * @param id
     * @return
     */
    ResResult removeSkillMaturity(int id);
    /**
     * 查询技能成熟度
     * @param resumeId
     * @return
     */
    ResResult listSkillMaturity(int resumeId);

    /**
     * 显示单个技能成熟度
     * @param id
     * @return
     */
    ResResult querySkillMaturity(int id);
}
