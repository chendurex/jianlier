package com.jianli.controller;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.WorkExp;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author chendurex
 * @date 2018-06-18 13:25
 */
@RestController
@RequestMapping("/resume")
@Api(description = "个人简介操作")
@Slf4j
public class ResumeController {
    private final ResumeService resumeService;
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @ApiOperation(value = "新增个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult workExpSubmit(@RequestBody @ApiParam("工作经历") WorkExp exp, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(exp));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.workExpSubmit(exp);
    }

    @ApiOperation(value = "修改个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult workExpModify(@RequestBody @ApiParam("工作经历") WorkExp exp, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(exp));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        exp.setModifyTime(exp.getCreateTime());
        exp.setModifyUid(exp.getUid());
        return resumeService.workExpModify(exp);
    }
}
