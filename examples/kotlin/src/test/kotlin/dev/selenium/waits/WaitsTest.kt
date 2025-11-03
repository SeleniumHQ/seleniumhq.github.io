package dev.selenium.waits

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


class WaitsTest : BaseTest() {

    @Test
    fun implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2))
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html")
        driver.findElement(By.id("adder")).click()

        val added = driver.findElement(By.id("box0"))

        Assertions.assertEquals("redbox",added.getDomAttribute("class"))

    }

    @Test
    fun explicit() {

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html")
        val revealed = driver.findElement(By.id("revealed"))
        driver.findElement(By.id("reveal")).click()

        val wait =  WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until { revealed.isDisplayed }

        revealed.sendKeys("Displayed")
        Assertions.assertEquals("Displayed", revealed.getDomProperty("value"))
    }

    @Test
    fun explicitWithOptions() {

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html")

        val revealed = driver.findElement(By.id("revealed"))
        driver.findElement(By.id("reveal")).click()

        val wait: Wait<WebDriver?> =
            FluentWait(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException::class.java)

        wait.until {
            revealed.sendKeys("Displayed")
            true
        }

        Assertions.assertEquals("Displayed", revealed.getDomProperty("value"))
    }

}