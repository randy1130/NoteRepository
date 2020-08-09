package com.test;



import java.util.concurrent.atomic.AtomicReference;


class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

/**
 * 自定义对象的原子CAS操作
 */
public class Demo {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 22);
        User lisi = new User("lisi", 22);
        AtomicReference<User> ar = new AtomicReference<>();
        ar.set(zhangsan);
        System.out.println(ar.compareAndSet(zhangsan, lisi) + "==" + ar.get());
        System.out.println(ar.compareAndSet(zhangsan, lisi) + "==" + ar.get());
    }
}