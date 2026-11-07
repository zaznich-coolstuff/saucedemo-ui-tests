
package com.mynewterm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {

    private WebDriverWait wait;

    // Elements on the order confirmation page
    private By pageHeading = By.className("title");
    private By confirmationHeading = By.className("complete-header");
    private By confirmationMessage = By.className("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Read the checkout completion page heading
    public String getPageHeading() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageHeading)
        ).getText();
    }

    // Read the order confirmation heading
    public String getConfirmationHeading() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmationHeading)
        ).getText();
    }

    // Read the order confirmation message
    public String getConfirmationMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmationMessage)
        ).getText();
    }
}
