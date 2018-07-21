package com.jianli.wechat;

/**
 * @author cheny.huang
 * @date 2018-07-21 15:01.
 */
public interface AuthInvoker {

    /**
     * 跳转至第三方登录系统
     */
    void redirect();

    /**
     * 验证当前请求是否本系统产生的合法请求
     * @param state
     * @return
     */
    boolean valid(String state);

    /**
     * 获取accessToken
     * @param code
     * @return
     */
    AccessTokenDTO getAccessToken(String code);

    /**
     * 获取用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    UserInfoDTO getUserInfo(String accessToken, String refreshToken, String openid);
}
