package com.easybytes.ex6.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("cappuccino")
@Primary
public class Cappuccino implements Coffee {

    @Override
    public String makeCoffee() {
        return "Cappuccino";
    }
}
