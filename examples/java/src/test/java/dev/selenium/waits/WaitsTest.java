package dev.selenium.waits;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsTest extends BaseChromeTest {
    @Test
    public void fails() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("box0"));
        });
    }

    @Test
    public void sleep() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();

        Thread.sleep(1000);

        WebElement added = driver.findElement(By.id("box0"));

        Assertions.assertEquals("redbox", added.getDomAttribute("class"));
    }

    @Test
    public void implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();

        WebElement added = driver.findElement(By.id("box0"));

        Assertions.assertEquals("redbox", added.getDomAttribute("class"));
    }

    @Test
    public void explicit() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement revealed = driver.findElement(By.id("revealed"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        driver.findElement(By.id("reveal")).click();
        wait.until(d -> revealed.isDisplayed());

        revealed.sendKeys("Displayed");
        Assertions.assertEquals("Displayed", revealed.getDomProperty("value"));
    }

    @Test
    public void explicitWithOptions() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement revealed = driver.findElement(By.id("revealed"));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

        driver.findElement(By.id("reveal")).click();
        wait.until(d -> {
            revealed.sendKeys("Displayed");
            return true;
        });

        Assertions.assertEquals("Displayed", revealed.getDomProperty("value"));
    }
}
