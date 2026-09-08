package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTBoundary;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * 三、图像 Image：把图片写入公共资源，再按区域、旋转角度绘制到页面上。
 */
public class ImageDemoTest {

    private static final String IMAGE_FILE = "image/123.png";

    @Test
    public void drawImage() throws IOException {
        File file = new File(IMAGE_FILE);
        assertTrue("缺少示例图片：" + file.getAbsolutePath(), file.exists());

        OFDocument ofDocument = new OFDocument();
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        // 参考网格，方便观察图片位置与旋转
        page.addLine(40, 0, 40, 200);
        page.addLine(120, 0, 120, 200);
        page.addLine(0, 40, 200, 40);
        page.addLine(0, 80, 200, 80);

        // 公共资源中增加图片，返回资源 ID
        int picId = ofDocument.getOfdCommonData()
                .addMultiMedia("Image", "PNG", file.getName(), Util.getFileBytes(file));

        // 原图
        page.addImageObject(picId, file.getName(), "PNG", new CTBoundary(40, 40, 40, 30));
        // 旋转 45 度
        page.addImageObject(picId, file.getName(), "PNG", new CTBoundary(120, 40, 40, 30), 45);

        DemoOutput.save(ofDocument, "image/image.ofd");
    }
}
