package com.easybytes.ex5.config;


import com.easybytes.ex5.bean.Person;
import com.easybytes.ex5.bean.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"com.easybytes.ex5.bean"})
public class ProjectConfig { }
