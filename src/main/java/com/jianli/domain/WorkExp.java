package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@ApiModel(value = "工作经历")
@Entity(name = "work_exp")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExp  {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(insertable = false, updatable = false)
    @ApiModelProperty(notes = "唯一ID，修改、删除工作经历时需要填写")
    private Integer id;
    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", required = true)
    @Column(insertable = false, updatable = false)
    private Integer uid;
    private Integer modifyUid;
    private Integer createUid;
    private Timestamp createTime;
    private Timestamp modifyTime;

    @ApiModelProperty(notes = "简历ID，新增简历的时候需要挂在到某个简历下")
    private Integer resumeId;
    @NotBlank
    @Length(min = 1, max = 50, message = "公司名称过长")
    @ApiModelProperty(notes = "公司名称", required = true)
    private String corp;
    @NotBlank
    @Length(min = 1, max = 50, message = "职位说明过长")
    @ApiModelProperty(notes = "担任职位", required = true)
    private String position;
    @NotBlank
    @Length(min = 1, max = 1000, message = "工作简介过长")
    @ApiModelProperty(notes = "工作简介", required = true)
    private String desc;

    public boolean submit() {
        return id == null && resumeId != null;
    }

    public boolean modify() {
        resumeId = null;
        return id != null;
    }
}
