
package com.mynewterm.qa.tests;

import com.mynewterm.qa.base.BaseTest;
import com.mynewterm.qa.pages.LoginPage;
import com.mynewterm.qa.pages.ProductsPage;
import com.mynewterm.qa.pages.CartPage;
import com.mynewterm.qa.pages.CheckoutInformationPage;
import com.mynewterm.qa.pages.CheckoutOverviewPage;
import com.mynewterm.qa.pages.CheckoutCompletePage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    // UI-03: End-to-End Checkout
    @Test
    public void completeCheckout() {

        // GIVEN: I am logged in with two products in my cart
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        List<String> expectedItems = List.of(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light"
        );

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.openCart();

        // THEN: The cart should contain both products
        CartPage cartPage = new CartPage(driver);
        List<String> cartItems = cartPage.getCartItemNames();

        Assert.assertEquals(
            cartItems.size(), 2,
            "Expected two products in the cart"
        );

        Assert.assertTrue(
            cartItems.containsAll(expectedItems),
            "Cart does not contain the expected products"
        );

        // WHEN: I proceed to checkout and enter customer details
        cartPage.proceedToCheckout();

        CheckoutInformationPage informationPage =
            new CheckoutInformationPage(driver);

        informationPage.enterCustomerDetails(
            "Zaz", "Excellenttester", "MK45 4HR"
        );

        informationPage.continueToOverview();

        // THEN: The order overview should be correct
        CheckoutOverviewPage overviewPage =
            new CheckoutOverviewPage(driver);

        List<String> overviewItems = overviewPage.getItemNames();

        Assert.assertEquals(
            overviewItems.size(), 2,
            "Expected two products in the order overview"
        );

        Assert.assertTrue(
            overviewItems.containsAll(expectedItems),
            "Order overview contains incorrect products"
        );

        // Calculate the expected subtotal from known product prices
        BigDecimal expectedSubtotal = new BigDecimal("29.99")
            .add(new BigDecimal("9.99"));

        BigDecimal actualSubtotal = parseAmount(
            overviewPage.getSubtotal()
        );

        Assert.assertEquals(
            actualSubtotal, expectedSubtotal,
            "Order subtotal is incorrect"
        );

        // SauceDemo uses an 8% tax rate
        BigDecimal expectedTax = expectedSubtotal
            .multiply(new BigDecimal("0.08"))
            .setScale(2, RoundingMode.HALF_UP);

        BigDecimal actualTax = parseAmount(
            overviewPage.getTax()
        );

        Assert.assertEquals(
            actualTax, expectedTax,
            "Order tax is incorrect"
        );

        // Verify the total equals the subtotal plus tax
        BigDecimal actualTotal = parseAmount(
            overviewPage.getTotal()
        );

        Assert.assertEquals(
            actualTotal,
            expectedSubtotal.add(expectedTax),
            "Order total is incorrect"
        );

        // Verify payment and shipping information
        Assert.assertEquals(
            overviewPage.getPaymentInformation(),
            "SauceCard #31337",
            "Payment information is incorrect"
        );

        Assert.assertEquals(
            overviewPage.getShippingInformation(),
            "Free Pony Express Delivery!",
            "Shipping information is incorrect"
        );

        // WHEN: I complete the order
        overviewPage.finishOrder();

        // THEN: The order confirmation should appear
        CheckoutCompletePage completePage =
            new CheckoutCompletePage(driver);

        Assert.assertEquals(
            completePage.getPageHeading(),
            "Checkout: Complete!",
            "Checkout completion page did not load"
        );

        Assert.assertEquals(
            completePage.getConfirmationHeading(),
            "Thank you for your order!",
            "Order confirmation heading is incorrect"
        );

        Assert.assertTrue(
            completePage.getConfirmationMessage().contains("dispatched"),
            "Expected a message confirming order dispatch"
        );
    }

    // Convert a displayed currency value into a BigDecimal
    private BigDecimal parseAmount(String text) {
        return new BigDecimal(
            text.replaceAll("[^0-9.]", "")
        );
    }
}
