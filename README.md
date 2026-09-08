# easyofd-java

OFD 板式文件（GB/T 33190-2016）JAVA 生成/读取/签章库。

`easyofd` 提供了一套面向对象的 API，屏蔽 OFD 内部 XML 结构细节，让你像操作画布一样生成 OFD 文档：画文字、画图形、贴图、套用模板页，并支持国密（SM2/SM3）电子签章、数字签名与验签。

- 项目主页：<https://easyofd.cn/>
- 标准文档 Wiki：<http://wiki.easyofd.cn>
# 一、 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/入门JAVA">入门</a> 
&emsp;&emsp;本章节介绍如何在项目中引用本easyofd的jar文件，完成项目初始化，建立测试文件文件产生项目OFD文件。  
&emsp;&emsp;详细内容见 **<a target="_blank" href="http://wiki.easyofd.cn/JAVA/入门JAVA">入门 start</a>**


# 二、<a target="_blank" href="http://wiki.easyofd.cn/JAVA/图形JAVA">图形 Path</a>
&emsp;&emsp;图形章节介绍OFD中基础的图形绘制相关操作，主要包括了线、矩形、圆和椭圆，也预留了接口支持特殊图形。也是对OFD标准的第九章节9.1 <a target="_blank" href="http://wiki.easyofd.cn/OFD标准/图形">图形对象</a> PathObject的功能实现部分。
&emsp;&emsp;详细内容见 **<a target="_blank" href="http://wiki.easyofd.cn/JAVA/图形JAVA">图形 Path</a>**

# 三、<a target="_blank" href="http://wiki.easyofd.cn/JAVA/图像JAVA">图像 Image</a>
&emsp;&emsp;图像章节介绍OFD中图像相关内容。也是对OFD标准的第十章节10.1 <a target="_blank" href="http://wiki.easyofd.cn/OFD标准/图像">图像对象</a> Image的功能实现部分。
&emsp;&emsp;详细内容见 **<a target="_blank" href="http://wiki.easyofd.cn/JAVA/图像JAVA">图像 </a>**
# 四、<a target="_blank" href="http://wiki.easyofd.cn/JAVA/文字JAVA">文字 Text</a> 
&emsp;&emsp;文字在OFD板式文件中站了很大比例，也是很重要的章节。

&emsp;&emsp;详细内容见 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/文字JAVA">文字 Text</a>
# 五、 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/模板JAVA">模板及页面设置 Template</a> 
&emsp;&emsp;在OFD板式文件中使用模板概念，可以将各页面通用的内容进行统一的维护利于页面内容的统一，这就可以很大程度上减少了文件的大小。

&emsp;&emsp;详细内容见 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/模板JAVA">模板及页面设置</a>

# 六、 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/数字签名JAVA">数字签名及验签 Singnature</a> 
&emsp;&emsp;数字签名是OFD板式文件对文件安全考虑的一个重要特性。JAVA版本的easyofd实现了该签名功能，同时页可以对外部已经完成数字签名文件进行验签。 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/数字签名JAVA">/JAVA/数字签名JAVA</a>
# 七、 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/电子签章JAVA">电子签章及验签 Seal</a> 
&emsp;&emsp;电子签章是OFD板式文件对文件安全考虑的另一个重要特性。目前很多电子发票和银行具有签章的功能。JAVA版本的easyofd实现了该签章功能，同时页可以对外部已经完成签章文件进行验签。 <a target="_blank" href="http://wiki.easyofd.cn/JAVA/电子签章JAVA">电子签章及验签 Seal</a>
 
---

## 目录

