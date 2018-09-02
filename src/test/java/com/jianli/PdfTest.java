package com.jianli;

import com.jianli.commons.Html2PdfUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * @author chendurex
 * @date 2018-09-01 15:29
 */
public class PdfTest {



    @Test
    public void testCreatePdf() throws Exception {
        String path = "/usr/local/tools/index.html";
        String dest = "/usr/local/tools/index.pdf";
        FileReader reader = new FileReader(path);
        Html2PdfUtils.writeTo(IOUtils.toString(reader), path, dest);
    }

    @Test
    public void test2() {
        String path = "/usr/local/tools/index.xhtml";
        String dest = "/usr/local/tools/index.pdf";
        Html2PdfUtils.writeTo(path, dest);
    }

    @Test
    public void testHtmlToXHtml() throws Exception {
        String path = "/usr/local/tools/test.html";
        String xpath = "/usr/local/tools/test.xhtml";
        FileInputStream fis = new FileInputStream(path);
        Tidy tidy = new Tidy();
        tidy.setShowWarnings(false);
        tidy.setXmlTags(false);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);//
        tidy.setMakeClean(true);
        Document xmlDoc = tidy.parseDOM(fis, null);
        tidy.pprint(xmlDoc,new FileOutputStream(xpath));
    }
}
