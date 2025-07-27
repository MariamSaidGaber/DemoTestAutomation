package com.swagslabs.tests;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.CustomSoftAssertion;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

   // LoginPage loginPage ;
    //Tests

    //Configuration
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

    @Test
    public void successfulLogin()
    {
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertSuccessfulLogin();
    }

//    @Test
//    public void unSuccessfulLogin()
//    {
//        new LoginPage(driver).enterUsername("standard_userj")
//                .enterPassword("secret_sauce")
//                .clickLoginButton()
//                .assertUnsuccessfullLogin();
//    }


    @AfterMethod
    public void tearDown()
    {
      DriverManager.getDriver().quit();
        //CustomSoftAssertion.customAssertAll();
    }

}
