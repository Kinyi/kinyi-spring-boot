package com.dataw.rhino.array;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Kinyi_Chan
 * @since 2021-02-07
 */
public class ArrayTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if ("c".equals(next)) {
////                list.remove(next); //报错，ConcurrentModificationException
//                iterator.remove();
////                list.add("h");
//            } else {
//                System.out.println(next);
//            }
//        }

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            if ("c".equals(next)) {
                listIterator.add("h");
                System.out.println(next);
            } else {
                System.out.println(next);
            }
        }

        System.out.println("==========");

        for (String s : list) {
            System.out.println(s);
        }

    }
}
