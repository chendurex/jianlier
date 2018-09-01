package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.WorkExp;
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
 * @date 2018-06-18 12:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExpParam {
    @NotNull(message = "公司名称不能为空")
    @Length(max = 1000, message = "公司名称过长")
    @ApiModelProperty(notes = "公司名称", example = "吹牛公司", required = true)
    private String corp;
    @NotNull(message = "职位说明不能为空")
    @Length(max = 1000, message = "职位说明过长")
    @ApiModelProperty(notes = "担任职位", example = "吹牛大队队长", required = true)
    private String position;
    @NotNull(message = "工作简介不能为空")
    @Length(max = 5000, message = "工作简介过长")
    @ApiModelProperty(notes = "工作简介", example = "帮老板在客户面前吹牛，增加公司逼格", required = true)
    private String description;
    @NotNull(message = "工作开始时间不能为空")
    @Length(max = 1000, message = "工作开始时间过长")
    @ApiModelProperty(notes = "工作开始时间", example = "2019-12-03", required = true)
    private String startTime;
    @NotNull(message = "工作结束时间不能为空")
    @Length(max = 1000, message = "工作结束时间过长")
    @ApiModelProperty(notes = "工作结束时间", example = "2019-12-03", required = true)
    private String endTime;
    @Min(value = 1, message = "排序值必须大于1")
    @NotNull(message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;

    public WorkExp toWorkExp() {
        return BeanUtils.copy(this, WorkExp.class);
    }
}
