package com.easybytes.ex8.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@Lazy
public class Engine {
    private String model = "V8";

    public Engine(){
        System.out.println("Creating Engine Bean");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "model='" + model + '\'' +
                '}';
    }
}
