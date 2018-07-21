package com.jianli.wechat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author cheny.huang
 * @date 2018-07-21 14:50.
 */
@Component
public class InvokerWechat implements AuthInvoker {
    private final String appId;
    private final String secret;
    private final String redirectUri;
    private static final String CACHE_PREFIX = "wechat:code:";
    public InvokerWechat(@Value("${wechat.appid}") String appId, @Value("${wechat.secret}") String secret,
                         @Value("${wechat.redirectUri}") String redirectUri) {
        this.appId = appId;
        this.secret = secret;
        this.redirectUri = redirectUri;
    }

    private static final Cache<String, Integer> CODE_CACHE =
            CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(1, TimeUnit.MINUTES).build();

    @Override
    public void redirect() {
        final String key = CACHE_PREFIX + UUID.randomUUID().toString();
        CODE_CACHE.put(key, 1);
        String url = Constant.assembleQrCodeUrl(appId, redirectUri, key);
        // todo 跳转到远程请求
    }

    @Override
    public boolean valid(String state) {
        return CODE_CACHE.getIfPresent(state) != null;
    }

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        String url = Constant.assembleAccessTokenUrl(appId, secret, code);
        // todo 访问远程请求
        return null;
    }

    @Override
    public UserInfoDTO getUserInfo(String accessToken, String refreshToken, String openid) {
        String url = Constant.assembleUserInfoUrl(accessToken, openid);
        // todo 获取信息
        // 如果accessToken失效，则刷新token再次获取
        String refreshUrl = Constant.assembleRefreshTokenUrl(appId, refreshToken);
        return null;
    }
}
