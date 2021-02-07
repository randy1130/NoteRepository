package com.sxt;

import java.io.*;

/**
 * 序列化深克隆
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Childer childer = new Childer();
        childer.setSize("10");
        person.setChilder(childer);
        Person myclone = person.myclone();
        childer.setSize("20");
        System.out.println(person);
        System.out.println(myclone);
    }
}

class Person implements Serializable {
    private String name;
    private Integer age;
    private Childer childer;


    public Person myclone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person person = (Person) ois.readObject();
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public Childer getChilder() {
        return childer;
    }

    public void setChilder(Childer childer) {
        this.childer = childer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", childer=" + childer +
                '}';
    }
}

class Childer implements Serializable {
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Childer{" +
                "size='" + size + '\'' +
                '}';
    }
}