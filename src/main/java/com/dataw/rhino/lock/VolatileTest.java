package com.dataw.rhino.lock;

/**
 * @author Kinyi_Chan
 * @since 2021-02-06
 */
public class VolatileTest {

    private static int i = 0;

    public static void main(String[] args) {
        //消费者线程处理文件
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 1) {
                    System.out.println("i==1 is ok?" + (i == 1));
                    break;
                }
            }
        }, "consumer").start();

        //生产者线程
        new Thread(() -> {
            try {
                //模拟生产者生产文件
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //生产完毕，设置信号量，通知消费线程
            i = 1;
        }, "producer").start();
    }
}
