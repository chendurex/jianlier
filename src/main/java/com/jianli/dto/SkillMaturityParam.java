package com.jianli.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-23 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillMaturityParam {

    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @NotBlank
    @Length(min = 1, max = 50, message = "技能名称太长")
    @ApiModelProperty(notes = "技能名称", example = "吹牛", required = true)
    private String skill;
    @Min(value= 1, message = "技能熟练度只能在1-10之间")
    @Max(value = 10, message = "技能熟练度只能在1-10之间")
    @ApiModelProperty(notes = "熟练度", example = "10", required = true)
    private Integer maturity;

}
