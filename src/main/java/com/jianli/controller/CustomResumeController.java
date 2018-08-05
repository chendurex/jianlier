package com.jianli.controller;

import com.jianli.dto.CustomResumeDescInsertParam;
import com.jianli.dto.CustomResumeDescUpdateParam;
import com.jianli.dto.CustomWorkExpInsertParam;
import com.jianli.dto.CustomWorkExpUpdateParam;
import com.jianli.response.ResResult;
import com.jianli.service.CustomResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author chendurex
 * @date 2018-06-18 13:25
 */
@RestController
@RequestMapping("/resume/custom")
@Api(description = "自定义简历模块")
@Slf4j
@CrossOrigin(origins = "*")
public class CustomResumeController {
    private final CustomResumeService customResumeService;

    public CustomResumeController(CustomResumeService customResumeService) {
        this.customResumeService = customResumeService;
    }

    @ApiOperation(value = "新增自定义简历描述", response = ResResult.class)
    @PostMapping(value = "/desc/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResumeDesc(@RequestBody @Validated CustomResumeDescInsertParam param) {
        return customResumeService.submitCustomResumeDesc(param);
    }

    @ApiOperation(value = "修改自定义简历描述", response = ResResult.class)
    @PostMapping(value = "/desc/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeDesc(@RequestBody @Validated CustomResumeDescUpdateParam param) {
        return customResumeService.modifyCustomResumeDesc(param);
    }

    @ApiOperation(value = "删除自定义简历描述", response = ResResult.class)
    @GetMapping(value = "/desc/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResumeDesc(@ApiParam(name = "id", value = "自定义简历描述ID", example = "1")
                                  @Validated @Min(value = 1) @RequestParam("id") Integer id) {
        return customResumeService.removeCustomResumeDesc(id);
    }

    @ApiOperation(value = "新增工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitCustomWorkExp(@RequestBody @Validated CustomWorkExpInsertParam param) {
        return customResumeService.submitCustomWorkExp(param);
    }

    @ApiOperation(value = "修改工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyCustomWorkExp(@RequestBody @Validated CustomWorkExpUpdateParam param) {
        return customResumeService.modifyCustomWorkExp(param);
    }

    @ApiOperation(value = "删除工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeCustomWorkExp(@ApiParam(name = "id", value = "自定义工作经历ID", example = "1")
                                        @Validated @Min(value = 1) @RequestParam("id") Integer id) {
        return customResumeService.removeCustomWorkExp(id);
    }
}
