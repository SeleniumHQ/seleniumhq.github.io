package dev.selenium.actions_api

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.RemoteWebDriver

import java.time.Duration

class ActionsTest : BaseTest() {

    @Test
    fun pause() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val start = System.currentTimeMillis()

        val clickable = driver.findElement(By.id("clickable"))
        Actions(driver)
            .moveToElement(clickable)
            .pause(Duration.ofSeconds(1))
            .clickAndHold()
            .pause(Duration.ofSeconds(1))
            .sendKeys("abc")
            .perform() 

        val duration = System.currentTimeMillis() - start
        Assertions.assertTrue(duration > 2000)
        Assertions.assertTrue(duration < 4000)
    }

    @Test
    fun releasesAll() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val clickable = driver.findElement(By.id("clickable"))
        val actions = Actions(driver)
        actions.clickAndHold(clickable)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .perform()

        (driver as RemoteWebDriver).resetInputState()

        actions.sendKeys("a").perform()
        Assertions.assertEquals("A", clickable.getAttribute("value").get(0).toString())
        Assertions.assertEquals("a", clickable.getAttribute("value").get(1).toString())
    }
}
