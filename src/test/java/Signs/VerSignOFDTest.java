package Signs;

import cn.easyofd.document.OFDVerifySignature;
import cn.easyofd.document.signs.ISignsCheck;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.security.Security;


public class VerSignOFDTest  implements ISignsCheck {

    /**
     *  签名文件SM3的签名值验证
     */
    @Test
    public void singedOFdFile() throws  Exception {

        try {

            String filePath = "OfdFiles/signs/image-signs.ofd"; // ASN.1文件路径
            Security.addProvider(new BouncyCastleProvider());
            OFDVerifySignature ofdVerifySignature =new OFDVerifySignature(new File(filePath));
            ofdVerifySignature.setSignsCheck(this::signsCheck);
            boolean verifySignature = ofdVerifySignature.verifySignature();

            System.out.println("aaaaaa:"+verifySignature);

        }catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }



    @Override
    public boolean signsCheck(ContentInfo contentInfo) {
        System.out.println("your check ....");
        return true;
    }
}
