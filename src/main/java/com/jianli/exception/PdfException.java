package com.jianli.exception;

/**
 * @author chendurex
 * @date 2018-09-01 13:35
 */
public class PdfException extends RuntimeException {
    public PdfException(String message, Throwable cause) {
        super(message, cause);
    }
}
