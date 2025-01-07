package com.jas.edu.utility;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.GeneralScience;
import com.jas.edu.data.entity.Tamil;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


public abstract class CreateWord implements HttpServletResponse {


    public static void generateWord(String filename, List<Tamil> result, String title) {

        OutputStream file = null;
        try {
            String path = System.getProperty("user.dir")+"\\src\\main\\resources\\META-INF\\resources\\assets\\"+filename;
            file  = new FileOutputStream(new File(path ));

            XWPFDocument document = new XWPFDocument();
            XWPFParagraph titleDoc = document.createParagraph();
            titleDoc.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun  = titleDoc.createRun();
            XWPFParagraph subTitle = document.createParagraph();
            XWPFRun subTitleRun = subTitle.createRun();

            int i = 1;
            titleRun.setText(title + JConstants.DOWNHEADEREXT);
            titleRun.setBold(true);
            titleRun.setFontSize(18);
            titleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            titleRun.setColor("FF0000");
            titleRun.addBreak();
            titleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            for (Tamil tamil : result) {
                subTitleRun.setColor("000080");
                subTitleRun.setFontSize(14);
                subTitleRun.setText( i + "  . " + JConstants.QUESTION +" : " + tamil.getTg_question());
                subTitleRun.addBreak();
                subTitleRun.addTab();
                subTitleRun.setText(JConstants.ANSWER +" : " +tamil.getTg_answer());
                subTitleRun.addBreak();
                subTitleRun.addBreak();
                i++;
            }
            document.write(file);
            file.close();
            document.close();
            UI.getCurrent().getPage().open("\\assets\\"+ filename, "_blank");

            Notification.show("File download completed");

         } catch (Exception e) {
            System.out.println("****"+ e.getMessage());
        }

    }
    public static void generatePdf(String filename, List<Tamil> result, String title) {
        System.out.println(title);

        //String home = System.getProperty("user.home");
        //File name = new File(home+"/Downloads/" + filename);
        String path = System.getProperty("user.dir");
        path = path + File.separator + filename;
        FontFactory.register("C:\\Windows\\Fonts\\Latha\\Latha Bold");
        Font font0 = FontFactory.getFont("Latha Bold", BaseFont.WINANSI, 12);

        //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, new FileOutputStream(new File(String.valueOf(path))));
            document.open();
            document.newPage();

            document.add(new Paragraph("Hello World"));

            int i = 1;
            for (Tamil tamil : result) {
                String que = new String( i + "  . " + JConstants.QUESTION +" : " + tamil.getTg_question());
                document.add(new Paragraph(que, font0));
                String ans = new String(  JConstants.ANSWER +" : " + tamil.getTg_answer());
                document.add(new Paragraph(ans, font0));
                i++;
            }
            //cb.endText();
            document.close();

            File directory = new File(String.valueOf(path));
            File toopen = directory.getAbsoluteFile();
            String s[] = new String[1];
            s[0] = String.valueOf(toopen);
            System.out.println(toopen);
            if (Desktop.isDesktopSupported()) {
                try {
                    if (toopen.exists()) {
                        Desktop.getDesktop().open(toopen);
                    }
                    else {
                        System.out.println("The file does not exist.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Runtime runtime = Runtime.getRuntime();
                if (System.getenv("OS") != null && System.getenv("OS").contains("Windows")) {
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + toopen);
                } else {
                    runtime.exec("xdg-open " + toopen);
                }
            }
            Notification.show("File download completed");

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String getFooterText() {
        String footerText1 = String.format("%1$-" + 40 + "s", JConstants.FOOTERTEXT1);
        String footerText2 = String.format("%1$-" + 40 + "s", JConstants.FOOTERTEXT2);

        String toReturn = footerText1 + footerText2;
        return toReturn;
    }

    public static void generateScienceWord(String filename, List<GeneralScience> result, String title) {
        OutputStream file = null;
        try {
            String path = System.getProperty("user.dir")+"\\src\\main\\resources\\META-INF\\resources\\assets\\"+filename;
            System.out.println("pathis " + path);

            file  = new FileOutputStream(new File(path ));

            XWPFDocument document = new XWPFDocument();
            XWPFParagraph titleDoc = document.createParagraph();
            titleDoc.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun  = titleDoc.createRun();
            XWPFParagraph subTitle = document.createParagraph();
            XWPFRun subTitleRun = subTitle.createRun();

            int i = 1;
            titleRun.setText(title + JConstants.DOWNHEADEREXT);
            titleRun.setBold(true);
            titleRun.setFontSize(18);
            titleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            titleRun.setColor("FF0000");
            titleRun.addBreak();
            titleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            for (GeneralScience generalScience : result) {
                subTitleRun.setColor("000080");
                subTitleRun.setFontSize(14);
                subTitleRun.setText( i + "  . " + JConstants.QUESTION +" : " + generalScience.getGs_question());
                subTitleRun.addBreak();
                subTitleRun.addTab();
                subTitleRun.setText(JConstants.ANSWER +" : " +generalScience.getGs_answer());
                subTitleRun.addBreak();
                subTitleRun.addBreak();
                i++;
            }
            document.write(file);
            file.close();
            document.close();
            UI.getCurrent().getPage().open("\\assets\\"+ filename, "_blank");

            Notification.show("File download completed");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}