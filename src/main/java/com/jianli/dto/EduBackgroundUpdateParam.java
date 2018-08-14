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
@ApiModel(value = "更新教育背景")
@Data
public class EduBackgroundUpdateParam extends EduBackgroundParam {
    @ApiModelProperty(notes = "唯一ID", example = "1", required = true)
    @Min(value = 1, message = "教育背景ID必须大于1")
    @NotNull(message = "教育背景ID不能为空")
    private Integer id;
}
