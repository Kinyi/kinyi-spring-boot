package com.dataw.rhino.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证hashCode并不是内存地址
 * 理论上来说，如果hashCode是内存地址的话，是应该不会重复的
 * 实际上hashCode出现了重复，而obj是没有重复
 *
 * @author Kinyi_Chan
 * @since 2021-02-09
 */
public class ObjectTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        for (int i = 0; i < 2000000; i++) {
            Object obj = new Object();
            if (objectList.contains(obj)) {
                System.out.println("hashCode应该不会重复吧！！！！！" + obj);
            } else {
                objectList.add(obj);
            }
            if (list.contains(obj.hashCode())) {
                System.out.println("重复了 - " + obj.hashCode());
            } else {
                list.add(obj.hashCode());
            }
        }
        System.out.println("list size: " + list.size());
        System.out.println("objectList size: " + objectList.size());
    }
}
