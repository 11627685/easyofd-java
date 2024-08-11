package Text;

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


public class TextRotate2Test {

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

        CTColor color2=new CTColor();
        color2.setValue("255 255 0");

        CTColor color3=new CTColor();
        color3.setValue("0 255 0");


//        //虚线
//        CTColor color2=new CTColor();
//        color2.setValue("255 0 255");

        page.addLine(0,20,300,20);
        page.addText("Y 20MM ",1,23,3);

        page.addLine(0,40,300,40);
        page.addText("Y 40MM ",1,43,3);


        page.addLine(0,60,300,60);
        page.addText("Y 60MM ",1,63,3);


        page.addLine(20,0,20,300);

        page.addText("X 0度 ",20,3,3);

        page.addText("X  45度",40,3,3);
        page.addLine(40,0,40,300);


        page.addText("X  90度",60,3,3);
        page.addLine(60,0,60,300);


        page.addText("X  135度",80,3,3);
        page.addLine(80,0,80,300);

        page.addText("X   180度",100,3,3);
        page.addLine(100,0,100,300);


        page.addText("X   225度",120,3,3);
        page.addLine(120,0,120,300);

        page.addText("X   270度",140,3,3);
        page.addLine(140,0,140,300);

        page.addText("X   315度",160,3,3);
        page.addLine(160,0,160,300);

        page.addText("X   360度",180,3,3);
        page.addLine(180,0,180,300);

        page.addLine(200,0,200,300);

        page.addLine(0,60-5.5562/2,300,60-5.5562/2,color);

        page.addText("上",20,20,5);
        CTPageBlock.TextObject textObject=page.addText("上",40,20,5);
        CTCTM ctctm=new CTCTM();
        ctctm.rotate(45* Math.PI / 180);// 90度
        textObject.setCTM(ctctm.toString());


        CTPageBlock.TextObject textObjec2=page.addText("上",60,20,5);
        CTCTM ctctm2=new CTCTM();
        ctctm2.rotate(90* Math.PI / 180);// 90度
        textObjec2.setCTM(ctctm2.toString());


        CTPageBlock.TextObject textObjec3=page.addText("上",80,20,5);
        CTCTM ctctm3=new CTCTM();
        ctctm3.rotate(135* Math.PI / 180);// 90度
        textObjec3.setCTM(ctctm3.toString());


        CTPageBlock.TextObject textObjec4=page.addText("上",100,20,5);
        CTCTM ctctm4=new CTCTM();
        ctctm4.rotate(180* Math.PI / 180);// 90度
        textObjec4.setCTM(ctctm4.toString());

        CTPageBlock.TextObject textObjec5=page.addText("上",120,20,5);
        CTCTM ctctm5=new CTCTM();
        ctctm5.rotate(225* Math.PI / 180);// 90度
        textObjec5.setCTM(ctctm5.toString());

        CTPageBlock.TextObject textObjec6=page.addText("上",140,20,5);
        CTCTM ctctm6=new CTCTM();
        ctctm6.rotate(270* Math.PI / 180);// 90度
        textObjec6.setCTM(ctctm6.toString());

        CTPageBlock.TextObject textObjec7=page.addText("上",160,20,5);
        CTCTM ctctm7=new CTCTM();
        ctctm7.rotate(315* Math.PI / 180);// 90度
        textObjec7.setCTM(ctctm7.toString());

        CTPageBlock.TextObject textObjec8=page.addText("上",180,20,5);
        CTCTM ctctm8=new CTCTM();
        ctctm8.rotate(360* Math.PI / 180);// 90度
        textObjec8.setCTM(ctctm8.toString());

        //字体高度 5.5562
        page.addLine(0,40-5.5562/2,300,40-5.5562/2,color);
        page.addLine(0,40-5.5562,300,40-5.5562/2,color);
        //page.addLine(0,40-5.5562/2,300,40-5.5562/2,color);
        page.addText("上",20,40,5);
//        page.addTextAngle("上",40,40,5,45);
//        page.addTextAngle("上",60,40,5,90);
//        page.addTextAngle("上",80,40,5,135);
//        page.addTextAngle("上",100,40,5,180);
//        page.addTextAngle("上",120,40,5,255);
//        page.addTextAngle("上",140,40,5,270);
//        page.addTextAngle("上",160,40,5,315);
//        page.addTextAngle("上",180,40,5,360);


        //字体高度 5.5562
        page.addLine(0,60-5.5562/2,300,60-5.5562/2,color);

        page.addText("4",20,60,5);
//        page.addTextAngle("4",40,60,5,45);
//        page.addTextAngle("4",60,60,5,90);
//        page.addTextAngle("4",80,60,5,135);
//        page.addTextAngle("4",100,60,5,180);
//        page.addTextAngle("4",120,60,5,255);
//        page.addTextAngle("4",140,60,5,270);
//        page.addTextAngle("4",160,60,5,315);
//        page.addTextAngle("4",180,60,5,360);


        try {

            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/text/TextRot2.ofd");

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
