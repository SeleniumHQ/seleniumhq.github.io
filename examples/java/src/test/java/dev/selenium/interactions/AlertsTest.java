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
    public void testForAlerts() throws Exception {

        ChromeOptions chromeOptions = getDefaultChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
        //Navigate to Url
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");

        //Simple Alert
        //Click the link to activate the alert
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //execute js for alert
        js.executeScript("alert('Sample Alert');");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        assertEquals(text, "Sample Alert");
        //Press the OK button
        alert.accept();

        //Confirm
        //execute js for confirm
        js.executeScript("confirm('Are you sure?');");
        //Wait for the alert to be displayed
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());


        alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        text = alert.getText();
        assertEquals(text, "Are you sure?");
        //Press the Cancel button
        alert.dismiss();

        //Prompt
        //execute js for prompt
        js.executeScript("prompt('What is your name?');");
        //Wait for the alert to be displayed and store it in a variable
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        text = alert.getText();
        assertEquals(text, "What is your name?");
        //Type your message
        alert.sendKeys("Selenium");
        //Press the OK button
        alert.accept();
        //quit the browser
        driver.quit();
    }
}