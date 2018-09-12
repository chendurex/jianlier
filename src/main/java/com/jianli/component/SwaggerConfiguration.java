package com.jianli.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chendurex
 * @date 2018-06-19 22:36
 */
@ConditionalOnProperty(prefix = "com.jianlier.boot", name = "pro", havingValue = "false", matchIfMissing = true)
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${spring.swagger.host}")
    private String host;
    @Value("${spring.swagger.default.uid}")
    private String uid;
    @Value("${spring.swagger.default.ticket}")
    private String ticket;
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .host(host)
                .globalOperationParameters(customizeHeader())
                .globalResponseMessage(RequestMethod.GET, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.HEAD, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.OPTIONS, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.PATCH, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.POST, defaultResponseMessage());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("项目文档描述").build();
    }

    private List<ResponseMessage> defaultResponseMessage() {
        return Arrays.asList(new ResponseMessageBuilder().code(400).message("缺少请求参数").build()
                , new ResponseMessageBuilder().code(401).message("未授权的客户端").build()
                , new ResponseMessageBuilder().code(403).message("没有权限访问当前地址").build()
                , new ResponseMessageBuilder().code(404).message("请求路径不对，找不到资源").build()
                , new ResponseMessageBuilder().code(500).message("服务器内部出错").build());
    }


    private List<Parameter> customizeHeader() {
        Parameter uidParameter = new ParameterBuilder()
                .name("uid")
                .modelRef(new ModelRef("int"))
                .parameterType("header")
                .defaultValue(uid)
                .required(true)
                .build();
        Parameter ticketParameter = new ParameterBuilder()
                .name("ticket")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .defaultValue(ticket)
                .required(true)
                .build();
        return Arrays.asList(uidParameter, ticketParameter);
    }
}