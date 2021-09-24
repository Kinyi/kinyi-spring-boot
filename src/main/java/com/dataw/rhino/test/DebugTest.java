package com.dataw.rhino.test;

/**
 * @author Kinyi_Chan
 * @since 2021-02-16
 */
public class DebugTest {

    public static void main(String[] args) {
        System.out.println("begin");

        print();

        System.out.println("end");
    }

    private static void print() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
