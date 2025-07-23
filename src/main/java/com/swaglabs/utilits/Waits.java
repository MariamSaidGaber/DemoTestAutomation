package com.swaglabs.utilits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    // we use explicit wait
    //present - visible - clickable
    /* private Empty constructor to not allow to initiate */
    private Waits(){
    }

    /* Function to make the element present */
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        /*it is the detailed  to the row */
     /*   WebElement element = driver.findElement(locator);
        return element !=null ? element: null;*/
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver1.findElement(locator));
    }

    /* Function to make the element visible */
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 ->
        {
            WebElement element = waitForElementVisible(driver, locator);
            return element.isDisplayed() ? element : null;

        });
    }


    /* Function to make the element clickable */
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 ->
        {

            WebElement element = waitForElementClickable(driver, locator);
            return element.isEnabled() ? element : null;

        });

    }


    /* Function to make the element clickable Edit */
    public static void waitForElementClickableEdit(WebDriver driver, By locator) {

        WebElement element = waitForElementClickable(driver, locator);
         new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> element.isEnabled());




    }

}