package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.CustomResumeDesc;
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
 * @date 2018-06-18 11:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomResumeDescParam {

    @Min(value = 0, message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    @NotNull(message = "内容不能为空")
    @ApiModelProperty(notes = "内容", example = "内容")
    private String txt;

    @NotNull(message = "自定义简历描述标题不能为空")
    @Length(max = 1000, message = "自定义简历描述标题过长")
    @ApiModelProperty(notes = "自定义简历描述标题", example = "增加公司逼格经历", required = true)
    private String title;

    public CustomResumeDesc toCustomResumeDesc() {
        return BeanUtils.copy(this, CustomResumeDesc.class);
    }
}
