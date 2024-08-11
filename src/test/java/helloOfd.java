import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class helloOfd {


    @Test
    public void Test()
    {
        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);
        page.addText("Hello EasyOFD!!",50,100);

        try {

            OutputStream os = null;
            os = new FileOutputStream("hello.ofd");
            ofDocument.save(os);
            if(os!=null)
            {
                os.flush();
                os.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
