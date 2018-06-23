package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改工作经历")
@Data
public class WorkExpUpdateParam extends WorkExpParam {
    @ApiModelProperty(notes = "唯一ID", example = "1", required = true)
    @Min(value = 1, message = "教育背景ID必须大于1")
    private Integer id;

}
