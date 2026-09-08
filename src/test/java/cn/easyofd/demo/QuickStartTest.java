package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * 一、入门：创建 OFD 文档、添加一页、画上文字与图形并保存。
 */
public class QuickStartTest {

    @Test
    public void helloOfd() throws IOException {
        // 创建一个 OFD 文档
        OFDocument ofDocument = new OFDocument();

        // 创建一页并加入文档
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        // 文字：内容、x、y、字号(mm)
        page.addText("你好，easyofd！", 20, 30, 5);

        // 直线
        page.addLine(0, 40, 210, 40);

        // 填充矩形
        CTColor red = new CTColor();
        red.setValue("255 0 0");
        page.addSquare(20, 50, 100, 40, null, red);

        DemoOutput.save(ofDocument, "quickstart.ofd");

        assertTrue(DemoOutput.file("quickstart.ofd").length() > 0);
    }
}
