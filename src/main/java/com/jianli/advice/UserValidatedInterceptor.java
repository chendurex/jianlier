package com.jianli.advice;

import com.jianli.commons.BeanUtils;
import com.jianli.exception.AuthenticException;
import com.jianli.response.DataResult;
import com.jianli.response.ResUtils;
import com.jianli.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * 验证当前操作是否属于当前登录用户，如果不是则直接提示错误
 * @author chendurex
 * @date 2018-09-01 20:17
 */
@Slf4j
@ControllerAdvice
public class UserValidatedInterceptor extends HandlerInterceptorAdapter implements ResponseBodyAdvice {
    @Autowired
    private UserService userService;
    @Value("${spring.user.backup}")
    private String backup;
    private static final String BACKUP = "backup";
    private static final String UID = "uid";
    private static final String TICKET = "ticket";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Enumeration<String> e = request.getHeaderNames();
         while(e.hasMoreElements()) {
             String v = e.nextElement();
            log.info("--------{}------{}", v, request.getHeader(v));
        }
        log.info("Request Start And uid: {}, ticket:{}", GlobalVariable.uid(), GlobalVariable.ticket());
        if (!request.getRequestURI().startsWith("/resume")) {
            return true;
        }
        GlobalVariable.set(request.getHeader(UID), request.getHeader(TICKET), request.getHeader(BACKUP));
        if (isBackup()) {
            return true;
        }
        boolean suc = userService.isOwner(GlobalVariable.uid(), GlobalVariable.ticket());
        if (suc) {
            return true;
        }
        throw new AuthenticException("请传入用户ID和凭证");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        log.info("Response Body Params: {}", BeanUtils.deepPrint(body));
        if (!isBackup() && (body instanceof DataResult)) {
            if (((DataResult)body).getUid() != null && !((DataResult)body).getUid().equals(GlobalVariable.uid())) {
                return ResUtils.fail("不允许操作非本人数据");
            }
        }
        return body;
    }

    private boolean isBackup() {
        return this.backup.equals(GlobalVariable.backup());
    }
}
