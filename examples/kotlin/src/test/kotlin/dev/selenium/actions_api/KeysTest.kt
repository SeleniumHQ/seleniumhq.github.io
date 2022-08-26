package dev.selenium.actions_api

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.HasCapabilities
import org.openqa.selenium.Keys
import org.openqa.selenium.Platform
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class KeysTest : BaseTest() {

    @Test
    fun keyDown() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")

        Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .perform()

        val textField = driver.findElement(By.id("textInput"))
        Assertions.assertEquals("A", textField.getAttribute("value"))
    }

    @Test
    fun keyUp() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")

        Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .sendKeys("b")
                .perform()

        val textField = driver.findElement(By.id("textInput"))
        Assertions.assertEquals("Ab", textField.getAttribute("value"))
    }

    @Test
    fun sendKeysToActiveElement() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")

        Actions(driver)
                .sendKeys("abc")
                .perform()

        val textField = driver.findElement(By.id("textInput"))
        Assertions.assertEquals("abc", textField.getAttribute("value"))
    }

    @Test
    fun sendKeysToDesignatedElement() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")
        driver.findElement(By.tagName("body")).click()

        val textField = driver.findElement(By.id("textInput"))
        Actions(driver)
                .sendKeys(textField, "Selenium!")
                .perform()

        Assertions.assertEquals("Selenium!", textField.getAttribute("value"))
    }

    @Test
    fun copyAndPaste() {
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")

        val platformName = (driver as HasCapabilities).getCapabilities().getPlatformName()

        val cmdCtrl = if(platformName == Platform.MAC) Keys.COMMAND else Keys.CONTROL

        val textField = driver.findElement(By.id("textInput"))
        Actions(driver)
                .sendKeys(textField, "Selenium!")
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform()

        Assertions.assertEquals("SeleniumSelenium!", textField.getAttribute("value"))
    }
}
