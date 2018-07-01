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
 * @date 2018-06-18 12:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduBackgroundParam {

    @Min(value = 1, message = "用户ID必须大于1")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @NotBlank
    @Length(max = 100, message = "学院名称过长")
    @ApiModelProperty(notes = "学院", example = "吹牛学院", required = true)
    private String college;
    @NotBlank
    @Length(max = 50, message = "专业名称过长")
    @ApiModelProperty(notes = "专业", example = "吹牛专业", required = true)
    private String professional;
    @NotBlank
    @Length(max = 20, message = "文凭名称过长")
    @ApiModelProperty(notes = "文凭", example = "吹牛博士学位", required = true)
    private String diploma;
    @NotBlank
    @Length(max = 50, message = "教育开始时间过长")
    @ApiModelProperty(notes = "开始时间", example = "2011-12-03", required = true)
    private String startTime;
    @NotBlank
    @Length(max = 50, message = "教育结束时间过长")
    @ApiModelProperty(notes = "结束时间", example = "2018-09-01", required = true)
    private String endTime;
    @NotBlank
    @Length(min = 1, max = 50, message = "教育背景标题太长或者不能为空")
    @ApiModelProperty(notes = "教育背景标题", example = "教育背景", required = true)
    private String title;

}
