package com.jianli.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExpParam {
    @Min(value = 1, message = "用户ID必须大于1")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @NotBlank
    @Length(min = 1, max = 50, message = "公司名称过长")
    @ApiModelProperty(notes = "公司名称", example = "吹牛公司", required = true)
    private String corp;
    @NotBlank
    @Length(min = 1, max = 50, message = "职位说明过长")
    @ApiModelProperty(notes = "担任职位", example = "吹牛大队队长", required = true)
    private String position;
    @NotBlank
    @Length(min = 1, max = 1000, message = "工作简介过长")
    @ApiModelProperty(notes = "工作简介", example = "帮老板在客户面前吹牛，增加公司逼格", required = true)
    private String description;
    @NotBlank
    @Length(min = 1, max = 20, message = "工作开始时间过长")
    @ApiModelProperty(notes = "工作开始时间", example = "2019-12-03", required = true)
    private String startTime;
    @NotBlank
    @Length(min = 1, max = 20, message = "工作结束时间过长")
    @ApiModelProperty(notes = "工作结束时间", example = "2019-12-03", required = true)
    private String endTime;
}
