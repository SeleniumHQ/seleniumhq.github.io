package dev.selenium.elements;

import dev.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class LocatorsTest extends BaseTest {

    public void findElementByClassName() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by class name
        WebElement element = driver.findElement(By.className("information"));
    }

    public void findElementByCssSelector() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by css selector
        WebElement element = driver.findElement(By.cssSelector("#fname"));
    }

    public void findElementById() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by id
        WebElement element = driver.findElement(By.id("lname"));
    }

    public void findElementByName() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by name
        WebElement element = driver.findElement(By.name("newsletter"));
    }

    public void findElementByLinkText() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by link text
        WebElement element = driver.findElement(By.linkText("Selenium Official Page"));
    }

    public void findElementByPartialLinkText() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by partial link text
        WebElement element = driver.findElement(By.partialLinkText("Official Page"));
    }

    public void findElementByTagName() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by tag name
        WebElement element = driver.findElement(By.tagName("a"));
    }

    public void findElementByXpath() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Find element by xpath
        WebElement element = driver.findElement(By.xpath("//input[@value='f']"));
    }

    public void findElementUsingBy() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

        // Define locators using different strategies shared above
        By informationLocator = By.className("information");
        By firstNameLocator = By.cssSelector("#fname");
        By lastNameLocator = By.id("lname");
        By newsletterLocator = By.name("newsletter");
        By linkTextLocator = By.linkText("Selenium Official Page");
        By partialLinkTextLocator = By.partialLinkText("Official Page");
        By tagNameLocator = By.tagName("a");
        By xpathLocator = By.xpath("//input[@value='f']");

        // Now we can directly use them in driver.findElement()
        WebElement informationElement = driver.findElement(informationLocator);
        WebElement firstNameElement = driver.findElement(firstNameLocator);
        WebElement lastNameElement = driver.findElement(lastNameLocator);
        WebElement newsletterElement = driver.findElement(newsletterLocator);
        WebElement linkTextElement = driver.findElement(linkTextLocator);
        WebElement partialLinkTextElement = driver.findElement(partialLinkTextLocator);
        WebElement tagNameElement = driver.findElement(tagNameLocator);
        WebElement xpathElement = driver.findElement(xpathLocator);
    }

    public void ByAllTest() {
        // Create instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/login.html");

        // get both logins
        By example = new ByAll(By.id("password-field"), By.id("username-field"));
        List<WebElement> login_inputs = driver.findElements(example);

        //send them both input
        login_inputs.get(0).sendKeys("username");
        login_inputs.get(1).sendKeys("password");
    }

    public String ByChainedTest() {
        // Create instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/login.html");

        // Find username-field inside of login-form
        By example = new ByChained(By.id("login-form"), By.id("username-field"));
        WebElement username_input = driver.findElement(example);

        //return placeholder text
        String placeholder = username_input.getAttribute("placeholder");
        return placeholder;
    }
}
