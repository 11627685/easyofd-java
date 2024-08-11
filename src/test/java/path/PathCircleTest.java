package path;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
import cn.easyofd.xsd.CTPageBlock;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

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


public class PathCircleTest {

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

        CTColor color2=new CTColor();
        color2.setValue("255 0 255");

        page.addLine(30,0,30,200);
        page.addLine(60,0,60,200);

        page.addLine(45,20,30,20);//左
        page.addLine(45,20,60,20);//右
        page.addLine(45,20,45,5);//上
        page.addLine(45,20,45,35);//下

        page.addCircle(45,20,15);

        page.addCircle(45,60,15,color,null);

        page.addCircle(45,100,15,null,color);

        page.addCircle(45,140,15,null,null,2.0,null,"1 1");

        page.addCircle(140,50,20);
        CTPageBlock.PathObject pathObject=page.addPentagram(140,50,20,color);
        pathObject.setStroke(false);


        page.addCircle(140,140,50,null,color2,2.0,null,"1 1");


        try {

            OutputStream os = null;
            os = new FileOutputStream("OfdFiles/path/Circles.ofd");
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
