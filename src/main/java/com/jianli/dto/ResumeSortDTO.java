package com.jianli.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cheny.huang
 * @date 2018-08-24 19:44.
 */
@ApiModel(value = "更新简历内部排序")
@Data
public class ResumeSortDTO {
    @Min(value = 1, message = "排序值")
    @NotNull(message = "排序值不能为空")
    @ApiModelProperty(notes = "排序值", example = "1", required = true)
    private Integer sort;
    @Min(value = 1, message = "数据ID必须大于1")
    @NotNull(message = "数据ID不能为空")
    @ApiModelProperty(notes = "数据ID", example = "1", required = true)
    private Integer id;
    @Min(value = 1, message = "排序类型必须大于1")
    @NotNull(message = "排序类型不能为空")
    @ApiModelProperty(notes = "排序类型，1教育背景、2工作经历、3技能熟练度、4自定义工作经历", example = "1", required = true)
    private Integer type;

    @JsonIgnore
    public boolean isEdu() {
        return type == 1;
    }

    @JsonIgnore
    public boolean isExp() {
        return type == 2;
    }

    @JsonIgnore
    public boolean isSkill() {
        return type == 3;
    }

    @JsonIgnore
    public boolean isCusExp() {
        return type == 4;
    }

}
