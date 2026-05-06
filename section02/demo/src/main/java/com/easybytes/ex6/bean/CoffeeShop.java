package com.easybytes.ex6.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShop {
    private final Coffee coffee;
    private final String name;

    public CoffeeShop(@Qualifier("espresso") Coffee coffee){
        this.coffee=coffee;
        this.name = "EasyBytes Coffee Shop";
    }

    public Coffee getCoffee(){
        return coffee;
    }

    public String getName() {
        return name;
    }
}
