
package com.mynewterm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Identify elements on the login page
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // Set up this Page Object
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    // Enter credentials and submit the login form
    public void login(String username, String password) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).sendKeys(username);

        driver.findElement(passwordField)
              .sendKeys(password);

        wait.until(
            ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }
}
