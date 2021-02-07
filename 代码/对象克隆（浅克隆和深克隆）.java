package com.sxt;

/**
 * 对象克隆（浅克隆和深克隆）
 */
public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User();
        School school = new School(10);
        user.setSchool(school);
        User clone = (User) user.clone();
        school.setLevel(20);
        System.out.println(user);
        System.out.println(clone);
    }
}

class User implements Cloneable {
    private String name;
    private School school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", school=" + school +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        clone.setSchool((School) school.clone());
        return clone;
    }
}

class School implements Cloneable {
    private Integer level;

    public School(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "School{" +
                "level=" + level +
                '}';
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}