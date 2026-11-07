
package com.mynewterm.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BrowserSmokeTest {

    @Test
    public void shouldOpenSauceDemo() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
            );

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("user-name")
                )
            );

            Assert.assertTrue(
                driver.findElement(By.id("login-button"))
                      .isDisplayed()
            );

        } finally {
            driver.quit();
        }
    }
}
