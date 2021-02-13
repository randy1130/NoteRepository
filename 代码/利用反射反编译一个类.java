package com.sxt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 利用反射反编译一个类
 */
public class Main {
    public static void main(String[] args) throws Exception {
        doSome("java.lang.String");
    }

    private static void doSome(String className) throws ClassNotFoundException {
        Class<?> userClass = Class.forName(className);
        StringBuffer bf = new StringBuffer();
        bf.append(Modifier.toString(userClass.getModifiers())); // 类的修饰列表
        bf.append(" ");
        bf.append("class");
        bf.append(" ");
        bf.append(userClass.getSimpleName()); // 类名
        bf.append(" ");
        bf.append("{\n");
        //Field[] fields = userClass.getFields(); // 拿到public修饰的字段
        // 循环属性
        Field[] fields = userClass.getDeclaredFields(); // 拿到全部的字段
        for (Field field : fields) {
            bf.append("\t");
            bf.append(Modifier.toString(field.getModifiers()));
            bf.append(" ");
            bf.append(field.getType().getSimpleName());
            bf.append(" ");
            bf.append(field.getName());
            bf.append(";\n");
        }
        // 循环构造方法
        Constructor<?>[] constructors = userClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            bf.append("\t");
            bf.append(Modifier.toString(constructor.getModifiers()));
            bf.append(" ");
            bf.append(userClass.getSimpleName());
            bf.append("(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                bf.append(parameterType.getSimpleName());
                bf.append(",");
            }
            if (parameterTypes.length > 0) {
                bf.deleteCharAt(bf.length() - 1);
            }
            bf.append("){}\n");
        }
        // 循环方法
        Method[] methods = userClass.getDeclaredMethods();
        for (Method method : methods) {
            bf.append("\t");
            bf.append(Modifier.toString(method.getModifiers()));
            bf.append(" ");
            bf.append(method.getReturnType().getSimpleName());
            bf.append(" ");
            bf.append(method.getName());
            bf.append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                bf.append(parameterType.getSimpleName());
                bf.append(",");
            }
            if (parameterTypes.length > 0) {
                bf.deleteCharAt(bf.length() - 1); // 删除指定下标的一个字符（删除最后一个丢逗号）
            }
            bf.append("){}\n");
        }

        bf.append("}");
        System.out.println(bf.toString());
    }
}
