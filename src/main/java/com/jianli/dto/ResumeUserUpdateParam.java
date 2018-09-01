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

    @ApiModelProperty(notes = "唯一ID", example = "1")
    @Min(value = 1, message = "简历ID必须大于1")
    @NotNull(message = "简历ID不能为空")
    private Integer id;
}