- [特性](#特性)
- [环境要求](#环境要求)
- [安装](#安装)
- [快速开始](#快速开始)
- [核心类与职责](#核心类与职责)
- [使用指南](#使用指南)
  - [页面与坐标](#页面与坐标)
  - [文字](#文字)
  - [图形](#图形)
  - [图片](#图片)
  - [模板页](#模板页)
  - [字体与颜色](#字体与颜色)
  - [读取已有 OFD](#读取已有-ofd)
  - [电子签章（电子印章）](#电子签章电子印章)
  - [数字签名与验签](#数字签名与验签)
- [工程结构](#工程结构)
- [测试](#测试)
- [构建](#构建)
- [许可](#许可)

---

## 特性

- **生成 OFD**：按 OFD 标准打包为 `OFD.xml` + `Doc_0/*` 的 ZIP（`.ofd`）文件，自动维护 `MaxUnitID`、资源引用 ID。
- **页面元素**：文字（含竖排、圆形/椭圆环绕文字、字型 Glyph 图元）、图形（直线、矩形、圆、椭圆、五角星、自定义 Path）、图像（含旋转）、复合对象、页面块、层（Layer）。
- **模板页**：`OFDTemplate` 定义一次，多页复用（`Background` / `Foreground`）。
- **资源管理**：字体、颜色空间、绘制参数、多媒体由 `OFDCommonData` / `OFDRes` 统一管理，字体文件与图片自动打包进 OFD。
- **附件**：`OFDAttachment` 支持把任意文件（如 `image/123.png`）作为附件写入 OFD，并能从 OFD 中读取、导出附件。
- **读取 OFD**：`OFDReadFile` 反序列化为 `OFDocument` 对象树（JAXB）。
- **国密签章**：支持 SM2/SM3 电子印章（`SESeal`，遵循 GM/T 0031 电子印章数据结构）、OFD 签章与验签。
- **数字签名**：基于 X.509 国密证书的 OFD 签名与验签。
- **完整 XSD 模型**：`cn.easyofd.xsd` 下为 OFD 标准的 JAXB Bean，可做任意底层定制。

## 环境要求

- JDK 1.8 及以上
- Maven 3.x
- 依赖：
  - `org.apache.commons:commons-compress:1.26.1`（ZIP 打包/解包）
  - `org.bouncycastle:bcprov-jdk15on:1.70`、`bcpkix-jdk15on:1.70`（国密算法与证书）
  - `junit:junit:4.13.1`（仅测试）

## 安装

Maven 坐标：

```xml
<dependency>
    <groupId>cn.easyofd</groupId>
    <artifactId>easyofd</artifactId>
    <version>2.0.0</version>
</dependency>
```

本地构建安装：

```bash
mvn clean install
```

## 快速开始

生成一个包含文字和直线的 A4 页面并保存为 `hello.ofd`：

```java
import cn.easyofd.document.OFDocument;
import cn.easyofd.document.page.OFDPage;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Hello {
    public static void main(String[] args) throws Exception {
        // 1. 创建文档
        OFDocument ofDocument = new OFDocument();

        // 2. 创建一页并加入文档
        OFDPage page = new OFDPage(ofDocument);
        ofDocument.addPage(page);

        // 3. 绘制内容（单位：毫米）
        page.addText("你好，OFD！", 20.0, 30.0);
        page.addLine(0, 40, 210, 40);

        // 4. 保存
        try (OutputStream os = new FileOutputStream("hello.ofd")) {
            ofDocument.save(os);
        }
    }
}
```

OFD 本质是一个 ZIP 包，`save()` 会写出：

```
OFD.xml                    根文件
Doc_0/Document.xml         文档主入口与页树
Doc_0/PublicRes.xml        公共资源（字型、颜色空间）
Doc_0/DocumentRes.xml      文档资源（绘制参数、多媒体）
Doc_0/Page_0/Content.xml   页面内容
Doc_0/Res/…                图片、字体等资源文件
Doc_0/Tpls/Tpl_x/Content.xml   模板页（若使用）
```

## 核心类与职责

| 类 | 说明 |
| --- | --- |
| `cn.easyofd.document.OFDocument` | 文档协调类。持有根 `OFD.xml`、`Document.xml`、页列表；`addPage()` 加页，`save(OutputStream)` 打包输出 |
| `cn.easyofd.document.page.OFDPage` | 页面类，继承自 `OFDTemplate`，额外提供 `addTemplatePage()` 引用模板页 |
| `cn.easyofd.document.Tpls.OFDTemplate` | 模板页/页面基类，提供文字、图形、图像、复合对象等所有绘图 API |
| `cn.easyofd.document.OFDCommonData` | 公共数据：`addFont()`、`addColorSpace()`、`addDrawParam()`、`addMultiMedia()`、`addTemplate()`、页面区域设置 |
| `cn.easyofd.document.res.OFDRes` | 资源描述文件（`PublicRes.xml` / `DocumentRes.xml`）的封装 |
| `cn.easyofd.document.attachment.OFDAttachment` | 附件管理：`addAttachment()` 增加附件，`getAttachmentBytes()` / `saveAttachment()` 读取导出附件 |
| `cn.easyofd.document.OFDReadFile` | 读取并解析已有 OFD 文件，返回 `OFDocument` |
| `cn.easyofd.document.OFDSealFile` | OFD 电子签章（盖章） |
| `cn.easyofd.document.OFDSingnatures` | OFD 数字签名 |
| `cn.easyofd.document.OFDVerifySignature` | 签章/签名验证 |
| `cn.easyofd.document.seal.SealBuilder` | 生成电子印章 `SESeal`（`.esl`） |
| `cn.easyofd.document.signs.x509.GMX509Builder` | 国密 SM2 X.509 证书构建/加载 |
| `cn.easyofd.document.util.Util` | 单位换算、文字度量、旋转矩阵、SM2/SM3、XML 序列化等工具 |
| `cn.easyofd.xsd.*` | OFD 标准对应的 JAXB Bean（由 `src/main/java/xsd/*.xsd` 生成） |
| `cn.easyofd.asn1.seal.*` | 电子印章 ASN.1 数据结构（`SESeal`、`SES_SealInfo` 等） |

## 使用指南

### 页面与坐标

- 坐标系原点在页面左上角，单位为**毫米（mm）**。
- 默认页面为 A4：`0 0 210 297`，默认字体宋体，默认字号 `3.70mm`（五号）。
- 可通过 `OFDocument#getOfdCommonData()` 修改页面区域：

```java
ofDocument.getOfdCommonData().setPagePhysicaArea(210, 297);      // 物理区域
ofDocument.getOfdCommonData().setPageApplicationArea(210, 297);  // 显示区域
ofDocument.getOfdCommonData().setPageContentArea(180, 260);      // 版心区域
ofDocument.getOfdCommonData().setBleedArea(210, 297);            // 出血区域
```

亦可在页面级设置：`page.setPhysicalBox(new CTBoundary(0, 0, 210, 297))` 等。

多页文档只需重复 `addPage()`：

```java
OFDPage page1 = new OFDPage(ofDocument);
OFDPage page2 = new OFDPage(ofDocument);
ofDocument.addPage(page1);
ofDocument.addPage(page2);
```

### 文字

```java
CTColor red = new CTColor();
red.setValue("255 0 0");
CTColor green = new CTColor();
green.setValue("0 255 0");

page.addText("默认宋体", 20.0, 20.0);            // 默认字号 3.7mm
page.addText("5mm 字号", 20, 30, 5);
page.addText("竖排阅读方向", 20, 40, 5, 90);      // readDirection: 0 / 90
page.addText("字体颜色", 100, 40, 8, red, null);  // fillColor, strokeColor
page.addText("字体勾边", 80, 100, 18, green, red);
```

环绕文字（常用于印章）：

```java
// 圆形环绕：圆心 (x,y)、半径 r、起止角度、方向 1-顺时针 2-逆时针
page.addTextCircleAndTrans("电子印章专用文字", 100, 100, 40, 0, 360, 1);

// 椭圆环绕：长轴 a、短轴 b
page.addTextEllipseAndTrans("椭圆环绕文字", 100, 100, 60, 40, 0, 360, 1);
```

字型图元（`Glyphs`，把文字转为字形索引序列，便于脱离系统字体渲染）：

```java
Font font = new Font("宋体", Font.PLAIN, 14);
page.addTextGlyph("文字图元", 20, 50, 5, ofDocument.getOfdCommonData().getDefaultFontid(), font);
```

> 说明：`addText` 的 `y` 坐标为文字 baseline，方法内部会依据 `FontMetrics` 自动计算 `Boundary`。

### 图形

```java
page.addLine(0, 20, 210, 20);                       // 直线
page.addLine(0, 30, 210, 30, 0.5, red);             // 直线 + 线宽 + 颜色
page.addSquare(20, 40, 100, 50);                    // 矩形（x, y, w, h）
page.addSquare(20, 100, 100, 50, red, green);       // 矩形 + 勾边 + 填充
page.addCircle(100, 100, 30);                       // 圆
page.addCircle(100, 100, 30, red, green, 0.5, null, null);
page.addEllipse(100, 150, 40, 20, 0.5);             // 椭圆（长轴 a，短轴 b）
page.addPentagram(100, 200, 20, red, green, 0.5);   // 正五角星
```

虚线与端点样式：

```java
page.addLine(0, 60, 210, 60, 0.353, red, 0.0, "3 2", "Round");
page.addSquare(20, 70, 100, 30, red, null, 0.5, 0.0, "5 3", "Round", "Miter", 3.528);
```

自定义 Path（`AbbreviatedData`）：

```java
CTBoundary boundary = new CTBoundary(10, 10, 100, 60);
page.addPathObject("M 0 0 L 100 0 L 100 60 L 0 60 C", boundary);
```

也可以直接构造底层对象并加入页面：

```java
page.addPathObject(pathObject);
page.addTextObject(textObject);
page.addImageObject(imageObject);
page.addCompositeObject(compositeObject);
page.addPageBlock(pageBlock);
```

### 图片

图片先注册进文档资源（返回资源 ID），再在页面上按 `Boundary` 绘制：

```java
File file = new File("image/123.png");
byte[] bytes = Files.readAllBytes(file.toPath());

// 1. 注册多媒体资源（自动打包进 Doc_0/Res/）
int picID = ofDocument.getOfdCommonData().addMultiMedia("Image", "PNG", file.getName(), bytes);

// 2. 在页面绘制
CTBoundary boundary = new CTBoundary(40, 40, 40, 30);   // x, y, w, h
page.addImageObject(picID, file.getName(), "PNG", boundary);

// 3. 带旋转角度的绘制（角度制）
page.addImageObject(picID, file.getName(), "PNG", new CTBoundary(120, 40, 40, 30), 45);
```

支持格式：BMP、JPEG、TIFF、PNG。

### 模板页

模板页适合页眉页脚、水印、背景底纹等多页复用场景：

```java
OFDTemplate template = new OFDTemplate(ofDocument);
CTColor gray = new CTColor();
gray.setValue("135 206 250");
template.addSquare(0, 0, 210, 297, null, gray);
template.addText("模板页内容", 50, 50);

// 注册模板，返回模板 ID
int templateId = ofDocument.getOfdCommonData().addTemplate(template);

// 页面引用模板（ZOrder: Background / Foreground）
OFDPage page = new OFDPage(ofDocument);
page.addTemplatePage(templateId, "Background");
ofDocument.addPage(page);
```

### 字体与颜色

```java
// 使用系统已集成字型（不嵌入字体文件）
int kaiId = ofDocument.getOfdCommonData().addFont("楷体");

// 注册并嵌入外部字体文件（字体名需与文件名一致）
int fontId = ofDocument.getOfdCommonData().registerFont("思源黑体", "font/思源黑体 Normal.ttc");

// 颜色空间与绘制参数
int csId = ofDocument.getOfdCommonData().addColorSpace("RGB");
int dpId = ofDocument.getOfdCommonData().addDrawParam(red, 0.5);
```

颜色空间类型可取值：`Gray`、`RGB`、`CMYK`。

### 附件

附件定义见 [OFD 标准 - 附件](http://wiki.easyofd.cn/OFD%E6%A0%87%E5%87%86/%E9%99%84%E4%BB%B6)。
附件列表文件为 `Doc_0/Attachments.xml`，附件内容文件默认存放于 `Doc_0/Attachs/` 下。

**写入附件：**

```java
// 文件方式：把 font/楷体.ttf 作为附件存入 OFD
CTAttachment attachment = ofDocument.addAttachment(new File("font/楷体.ttf"));

// 路径方式
ofDocument.addAttachment("font/楷体.ttf");

// 字节方式：可指定是否可见(Visible)与用途(Usage)
byte[] bytes = Files.readAllBytes(Paths.get("font/楷体.ttf"));
ofDocument.addAttachment("楷体.ttf", "ttf", bytes, true, "none");
```

`addAttachment()` 会自动补充 `ID`、`FileLoc`、`Format`、`Size`（KB）、`CreationDate`、`ModDate`，
并在 `Document.xml` 中设置附件列表入口 `<ofd:Attachments>Attachments.xml</ofd:Attachments>`。
同名附件会自动追加序号（`楷体_1.ttf`）。

**读取附件：**

```java
OFDReadFile ofdReadFile = new OFDReadFile(new File("XML/attachment/attachment.ofd"));
OFDocument ofDocument = ofdReadFile.read();

OFDAttachment ofdAttachment = ofDocument.getOfdAttachment();   // 没有附件时为 null

// 附件描述列表
List<CTAttachment> attachments = ofdAttachment.getAttachment();
for (CTAttachment one : attachments) {
    System.out.println(one.getName() + " " + one.getFormat() + " " + one.getSize() + "KB");
}

// 按名称 / 按 FileLoc 获取附件内容
byte[] data = ofdAttachment.getAttachmentBytesByName("楷体.ttf");
byte[] data2 = ofdAttachment.getAttachmentBytes("Attachs/楷体.ttf");

// 附件另存为文件
ofdAttachment.saveAttachmentByName("楷体.ttf", new File("XML/attachment/楷体-out.ttf"));
ofdAttachment.saveAttachmentToDir("楷体.ttf", "XML/attachment");
```

生成的 `Doc_0/Attachments.xml`：

```xml
<?xml version="1.0" encoding="utf-8"?>
<ofd:Attachments xmlns:ofd="http://www.ofdspec.org/2016">
    <ofd:Attachment ID="6" Name="楷体.ttf" Format="ttf" CreationDate="2024-05-08T20:13:43+08:00" ModDate="..." Size="11511.0625">
        <ofd:FileLoc>Attachs/楷体.ttf</ofd:FileLoc>
    </ofd:Attachment>
</ofd:Attachments>
```

### 读取已有 OFD

```java
OFDReadFile ofdReadFile = new OFDReadFile(new File("XML/image/image-beRead.ofd"));
OFDocument ofDocument = ofdReadFile.read();

// 读取后即可访问公共数据、页树、资源、附件等
OFDCommonData commonData = ofDocument.getOfdCommonData();
List<OFDPage> pages = ofDocument.getOfdpages();
OFDAttachment attachments = ofDocument.getOfdAttachment();
```

`OFDReadFile` 还提供 `getFilesMap()`（包内所有条目）、`getFilePath()`（文件路径列表）、`getZipFile()` 等底层访问能力。

### 电子签章（电子印章）

**1) 制作电子印章（`.esl`）**

```java
byte[] picBytes = Files.readAllBytes(Paths.get("XML/seal/seal.png"));
ASN1OctetString picData = new DEROctetString(picBytes);
ASN1OctetString cert    = new DEROctetString(certificate.getEncoded());

SESeal seSeal = SealBuilder.getInstance(
        "印章标识ID",   // ID
        1,              // 类型：01法定名称章 02财务专用章 03发票专用章 04合同专用章 05名章
        "某某公司公章",
        (BCECPrivateKey) privateKey,   // 国密 SM2 私钥
        "png", picData,
        118, 118,                      // 图像显示宽高
        null,                          // 自定义扩展数据
        cert);                         // 证书
```

**2) 在 OFD 上盖章**

```java
SESeal seSeal = Util.getSealFromFile("XML/seal/seal-out.esl");
BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) getPrivateKey("XML/seal/seal-key.esl");

OFDSealFile ofdSealFile = new OFDSealFile(new File("XML/image/image-beSeal.ofd"), seSeal);
ofdSealFile.seal(bcecPrivateKey, new CTBoundary(10, 10, 40, 30));  // 印章位置与大小

try (OutputStream os = new FileOutputStream("XML/seal/image-seal.ofd")) {
    ofdSealFile.saveOFD(os);
}
```

`seal(...)` 另有重载可指定 `fileReference`（仅对指定文件计算摘要）。

**3) 验证签章**

```java
Security.addProvider(new BouncyCastleProvider());
OFDVerifySignature verify = new OFDVerifySignature(new File("XML/seal/image-seal.ofd"));
verify.setSealCheck(sesSignature -> {
    // 自定义印章校验逻辑
    return true;
});
boolean ok = verify.verifySignature();
```

### 数字签名与验签

**签名：**

```java
X509Certificate certificate = GMX509Builder.getInstance("key/ofd.cert.pem");
BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) loadPrivateKey("key/ofd.key.pem");

OFDSingnatures signatures = new OFDSingnatures(new File("XML/image/image-beSigns-1.ofd"), certificate);
signatures.siqn(bcecPrivateKey);   // 也可传入 String[] fileReference 指定签名范围

try (OutputStream os = new FileOutputStream("XML/signs/image-signs-2.ofd")) {
    signatures.saveOFD(os);
}
```

**验签：**

```java
Security.addProvider(new BouncyCastleProvider());
OFDVerifySignature verify = new OFDVerifySignature(new File("XML/signs/image-signs.ofd"));
verify.setSignsCheck(contentInfo -> {
    // 自定义签名值校验逻辑
    return true;
});
boolean ok = verify.verifySignature();
```

**生成/加载国密证书：**

```java
// 加载 PEM 证书
X509Certificate cert = GMX509Builder.getInstance("key/ofd.cert.pem");

// 生成自签名 SM2 证书
GMX509Builder builder = GMX509Builder.getInstance(
        BigInteger.ONE,
        new X500Name("CN=easyofd"),
        new X500Name("CN=test"),
        Calendar.getInstance(),
        expiry,
        extensionsGenerator);
X509Certificate newCert = builder.getCertificate();
```

## 工程结构

```
easyofd-java/
├─ pom.xml                          示例工程依赖（easyofd + junit + bouncycastle）
├─ LICENSE                          Apache-2.0
├─ README.md
├─ font/                            开源字体（思源黑体，SIL OFL），见 font/README.md
├─ image/                           示例图片
├─ out/                             示例输出目录（Git 忽略）
└─ src/test/java/cn/easyofd/demo/
   ├─ QuickStartTest.java           入门：创建文档、写文字、画线、画矩形
   ├─ PathDemoTest.java             图形：线、矩形、圆、椭圆、五角星
   ├─ ImageDemoTest.java            图像：图片与旋转
   ├─ TextDemoTest.java             文字：注册字体、沿圆/椭圆排布
   ├─ TemplateDemoTest.java         模板页
   ├─ AttachmentDemoTest.java       附件写入
   ├─ SignDemoTest.java             数字签名与验签
   ├─ SealDemoTest.java             制作电子印章、盖章与验章
   ├─ ReadDemoTest.java             读取已有 OFD
   ├─ DemoDocs.java                 生成示例用的基础文档
   ├─ DemoKeys.java                 运行时生成证书与私钥
   └─ DemoOutput.java               输出目录管理
```

> 本仓库只含示例工程，核心库以 Maven 依赖 `cn.easyofd:easyofd` 引入。

## 测试

测试用例即用即查的示例，运行全部测试：

```bash
mvn test
```

生成的 OFD 文件输出到 `out/` 目录：

| 场景 | 示例类 | 输出 |
| --- | --- | --- |
| 入门 | `QuickStartTest` | `out/quickstart.ofd` |
| 图形 | `PathDemoTest` | `out/path/path.ofd` |
| 图片与旋转 | `ImageDemoTest` | `out/image/image.ofd` |
| 文字 | `TextDemoTest` | `out/text/text.ofd` |
| 模板页 | `TemplateDemoTest` | `out/template/template.ofd` |
| 附件写入 | `AttachmentDemoTest` | `out/attachment/attachment.ofd` |
| 数字签名与验签 | `SignDemoTest` | `out/sign/signed.ofd` |
| 电子签章与验章 | `SealDemoTest` | `out/seal/sealed.ofd` |
| 读取 OFD | `ReadDemoTest` | `out/read/read.ofd` |

## 构建

```bash
# 编译
mvn clean compile

# 打包 
mvn clean package

# 安装到本地仓库
mvn clean install

# 生成 Javadoc
mvn javadoc:javadoc
```

> 说明：发布到 Maven Central 使用了 `central-publishing-maven-plugin` 与 `maven-gpg-plugin`，普通构建不受影响。

## 许可

Apache License, Version 2.0。详见每个源文件头部的版权声明：

```
Copyright 2025 ZhangXinPan (11627685@qq.com)
Licensed under the Apache License, Version 2.0
```

联系与反馈：<11627685@qq.com>
