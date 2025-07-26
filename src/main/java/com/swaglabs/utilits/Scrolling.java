package com.swaglabs.utilits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {

    private Scrolling()
    {

    }
    //scroll to element
    public static void scrollToElememt(WebDriver driver, By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(locator));
    }

}
