import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.Eventually
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Milliseconds, Span}
import org.scalatestplus.selenium.WebBrowser

class FirstScriptTest extends AnyFlatSpec with BeforeAndAfterAll with Matchers with Eventually with WebBrowser {
  implicit val webDriver: ChromeDriver = {
    WebDriverManager.chromedriver().setup()
    new ChromeDriver()
  }

  it should "be able to search on google" in {
    go to "https://google.com"
    pageTitle should be("Google")
    implicitlyWait(Span(500, Milliseconds))
    textField("q").value = "Selenium"
    click on name("btnK")
    eventually { textField("q").value should be ("Selenium") }
  }

  override def afterAll(): Unit = {
    quit()
  }

}
