
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class ProductsPage {

    private WebDriverWait wait;

    // Elements on the Products page
    private By pageHeading = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By firstProduct = By.cssSelector(".inventory_item:first-child");
    private By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    // UI-01: Read the Products page heading
    public String getHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageHeading)).getText();
    }

    // UI-01: Check that products are visible
    public boolean hasProducts() {
        return !wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems)).isEmpty();
    }

    // UI-02: Find the first product in the listing
    private WebElement getFirstProduct() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstProduct));
    }

    // Read the product information shown in the listing
    public String getFirstProductName() {
        return getFirstProduct()
                .findElement(By.className("inventory_item_name")).getText();
    }

    public String getFirstProductDescription() {
        return getFirstProduct()
                .findElement(By.className("inventory_item_desc")).getText();
    }

    public String getFirstProductPrice() {
        return getFirstProduct()
                .findElement(By.className("inventory_item_price")).getText();
    }

    // Open the selected product's details page
    public void openFirstProduct() {
        getFirstProduct()
                .findElement(By.className("inventory_item_name")).click();
    }

    // UI-03: Add a specific product to the cart by name
    public void addProductToCart(String productName) {

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems));

        // Find the product with the matching name
        for (WebElement product : products) {

            String name = product.findElement(
                    By.className("inventory_item_name")).getText();

            if (name.equals(productName)) {

                WebElement addButton = product.findElement(
                        By.tagName("button"));

                wait.until(
                        ExpectedConditions.elementToBeClickable(addButton)).click();

                return;
            }
        }

        // Fail clearly if the requested product doesn't exist
        throw new NoSuchElementException(
                "Product not found: " + productName);
    }

    // UI-03: Open the shopping cart
    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    // Read the price of a specific product by name
    public String getProductPrice(String productName) {

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems));

        for (WebElement product : products) {

            String name = product.findElement(
                    By.className("inventory_item_name")).getText();

            if (name.equals(productName)) {
                return product.findElement(
                        By.className("inventory_item_price")).getText();
            }
        }

        throw new NoSuchElementException(
                "Product not found: " + productName);
    }

}
