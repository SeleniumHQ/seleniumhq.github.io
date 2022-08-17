package dev.selenium.actions_api;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class KeysTest extends BaseTest {
    @Test
    public void keyDown() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        Assertions.assertEquals("A", textField.getAttribute("value"));
    }

    @Test
    public void keyUp() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .sendKeys("b")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        Assertions.assertEquals("Ab", textField.getAttribute("value"));
    }

    @Test
    public void sendKeysToActiveElement() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .sendKeys("abc")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        Assertions.assertEquals("abc", textField.getAttribute("value"));
    }

    @Test
    public void sendKeysToDesignatedElement() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");
        driver.findElement(By.tagName("body")).click();

        WebElement textField = driver.findElement(By.id("textInput"));
        new Actions(driver)
                .sendKeys(textField, "Selenium!")
                .perform();

        Assertions.assertEquals("Selenium!", textField.getAttribute("value"));
    }

    @Test
    public void copyAndPaste() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        Platform platformName = ((HasCapabilities) driver).getCapabilities().getPlatformName();

        Keys cmdCtrl = platformName.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        WebElement textField = driver.findElement(By.id("textInput"));
        new Actions(driver)
                .sendKeys(textField, "Selenium!")
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform();

        Assertions.assertEquals("SeleniumSelenium!", textField.getAttribute("value"));
    }
}
