// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package dev.selenium.interactions;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsTest extends BaseTest {

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

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("cheese", alert.getText());
        alert.accept();

    }

    @Test
    public void alertEmptyInformationTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("empty-alert")).click();


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

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Enter something", alert.getText());
        alert.accept();
    }

    @Test
    public void multiplePromptInputsTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");
        driver.findElement(By.id("double-prompt")).click();

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

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Slow", alert.getText());

        alert.accept();

    }


    @Test
    public void confirmationAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html#");

        driver.findElement(By.id("confirm")).click();

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


        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("framed cheese", alert.getText());

        alert.accept();

    }

    @Test
    public void testForAlerts() {

        ChromeOptions chromeOptions = getDefaultChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('Sample Alert');");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        assertEquals("Sample Alert", alert.getText());
        alert.accept();

        js.executeScript("confirm('Are you sure?');");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();
        assertEquals("Are you sure?", alert.getText());
        alert.dismiss();

        js.executeScript("prompt('What is your name?');");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertEquals("What is your name?", alert.getText());
        alert.sendKeys("Selenium");
        alert.accept();
        driver.quit();
    }
}
