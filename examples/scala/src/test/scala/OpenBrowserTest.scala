import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.edge.{EdgeDriver, EdgeOptions}
import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions}
import org.openqa.selenium.ie.{InternetExplorerDriver, InternetExplorerOptions}
import org.openqa.selenium.safari.{SafariDriver, SafariOptions}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.Eventually
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser

class OpenBrowserTest extends AnyFlatSpec with BeforeAndAfterAll with Matchers with Eventually with WebBrowser {

  it should " be able to launch chrome" in {
    val chromeOptions = new ChromeOptions()
    val chromeDriver = new ChromeDriver(chromeOptions)
    chromeDriver.quit()
  }

  it should " be able to launch firefox" in {
    val firefoxOptions = new FirefoxOptions()
    val firefoxDriver = new FirefoxDriver(firefoxOptions)
    firefoxDriver.quit()
  }

  it should " be able to launch edge" in {
    val edgeOptions = new EdgeOptions()
    val edgeDriver = new EdgeDriver(edgeOptions)
    edgeDriver.quit()
  }

  ignore should " be able to launch ie" in {
    val ieOptions = new InternetExplorerOptions()
    ieOptions.attachToEdgeChrome()
    ieOptions.withEdgeExecutablePath("/path/to/ie/browser")
    val ieDriver = new InternetExplorerDriver(ieOptions)
    ieDriver.quit()
  }

  ignore should " be able to launch opera" in {
    val chromeOptions = new ChromeOptions()
    chromeOptions.setBinary("/path/to/opera/browser")
    val ieDriver = new ChromeDriver(chromeOptions)
    ieDriver.quit()
  }

  ignore should " be able to launch safari" in {
    val safariOptions = new SafariOptions()
    val safariDriver = new SafariDriver(safariOptions)
    safariDriver.quit()
  }

}
