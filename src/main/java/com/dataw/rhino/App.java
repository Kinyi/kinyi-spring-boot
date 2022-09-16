package com.dataw.rhino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kinyi_Chan
 * @since 2019-11-14
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(App.class, args);
//        SensorsDataUtil sensorsDataUtil = new SensorsDataUtil();
//        SensorsDataUtil sensorsDataUtil1 = new SensorsDataUtil();

//        System.out.println(sensorsDataUtil.getInstance());
//        System.out.println(sensorsDataUtil1.getInstance());
//        String a = "name_";
//        System.out.println(a.split("_")[1]);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("shutdown!!!")));
//
//        while (true) {
//            System.out.println(new Random().nextInt(1));
//            Thread.sleep(1000);
//        }

//        DemoImpl demo = new DemoImpl();
//        System.out.println(demo.say());
//        demo.eat();
    }

//    @Bean(initMethod = "start")
//    BenzCar benzCar(Engine engine) {
//        BenzCar car = new BenzCar();
//        car.engine = engine;
//        return car;
//    }

//    @Bean
//    SpecialBeanForEngine specialBeanForEngine() {
//        return new SpecialBeanForEngine();
//    }
}
