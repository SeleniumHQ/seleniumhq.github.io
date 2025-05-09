package com.example.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecuredPage{

  private final WebDriver driver;
  // private final LoadableComponent<?> parent;
  private final String username;
  private final String password;

  public SecuredPage(WebDriver driver, String username, String password) {
    this.driver = driver;
    this.parent = parent;
    this.username = username;
    this.password = password;
  }

  
  protected void load() {
    parent.get();

    String originalUrl = driver.getCurrentUrl();

    // Sign in
    driver.get("https://www.google.com/accounts/ServiceLogin?service=code");
    driver.findElement(By.name("Email")).sendKeys(username);
    WebElement passwordField = driver.findElement(By.name("Passwd"));
    passwordField.sendKeys(password);
    passwordField.submit();

    // Now return to the original URL
    driver.get(originalUrl);
  }

  
  protected void isLoaded() throws Error {
    // If you're signed in, you have the option of picking a different login.
    // Let's check for the presence of that.

    try {
      WebElement div = driver.findElement(By.id("multilogin-dropdown"));
    } catch (NoSuchElementException e) {
      fail("Cannot locate user name link");
    }
  }
}