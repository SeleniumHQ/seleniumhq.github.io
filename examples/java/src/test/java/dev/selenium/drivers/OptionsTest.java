package dev.selenium.drivers;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class OptionsTest extends BaseTest {

  @Test
  public void setPageLoadStrategyNormal() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setPageLoadStrategyEager() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setPageLoadStrategyNone() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setAcceptInsecureCerts() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setAcceptInsecureCerts(true);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }
}

