package com.sxt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志工具类
 */
public class Main {
    public static void main(String[] args) {
        LOGGER.info("哈哈哈");
        LOGGER.info("Hello");
        LOGGER.info("world");
    }
}

class LOGGER {
    public static void info(String message) {
        String path = LOGGER.class.getResource("/").getPath() + "../../src/test/java/com/sxt/param/";
        PrintStream stream = null;
        try {
            stream = new PrintStream(new FileOutputStream(path + "log.txt", true));
            System.setOut(stream);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            String time = format.format(date);
            System.out.println(time + "   :" + message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}