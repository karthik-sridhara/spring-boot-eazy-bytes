package com.easybytes.ex7;


import com.easybytes.ex7.bean.Bike;
import com.easybytes.ex7.bean.Car;
import com.easybytes.ex7.bean.Engine;
import com.easybytes.ex7.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example7 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class
        );



        if(context.containsBean("engine")){
            System.out.println("Engine bean is present in the context.");
            Engine engine1 = context.getBean(Engine.class);
            Engine engine2 = context.getBean(Engine.class);
            System.out.println(engine1.hashCode());
            System.out.println(engine2.hashCode());
        }

        if(context.containsBean("car")){
            System.out.println(context.getBean(Car.class));
        }
        if(context.containsBean("bike")){
            System.out.println(context.getBean(Bike.class));
        }

        context.close();

    }
}
