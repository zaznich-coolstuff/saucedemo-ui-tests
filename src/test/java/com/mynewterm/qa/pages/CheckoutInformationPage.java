
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CheckoutInformationPage {

    private WebDriverWait wait;

    // Fields on the checkout information form
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutInformationPage(WebDriver driver) {
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Enter the customer's checkout information
    public void enterCustomerDetails(
        String firstName,
        String lastName,
        String postalCode
    ) {
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).sendKeys(firstName);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(lastNameField)
        ).sendKeys(lastName);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(postalCodeField)
        ).sendKeys(postalCode);
    }

    // Continue to the order overview
    public void continueToOverview() {
        wait.until(
            ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }
}
