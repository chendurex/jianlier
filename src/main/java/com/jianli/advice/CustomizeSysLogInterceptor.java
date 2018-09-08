package com.jianli.advice;

import com.jianli.commons.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chen
 * date 2018/8/2 20:39
 */
public class CustomizeSysLogInterceptor extends HandlerInterceptorAdapter {
    private static final int SLOW_TIME = 3000;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        startTimeThreadLocal.set(System.currentTimeMillis());
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request Start And RequestPath Is: {}", getRealPath(request));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        Map<String, String[]> params = request.getParameterMap();
        long consumeTime = getConsumeTime();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Params: {}", BeanUtils.deepPrint(params));
            LOGGER.info("End: {}. executeTime: {} ms.", getRealPath(request), consumeTime);
        }

        if (consumeTime > SLOW_TIME) {
            LOGGER.warn("slow api ,{} executeTime {} ms", getRealPath(request), consumeTime);
        }
    }

    private String getRealPath(HttpServletRequest request) {
        String path = request.getServletPath();
        return (path == null || path.trim().length() == 0) ? request.getRequestURI() : path;
    }

    private long getConsumeTime() {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        return endTime - beginTime;
    }

}
