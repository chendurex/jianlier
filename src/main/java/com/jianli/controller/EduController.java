package com.jianli.controller;

import com.jianli.education.EduInfo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chendurex
 * @description
 * @date 2018-04-18 20:34
 */
@RestController
@RequestMapping("/edu")
public class EduController {
    @PostMapping(value = "submit")
    public ResResult submit(@RequestBody EduInfo eduInfo) {
        System.out.println(eduInfo);
        return ResUtils.suc();
    }
}
