package java.Text;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTCTM;
import cn.easyofd.xsd.CTColor;
import cn.easyofd.xsd.CTPageBlock;
import org.junit.Test;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
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


public class TextRotateTest {

    @Test
    public void Test() throws IOException, FontFormatException {
        boolean sucessed=false;

        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);

        ofDocument.addPage(page);

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

        // 颜色
        CTColor color=new CTColor();
        color.setValue("255 0 0");
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");

        CTColor color3=new CTColor();
        color3.setValue("0 255 0");

        page.addLine(0,50,200,50);

        //文字旋转
        page.addText("字体旋转",20,50,4);
        page.addLine(20,0,20,150);

        CTCTM ctctm=new CTCTM();
        ctctm.rotate(60* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject=page.addText("字体旋转-60",50,50,4);
        textObject.setCTM(ctctm.toString());

        page.addLine(50,0,50,150);


        CTCTM ctctm2=new CTCTM();
        ctctm2.rotate(-60* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject2=page.addText("字体旋转-负60",80,50,4);
        textObject2.setCTM(ctctm2.toString());

        page.addLine(80,0,80,100);


        CTCTM ctctm3=new CTCTM();
        ctctm3.rotate(240* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject3=page.addText("字体旋转-120",120,50,4);
        textObject3.setCTM(ctctm3.toString());

        page.addLine(120,0,120,100);


        //平移字体
        CTPageBlock.TextObject textObject5=page.addText("字体平移 X10 Y15",120,50,5);
        CTCTM ctctm5=new CTCTM();
        ctctm5.move(10,15);
        textObject5.setCTM(ctctm5.toString());

        page.addLine(130,0,130,65,color3);

        page.addLine(0,65,130,65,color3);


        page.addText("正常",20,120,5);

        //放大字体
        CTPageBlock.TextObject textObject4=page.addText("放大 X轴2 Y轴4",50,120,5);
        CTCTM ctctm4=new CTCTM();
        ctctm4.scale(2,4);
        textObject4.setCTM(ctctm4.toString());

        page.addLine(0,120,130,120);

        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextRotate.ofd");

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
