package com.easybytes.ex5.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
//    @Autowired
    private Vehicle vehicle;

//    @Autowired
    public Person(Vehicle vehicle) {
        System.out.println("Creating Person Bean");
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

//    @Autowired
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @PostConstruct
    private void initialization(){
        name="Kar";
    }
}
