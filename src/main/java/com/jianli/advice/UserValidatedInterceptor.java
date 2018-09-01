package com.jianli.advice;

import com.jianli.commons.BeanUtils;
import com.jianli.commons.StringUtils;
import com.jianli.response.DataResult;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证当前操作是否属于当前登录用户，如果不是则直接提示错误
 * @author chendurex
 * @date 2018-09-01 20:17
 */
@Slf4j
//@ControllerAdvice
public class UserValidatedInterceptor extends HandlerInterceptorAdapter implements ResponseBodyAdvice {
    @Autowired
    private UserService userService;
    @Value("${spring.user.backup}")
    private String backup;
    private static final String BACKUP = "backup";
    private static final String UID = "uid";
    private static final String OPENID = "ticket";
    private ThreadLocal<BackupValue> namedThreadLocal = new NamedThreadLocal<>("uid-named");
    private class BackupValue {
        private String backup;
        private Integer uid;

        BackupValue(String backup, Integer uid) {
            this.backup = backup;
            this.uid = uid;
        }

        Integer getUid() {
            return this.uid;
        }

        String getBackup() {
            return this.backup;
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!request.getRequestURI().startsWith("/resume")) {
            return true;
        }
        String backup = request.getHeader(BACKUP);
        if (isBackup(backup)) {
            return true;
        } else {
            String uid = request.getHeader(UID);
            if (StringUtils.isEmpty(uid)) {
                return false;
            }
            namedThreadLocal.set(new BackupValue(backup, Integer.valueOf(uid)));
            return userService.isOwner(Integer.valueOf(uid), request.getHeader(OPENID));
        }
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        log.info("Response Body Params: {}", BeanUtils.deepPrint(body));
        BackupValue backupValue = namedThreadLocal.get();
        namedThreadLocal.remove();
        if (backupValue != null && !isBackup(backupValue.getBackup()) && (body instanceof DataResult)) {
            if (((DataResult)body).getUid() != null && !((DataResult)body).getUid().equals(backupValue.getUid())) {
                return ResUtils.fail("不允许操作非本人数据");
            }
        }
        return body;
    }

    private boolean isBackup(String backup) {
        return this.backup.equals(backup);
    }
}
