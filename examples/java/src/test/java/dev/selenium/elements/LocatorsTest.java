package dev.selenium.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import dev.selenium.BaseTest;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsTest extends BaseTest {


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
