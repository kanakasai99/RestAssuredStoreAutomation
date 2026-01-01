package utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInitializer {
    public static void initializeLogger() {
        PropertyConfigurator.configure("src/test/resources/testData/log4j.properties");

        String logDir = "C:/Users/Sai/IdeaProjects/RestAssuredAutomation/RestAssuredStoreAutomation/logs/";


        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String logFileName = logDir + "log_" + timestamp + ".log";

        try {
            FileAppender fileAppender = new FileAppender();
            fileAppender.setName("dynamicFileAppender");
            fileAppender.setFile(logFileName);
            fileAppender.setLayout(new org.apache.log4j.PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
            fileAppender.setAppend(true);
            fileAppender.activateOptions();

            Logger.getRootLogger().addAppender(fileAppender);

            System.out.println("✅ Logs will be written to: " + logFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
