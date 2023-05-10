package dev.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;

public class BaseTest {
    public WebDriver driver;

    @BeforeAll
    public static void defaultLogging() {
        System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY, DriverService.LOG_NULL);
    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
