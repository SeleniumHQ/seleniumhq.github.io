package dev.selenium.interactions

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class AlertsTest : BaseTest() {
    private lateinit var wait: WebDriverWait

    @BeforeEach
    fun setupWait() {
        wait = WebDriverWait(driver, Duration.ofSeconds(2))
    }

    @Test
    fun alertInformationTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html")

        // Click the link to activate the alert
        driver.findElement(By.id("alert")).click()

        // Wait for the alert to be displayed and store it in a variable
        val alert = wait.until(ExpectedConditions.alertIsPresent())

        // Store the alert text in a variable
        val text = alert.text

        // Press the OK button
        alert.accept()

        Assertions.assertEquals("cheese", text)
    }

    @Test
    fun confirmationAlertTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html")

        // Click the link to activate the alert
        driver.findElement(By.id("confirm")).click()

        // Wait for the alert to be displayed
        wait.until(ExpectedConditions.alertIsPresent())

        // Store the alert in a variable
        val alert = driver.switchTo().alert()

        // Store the alert in a variable for reuse
        val text = alert.text

        // Press the Cancel button
        alert.dismiss()

        Assertions.assertEquals("Are you sure?", text)
        Assertions.assertFalse(driver.currentUrl.endsWith("simpleTest.html"))
    }

    @Test
    fun promptDisplayAndInputTest() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html")

        // Click the link to activate the alert
        driver.findElement(By.id("prompt")).click()

        // Wait for the alert to be displayed and store it in a variable
        val alert = wait.until(ExpectedConditions.alertIsPresent())

        // Type your message
        alert.sendKeys("Selenium")

        // Press the OK button
        alert.accept()

        val textElement = driver.findElement(By.id("text"))
        Assertions.assertEquals("Selenium", textElement.text)
    }
}
