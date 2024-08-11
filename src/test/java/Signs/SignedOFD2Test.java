package Signs;

import cn.easyofd.document.OFDSingnatures;
import cn.easyofd.document.signs.x509.GMX509Builder;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;


public class SignedOFD2Test {

    /**
     *  签名文件SM3的签名值验证
     */
    @Test
    public void singedOFdFile() throws  Exception {

        try {
            String filePath2 = "OfdFiles/image/image-beSigns-2.ofd"; // ASN.1文件路径
            String filePath1 = "OfdFiles/signs/image-signs.ofd"; // ASN.1文件路径

            GMX509Builder gmx509Builder=getCertificate();
            X509Certificate certificate= gmx509Builder.getCertificate();
            System.out.println(certificate.toString());


            OFDSingnatures fileSingnatures=new OFDSingnatures(new File(filePath2),certificate);


            BCECPrivateKey bcecPrivateKey=(BCECPrivateKey) gmx509Builder.getPrivateKey() ;


            fileSingnatures.siqn(bcecPrivateKey);


            try {

                OutputStream os = null;
                os = new FileOutputStream(filePath1);
                fileSingnatures.saveOFD(os);
                if(os!=null)
                {
                    os.flush();
                    os.close();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("aaaaaa");

        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public GMX509Builder getCertificate() throws Exception
    {
        ExtensionsGenerator extGenerator = new ExtensionsGenerator();

        // 添加Basic Constraints扩展，cA=false，不设置pathLenConstraint

        extGenerator.addExtension(X509Extension.basicConstraints, false, new BasicConstraints(false));

//        digitalSignature：用于数字签名。
//        nonRepudiation：用于不可否认性。
//        keyEncipherment：用于密钥加密。
//        dataEncipherment：用于数据加密。
//        keyAgreement：用于密钥协商。
//        keyCertSign：用于证书签名。
//        cRLSign：用于证书撤销列表(CRL)的签名。
//        encipherOnly：用于仅加密。
//        decipherOnly：用于仅解密
        KeyUsage keyUsage = new KeyUsage(KeyUsage.digitalSignature | KeyUsage.nonRepudiation);
        extGenerator.addExtension(X509Extension.keyUsage, false, keyUsage);

//        // 添加属性
//        attrs.put("CN", "John Doe"); // 通用名
//        attrs.put("O", "Example Corp"); // 组织
//        attrs.put("OU", "IT Department"); // 组织单位
//        attrs.put("C", "US"); // 国家代码
//        attrs.put("ST", "California"); // 州或省份
//        attrs.put("L", "San Francisco"); // 地点
//        attrs.put("STREET", "123 Example St"); // 街道地址
//        attrs.put("DC", "example"); // 域名组件

       // X500Name issuer =    new X500Name("C=CN,CN=张新攀, O=easyofd.cn, OU=easyofd,  ST=上海, L=宝山, STREET=宝山, DC=easyofd");
        X500Name issuer =    new X500Name("C=CN, O= easyofd Root, CN=easyofdRoot");
        X500Name subject =  new X500Name("CN=easyofd电子签名, O=easyofd,   C=CN,  ST= 上海");
        Calendar start = Calendar.getInstance();
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.YEAR, 1);


        GMX509Builder gmx509Builder;
        try {
            gmx509Builder= GMX509Builder.getInstance(new BigInteger("2315416525683890325"),issuer,subject,start,expiry,extGenerator);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (CertIOException e) {
            throw new RuntimeException(e);
        } catch (OperatorCreationException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
        return  gmx509Builder;
    }




    private   byte[] getFileBytes(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[1024]; // 缓冲区大小，可以根据需要调整
        int bytesRead = 0;
        int offset = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        while ((bytesRead = inputStream.read(buffer, offset, buffer.length - offset)) != -1) {
            offset += bytesRead;
            if (offset == buffer.length) {
                outputStream.write(buffer, 0, offset);
                offset = 0;
            }
        }

        if (offset > 0) {
            outputStream.write(buffer, 0, offset);
        }

        return outputStream.toByteArray();

    }




}
