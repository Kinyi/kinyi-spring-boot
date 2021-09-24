package com.dataw.rhino.jdk;

/**
 * @author Kinyi_Chan
 * @since 2021-05-07
 */
public class MessageFormatDemo {

    //    public static void main(String[] args) {
//        System.out.println(MessageFormat.format("String: {0}, value: {1}, important: {0}", "name", "zhangsan"));
//    }
    ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
//        HashMap<String, String> stringStringHashMap = new HashMap<>(0);
//        System.out.println(stringStringHashMap);
//        String a = "202103";
//        System.out.println(new StringBuilder(a).insert(4, "-").toString());
//        int[] a = new int[10];
//        int[] b = new int[100];
//        Class c10 = a.getClass();
//        Class c11 = b.getClass();
//        System.out.println(c10 == c11);

//        Integer x = 1271;
//        int y = 1271;
//        System.out.println(x.equals(y));

//        String i = "5";
//        switch (i) {
//            case "0":
//                System.out.println("0");
////                break;
//            case "1":
//                System.out.println("1");
////                break;
//            default:
//                System.out.println("out");
//                break;
//            case "2":
//                System.out.println("2");
////                break;
//            case "3":
//                System.out.println("3");
//        }

//        Map<String, String> map = new HashMap<>(2);
//        map.put("name", "allen");
//        map.put("sex", "male");
//        System.out.println(map);
        MessageFormatDemo demo = new MessageFormatDemo();
        Integer integer = demo.local.get();
        System.out.println(integer);
        demo.local.remove();
        demo.local.remove();
        demo.local.remove();
        demo.local.remove();
        System.out.println("finish");
    }
}
