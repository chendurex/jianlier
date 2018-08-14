package com.jianli.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-08-11 23:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSummaryParam {

    @Min(value= 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    @ApiModelProperty(notes = "简历ID", example = "1", required = true)
    private Integer resumeId;

    @Length(max = 5000, message = "个人简介过长")
    @ApiModelProperty(notes = "个人简介", example = "专业吹牛三十年，不服来战")
    private String summary;

}
