# easyofd-java 示例工程

OFD 板式文件（GB/T 33190-2016）[easyofd](https://easyofd.cn) 的 **示例 / Demo 工程**。
核心库以 Maven 依赖方式引入，本工程只包含可直接运行的示例代码。

- 项目主页：<https://easyofd.cn/>
- 标准文档 Wiki：<http://wiki.easyofd.cn>
- 核心库 Maven Central：`cn.easyofd:easyofd`

## 环境要求

- JDK 1.8
- Maven 3.6+

## 引入依赖

```xml
<dependency>
    <groupId>cn.easyofd</groupId>
    <artifactId>easyofd</artifactId>
    <version>2.0.0</version>
</dependency>
```

> 若 `2.0.0` 尚未同步到 Maven Central，可在核心库源码目录执行 `mvn clean install` 安装到本地仓库后再运行本工程。

## 运行示例

```bash
mvn test
```

全部示例生成的 OFD 文件都输出到 `out/` 目录（已被 Git 忽略）。

## 示例清单

| 示例 | 说明 | 输出 |
|---|---|---|
| `QuickStartTest` | 入门：创建文档、加页、写文字、画线、画矩形 | `out/quickstart.ofd` |
| `PathDemoTest` | 图形：线、虚线、矩形、圆、椭圆、五角星 | `out/path/path.ofd` |
| `ImageDemoTest` | 图像：图片写入公共资源并按区域/旋转绘制 | `out/image/image.ofd` |
| `TextDemoTest` | 文字：注册字体、字号颜色、沿圆/椭圆排布 | `out/text/text.ofd` |
| `TemplateDemoTest` | 模板：模板页复用，减少文件体积 | `out/template/template.ofd` |
| `AttachmentDemoTest` | 附件：把文件作为附件写入 OFD | `out/attachment/attachment.ofd` |
| `SignDemoTest` | 数字签名：签名并验签 | `out/sign/signed.ofd` |
| `SealDemoTest` | 电子签章：制章、签章并验章 | `out/seal/sealed.ofd` |
| `ReadDemoTest` | 读取：解包 OFD，查看内部条目与页面 | `out/read/read.ofd` |

## 目录结构

```
easyofd-java
├── pom.xml                     示例工程依赖（easyofd + junit + bouncycastle）
├── font/                       开源字体（思源黑体，SIL OFL），见 font/README.md
├── image/                      示例图片
├── out/                        示例输出目录（Git 忽略）
└── src/test/java/cn/easyofd/demo
    ├── QuickStartTest.java
    ├── PathDemoTest.java
    ├── ImageDemoTest.java
    ├── TextDemoTest.java
    ├── TemplateDemoTest.java
    ├── AttachmentDemoTest.java
    ├── SignDemoTest.java
    ├── SealDemoTest.java
    ├── ReadDemoTest.java
    ├── DemoDocs.java           生成示例用基础文档
    ├── DemoKeys.java           运行时生成证书与私钥
    └── DemoOutput.java         输出目录管理
```

## 关于密钥

示例**不包含任何私钥文件**。签名与签章示例在运行时通过 `DemoKeys` 即时生成自签证书和密钥对，
每次运行都是新密钥，用完即弃。

真实业务请使用合规 CA 颁发的证书，并将私钥存放在 USBKey / 密码机 / KMS 中，切勿提交到代码仓库。

## 关于字体

仓库只保留可自由分发的开源字体，详见 [font/README.md](font/README.md)。

## 许可证

[Apache License 2.0](LICENSE)
