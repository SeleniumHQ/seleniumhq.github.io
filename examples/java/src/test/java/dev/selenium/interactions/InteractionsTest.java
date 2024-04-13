package dev.selenium.interactions;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import java.util.Set;

public class InteractionsTest extends BaseChromeTest {
  @Test
  public void getTitle() {
    try {
      driver.get("https://www.selenium.dev/");
      // get title
      String title = driver.getTitle();
      Assertions.assertEquals(title, "Selenium");
    } finally {
      driver.quit();
    }
  }
  @Test
  public void getCurrentUrl() {
    try {
      driver.get("https://www.selenium.dev/");
      // get current url
      String url = driver.getCurrentUrl();
      Assertions.assertEquals(url, "https://www.selenium.dev/");
    } finally {
      driver.quit();
    }
  }
}