package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@Data
@ApiModel(value = "简历信息")
public class ResumeInfo {
    @ApiModelProperty(notes = "用户ID", required = true)
    private int uid;
    @ApiModelProperty(notes = "教育背景")
    private List<EduBackground> eduBackgrounds;
    @ApiModelProperty(notes = "工作经历")
    private List<WorkExp> workExps;


}
