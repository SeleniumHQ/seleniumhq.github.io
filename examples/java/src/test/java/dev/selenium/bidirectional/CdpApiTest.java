package dev.selenium.bidirectional;

import com.google.common.collect.ImmutableMap;
import dev.selenium.BaseTest;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.browser.Browser;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.Headers;
import org.openqa.selenium.devtools.v117.performance.Performance;
import org.openqa.selenium.devtools.v117.performance.model.Metric;
import org.openqa.selenium.devtools.v117.runtime.Runtime;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CdpApiTest extends BaseTest {
  DevTools devTools;

  @BeforeEach
  public void createSession() {
    driver = new ChromeDriver();
  }

  @Test
  public void setCookie() {
    devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    devTools.send(
        Network.setCookie(
            "cheese",
            "gouda",
            Optional.empty(),
            Optional.of("www.selenium.dev"),
            Optional.empty(),
            Optional.of(true),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()));

    driver.get("https://www.selenium.dev");
    Cookie cheese = driver.manage().getCookieNamed("cheese");
    Assertions.assertEquals("gouda", cheese.getValue());
  }

  @Test
  public void performanceMetrics() {
    driver.get("https://www.selenium.dev/selenium/web/frameset.html");

    devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    devTools.send(Performance.enable(Optional.empty()));

    List<Metric> metricList = devTools.send(Performance.getMetrics());

    Map<String, Number> metrics = new HashMap<>();
    for (Metric metric : metricList) {
      metrics.put(metric.getName(), metric.getValue());
    }

    Assertions.assertTrue(metrics.get("DevToolsCommandDuration").doubleValue() > 0);
    Assertions.assertEquals(12, metrics.get("Frames").intValue());
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
  public void consoleLogs() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    devTools.send(Runtime.enable());

    CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();
    devTools.addListener(
        Runtime.consoleAPICalled(),
        event -> messages.add((String) event.getArgs().get(0).getValue().orElse("")));

    ((JavascriptExecutor) driver).executeScript("console.log('I love cheese')");

    new WebDriverWait(driver, Duration.ofSeconds(5)).until(_d -> !messages.isEmpty());
    Assertions.assertEquals("I love cheese", messages.get(0));
  }

  @Test
  public void waitForDownload() throws InterruptedException {
    driver.get("https://www.selenium.dev/selenium/web/downloads/download.html");

    devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    int downloadCount = 0;
    devTools.send(
        Browser.setDownloadBehavior(
            Browser.SetDownloadBehaviorBehavior.ALLOWANDNAME,
            Optional.empty(),
            Optional.of(""),
            Optional.of(true)));

    CopyOnWriteArrayList<String> downloads = new CopyOnWriteArrayList<>();
    devTools.addListener(
        Browser.downloadProgress(),
        e -> {
          if (Objects.equals(e.getState().toString(), "completed")) {
            downloads.add(e.getGuid());
          }
        });

    driver.findElement(By.id("file-2")).click();

    Assertions.assertDoesNotThrow(() -> wait.until(_d -> downloads.size() > downloadCount));
  }

  @Test
  public void waitForPageLoad() throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.NONE);
    WebDriver fastLoadDriver = new ChromeDriver(options);

    devTools = ((HasDevTools) fastLoadDriver).getDevTools();
    devTools.createSession();
    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

    Map<String, Object> requests = new ConcurrentHashMap<>();
    devTools.addListener(
        Network.requestWillBeSent(), r -> requests.put(r.getRequestId().toString(), r));
    devTools.addListener(
        Network.responseReceived(), r -> requests.remove(r.getRequestId().toString()));

    fastLoadDriver.get("https://www.nytimes.com");

    long startTime = System.currentTimeMillis();
    int requestSize = requests.size();

    while ((System.currentTimeMillis() - startTime) < 2000) {
      System.out.println("Pending requests count: " + requests.size());
      if (requests.size() == requestSize) {
        Thread.sleep(200);
      } else {
        startTime = System.currentTimeMillis();
        requestSize = requests.size();
      }
    }
  }
}
