package com.dataw.rhino.memory;

/**
 * @author Kinyi_Chan
 * @since 2021-02-28
 */
public class MemoryTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

        }

        while (true) {
            try {
                System.out.println("hello");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
