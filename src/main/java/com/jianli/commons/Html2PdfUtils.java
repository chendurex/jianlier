package com.jianli.commons;

import com.docraptor.ApiClient;
import com.docraptor.Doc;
import com.docraptor.DocApi;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author chendurex
 * @date 2018-08-26 15:01
 */
public class Html2PdfUtils {
    public static void html2Pdf(String html, String fileName) {
        DocApi docraptor = new DocApi();
        ApiClient client = docraptor.getApiClient();
        client.setApiKey("basicAuth");

        client.setUsername("app key");
        Doc doc = new Doc();
        doc.test(true);
        doc.setDocumentContent(html);
        doc.setDocumentType(Doc.DocumentTypeEnum.PDF);
        doc.setName(fileName.substring(fileName.lastIndexOf(".", fileName.length())));
        try {
            Files.write(Paths.get(fileName), docraptor.createDoc(doc));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
