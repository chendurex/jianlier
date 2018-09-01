package com.jianli.commons;

import java.util.Objects;
import java.util.UUID;

/**
 * @author chendurex
 * @description
 * @date 2018-03-18 13:01
 */
public final class UniqueSerials {
    public static String uniqueSerials() {
        return uniqueSerials("");
    }

    public static String uniqueSerials(String prefix) {
        Objects.requireNonNull(prefix);
        return (prefix.isEmpty() ? "" : prefix+"-")+UUID.randomUUID().toString();
    }

    public static String assembleHtmlPath(String path, Integer uid, Integer resumeId) {
        return assemblePath(path, uid, resumeId, "html");
    }

    public static String assemblePdfPath(String path, Integer uid, Integer resumeId) {
        return assemblePath(path, uid, resumeId, "pdf");
    }

    private static String assemblePath(String path, Integer uid, Integer resumeId, String suffix) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(uid);
        Objects.requireNonNull(resumeId);
        return path + "/" + uid + "-" + resumeId + "." + suffix;
    }
}
