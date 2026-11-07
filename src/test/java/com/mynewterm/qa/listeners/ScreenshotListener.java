
package com.mynewterm.qa.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mynewterm.qa.base.BaseTest;

public class ScreenshotListener implements ITestListener {

    // Run automatically when a test fails
    @Override
    public void onTestFailure(ITestResult result) {

        // Get the test instance and its browser
        if (!(result.getInstance() instanceof BaseTest test)) {
            return;
        }

        WebDriver driver = test.getDriver();

        if (driver == null) {
            return;
        }

        try {
            // Create the screenshots folder if needed
            Path folder = Path.of("target", "screenshots");
            Files.createDirectories(folder);

            // Name the screenshot after the failed test
            String filename = result.getMethod().getMethodName()
                + "-" + System.currentTimeMillis() + ".png";

            Path screenshotPath = folder.resolve(filename);

            // Capture and save the screenshot
            byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

            Files.write(screenshotPath, screenshot);

            System.out.println(
                "Failure screenshot saved: " + screenshotPath
            );

        } catch (IOException | WebDriverException e) {
            System.err.println(
                "Could not capture failure screenshot: " + e.getMessage()
            );
        }
    }
}

