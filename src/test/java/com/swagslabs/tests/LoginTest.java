package com.swagslabs.tests;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.CustomSoftAssertion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
   // LoginPage loginPage ;


    //Tests


    //Configuration
    @BeforeMethod
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
/*Edge driver does not work with me*/
/*
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new EdgeDriver();
        */

        /**********(Method 1)*******************/
        /*
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://www.saucedemo.com/");
         */
        /*********(Method 2)***Anonymous object***************/
        new LoginPage(driver).navigateToLoginPage("https://www.saucedemo.com/");



    }

    @Test
    public void successfulLogin()
    {


        new LoginPage(driver).enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertSuccessfulLoginSoft();
    }

//    @Test
//    public void unSuccessfulLogin()
//    {
//
//
//        new LoginPage(driver).enterUsername("standard_userj")
//                .enterPassword("secret_sauce")
//                .clickLoginButton()
//                .assertUnsuccessfullLogin();
//    }


    @AfterMethod
    public void tearDown()
    {
      driver.quit();
        CustomSoftAssertion.customAssertAll();
    }

}
