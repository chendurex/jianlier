package com.jianli;

import com.jianli.controller.BinEducationController;
import com.jianli.domain.BinEducation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author chendurex
 * @description
 * @date 2018-03-31 22:52
 */

public class MailTest extends BaseTest {
    @Autowired
    private com.jianli.component.MailSender sender;
    @Value("${spring.mail.default.context}")
    private String test;
    @Value("${edu.mail.received}")
    private String received;
    @Autowired
    private BinEducationController eduControler;

    @Test
    public void testSendMessage() {
        BinEducation info = new BinEducation();
        info.setName("haha");
        info.setContact("11111");
        info.setQuestion("iiiiiiiii");
        eduControler.submit(info);
        
    }
}
