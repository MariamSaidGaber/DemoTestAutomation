package com.swaglabs.listeners;

import com.swaglabs.utilits.*;
import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    File allure_result = new File("test-outputs/allure-result");
    File logs = new File("test-outputs/logs");
    File screenshots= new File("test-outputs/screenshots");
    /*IExecutionListener*/
    @Override
    public void onExecutionStart()
    {
        LogsUtil.info("Test execution started");
        PropertiesUtils.loadProperties();
        FilesUtiles.deleteFiles(allure_result);
        FilesUtiles.cleanDirectory(logs);
        FilesUtiles.cleanDirectory(screenshots);

    }

    @Override
    public void onExecutionFinish()
    {
        LogsUtil.info("Test execution finished");
    }

    /*ITestListener*/
    @Override
    public void onTestSuccess(ITestResult result)
    {
        LogsUtil.info("Test case", result.getName(),"passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        LogsUtil.info("Test case", result.getName(),"failed");
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        LogsUtil.info("Test case", result.getName(),"skipped");
    }
    /*IInvokedMethodListener*/
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult)
    {
        /*Check is TestMethod to attache after test only, not beforetest, aftertest and so on*/
        if(method.isTestMethod())
        {
            try{
                CustomSoftAssertion.customAssertAll();
            } catch (AssertionError e) {
                testResult.setStatus( ITestResult.FAILURE);
                testResult.setThrowable(e);
            }

            switch (testResult.getStatus())
            {
                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshots("Passed-"+ testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshots("Failure-"+ testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshots("Skipped-"+ testResult.getName());

            }
            AllureUtils.attacheLogsToAllureReport();
        }

    }

}
