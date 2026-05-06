package com.easybytes.ex8;




import com.easybytes.ex8.bean.Engine;
import com.easybytes.ex8.bean.UserSession;
import com.easybytes.ex8.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example8 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(
                ProjectConfig.class
        );


        Engine engine1 = context.getBean(Engine.class);
        Engine engine2 = context.getBean(Engine.class);
        System.out.println(engine1.hashCode());
        System.out.println(engine2.hashCode());

        UserSession userSession1 = context.getBean(UserSession.class);
        UserSession userSession2 = context.getBean(UserSession.class);
        System.out.println(userSession1.hashCode());
        System.out.println(userSession2.hashCode());

        context.close();

    }
}
