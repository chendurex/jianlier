package com.jianli.commons;

/**
 * @author chendurex
 * @date 2018-08-19 16:19
 */
public final class PrimitiveUtils {
    public static boolean intToBool(Integer integer) {
        return integer != null && integer > 0;
    }
}
