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

    @Value("${spring.component.nickname}")
    private String nickName;
    @Value("${spring.component.alert}")
    private String alert;
    @Value("${spring.component.default.context}")
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
            sendAlert(to);
        }
        log.info("发送邮件完成，filepath:{}, to:{}, 总共耗时:{}s", path, to, (System.currentTimeMillis()-start)/1000);
    }

    private void sendAlert(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(to);
        mailMessage.setFrom(nickName);
        mailMessage.setTo(alert);
        mailMessage.setSubject("用户邮件发送失败！");
        mailSender.send(mailMessage);
    }

    private String extractFileName(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }

}
