package com.easybytes.ex7.bean;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    private String model = "V8";

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
