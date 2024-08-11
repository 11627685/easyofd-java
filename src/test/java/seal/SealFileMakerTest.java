package seal;


import cn.easyofd.asn1.seal.SESeal;
import cn.easyofd.document.seal.SealBuilder;
import cn.easyofd.document.signs.x509.GMX509Builder;
import cn.easyofd.document.util.Util;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.junit.Test;

import java.io.*;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;


/* Copyright 2024  ZhangXinPan
 *  11627685@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class SealFileMakerTest {

    @Test
    public void MakerTest() throws IOException, CryptoException, CertificateEncodingException {
        boolean sucessed=false;

        //第一步 创建电子印章证书
        GMX509Builder gmx509Builder= null;
        try {
            gmx509Builder = getCertificate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        X509Certificate certificate= gmx509Builder.getCertificate();
        System.out.println(certificate.toString());

        BCECPrivateKey bcecPrivateKey=(BCECPrivateKey) gmx509Builder.getPrivateKey() ;

        //第二步   电子印章的图片
        File file = new File("image/123.png");
        byte[] picdata= Util.getFileBytes(file);

        DEROctetString    data=  new   DEROctetString (picdata);
        DEROctetString  cert=   new  DEROctetString (certificate.getEncoded());

        SESeal seSeal= SealBuilder.getInstance("00001",5,"easyOFD电子印章",bcecPrivateKey,
                "png",data,40,30,null,cert
        );
        String filePath1 = "OfdFiles/seal/seal-out.esl";

        SaveSealFile(filePath1,seSeal);

        String filePath2 = "OfdFiles/seal/seal-key.esl";
        SaveSealKeyFile(filePath2,bcecPrivateKey);

        sucessed=true;


        assertEquals(true,sucessed  ) ;

    }

    private void SaveSealKeyFile(String filePath2, BCECPrivateKey bcecPrivateKey) {

        try (FileOutputStream fos = new FileOutputStream(filePath2)) {
            // 将私钥转换为PKCS#8格式的DER编码
            byte[] encodedPrivateKey = bcecPrivateKey.getEncoded();

            // 将DER编码的字节序列写入文件
            fos.write(encodedPrivateKey);
            System.out.println("私钥已保存到文件。");
        } catch (IOException   e) {
            e.printStackTrace();
        }


    }

    private void SaveSealFile(String filePath1, SESeal seSeal) throws FileNotFoundException {

        OutputStream fis = new FileOutputStream(filePath1);
        try {
            fis.write(seSeal.getEncoded());
            if(fis!=null)
            {
                fis.flush();
                fis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
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




}
