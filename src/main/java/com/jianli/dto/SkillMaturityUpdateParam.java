package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-23 22:07
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新技能熟练度")
@Data
public class SkillMaturityUpdateParam extends SkillMaturityParam{

    @ApiModelProperty(notes = "唯一ID", example = "1", required = true)
    @Min(value = 1, message = "技能熟练度ID必须大于1")
    private Integer id;
}
