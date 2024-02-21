package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.Input;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

class ActionsTest extends BaseTest {
    private Input input;

    private String windowHandle;

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
        windowHandle = driver.getWindowHandle();
        input = new Input(driver);
    }

    @Test
    void canPerformInputActions() {
        driver.get("https://www.selenium.dev/selenium/web/formSelectionPage.html");

        List<WebElement> options = driver.findElements(By.tagName("option"));

        Actions actions = new Actions(driver);
        Actions selectThreeOptions =
                actions.click(options.get(1)).keyDown(Keys.SHIFT).click(options.get(3)).keyUp(Keys.SHIFT);

        input.perform(windowHandle, selectThreeOptions.getSequences());

        WebElement showButton = driver.findElement(By.name("showselected"));
        showButton.click();

        WebElement resultElement = driver.findElement(By.id("result"));
        Assertions.assertTrue(resultElement.getText().contains("roquefort parmigiano cheddar"));
        }

    @Test
    void canPerformReleaseAction() {
        driver.get("https://www.selenium.dev/selenium/web/bidi/release_action.html");

        WebElement inputTextBox = driver.findElement(By.id("keys"));

        Actions sendLowercase =
                new Actions(driver).keyDown(inputTextBox, "a").keyDown(inputTextBox, "b");

        input.perform(windowHandle, sendLowercase.getSequences());
        ((JavascriptExecutor) driver).executeScript("resetEvents()");

        input.release(windowHandle);

        List<Map<String, Object>> events =
                (List<Map<String, Object>>)
                        ((JavascriptExecutor) driver).executeScript("return allEvents.events");
        Assertions.assertEquals("KeyB", events.get(0).get("code"));
        Assertions.assertEquals("KeyA", events.get(1).get("code"));
        }
    }
