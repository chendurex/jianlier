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

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomWorkExpParam {
    @Min(value = 1, message = "用户ID必须大于1")
    @ApiModelProperty(notes = "用户ID", example = "1", required = true)
    private Integer uid;
    @NotBlank(message = "自定义工作经历名称不能为空")
    @Length(min = 1, max = 1000, message = "自定义项目名称过长")
    @ApiModelProperty(notes = "自定义工作经历名称", example = "在公司吹牛经历", required = true)
    private String name;
    @NotBlank(message = "职位说明不能为空")
    @Length(min = 1, max = 1000, message = "职位说明过长")
    @ApiModelProperty(notes = "担任职位", example = "吹牛大队队长", required = true)
    private String position;
    @NotBlank(message = "自定义工作经历简介不能为空")
    @Length(min = 1, max = 5000, message = "工作简介过长")
    @ApiModelProperty(notes = "自定义工作经历简介", example = "帮老板在客户面前吹牛，增加公司逼格", required = true)
    private String description;
    @NotBlank(message = "自定义工作经历标题不能为空")
    @Length(min = 1, max = 5000, message = "自定义项目标题过长")
    @ApiModelProperty(notes = "自定义工作经历标题", example = "增加公司逼格经历", required = true)
    private String title;
    @Min(value = 0, message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;

    @NotBlank(message = "工作开始时间不能为空")
    @Length(min = 1, max = 1000, message = "工作开始时间过长")
    @ApiModelProperty(notes = "工作开始时间", example = "2019-12-03", required = true)
    private String startTime;
    @NotBlank(message = "工作结束时间不能为空")
    @Length(min = 1, max = 1000, message = "工作结束时间过长")
    @ApiModelProperty(notes = "工作结束时间", example = "2019-12-03", required = true)
    private String endTime;

    public CustomWorkExp toCustomWorkExp() {
        return BeanUtils.copy(this, CustomWorkExp.class);
    }

}
