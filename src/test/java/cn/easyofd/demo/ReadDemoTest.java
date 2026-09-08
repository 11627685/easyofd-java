package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.OFDReadFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 读取 OFD：解包已有文件，查看内部条目与页面数量。
 */
public class ReadDemoTest {

    @Test
    public void readOfd() throws Exception {
        File file = DemoDocs.createSimpleOfd("read/read.ofd", "待读取文档 easyofd");
        assertTrue(file.exists());

        OFDReadFile readFile = new OFDReadFile(file);

        // 解包为 OFD 文档对象
        OFDocument document = readFile.read();
        assertEquals(1, document.getOfdpages().size());
        System.out.println("页面数量：" + document.getOfdpages().size());

        // 查看 OFD 内部文件条目
        String[] paths = readFile.getFilePath();
        if (paths != null) {
            System.out.println("OFD 内部文件列表：");
            for (String path : paths) {
                System.out.println("  " + path);
            }
        }
    }
}
