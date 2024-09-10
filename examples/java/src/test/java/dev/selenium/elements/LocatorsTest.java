package dev.selenium.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import dev.selenium.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsTest extends BaseTest {

    public String ByChainedTest() 
    {
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