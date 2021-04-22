package com.example.lib.lang;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    public static void writeSDFile(File file, String content) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = content.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
