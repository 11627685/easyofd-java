package cn.easyofd.demo;

import cn.easyofd.document.OFDSingnatures;
import cn.easyofd.document.OFDVerifySignature;
import cn.easyofd.document.signs.x509.GMX509Builder;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Security;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertTrue;

/**
 * 六、数字签名 Signature：对 OFD 签名，并对签名结果验签。
 *
 * <p>证书与私钥在运行时即时生成，工程内不保存任何密钥文件。</p>
 */
public class SignDemoTest {

    @Test
    public void signAndVerify() throws Exception {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        // 1. 准备一份待签名的 OFD
        File source = DemoDocs.createSimpleOfd("sign/source.ofd", "待签名文档 easyofd");

        // 2. 生成示例证书与私钥（实际业务请使用 CA 颁发的证书）
        GMX509Builder builder = DemoKeys.newBuilder("easyofd 示例签名");
        X509Certificate certificate = builder.getCertificate();
        BCECPrivateKey privateKey = (BCECPrivateKey) builder.getPrivateKey();

        // 3. 签名
        OFDSingnatures signatures = new OFDSingnatures(source, certificate);
        signatures.siqn(privateKey);

        File signed = DemoOutput.file("sign/signed.ofd");
        try (OutputStream os = new FileOutputStream(signed)) {
            signatures.saveOFD(os);
        }
        System.out.println("已生成签名文件：" + signed.getAbsolutePath());

        // 4. 验签
        OFDVerifySignature verify = new OFDVerifySignature(signed);
        verify.setSignsCheck(contentInfo -> {
            System.out.println("验签回调，签名算法 OID：" + contentInfo.getContentType());
            return true;
        });
        assertTrue("签名校验失败", verify.verifySignature());
    }
}
