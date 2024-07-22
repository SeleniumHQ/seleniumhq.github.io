package dev.selenium.bidi.cdp;

import com.google.common.collect.ImmutableMap;
import dev.selenium.BaseTest;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v125.browser.Browser;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.Headers;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CdpApiTest extends BaseTest {
  DevTools devTools;

  @BeforeEach
  public void createSession() {
    ChromeOptions options = new ChromeOptions();
    options.setBrowserVersion("125");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @Test
  public void basicAuth() {
    devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    devTools.send(Network.enable(Optional.of(100000), Optional.of(100000), Optional.of(100000)));

    String encodedAuth = Base64.getEncoder().encodeToString("admin:admin".getBytes());
    Map<String, Object> headers = ImmutableMap.of("Authorization", "Basic " + encodedAuth);

    devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

    driver.get("https://the-internet.herokuapp.com/basic_auth");

    Assertions.assertEquals(
        "Congratulations! You must have the proper credentials.",
        driver.findElement(By.tagName("p")).getText());
  }

  @Test
  public void waitForDownload() {
    driver.get("https://www.selenium.dev/selenium/web/downloads/download.html");

    devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    devTools.send(
        Browser.setDownloadBehavior(
            Browser.SetDownloadBehaviorBehavior.ALLOWANDNAME,
            Optional.empty(),
            Optional.of(""),
            Optional.of(true)));

    AtomicBoolean completed = new AtomicBoolean(false);
    devTools.addListener(
        Browser.downloadProgress(),
        e -> completed.set(Objects.equals(e.getState().toString(), "completed")));

    driver.findElement(By.id("file-2")).click();

    Assertions.assertDoesNotThrow(() -> wait.until(_d -> completed));
  }
}
