package com.swaglabs.pages;

import com.swaglabs.utilits.BrowserActions;
import com.swaglabs.utilits.CustomSoftAssertion;
import com.swaglabs.utilits.ElementActions;
import com.swaglabs.utilits.Validation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    //Locators
    private  WebDriver driver;
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

    public void navigateToLoginPage(String Url)
    {
        BrowserActions.navigateToUrl(driver,Url);
    }


    //Actions  > wait - scroll - find -sendKeys
    public LoginPage enterUsername(String username)
    {
        ElementActions.sendData(driver,this.username,username);
        return this;
    }

    public LoginPage enterPassword(String password)
    {
        ElementActions.sendData(driver,this.password,password);
        return this;
    }

    public LoginPage clickLoginButton()
    {
        ElementActions.clickElement(driver,this.loginButton);
        return this;
    }

    public String getErrorMessage()
    {
        return ElementActions.getText(driver,errorMessage);
    }


    //Validations
    public LoginPage assertLoginPageUrl()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentUrl(driver),
                "https://www.saucedemo.com/inventory.html");
        return this;
    }

    public LoginPage assertLoginPageTitle()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver),"Swag Labs");
        return this;
    }
    /*Soft Assertion*/
    public LoginPage assertSuccessfulLoginSoft()
    {
        assertLoginPageUrl().assertLoginPageTitle();
        return this;
    }
    /*Hard Assertion*/
    public LoginPage assertSuccessfulLogin()
    {
        Validation.validatePageUrl(driver, "https://www.saucedemo.com/inventory.html");
       return this;
    }

    public LoginPage assertUnsuccessfullLogin()
    {
        Validation.validateNotEquals(getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is not expected");
        return this;
    }
}
