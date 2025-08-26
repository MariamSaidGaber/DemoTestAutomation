package com.swaglabs.pages;

import com.swaglabs.utilits.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    //variables
    private WebDriver driver;
    //locators
    private final By finishButton = By.id("finish");
    //constructor
    public OverviewPage(WebDriver driver)
    {
        this.driver = driver;
    }
    //code
    @Step("Click on Finish button")
    public ConfirmationPage clickOnFinishButton()
    {
        ElementActions.clickElement(driver, this.finishButton);
        return new ConfirmationPage(driver);
    }
    //validation


}
