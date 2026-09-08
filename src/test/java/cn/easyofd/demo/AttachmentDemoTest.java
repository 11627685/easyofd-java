package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.attachment.OFDAttachment;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTAttachment;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 附件 Attachment：把任意文件作为附件写入 OFD，并可再读出。
 */
public class AttachmentDemoTest {

    private static final String ATTACHMENT_FILE = "image/123.png";

    @Test
    public void addAttachment() throws IOException {
        File file = new File(ATTACHMENT_FILE);
        assertTrue("缺少示例图片：" + file.getAbsolutePath(), file.exists());

        OFDocument ofDocument = new OFDocument();
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        page.addText("该文件包含一个附件：123.png", 20, 30, 5);
        page.addLine(0, 40, 210, 40);

        CTAttachment attachment = ofDocument.addAttachment(file);
        assertEquals("123.png", attachment.getName());
        assertEquals("png", attachment.getFormat());
        assertEquals(OFDAttachment.ATTACHMENT_DIR + "123.png", attachment.getFileLoc());
        assertEquals("Attachments.xml", ofDocument.getDocument().getAttachments());

        OFDAttachment ofdAttachment = ofDocument.getOfdAttachment();
        assertEquals(1, ofdAttachment.getAttachment().size());

        DemoOutput.save(ofDocument, "attachment/attachment.ofd");
    }
}
