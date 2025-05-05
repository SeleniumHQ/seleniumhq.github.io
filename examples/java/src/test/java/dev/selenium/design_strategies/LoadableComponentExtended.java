package com.example.webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static junit.framework.Assert.assertTrue;

public class EditIssue extends LoadableComponent<EditIssue> {

  private final WebDriver driver;
  
  // By default the PageFactory will locate elements with the same name or id
  // as the field. Since the issue_title element has an id attribute of "issue_title"
  // we don't need any additional annotations.
  private WebElement issue_title;
  
  // But we'd prefer a different name in our code than "issue_body", so we use the
  // FindBy annotation to tell the PageFactory how to locate the element.
  @FindBy(id = "issue_body") private WebElement body;
  
  public EditIssue(WebDriver driver) {
    this.driver = driver;
    
    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();
    assertTrue("Not on the issue entry page: " + url, url.endsWith("/new"));
  }

  public void setHowToReproduce(String howToReproduce) {
    WebElement field = driver.findElement(By.id("issue_form_repro-command"));
    clearAndType(field, howToReproduce);
  }

  public void setLogOutput(String logOutput) {
    WebElement field = driver.findElement(By.id("issue_form_logs"));
    clearAndType(field, logOutput);
  }

  public void setOperatingSystem(String operatingSystem) {
    WebElement field = driver.findElement(By.id("issue_form_operating-system"));
    clearAndType(field, operatingSystem);
  }

  public void setSeleniumVersion(String seleniumVersion) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-version"));
    clearAndType(field, logOutput);
  }

  public void setBrowserVersion(String browserVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-versions"));
    clearAndType(field, browserVersion);
  }

  public void setDriverVersion(String driverVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-driver-versions"));
    clearAndType(field, driverVersion);
  }

  public void setUsingGrid(String usingGrid) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-grid-version"));
    clearAndType(field, usingGrid);
  }

  public IssueList submit() {
    driver.findElement(By.cssSelector("button[type='submit']")).click();
    return new IssueList(driver);
  }

  private void clearAndType(WebElement field, String text) {
    field.clear();
    field.sendKeys(text);
  }
}

// EditIssue page = new EditIssue(driver).get();

// Further Updates

  // @Override
  // protected void load() {
  //   securedPage.get();

  //   driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
  // }
