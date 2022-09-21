package dev.selenium.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest {
    public ChromeDriver driver;

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void basicOptions() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }
}
