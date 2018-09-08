package com.jianli.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jianli.commons.BeanUtils;
import com.jianli.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chendurex
 * @date 2018-07-22 09:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties()
public class UserParam {
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
    private Integer expiresTime;
    public User toUser() {
        return BeanUtils.copy(this, User.class);
    }
}
