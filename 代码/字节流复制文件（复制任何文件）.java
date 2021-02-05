package com.sxt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流复制文件
 */
public class Main {
    public static void main(String[] args) {
        String path = Main.class.getResource("/").getPath() + "../../src/test/java/com/sxt/param/";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(path + "第一章-项目介绍和工程搭建.pdf");
            fos = new FileOutputStream(path + "newFile.pdf");
            int available = fis.available();
            System.out.println("文件还剩多少字节->" + available);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
            fos.flush(); // 清空管道中的最后一波数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

