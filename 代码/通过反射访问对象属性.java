package com.sxt;

import java.lang.reflect.Field;

/**
 * 通过反射访问对象属性
 */
public class Main01 {
    public static void main(String[] args) throws Exception {
        Class personalClass = Class.forName("com.sxt.Personal");
        Object object = personalClass.newInstance(); // 创造对象
        Field field = personalClass.getDeclaredField("name"); // 拿到属性对象
        field.setAccessible(true); // 如果这个字段是private修饰的，必须把这个属性设置为true才可以访问
        field.set(object, "Tom"); // 设置或修改值
        Object value = field.get(object); // 读取这个age属性的值
        System.out.println(value);
    }
}

class Personal {
    private String name;
    protected int age;
    boolean isBoy;
    private char ok;
}
