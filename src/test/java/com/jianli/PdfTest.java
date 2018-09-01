package com.jianli;

import com.jianli.commons.Html2PdfUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author chendurex
 * @date 2018-09-01 15:29
 */
public class PdfTest {



    @Test
    public void testCreatePdf() throws Exception {
        String path = "/usr/local/tools/test.xhtml";
        String dest = "/usr/local/tools/test.pdf";
        Html2PdfUtils.writeTo2(path, dest);
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
