package com.easybytes.ex3;

import com.easybytes.ex3.bean.Vehicle;
import com.easybytes.ex3.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class
        );

        var vehicle1 = context.getBean( Vehicle.class);;
        System.out.println(vehicle1.getNumberPlate());
        context.close();

    }
}
