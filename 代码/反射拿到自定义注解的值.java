package com.sxt;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 反射拿到自定义注解的值
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class clzss = Class.forName("com.sxt.User");
        if (clzss.isAnnotationPresent(MyAnnotation.class)) { // 判断这个类上是否有这个注解
            Annotation annotation = clzss.getAnnotation(MyAnnotation.class);
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            String name = myAnnotation.name();
            int age = myAnnotation.age();
            boolean[] value = myAnnotation.value();
            System.out.println(name);
            System.out.println(age);
            System.out.println(String.valueOf(value[0]) + String.valueOf(value[1]));
        }
    }
}

@MyAnnotation(name = "Tom", age = 10, value = {true, false})
class User {

}

/**
 * @Retention 修饰MyAnnotation这个自定义注解的作用位置
 * SOURCE 只保存在java源文件中
 * CLASS 保存在class文件中
 * RUNTIME 可以被反射拿到
 * @Target 修饰MyAnnotation这个自定义注解可以在那里出现
 * CONSTRUCTOR 构造方法上
 * FIELD 子段上
 * METHOD 方法上
 * 等等。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
@interface MyAnnotation {
    /**
     * 这个位置的类型只能写int short char long double float boolean 枚举 类型以及他们的数组
     * default 可以指定它的默认值
     * 写值时，如果只有一个参数且这个参数名为value时可以省略不写
     */
    String name() default "";

    int age();

    boolean[] value();
}

