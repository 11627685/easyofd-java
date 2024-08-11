package path;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTBoundary;
import cn.easyofd.xsd.CTColor;
import cn.easyofd.xsd.CTPageBlock;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class PathtriangleTest {

    @Test
    public void Test()
    {
        boolean sucessed=false;
        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        //x轴的坐标//210

        page.addLine(0,1,210,1);

        for(int n=0;n<=210/10;n++)
        {
            page.addLine(n*10,1,n*10,3);
            page.addText((n*10)+"",n*10,4);
        }
        //y轴的坐标 297
        page.addLine(1,0,1,300);

        for(int n=0;n<=300/10;n++)
        {
            page.addLine(1,n*10,3,n*10);
            page.addText((n*10)+"",5, n*10+1);
        }
        //线宽 颜色
        CTColor color=new CTColor();
        color.setValue("255 0 0");

        //测试 20 线
        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");

        CTBoundary boundary=new CTBoundary(0,0,100,100);
        //测试 70 线
        String  abbreviatedData= "M 10 80 L 50 10 L 90 80 L 10 80 C" ;
        CTPageBlock.PathObject pathObject= page.addPathObject(abbreviatedData,boundary);
        pathObject.setJoin("Round");
        pathObject.setLineWidth(2.0);
        pathObject.setStrokeColor(color);
        pathObject.setFillColor(color2);
        pathObject.setDashPattern("2 2");


        try {

            OutputStream os = null;
            os = new FileOutputStream("OfdFiles/path/triangle.ofd");
            ofDocument.save(os);
            if(os!=null)
            {
                os.flush();
                os.close();
            }

              sucessed=true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(true,sucessed  ) ;

    }



}
