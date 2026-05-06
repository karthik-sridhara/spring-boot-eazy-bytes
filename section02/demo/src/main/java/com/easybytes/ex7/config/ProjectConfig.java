package com.easybytes.ex7.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyBeanRegistry.class})
@ComponentScan(basePackages = {"com.easybytes.ex7.bean"})
public class ProjectConfig {
}
