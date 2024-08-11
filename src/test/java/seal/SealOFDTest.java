package seal;


import cn.easyofd.asn1.seal.SESeal;
import cn.easyofd.document.OFDSealFile;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTBoundary;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

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


public class SealOFDTest {

    @Test
    public void MakerTest() throws IOException, CryptoException, CertificateEncodingException, NoSuchProviderException, InvalidKeySpecException {
        boolean sucessed=false;
        //第一步 获取电子印章证书
        String sealPath = "OfdFiles/seal/seal-out.esl";
        SESeal seSeal= Util.getSealFromFile(sealPath);
        //第二步 获取私钥文件
        String keyPath = "OfdFiles/seal/seal-key.esl";
        BCECPrivateKey bcecPrivateKey=(BCECPrivateKey) getPrivateKey(keyPath)   ;
        //第三分 对签名范围内文件进行电子签章
        String filePath1 = "OfdFiles/image/image-beSeal.ofd";
        String filePath2 = "OfdFiles/seal/image-seal.ofd";
        CTBoundary ctBoundary=new CTBoundary(10,10,40,30);
        OFDSealFile ofdSealFile=new OFDSealFile( new File(filePath1),seSeal);
        try {
            ofdSealFile.seal(bcecPrivateKey,ctBoundary);
            try {
                OutputStream os = null;
                os = new FileOutputStream(filePath2);
                ofdSealFile.saveOFD(os);
                if(os!=null)
                {
                    os.flush();
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //第四步   保存新文件


        sucessed=true;


        assertEquals(true,sucessed  ) ;

    }

    public   PrivateKey getPrivateKey(String pemFilePath) throws NoSuchProviderException, InvalidKeySpecException {

        PrivateKey privateKey=null;
        Security.addProvider(new BouncyCastleProvider());

        try (FileInputStream fis = new FileInputStream(pemFilePath)) {
            // 从文件中读取DER编码的私钥
            byte[] encodedPrivateKey = new byte[fis.available()];
            fis.read(encodedPrivateKey);

            // 使用PKCS#8编码的KeySpec来创建私钥
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC"); // 使用Bouncy Castle的KeyFactory
              privateKey = (BCECPrivateKey) keyFactory.generatePrivate(privateKeySpec);

           // System.out.println("私钥已从文件加载并构建为BCECPrivateKey对象。");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return  privateKey;

    }








}
