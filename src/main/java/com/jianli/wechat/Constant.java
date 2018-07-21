package com.jianli.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author cheny.huang
 * @date 2018-07-21 14:31.
 */
class Constant {
    private static final String QR_CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?" +
            "appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    static String assembleQrCodeUrl(String appId, String redirectUri, String state) {
        try {
            return String.format(QR_CODE_URL, appId, URLEncoder.encode(redirectUri, "UTF-8"), state);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    static String assembleAccessTokenUrl(String appId, String secret, String code) {
        return String.format(ACCESS_TOKEN_URL, appId, secret, code);
    }

    static String assembleRefreshTokenUrl(String appId, String refreshToken) {
        return String.format(REFRESH_TOKEN_URL, appId, refreshToken);
    }

    static String assembleUserInfoUrl(String accessToken, String openId) {
        return String.format(USER_INFO_URL, accessToken, openId);
    }
}
