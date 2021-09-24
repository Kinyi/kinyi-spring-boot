package com.dataw.rhino.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Kinyi_Chan
 * @since 2021-02-08
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if ("3".equals(next.getKey())) {
//                map.remove(next.getKey());
//                iterator.remove();
                map.put("a", "b");
            } else {
                System.out.println(next);
            }
        }
    }
}
