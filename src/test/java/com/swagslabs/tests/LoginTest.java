package com.swagslabs.tests;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
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


@Listeners(TestNGListeners.class)
public class LoginTest {

    //Variables
    WebDriver driver;
     JsonUtils testData;
   // LoginPage loginPage ;
    //Tests

    //Configuration


    @BeforeClass
    public void beforeClass()
    {

        testData = new JsonUtils("test-data");
    }

    @BeforeMethod
    public void setUp()
    {

        String browserName = getPropertyValue("browserType");
        driver = DriverManager.createInstance(browserName);

        /**********(Method 1)*******************/
        /*
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://www.saucedemo.com/");
         */
        /*********(Method 2)***Anonymous object***************/
        new LoginPage(driver).navigateToLoginPage("https://www.saucedemo.com/");
    }

    @Test()
    public void successfulLogin()
    {
        new LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin();


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

        BrowserActions.closeBrowser(driver);
        //CustomSoftAssertion.customAssertAll();
    }

}
