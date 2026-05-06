package com.easybytes.ex5.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    private String numberPlate;
    private String type;

    public Vehicle() {
        System.out.println("Creating Vehicle Bean");
    }

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

    @Override
    public String toString(){
        return String.format("Vehicle{numberPlate='%s', type='%s'}", numberPlate, type);
    }


    @PostConstruct
    private void initialize() {
        type = "Car";
        numberPlate = "KA01-12-0001";
    }

    @PreDestroy
    private void destory() {
        System.out.println("Destroying Vehicle Bean ");
    }
}

