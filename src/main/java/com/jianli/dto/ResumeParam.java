package com.jianli.dto;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.Resume;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author chendurex
 * @date 2018-06-18 11:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeParam {

    @Pattern(regexp = "^1([34578])\\d{9}$",message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(notes = "手机号码", example = "13838381438", required = true)
    private String mobile;
    @ApiModelProperty(notes = "地址", example = "惠州第一吹牛村")
    private String address;
    @ApiModelProperty(notes = "微信号，用户绑定的微信号", example = "chendurex@ccc.com")
    private String wechat;
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(notes = "邮箱，用户在需要发送简历时候需要填写", example = "chendurex@gmail.com")
    private String email;
    @Length(max = 5000, message = "个人简介过长")
    @ApiModelProperty(notes = "个人简介", example = "专业吹牛三十年，不服来战")
    private String summary;
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(notes = "姓名", example = "爱吹牛的天线宝宝", required = true)
    private String name;
    @NotBlank(message = "简介标题不能为空")
    @ApiModelProperty(notes = "简介标题", example = "自我介绍", required = true)
    private String summaryTitle;
    @NotBlank(message = "求职意向标题不能为空")
    @ApiModelProperty(notes = "求职意向标题", example = "求职意向", required = true)
    private String objectiveTitle;

    public Resume toResume() {
        return BeanUtils.copy(this, Resume.class);
    }
}
