package com.swaglabs.pages;

import com.swaglabs.utilits.BrowserActions;
import com.swaglabs.utilits.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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
    public LoginPage assertSuccessfulLogin()
    {
       Assert.assertEquals(BrowserActions.getCurrentUrl(driver),"https://www.saucedemo.com/inventory.html");
        return this;
    }

    public LoginPage assertUnsuccessfullLogin()
    {
        Assert.assertEquals(getErrorMessage()
                ,"Epic sadface: Username and password do not match any user in this service");
        return this;
    }
}
