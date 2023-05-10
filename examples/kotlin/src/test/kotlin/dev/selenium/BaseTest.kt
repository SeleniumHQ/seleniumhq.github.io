package dev.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {
    lateinit var driver: WebDriver
    
    @BeforeEach
    fun setup() {
        driver = ChromeDriver()
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }
}
