package com.jianli.controller;

import com.jianli.dto.ResumeSummaryInsertParam;
import com.jianli.dto.ResumeSummaryParam;
import com.jianli.dto.ResumeTitleParam;
import com.jianli.response.ResResult;
import com.jianli.service.SummaryService;
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
@RequestMapping("/resume/summary/")
@Api(description = "个人自我介绍操作")
@Slf4j
@CrossOrigin(origins = "*")
public class SummaryController {
    private final SummaryService summaryService;
    public SummaryController(SummaryService resumeService) {
        this.summaryService = resumeService;
    }


    @ApiOperation(value = "新增简历自我介绍", response = ResResult.class)
    @PostMapping(value = "submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResumeSummary(@RequestBody @Validated ResumeSummaryInsertParam param) {
        return summaryService.submitResumeSummary(param.getResumeId(), param.getSummary(), param.getSort());
    }

    @ApiOperation(value = "修改简历自我介绍", response = ResResult.class)
    @PostMapping(value = "modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeSummary(@RequestBody @Validated ResumeSummaryParam param) {
        return summaryService.modifyResumeSummary(param.getResumeId(), param.getSummary());
    }

    @ApiOperation(value = "删除自我介绍模块", response = ResResult.class)
    @GetMapping(value = "remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResumeSummary(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return summaryService.removeResumeSummary(id);
    }

    @ApiOperation(value = "修改简历自我介绍标题", response = ResResult.class)
    @PostMapping(value = "modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySummaryTitle(@RequestBody @Validated ResumeTitleParam param) {
        return summaryService.modifyResumeSummaryTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

}
