package com.sxt;

import java.lang.reflect.Constructor;

/**
 * 利用反射调用构造方法
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> userClass = Class.forName("com.sxt.User" );
        // 无参构造第一种
       /* Object object = userClass.newInstance();
        System.out.println(object);*/
        // 无参构造第二种
       /* Constructor<?> constructor = userClass.getDeclaredConstructor();
        Object object = constructor.newInstance();
        System.out.println(object);*/
        //有参构造
        Constructor<?> constructor = userClass.getDeclaredConstructor(String.class, int.class);
        Object object = constructor.newInstance("Tom", 10);
        System.out.println(object);
    }
}

class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}