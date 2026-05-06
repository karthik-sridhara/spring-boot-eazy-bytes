package com.easybytes.ex3.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.easybytes.ex3.bean")
public class ProjectConfig {

//    @Bean
//    @Primary
//    @Description("this a car with number plate MH-12-AB-0001")
//    public Vehicle vehicle1(){
//        Vehicle vehicle =  new Vehicle();
//        vehicle.setNumberPlate("MH-12-AB-0001");
//        vehicle.setType("Car");
//        return vehicle;
//    }




}
