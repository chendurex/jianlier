package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-08-11 23:52
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "新增自我介绍")
@Data
public class ResumeSummaryInsertParam extends ResumeSummaryParam {

    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    @NotNull(message = "用户ID不能为空")
    private Integer uid;

    @ApiModelProperty(notes = "排序值", example = "1")
    private Integer sort;

}
