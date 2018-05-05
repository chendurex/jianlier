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
}
