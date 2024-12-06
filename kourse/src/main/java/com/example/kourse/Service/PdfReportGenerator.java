//package com.example.kourse_work.Service;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.stereotype.Service;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@Service
//public class PdfReportGenerator {
//    public void generateReport(String filePath, String content) {
//        Document document = new Document();
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream(filePath));
//            document.open();
//            document.add(new Paragraph(content));
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//        }
//    }
//}
