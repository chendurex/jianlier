package com.jianli.response;

/**
 * @author chendurex
 * @description
 * @date 2018-03-18 21:02
 */
public final class ResUtils {
    private static final String SUC_DESC = Boolean.TRUE.toString();
    public static <T>ResResult data(T data) {
        return data(data, null);
    }

    public static <T>ResResult data(T data, Integer uid) {
        return new DataResult<>(ResStat.SUC, SUC_DESC, data, uid);
    }

    public static ResResult suc() {
        return result(ResStat.SUC, SUC_DESC);
    }

    public static ResResult fail(String desc) {
        return result(ResStat.FAIL, desc);
    }

    private static ResResult result(int code, String desc) {
        return new ResResult(code, desc);
    }
}
