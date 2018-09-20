package com.jianli.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileExistsException;

import java.io.*;

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

    public static void removeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static byte[] fileToByte(String path) {
        try {
            return org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("文件不存在",e);
        }
    }

    public static void writeToWithoutAppend(String message, String path) {
        File file = new File(path);
        createIfNotExist(path);
        try (FileWriter fr = new FileWriter(file);
             BufferedWriter br = new BufferedWriter(fr);
             PrintWriter pr = new PrintWriter(br)){
            pr.println(message);
        } catch(Exception e) {
            throw new RuntimeException("写入文件失败", e);
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
