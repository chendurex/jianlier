package com.jianli.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@Data
@ApiModel(value = "简历信息")
@Entity(name = "resume")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

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

    @Pattern(regexp = "^1([34578])\\d{9}$",message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(notes = "手机号码", required = true)
    private String mobile;
    @ApiModelProperty(notes = "地址")
    private String address;
    @ApiModelProperty(notes = "微信号，用户绑定的微信号")
    private String wechat;
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(notes = "邮箱，用户在需要发送简历时候需要填写")
    private String email;
    @Length(max = 500, message = "个人简介过长")
    @ApiModelProperty(notes = "个人简介")
    private String summary;

    @Transient
    private List<EduBackground> eduBackgrounds;
    @Transient
    private List<WorkExp> workExps;
    @Transient
    private List<SkillMaturity> skillMaturities;

    public boolean submit() {
        this.createTime = Timestamp.from(Instant.now());
        this.modifyTime = createTime;
        this.createUid = uid;
        this.modifyUid = uid;
        id = null;
        return true;
    }

    public boolean modify() {
        this.modifyTime = Timestamp.from(Instant.now());
        this.modifyUid = uid;
        return id != null;
    }
}
