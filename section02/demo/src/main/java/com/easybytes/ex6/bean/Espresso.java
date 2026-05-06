package com.easybytes.ex6.bean;

import org.springframework.stereotype.Component;

@Component("espresso")
public class Espresso implements Coffee {

    @Override
    public String makeCoffee() {
        return "Espresso";
    }
}
