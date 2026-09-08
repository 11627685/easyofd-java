package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 生成示例用的基础 OFD 文档，供签名、签章、读取等示例复用。
 */
public final class DemoDocs {

    private DemoDocs() {
    }

    /**
     * 生成一个只有一页文字的 OFD 文件，返回生成的文件。
     */
    public static File createSimpleOfd(String relativePath, String title) throws IOException {
        OFDocument document = new OFDocument();
        OFDPage page = new OFDPage(document);
        document.addPage(page);

        page.addText(title, 20, 30, 5);
        page.addLine(0, 40, 210, 40);

        File file = DemoOutput.file(relativePath);
        try (OutputStream os = new FileOutputStream(file)) {
            document.save(os);
        }
        return file;
    }
}
