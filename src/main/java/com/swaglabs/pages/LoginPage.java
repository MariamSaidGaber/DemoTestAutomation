package com.swaglabs.pages;

import com.swaglabs.utilits.BrowserActions;
import com.swaglabs.utilits.CustomSoftAssertion;
import com.swaglabs.utilits.ElementActions;
import com.swaglabs.utilits.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.swaglabs.utilits.PropertiesUtils.getPropertyValue;

public class LoginPage {

    //Locators
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    String Url = "https://www.saucedemo.com/";
    private final By errorMessage = By.cssSelector("[data-test='error']");
    //Constructor
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Navigate To Login Page")
    public void navigateToLoginPage(String Url)
    {
        BrowserActions.navigateToUrl(driver,Url);
    }


    //Actions  > wait - scroll - find -sendKeys
    @Step("Enter Username: {0}")
    public LoginPage enterUsername(String username)
    {
        ElementActions.sendData(driver,this.username,username);
        return this;
    }
    @Step("Enter Password: {0}")
    public LoginPage enterPassword(String password)
    {
        ElementActions.sendData(driver,this.password,password);
        return this;
    }
    @Step("Click Login Button")
    public LoginPage clickLoginButton()
    {
        ElementActions.clickElement(driver,this.loginButton);
        return this;
    }
    @Step("Getting Error Message")
    public String getErrorMessage()
    {
        return ElementActions.getText(driver,errorMessage);
    }


    //Validations
    @Step("Assert Login Page URL")
    public LoginPage assertLoginPageUrl()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentUrl(driver),
                "https://www.saucedemo.com/inventory.html");
        return this;
    }
    @Step("Assert Login Page Title")
    public LoginPage assertLoginPageTitle()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver),"Swag Labs");
        return this;
    }
    /*Soft Assertion*/
    @Step("Assert Successful Login Soft")
    public LoginPage assertSuccessfulLoginSoft()
    {
        assertLoginPageUrl().assertLoginPageTitle();
        return this;
    }
    /*Hard Assertion*/
    @Step("Assert Successful Login")
    public LoginPage assertSuccessfulLogin()
    {
        //Validation.validatePageUrl(driver, "https://www.saucedemo.com/inventory.html");
        Validation.validatePageUrl(driver, getPropertyValue("homeURL"));
       return this;
    }
    @Step("Assert Unsuccessful Login")
    public LoginPage assertUnsuccessfullLogin()
    {
        Validation.validateNotEquals(getErrorMessage(),getPropertyValue("errorMSG"),"Error message is not expected");
        return this;
    }

}
