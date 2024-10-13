package dev.selenium.selenium_manager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class usage {

    @Test
    public void testSetupWithoutManager() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/documentation/selenium_manager/");
        driver.quit();
    }

    @Test
    public void testSetupWithManager() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/documentation/selenium_manager/");
        driver.quit();
    }
    
}
