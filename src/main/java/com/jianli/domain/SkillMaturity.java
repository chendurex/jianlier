package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author chendurex
 * @date 2018-06-18 12:27
 */
@ApiModel(value = "工作技能")
@Entity(name = "skill_maturity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillMaturity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(notes = "唯一ID，修改、删除时需要填写")
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
    @Length(min = 1, max = 50, message = "技能名称太长")
    @ApiModelProperty(notes = "技能名称", required = true)
    private String skill;
    @Min(value= 1, message = "技能熟练度只能在1-10之间")
    @Max(value = 10, message = "技能熟练度只能在1-10之间")
    @ApiModelProperty(notes = "熟练度", required = true)
    private Integer maturity;

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
