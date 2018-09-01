package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cheny.huang
 * @date 2018-08-24 19:44.
 */
@ApiModel(value = "上传HTML内容")
@Data
public class UploadResumeDTO {
    @Min(value = 1, message = "用户ID必须大于1")
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @Min(value = 1, message = "简历ID大于1")
    @NotNull(message = "简历ID不能为空")
    @ApiModelProperty(notes = "简历ID", example = "1", required = true)
    private Integer resumeId;
    @NotNull(message = "html内容")
    @ApiModelProperty(notes = "html内容", example = "<html><body>test</body></html>", required = true)
    private String html;

}
