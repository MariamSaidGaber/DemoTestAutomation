package com.swaglabs.pages;

import com.swaglabs.utilits.CustomSoftAssertion;
import com.swaglabs.utilits.ElementActions;
import com.swaglabs.utilits.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    //variables
    private WebDriver driver;
    //locators
    By productName = By.cssSelector(".inventory_item_name");
    By productPrice = By.cssSelector(".inventory_item_price");
    By checkoutButton = By.cssSelector(".checkout_button");
    //constructor
    public CartPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //code
    @Step("Get product name")
    private String getProductName()
    {
        return ElementActions.getText(driver,productName);
    }
    @Step("Get product price")
    private String getProductPrice()
    {
        return ElementActions.getText(driver,productPrice);
    }
    @Step("Click on checkout button")
    public InformationPage clickCheckoutButton()
    {
        ElementActions.clickElement(driver,checkoutButton);
        return new InformationPage(this.driver);
    }

    //validations
    @Step("Assert product details")
    public CartPage assertProductDetails(String productName, String productPrice)
    {
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
       // Validation.validateEquals(actualProductName,productName,"Product name mismatch");
       // Validation.validateEquals(actualProductPrice,productPrice,"Product price mismatch");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName,productName,"Product name mismatch");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice,productPrice,"Product price mismatch");
        return this;
    }

}
