package cn.easyofd.demo;

import cn.easyofd.document.OFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 示例输出目录管理：所有示例生成的 OFD 都放在工程的 out/ 目录下（已被 .gitignore 忽略）。
 */
public final class DemoOutput {

    public static final File OUT_DIR = new File("out");

    private DemoOutput() {
    }

    /**
     * 取得 out/ 下的输出文件，父目录不存在时自动创建。
     */
    public static File file(String relativePath) throws IOException {
        File file = new File(OUT_DIR, relativePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("无法创建目录：" + parent.getAbsolutePath());
        }
        return file;
    }

    /**
     * 把文档保存到 out/ 下的指定位置。
     */
    public static File save(OFDocument document, String relativePath) throws IOException {
        File file = file(relativePath);
        try (OutputStream os = new FileOutputStream(file)) {
            document.save(os);
        }
        System.out.println("已生成：" + file.getAbsolutePath());
        return file;
    }
}
