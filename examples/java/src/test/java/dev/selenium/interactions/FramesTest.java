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

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesTest{

    @Test
    public void informationWithElements() {
    	
    	 WebDriver driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        
         // Navigate to Url
         driver.get("https://www.selenium.dev/selenium/web/iframes.html");
        
         
         //switch To IFrame using Web Element
         WebElement iframe = driver.findElement(By.id("iframe1"));
         //Switch to the frame
         driver.switchTo().frame(iframe);
         assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
         //Now we can type text into email field
         WebElement emailE = driver.findElement(By.id("email"));
         emailE.sendKeys("admin@selenium.dev");
         emailE.clear();
         driver.switchTo().defaultContent();
       
         
         //switch To IFrame using name or id
         driver.findElement(By.name("iframe1-name"));
         //Switch to the frame
         driver.switchTo().frame(iframe);
         assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
         WebElement email = driver.findElement(By.id("email"));
         //Now we can type text into email field
         email.sendKeys("admin@selenium.dev");
         email.clear();
         driver.switchTo().defaultContent();
     
         
         //switch To IFrame using index
         driver.switchTo().frame(0);
         assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
         
         //leave frame
         driver.switchTo().defaultContent();
         assertEquals(true, driver.getPageSource().contains("This page has iframes"));
         
         //quit the browser
         driver.quit();
    }

}
