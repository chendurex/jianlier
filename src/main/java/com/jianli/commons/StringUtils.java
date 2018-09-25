package com.jianli.commons;

/**
 * @author chendurex
 * @date 2018-07-22 09:06
 */
public class StringUtils {
    public static boolean isEmpty(Object o) {
        return org.springframework.util.StringUtils.isEmpty(o);
    }

    public static String backup(String s1, String s2) {
        return isEmpty(s1) ? s2 : s1;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static int toInt(String integer) {
        return integer == null ? 0 : Integer.valueOf(integer);
    }
}
