package com.jianli.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author chendurex
 * @description
 * @date 2018-04-01 00:36
 */
@Component
@Slf4j
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;

    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.nickname}")
    private String nickName;
    @Value("${spring.mail.alert}")
    private String alert;
    @Value("${spring.mail.default.context}")
    private String defaultContext;

    @Override
    public void send(String to, String path) {
        long start = System.currentTimeMillis();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.addAttachment(extractFileName(path), new File(path));
            helper.setFrom(nickName);
            helper.setTo(to);
            helper.setText(defaultContext);
            helper.setSubject("客官!您的简历来了！请查收！");
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("邮件发送失败, 当前错误信息为：", e);
            sendMessage(alert, to);
        }
        log.info("发送邮件完成，filepath:{}, to:{}, 总共耗时:{}s", path, to, (System.currentTimeMillis()-start)/1000);
    }

    @Override
    public void sendMessage(String to, String message) {
        log.info("邮件发送开始开始，接收内容为：{}，消息内容为：{}", to, message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(message);
        mailMessage.setFrom(nickName);
        mailMessage.setTo(to);
        mailMessage.setSubject("来自大表哥的重要邮件!");
        mailSender.send(mailMessage);
    }


    private String extractFileName(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }

}
