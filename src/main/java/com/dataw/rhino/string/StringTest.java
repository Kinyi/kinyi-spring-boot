package com.dataw.rhino.string;

/**
 * @author Kinyi_Chan
 * @since 2021-02-20
 */
public class StringTest {

    public static void main(String[] args) {
        final String stra = new StringBuilder().append("chen").append("qinyi").toString();
        System.out.println(stra);
        System.out.println(stra.intern());
        System.out.println(stra == stra.intern());

        final String strb = new StringBuilder().append("ja").append("va").toString();
        System.out.println(strb);
        System.out.println(strb.intern());
        System.out.println(strb == strb.intern());
    }
}
