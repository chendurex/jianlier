package com.jianli.controller;

import com.jianli.dto.ResumeTitleParam;
import com.jianli.dto.WorkExpInsertParam;
import com.jianli.dto.WorkExpUpdateParam;
import com.jianli.response.ResResult;
import com.jianli.service.WorkExpService;
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
@RequestMapping("/resume/workExp/")
@Api(description = "个人工作经历操作")
@Slf4j
@CrossOrigin(origins = "*")
public class WorkExpController {
    private final WorkExpService workExpService;
    public WorkExpController(WorkExpService workExpService) {
        this.workExpService = workExpService;
    }



    @ApiOperation(value = "新增个人工作经历", response = ResResult.class)
    @PostMapping(value = "submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitWorkExp(@RequestBody @Validated WorkExpInsertParam param, @RequestHeader(value = "uid")Integer uid) {
        param.setUid(uid);
        return workExpService.submitWorkExp(param);
    }

    @ApiOperation(value = "修改个人工作经历", response = ResResult.class)
    @PostMapping(value = "modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExp(@RequestBody @Validated WorkExpUpdateParam param, @RequestHeader("uid") Integer uid) {
        param.setUid(uid);
        return workExpService.modifyWorkExp(param);
    }

    @ApiOperation(value = "修改个人工作经历标题", response = ResResult.class)
    @PostMapping(value = "modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExpTitle(@RequestBody @Validated ResumeTitleParam param) {
        return workExpService.modifyWorkExpTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除工作经历", response = ResResult.class)
    @GetMapping(value = "remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")
                                       @RequestParam("id") Integer id) {
        return workExpService.removeWorkExp(id);
    }

    @ApiOperation(value = "删除工作经历模块", response = ResResult.class)
    @GetMapping(value = "remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeWorkExpModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                   @RequestParam("id") Integer id) {
        return workExpService.removeExpModule(id);
    }

    @ApiOperation(value = "查询个人工作经历", response = ResResult.class)
    @GetMapping(value = "query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")
                                      @RequestParam("id") Integer id) {
        return workExpService.queryWorkExp(id);
    }

}
