
package com.mynewterm.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {

    private WebDriverWait wait;

    // Elements on the order overview page
    private By cartItems = By.className("cart_item");
    private By productName = By.className("inventory_item_name");

    private By subtotal = By.className("summary_subtotal_label");
    private By tax = By.className("summary_tax_label");
    private By total = By.className("summary_total_label");

    private By summaryValues = By.className("summary_value_label");
    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Read the product names in the order
    public List<String> getItemNames() {
        List<WebElement> items = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)
        );

        List<String> names = new ArrayList<>();

        for (WebElement item : items) {
            names.add(
                item.findElement(productName).getText()
            );
        }

        return names;
    }

    // Read the order's financial summary
    public String getSubtotal() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(subtotal)
        ).getText();
    }

    public String getTax() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(tax)
        ).getText();
    }

    public String getTotal() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(total)
        ).getText();
    }

    // Read the payment and shipping information
    public String getPaymentInformation() {
        return wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(summaryValues)
        ).get(0).getText();
    }

    public String getShippingInformation() {
        return wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(summaryValues)
        ).get(1).getText();
    }

    // Complete the purchase
    public void finishOrder() {
        wait.until(
            ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }
}
