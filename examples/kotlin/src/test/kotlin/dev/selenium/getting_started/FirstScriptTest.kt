package dev.selenium.getting_started

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstScriptTest {
    private lateinit var driver: WebDriver

    @Test
    fun eightComponents() {
        driver = ChromeDriver()

        driver.get("https://google.com")

        title = driver.title
        assertEquals("Google", title)

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

        var searchBox = driver.findElement(By.name("q"))
        val searchButton = driver.findElement(By.name("btnK"))

        searchBox.sendKeys("Selenium")
        searchButton.click()

        searchBox = driver.findElement(By.name("q"))
        val value = searchBox.getAttribute("value")
        assertEquals("Selenium", value)

        driver.quit()
    }
}