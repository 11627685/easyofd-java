# 字体说明

本目录只存放**可自由分发**的开源字体，示例工程依赖它渲染中文。

| 字体 | 许可 | 说明 |
|---|---|---|
| 思源黑体 Normal.ttc | SIL Open Font License 1.1 | Adobe / Google 联合发布，可自由使用、修改、再分发 |

- 许可全文：<https://scripts.sil.org/OFL>
- 字体来源：<https://github.com/adobe-fonts/source-han-sans>

## 为什么不含宋体 / 楷体

`宋体`、`楷体`、`SimSun`、`KaiTi`、`Calibri` 等字体受商业版权保护，不能随开源仓库分发。
示例代码中所有涉及字体的位置均已改为使用思源黑体。

## 换成自己的字体

修改 `TextDemoTest` 中的两个常量即可：

```java
private static final String FONT_FILE = "font/思源黑体 Normal.ttc";
private static final String FONT_NAME = "思源黑体 Normal";
```

若不想随仓库携带字体文件，也可把字体放在仓库外，改写为绝对路径后运行。
