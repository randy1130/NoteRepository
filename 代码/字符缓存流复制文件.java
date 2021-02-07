package com.sxt;

import java.io.*;

/**
 * 缓存流复制文件
 */
public class Main {
    public static void main(String[] args) {
        String path = Main.class.getResource("/").getPath() + "../../src/test/java/com/sxt/param/";
        /**
         * 新写法不用关流
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "oldFile.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path + "newFile.txt"));) {
            String str;
            while ((str = reader.readLine()) != null) {
                writer.write(str);
                writer.newLine(); // 换行
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
