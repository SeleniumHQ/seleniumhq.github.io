package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChromeTest {
    public ChromeDriver driver;

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void basicOptions() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Test
    public void headlessOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
    }

    @Test
    public void keepBrowserOpen() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true);
        driver = new ChromeDriver(options);
    }

    @Test
    public void extensionOptions() {
        ChromeOptions options = new ChromeOptions();
        Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
        options.addExtensions(new File(path.toUri()));
        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

}
