package com.sxt;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 读取配置文件
 */
public class Main {
    public static void main(String[] args) throws Exception {
        getPath1("application.properties");
        getPath2("application.properties");
        getPath3("application");
    }

    private static void getPath1(String filePath) throws Exception {
        /**
         * 拿到文件绝对路径读数据参数为（src下的类路径）
         */
        String path = Thread.currentThread().getContextClassLoader().getResource(filePath).getPath();
        FileReader reader = new FileReader(path);
        Properties properties = new Properties();
        properties.load(reader);
        String value = properties.getProperty("key");
        System.out.println(value);
    }

    private static void getPath2(String filePath) throws Exception {
        /**
         * 拿到文件流读数据参数为（src下的类路径）
         */
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        Properties properties = new Properties();
        properties.load(inputStream);
        String value = properties.getProperty("key");
        System.out.println(value);
    }

    private static void getPath3(String filePath) throws Exception {
        /**
         * 资源管理器读文件（src下的类路径）
         * 1.文件扩展名只能是properties
         * 2.参数不能带扩展名
         */
        ResourceBundle bundle = ResourceBundle.getBundle(filePath);
        String value = bundle.getString("key");
        System.out.println(value);
    }

}
