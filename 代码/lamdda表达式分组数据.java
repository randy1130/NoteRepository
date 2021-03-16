package com.sxt;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("Tom1", 1, "China"));
        list.add(new User("Jerry1", 2, "China"));
        list.add(new User("Tom2", 1, "China"));
        list.add(new User("Jerry2", 2, "China"));
        list.add(new User("Tom3", 1, "China"));
        list.add(new User("Dog1", 3, "USA"));
        //method1(list);
        //method2(list);
        //method3(list);
        //method4(list);
        //method5(list);
        method6(list);


    }

    private static void method1(List<User> list) {
        // 按某个属性分组
        Map<Integer, List<User>> groupList = list.stream().collect(Collectors.groupingBy(User::getAge));
        for (Map.Entry<Integer, List<User>> entry : groupList.entrySet()) {
            System.out.println(entry.getKey());
            for (User user : entry.getValue()) {
                System.out.println(user);
            }
            System.out.println("----------");
        }
    }

    private static void method2(List<User> list) {
        // 统计每组的数据量
        Map<Integer, Long> groupList = list.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : groupList.entrySet()) {
            System.out.println(entry.getKey() + "==>" + entry.getValue());
        }
    }

    private static void method3(List<User> list) {
        // 按某属性分组，某另一属性求和
        Map<String, Double> groupList = list.stream().filter(i -> i.getAge() != 0).collect(Collectors.groupingBy(User::getAddr, Collectors.summingDouble(User::getAge)));
        for (Map.Entry<String, Double> entry : groupList.entrySet()) {
            System.out.println(entry.getKey() + "==>" + entry.getValue());
        }
    }

    private static void method4(List<User> list) {
        // 按某属性分组，某另一属性求平均值
        Map<String, Double> groupList = list.stream().filter(i -> i.getAge() != 0).collect(Collectors.groupingBy(User::getAddr, Collectors.averagingDouble(User::getAge)));
        for (Map.Entry<String, Double> entry : groupList.entrySet()) {
            System.out.println(entry.getKey() + "==>" + entry.getValue());
        }
    }

    private static void method5(List<User> list) {
        // 两个条件分组（多个条件分组）
        Map<String, Map<Integer, List<User>>> groupList = list.stream().collect(Collectors.groupingBy(User::getAddr, Collectors.groupingBy(User::getAge)));
        System.out.println(JSONObject.toJSONString(groupList));
    }

    private static void method6(List<User> list) {
        // 按某属性分组，List换为特定的另一属性
        Map<Integer, List<String>> groupList = list.stream().collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.toList())));
        System.out.println(JSONObject.toJSONString(groupList));
    }
}

class User {
    private String name;

    private int age;
    private String addr;

    public User() {
    }

    public User(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}

