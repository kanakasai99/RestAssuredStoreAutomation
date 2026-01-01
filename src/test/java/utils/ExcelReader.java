package utils;
import java.io.FileInputStream; import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> readExcel(String path) throws IOException {

        List<Map<String, String>> data = new ArrayList<>();

        FileInputStream file = new FileInputStream("C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\product.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        for (int r = 1; r <= rows; r++) { // skip header
            XSSFRow row = sheet.getRow(r);
            List<String> rowData = new ArrayList<>();

            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);
                rowData.add(cell.toString());
            }
            data.add((Map<String, String>) rowData);
        }

        workbook.close();
        file.close();

        return data;
    }
}



