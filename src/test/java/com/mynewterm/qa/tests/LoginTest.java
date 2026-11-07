
package com.mynewterm.qa.tests;

import com.mynewterm.qa.base.BaseTest;
import com.mynewterm.qa.pages.LoginPage;
import com.mynewterm.qa.pages.ProductsPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    // UI-01: Successful Login
    @Test
    public void successfulLogin() {

        // GIVEN: I am on the SauceDemo login page
        LoginPage loginPage = new LoginPage(driver);

        // WHEN: I log in with valid credentials
        loginPage.login("standard_user", "secret_sauce");

        // THEN: The Products page should load
        ProductsPage productsPage = new ProductsPage(driver);

        // Wait for navigation to the Products page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        // Verify the URL
        Assert.assertTrue(
            driver.getCurrentUrl().endsWith("/inventory.html"),
            "Expected to be redirected to the Products page"
        );

        // Verify the page heading
        Assert.assertEquals(
            productsPage.getHeading(),
            "Products",
            "Expected the Products heading to be displayed"
        );

        // Verify the product inventory is visible
        Assert.assertTrue(
            productsPage.hasProducts(),
            "Expected the product inventory to be visible"
        );
    }
}

