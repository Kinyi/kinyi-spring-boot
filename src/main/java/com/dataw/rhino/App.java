package com.dataw.rhino;

import com.dataw.rhino.demo.postprocessor.SpecialBeanForEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Kinyi_Chan
 * @since 2019-11-14
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }

//    @Bean(initMethod = "start")
//    BenzCar benzCar(Engine engine) {
//        BenzCar car = new BenzCar();
//        car.engine = engine;
//        return car;
//    }

    @Bean
    SpecialBeanForEngine specialBeanForEngine() {
        return new SpecialBeanForEngine();
    }
}
