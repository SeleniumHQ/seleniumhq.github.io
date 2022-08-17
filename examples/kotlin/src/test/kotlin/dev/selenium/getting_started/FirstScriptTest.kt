package dev.selenium.getting_started

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstScriptTest {
    private lateinit var driver: WebDriver

    @BeforeAll
    fun setupAll() {
        WebDriverManager.chromedriver().setup()
    }

    @BeforeEach
    fun setup() {
        driver = ChromeDriver()
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun eightComponents() {
        driver.get("https://duckduckgo.com/")

        val title = driver.title
        assertTrue(title.contains("DuckDuckGo"))

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60))

        var searchBox = driver.findElement(By.name("q"))
        val searchButton = driver.findElement(By.id("search_button_homepage"))

        searchBox.sendKeys("Selenium")
        searchButton.click()

        searchBox = driver.findElement(By.name("q"))
        val value = searchBox.getAttribute("value")
        assertEquals("Selenium", value)
    }

}