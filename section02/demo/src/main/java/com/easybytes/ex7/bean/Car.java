package com.easybytes.ex7.bean;

public class Car {
    private String brand;
    private String Model;
    private final Engine engine;

    public Car(Engine engine){
        this.engine = engine;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", Model='" + Model + '\'' +
                ", engine=" + engine +
                '}';
    }
}
