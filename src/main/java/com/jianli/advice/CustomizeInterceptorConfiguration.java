package com.jianli.advice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chen
 * @date 2018/8/04 9:37
 */
@Configuration
public class CustomizeInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor customSysLogInterceptor() {
        return new CustomizeSysLogInterceptor();
    }

    @Bean
    public HandlerInterceptor userValidatedInterceptor() {
        return new UserValidatedInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customSysLogInterceptor());
        registry.addInterceptor(userValidatedInterceptor()).addPathPatterns("/resume/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*");
    }

}