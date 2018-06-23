package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "新增简历信息")
public class ResumeInsertParam extends ResumeParam {
    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
}
