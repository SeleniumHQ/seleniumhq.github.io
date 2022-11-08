package dev.selenium;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver driver;

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
