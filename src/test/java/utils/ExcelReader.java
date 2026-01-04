package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> readExcel(
            String filePath,
            String sheetName
    ) {

        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = headerRow.getLastCellNum();

            DataFormatter formatter = new DataFormatter();

            // Read rows (skip header)
            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);
                Map<String, String> rowData = new LinkedHashMap<>();

                for (int j = 0; j < colCount; j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = formatter.formatCellValue(row.getCell(j));
                    rowData.put(key, value);
                }

                dataList.add(rowData);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel", e);
        }

        return dataList;
    }


        public static List<Map<String, String>> readUserExcel(
                String filePath,
                String sheetName
        ) {

            List<Map<String, String>> dataList = new ArrayList<>();

            try (FileInputStream fis = new FileInputStream(filePath);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(sheetName);
                Row headerRow = sheet.getRow(0);

                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = headerRow.getLastCellNum();

                DataFormatter formatter = new DataFormatter();

                for (int i = 1; i < rowCount; i++) {

                    Row row = sheet.getRow(i);
                    Map<String, String> rowData = new HashMap<>();

                    for (int j = 0; j < colCount; j++) {
                        String key = headerRow.getCell(j).getStringCellValue();
                        String value = formatter.formatCellValue(row.getCell(j));
                        rowData.put(key, value);
                    }

                    dataList.add(rowData);
                }

            } catch (Exception e) {
                throw new RuntimeException("Failed to read Excel", e);
            }

            return dataList;
        }
    }


