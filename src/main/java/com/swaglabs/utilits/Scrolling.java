package com.swaglabs.utilits;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {

    private Scrolling()
    {

    }
    //scroll to element
    @Step("Scrolling to the element: {locator}")
    public static void scrollToElememt(WebDriver driver, By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",
                ElementActions.findElement(driver, locator));
    }

}
