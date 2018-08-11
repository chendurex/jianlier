package com.jianli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@JsonIgnoreProperties(value = {"refreshToken", "accessToken", "unionId", "createTime", "modifyTime"})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(notes = "用户ID，修改用户信息时需要填写")
    private Integer id;
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
