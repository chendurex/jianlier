package com.jianli.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cheny.huang
 * @date 2018-07-21 15:10.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class UserInfoDTO {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String unionId;
    private String errcode;
    private String errmsg;
}
