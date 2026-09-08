package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.Tpls.OFDTemplate;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;

import java.io.IOException;

/**
 * 五、模板 Template：把多个页面共用的内容放进模板页，减少文件体积。
 */
public class TemplateDemoTest {

    @Test
    public void useTemplate() throws IOException {
        OFDocument ofDocument = new OFDocument();
        ofDocument.getOfdCommonData().setPagePhysicaArea(100, 100);

        // 模板页：所有页面共用的背景内容
        OFDTemplate template = new OFDTemplate(ofDocument);
        CTColor skyBlue = new CTColor();
        skyBlue.setValue("135 206 250");
        template.addSquare(0, 0, 100, 100, null, skyBlue);
        template.addText("模版页内容", 50, 50);
        template.addEllipse(50, 50, 10, 10, 3.0);

        int templateId = ofDocument.getOfdCommonData().addTemplate(template);

        // 第一页：引用模板 + 自己的内容
        OFDPage page = new OFDPage(ofDocument);
        page.addTemplatePage(templateId, "Backqround");
        page.addLine(0, 1, 210, 1);
        for (int n = 0; n <= 210 / 10; n++) {
            page.addLine(n * 10, 1, n * 10, 3);
            page.addText((n * 10) + "", n * 10, 4);
        }
        ofDocument.addPage(page);

        // 第二页：复用同一模板
        OFDPage page2 = new OFDPage(ofDocument);
        page2.addTemplatePage(templateId, "Backqround");
        page2.addText("第二页，复用模板背景", 20, 30, 5);
        ofDocument.addPage(page2);

        DemoOutput.save(ofDocument, "template/template.ofd");
    }
}
