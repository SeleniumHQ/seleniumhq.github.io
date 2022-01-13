import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration


class FirstScriptTest {

    @Test
    fun eightComponents() {
        val driver: WebDriver?
        driver = ChromeDriver()
        driver.get("https://google.com")
        Assertions.assertEquals("Google", driver.getTitle())
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
        var searchBox = driver.findElement(By.name("q"))
        val searchButton = driver.findElement(By.name("btnK"))
        searchBox.sendKeys("Selenium")
        searchButton.click()
        searchBox = driver.findElement(By.name("q"))
        Assertions.assertEquals("Selenium", searchBox.getAttribute("value"))
        driver.quit()
    }
}