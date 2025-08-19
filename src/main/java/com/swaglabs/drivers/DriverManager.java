package com.swaglabs.drivers;

import com.swaglabs.utilits.LogsUtil;
import com.swaglabs.utilits.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DriverManager {


    /*Parallel Execution*/
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    /*Constructor*/
    private DriverManager()
    {
        super();
    }

    @Step("Create driver instance on: {browserName}")
    public static WebDriver createInstance(String browserName)
    {
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        LogsUtil.info("Driver is created",browserName);
        setDriver(driver);
       return getDriver();
    }
    public static WebDriver getDriver()
    {
        if(driverThreadLocal.get() ==null)
        {
            /*TestNG Assertion*/
            LogsUtil.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver)
    {
         driverThreadLocal.set(driver);
    }

}
