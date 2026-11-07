
package com.mynewterm.qa.tests;

import com.mynewterm.qa.base.BaseTest;
import com.mynewterm.qa.pages.LoginPage;
import com.mynewterm.qa.pages.ProductsPage;
import com.mynewterm.qa.pages.ProductDetailsPage;
import com.mynewterm.qa.pages.CartPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    // UI-02: Product Selection
    @Test
    public void productSelectionAndCart() {

        // GIVEN: I am logged in and viewing the Products page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        // Record the product information from the listing
        String expectedName = productsPage.getFirstProductName();
        String expectedDescription = productsPage.getFirstProductDescription();
        String expectedPrice = productsPage.getFirstProductPrice();

        // WHEN: I open the product details page
        productsPage.openFirstProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        // THEN: The details should match the listing
        Assert.assertEquals(
            detailsPage.getName(),
            expectedName,
            "Product name does not match the listing"
        );

        Assert.assertEquals(
            detailsPage.getDescription(),
            expectedDescription,
            "Product description does not match the listing"
        );

        Assert.assertEquals(
            detailsPage.getPrice(),
            expectedPrice,
            "Product price does not match the listing"
        );

        // WHEN: I add the product to the cart
        detailsPage.addToCart();

        // THEN: The cart badge should show one item
        Assert.assertEquals(
            detailsPage.getCartBadgeCount(),
            "1",
            "Cart badge should display one item"
        );

        // WHEN: I open the shopping cart
        detailsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        // THEN: The cart should contain the correct product
        Assert.assertEquals(
            cartPage.getItemName(),
            expectedName,
            "Incorrect product name in cart"
        );

        Assert.assertEquals(
            cartPage.getItemDescription(),
            expectedDescription,
            "Incorrect product description in cart"
        );

        Assert.assertEquals(
            cartPage.getItemPrice(),
            expectedPrice,
            "Incorrect product price in cart"
        );

        Assert.assertEquals(
            cartPage.getItemQuantity(),
            "1",
            "Expected a quantity of one in the cart"
        );
    }
}
