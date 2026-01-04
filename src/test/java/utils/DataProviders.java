package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.util.*;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataProviders {

    @DataProvider(name = "jsonDataProduct")
    public Object[][] jsonDataProvider() throws IOException {
        // Path to your JSON file
        String filePath = "C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\product.json";

        // Read JSON file and map it to a List of Maps
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> dataList = objectMapper.readValue(new File(filePath),
                new TypeReference<List<Map<String, String>>>() {
                });

        // Convert List<Map<String, String>> to Object[][]
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = new Object[] { dataList.get(i) };
        }

        return dataArray;
    }
    @DataProvider(name = "jsonDataUser")
    public Object[][] jsonDataUser() throws IOException {
        // Path to your JSON file
        String filePath = "C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\user.json";

        // Read JSON file and map it to a List of Maps
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> dataList = objectMapper.readValue(new File(filePath),
                new TypeReference<List<Map<String, Object>>>() {
                });

        // Convert List<Map<String, String>> to Object[][]
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = new Object[] { dataList.get(i) };
        }

        return dataArray;
    }
    @DataProvider(name = "jsonDataUser1")
    public Object[][] jsonDataUser1() throws Exception {

        // Path to JSON test data
        String filePath =
                "src/test/resources/testData/user.json";

        ObjectMapper mapper = new ObjectMapper();

        // Convert JSON array → List<User>
        List<User> users = mapper.readValue(
                new File(filePath),
                new TypeReference<List<User>>() {}
        );

        // Convert List<User> → Object[][]
        Object[][] data = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);   // one User per test run
        }

        return data;

    }

    /*  @DataProvider  /*CSV file
    public Object[][] csvDataProvider() throws IOException {
        // Path to the CSV file
        String filePath = "C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\product.xlsx";

        // Read the CSV file and store data in a List
        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Splitting by comma
                dataList.add(data);
            }
        }

        // Convert List<String[]> to Object[][]
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }

        return dataArray;
    }*/
    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {

        String path =
                "C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\product.xlsx";

        List<Map<String, String>> dataList =
                ExcelReader.readExcel(path, "Sheet1");

        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }

        return data;
    }
    @DataProvider(name = "userExcelData")
    public Object[][] getUserData() {

        String path = "C:\\Users\\Sai\\IdeaProjects\\RestAssuredAutomation\\RestAssuredStoreAutomation\\src\\test\\resources\\testData\\user.xlsx";

        List<Map<String, String>> excelData =
                ExcelReader.readUserExcel(path, "Sheet1");

        Object[][] data = new Object[excelData.size()][1];

        for (int i = 0; i < excelData.size(); i++) {
            User user = UserBuilder.buildUser(excelData.get(i));
            data[i][0] = user;
        }

        return data;
    }
}


