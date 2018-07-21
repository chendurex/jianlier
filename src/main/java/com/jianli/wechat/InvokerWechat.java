package com.jianli.wechat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
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
        ClientResponse response = null;
        try {
            WebResource webResource = Client.create().resource(url);
            WebResource.Builder requestBuilder = webResource.getRequestBuilder();
            response = requestBuilder.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            if (response.getStatus() == Response.Status.OK.getStatusCode() && response.hasEntity()) {
                // 微信返回的content-type是text/plain，但是实际的内容是json格式的，所以重新转换下格式为json
                response.getHeaders().put("Content-Type", Collections.singletonList(MediaType.APPLICATION_JSON));
                return response.getEntity(AccessTokenDTO.class);
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
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
