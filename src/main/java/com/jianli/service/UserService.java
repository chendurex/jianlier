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
     * 用户通过ticket获取用户信息
     * @param ticket
     * @return
     */
    ResResult getInfoByTicket(String ticket);

    /**
     * 刷新ticket
     * @param ticket
     * @return
     */
    ResResult refreshTicket(String ticket);

    /**
     * 返回当前id是否属于当前用户id
     * @param id
     * @param openid
     * @return
     */
    boolean isOwner(int id, String openid);
}
