package dev.selenium.support;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SelectListTest extends BaseTest {

    @BeforeEach
    public void navigate() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
    }

    @Test
    public void selectOption() {
        WebElement selectElement = driver.findElement(By.name("selectomatic"));
        Select select = new Select(selectElement);

        WebElement twoElement = driver.findElement(By.cssSelector("option[value=two]"));
        WebElement fourElement = driver.findElement(By.cssSelector("option[value=four]"));
        WebElement countElement = driver.findElement(By.cssSelector("option[value='still learning how to count, apparently']"));

        select.selectByVisibleText("Four");
        Assertions.assertTrue(fourElement.isSelected());

        select.selectByValue("two");
        Assertions.assertTrue(twoElement.isSelected());

        select.selectByIndex(3);
        Assertions.assertTrue(countElement.isSelected());
    }

    @Test
    public void selectMultipleOption() {
        WebElement selectElement = driver.findElement(By.name("multi"));
        Select select = new Select(selectElement);

        WebElement hamElement = driver.findElement(By.cssSelector("option[value=ham]"));
        WebElement gravyElement = driver.findElement(By.cssSelector("option[value='onion gravy']"));
        WebElement eggElement = driver.findElement(By.cssSelector("option[value=eggs]"));
        WebElement sausageElement = driver.findElement(By.cssSelector("option[value='sausages']"));

        List<WebElement> optionElements = selectElement.findElements(By.tagName("option"));
        List<WebElement> optionList = select.getOptions();
        Assertions.assertEquals(optionElements, optionList);

        List<WebElement> selectedOptionList = select.getAllSelectedOptions();
        List<WebElement> expectedSelection = new ArrayList<WebElement>() {{
            add(eggElement);
            add(sausageElement);
        }};
        Assertions.assertEquals(expectedSelection, selectedOptionList);

        select.selectByValue("ham");
        select.selectByValue("onion gravy");
        Assertions.assertTrue(hamElement.isSelected());
        Assertions.assertTrue(gravyElement.isSelected());

        select.deselectByValue("eggs");
        select.deselectByValue("sausages");
        Assertions.assertFalse(eggElement.isSelected());
        Assertions.assertFalse(sausageElement.isSelected());
    }

    @Test
    public void disabledOption() {
        WebElement selectElement = driver.findElement(By.name("single_disabled"));
        Select select = new Select(selectElement);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            select.selectByValue("disabled");
        });
    }
}
