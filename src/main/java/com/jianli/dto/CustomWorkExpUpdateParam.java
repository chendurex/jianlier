package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改自定义工作经历")
@Data
public class CustomWorkExpUpdateParam extends CustomWorkExpParam {
    @ApiModelProperty(notes = "唯一ID", example = "1", required = true)
    @Min(value = 1, message = "自定义工作经历ID必须大于1")
    @NotNull(message = "自定义工作经历ID不能为空")
    private Integer id;

}
