package com.jianli.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author chendurex
 * @date 2018-06-18 13:02
 */
@Data
@Entity(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(notes = "用户ID，修改用户信息时需要填写")
    private Integer id;
    /*private String mobile;
    private String address;
    private String email;
    private String summary;*/
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openid;
    private String scope;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private String unionId;
    @Column(updatable = false)
    private Timestamp createTime;
    private Timestamp modifyTime;


    public void submit() {
        this.createTime = Timestamp.from(Instant.now());
        this.modifyTime = createTime;
    }

    public void modify() {
        this.modifyTime = Timestamp.from(Instant.now());
    }
}
