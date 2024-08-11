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


public class OnePageTextTest {

    @Test
    public void Test() throws IOException, FontFormatException {
        boolean sucessed=false;



        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);

        ofDocument.addPage(page);


        //线宽 颜色
        CTColor color=new CTColor();
        color.setValue("255 0 0");


        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");

        page.addLine(0,5,300,5);

        page.addLine(0,10,300,10);

        page.addLine(0,20,300,20);

        page.addLine(0,30,300,30);

        /*设置默认字体*/
        page.addText("默认字体：宋体",20.0,20.0);
        page.addText("默认字体：宋体,5MM大小",20,30,5);

        /*阅读方向设定*/
        page.addText("阅读方向",20,40,5,90);


        page.addText("字体颜色",50,40,8,color,null);


        page.addText("字体勾边颜色",90,40,18,null,color);

        /*新的字体 不建议这么打包到文件内，文件太大，建议服务器删上部分字体，用户客户端下载必要的字体，并安装*/
        //问题一 字体
        int newfontID=ofDocument.getOfdCommonData().registerFont("思源黑体 Normal","font/思源黑体 Normal.ttc");
        Font font=new Font("思源黑体字体 Normal",Font.PLAIN,14);
        page.addText("思源黑体字体",20,70, 3.70f,newfontID,font,null,null,null,null,null,null,null);



        page.addLine(0,100,300,100);
        //问题二 /*文字旋转*/

        page.addText("字体旋转",50,100,5);

        CTCTM ctctm=new CTCTM();
        ctctm.rotate(60* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject=page.addText("字体旋转",30,100,5);
        textObject.setCTM(ctctm.toString());


        CTCTM ctctm2=new CTCTM();
        ctctm2.rotate(-60* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject2=page.addText("字体旋转2",90,100,5);
        textObject2.setCTM(ctctm2.toString());

        CTCTM ctctm3=new CTCTM();
        ctctm3.rotate(240* Math.PI / 180);// 60度
        CTPageBlock.TextObject textObject3=page.addText("字体旋转3",120,100,5);
        textObject3.setCTM(ctctm3.toString());

        //放大字体
        CTPageBlock.TextObject textObject4=page.addText("放大字体",50,120,5);
        CTCTM ctctm4=new CTCTM();
        ctctm4.scale(5,2);
        textObject4.setCTM(ctctm4.toString());


        //平移字体
        CTPageBlock.TextObject textObject5=page.addText("字体旋转-平移",50,100,5);

        CTCTM ctctm5=new CTCTM();
        ctctm5.move(2,5);
        textObject5.setCTM(ctctm5.toString());



        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/Text.ofd");

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
