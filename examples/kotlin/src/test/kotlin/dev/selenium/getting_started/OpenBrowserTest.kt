package dev.selenium.getting_started

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.safari.SafariOptions


class OpenBrowserTest {
    private lateinit var driver: WebDriver

    @Disabled("Only runs if chromedriver is in PATH")
    @Test
    fun chromeSession() {
        val options = ChromeOptions()
        driver = ChromeDriver(options)
        driver.quit()
    }

    @Disabled("Only runs if msedgedriver is in PATH")
    @Test
    fun edgeSession() {
        val options = EdgeOptions()
        driver = EdgeDriver(options)
        driver.quit()
    }

    @Disabled("Only runs if geckodriver is in PATH")
    @Test
    fun firefoxSession() {
        val options = FirefoxOptions()
        driver = FirefoxDriver(options)
        driver.quit()
    }

    @Disabled("Only runs on Windows")
    @Test
    fun internetExplorerSession() {
        val options = InternetExplorerOptions()
        driver = InternetExplorerDriver(options)
        driver.quit()
    }

    @Disabled("Only runs on Windows")
    @Test
    fun internetExplorerCompatibilitySession() {
        val options = InternetExplorerOptions()
        options.attachToEdgeChrome()
        options.withEdgeExecutablePath("/path/to/edge/browser")
        driver = InternetExplorerDriver(options)
        driver.quit()
    }

    @Disabled("Requires non-standard browser")
    @Test
    fun operaSession() {
        val options = ChromeOptions()
        options.setBinary("/path/to/opera/browser")
        driver = ChromeDriver(options)
        driver.quit()
    }

    @Disabled("Only runs on Mac")
    @Test
    fun safariSession() {
        val options = SafariOptions()
        driver = SafariDriver(options)
        driver.quit()
    }
}