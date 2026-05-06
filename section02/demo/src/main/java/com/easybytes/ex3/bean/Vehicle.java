package com.easybytes.ex3.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

//    public class Vehicle implements InitializingBean,DisposableBean
    private String numberPlate;
    private String type;

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        type = "Car";
//        numberPlate = "KA01-12-0001";
//    }
    @PostConstruct
    private void initialize(){
        type = "Car";
        numberPlate = "KA01-12-0001";
    }

//
    @PreDestroy
    public void destory(){
        System.out.println("Destroying Vehicle Bean ");
    }


//    @Override
//    public void destroy() throws Exception {
//        System.out.println("Destroying Vehicle Bean from DisposableBean interface");
//    }
}
