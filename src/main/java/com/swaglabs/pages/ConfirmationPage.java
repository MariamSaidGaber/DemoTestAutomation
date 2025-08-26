package com.swaglabs.pages;

import com.swaglabs.utilits.ElementActions;
import com.swaglabs.utilits.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    //variables
    private WebDriver driver;
    //locators
    private final By confirmationMessage = By.cssSelector(".complete-header");
    private final By backHomeButton = By.id("back-to-products");
    //constructor
    public ConfirmationPage(WebDriver driver)
    {
        this.driver = driver;
    }
    //code
    @Step("Get Confirmation Message")
    public String getConfirmationMessage()
    {
        return ElementActions.getText(driver,confirmationMessage);
    }

    @Step("Click on Finish button")
    public ConfirmationPage clickOnBackHome()
    {
        ElementActions.clickElement(driver,this.backHomeButton);
        return this;
    }

    //validation
    @Step("Assert confirmation message")
    public void assertConfirmationMessage(String expectedMessage)
    {
        String actualMessage = getConfirmationMessage();
        Validation.validateEquals(actualMessage, expectedMessage,"Confirmation message mismatch");
    }

}
