package com.jianli.controller;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

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
    public ResResult submitWorkExp(@RequestBody @ApiParam("工作经历") WorkExp exp, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(exp));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitWorkExp(exp);
    }

    @ApiOperation(value = "修改个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExp(@RequestBody @ApiParam("工作经历") WorkExp exp, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(exp));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyWorkExp(exp);
    }

    @ApiOperation(value = "查询个人工作经历列表", response = ResResult.class)
    @GetMapping(value = "/workExp/list")
    public ResResult listWorkExp(@ApiParam("简历ID")@Min(value = 1) Integer resumeId) {
        return resumeService.listWorkExp(resumeId);
    }

    @ApiOperation(value = "查询个人工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/query")
    public ResResult queryWorkExp(@ApiParam("工作经历ID")@Min(value = 1) Integer id) {
        return resumeService.workExp(id);
    }



    @ApiOperation(value = "新增个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitEduBackground(@RequestBody @ApiParam("教育背景") EduBackground edu, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(edu));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitEduBackground(edu);
    }

    @ApiOperation(value = "修改个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackground(@RequestBody @ApiParam("教育背景") EduBackground edu, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(edu));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyEduBackground(edu);
    }

    @ApiOperation(value = "查询个人教育背景列表", response = ResResult.class)
    @GetMapping(value = "/eduBackground/list")
    public ResResult lisEduBackground(@ApiParam("简历ID")@Min(value = 1) Integer resumeId) {
        return resumeService.listEduBackground(resumeId);
    }

    @ApiOperation(value = "查询个人教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/query")
    public ResResult queryEduBackground(@ApiParam("教育背景ID")@Min(value = 1) Integer id) {
        return resumeService.eduBackground(id);
    }
}
