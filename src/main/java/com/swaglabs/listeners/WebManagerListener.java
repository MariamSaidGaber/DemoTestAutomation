package com.swaglabs.listeners;

import com.swaglabs.utilits.Scrolling;
import com.swaglabs.utilits.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

/*This listener is from selenium*/
public class WebManagerListener implements WebDriverListener {

    @Override

    public void beforeFindElement(WebDriver driver, By locator)
    {
        /*this is an option if you want to use it from selenium */
        /*
        Waits.waitForElementClickable(driver,locator);
        Scrolling.scrollToElememt(driver,locator);

         */
    }
}
