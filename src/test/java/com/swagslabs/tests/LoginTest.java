package com.swagslabs.tests;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.BrowserActions;
import com.swaglabs.utilits.CustomSoftAssertion;
import com.swaglabs.utilits.FilesUtiles;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

public class LoginTest {

     File allure_result = new File("test-outputs/allure-result");
   // LoginPage loginPage ;
    //Tests

    //Configuration

    @BeforeSuite
    public void beforeSuite()
    {
        FilesUtiles.deleteFiles(allure_result);
    }



    @BeforeMethod
    public void setUp()
    {
        DriverManager.createInstance("chrome");
        /**********(Method 1)*******************/
        /*
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://www.saucedemo.com/");
         */
        /*********(Method 2)***Anonymous object***************/
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage("https://www.saucedemo.com/");
    }

    @Test(priority = 0)
    public void successfulLogin()
    {
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertSuccessfulLogin();

    }

//    @Test(priority = 1)
//    public void unSuccessfulLogin()
//    {
//        new LoginPage(DriverManager.getDriver()).enterUsername("standard_usej")
//                .enterPassword("secret_sauce")
//                .clickLoginButton()
//                .assertUnsuccessfullLogin();
//    }


    @AfterMethod
    public void tearDown()
    {

        BrowserActions.closeBrowser(DriverManager.getDriver());
        //CustomSoftAssertion.customAssertAll();
    }

}
