package com.jianli.wechat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jianli.commons.StringUtils;
import com.jianli.dto.UserParam;
import com.jianli.dto.WechatLoginParamVO;
import com.jianli.exception.WechatException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class InvokerWechat implements AuthInvoker {
    private final String appId;
    private final String secret;
    private final String redirectUri;
    private static final String CACHE_PREFIX = "wechat:code:";
    public InvokerWechat(@Value("${wechat.appid}") String appId, @Value("${wechat.secret}") String secret,
                         @Value("${wechat.redirect.uri}") String redirectUri) {
        this.appId = appId;
        this.secret = secret;
        this.redirectUri = redirectUri;
    }

    private static final Cache<String, Integer> CODE_CACHE =
            CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES).build();

    @Override
    public String redirect() {
        final String key = CACHE_PREFIX + UUID.randomUUID().toString();
        CODE_CACHE.put(key, 1);
        return Constant.assembleQrCodeUrl(appId, redirectUri, key);
    }

    @Override
    public WechatLoginParamVO getWechatParam() {
        final String key = CACHE_PREFIX + UUID.randomUUID().toString();
        CODE_CACHE.put(key, 1);
        return WechatLoginParamVO.builder().appId(appId).redirectUri(redirectUri).scope("snsapi_login").state(key).build();
    }

    @Override
    public UserParam getAccessToken(String code, String state) {
        if (StringUtils.isEmpty(CODE_CACHE.getIfPresent(state))) {
            log.warn("state已经失效");
            throw new WechatException("请求已经过期，请重新登录");
        }
        String url = Constant.assembleAccessTokenUrl(appId, secret, code);
        ClientResponse response = null;
        try {
            WebResource webResource = Client.create().resource(url);
            WebResource.Builder requestBuilder = webResource.getRequestBuilder();
            response = requestBuilder.get(ClientResponse.class);
            log.info("请求wechat,status:{}", response.getStatus());

            if (response.getStatus() == Response.Status.OK.getStatusCode() && response.hasEntity()) {
                // 微信返回的content-type是text/plain，但是实际的内容是json格式的，所以重新转换下格式为json
                response.getHeaders().put("Content-Type", Collections.singletonList(MediaType.APPLICATION_JSON));
                AccessTokenDTO at = response.getEntity(AccessTokenDTO.class);
                if (StringUtils.isNotEmpty(at.getErrcode())) {
                    log.warn("获取微信code失败, errorCode:{}, msg:{}", at.getErrcode(), at.getErrmsg());
                    throw new WechatException("微信登录失败，msg"+at.getErrmsg());
                }
                return UserParam.builder().accessToken(at.getAccess_token()).expiresIn(at.getExpires_in())
                        .openid(at.getOpenid()).refreshToken(at.getRefresh_token()).scope(at.getScope()).build();
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        throw new WechatException("登录微信失败，请重新登录");
    }

    @Override
    public UserParam getUserInfo(String accessToken, String openid) {
        String url = Constant.assembleUserInfoUrl(accessToken, openid);
        ClientResponse response = null;
        try {
            WebResource webResource = Client.create().resource(url);
            WebResource.Builder requestBuilder = webResource.getRequestBuilder();
            response = requestBuilder.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            log.info("请求wechat,status:{}", response.getStatus());
            if (response.getStatus() == Response.Status.OK.getStatusCode() && response.hasEntity()) {
                response.getHeaders().put("Content-Type", Collections.singletonList(MediaType.APPLICATION_JSON));
                UserInfoDTO user = response.getEntity(UserInfoDTO.class);
                if (StringUtils.isNotEmpty(user.getErrcode())) {
                    log.warn("获取微信用户信息失败, errorCode:{}, msg:{}", user.getErrcode(), user.getErrmsg());
                    throw new WechatException("获取微信信息失败，msg"+user.getErrmsg());
                }
                return UserParam.builder().nickname(user.getNickname()).headImgUrl(user.getHeadimgurl()).country(user.getCountry())
                        .province(user.getProvince()).city(user.getCity()).sex(user.getSex()).unionId(user.getUnionid()).build();
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        throw new WechatException("获取微信用户信息失败，请重新登录");
    }

}
