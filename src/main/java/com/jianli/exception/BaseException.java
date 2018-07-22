package com.jianli.exception;

/**
 * @author chendurex
 * @date 2018-07-22 09:12
 */
abstract class BaseException extends RuntimeException {

    BaseException(String msg) {
        super(msg);
    }
}
