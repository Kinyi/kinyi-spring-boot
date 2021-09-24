package com.dataw.rhino.throwable;

/**
 * @author Kinyi_Chan
 * @since 2021-02-02
 */
public class ErrorTest {

    public static void main(String[] args) {
        /*try {
            throw new OutOfMemoryError("oh, error happen!");
        } catch (Error error) {
            error.printStackTrace();
        }*/

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 3) {
                    throw new Error("god!");
                }
            }
        }).start();

        int i = 0;
        while (true) {
            i++;
            System.out.println("main");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 10) {
                throw new Error("error main!");
            }
        }
    }
}
