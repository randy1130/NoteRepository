package com;

public class User  implements Comparable<User>{
    private String name;
    private Integer phone;
    @Override
    public int compareTo(User o) {
        if(this.getName()!=o.getName()){
            return this.getName().compareTo(o.getName());
        }
        if(this.phone!=o.phone){
            return this.phone-o.phone;
        }
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
