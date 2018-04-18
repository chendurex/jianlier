package com.jianli.component;

/**
 * @author chendurex
 * @description
 * @date 2018-04-01 00:34
 */

public interface MailSender {
    /**
     * 发送邮件
     * @param to 目的地址
     * @param filePath 附件地址
     */
    void send(String to, String filePath);

    void sendMessage(String to, String message);
}
