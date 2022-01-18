import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.Eventually
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser

class InstallDriverTest extends AnyFlatSpec with BeforeAndAfterAll with Matchers with Eventually with WebBrowser {

  it should " be able to launch chrome" in {
    WebDriverManager.chromedriver().setup()
    val chromeDriver = new ChromeDriver()
    chromeDriver.quit()
  }

  it should " be able to launch firefox" in {
    WebDriverManager.firefoxdriver().setup()
    val firefoxDriver = new FirefoxDriver()
    firefoxDriver.quit()
  }

  it should " be able to launch edge" in {
    WebDriverManager.edgedriver().setup()
    val edgeDriver = new EdgeDriver()
    edgeDriver.quit()
  }

  ignore should " be able to launch ie" in {
    WebDriverManager.iedriver().setup()
    val ieDriver = new InternetExplorerDriver()
    ieDriver.quit()
  }

}
