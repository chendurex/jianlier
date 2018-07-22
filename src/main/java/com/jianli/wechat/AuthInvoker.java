package com.jianli.wechat;

import com.jianli.dto.UserParam;

/**
 * @author cheny.huang
 * @date 2018-07-21 15:01.
 */
public interface AuthInvoker {

    /**
     * 获取跳转至第三方登录系统的URL
     */
    String redirect();

    /**
     * 获取accessToken
     * @param code
     * @return
     */
    UserParam getAccessToken(String code, String state);

    /**
     * 获取用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    UserParam getUserInfo(String accessToken, String openid);
}
