package com.easybytes.ex4.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Vehicle {

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

    @Override
    public String toString(){
        return String.format("Vehicle{numberPlate='%s', type='%s'}", numberPlate, type);
    }


    @PostConstruct
    private void initialize() {
        type = "Car";
        numberPlate = "KA01-12-0001";
        System.out.println("Initializing Vehicle Bean with number plate: " + numberPlate + " and type: " + type);
    }

    @PreDestroy
    public void destory() {
        System.out.println("Destroying Vehicle Bean ");
    }
}

