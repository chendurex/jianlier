package com.jianli.advice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chen
 * @date 2018/8/04 9:37
 */
@Configuration
public class CustomInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor customSysLogInterceptor() {
        return new CustomSysLogInterceptor();
    }

    @Bean
    public HandlerInterceptor userValidatedInterceptor() {
        return new UserValidatedInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customSysLogInterceptor());
       // registry.addInterceptor(userValidatedInterceptor());
    }

}