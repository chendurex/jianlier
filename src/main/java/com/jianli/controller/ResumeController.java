package com.jianli.controller;

import com.jianli.commons.UniqueSerials;
import com.jianli.dto.*;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @Value("${upload.image.filepath}")
    private String imageFilepath;

    @ApiOperation(value = "上传简历头像", response = ResResult.class)
    @PostMapping(value = "/uploadHeadImg")
    public ResResult uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("uid") Integer userId, @RequestParam("resumeId")Integer resumeId) {
        if (StringUtils.isEmpty(userId)) {
            return ResUtils.fail("请先登录账号再上传");
        }
        if (file.isEmpty()) {
            return ResUtils.fail("请选择上传的文件");
        }
        try {
            final String originName = file.getOriginalFilename();
            final String suffix = originName.substring(originName.lastIndexOf(".", originName.length()));
            String uploadPath = imageFilepath + UniqueSerials.uniqueSerials(String.valueOf(userId)) + suffix;
            Path path = Paths.get(uploadPath);
            Files.write(path, file.getBytes());
            resumeService.uploadHeadImg(uploadPath, resumeId);
            return ResUtils.suc(uploadPath);
        } catch (IOException ex) {
            log.error("upload file fail", ex);
            return ResUtils.fail("图片上传失败，请稍后再试");
        }
    }

    @ApiOperation(value = "上传简历HTML文档", response = ResResult.class)
    @PostMapping(value = "/uploadHTML")
    public ResResult uploadHtml(@RequestParam("html") String html, @RequestParam("resumeId") Integer resumeId) {
        resumeService.uploadHtml(html, resumeId);
        return ResUtils.suc();
    }

    @ApiOperation(value = "发送HTML文档给用户", response = ResResult.class)
    @PostMapping(value = "/sendHTML")
    public ResResult sendPdf(@RequestParam("resumeId") Integer resumeId) {
        return resumeService.sendPdf(resumeId);
    }

    /*@ApiOperation(value = "新增简历基本信息", response = ResResult.class)
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResume(@RequestBody @Validated ResumeUserInsertParam param) {
        //return resumeService.submitResumeUserInfo(param);
        resumeService.getResumeIdByUid(9999);
        return ResUtils.suc();
    }*/

    @ApiOperation(value = "修改简历基本信息", response = ResResult.class)
    @PostMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResume(@RequestBody @Validated ResumeUserUpdateParam param) {
        return resumeService.modifyResumeUserInfo(param);
    }

    @ApiOperation(value = "修改简历内部排序值", response = ResResult.class)
    @PostMapping(value = "/sort/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeInnerSort(@RequestBody @Validated List<ResumeInnerSortDTO> resumeSortDTO) {
        return resumeService.modifyResumeInnerSort(resumeSortDTO);
    }

    /*@ApiOperation(value = "删除简历", response = ResResult.class)
    @GetMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResume(@ApiParam(name = "id", value = "简历ID", example = "1")
                                  @RequestParam("id") Integer id) {
        return resumeService.removeResume(id);
    }*/


    @ApiOperation(value = "新增简历自我介绍", response = ResResult.class)
    @PostMapping(value = "/summary/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitResumeSummary(@RequestBody @Validated ResumeSummaryInsertParam param) {
        return resumeService.submitResumeSummary(param.getResumeId(), param.getSummary(), param.getSort());
    }

    @ApiOperation(value = "修改简历自我介绍", response = ResResult.class)
    @PostMapping(value = "/summary/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeSummary(@RequestBody @Validated ResumeSummaryParam param) {
        return resumeService.modifyResumeSummary(param.getResumeId(), param.getSummary());
    }

    @ApiOperation(value = "删除自我介绍模块", response = ResResult.class)
    @GetMapping(value = "/summary/remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeResumeSummary(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return resumeService.removeResumeSummary(id);
    }

    @ApiOperation(value = "修改简历自我介绍标题", response = ResResult.class)
    @PostMapping(value = "/summary/modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySummaryTitle(@RequestBody @Validated ResumeTitleParam param) {
        return resumeService.modifyResumeSummaryTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "查询简历信息", response = ResResult.class)
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryResume(@ApiParam(name = "id", value = "简历ID", example = "1")
                                 @RequestParam("id") Integer id) {
        return resumeService.queryResume(id);
    }


    @ApiOperation(value = "新增个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitWorkExp(@RequestBody @Validated WorkExpInsertParam param) {
        return resumeService.submitWorkExp(param);
    }

    @ApiOperation(value = "修改个人工作经历", response = ResResult.class)
    @PostMapping(value = "/workExp/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExp(@RequestBody @Validated WorkExpUpdateParam param) {
        return resumeService.modifyWorkExp(param);
    }

    @ApiOperation(value = "修改个人工作经历标题", response = ResResult.class)
    @PostMapping(value = "/workExp/modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyWorkExpTitle(@RequestBody @Validated ResumeTitleParam param) {
        return resumeService.modifyWorkExpTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")
                                       @RequestParam("id") Integer id) {
        return resumeService.removeWorkExp(id);
    }

    @ApiOperation(value = "删除工作经历模块", response = ResResult.class)
    @GetMapping(value = "/workExp/remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeWorkExpModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                   @RequestParam("id") Integer id) {
        return resumeService.removeExpModule(id);
    }

    @ApiOperation(value = "查询个人工作经历", response = ResResult.class)
    @GetMapping(value = "/workExp/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryWorkExp(@ApiParam(name = "id", value = "工作经历ID", example = "1")
                                      @RequestParam("id") Integer id) {
        return resumeService.queryWorkExp(id);
    }

    @ApiOperation(value = "新增个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitEduBackground(@RequestBody @Validated EduBackgroundInsertParam param) {
        return resumeService.submitEduBackground(param);
    }

    @ApiOperation(value = "修改个人教育背景", response = ResResult.class)
    @PostMapping(value = "/eduBackground/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackground(@RequestBody @Validated EduBackgroundUpdateParam param) {
        return resumeService.modifyEduBackground(param);
    }

    @ApiOperation(value = "修改个人教育背景标题", response = ResResult.class)
    @PostMapping(value = "/eduBackground/modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyEduBackgroundTitle(@RequestBody @Validated ResumeTitleParam param) {
        return resumeService.modifyEduBackgroundTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")
                                             @RequestParam("id") Integer id) {
        return resumeService.removeEduBackground(id);
    }

    @ApiOperation(value = "删除教育背景模块", response = ResResult.class)
    @GetMapping(value = "/eduBackground/remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeEduBackgroundModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return resumeService.removeEduModule(id);
    }

    @ApiOperation(value = "查询个人教育背景", response = ResResult.class)
    @GetMapping(value = "/eduBackground/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryEduBackground(@ApiParam(name = "id", value = "教育背景ID", example = "1")
                                            @RequestParam("id") Integer id) {
        return resumeService.queryEduBackground(id);
    }

    @ApiOperation(value = "新增个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult submitSkillMaturity(@RequestBody @Validated SkillMaturityInsertParam skill) {
        return resumeService.submitSkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillMaturity(@RequestBody @Validated SkillMaturityUpdateParam skill) {
        return resumeService.modifySkillMaturity(skill);
    }

    @ApiOperation(value = "修改个人技能熟练度标题", response = ResResult.class)
    @PostMapping(value = "/skillMaturity/modifyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifySkillTitle(@RequestBody @Validated ResumeTitleParam param) {
        return resumeService.modifySkillMaturityTitle(param.getResumeId(), param.getSort(), param.getTitle());
    }

    @ApiOperation(value = "删除技能熟悉度", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeSkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")
                                             @RequestParam("id") Integer id) {
        return resumeService.removeSkillMaturity(id);
    }

    @ApiOperation(value = "删除技能熟悉度模块", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/remove/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult removeSkillMaturityModule(@ApiParam(name = "id", value = "简历ID", example = "1")
                                         @RequestParam("id") Integer id) {
        return resumeService.removeSkillModule(id);
    }

    @ApiOperation(value = "查询个人技能熟练度", response = ResResult.class)
    @GetMapping(value = "/skillMaturity/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult querySkillMaturity(@ApiParam(name = "id", value = "技能熟练度ID", example = "1")
                                            @RequestParam("id") Integer id) {
        return resumeService.querySkillMaturity(id);
    }

}
