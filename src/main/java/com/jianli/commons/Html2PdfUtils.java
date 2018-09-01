package com.jianli.commons;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jianli.exception.PdfException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @author chendurex
 * @date 2018-08-26 15:01
 */
public class Html2PdfUtils {
    private static final String SIM_SUN_FONT = "/usr/share/fonts/SimSun.ttf";

    public static void writeTo(String origin, String dest) {
        try(OutputStream os = new FileOutputStream(dest);
            FileInputStream fis = new FileInputStream(origin)) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
            fontProvider.register(SIM_SUN_FONT);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, fis, null, Charset.forName("UTF-8"), fontProvider);
            document.close();
        } catch (Exception e) {
            throw new PdfException("生成PDF文件失败，", e);
        }
    }
}
