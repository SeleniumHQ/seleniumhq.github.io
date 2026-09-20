package dev.selenium.elements

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FindersTest : BaseTest() {

    private val locatorsPage = "https://www.selenium.dev/selenium/web/locators_tests/locators.html"

    @Test
    fun findsFirstMatchingElement() {
        driver.get(locatorsPage)
        val firstInput = driver.findElement(By.className("information"))

        assertEquals("fname", firstInput.getAttribute("id"))
    }

    @Test
    fun findsElementWithinASubsetOfTheDom() {
        driver.get(locatorsPage)
        val form = driver.findElement(By.tagName("form"))
        val input = form.findElement(By.className("information"))

        assertEquals("fname", input.getAttribute("id"))
    }

    @Test
    fun usesAnOptimizedLocator() {
        driver.get(locatorsPage)
        val input = driver.findElement(By.cssSelector("form .information"))

        assertEquals("fname", input.getAttribute("id"))
    }

    @Test
    fun findsAllMatchingElements() {
        driver.get(locatorsPage)
        val inputs = driver.findElements(By.tagName("input"))

        assertTrue(inputs.size > 1)
    }

    @Test
    fun getsElementFromACollection() {
        driver.get(locatorsPage)
        val elements = driver.findElements(By.tagName("p"))
        for (element in elements) {
            println("Paragraph text:" + element.text)
        }

        assertTrue(elements.isNotEmpty())
    }

    @Test
    fun findsElementsFromElement() {
        driver.get(locatorsPage)
        val form = driver.findElement(By.tagName("form"))
        val elements = form.findElements(By.tagName("input"))
        for (e in elements) {
            println(e.getAttribute("value"))
        }

        assertTrue(elements.isNotEmpty())
    }

    @Test
    fun getsActiveElement() {
        driver.get(locatorsPage)
        driver.findElement(By.cssSelector("#fname")).sendKeys("webElement")
        val attr = driver.switchTo().activeElement().getAttribute("name")

        assertEquals("fname", attr)
    }

    @BeforeEach
    fun configureImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
    }
}
