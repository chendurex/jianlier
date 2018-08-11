package com.jianli.service;

import com.jianli.domain.User;
import com.jianli.response.ResResult;

/**
 * @author chendurex
 * @date 2018-07-22 09:36
 */
public interface UserService {
    /**
     * 微信回调
     * @return
     */
    User submit(String code, String state);

    /**
     * 用户通过openid获取用户信息
     * @param openid
     * @return
     */
    ResResult getInfoByOpenid(String openid);
}
