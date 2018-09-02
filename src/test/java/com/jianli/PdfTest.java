package com.jianli;

import com.jianli.component.Html2Pdf;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;

/**
 * @author chendurex
 * @date 2018-09-01 15:29
 */
public class PdfTest extends  BaseTest{

    @Autowired
    private Html2Pdf html2Pdf;
    @Test
    public void createPdf() throws Exception {
        String path = "/usr/local/tools/test.html";
        String dest = "/usr/local/tools/test.pdf";
        FileReader fileReader = new FileReader(path);
        html2Pdf.writeTO(IOUtils.toString(fileReader), path, dest);
    }
}
