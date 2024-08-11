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


public class OnePageTextFollow2Test {

    @Test
    public void Test() throws IOException, FontFormatException {
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


        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");


        //问题三 圆形 椭圆 跟随文字
        float fontSize= Util.FontSize ;
        Font font=new Font("宋体",Font.PLAIN,(int) Util.mmToPoints((float)fontSize));
        int fontID = ofDocument.getOfdCommonData().getDefaultFontid();


        page.addTextCircleAndTrans("某某某某某某某某某某某某某某某某某某某某某某某某某某某某某某",70,50,24,90,440,1,fontSize,fontID,font,color,null,null,null);
       // page.addCircle(70,50,24,color,null,1.0,null,null);

        //page.addTextCircle("上海某某软件股份有限公司",70,50,24,190,350,1);



        page.addCircle(140,50,24,color,null,1.0,null,null);

        page.addTextCircleAndTrans("某某某某某某某某某某某某某某某某某某某某某某某某某某某某某某",140,50,24,90,440,1);




        page.addCircle(70,120,24,color,null,1.0,null,null);

        page.addTextCircleAndTrans("0123456789",70,120,24,45,135,1);



        page.addCircle(140,120,24,color,null,1.0,null,null);

        page.addTextCircleAndTrans("0123456789",140,120,24,45,135,2);



        CTPageBlock.PathObject pathObject=page.addPentagram(140,120,10,null,color,null);
        pathObject.setStroke(false);
      //  page.addElliptic(70,120,16,12,color,null,1.0,null,null);





        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextFollowCircle.ofd");

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
