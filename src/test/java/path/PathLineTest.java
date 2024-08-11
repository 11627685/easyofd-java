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


public class PathLineTest {

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
        //默认线
        //测试 10 线
        page.addLine(5,10,140,10);
        //线宽 颜色
        CTColor color=new CTColor();
        color.setValue("255 0 0");
        page.addLine(10,10,100,10,2.0,color);

        //测试 20 线
        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");
        page.addLine(10,20,100,20,2.0,color2,null,"10 10",null);

        //测试 30 线
        page.addLine(10,30,100,30,2.0,null,5.0,"10 10",null);

        //测试 40 线
        page.addLine(50,40,70,40,2.0,null,null,null,"Round");

        page.addLine(50,50,70,50,2.0,null,null,null,"Square");

        page.addLine(50,60,70,60,2.0,null,null,null,null);

        //线条连接样式 Linejoin
        // 因为是独立的两个线段，所有不会有效果，解决路径 一 通过矩形 二 自己设置绘制的path内容，都在一个path内设置有效
        CTBoundary boundary=new CTBoundary(0,0,100,100);
        //测试 70 线
        String  abbreviatedData= "M 65 75 L 70 70 L 75 75" ;
        CTPageBlock.PathObject pathObject= page.addPathObject(abbreviatedData,boundary);
        pathObject.setJoin("Round");
        pathObject.setLineWidth(2.0);

        abbreviatedData= "M 75 85 L 80 80 L 85 85" ;
        CTPageBlock.PathObject pathObject2= page.addPathObject(abbreviatedData,boundary);
        pathObject2.setJoin("Bevel");
        pathObject2.setLineWidth(2.0);



        try {

            OutputStream os = null;
            os = new FileOutputStream("OfdFiles/path/lines.ofd");
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
