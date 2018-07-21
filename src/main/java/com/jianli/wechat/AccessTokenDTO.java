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
class AccessTokenDTO {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String errcode;
    private String errmsg;
}
