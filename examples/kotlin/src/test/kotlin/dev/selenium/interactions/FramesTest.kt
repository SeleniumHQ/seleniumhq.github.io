package dev.selenium.interactions

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class FramesTest : BaseTest() {
    private val framesPage = "https://www.selenium.dev/selenium/web/iframes.html"

    @Test
    fun switchToFrameByElement() {
        driver.get(framesPage)

        // Store the web element
        val iframe = driver.findElement(By.id("iframe1"))

        // Switch to the frame
        driver.switchTo().frame(iframe)

        // Now we can type text into the email field
        val email = driver.findElement(By.id("email"))
        email.sendKeys("admin@selenium.dev")

        Assertions.assertEquals("admin@selenium.dev", email.getAttribute("value"))
        Assertions.assertTrue(driver.pageSource.orEmpty().contains("We Leave From Here"))
    }

    @Test
    fun switchToFrameByNameOrId() {
        driver.get(framesPage)

        // Using the ID
        driver.switchTo().frame("iframe1")
        driver.switchTo().defaultContent()

        // Or using the name instead
        driver.switchTo().frame("iframe1-name")

        // Now we can type text into the email field
        val email = driver.findElement(By.id("email"))
        email.sendKeys("admin@selenium.dev")

        Assertions.assertEquals("admin@selenium.dev", email.getAttribute("value"))
        Assertions.assertTrue(driver.pageSource.orEmpty().contains("We Leave From Here"))
    }

    @Test
    fun switchToFrameByIndex() {
        driver.get(framesPage)

        // Switches to the first frame
        driver.switchTo().frame(0)

        Assertions.assertTrue(driver.pageSource.orEmpty().contains("We Leave From Here"))
    }

    @Test
    fun leaveFrame() {
        driver.get(framesPage)
        driver.switchTo().frame(0)

        // Return to the top level
        driver.switchTo().defaultContent()

        Assertions.assertTrue(driver.pageSource.orEmpty().contains("This page has iframes"))
    }
}
