package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.ResumeModule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "修改简历模块")
public class ResumeModuleUpdateParam {

    @Min(value = 1, message = "模块ID必须大于1")
    @ApiModelProperty(notes = "模块ID", example = "1", required = true)
    private Integer id;

    @Min(value = 1, message = "用户ID必须大于1")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;

    @Min(value = 0, message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;

    @NotBlank
    @ApiModelProperty(notes = "内容", example = "内容")
    private String txt;

    public ResumeModule toResumeModule() {
        return BeanUtils.copy(this, ResumeModule.class);
    }
}
