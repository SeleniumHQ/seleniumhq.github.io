package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    @Disabled("Skipping extension related test")
    public void extensionOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("/path/to/extension.crx"));
        ChromeDriver driver = new ChromeDriver(options);
    }

}
