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
@ApiModel(value = "修改自定义项目介绍")
public class CustomResumeDescUpdateParam extends CustomResumeDescParam {

    @Min(value = 1, message = "模块ID必须大于1")
    @NotNull(message = "模块ID不能为空")
    @ApiModelProperty(notes = "模块ID", example = "1", required = true)
    private Integer id;
}
