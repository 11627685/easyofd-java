package path;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
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


public class coordinateTest {

    @Test
    public void Test()
    {
        boolean sucessed=false;
        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        //x轴的坐标//210

        page.addLine(0,100,210,100);

        for(int n=0;n<=210/10;n++)
        {
            page.addLine(n*10,100,n*10,102);
            page.addText((n*10)+"",n*10,110);
        }
        //y轴的坐标 297
        page.addLine(50,0,50,300);

        for(int n=0;n<=300/10;n++)
        {
            page.addLine(50,n*10,52,n*10);
            page.addText((n*10)+"",55, n*10+1);
        }

        try {

            OutputStream os = null;
            os = new FileOutputStream("OfdFiles/path/coordinate.ofd");
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
