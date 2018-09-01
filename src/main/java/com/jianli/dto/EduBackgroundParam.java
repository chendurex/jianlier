package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
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
 * @date 2018-06-18 12:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduBackgroundParam {
    @NotNull(message = "学院名称不能为空")
    @Length(max = 1000, message = "学院名称过长")
    @ApiModelProperty(notes = "学院", example = "吹牛学院", required = true)
    private String college;
    @NotNull(message = "专业名称不能为空")
    @Length(max = 1000, message = "专业名称过长")
    @ApiModelProperty(notes = "专业", example = "吹牛专业", required = true)
    private String professional;
    @NotNull(message = "文凭名称不能为空")
    @Length(max = 1000, message = "文凭名称过长")
    @ApiModelProperty(notes = "文凭", example = "吹牛博士学位", required = true)
    private String diploma;
    @NotNull(message = "教育背景介绍不能为空")
    @Length(max = 5000, message = "教育背景介绍过长")
    @ApiModelProperty(notes = "教育背景介绍", example = "我在学校学到了吹牛技术", required = true)
    private String summary;
    @NotNull(message = "教育开始时间不能为空")
    @Length(max = 1000, message = "教育开始时间过长")
    @ApiModelProperty(notes = "开始时间", example = "2011-12-03", required = true)
    private String startTime;
    @NotNull(message = "教育结束时间不能为空")
    @Length(max = 1000, message = "教育结束时间过长")
    @ApiModelProperty(notes = "结束时间", example = "2018-09-01", required = true)
    private String endTime;
    @Min(value = 1, message = "排序值必须大于1")
    @NotNull(message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;

    public EduBackground toEduBackground() {
        return BeanUtils.copy(this, EduBackground.class);
    }
}
