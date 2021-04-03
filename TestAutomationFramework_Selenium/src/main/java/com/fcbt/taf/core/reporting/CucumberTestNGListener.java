package com.fcbt.taf.core.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CucumberTestNGListener implements ITestListener {

    @Override
    public synchronized void onStart(ITestContext context) {
        Reporter.setUp();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        Reporter.tearDown();
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Reporter.pass("PASSED - "+result.getName());
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getThrowable());
        String methodName = result.getMethod().getMethodName();
        Reporter.fail("FAILED - " + result.getName(), result.getThrowable());
        Reporter.addScreenshot(methodName + "-Failed", "Screenshot After Test Failure..!!!");
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        Reporter.skip("SKIPPED - "+result.getName(), result.getThrowable());
    }
}
