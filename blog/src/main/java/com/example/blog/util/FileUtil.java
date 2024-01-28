package com.example.blog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * @author: 308501
 * @time: 2021/11/3 20:01
 * @description :文件上传
 */


public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            Boolean b = targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static Boolean deleteFile(String filePath, String fileName) {
        File targetFile = new File(filePath + fileName);
        return targetFile.delete();
    }



    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}


