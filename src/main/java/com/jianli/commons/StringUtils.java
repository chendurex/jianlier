package com.jianli.commons;

/**
 * @author chendurex
 * @date 2018-07-22 09:06
 */
public class StringUtils {
    public static boolean isEmpty(Object o) {
        return org.springframework.util.StringUtils.isEmpty(o);
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
}
