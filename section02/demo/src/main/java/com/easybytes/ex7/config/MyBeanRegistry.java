package com.easybytes.ex7.config;

import com.easybytes.ex7.bean.Bike;
import com.easybytes.ex7.bean.Car;
import com.easybytes.ex7.bean.Engine;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

import java.util.Random;

public class MyBeanRegistry implements BeanRegistrar {

    @Override
    public void register(@NonNull BeanRegistry registry, Environment env) {
        int randomInt = new Random().nextInt(100);

        if (randomInt % 2 == 0){
            System.out.println("Registering Car bean");
            registry.registerBean("car",Car.class,spec->spec.supplier(
                context-> {
                    Car car = new Car( context.bean(Engine.class));
                    car.setBrand("Toyota");
                    car.setModel("Camry");
                    return car;
                })
            );
        }else{
            System.out.println("Registering Bike bean");
            registry.registerBean("bike",Bike.class,spec->spec.supplier(
                    context-> {
                        Bike bike = new Bike( context.bean(Engine.class));
                        bike.setBrand("Yamaha");
                        bike.setModel("YZF-R3");
                        return bike;
                    })
            );
        }

    }
}
