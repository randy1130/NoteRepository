package com.sxt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流复制文件
 */
public class Main {
    public static void main(String[] args) {
        String path = Main.class.getResource("/").getPath() + "../../src/test/java/com/sxt/param/";
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(path + "oldFile.txt");
            writer = new FileWriter(path + "newFile.txt");
            char[] chars = new char[1024];
            int length;
            reader.skip(1); // 跳过1个字符开始读
            while ((length = reader.read(chars)) != -1) {
                writer.write(chars, 0, length);
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

