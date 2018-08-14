package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "新增自定义项目介绍")
public class CustomResumeDescInsertParam extends CustomResumeDescParam {

    @ApiModelProperty(notes = "简历ID", example = "1")
    @Min(value = 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    private Integer resumeId;

}
