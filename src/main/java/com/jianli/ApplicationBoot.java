package com.jianli;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApplicationBoot {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationBoot.class).run(args);
    }
}
