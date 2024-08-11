package Signs;

import cn.easyofd.document.OFDSingnatures;
import cn.easyofd.document.signs.x509.GMX509Builder;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;


public class SignedOFDTest {

    /**
     *  签名文件SM3的签名值验证
     */
    @Test
    public void singedOFdFile() throws  Exception {

        try {

                String filePath2 = "OfdFiles/image/image-beSigns-1.ofd"; // ASN.1文件路径
                String filePath1 = "OfdFiles/signs/image-signs-2.ofd"; // ASN.1文件路径

                X509Certificate certificate=getCertificate();
                PrivateKey privateKey=  getPrivateKey();
                System.out.println(certificate.toString());


                OFDSingnatures fileSingnatures=new OFDSingnatures(new File(filePath2),certificate);


                BCECPrivateKey bcecPrivateKey=(BCECPrivateKey) privateKey ;


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

    public X509Certificate getCertificate() throws Exception
    {


        X509Certificate   x509Certificate=   GMX509Builder.getInstance("key/ofd.cert.pem");

        return  x509Certificate;
    }

   public  PrivateKey getPrivateKey() throws NoSuchProviderException, InvalidKeySpecException {
       String pemFilePath = "key/ofd.key.pem"; // 替换为PEM文件的实际路径
       PrivateKey privateKey=null;
       try (FileReader fileReader = new FileReader(pemFilePath)) {
           PEMParser pemParser = new PEMParser(fileReader);
           org.bouncycastle.openssl.PEMKeyPair pemPair = (org.bouncycastle.openssl.PEMKeyPair) pemParser.readObject();
             privateKey = new JcaPEMKeyConverter().getPrivateKey(pemPair.getPrivateKeyInfo());
           System.out.println("Private Key: " + privateKey);
       } catch (IOException e) {
           e.printStackTrace();
       }

       return  privateKey;

   }




}
