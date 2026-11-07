
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductsPage {

    
    private WebDriverWait wait;

    // Elements on the Products page
    private By pageHeading = By.className("title");
    private By inventoryItems = By.className("inventory_item");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Read the Products page heading
    public String getHeading() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageHeading)
        ).getText();
    }

    // Check that products are visible
    public boolean hasProducts() {
        return !wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                inventoryItems
            )
        ).isEmpty();
    }
}
