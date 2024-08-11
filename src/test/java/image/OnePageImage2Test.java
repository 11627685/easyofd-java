package image;

import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;
import cn.easyofd.xsd.CTBoundary;
import cn.easyofd.xsd.CTPageBlock;
import org.junit.Test;

import java.io.*;

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


public class OnePageImage2Test {

    @Test
    public void Test()
    {
        boolean sucessed=false;
        // 创建一个OFD文档
        OFDocument ofDocument = new OFDocument();
        // 创建一页
        OFDPage page = new OFDPage(ofDocument);

        ofDocument.addPage(page);

        CTBoundary boundary= new CTBoundary(40,40,6.69,12.9);



        page.addLine(40,0,40,200);

        page.addLine(120,0,120,200);

        page.addLine(0,40,200,40);

        page.addLine(0,80,200,80);

        page.addLine(0,120,200,120);

        page.addLine(0,160,200,160);

        page.addText("0度",30,45,5);
        page.addText("45度",180,45,5);

        page.addText("90度",30,85,5);
        page.addText("135度",180,85,5);

        page.addText("180度",30,125,5);
        page.addText("225度",180,125,5);

        page.addText("270度",30,165,5);
        page.addText("315度",180,165,5);



        try {

            File file = new File("image/456.png");
            int  picID=ofDocument.getOfdCommonData().addMultiMedia( "Image","PNG"  ,file.getName(),this.getFileBytes(file));
            //正常
             page.addImageObject(picID,file.getName(),"PNG",boundary);


            CTBoundary boundary2= new CTBoundary(120,40,6.69,12.9);

            CTPageBlock.ImageObject imageObject= page.addImageObject(picID,file.getName(),"PNG",boundary2,45);


            CTBoundary boundary3= new CTBoundary(40,80,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary3,90);


            CTBoundary boundary4= new CTBoundary(120,80,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary4,135);


            CTBoundary boundary5= new CTBoundary(40,120,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary5,180);



            CTBoundary boundary6= new CTBoundary(120,120,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary6,225);

            CTBoundary boundary7= new CTBoundary(40,160,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary7,270);

            CTBoundary boundary8= new CTBoundary(120,160,6.69,12.9);
            page.addImageObject(picID,file.getName(),"PNG",boundary8,315);


            OutputStream os = null;

            os = new FileOutputStream("OfdFiles/image/image1.ofd");

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


    private   byte[] getFileBytes(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            return fileBytes;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
