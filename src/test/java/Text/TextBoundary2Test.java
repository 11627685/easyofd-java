package Text;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;
import sun.font.FontDesignMetrics;

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


public class TextBoundary2Test {

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
        color2.setValue("0 0 255");

        CTColor color3=new CTColor();
        color3.setValue("0 255 0");



        String text = "默认字体：宋体";
        /*设置默认字体*/
        page.addText(text,20.0,20.0,15);
        Font font=new Font("宋体",Font.PLAIN,(int) Util.mmToPoints((float)15));
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        // 使用FontMetrics测量字体高度和字符串长度
        float  ascent=  metrics.getMaxAscent();
        float  descent=  metrics.getMaxDescent();

        page.addLine(15,20- Util.pointsToMM(ascent) ,300,20- Util.pointsToMM(ascent),color);
        page.addLine(15,20+ Util.pointsToMM(descent) ,300,20+ Util.pointsToMM(descent),color3);
        page.addLine(15,20 ,300,20);

        float midline=(ascent+descent)/2-descent;
        page.addLine(15,20- Util.pointsToMM(midline),300,20- Util.pointsToMM(midline),color2);

        page.addText("baseline",150.0,20);
        page.addText("ascentline",150.0,8);
        page.addText("descentline",170.0,21);
        page.addText("midline",170.0,20- Util.pointsToMM(midline));


        System.out.println("top:" +(20- Util.pointsToMM(ascent)));
        System.out.println("mid:" +(20- Util.pointsToMM(midline)) );
        System.out.println("bott:" +(20+ Util.pointsToMM(descent)));



        String text2 = "宋";


        /*设置默认字体*/
        page.addText(text2,20.0,40.0,15);
        float top=(40- Util.pointsToMM(ascent));
        float width= Util.pointsToMM(metrics.stringWidth(text2));
        float height= Util.pointsToMM(metrics.getHeight());

        page.addLine(15,40- Util.pointsToMM(ascent) ,300,40- Util.pointsToMM(ascent),color);
        page.addLine(15,40+ Util.pointsToMM(descent) ,300,40+ Util.pointsToMM(descent),color3);
        page.addLine(15,40 ,300,40);

        page.addLine(20+width/2,top ,20+width/2,top+height,color);
        page.addLine(20+width,top ,20+width,top+height,color2);



        //midline=(ascent+descent)/2-descent;
        midline=top+height/2;
        page.addLine(15,midline,300, midline,color2);


        page.addLine(20,top ,20,top+height);

        try {

            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextBoundary2.ofd");

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
