package com.jianli.controller;

import com.jianli.component.MailSender;
import com.jianli.domain.BinEducation;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jianli.repo.BinEducationRepo;
import com.jianli.commons.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chendurex
 * @description
 * @date 2018-04-18 20:34
 */
@RestController
@RequestMapping("/edu")
@Api(description = "无用的", hidden = true)
public class BinEducationController {
    private final BinEducationRepo eduRepository;
    private final MailSender sender;

    public BinEducationController(BinEducationRepo repo, MailSender sender) {
        this.eduRepository = repo;
        this.sender = sender;
    }


	@Value("${edu.user.security}")
	private String security;
    @Value("${edu.mail.received}")
    private String received;
    @Value("${log.file.path}")
    private String logPath;
	@CrossOrigin(origins = "*")
    @PostMapping(value = "submit")
    @ApiOperation(value = "教育提交", hidden = true)
    public ResResult submit(@RequestBody BinEducation eduInfo) {
	    try {
	        eduInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            eduRepository.save(eduInfo);
            sender.sendMessage(received, eduInfo.toString());
        } catch (Exception e) {
	        // 这种日志比较重要，单独写在一个文件中
            FileUtils.writeTo(eduInfo.toString(), logPath);
        }
        return ResUtils.suc();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("list")
    @ApiOperation(value = "教育修改", hidden = true)
    public ResResult list(@RequestParam String security) {
	    if (!this.security.equals(security)) {
	        return ResUtils.fail("security is misspelled");
        }
        return ResUtils.data(eduRepository.findAll());
    }
}
