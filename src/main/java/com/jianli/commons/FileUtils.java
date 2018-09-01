package com.jianli.commons;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author chendurex
 * @description
 * @date 2018-04-21 17:18
 */
@Slf4j
public class FileUtils {

    public static boolean exist(String path) {
        return new File(path).exists();
    }

    public static void createIfNotExist(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public static void writeTo(String message, String path) {
        File file = new File(path);
        createIfNotExist(path);
        try (FileWriter fr = new FileWriter(file, true);
             BufferedWriter br = new BufferedWriter(fr);
             PrintWriter pr = new PrintWriter(br)){
             pr.println(message);
        } catch(Exception e) {
            log.error("写文件失败,", e);
        }
    }
}
