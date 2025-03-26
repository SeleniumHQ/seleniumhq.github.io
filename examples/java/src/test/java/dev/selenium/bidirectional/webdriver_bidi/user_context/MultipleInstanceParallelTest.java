package dev.selenium.bidirectional.webdriver_bidi.user_context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class MultipleInstanceParallelTest {

  private WebDriver driver;

  @BeforeEach
  public void setup() {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("webSocketUrl", true);
    options.addArguments("-private");
    driver = new FirefoxDriver(options);
  }

  @Test
  void canSwitchToBlue() {
    driver.get("https://www.selenium.dev/selenium/web/cookie-background.html");

    WebElement body = driver.findElement(By.tagName("body"));
    String bgColor = body.getCssValue("background-color");

    String expectedColor = "rgb(255, 255, 255)";
    // Background color is white
    Assertions.assertEquals(bgColor, expectedColor);

    driver.get("https://www.selenium.dev/selenium/web/cookie-background.html");

    driver.findElement(By.id("blue-btn")).click();
    body = driver.findElement(By.tagName("body"));
    bgColor = body.getCssValue("background-color");

    expectedColor = "rgb(173, 216, 230)";
    // Background color is blue
    Assertions.assertEquals(bgColor, expectedColor);

    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @Test
  void canSwitchToGreen() {
    driver.get("https://www.selenium.dev/selenium/web/cookie-background.html");

    WebElement body = driver.findElement(By.tagName("body"));
    String bgColor = body.getCssValue("background-color");

    String expectedColor = "rgb(255, 255, 255)";
    Assertions.assertEquals(bgColor, expectedColor);

    driver.findElement(By.id("green-btn")).click();
    body = driver.findElement(By.tagName("body"));
    bgColor = body.getCssValue("background-color");

    expectedColor = "rgb(144, 238, 144)";
    Assertions.assertEquals(bgColor, expectedColor);

    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @Test
  void canHaveTheDefaultBackgroundColor() {
    driver.get("https://www.selenium.dev/selenium/web/cookie-background.html");

    WebElement body = driver.findElement(By.tagName("body"));
    String bgColor = body.getCssValue("background-color");

    String expectedColor = "rgb(255, 255, 255)";
    Assertions.assertEquals(bgColor, expectedColor);

    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @AfterEach
  public void cleanup() {
    driver.quit();
  }
}
