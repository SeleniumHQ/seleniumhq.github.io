package dev.selenium.bidirectional.chrome_devtools;

import com.google.common.collect.ImmutableMap;
import dev.selenium.BaseTest;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v118.browser.Browser;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.Headers;
import org.openqa.selenium.devtools.v118.performance.Performance;
import org.openqa.selenium.devtools.v118.performance.model.Metric;
import org.openqa.selenium.devtools.v118.runtime.Runtime;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CdpApiTest extends BaseTest {
  DevTools devTools;

  @BeforeEach
  public void createSession() {
    ChromeOptions options = new ChromeOptions();
    options.setBrowserVersion("118");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
  @Disabled("4.15 broke the casting")
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

    CopyOnWriteArrayList<String> logs = new CopyOnWriteArrayList<>();
    devTools.addListener(
        Runtime.consoleAPICalled(),
        event -> logs.add((String) event.getArgs().get(0).getValue().orElse("")));

    driver.findElement(By.id("consoleLog")).click();

    wait.until(_d -> !logs.isEmpty());
    Assertions.assertEquals("Hello, world!", logs.get(0));
  }

  @Test
  public void jsErrors() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    devTools.send(Runtime.enable());

    CopyOnWriteArrayList<JavascriptException> errors = new CopyOnWriteArrayList<>();
    devTools.getDomains().events().addJavascriptExceptionListener(errors::add);

    driver.findElement(By.id("jsException")).click();

    wait.until(_d -> !errors.isEmpty());
    Assertions.assertTrue(errors.get(0).getMessage().contains("Error: Not working"));
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
