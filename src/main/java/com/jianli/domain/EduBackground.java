package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author chendurex
 * @date 2018-06-18 12:26
 */
@ApiModel(value = "教育背景")
@Entity(name = "edu_background")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduBackground {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(notes = "唯一ID，修改、删除教育背景时需要填写")
    private Integer id;
    @Min(value = 1, message = "用户ID必须大于0")
    @ApiModelProperty(notes = "用户ID", required = true)
    @Transient
    private Integer uid;
    private Integer modifyUid;
    @Column(updatable = false)
    private Integer createUid;
    @Column(updatable = false)
    private Timestamp createTime;
    private Timestamp modifyTime;
    @ApiModelProperty(notes = "简历ID，新增简历的时候需要挂在到某个简历下")
    @Column(updatable = false)
    private Integer resumeId;
    @NotBlank
    @Length(max = 100, message = "学院名称过长")
    @ApiModelProperty(notes = "学院", required = true)
    private String college;
    @NotBlank
    @Length(max = 50, message = "专业名称过长")
    @ApiModelProperty(notes = "专业", required = true)
    private String professional;
    @NotBlank
    @Length(max = 20, message = "文凭名称过长")
    @ApiModelProperty(notes = "文凭", required = true)
    private String diploma;
    @NotBlank
    @Length(max = 50, message = "教育开始时间过长")
    @ApiModelProperty(notes = "开始时间", required = true)
    private String startTime;
    @NotBlank
    @Length(max = 50, message = "教育结束时间过长")
    @ApiModelProperty(notes = "结束时间", required = true)
    private String endTime;

    public boolean submit() {
        this.createTime = Timestamp.from(Instant.now());
        this.modifyTime = createTime;
        this.createUid = uid;
        this.modifyUid = uid;
        id = null;
        return resumeId != null;
    }

    public boolean modify() {
        this.modifyTime = Timestamp.from(Instant.now());
        this.modifyUid = uid;
        resumeId = null;
        return id != null;
    }
}
