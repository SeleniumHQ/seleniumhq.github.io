package dev.selenium.grid;

import dev.selenium.BaseTest;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GettingStartedTest extends BaseTest {

  @Test
  public void addsMetadataToASession() throws Exception {
    URL gridUrl = startStandaloneGrid();

    ChromeOptions chromeOptions = getDefaultChromeOptions();
    // Showing a test name instead of the session id in the Grid UI
    chromeOptions.setCapability("se:name", "My simple test");
    // Other type of metadata can be seen in the Grid UI by clicking on the
    // session info or via GraphQL
    chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
    WebDriver driver = new RemoteWebDriver(gridUrl, chromeOptions);
    this.driver = driver;
    driver.get("https://www.selenium.dev");
  }
}
