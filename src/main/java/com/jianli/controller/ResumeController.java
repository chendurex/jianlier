package com.jianli.controller;

import com.jianli.commons.FileUtils;
import com.jianli.commons.UniqueSerials;
import com.jianli.dto.ResumeInnerSortDTO;
import com.jianli.dto.ResumeUserUpdateParam;
import com.jianli.dto.UploadResumeDTO;
import com.jianli.exception.PdfException;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
    @Value("${upload.base.filepath}")
    private String baseFilepath;

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
            String realPath = uploadPath.replace(baseFilepath, "");
            resumeService.uploadHeadImg(realPath, resumeId);
            return ResUtils.data(realPath);
        } catch (IOException ex) {
            log.error("upload file fail", ex);
            return ResUtils.fail("图片上传失败，请稍后再试");
        }
    }

    @ApiOperation(value = "上传简历HTML文档", response = ResResult.class)
    @PostMapping(value = "/uploadHTML")
    public ResResult uploadHtml(@RequestBody @Validated UploadResumeDTO uploadResumeDTO, @RequestHeader("uid") Integer uid) {
        return resumeService.uploadHtml(uploadResumeDTO.getHtml(),
                uploadResumeDTO.getResumeId(), uid);
    }

    @ApiOperation(value = "下载PDF文档", response = ResResult.class)
    @PostMapping(value = "/downloadPdf")
    public void downloadPdf(@RequestBody @Validated UploadResumeDTO uploadResumeDTO, @RequestHeader("uid") Integer uid, HttpServletResponse response) {
        String pdf = resumeService.downloadPdf(uploadResumeDTO.getHtml(), uploadResumeDTO.getResumeId(), uid);
        setResponseHeader(response, pdf.substring(pdf.lastIndexOf("/")+1));
        try {
            OutputStream os = response.getOutputStream();
            os.write(FileUtils.fileToByte(pdf));
            os.flush();
        } catch (IOException e) {
            throw new PdfException("下载文件失败，pdf："+pdf, e);
        }
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            response.reset();// 清空输出流
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    @ApiOperation(value = "发送HTML文档给用户", response = ResResult.class)
    @GetMapping(value = "/sendHTML")
    public ResResult sendPdf(@RequestParam("resumeId") Integer resumeId, @RequestParam("uid")Integer uid) {
        return resumeService.sendPdf(resumeId, uid);
    }


    @ApiOperation(value = "修改简历基本信息", response = ResResult.class)
    @PostMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResume(@RequestBody @Validated ResumeUserUpdateParam param, @RequestHeader("uid") Integer uid) {
        param.setUid(uid);
        return resumeService.modifyResumeUserInfo(param);
    }

    @ApiOperation(value = "修改简历内部排序值", response = ResResult.class)
    @PostMapping(value = "/sort/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult modifyResumeInnerSort(@RequestBody @Validated List<ResumeInnerSortDTO> resumeSortDTO) {
        return resumeService.modifyResumeInnerSort(resumeSortDTO);
    }


    @ApiOperation(value = "查询简历信息", response = ResResult.class)
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResResult queryResume(@ApiParam(name = "id", value = "简历ID", example = "1")
                                 @RequestParam("id") Integer id) {
        return resumeService.queryResume(id);
    }
}
