package com.jianli.controller;

import com.jianli.education.EduInfo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.jianli.component.MailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * @author chendurex
 * @description
 * @date 2018-04-18 20:34
 */
@RestController
@RequestMapping("/edu")
public class EduController {
	@Autowired
	private MailSender mailSender;
	@CrossOrigin(origins = "*")
    @PostMapping(value = "submit")
    public ResResult submit(@RequestBody EduInfo eduInfo) {
        //mailSender.sendMessage("742608173@qq.com", eduInfo.toString());
        mailSender.sendMessage("316121113@qq.com", eduInfo.toString());
        return ResUtils.suc();
    }
}
