package com.jianli;

import com.jianli.wechat.AuthInvoker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cheny.huang
 * @date 2018-07-21 16:38.
 */
public class WechatTest extends BaseTest {
    @Autowired
    private AuthInvoker authInvoker;
    @Test
    public void testGetAccessToken() {
        System.out.println(authInvoker.getAccessToken("081Z4Pru0JeZcc1vKfvu0oKTru0Z4PrD", "v"));
    }

    @Test
    public void testGetUserInfo() {
        String v = "{\"access_token\":\"11_tgxEkVyLVkLnta9BygIJ5tTjuSDvnntgH4qcMnP4hL7jtQ09SfEKzaltIhFnlpsfZq4XwVA109yOAhAujVLTOg\",\"expires_in\":7200,\"refresh_token\":\"11_kHJt4aU2U3zf3aV2cAT98FHkWpEJ03wpcwhqviG4nA_TfuXCeT2YYkBg1HKE6aTtWOnot4iL4a3IZ-hG-5-htQ\",\"openid\":\"ow0EF1jao7b8j9jhquxVkad4SHOU\",\"scope\":\"snsapi_login\",\"unionid\":\"o05cB1XH7XQpDF5TmDHNexBH33bI\"}";
        System.out.println(authInvoker.getUserInfo(
                "11_tgxEkVyLVkLnta9BygIJ5tTjuSDvnntgH4qcMnP4hL7jtQ09SfEKzaltIhFnlpsfZq4XwVA109yOAhAujVLTOg", "ow0EF1jao7b8j9jhquxVkad4SHOU"));
    }

}
