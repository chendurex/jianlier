package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-23 22:07
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "插入技能熟练度")
@Data
public class SkillMaturityInsertParam extends SkillMaturityParam {

    @Min(value= 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    @ApiModelProperty(notes = "简历ID", example = "1", required = true)
    private Integer resumeId;

}
