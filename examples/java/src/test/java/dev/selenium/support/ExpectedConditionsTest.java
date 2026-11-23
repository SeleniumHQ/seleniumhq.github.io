package dev.selenium.support;

import dev.selenium.BaseTest;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpectedConditionsTest extends BaseTest {
  @Test
  public void expectedCondition() {
    startChromeDriver(new ChromeOptions());
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
    WebElement revealed = driver.findElement(By.id("revealed"));
    driver.findElement(By.id("reveal")).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("revealed")));

    revealed.sendKeys("Displayed");
    Assertions.assertEquals("Displayed", revealed.getDomProperty("value"));
  }
}
