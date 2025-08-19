package com.swagslabs.tests;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;
import java.io.File;
import java.util.Properties;

import static com.swaglabs.utilits.PropertiesUtils.getPropertyValue;
import static com.swaglabs.utilits.PropertiesUtils.loadProperties;



public class LoginTest {

    //Variables

     File allure_result = new File("test-outputs/allure-result");
     JsonUtils testData;
   // LoginPage loginPage ;
    //Tests

    //Configuration

    @BeforeSuite
    public void beforeSuite()
    {
        loadProperties();
        FilesUtiles.deleteFiles(allure_result);
        testData = new JsonUtils("test-data");
    }



    @BeforeMethod
    public void setUp()
    {
        //String browserName = PropertiesUtils.getPropertyValue("browserType");
        String browserName = getPropertyValue("browserType");
        DriverManager.createInstance(browserName);

        /**********(Method 1)*******************/
        /*
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://www.saucedemo.com/");
         */
        /*********(Method 2)***Anonymous object***************/
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage("https://www.saucedemo.com/");
    }

    @Test()
    public void successfulLogin()
    {
        new LoginPage(DriverManager.getDriver()).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin();
        ScreenshotsUtils.takeScreenshots("successful-login");

    }

//    @Test()
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

    @AfterClass
    public void afterClass()
    {
        AllureUtils.attacheLogsToAllureReport();
    }
}
