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

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsTest extends BaseTest {

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
