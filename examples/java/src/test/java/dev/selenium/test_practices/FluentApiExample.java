package dev.selenium.test_practices;

import java.time.Duration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class BasePage {
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }
}

class GoogleSearchPage extends BasePage {
  public GoogleSearchPage(WebDriver driver) {
    super(driver);
    // Generally do not assert within pages or components.
    // Effectively throws an exception if the lambda condition is not met.
    new WebDriverWait(driver, Duration.ofSeconds(3)).until(d -> d.findElement(By.id("logo")));
  }

  public GoogleSearchPage setSearchString(String sstr) {
    driver.findElement(By.id("gbqfq")).sendKeys(sstr);
    return this;
  }

  public void clickSearchButton() {
    driver.findElement(By.id("gbqfb")).click();
  }
}

public class FluentApiExample {

  @Test
  @Disabled("Illustrative only: targets Google's live, frequently changing search page markup")
  void queryTheGoogleSearchPageUsingAFluentApi() {
    WebDriver driver = new ChromeDriver();
    try {
      driver.get("http://www.google.com/webhp?hl=en&tab=ww");
      GoogleSearchPage gsp = new GoogleSearchPage(driver);
      gsp.setSearchString("cheese").clickSearchButton();
    } finally {
      driver.quit();
    }
  }
}
