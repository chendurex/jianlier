package com.jianli.advice;

import com.jianli.commons.StringUtils;
import com.jianli.exception.AuthenticException;
import com.jianli.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证当前操作是否属于当前登录用户，如果不是则直接提示错误
 * @author chendurex
 * @date 2018-09-01 20:17
 */
@Slf4j
@ControllerAdvice
public class UserValidatedInterceptor extends HandlerInterceptorAdapter {
    private static final String UID = "uid";
    private static final String TICKET = "ticket";
    private static final String OPTIONS = "OPTIONS";
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (OPTIONS.equals(request.getMethod())) {
            return true;
        }
        String uid = request.getHeader(UID);
        String ticket = request.getHeader(TICKET);
        log.info("Request Start And uid: {}, ticket:{}", uid, ticket);
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(ticket)) {
            throw new AuthenticException("您的凭证已过期，请重新登录");
        }
        boolean suc = userService.isOwner(StringUtils.toInt(request.getHeader(UID)), request.getHeader(TICKET));
        if (suc) {
            return true;
        }
        throw new AuthenticException("您的凭证已过期，请重新登录");
    }
}
