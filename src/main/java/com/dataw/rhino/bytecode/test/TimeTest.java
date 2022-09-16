package com.dataw.rhino.bytecode.test;

/**
 * @author Kinyi_Chan
 * @since 2022-09-15
 */
public class TimeTest {
    public void test() {
        System.out.println("开始执行TimeTest#test");
        System.out.println("sleep开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep结束");
    }
}