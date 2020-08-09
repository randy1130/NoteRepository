package com.zr.test;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) {
        System.out.println("请输入身份证号：");
        String date = new Scanner(System.in).nextLine().replace(" ", "");
        Pattern pattern = Pattern.compile("^(\\d{15}|\\d{17}[\\dx])$");
        Matcher matcher = pattern.matcher(date);
        //符合为true，否则为false
        boolean flag = matcher.matches();
        if (flag) {
            System.out.println("输入合法，生日是：" + date.substring(6, 14));
        } else {
            System.out.println("输入不合法！");
            main(args);
        }
    }
}