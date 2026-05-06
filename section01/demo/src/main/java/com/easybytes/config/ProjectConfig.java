package com.easybytes.config;

import com.easybytes.bean.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Vehicle vehicle1(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-1111");
        vehicle.setType("Car");
        return vehicle;
    }

    @Bean
    public Vehicle vehicle2(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-1112");
        vehicle.setType("Car");
        return vehicle;
    }

    @Bean
    public String hello(){
        return "Hello world";
    }

    @Bean
    public int luckyNumber(){
        return 7;
    }


}
