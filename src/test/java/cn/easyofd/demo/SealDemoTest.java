package cn.easyofd.demo;

import cn.easyofd.asn1.seal.SESeal;
import cn.easyofd.document.OFDSealFile;
import cn.easyofd.document.OFDVerifySignature;
import cn.easyofd.document.seal.SealBuilder;
import cn.easyofd.document.signs.x509.GMX509Builder;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTBoundary;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.Security;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertTrue;

/**
 * 七、电子签章 Seal：制作电子印章并对 OFD 签章，然后对签章结果验章。
 *
 * <p>印章证书与私钥在运行时即时生成，工程内不保存任何密钥文件。</p>
 */
public class SealDemoTest {

    private static final String SEAL_PICTURE = "image/123.png";

    @Test
    public void sealAndVerify() throws Exception {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        File source = DemoDocs.createSimpleOfd("seal/source.ofd", "待签章文档 easyofd");

        // 1. 生成电子印章证书与私钥
        GMX509Builder builder = DemoKeys.newBuilder("easyofd 示例签章");
        X509Certificate certificate = builder.getCertificate();
        BCECPrivateKey privateKey = (BCECPrivateKey) builder.getPrivateKey();

        // 2. 制作电子印章：印章图片 + 印章证书
        byte[] picture = Util.getFileBytes(new File(SEAL_PICTURE));
        SESeal seal = SealBuilder.getInstance("00001", 5, "easyOFD电子印章", privateKey,
                "png", new DEROctetString(picture), 40, 30, null,
                new DEROctetString(certificate.getEncoded()));

        // 3. 签章：指定签章位置
        OFDSealFile sealFile = new OFDSealFile(source, seal);
        sealFile.seal(privateKey, new CTBoundary(10, 10, 40, 30));

        File sealed = DemoOutput.file("seal/sealed.ofd");
        try (OutputStream os = new FileOutputStream(sealed)) {
            sealFile.saveOFD(os);
        }
        System.out.println("已生成签章文件：" + sealed.getAbsolutePath());

        // 4. 验章
        OFDVerifySignature verify = new OFDVerifySignature(sealed);
        verify.setSealCheck(sesSignature -> {
            System.out.println("验章回调，签章数据：" + sesSignature);
            return true;
        });
        assertTrue("签章校验失败", verify.verifySignature());
    }
}
