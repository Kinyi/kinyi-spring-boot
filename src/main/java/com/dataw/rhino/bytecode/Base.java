package com.dataw.rhino.bytecode;

/**
 * java -jar dive-in-spring-boot-0.0.1-SNAPSHOT.jar
 *
 * @author Kinyi_Chan
 * @since 2022-09-14
 */
public class Base {

    public static void main(String[] args) {
        Base base = new Base();
        while (true) {
            base.process();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void process() {
        System.out.println("process");
    }
}
