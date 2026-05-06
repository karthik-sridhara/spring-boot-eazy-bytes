package com.easybytes.ex5;

import com.easybytes.ex5.bean.Person;
import com.easybytes.ex5.bean.Vehicle;
import com.easybytes.ex5.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example5 {
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
