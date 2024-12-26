package dev.selenium.bidirectional.webdriver_bidi;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.BiDi;
import org.openqa.selenium.bidi.HasBiDi;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBiDiTest {
    @Test
    public void testFirefoxBiDi() {
        // Configure Firefox options with webSocketUrl capability
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:debuggerAddress", true); // Enables WebSocket-based debugging
        options.setCapability("webSocketUrl", true); // Explicitly request WebSocket URL

        WebDriver driver = new FirefoxDriver(options);

        try {
            // Establish a BiDi session
            BiDi biDi = ((HasBiDi) driver).getBiDi();
            System.out.println("BiDi session established for Firefox.");
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
