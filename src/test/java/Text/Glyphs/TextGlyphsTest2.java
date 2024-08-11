package Text.Glyphs;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.document.util.Util;
import cn.easyofd.xsd.CTColor;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

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


/**
 * 如果你只需要字体中的部分中文字符，而不需要其他字符，减少 TTF 文件大小的方法有以下几种：
 * 使用字体编辑工具：使用字体编辑工具（如 FontForge、Glyphs 等）打开 TTF 文件，然后删除不需要的字符。只保留你需要的字符集，然后保存新的字体文件。这会显著减少文件大小。
 */


public class TextGlyphsTest2 {

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

        page.addLine(0,40,300,40);

        page.addLine(0,30,300,30);
        page.addLine(0,50,300,50);
        page.addLine(0,60,300,60);

        page.addLine(20,0,20,65);


        /*设置默认字体*/
        double fontSize=5;

        int fontKaitiID=ofDocument.getOfdCommonData().registerFont("楷体","font/楷体.ttf");
        Font font2=new Font("楷体",Font.PLAIN,(int) Util.mmToPoints((float)fontSize));
        page.addText("楷体:中文展示情况",110,50,fontSize,fontKaitiID,font2,null,null,null,null,null,null,null);


          sucessed=true;

        assertEquals(true,sucessed  ) ;





    }






}
