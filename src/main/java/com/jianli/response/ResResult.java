package com.jianli.response;

import lombok.Data;

/**
 * @author chendurex
 * @description
 * @date 2018-03-18 12:33
 */
@Data
public class ResResult<T> {
    private final int status;
    private final String desc;
    private final T data;

    public ResResult(int status, String desc, T data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

}
