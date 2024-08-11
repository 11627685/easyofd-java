package path;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTColor;
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


public class OnePagePathTest {

    @Test
    public void Test()
    {
        boolean sucessed=false;
        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        //默认线
        page.addLine(1,1,140,1);

        //线宽 颜色
        CTColor color=new CTColor();
        color.setValue("255 0 0");
        page.addLine(1,6,150,6,2.0,color);


        //虚线
        CTColor color2=new CTColor();
        color2.setValue("255 0 255");
        page.addLine(1,10,200,10,2.0,color2,null,"5 5",null);

        //默认矩形
        page.addSquare(5,15,150,10);

        page.addSquare(5,30,150,10,null,color2);

        page.addSquare(5,45,150,10,color,null);

        page.addSquare(5,60,150,10,color,null,2.5,0.0,"3 3");

        //圆
        page.addCircle(20,80,5);

        page.addCircle(40,80,5,color,null);

        page.addCircle(60,80,5,null,color);

        page.addCircle(80,80,5,null,null,2.0,null,"1 1");


        //椭圆
        page.addEllipse(20,95,4,3,null,null,null,null,null);

        page.addEllipse(50,95,8,6,color2,null,null,null,null);
        page.addEllipse(50,95,7,5,color2,null,null,null,null);

        page.addEllipse(170,95,16,12,null,color2,null,null,null);

        try {

            OutputStream os = null;
            os = new FileOutputStream("OfdFiles/path/path.ofd");
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
