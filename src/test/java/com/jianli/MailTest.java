package com.jianli;

import com.docraptor.ApiClient;
import com.docraptor.Doc;
import com.docraptor.DocApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author chendurex
 * @description
 * @date 2018-03-31 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class MailTest {
    @Autowired
    private com.jianli.component.MailSender sender;
    @Value("${spring.component.default.context}")
    private String test;
    @Test
    public void testMail() {
        sender.send("461240503@qq.com", "/usr/local/tools/简历儿需求文档1.0.pdf");
        sender.send("316121113@qq.com", "/usr/local/tools/x.jp");
    }


    @Test
    public void test () {
        System.out.println(test);
    }

    @Test
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
