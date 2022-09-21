package dev.selenium.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxTest {
    public FirefoxDriver driver;

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void basicOptions() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }
}
