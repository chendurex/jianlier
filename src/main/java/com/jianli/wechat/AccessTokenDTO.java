package com.jianli.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cheny.huang
 * @date 2018-07-21 15:06.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccessTokenDTO {
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openid;
    private String scope;
}
