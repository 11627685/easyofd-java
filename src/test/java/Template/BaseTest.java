package Template;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.Tpls.OFDTemplate;
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


public class BaseTest {

    @Test
    public void Test() throws IOException, FontFormatException {
        boolean sucessed=false;

        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();

        ofDocument.getOfdCommonData().setPagePhysicaArea(100,100);
        // 创建一页
        OFDTemplate template = new OFDTemplate(ofDocument);
        CTColor gray=new CTColor();
        gray.setValue("135 206 250");

        template.addSquare(0,0,100,100,null,gray);
        template.addText("模版页内容",50,50);
        template.addEllipse(50,50,10,10,3.0);

        int templateid=ofDocument.getOfdCommonData().addTemplate(template);

        // 创建一页
        OFDPage page = new OFDPage(ofDocument);
        page.addTemplatePage(templateid,"Backqround");
        ofDocument.addPage(page);

        OFDPage page2 = new OFDPage(ofDocument);
        page2.addTemplatePage(templateid,"Backqround");
        ofDocument.addPage(page2);



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



        try {



            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/Tpls/TplsBase.ofd");

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
