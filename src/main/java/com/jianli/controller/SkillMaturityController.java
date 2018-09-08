package com.jianli.controller;

import com.jianli.dto.ResumeTitleParam;
import com.jianli.dto.SkillMaturityInsertParam;
import com.jianli.dto.SkillMaturityUpdateParam;
import com.jianli.response.ResResult;
import com.jianli.service.SkillMaturityService;
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
@RequestMapping("/resume/skillMaturity/")
@Api(description = "简历技能熟练度操作")
@Slf4j
@CrossOrigin(origins = "*")
public class SkillMaturityController {
    private final SkillMaturityService skillMaturityService;
    public SkillMaturityController(SkillMaturityService skillMaturityService) {
        this.skillMaturityService = skillMaturityService;
    }


    @ApiOperation(value = "新增个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitSkillMaturity(@RequestBody @Validated SkillMaturityInsertParam skill, @RequestHeader("uid") Integer uid) {
        skill.setUid(uid);
        return skillMaturityService.submitSkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillMaturity(@RequestBody @Validated SkillMaturityUpdateParam skill, @RequestHeader("uid") Integer uid) {
        skill.setUid(uid);
        return skillMaturityService.modifySkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度标题", response = ResResult.class)
    @PostMapping(value = "modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillTitle(@RequestBody @Validated ResumeTitleParam param) {
        return skillMaturityService.modifySkillMaturityTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除技能熟悉度", response = ResResult.class)
    @GetMapping(value = "remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeSkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")
                                             @RequestParam("id") Integer id) {
        return skillMaturityService.removeSkillMaturity(id);
    }

    @ApiOperation(value = "删除技能熟悉度模块", response = ResResult.class)
    @GetMapping(value = "remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeSkillMaturityModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return skillMaturityService.removeSkillModule(id);
    }

    @ApiOperation(value = "查询个人技能熟练度", response = ResResult.class)
    @GetMapping(value = "query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult querySkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")
                                            @RequestParam("id") Integer id) {
        return skillMaturityService.querySkillMaturity(id);
    }

}
