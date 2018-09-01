package com.jianli.service;

import com.jianli.dto.ResumeInnerSortDTO;
import com.jianli.dto.ResumeUserInsertParam;
import com.jianli.dto.ResumeUserUpdateParam;
import com.jianli.response.ResResult;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 13:56
 */
public interface ResumeService {

    /**
     * 上传图片
     * @param path
     */
    void uploadHeadImg(String path, int resumeId);

    /**
     * 上传HTML文档
     * @param txt
     * @param resumeId
     */
    void uploadHtml(String txt, int resumeId, int uid);

    /**
     * 发送pdf文件给用户
     * @param resumeId
     */
    ResResult sendPdf(int resumeId, int uid);

    /**
     * 根据用户ID获取默认的简历ID，如果存存在则创建一条默认的
     * @param uid
     * @return
     */
    Integer getResumeIdByUid(Integer uid);

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
     * 更新简历内部排序值
     * @param resumeInnerSortDTOS
     * @return
     */
    ResResult modifyResumeInnerSort(List<ResumeInnerSortDTO> resumeInnerSortDTOS);


    /**
     * 显示简历信息
     * @param id
     * @return
     */
    ResResult queryResume(int id);


}
