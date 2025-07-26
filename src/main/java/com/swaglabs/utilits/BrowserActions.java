package com.swaglabs.utilits;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {

    }

    public static void navigateToUrl(WebDriver driver, String Url)
    {
        driver.get(Url);
    }

    public static String getCurrentUrl(WebDriver driver)
    {
        return driver.getCurrentUrl();
    }

    public static String getPageTitle(WebDriver driver)
    {
        return driver.getTitle();
    }

    public static void refreshPage(WebDriver driver)
    {
        driver.navigate().refresh();
    }
}
