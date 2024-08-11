package java.Text;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.document.util.Util;
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


public class OnePageTextFollowTest {

    @Test
    public void Test() throws IOException, FontFormatException {
        boolean sucessed=false;

        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);

        ofDocument.addPage(page);



        //x轴的坐标//210

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


        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");

        //发票专用章的形状为椭圆形，长轴为40mm、短轴为30mm、边宽1mm，印色为红色。
        //问题三 圆形 椭圆 跟随文字 　发票专用章所刊汉字，应当使用简化字，字体为仿宋体：“发票专用章”字样字高4.6mm、字宽3mm；纳税人名称字高4.2mm、字宽根据名称字数确定；纳税人识别号数字为Arial体，数字字高为3.7mm，字宽1.3mm。

        //page.addTextEllipseAndTrans("某某某某某某某某某某某某某某某",40,30,20,15,90,450,1);
        page.addEllipse(40,30,20,15,color,null,1.0,null,null);

        //长轴
        page.addLine(20,30,60,30);

        //短轴
        page.addLine(40,15,40,45);

        float fontSize= Util.FontSize ;
        Font font=new Font("宋体",Font.PLAIN,(int) Util.mmToPoints((float)fontSize));
        int fontID = ofDocument.getOfdCommonData().getDefaultFontid();

        //page.addElliptic(40,30,10,8,color,null,1.0,null,null);
        page.addTextEllipseAndTrans("某某某某某某某某某某某某某某某",40,30,20,15,90,450,1,fontSize,fontID,font,color2,null,null,null);

//
//        page.addElliptic(120, 30,16,12,color,null,1.0,null,null);
//
//
//        page.addTextEllipticAndTrans("全国统一发票监制章",120, 30,16,12,190,350,1);
//



        CTPageBlock.PathObject pathObjec=page.addPentagram(120,30,8,null,color,null);
        pathObjec.setStroke(false);





        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextFollowEllipse.ofd");

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