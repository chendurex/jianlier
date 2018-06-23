package com.jianli.controller;

import com.jianli.commons.BeanUtils;
import com.jianli.dto.*;
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
@CrossOrigin(origins = "*")
public class ResumeController {
    private final ResumeService resumeService;
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @ApiOperation(value = "新增简历基本信息", response = ResResult.class)
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResume(@RequestBody ResumeInsertParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitResume(param);
    }

    @ApiOperation(value = "修改简历信息", response = ResResult.class)
    @PostMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResume(@RequestBody ResumeUpdateParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyResume(param);
    }

    @ApiOperation(value = "删除简历", response = ResResult.class)
    @GetMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResume(@ApiParam(name = "id", value = "简历ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.removeResume(id);
    }

    @ApiOperation(value = "查询简历信息", response = ResResult.class)
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryResume(@ApiParam(name = "id", value = "简历ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.queryResume(id);
    }


    @ApiOperation(value = "新增个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitWorkExp(@RequestBody WorkExpInsertParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitWorkExp(param);
    }

    @ApiOperation(value = "修改个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExp(@RequestBody WorkExpUpdateParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyWorkExp(param);
    }

    @ApiOperation(value = "删除工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.removeWorkExp(id);
    }

    @ApiOperation(value = "查询个人工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.queryWorkExp(id);
    }

    @ApiOperation(value = "查询个人工作经历列表", response = ResResult.class)
    @GetMapping(value = "/workExp/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult listWorkExp(@ApiParam(name = "resumeId", value = "简历ID", example = "1")@Min(value = 1) @RequestParam("resumeId") Integer resumeId) {
        return resumeService.listWorkExp(resumeId);
    }

    @ApiOperation(value = "新增个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitEduBackground(@RequestBody EduBackgroundInsertParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitEduBackground(param);
    }

    @ApiOperation(value = "修改个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackground(@RequestBody EduBackgroundUpdateParam param, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(param));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifyEduBackground(param);
    }

    @ApiOperation(value = "删除教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.removeEduBackground(id);
    }

    @ApiOperation(value = "查询个人教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.queryEduBackground(id);
    }

    @ApiOperation(value = "查询个人教育背景列表", response = ResResult.class)
    @GetMapping(value = "/eduBackground/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult lisEduBackground(@ApiParam(name = "resumeId", value = "简历ID", example = "1")@Min(value = 1) @RequestParam("resumeId") Integer resumeId) {
        return resumeService.listEduBackground(resumeId);
    }

    @ApiOperation(value = "新增个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitSkillMaturity(@RequestBody SkillMaturityInsertParam skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(skill));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.submitSkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillMaturity(@RequestBody SkillMaturityUpdateParam skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.warn("用户数据不正确，用户提交的数据内容为：{}", BeanUtils.deepPrint(skill));
            return ResUtils.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return resumeService.modifySkillMaturity(skill);
    }

    @ApiOperation(value = "删除技能熟悉度", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeSkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.removeSkillMaturity(id);
    }

    @ApiOperation(value = "查询个人技能熟练度", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult querySkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")@Min(value = 1) @RequestParam("id") Integer id) {
        return resumeService.querySkillMaturity(id);
    }

    @ApiOperation(value = "查询个人技能熟练度列表", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult listSkillMaturity(@ApiParam(name = "resumeId", value = "简历ID", example = "1")@Min(value = 1) @RequestParam("resumeId") Integer resumeId) {
        return resumeService.listSkillMaturity(resumeId);
    }
}
