package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[" + LocalDateTime.now() + "] STARTED → "
                + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[" + LocalDateTime.now() + "] PASSED → "
                + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[" + LocalDateTime.now() + "] FAILED → "
                + result.getName());
    }
}

