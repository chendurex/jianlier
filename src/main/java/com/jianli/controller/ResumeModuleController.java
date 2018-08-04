package com.jianli.controller;

import com.jianli.dto.*;
import com.jianli.response.ResResult;
import com.jianli.service.ResumeModuleService;
import com.jianli.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author chendurex
 * @date 2018-06-18 13:25
 */
@RestController
@RequestMapping("/resume/module")
@Api(description = "个人简介操作")
@Slf4j
@CrossOrigin(origins = "*")
public class ResumeModuleController {
    private final ResumeModuleService resumeModuleService;

    public ResumeModuleController(ResumeModuleService resumeModuleService) {
        this.resumeModuleService = resumeModuleService;
    }

    @ApiOperation(value = "新增简历模块", response = ResResult.class)
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResumeModule(@RequestBody @Validated ResumeModuleInsertParam param) {
        return resumeModuleService.submitResumeModule(param);
    }

    @ApiOperation(value = "修改简历模块", response = ResResult.class)
    @PostMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeModule(@RequestBody @Validated ResumeModuleUpdateParam param) {
        return resumeModuleService.modifyResumeModule(param);
    }

    @ApiOperation(value = "删除简历模块", response = ResResult.class)
    @GetMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResumeModule(@ApiParam(name = "id", value = "简历模块ID", example = "1")
                                  @Validated @Min(value = 1) @RequestParam("id") Integer id) {
        return resumeModuleService.removeResumeModule(id);
    }

    @ApiOperation(value = "查询简历模块", response = ResResult.class)
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryResume(@ApiParam(name = "uid", value = "用户ID", example = "1")
                                 @Validated @Min(value = 1) @RequestParam("uid") Integer uid) {
        return resumeModuleService.query(uid);
    }

}
