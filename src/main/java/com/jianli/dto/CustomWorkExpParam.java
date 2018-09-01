package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.CustomWorkExp;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomWorkExpParam {
    @NotNull(message = "自定义标题不能为空")
    @Length(max = 1000, message = "自定义标题过长")
    @ApiModelProperty(notes = "自定义标题", example = "自定义标题", required = true)
    private String title;
    @Min(value = 0, message = "排序值不能为空")
    @NotNull(message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;

    public CustomWorkExp toCustomWorkExp() {
        return BeanUtils.copy(this, CustomWorkExp.class);
    }

}
