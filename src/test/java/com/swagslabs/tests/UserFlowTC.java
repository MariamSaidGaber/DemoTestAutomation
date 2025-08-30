package com.swagslabs.tests;
import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.BrowserActions;
import com.swaglabs.utilits.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;
import org.testng.annotations.*;

import static com.swaglabs.utilits.PropertiesUtils.getPropertyValue;


/***********************                NOTE                *****************/
/***********Don't forget to call TestNG listeners to call loadProperties********/


@Listeners(TestNGListeners.class)
public class UserFlowTC {

    WebDriver driver;
    JsonUtils testData;

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
        new LoginPage(driver).navigateToLoginPage("https://www.saucedemo.com/");
    }

    @Test
    public void UserFlow()
    {
        //We called all pages in this method
        new LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin()
                .addSpecificProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"))
                .clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"),
                        testData.getJsonData("product-names.item1.price"))
                .clickCheckoutButton()
                .fillInformationForm(testData.getJsonData("information-form.firstName"),
                        testData.getJsonData("information-form.lastName"),
                        testData.getJsonData("information-form.postalCode"))
                .assertInformationPage(testData.getJsonData("information-form.firstName"),
                        testData.getJsonData("information-form.lastName"),
                        testData.getJsonData("information-form.postalCode"))
                .clickOnContinueButton()
                .clickOnFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmation-message"))
                .clickOnBackHome();
    }

    @AfterMethod
    public void tearDown()
    {
      //  BrowserActions.closeBrowser(driver);
    }

}
