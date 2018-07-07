package com.jianli.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author chendurex
 * @description 在spring 容器初始化之后，bean实例生成之前注入资源
 * @date 2018-04-01 01:50
 */
@Slf4j
@Configuration
public class SpringResourcesInitializer implements ApplicationContextInitializer {
    private static final String PRO_RESOURCES = "/usr/local/tools/config.properties";
    private static final String DEFAULT_RESOURCES = "/usr/local/src/tools/config.properties";
    private static final String DEFAULT_PRODUCT_PREFIX = "product";
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        loadExternal();
    }

    private void loadExternal() {
        try {
            Properties properties = System.getProperties();
            if (properties.getProperty(DEFAULT_PRODUCT_PREFIX) != null) {
                properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(PRO_RESOURCES))));
            } else {
                properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(DEFAULT_RESOURCES))));
            }
        } catch (IOException e) {
            log.error("读取资源文件失败，", e);
            throw new RuntimeException(e);
        }
    }
}
