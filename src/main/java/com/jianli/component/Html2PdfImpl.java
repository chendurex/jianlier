package com.jianli.component;

import com.jianli.commons.FileUtils;
import com.jianli.exception.PdfException;
import com.pdfcrowd.Pdfcrowd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chendurex
 * @date 2018-09-02 22:33
 */
@Component
@Slf4j
public class Html2PdfImpl implements Html2Pdf {
    private final String username;
    private final String apiKey;

    public Html2PdfImpl(@Value("${spring.pdf.username}") String username, @Value("${spring.pdf.apikey}") String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }
    @Override
    public void writeTO(String text, String origin, String dest) {
        if (FileUtils.exist(origin)) {
            log.info("已经生成过的文档，不需要再次生成,origin:{}, dest:{}", origin, dest);
            return ;
        }
        // 写入原始HTML文件
        FileUtils.writeToWithoutAppend(text, origin);
        try {
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(username, apiKey);
            client.convertStringToFile(text, dest);
        } catch(Exception e) {
            FileUtils.removeFile(origin);
            throw new PdfException("生成PDF失败", e);
        }
    }
}
