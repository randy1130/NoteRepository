package com.zr.test;

import java.util.*;


/***
 * Map集合的三种遍历方法
 */
public class Demo {
    public static void main(String[] args){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(4, "d");
        map.put(9, "c");
        map.put(6, "g");
        //第一种：拿到键的集合，然后遍历键的时候对应取出值
        Set<Integer> set = map.keySet();
        for (Integer key : set) {
            System.out.println("key: " + key + "\t value: " + map.get(key));
        }
        //第二种 ：遍历值
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println("\t value: " + value);
        }
        //第三种：迭代器
        Iterator it = map.entrySet().iterator();
        // 键和值
        Integer key = null;
        String value = null;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            key = (Integer) entry.getKey();
            value = (String) entry.getValue();
            System.out.println("key:" + key + "---" + "value:" + value);

        }
    }
}