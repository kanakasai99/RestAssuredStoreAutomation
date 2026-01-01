package testcases;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import endpoints.EndPoints;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.LogInitializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;


public class BaseClass {

    ConfigReader configReader;
    //For logging
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;
    @BeforeSuite
    public void setupLogging() {
        LogInitializer.initializeLogger();
    }
    @BeforeClass
    public void setup() throws FileNotFoundException {
        RestAssured.baseURI = EndPoints.BASE_URL;

        configReader = new ConfigReader();
        // Setup filters for logging
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
        PrintStream log = new PrintStream(fos, true);

        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);

        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);


    }

    public Boolean isSortedDescending(List<Integer> list) {

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(0) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public Boolean isSortedAscending(List<Integer> list) {

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(0) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}

