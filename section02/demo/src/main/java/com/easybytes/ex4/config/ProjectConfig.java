package com.easybytes.ex4.config;


import com.easybytes.ex4.bean.Person;
import com.easybytes.ex4.bean.Vehicle;
import org.springframework.context.annotation.*;

@Configuration
public class ProjectConfig {

    @Bean
    @Primary
    public Vehicle vehicle1(){
        return new Vehicle();
    }

    @Bean
    @Primary
    public Person person1(Vehicle vehicle){
        Person person = new Person();
        person.setName("John");
        person.setVehicle(vehicle);
        return person;
    }
}
