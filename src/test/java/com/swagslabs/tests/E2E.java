package com.swagslabs.tests;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utilits.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.swaglabs.utilits.PropertiesUtils.getPropertyValue;
import static com.swaglabs.utilits.TimestampUtils.getTimestamp;


@Listeners(TestNGListeners.class)
public class E2E {

    //Variables
     WebDriver driver;
     JsonUtils testData;
     /*To make the name unique*/
     String FIRST_NAME;
     String LAST_NAME;
   // LoginPage loginPage ;
    //Tests

    //Configuration


    @BeforeClass
    public void beforeClass()
    {

        testData = new JsonUtils("test-data");
        FIRST_NAME = testData.getJsonData("information-form.firstName")+getTimestamp();
        LAST_NAME =  testData.getJsonData("information-form.lastName")+getTimestamp();
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


    @Test
    public void successfulLogin()
    {
        new LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin();
    }

//    @Test
//    public void unSuccessfulLogin()
//    {
//        new LoginPage(DriverManager.getDriver()).enterUsername("standard_usej")
//                .enterPassword("secret_sauce")
//                .clickLoginButton()
//                .assertUnsuccessfullLogin();
//    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart()
    {
        new HomePage(driver).addSpecificProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));


    }

    @Test(dependsOnMethods = "addingProductToCart")
    public void checkoutProduct()
    {
        new HomePage(driver).clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"),
                        testData.getJsonData("product-names.item1.price"));


    }

    @Test(dependsOnMethods = "checkoutProduct")
    public void fillInformationForm()
    {
        new CartPage(driver).clickCheckoutButton().
            fillInformationForm(FIRST_NAME, LAST_NAME, testData.getJsonData("information-form.postalCode")).
            assertInformationPage(FIRST_NAME, LAST_NAME, testData.getJsonData("information-form.postalCode") );

    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckout()
    {
        new InformationPage(driver).
                clickOnContinueButton()
                .clickOnFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmation-message"))
                .clickOnBackHome();

    }

    @AfterClass
    public void tearDown()
    {
     //   BrowserActions.closeBrowser(driver);
    }

}
