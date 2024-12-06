//package com.example.kourse_work.Service;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@Service
//public class ExcelReportGenerator {
//    public void generateReport(String filePath, String sheetName, String[] headers, String[][] data) {
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet(sheetName);
//
//            // Header Row
//            Row headerRow = sheet.createRow(0);
//            for (int i = 0; i < headers.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(headers[i]);
//            }
//
//            // Data Rows
//            for (int i = 0; i < data.length; i++) {
//                Row dataRow = sheet.createRow(i + 1);
//                for (int j = 0; j < data[i].length; j++) {
//                    Cell cell = dataRow.createCell(j);
//                    cell.setCellValue(data[i][j]);
//                }
//            }
//
//            // Write to file
//            try (FileOutputStream fos = new FileOutputStream(filePath)) {
//                workbook.write(fos);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}