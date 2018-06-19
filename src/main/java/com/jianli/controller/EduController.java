package com.jianli.controller;

import com.jianli.education.EduInfo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jianli.repo.EduRepo;
import com.jianli.commons.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EduController {

	@Autowired
    private EduRepo eduRepository;
	@Value("${edu.user.security}")
	private String security;
    @Value("${log.file.path}")
    private String logPath;
	@CrossOrigin(origins = "*")
    @PostMapping(value = "submit")
    public ResResult submit(@RequestBody EduInfo eduInfo) {
	    try {
	        eduInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            eduRepository.save(eduInfo);
        } catch (Exception e) {
            FileUtils.writeTo(eduInfo.toString(), logPath);
        }
        return ResUtils.suc();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("list")
    public ResResult list(@RequestParam String security) {
	    if (!this.security.equals(security)) {
	        return ResUtils.fail("security is misspelled");
        }
        return ResUtils.data(eduRepository.findAll());
    }
}
