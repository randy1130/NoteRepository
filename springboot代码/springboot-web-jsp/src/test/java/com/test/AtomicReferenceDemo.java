package com.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@ToString
class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

/**
 * 自定义对象的原子CAS操作
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 22);
        User lisi = new User("lisi", 22);
        AtomicReference<User> ar = new AtomicReference<>();
        ar.set(zhangsan);
        System.out.println(ar.compareAndSet(zhangsan, lisi) + "==" + ar.get());
        System.out.println(ar.compareAndSet(zhangsan, lisi) + "==" + ar.get());
    }
}
