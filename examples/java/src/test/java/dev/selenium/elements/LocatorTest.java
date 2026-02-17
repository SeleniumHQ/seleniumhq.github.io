import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Initialize the driver before each test
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testClassName() {
        WebElement element = driver.findElement(By.className("information"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("input", element.getTagName());
    }

    @Test
    public void testCssSelector() {
        WebElement element = driver.findElement(By.cssSelector("#fname"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("Jane", element.getAttribute("value"));
    }

    @Test
    public void testId() {
        WebElement element = driver.findElement(By.id("lname"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("Doe", element.getAttribute("value"));
    }

    @Test
    public void testName() {
        WebElement element = driver.findElement(By.name("newsletter"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("input", element.getTagName());
    }

    @Test
    public void testLinkText() {
        WebElement element = driver.findElement(By.linkText("Selenium Official Page"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("https://www.selenium.dev/", element.getAttribute("href"));
    }

    @Test
    public void testPartialLinkText() {
        WebElement element = driver.findElement(By.partialLinkText("Official Page"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("https://www.selenium.dev/", element.getAttribute("href"));
    }

    @Test
    public void testTagName() {
        WebElement element = driver.findElement(By.tagName("a"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("https://www.selenium.dev/", element.getAttribute("href"));
    }

    @Test
    public void testXpath() {
        WebElement element = driver.findElement(By.xpath("//input[@value='f']"));
        Assertions.assertNotNull(element);
        Assertions.assertEquals("radio", element.getAttribute("type"));
    }
}