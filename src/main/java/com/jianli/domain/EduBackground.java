package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-18 12:26
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "教育背景")
@Entity(name = "edu_background")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduBackground extends MetadataInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "唯一ID，修改、删除教育背景时需要填写")
    private int id;
    @ApiModelProperty(notes = "简历ID，新增简历的时候需要挂在到某个简历下")
    private int resumeId;
    @NotBlank
    @Max(value = 100, message = "学院名称过长")
    @ApiModelProperty(notes = "学院", required = true)
    private String college;
    @NotBlank
    @Max(value = 50, message = "专业名称过长")
    @ApiModelProperty(notes = "专业", required = true)
    private String professional;
    @NotBlank
    @Max(value = 20, message = "文凭名称过长")
    @ApiModelProperty(notes = "文凭", required = true)
    private String diploma;
    @NotBlank
    @Max(value = 50, message = "教育开始时间过长")
    @ApiModelProperty(notes = "开始时间", required = true)
    private String startTime;
    @NotBlank
    @Max(value = 50, message = "教育结束时间过长")
    @ApiModelProperty(notes = "结束时间", required = true)
    private String endTime;
}
