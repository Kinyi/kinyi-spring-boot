package com.dataw.rhino.array;

/**
 * @author Kinyi_Chan
 * @since 2021-03-14
 */
public class StaticTest {

    private static int zjx = 123;

    static {
        zjx = 234;
    }

    public static void main(String[] args) {
        System.out.println(zjx);
    }
}
