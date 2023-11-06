package dev.selenium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected File driverPath;
  protected File browserPath;

  public WebElement getLocatedElement(WebDriver driver, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> driver.findElement(by));
  }

  protected FirefoxDriver startFirefoxDriver() {
    return startFirefoxDriver(new FirefoxOptions());
  }

  protected FirefoxDriver startFirefoxDriver(FirefoxOptions options) {
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    driver = new FirefoxDriver(options);
    return (FirefoxDriver) driver;
  }

  protected ChromeDriver startChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    return startChromeDriver(options);
  }

  protected ChromeDriver startChromeDriver(ChromeOptions options) {
    driver = new ChromeDriver(options);
    return (ChromeDriver) driver;
  }

  protected File getTempDirectory(String prefix) {
    File tempDirectory = null;
    try {
      tempDirectory = Files.createTempDirectory(prefix).toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    tempDirectory.deleteOnExit();

    return tempDirectory;
  }

  protected File getTempFile(String prefix, String suffix) {
    File logLocation = null;
    try {
      logLocation = File.createTempFile(prefix, suffix);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    logLocation.deleteOnExit();

    return logLocation;
  }

  protected URL startStandaloneGrid() {
    int port = PortProber.findFreePort();
    try {
      Main.main(
          new String[] {
            "standalone",
            "--port",
            String.valueOf(port),
            "--selenium-manager",
            "true",
            "--enable-managed-downloads",
            "true",
            "--log-level",
            "WARNING"
          });
      return new URL("http://localhost:" + port);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  public void quit() {
    if (driver != null) {
      driver.quit();
    }
  }
}
