package com.easybytes.ex1.config;

import com.easybytes.ex1.bean.Vehicle;
import org.springframework.context.annotation.*;

/**
 * @Import({AnotherProjectConfig.class}) is alternative to
 * registration
 * var context = new AnnotationConfigApplicationContext(
 *                 AnotherProjectConfig.class
 * );
 */
@Configuration
//@Import({AnotherProjectConfig.class})
public class ProjectConfig {

    @Bean
    @Primary
    @Description("this a car with number plate MH-12-AB-0001")
    public Vehicle vehicle1(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-0001");
        vehicle.setType("Car");
        return vehicle;
    }

    @Bean(name = "0002")
    public Vehicle vehicle2(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-0002");
        vehicle.setType("Car");
        return vehicle;
    }

    @Bean(value="0003")
    public Vehicle vehicle3(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-0003");
        vehicle.setType("Bike");
        return vehicle;
    }

    @Bean("0004")
    public Vehicle vehicle4(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-0004");
        vehicle.setType("Bike");
        return vehicle;
    }

    @Bean({"0005","vehicle5"})
    public Vehicle vehicle5(){
        Vehicle vehicle =  new Vehicle();
        vehicle.setNumberPlate("MH-12-AB-0005");
        vehicle.setType("Bike");
        return vehicle;
    }

}
