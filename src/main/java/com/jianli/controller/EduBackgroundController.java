package com.jianli.controller;

import com.jianli.dto.EduBackgroundInsertParam;
import com.jianli.dto.EduBackgroundUpdateParam;
import com.jianli.dto.ResumeTitleParam;
import com.jianli.response.ResResult;
import com.jianli.service.EduBackgroundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author chendurex
 * @date 2018-06-18 13:25
 */
@RestController
@RequestMapping("/resume/eduBackground/")
@Api(description = "简历教育背景操作")
@Slf4j
@CrossOrigin(origins = "*")
public class EduBackgroundController {
    private final EduBackgroundService eduBackgroundService;
    public EduBackgroundController(EduBackgroundService eduBackgroundService) {
        this.eduBackgroundService = eduBackgroundService;
    }


    @ApiOperation(value = "新增个人教育背景", response = ResResult.class)
    @PostMapping(value = "submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitEduBackground(@RequestBody @Validated EduBackgroundInsertParam param, @RequestHeader("uid") Integer uid) {
        param.setUid(uid);
        return eduBackgroundService.submitEduBackground(param);
    }

    @ApiOperation(value = "修改个人教育背景", response = ResResult.class)
    @PostMapping(value = "modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackground(@RequestBody @Validated EduBackgroundUpdateParam param, @RequestHeader("uid") Integer uid) {
        param.setUid(uid);
        return eduBackgroundService.modifyEduBackground(param);
    }

    @ApiOperation(value = "修改个人教育背景标题", response = ResResult.class)
    @PostMapping(value = "modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackgroundTitle(@RequestBody @Validated ResumeTitleParam param) {
        return eduBackgroundService.modifyEduBackgroundTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除教育背景", response = ResResult.class)
    @GetMapping(value = "remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")
                                             @RequestParam("id") Integer id) {
        return eduBackgroundService.removeEduBackground(id);
    }

    @ApiOperation(value = "删除教育背景模块", response = ResResult.class)
    @GetMapping(value = "remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeEduBackgroundModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return eduBackgroundService.removeEduModule(id);
    }

    @ApiOperation(value = "查询个人教育背景", response = ResResult.class)
    @GetMapping(value = "query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")
                                            @RequestParam("id") Integer id) {
        return eduBackgroundService.queryEduBackground(id);
    }

   
}
