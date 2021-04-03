package com.fcbt.taf.core.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelUtilities {

    public static Map<String, String> getTestData(String filePath){

        Map<String, String> dataMap = new LinkedHashMap<>();
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.getSheet("TestData");

        int lastRowNum = sheet.getLastRowNum();
        int lastColumnNum = sheet.getRow(1).getLastCellNum();

        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
                dataMap.put(row.getCell(0).toString(),row.getCell(1).toString());
        }
        return dataMap;
    }

    public static Workbook getWorkbook(String filePath){
        String fileExtension = filePath.substring(filePath.indexOf('.'));
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            if (fileExtension.equals(".xlsx")) {
                workbook = new XSSFWorkbook(fileInputStream);
            }
            else if(fileExtension.equals(".xls")){
                workbook = new HSSFWorkbook(fileInputStream);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return workbook;
    }


}
