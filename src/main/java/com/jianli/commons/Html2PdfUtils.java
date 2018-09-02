package com.jianli.commons;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jianli.exception.PdfException;
import org.w3c.tidy.Tidy;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author chendurex
 * @date 2018-08-26 15:01
 */
public class Html2PdfUtils {
    private static final String SIM_SUN_FONT = "/usr/share/fonts/SimSun.ttf";

    public static void writeTo(String text, String origin, String dest) {
        FileUtils.createIfNotExist(origin);
        FileUtils.createIfNotExist(dest);
        String xhtml = origin.replace(".html", ".xhtml");
        try (StringReader reader = new StringReader(text);
             FileOutputStream fos = new FileOutputStream(xhtml);
             FileWriter writer = new FileWriter(origin)){
            // 写入原始html
            writer.write(text);
            Tidy tidy = new Tidy();
            tidy.setShowWarnings(false);
            tidy.setXmlTags(false);
            tidy.setInputEncoding("UTF-8");
            tidy.setOutputEncoding("UTF-8");
            tidy.setXHTML(true);
            tidy.setMakeClean(true);
            org.w3c.dom.Document xmlDoc = tidy.parseDOM(reader, null);
            // 写入到转化后的xhtml
            tidy.pprint(xmlDoc, fos);
            // 渲染成pdf
            writeTo(xhtml, dest);
        } catch (Exception e) {
            throw new PdfException("生成PDF文件失败，", e);
        }

    }

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

    public static void writeTo2(String origin, String dest) throws Exception{
        OutputStream outputStream = new FileOutputStream(dest);
        BaseFont bf = BaseFont.createFont(SIM_SUN_FONT, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bf, 12);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph context = new Paragraph();
        ElementList elementList = MyXMLWorkerHelper.parseToElementList(new FileInputStream(origin), null);
        for (Element element : elementList) {
            context.add(element);
        }
        document.add(context);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(
                "亲笔签名/公司公章： _______________________________", fontChinese));
        document.add(new Paragraph("日期： ", fontChinese));
        document.close();
    }
}
