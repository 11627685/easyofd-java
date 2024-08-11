package Text;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
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


public class TextBaseTest {

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

        page.addLine(0,20,300,20);
        page.addLine(0,30,300,30);

        page.addLine(20,0,20,65);

        /*设置默认字体*/
        page.addText("默认字体：宋体",20.0,20.0);
        page.addText("默认字体：宋体,5MM大小",20,30,5);

        /*阅读方向设定*/
        page.addText("阅读方向",20,40,5,90);

        page.addLine(0,40,300,40);

        page.addText("字体颜色",100,40,8,color,null);

        page.addLine(100,0,100,65);


        CTColor color3=new CTColor();
        color3.setValue("0 255 0");
        page.addText("字体勾边颜色",80,100,18,color3,color);

        page.addLine(80,0,80,120);
        page.addLine(0,100,190,100);

        //文字旋转

//        page.addText("字体旋转",50,100,5);
//        CTCTM ctctm=new CTCTM();
//        ctctm.rotate(60* Math.PI / 180);// 60度
//        CTPageBlock.TextObject textObject=page.addText("字体旋转",30,100,5);
//        textObject.setCTM(ctctm.toString());
//
//
//        CTCTM ctctm2=new CTCTM();
//        ctctm2.rotate(-60* Math.PI / 180);// 60度
//        CTPageBlock.TextObject textObject2=page.addText("字体旋转2",90,100,5);
//        textObject2.setCTM(ctctm2.toString());
//
//        CTCTM ctctm3=new CTCTM();
//        ctctm3.rotate(240* Math.PI / 180);// 60度
//        CTPageBlock.TextObject textObject3=page.addText("字体旋转3",120,100,5);
//        textObject3.setCTM(ctctm3.toString());
//
//        //平移字体
//        CTPageBlock.TextObject textObject5=page.addText("字体旋转-平移",50,100,5);
//
//        CTCTM ctctm5=new CTCTM();
//        ctctm5.move(2,5);
//        textObject5.setCTM(ctctm5.toString());

//
//        //放大字体
//        CTPageBlock.TextObject textObject4=page.addText("放大字体",50,120,5);
//        CTCTM ctctm4=new CTCTM();
//        ctctm4.scale(5,2);
//        textObject4.setCTM(ctctm4.toString());
//



        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextBase.ofd");

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
