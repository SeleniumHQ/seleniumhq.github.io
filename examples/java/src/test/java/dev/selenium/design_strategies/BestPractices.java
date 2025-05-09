package com.example.webdriver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

class EditIssue {

  private final WebDriver driver;

  public EditIssue(WebDriver driver) {
    this.driver = driver;
  }

  public void setTitle(String title) {
    WebElement field = driver.findElement(By.id("issue_title"));
    clearAndType(field, title);
  }

  public void setBody(String body) {
    WebElement field = driver.findElement(By.id("issue_body"));
    clearAndType(field, body);
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
    clearAndType(field, seleniumVersion);
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

// class IssueList extends LoadableComponent<IssueList> {
//     private final WebDriver driver;

//     public IssueList(WebDriver driver) {
//         this.driver = driver;
//     }

// }

class EditIssueBetter extends LoadableComponent<EditIssueBetter> {

  private final WebDriver driver;
  
  // By default the PageFactory will locate elements with the same name or id
  // as the field. Since the issue_title element has an id attribute of "issue_title"
  // we don't need any additional annotations.
  private WebElement issue_title;
  
  // But we'd prefer a different name in our code than "issue_body", so we use the
  // FindBy annotation to tell the PageFactory how to locate the element.
  @FindBy(id = "issue_body") private WebElement body;
  
  public EditIssueBetter(WebDriver driver) {
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
    Assertions.assertTrue(url.endsWith("/new"), "Not on the issue entry page: " + url);
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
    clearAndType(field, seleniumVersion);
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

class ProjectPage extends LoadableComponent<ProjectPage> {

  private final WebDriver driver;
  private final String projectName;

  public ProjectPage(WebDriver driver, String projectName) {
    this.driver = driver;
    this.projectName = projectName;
  }

  @Override
  protected void load() {
    driver.get("http://" + projectName + ".googlecode.com/");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();

    Assertions.assertTrue(url.contains(projectName));
  }
}

class SecuredPage extends LoadableComponent<SecuredPage> {

  private final WebDriver driver;
  private final LoadableComponent<?> parent;
  private final String username;
  private final String password;

  public SecuredPage(WebDriver driver, LoadableComponent<?> parent, String username, String password) {
    this.driver = driver;
    this.parent = parent;
    this.username = username;
    this.password = password;
  }

  @Override
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

  @Override
  protected void isLoaded() throws Error {
    // If you're signed in, you have the option of picking a different login.
    // Let's check for the presence of that.

    try {
      WebElement div = driver.findElement(By.id("multilogin-dropdown"));
    } catch (NoSuchElementException e) {
      Assertions.fail("Cannot locate user name link");
    }
  }
}

// public class FooTest {
//   private EditIssue editIssue;

//   @Before
//   public void prepareComponents() {
//     WebDriver driver = new FirefoxDriver();

//     ProjectPage project = new ProjectPage(driver, "selenium");
//     SecuredPage securedPage = new SecuredPage(driver, project, "example", "top secret");
//     editIssue = new EditIssue(driver, securedPage);
//   }

//   @Test
//   public void demonstrateNestedLoadableComponents() {
//     editIssue.get();

//     editIssue.title.sendKeys('Title');
//     editIssue.body.sendKeys('What Happened');
//     editIssue.setHowToReproduce('How to Reproduce');
//     editIssue.setLogOutput('Log Output');
//     editIssue.setOperatingSystem('Operating System');
//     editIssue.setSeleniumVersion('Selenium Version');
//     editIssue.setBrowserVersion('Browser Version');
//     editIssue.setDriverVersion('Driver Version');
//     editIssue.setUsingGrid('I Am Using Grid');
//   }

// }

class ActionBot {
  private final WebDriver driver;

  public ActionBot(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void submit(By locator) {
    driver.findElement(locator).submit();
  }

  /** 
   * Type something into an input field. WebDriver doesn't normally clear these
   * before typing, so this method does that first. It also sends a return key
   * to move the focus out of the element.
   */
  public void type(By locator, String text) { 
    WebElement element = driver.findElement(locator);
    element.clear();
    element.sendKeys(text + "\n");
  }
}

class IssueList extends LoadableComponent<IssueList> {
  private final WebDriver driver;

  public IssueList(WebDriver driver) {
      this.driver = driver;
  }

  @Override
  protected void load() {
    return true;
  }

  @Override
  protected void isLoaded() throws Error {
    return true;
  }

}
