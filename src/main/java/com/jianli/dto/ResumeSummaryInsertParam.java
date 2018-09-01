package com.jianli.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chendurex
 * @date 2018-08-11 23:52
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "新增自我介绍")
@Data
public class ResumeSummaryInsertParam extends ResumeSummaryParam {

    @ApiModelProperty(notes = "排序值", example = "1")
    private Integer sort;

}
