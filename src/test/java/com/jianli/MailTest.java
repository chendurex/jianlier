package com.jianli;

import com.docraptor.ApiClient;
import com.docraptor.Doc;
import com.docraptor.DocApi;
import com.jianli.controller.BinEducationController;
import com.jianli.domain.BinEducation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author chendurex
 * @description
 * @date 2018-03-31 22:52
 */

public class MailTest extends BaseTest {
    @Autowired
    private com.jianli.component.MailSender sender;
    @Value("${spring.mail.default.context}")
    private String test;
    @Value("${edu.mail.received}")
    private String received;
    @Autowired
    private BinEducationController eduControler;
    @Test
    public void testMail() {
        sender.send(received, "/usr/local/tools/x.jp");
    }

    @Test
    public void testSendMessage() {
        BinEducation info = new BinEducation();
        info.setName("haha");
        info.setContact("11111");
        info.setQuestion("iiiiiiiii");
        eduControler.submit(info);
        
    }


    @Test
    public void test () {
        System.out.println(test);
    }

    //@Test
    public void testConvertPdf() throws Exception {
        DocApi docraptor = new DocApi();
        ApiClient client = docraptor.getApiClient();
        client.setUsername("YOUR_API_KEY_HERE");
        //client.setDebugging(true);

        Doc doc = new Doc();
        doc.setTest(true);                                                   // test documents are free but watermarked
        doc.setDocumentContent("<html><body>Hello World</body></html>");     // supply content directly
        // doc.setDocumentUrl("http://docraptor.com/examples/invoice.html"); // or use a url
        doc.setDocumentType(Doc.DocumentTypeEnum.PDF);                       // PDF or XLS or XLSX
        doc.setName("docraptor-java.pdf");                                   // help you find a document later
        doc.setJavascript(true);                                             // enable JavaScript processing
        // prince_options = new PrinceOptions();
        // doc.setPrinceOptions(prince_options);
        // prince_options.setMedia("screen");                                // use screen styles instead of print styles
        // prince_options.setBaseurl("http://hello.com")                     // pretend URL when using document_content
        Files.write(Paths.get("/usr/local/tools/hehe.pdf"), docraptor.createDoc(doc));
    }
}
