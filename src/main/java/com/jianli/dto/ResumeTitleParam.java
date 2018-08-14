package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cheny.huang
 * @date 2018-08-06 18:58.
 */
@Data
@ApiModel(value = "修改简历标题")
public class ResumeTitleParam {
    @ApiModelProperty(notes = "简历ID", example = "1")
    @NotNull(message = "简历ID不能为空")
    @Min(value = 1, message = "简历ID必须大于1")
    private Integer resumeId;

    @ApiModelProperty(notes = "标题", example = "哈哈哈")
    @Length(min = 1, max = 1000, message = "标题不能为空或者过长")
    private String title;

    @ApiModelProperty(notes = "排序值", example = "1")
    @Min(value = 0, message = "排序值不能小于0")
    @NotNull(message = "排序值不能为空")
    private Integer sort;
}
