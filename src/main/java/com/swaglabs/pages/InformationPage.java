package com.swaglabs.pages;

import com.swaglabs.utilits.CustomSoftAssertion;
import com.swaglabs.utilits.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {

    //variables
    private WebDriver driver;
    //locators

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    //constructor
    public InformationPage(WebDriver driver)
    {
        this.driver = driver;
    }
    //code
    @Step("Fill information form: First mame: {0}, Last Name: {1}, Postal Code: {2}")
    public InformationPage fillInformationForm(String firstName, String lastName, String postalCode)
    {
        ElementActions.sendData(driver, this.firstName, firstName);
        ElementActions.sendData(driver, this.lastName, lastName);
        ElementActions.sendData(driver, this.postalCode, postalCode);
        return this;
    }

    @Step("Click on continue button")
    public OverviewPage clickOnContinueButton()
    {
        ElementActions.clickElement(driver,continueButton);
        return new OverviewPage(driver);
    }
    //validation
    @Step("Assert information page")
    public void assertInformationPage(String firstName, String lastName, String postalCode)
    {
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.firstName),firstName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.lastName),lastName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.postalCode),postalCode);
    }
}
