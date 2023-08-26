package com.taiquan.utils;

import com.taiquan.bean.DataCompany;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

public class ReadExcel {
    private int totalRows = 0;
    private int totalCells;
    {
        totalCells = 0;
    }
    private String errorMsg;

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean validateExcel(String filePath){
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){
            errorMsg = "文件名不是excel格式";
            println("Come to validateExcel " + filePath);
            return false;
        }
        return true;
    }

    public List<DataCompany> getExcelInfo(String fileName, MultipartFile fFile, HttpServletRequest request) {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) fFile;
        String filePath =request.getSession().getServletContext().getRealPath("");
        File fileDir = new File(filePath + "/uploads");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file1 = new File(filePath+ "/" + new Date().getTime() + ".xls");
        try {
            println(fFile.getName());
            commonsMultipartFile.getFileItem().write(file1);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<DataCompany> companies = new ArrayList<>();
        InputStream is = null;
        try {
            if (!validateExcel(fileName)) {
                println(file1.getAbsolutePath());
                println(file1.getName());
                return null;
            }
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(fileName))
                isExcel2003 = false;
            is = new FileInputStream(file1);
            companies = getExcelInfo(is,isExcel2003);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null){
                try {
                    is.close();
                }catch (IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return companies;
    }

    private List<DataCompany> getExcelInfo(InputStream is, boolean isExcel2003) {
        List<DataCompany> companies = null;
        try {
            Workbook workbook = null;
            if (isExcel2003){
                workbook = new HSSFWorkbook(is);
            }else 
                workbook = new XSSFWorkbook(is);
            companies = readExcelValue(workbook);
        }catch (IOException e){
            e.printStackTrace();
        }
        return companies;
    }

    private List<DataCompany> readExcelValue(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        Map<String,Integer> fields = null;
        if (totalRows >= 1 && sheet.getRow(0) != null){
            Row row = sheet.getRow(0);
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            fields = refectCells(row);
        }
        System.out.println("################################      map fields = " + fields);
        List<DataCompany> companies = new ArrayList<>();
        DataCompany company;
        for (int r = 1;r < totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            company = new DataCompany();
            assert fields != null;
            company.setWebAdd(row.getCell(fields.get("webAdd")).getStringCellValue());
            if (row.getCell(fields.get("moneyUnit")) != null) {
                company.setMoneyUnit(row.getCell(fields.get("moneyUnit")).getStringCellValue());
            }
            if (row.getCell(fields.get("money")) != null){
                company.setMoney((float)row.getCell(fields.get("money")).getNumericCellValue());
            }
            company.setAddress(row.getCell(fields.get("address")).getStringCellValue());
            company.setName(row.getCell(fields.get("name")).getStringCellValue());
            company.setBusiness(row.getCell(fields.get("business")).getStringCellValue());
            company.setOwner(row.getCell(fields.get("owner")).getStringCellValue());
            company.setCity(row.getCell(fields.get("city")).getStringCellValue());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                company.setEsDate(sdf.parse(row.getCell(fields.get("esDate")).getStringCellValue()));
            }catch (Exception e){
                e.printStackTrace();
            }
            company.setPhoneNumber(new ArrayList<>(Arrays.asList(row.getCell(fields.get("phoneNumber")).getStringCellValue().split(" "))));
            company.setEmail(row.getCell(fields.get("email")).getStringCellValue());
            companies.add(company);
        }
        return companies;
    }

    private Map<String,Integer> refectCells(Row row) {
        List<String> fields = new ArrayList<>();
        for (int j = 0;j < totalCells;j++){
            fields.add(row.getCell(j).getStringCellValue());
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("name",fields.indexOf("name"));
        if (fields.contains("owner")){
            map.put("owner",fields.indexOf("owner"));
        }

        if (fields.contains("city")){
            map.put("city",fields.indexOf("city"));
        }
        if (fields.contains("money")){
            map.put("money",fields.indexOf("money"));
        }
        if (fields.contains("moneyUnit")){
            map.put("moneyUnit",fields.indexOf("moneyUnit"));
        }

        if (fields.contains("esDate")){
            map.put("esDate",fields.indexOf("esDate"));
        }

        if (fields.contains("business")){
            map.put("business",fields.indexOf("business"));
        }
        if (fields.contains("phoneNumber")) {
            map.put("phoneNumber", fields.indexOf("phoneNumber"));
        }
        if (fields.contains("address")) {
            map.put("address", fields.indexOf("address"));
        }
        if (fields.contains("webAdd")) {
            map.put("webAdd", fields.indexOf("webAdd"));
        }
        if (fields.contains("email")) {
            map.put("email", fields.indexOf("email"));
        }
        return map;
    }
}
