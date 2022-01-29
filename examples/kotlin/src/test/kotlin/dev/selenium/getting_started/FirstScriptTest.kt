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

    @BeforeAll
    fun setupDriverBinary() {
        WebDriverManager.chromedriver().setup()
    }

    @BeforeEach
    fun setupBrowser() {
        driver = ChromeDriver()
    }

    @Test
    fun eightComponents() {
        driver.get("https://google.com")

        assertEquals("Google", driver.title)

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

        var searchBox = driver.findElement(By.name("q"))
        val searchButton = driver.findElement(By.name("btnK"))

        searchBox.sendKeys("Selenium")
        searchButton.click()

        searchBox = driver.findElement(By.name("q"))
        assertEquals("Selenium", searchBox.getAttribute("value"))
    }

    @AfterEach
    fun cleanupBrowser() {
        driver.quit()
    }
}