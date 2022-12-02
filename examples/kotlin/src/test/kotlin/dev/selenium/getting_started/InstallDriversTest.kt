package dev.selenium.getting_started

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver


class InstallDriversTest {
    @Test
    @Disabled("Do not run in CI")
    fun chromeSession() {
        WebDriverManager.chromedriver().setup()
        val driver: WebDriver = ChromeDriver()
        driver.quit()
    }

    @Test
    @Disabled("Do not run in CI")
    fun edgeSession() {
        WebDriverManager.edgedriver().setup()
        val driver: WebDriver = EdgeDriver()
        driver.quit()
    }

    @Test
    @Disabled("Do not run in CI")
    fun firefoxSession() {
        WebDriverManager.firefoxdriver().setup()
        val driver: WebDriver = FirefoxDriver()
        driver.quit()
    }

    @Test
    @Disabled("Do not run in CI")
    fun ieSession() {
        WebDriverManager.iedriver().setup()
        val driver: WebDriver = InternetExplorerDriver()
        driver.quit()
    }
}