package com.swaglabs.pages;

import com.swaglabs.utilits.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //variables
    WebDriver driver;
    //Constructor
    public HomePage( WebDriver driver)
    {
         this.driver =  driver;
    }

    //locators
        By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    //actions

    @Step("Navigate to home page")
    public HomePage navigateToHomePage()
    {
        BrowserActions.navigateToUrl(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }
    @Step("Add specific product to cart")
    public HomePage addSpecificProductToCart(String productName)
    {
        /* relative locator */
      //  By passLocator = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='Sauce Labs Backpack']"));
        /*dynamic locator with relative locator */
        LogsUtil.info("Adding "+ productName+ "to cart");
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName +"']"));
        ElementActions.clickElement(driver,addToCartButton);
        return this;

    }
    @Step("Click on cart icon")
    public CartPage clickCartIcon()
    {
        ElementActions.clickElement(driver, cartIcon);
        return new CartPage(driver);
    }
    //validation
    @Step("Assert product added to cart")
    public HomePage assertProductAddedToCart(String productName)
    {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName +"']"));
        String actualValue = ElementActions.getText(driver,addToCartButton);
        LogsUtil.info("Actual Value: " + actualValue );
        Validation.validateEquals(actualValue,"Remove", "Product not added to cart");
        LogsUtil.info(productName + "added to cart successfully");
        return this;
    }
}
