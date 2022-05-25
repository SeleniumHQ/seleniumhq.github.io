package dev.selenium.getting_started;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstScriptTest {
    public WebDriver driver;

    @Test
    public void eightComponents() {
        driver = new ChromeDriver();

        driver.get("https://google.com");

        String title = driver.getTitle();
        Assertions.assertEquals("Google", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        Assertions.assertEquals("Selenium", value);

        driver.quit();
    }
}
