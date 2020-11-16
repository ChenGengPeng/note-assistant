package com.sziit.noteassistant.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.sziit.noteassistant.pojo.NoteAuth;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/11  10:59
 */
@Component
public class PdfUtils {

    public static String generatedPdf(NoteAuth note,String key) {
        String filename = "pdf/" + key + ".pdf";
        //创建文件
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream byteArrayOutputStream = null;
        JSONArray data = note.getData();
        String s = null;
        PdfWriter writer = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            //中文字体,解决中文不能显示问题
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese);
            //创建书写器
            writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Font title = new Font();
            title.setSize(24);
            font.setStyle(Font.BOLD);
            document.add(new Paragraph(note.getTitle(), title));
            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    String type = String.valueOf(jsonObject.get("type"));
                    String value = String.valueOf(jsonObject.get("value"));
                    if ("text".equals(type) || "ocrText".equals(type)) {
                        if (document.isOpen()) {
                            document.add(new Paragraph(value, font));
                        } else {
                            document.open();
                            document.add(new Paragraph(value, font));
                        }
                    }
                    if ("image".equals(type)) {
//                    new一个URL对象
                        URL url = new URL(value);
                        BufferedImage read = ImageIO.read(url.openStream());
                        Image image = Image.getInstance(read, null);
                        image.scaleAbsolute(300, 200);
                        if (document.isOpen()) {
                            document.add(image);
                        } else {
                            document.open();
                            document.add(image);
                        }
                    }
                }
            }
            writer.close();
            document.close();
            byte[] content = byteArrayOutputStream.toByteArray();
            s = QiniuyunUtils.byteUpLoad(content, filename);
            byteArrayOutputStream.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (writer != null) {
                    writer.close();
                }
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static String generateWord(NoteAuth noteAuth,String key) {
        String filename = "word/" + key + ".docx";
        String s = null;
        JSONArray data = noteAuth.getData();
        XWPFDocument document = new XWPFDocument();
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayOutputStream bs = null;
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(24);
        run.setText(noteAuth.getTitle());
        run.addBreak(BreakType.TEXT_WRAPPING);
        run.setFontSize(12);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            if ( !data.isEmpty()) {
                for (int i = 0; i < noteAuth.getData().size(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    String type = String.valueOf(jsonObject.get("type"));
                    String value = String.valueOf(jsonObject.get("value"));
                    if ("text".equals(type) || "ocrText".equals(type)) {
                        run.setText(value + "\t");
                        run.addBreak(BreakType.TEXT_WRAPPING);
                    }
                    if ("image".equals(type)) {
                        URL url = new URL(value);
                        BufferedImage read = ImageIO.read(url.openStream());
                        bs = new ByteArrayOutputStream();
                        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
                        ImageIO.write(read, "jpeg", imOut);
                        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());
                        run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(300), Units.toEMU(200));
                        run.addBreak(BreakType.TEXT_WRAPPING);
                        imOut.close();
                    }
                }
            }
            document.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            s = QiniuyunUtils.byteUpLoad(bytes, filename);
            byteArrayOutputStream.close();
            document.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (bs != null) {
                    bs.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
