package com.jianli.component;

/**
 * @author chendurex
 * @date 2018-09-02 22:32
 */
public interface Html2Pdf {
    /**
     * html转pdf
     * @param text
     * @param origin
     * @param dest
     */
    void writeTO(String text, String origin, String dest);
}
