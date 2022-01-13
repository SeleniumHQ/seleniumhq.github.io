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
    fun chromeSession() {
        WebDriverManager.chromedriver().setup()
        val driver: WebDriver = ChromeDriver()
        driver.quit()
    }

    @Test
    fun edgeSession() {
        WebDriverManager.edgedriver().setup()
        val driver: WebDriver = EdgeDriver()
        driver.quit()
    }

    @Test
    fun firefoxSession() {
        WebDriverManager.firefoxdriver().setup()
        val driver: WebDriver = FirefoxDriver()
        driver.quit()
    }

    @Disabled("Only runs on Windows")
    @Test
    fun ieSession() {
        WebDriverManager.iedriver().setup()
        val driver: WebDriver = InternetExplorerDriver()
        driver.quit()
    }
}