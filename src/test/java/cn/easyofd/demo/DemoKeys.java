package cn.easyofd.demo;

import cn.easyofd.document.signs.x509.GMX509Builder;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.Security;
import java.util.Calendar;

/**
 * 示例密钥：运行时即时生成 SM2 密钥对与自签证书，工程内不再保存任何私钥文件。
 *
 * <p>真实业务请改用合规 CA 颁发的证书，私钥应存放在 USBKey / 密码机 / KMS 中。</p>
 */
public final class DemoKeys {

    private DemoKeys() {
    }

    /**
     * 生成一张自签名的示例证书（含对应的 SM2 私钥）。
     *
     * @param commonName 证书使用者名称
     */
    public static GMX509Builder newBuilder(String commonName) throws Exception {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        ExtensionsGenerator extGenerator = new ExtensionsGenerator();
        extGenerator.addExtension(X509Extension.basicConstraints, false, new BasicConstraints(false));
        extGenerator.addExtension(X509Extension.keyUsage, false,
                new KeyUsage(KeyUsage.digitalSignature | KeyUsage.nonRepudiation));

        X500Name issuer = new X500Name("C=CN, O=easyofd Demo Root, CN=easyofdDemoRoot");
        X500Name subject = new X500Name("CN=" + commonName + ", O=easyofd, C=CN, ST=上海");

        Calendar start = Calendar.getInstance();
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.YEAR, 1);

        return GMX509Builder.getInstance(BigInteger.valueOf(System.currentTimeMillis()),
                issuer, subject, start, expiry, extGenerator);
    }
}
