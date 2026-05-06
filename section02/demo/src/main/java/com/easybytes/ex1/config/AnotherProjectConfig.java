package com.easybytes.ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AnotherProjectConfig {

    @Bean
    @Primary
    String hello(){
        return "Hello world";
    }

    @Bean("app-name")
    String getAppName(){
        return "Demo";
    }
}
