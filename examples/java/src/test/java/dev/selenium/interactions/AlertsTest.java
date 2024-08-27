package dev.selenium.interactions;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsTest extends BaseTest {

    private AlertsTest() {
    };

    @BeforeEach
    public void createSession() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterEach
    public void endSession() {
        driver.quit();
    }

    @Test
    public void alertInformationTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");

        driver.findElement(By.id("alert")).click();
        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("cheese", alert.getText());
        alert.accept();

    }

    @Test
    public void alertEmptyInformationTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("empty-alert")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("", alert.getText());
        alert.accept();

    }

    @Test
    public void promptDisplayAndInputTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("prompt")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Enter something", alert.getText());

        alert.sendKeys("Selenium");
        alert.accept();

    }

    @Test
    public void promptDefaultInputTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");

        driver.findElement(By.id("prompt-with-default")).click();
        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Enter something", alert.getText());
        alert.accept();
        // Implementation needed to check teh default value is accepted.

    }

    @Test
    public void multiplePromptInputsTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("double-prompt")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert1 = driver.switchTo().alert();
        Assertions.assertEquals("First", alert1.getText());

        alert1.sendKeys("first");
        alert1.accept();


        Alert alert2 = driver.switchTo().alert();
        Assertions.assertEquals("Second", alert2.getText());
        alert2.sendKeys("second");
        alert2.accept();

    }

    @Test
    public void slowAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("slow-alert")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Slow", alert.getText());

        alert.accept();

    }


    @Test
    public void confirmationAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");

        driver.findElement(By.id("confirm")).click();
        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Are you sure?", alert.getText());

        alert.accept();
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("simpleTest.html"));

    }


    @Test
    public void iframeAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        WebElement iframe = driver.findElement(By.name("iframeWithAlert"));
        driver.switchTo().frame(iframe);

        driver.findElement(By.id("alertInFrame")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("framed cheese", alert.getText());

        alert.accept();

    }

    @Test
    public void nestedIframeAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        WebElement iframe1 = driver.findElement(By.name("iframeWithIframe"));
        driver.switchTo().frame(iframe1);

        WebElement iframe2 = driver.findElement(By.name("iframeWithAlert"));
        driver.switchTo().frame(iframe2);

        driver.findElement(By.id("alertInFrame")).click();

        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("framed cheese", alert.getText());

        alert.accept();

    }

}
