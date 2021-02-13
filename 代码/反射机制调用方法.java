package com.sxt;

import java.lang.reflect.Method;

/**
 * 反射机制调用方法
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.sxt.UserService");
        Object object = clazz.newInstance(); // 创建对象
        /**
         *  1. 方法名 2. 可变参
         *  确定一个方法是方法名和参数列表
         */
        Method method = clazz.getDeclaredMethod("login", String.class, String.class);
        // method.setAccessible(true); // private 方法设置这个属性就可以调用了
        /**
         * 1. 对象 2. 实参
         * 返回值为方法的返回值
         */
        Object result = method.invoke(object, "admin", "123");
        System.out.println(result);
    }
}

class UserService {
    public String login(String name, String pwd) {
        return "admin".equals(name) && "123".equals(pwd) ? "登录成功！" : "登录失败！";
    }
}
