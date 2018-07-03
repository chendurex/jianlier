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
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-18 12:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduBackgroundParam {

    @Min(value = 1, message = "用户ID必须大于1")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @NotBlank(message = "学院名称不能为空")
    @Length(max = 100, message = "学院名称过长")
    @ApiModelProperty(notes = "学院", example = "吹牛学院", required = true)
    private String college;
    @NotBlank(message = "专业名称不能为空")
    @Length(max = 50, message = "专业名称过长")
    @ApiModelProperty(notes = "专业", example = "吹牛专业", required = true)
    private String professional;
    @NotBlank(message = "文凭名称不能为空")
    @Length(max = 20, message = "文凭名称过长")
    @ApiModelProperty(notes = "文凭", example = "吹牛博士学位", required = true)
    private String diploma;
    @NotBlank(message = "教育背景介绍不能为空")
    @Length(max = 20, message = "教育背景介绍过长")
    @ApiModelProperty(notes = "教育背景介绍", example = "我在学校学到了吹牛技术", required = true)
    private String summary;
    @NotBlank(message = "教育开始时间不能为空")
    @Length(max = 50, message = "教育开始时间过长")
    @ApiModelProperty(notes = "开始时间", example = "2011-12-03", required = true)
    private String startTime;
    @NotBlank(message = "教育结束时间不能为空")
    @Length(max = 50, message = "教育结束时间过长")
    @ApiModelProperty(notes = "结束时间", example = "2018-09-01", required = true)
    private String endTime;

    public EduBackground toEduBackground() {
        return BeanUtils.copy(this, EduBackground.class);
    }
}
