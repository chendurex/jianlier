package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@ApiModel("新增教育背景")
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkExpInsertParam extends WorkExpParam {

    @ApiModelProperty(notes = "简历ID", example = "1", required = true)
    @Min(value= 1, message = "简历ID必须大于1")
    private Integer resumeId;

}
