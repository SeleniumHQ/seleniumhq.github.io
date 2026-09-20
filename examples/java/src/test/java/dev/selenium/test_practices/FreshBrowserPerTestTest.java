package dev.selenium.test_practices;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FreshBrowserPerTestTest extends BaseTest {

  @Test
  public void startsAFreshBrowserForTheTest() {
    WebDriver driver = new FirefoxDriver();
    this.driver = driver;
  }
}
