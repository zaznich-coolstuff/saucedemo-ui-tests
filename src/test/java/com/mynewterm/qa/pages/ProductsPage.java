
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductsPage {

    private WebDriverWait wait;

    // Elements on the Products page
    private By pageHeading = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By firstProduct = By.cssSelector(".inventory_item:first-child");

    public ProductsPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // UI-01: Read the Products page heading
    public String getHeading() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageHeading)
        ).getText();
    }

    // UI-01: Check that products are visible
    public boolean hasProducts() {
        return !wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems)
        ).isEmpty();
    }

    // UI-02: Find the first product in the listing
    private WebElement getFirstProduct() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstProduct)
        );
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
}

