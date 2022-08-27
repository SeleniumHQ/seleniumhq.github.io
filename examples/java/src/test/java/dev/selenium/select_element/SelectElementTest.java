package dev.selenium.select_element;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class SelectElementTest extends BaseTest {
     @Test
    public void selectByVisibleText() {
        driver.get("https://www.selenium.dev/selenium/web/selectPage.html");
        Select dropDown  = new Select(driver.findElement(By.id("selectWithoutMultiple")));
        dropDown.selectByVisibleText("two");
        Assertions.assertEquals("two",dropDown.getFirstSelectedOption().getText());
        }
}
