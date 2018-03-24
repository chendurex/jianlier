package com.jianli.controller;

import com.jianli.commons.UniqueSerials;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author chendurex
 * @date 2018-03-17 12:22
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Value("${upload.image.filepath}")
    private String imageFilepath;

    @PostMapping(value = "uploadImage")
    public ResResult uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return ResUtils.fail("请先登录账号再上传");
        }
        if (file.isEmpty()) {
            return ResUtils.fail("请选择上传的文件");
        }
        try {
            final String originName = file.getOriginalFilename();
            final String suffix = originName.substring(originName.lastIndexOf(".", originName.length()));
            String uniqueSerials = UniqueSerials.uniqueSerials(userId) + suffix;
            Path path = Paths.get(imageFilepath + uniqueSerials);
            Files.write(path, file.getBytes());
            return ResUtils.suc(uniqueSerials);
        } catch (IOException ex) {
            log.error("upload file fail", ex);
            return ResUtils.fail("图片上传失败，请稍后再试");
        }
    }
}
