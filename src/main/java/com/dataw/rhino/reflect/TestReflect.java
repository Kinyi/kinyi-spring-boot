package com.dataw.rhino.reflect;

/**
 * @author Kinyi_Chan
 * @since 2021-01-20
 */
public class TestReflect {

    public static void main(String[] args) {
//        Subject subject = new SubjectImpl();
//        Subject proxy = (Subject) Proxy.newProxyInstance(
//                subject.getClass().getClassLoader(),
//                subject.getClass().getInterfaces(),
//                new ProxyInvocationHandler(subject));
//        String w = proxy.sayHello();
//
//
//        Animal animal = (Animal) Proxy.newProxyInstance(
//                Animal.class.getClassLoader(),
//                new Class[]{Animal.class},
//                new AnProxyInvocationHandler());
//        String s = animal.sayHello();
//        System.out.println();

//        int a = 300;
//        int b = 300;
//
//        Integer c = 300;
//        Integer d = 300;
//
//        System.out.println(a == b);
//        System.out.println(c == d);
//
//        try {
//            Class<?> aClass = Class.forName("");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        byte[] arr = new byte[1024 * 1024 * 2];
        arr = new byte[1024 * 1024 * 2];
        arr = new byte[1024 * 1024 * 2];
        arr = null;
        byte[] arr2 = new byte[1024 * 512];

        byte[] arr3 = new byte[1024 * 1024 * 2];
        arr3 = new byte[1024 * 1024 * 2];
        arr3 = new byte[1024 * 1024 * 2];
        arr3 = new byte[1024 * 128];
        arr3 = null;
        byte[] arr4 = new byte[1024 * 1024 * 2];

        for (; ; ) {
            arr4 = new byte[1024 * 1024 * 2];
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        int i = 6;
//        int j = ++i;
//        boolean k = true;
//        char m = 'j';
//        String a = "hahaha";
//
//        Object b = new Object();
//        String s = b.toString();
//        long c = 100;
//
//        float d = 23;
//
//        Integer e = 188;
//
//        Integer f = 188;
//
//        System.out.println("!!!!!!!!!!" + (e == f));
    }
}
