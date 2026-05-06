package com.easybytes.ex1;

import com.easybytes.ex1.bean.Vehicle;
import com.easybytes.ex1.config.AnotherProjectConfig;
import com.easybytes.ex1.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class,
                AnotherProjectConfig.class
        );

        var vehicle1_1 = context.getBean("vehicle1",Vehicle.class);;
        System.out.println(vehicle1_1.getNumberPlate());

        var vehicle1_2 = context.getBean(Vehicle.class);
        System.out.println(vehicle1_2.getNumberPlate());

        //after providing name to the bean we can get the bean by method name
        //var vehicle2 = (Vehicle) context.getBean("vehicle2");

        var vehicle2 = (Vehicle) context.getBean("0002");
        System.out.println(vehicle2.getNumberPlate());

        var vehicle3 = context.getBean("0003",Vehicle.class);
        System.out.println(vehicle3.getNumberPlate());

        var vehicle4 = context.getBean("0004",Vehicle.class);
        System.out.println(vehicle4.getNumberPlate());

        var vehicle5_1 = context.getBean("0005",Vehicle.class);
        System.out.println(vehicle5_1.getNumberPlate());
        var vehicle5_2 = context.getBean("vehicle5",Vehicle.class);
        System.out.println(vehicle5_2.getNumberPlate());

        String hello = context.getBean(String.class);
        System.out.println(hello);

        String appName = context.getBean("app-name",String.class);
        System.out.println(appName);
    }
}
