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
        System.out.println(authInvoker.getAccessToken("fffffffff"));
    }

}
