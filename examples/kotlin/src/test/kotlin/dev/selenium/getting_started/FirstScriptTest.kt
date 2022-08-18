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
        driver.get("https://www.selenium.dev/selenium/web/web-form.html")

        val title = driver.title
        assertEquals("Web form", title)

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

        var textBox = driver.findElement(By.name("my-text"))
        val submitButton = driver.findElement(By.cssSelector("button"))

        textBox.sendKeys("Selenium")
        submitButton.click()

        val message = driver.findElement(By.id("message"))
        val value = message.getText()
        assertEquals("Received!", value)
    }

}