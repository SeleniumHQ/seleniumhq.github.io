package dev.selenium.test_practices;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

class AccountPage {
}

class LoginPage {
  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Takes a username and password, fills out the fields, and clicks "login".
   *
   * @return An instance of the AccountPage
   */
  public AccountPage loginAsUser(String username, String password) {
    WebElement loginField = driver.findElement(By.id("loginField"));
    loginField.clear();
    loginField.sendKeys(username);

    // Fill out the password field. The locator we're using is "By.id", and we should
    // have it defined elsewhere in the class.
    WebElement passwordField = driver.findElement(By.id("password"));
    passwordField.clear();
    passwordField.sendKeys(password);

    // Click the login button, which happens to have the id "submit".
    driver.findElement(By.id("submit")).click();

    // Create and return a new instance of the AccountPage (via the built-in Selenium
    // PageFactory).
    return PageFactory.initElements(driver, AccountPage.class);
  }
}

public class DomainSpecificLanguageExample {

  @Test
  @Disabled("Illustrative only: LoginPage targets a fictional application, not a live site")
  void loginAsUserAbstractsTheLoginFormFromTheTest() {
    WebDriver driver = new ChromeDriver();
    try {
      LoginPage loginPage = new LoginPage(driver);
      loginPage.loginAsUser("cbrown", "cl0wn3");
    } finally {
      driver.quit();
    }
  }
}
