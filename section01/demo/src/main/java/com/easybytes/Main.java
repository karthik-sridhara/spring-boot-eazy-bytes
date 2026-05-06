package com.easybytes;

import com.easybytes.bean.Vehicle;
import com.easybytes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setNumberPlate("MH-12-AB-1113");
        vehicle1.setType("Bike");

        System.out.println(vehicle1.getNumberPlate());

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var vehicle2 =(Vehicle) context.getBean("vehicle1");
        System.out.println(vehicle2.getNumberPlate());

        var vehicle3 = (Vehicle) context.getBean("vehicle2");
        System.out.println(vehicle3.getNumberPlate());

        String hello = context.getBean(String.class);
        System.out.println(hello);

        int luckyNumber = context.getBean(Integer.class);
        System.out.println(luckyNumber);



    }
}
