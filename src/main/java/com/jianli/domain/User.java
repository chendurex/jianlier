package com.jianli.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author chendurex
 * @date 2018-06-18 13:02
 */
@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "用户ID，修改用户信息时需要填写")
    private int id;
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
    @Max(value = 500, message = "个人简介过长")
    @ApiModelProperty(notes = "个人简介")
    private String summary;
}
