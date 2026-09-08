package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * 四、文字 Text：注册字体、设置字号颜色，以及沿圆/椭圆排布文字。
 */
public class TextDemoTest {

    /**
     * 开源字体（思源黑体，SIL Open Font License），可自由分发，见 font/README.md
     */
    private static final String FONT_FILE = "font/思源黑体 Normal.ttc";
    private static final String FONT_NAME = "思源黑体 Normal";

    @Test
    public void drawText() throws IOException {
        assertTrue("缺少开源字体：" + FONT_FILE + "，详见 font/README.md", new File(FONT_FILE).exists());

        OFDocument ofDocument = new OFDocument();
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        // 注册并使用开源字体
        int fontId = ofDocument.getOfdCommonData().registerFont(FONT_NAME, FONT_FILE);
        Font font = new Font(FONT_NAME, Font.PLAIN, 14);
        page.addText("思源黑体 Normal 字体", 20, 20, 3.70f, fontId, font, null, null, null, null, null, null, null);

        CTColor blue = new CTColor();
        blue.setValue("0 0 255");

        // 沿椭圆排布
        int defaultFontId = ofDocument.getOfdCommonData().getDefaultFontid();
        Font defaultFont = new Font(FONT_NAME, Font.PLAIN, (int) Util.mmToPoints(Util.getFontSize()));
        page.addTextEllipseAndTrans("沿椭圆排布的文字 easyofd", 40, 60, 30, 20, 0, 360, 1,
                Util.getFontSize(), defaultFontId, defaultFont, blue, null, null, null);

        // 沿圆排布
        page.addTextCircleAndTrans("沿圆排布的文字 easyofd", 120, 140, 25, 0, 360, 1,
                Util.getFontSize(), defaultFontId, defaultFont, blue, null, null, null);

        DemoOutput.save(ofDocument, "text/text.ofd");
    }
}
