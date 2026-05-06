package com.easybytes.ex4;

import com.easybytes.ex4.bean.Person;
import com.easybytes.ex4.bean.Vehicle;
import com.easybytes.ex4.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example4 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class
        );

        Person person1 = context.getBean(Person.class);
        System.out.println("Person Name: " + person1.getName());
        System.out.println("Person Own Vehicle: "+ person1.getVehicle());

        System.out.println("Unknown Vehicle: "+context.getBean(Vehicle.class));
        context.close();

    }
}
