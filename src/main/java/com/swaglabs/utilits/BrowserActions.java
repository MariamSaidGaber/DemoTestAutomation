package com.swaglabs.utilits;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {

    }
    @Step("Navigating to URL: {url}")
    public static void navigateToUrl(WebDriver driver, String Url)
    {
        driver.get(Url);
        LogsUtil.info("Navigated to url: ", Url);
    }
    @Step("Getting current URL: {url}")
    public static String getCurrentUrl(WebDriver driver)
    {
        LogsUtil.info("Current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    @Step("Getting page Title: {url}")
    public static String getPageTitle(WebDriver driver)
    {
        LogsUtil.info("Page title: ", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public static void refreshPage(WebDriver driver)
    {
        LogsUtil.info("Refreshing the page");
        driver.navigate().refresh();
    }

    @Step("Closing the browser")
    public static void closeBrowser(WebDriver driver)
    {
        LogsUtil.info("Closing the browser");
        driver.quit();
    }

}
