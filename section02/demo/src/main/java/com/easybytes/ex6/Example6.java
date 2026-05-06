package com.easybytes.ex6;


import com.easybytes.ex6.bean.CoffeeShop;
import com.easybytes.ex6.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example6 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class
        );

        CoffeeShop  coffeeShop = context.getBean(CoffeeShop.class);
        System.out.println("Coffee shop name: " + coffeeShop.getName());
        System.out.println("Coffee: "+ coffeeShop.getCoffee().makeCoffee());

        context.close();

    }
}
