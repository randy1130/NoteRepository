package com.zr.springboot;

interface Animal {
    void run();
}
class Cat implements Animal {

    public void run() {
        System.out.println("猫在跑！");
    }
}
class Dog implements Animal {
    public void run() {
        System.out.println("狗在跑！");
    }
}
class AnimalFactory {

    public static Animal getInstance(String animal) {
        if (animal.equalsIgnoreCase("cat")) {
            return new Cat();
        } else if (animal.equalsIgnoreCase("dog")) {
            return new Dog();
        } else {
            return null;
        }
    }
}
public class Test01 {
    public static void main(String[] args) {
        AnimalFactory.getInstance("cat").run();
    }
}
