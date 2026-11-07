
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriverWait wait;

    // Elements on the product details page
    private By productName = By.className("inventory_details_name");
    private By productDescription = By.className("inventory_details_desc");
    private By productPrice = By.className("inventory_details_price");
    private By addToCartButton = By.id("add-to-cart");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public ProductDetailsPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Read the product information
    public String getName() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(productName)
        ).getText();
    }

    public String getDescription() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(productDescription)
        ).getText();
    }

    public String getPrice() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).getText();
    }

    // Add the product to the cart
    public void addToCart() {
        wait.until(
            ExpectedConditions.elementToBeClickable(addToCartButton)
        ).click();
    }

    // Read the number displayed on the cart badge
    public String getCartBadgeCount() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    // Open the shopping cart
    public void openCart() {
        wait.until(
            ExpectedConditions.elementToBeClickable(cartLink)
        ).click();
    }
}
