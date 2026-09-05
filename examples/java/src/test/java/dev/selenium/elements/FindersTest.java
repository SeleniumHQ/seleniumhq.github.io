package dev.selenium.elements;

import dev.selenium.BaseTest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindersTest extends BaseTest {

  private static final String LOCATORS_PAGE =
      "https://www.selenium.dev/selenium/web/locators_tests/locators.html";

  @Test
  public void findsFirstMatchingElement() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);
    WebElement firstInput = driver.findElement(By.className("information"));

    assertEquals("fname", firstInput.getAttribute("id"));
  }

  @Test
  public void findsElementWithinASubsetOfTheDom() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);
    WebElement form = driver.findElement(By.tagName("form"));
    WebElement input = form.findElement(By.className("information"));

    assertEquals("fname", input.getAttribute("id"));
  }

  @Test
  public void usesAnOptimizedLocator() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);
    WebElement input = driver.findElement(By.cssSelector("form .information"));

    assertEquals("fname", input.getAttribute("id"));
  }

  @Test
  public void findsAllMatchingElements() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);
    List<WebElement> inputs = driver.findElements(By.tagName("input"));

    assertTrue(inputs.size() > 1);
  }

  @Test
  public void getsElementFromACollection() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);

    List<WebElement> elements = driver.findElements(By.tagName("p"));
    for (WebElement element : elements) {
      System.out.println("Paragraph text:" + element.getText());
    }

    assertTrue(elements.size() > 0);
  }

  @Test
  public void findsElementsFromElement() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);

    WebElement form = driver.findElement(By.tagName("form"));
    List<WebElement> elements = form.findElements(By.tagName("input"));
    for (WebElement e : elements) {
      System.out.println(e.getAttribute("value"));
    }

    assertTrue(elements.size() > 0);
  }

  @Test
  public void getsActiveElement() {
    startChromeDriver();
    driver.get(LOCATORS_PAGE);

    driver.findElement(By.cssSelector("#fname")).sendKeys("webElement");
    String attr = driver.switchTo().activeElement().getAttribute("name");

    assertEquals("fname", attr);
  }
}
