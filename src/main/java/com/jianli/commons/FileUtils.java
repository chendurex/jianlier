package com.jianli.commons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 17:18
 */
public class FileUtils {

    public static boolean exist(String path) {
        return new File(path).exists();
    }

    public static void writeTo(String message, String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter fr = new FileWriter(file, true);
             BufferedWriter br = new BufferedWriter(fr);
             PrintWriter pr = new PrintWriter(br)){
             pr.println(message);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
