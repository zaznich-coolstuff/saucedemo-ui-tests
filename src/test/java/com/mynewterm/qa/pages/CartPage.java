
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.ArrayList;

import java.time.Duration;

public class CartPage {

    private WebDriverWait wait;

    // Elements on the shopping cart page
    private By cartItem = By.className("cart_item");
    private By productName = By.className("inventory_item_name");
    private By productDescription = By.className("inventory_item_desc");
    private By productPrice = By.className("inventory_item_price");
    private By productQuantity = By.className("cart_quantity");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Wait for a product to appear in the cart
    private WebElement getCartItem() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(cartItem)
        );
    }

    // Read the product information displayed in the cart
    public String getItemName() {
        return getCartItem()
            .findElement(productName).getText();
    }

    public String getItemDescription() {
        return getCartItem()
            .findElement(productDescription).getText();
    }

    public String getItemPrice() {
        return getCartItem()
            .findElement(productPrice).getText();
    }

    public String getItemQuantity() {
        return getCartItem()
            .findElement(productQuantity).getText();
    }

    // UI-03: Get the names of all products in the cart
    public List<String> getCartItemNames() {

        // Wait for the cart items to become visible
        List<WebElement> items = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItem)
        );

        List<String> names = new ArrayList<>();

        // Read the name of each product
        for (WebElement item : items) {
            names.add(
                item.findElement(productName).getText()
            );
        }

        return names;
    }

    // UI-03: Proceed from the cart to checkout
    public void proceedToCheckout() {
        wait.until(
            ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}
