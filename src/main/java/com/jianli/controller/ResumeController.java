package com.jianli.controller;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.EduBackground;
import com.jianli.domain.Resume;
import com.jianli.domain.SkillMaturity;
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

    @ApiOperation(value = "新增简历基本信息", response = ResResult.class)
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResume(@RequestBody @ApiParam("简历信息") Resume resume, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(resume));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitResume(resume);
    }

    @ApiOperation(value = "修改简历信息", response = ResResult.class)
    @PostMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResume(@RequestBody @ApiParam("简历信息") Resume resume, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(resume));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyResume(resume);
    }

    @ApiOperation(value = "删除简历", response = ResResult.class)
    @PostMapping(value = "/remove")
    public ResResult removeResume(@ApiParam("简历ID")@Min(value = 1)Integer id) {
        return resumeService.removeResume(id);
    }

    @ApiOperation(value = "查询简历信息", response = ResResult.class)
    @GetMapping(value = "/query")
    public ResResult queryResume(@ApiParam("简历ID")@Min(value = 1) Integer id) {
        return resumeService.queryResume(id);
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

    @ApiOperation(value = "删除工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/remove")
    public ResResult removeWorkExp(@ApiParam("工作经历ID")@Min(value = 1)Integer id) {
        return resumeService.removeWorkExp(id);
    }

    @ApiOperation(value = "查询个人工作经历列表", response = ResResult.class)
    @GetMapping(value = "/workExp/list")
    public ResResult listWorkExp(@ApiParam("简历ID")@Min(value = 1) Integer resumeId) {
        return resumeService.listWorkExp(resumeId);
    }

    @ApiOperation(value = "查询个人工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/query")
    public ResResult queryWorkExp(@ApiParam("工作经历ID")@Min(value = 1) Integer id) {
        return resumeService.queryWorkExp(id);
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

    @ApiOperation(value = "删除教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/remove")
    public ResResult removeEduBackground(@ApiParam("教育背景ID")@Min(value = 1) Integer id) {
        return resumeService.removeEduBackground(id);
    }

    @ApiOperation(value = "查询个人教育背景列表", response = ResResult.class)
    @GetMapping(value = "/eduBackground/list")
    public ResResult lisEduBackground(@ApiParam("简历ID")@Min(value = 1) Integer resumeId) {
        return resumeService.listEduBackground(resumeId);
    }

    @ApiOperation(value = "查询个人教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/query")
    public ResResult queryEduBackground(@ApiParam("教育背景ID")@Min(value = 1) Integer id) {
        return resumeService.queryEduBackground(id);
    }

    @ApiOperation(value = "新增个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitSkillMaturity(@RequestBody @ApiParam("技能熟练度") SkillMaturity skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(skill));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitSkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillMaturity(@RequestBody @ApiParam("技能熟练度") SkillMaturity skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(skill));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifySkillMaturity(skill);
    }

    @ApiOperation(value = "删除技能熟悉度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/remove")
    public ResResult removeSkillMaturity(@ApiParam("技能熟悉度ID")@Min(value = 1) Integer id) {
        return resumeService.removeSkillMaturity(id);
    }

    @ApiOperation(value = "查询个人技能熟练度列表", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/list")
    public ResResult listSkillMaturity(@ApiParam("简历ID")@Min(value = 1) Integer resumeId) {
        return resumeService.listSkillMaturity(resumeId);
    }

    @ApiOperation(value = "查询个人技能熟练度", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/query")
    public ResResult querySkillMaturity(@ApiParam("技能熟练度ID")@Min(value = 1) Integer id) {
        return resumeService.querySkillMaturity(id);
    }
}
