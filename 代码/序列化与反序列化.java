package com.sxt;

import java.io.*;

/**
 * 序列化与反序列化
 */
public class Main {
    public static void main(String[] args) {
        String path = Main.class.getResource("/").getPath() + "../../src/test/java/com/sxt/param/";
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + "user"));
            ois = new ObjectInputStream(new FileInputStream(path + "user"));
            User user = new User("Jerry", 10);
            // 序列化到硬盘文件
            oos.writeObject(user); // 这里的user也可以是一个装有好几个对象的集合实现多个对象的序列化
            oos.flush();
            // 反序列化到内存
            Object object = ois.readObject();
            User newUser = (User) object;
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
