package com.sxt;

/**
 * 利用反射获取一个类的父类和接口
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class stringClass = Class.forName("java.lang.String" );
        Class superclass = stringClass.getSuperclass();
        // 父类
        System.out.println(superclass.getName());
        Class[] interfaces = stringClass.getInterfaces();
        for (Class inter : interfaces) {
            // 接口
            System.out.println(inter.getName());
        }
    }
}