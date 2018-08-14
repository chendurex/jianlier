package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-18 12:26
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "新增教育背景")
@Data
public class EduBackgroundInsertParam extends EduBackgroundParam {

    @ApiModelProperty(notes = "简历ID", example = "1")
    @Min(value = 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    private Integer resumeId;

}
