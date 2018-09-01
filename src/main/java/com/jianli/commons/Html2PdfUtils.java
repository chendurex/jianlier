package com.jianli.commons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
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

    public static void writeTo(String origin, String dest) {
        try(OutputStream os = new FileOutputStream(dest);
            FileInputStream fis = new FileInputStream(origin)) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            BaseFont chinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font f=new Font(chinese);
            Paragraph p=new Paragraph("chinese",f);
            document.add(p);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, fis, Charset.forName("UTF-8"));
            document.close();
        } catch (Exception e) {
            throw new PdfException("生成PDF文件失败，", e);
        }
    }
}
