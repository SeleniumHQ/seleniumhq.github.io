package dev.selenium.browsers;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirefoxTest extends BaseTest {
    public FirefoxDriver driver;

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void basicOptions() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    @Test
    public void installAddon() {
        driver = new FirefoxDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
        driver.installExtension(xpiPath);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void uninstallAddon() {
        driver = new FirefoxDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
        String id = driver.installExtension(xpiPath);
        driver.uninstallExtension(id);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        Assertions.assertEquals(driver.findElements(By.id("webextensions-selenium-example")).size(), 0);
    }

    @Test
    public void installUnsignedAddonPath() {
        driver = new FirefoxDriver();
        Path path = Paths.get("src/test/resources/extensions/selenium-example");
        driver.installExtension(path, true);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = getLocatedElement(driver, By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void headlessOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        driver = new FirefoxDriver(options);
    }    
}
