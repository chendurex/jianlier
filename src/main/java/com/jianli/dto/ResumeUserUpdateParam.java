package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "修改简历信息")
public class ResumeUserUpdateParam extends ResumeParam {
    @Min(value = 1, message = "用户ID必须大于0")
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;

    @ApiModelProperty(notes = "唯一ID", example = "1")
    @Min(value = 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    private Integer id;
}
