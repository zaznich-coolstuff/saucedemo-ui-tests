
package com.mynewterm.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;

public class BaseTest {

    // Browser instance shared with each test class
    protected WebDriver driver;

    // Start a fresh browser before each test
@BeforeMethod
public void setUp() {
    ChromeOptions options = new ChromeOptions();

    // Prevent Chrome password manager pop-ups during testing
    options.setExperimentalOption("prefs", Map.of(
        "credentials_enable_service", false,
        "profile.password_manager_enabled", false,
        "profile.password_manager_leak_detection", false
    ));

    driver = new ChromeDriver(options);
    driver.get("https://www.saucedemo.com/");
}

    // Close the browser after each test, even if it fails
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
