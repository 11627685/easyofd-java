package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;

import java.io.IOException;

/**
 * 二、图形 Path：线、矩形、圆、椭圆、五角星。
 */
public class PathDemoTest {

    @Test
    public void drawPaths() throws IOException {
        OFDocument ofDocument = new OFDocument();
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        CTColor red = new CTColor();
        red.setValue("255 0 0");
        CTColor magenta = new CTColor();
        magenta.setValue("255 0 255");

        // 线：默认线宽、指定线宽与颜色、虚线
        page.addLine(1, 1, 140, 1);
        page.addLine(1, 6, 150, 6, 2.0, red);
        page.addLine(1, 10, 200, 10, 2.0, magenta, null, "5 5", null);

        // 矩形：边框、填充、圆角、虚线
        page.addSquare(5, 15, 150, 10);
        page.addSquare(5, 30, 150, 10, null, magenta);
        page.addSquare(5, 45, 150, 10, red, null);
        page.addSquare(5, 60, 150, 10, red, null, 2.5, 0.0, "3 3");

        // 圆
        page.addCircle(20, 80, 5);
        page.addCircle(40, 80, 5, red, null);
        page.addCircle(60, 80, 5, null, red);
        page.addCircle(80, 80, 5, null, null, 2.0, null, "1 1");

        // 椭圆
        page.addEllipse(20, 95, 4, 3, null, null, null, null, null);
        page.addEllipse(50, 95, 8, 6, magenta, null, null, null, null);
        page.addEllipse(170, 95, 16, 12, null, magenta, null, null, null);

        // 五角星
        page.addPentagram(20, 120, 8, red);

        DemoOutput.save(ofDocument, "path/path.ofd");
    }
}
