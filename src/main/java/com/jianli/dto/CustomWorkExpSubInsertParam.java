package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@ApiModel("新增自定义工作经历列表")
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomWorkExpSubInsertParam extends CustomWorkExpSubParam {

    @ApiModelProperty(notes = "简历ID", example = "1", required = true)
    @Min(value= 1, message = "简历ID必须大于1")
    private Integer resumeId;
    @Min(value = 1, message = "自定义工作经历父ID必须大于1")
    @ApiModelProperty(notes = "自定义工作经历父ID", example = "1", required = true)
    private Integer pid;

}
