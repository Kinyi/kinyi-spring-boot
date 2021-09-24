package com.dataw.rhino.lock;

import java.util.ArrayList;

/**
 * @author Kinyi_Chan
 * @since 2021-02-09
 */
public class VolatileTest4 {

    private volatile static int i = 0;
    private static int count = 8000;//设置100，200。。。1000，10000,100000 查看结果

    public static void main(String[] args) {

        //消费者线程
        Thread t1 = new Thread(() -> {
            ArrayList list = new ArrayList();
            for (int y = 1; y <= count; y++) {
                list.add(y);
            }
            while (true) {
                if (list.contains(i)) {
                    System.out.println("i=========" + i);
                    break;
                }
            }
        }, "consumer");
        t1.start();

        //生产者线程
        Thread t = new Thread(() -> {
            shortWait(200000000);
            //生产完毕，设置信号量，通知消费线程
            for (int x = 1; x <= count; x++) {
                i = x;
            }
        }, "producer");
        t.start();
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
