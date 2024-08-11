package seal;


import cn.easyofd.asn1.seal.SES_Signature;
import cn.easyofd.document.OFDVerifySignature;
import cn.easyofd.document.seal.ISealCheck;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.spec.InvalidKeySpecException;

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


public class VerSealOFDTest implements ISealCheck {

    @Test
    public void VerImageSeal() throws IOException, CryptoException, CertificateEncodingException, NoSuchProviderException, InvalidKeySpecException {
        boolean sucessed=false;

        try {

            String filePath = "OfdFiles/seal/image-seal.ofd"; // ASN.1文件路径
            Security.addProvider(new BouncyCastleProvider());
            OFDVerifySignature ofdVerifySignature =new OFDVerifySignature(new File(filePath));
            ofdVerifySignature.setSealCheck(this::sealCheck);
            boolean verifySignature = ofdVerifySignature.verifySignature();

            System.out.println("aaaaaa:"+verifySignature);

        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

        sucessed=true;


        assertEquals(true,sucessed  ) ;

    }

    @Test
    public void VerFapiaoSeal() throws IOException, CryptoException, CertificateEncodingException, NoSuchProviderException, InvalidKeySpecException {
        boolean sucessed=false;

        try {

            String filePath = "OfdFiles/seal/fapiao.ofd"; // ASN.1文件路径
            Security.addProvider(new BouncyCastleProvider());
            OFDVerifySignature ofdVerifySignature =new OFDVerifySignature(new File(filePath));
            ofdVerifySignature.setSealCheck(this::sealCheck);
            boolean verifySignature = ofdVerifySignature.verifySignature();

            System.out.println("aaaaaa:"+verifySignature);

        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

        sucessed=true;


        assertEquals(true,sucessed  ) ;

    }


    @Override
    public boolean sealCheck(SES_Signature sesSignature) {

        System.out.println("your seal check");
        return true;
    }
}
