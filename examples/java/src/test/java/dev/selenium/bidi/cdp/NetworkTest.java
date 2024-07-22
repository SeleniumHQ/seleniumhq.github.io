package dev.selenium.bidi.cdp;

import com.google.common.net.MediaType;
import dev.selenium.BaseTest;
import java.net.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v124.browser.Browser;
import org.openqa.selenium.devtools.v124.network.Network;
import org.openqa.selenium.devtools.v124.performance.Performance;
import org.openqa.selenium.devtools.v124.performance.model.Metric;
import org.openqa.selenium.remote.http.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetworkTest extends BaseTest {

  @BeforeEach
  public void createSession() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @Test
  public void basicAuthentication() {
    Predicate<URI> uriPredicate = uri -> uri.toString().contains("herokuapp.com");
    Supplier<Credentials> authentication = UsernameAndPassword.of("admin", "admin");
    ((HasAuthentication) driver).register(uriPredicate, authentication);

    driver.get("https://the-internet.herokuapp.com/basic_auth");

    String successMessage = "Congratulations! You must have the proper credentials.";
    WebElement elementMessage = driver.findElement(By.tagName("p"));
    Assertions.assertEquals(successMessage, elementMessage.getText());
  }

  @Test
  public void recordResponse() {
    CopyOnWriteArrayList<String> contentType = new CopyOnWriteArrayList<>();

    try (NetworkInterceptor ignored =
        new NetworkInterceptor(
            driver,
            (Filter)
                next ->
                    req -> {
                      HttpResponse res = next.execute(req);
                      contentType.add(res.getHeader("Content-Type"));
                      return res;
                    })) {
      driver.get("https://www.selenium.dev/selenium/web/blank.html");
      wait.until(_d -> contentType.size() > 1);
    }

    Assertions.assertEquals("text/html; charset=utf-8", contentType.get(0));
  }

  @Test
  public void transformResponses() {
    try (NetworkInterceptor ignored =
        new NetworkInterceptor(
            driver,
            Route.matching(req -> true)
                .to(
                    () ->
                        req ->
                            new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                                .setContent(Contents.utf8String("Creamy, delicious cheese!"))))) {
      driver.get("https://www.selenium.dev/selenium/web/blank.html");
    }

    WebElement body = driver.findElement(By.tagName("body"));
    Assertions.assertEquals("Creamy, delicious cheese!", body.getText());
  }

  @Test
  public void interceptRequests() {
    AtomicBoolean completed = new AtomicBoolean(false);

    try (NetworkInterceptor ignored =
        new NetworkInterceptor(
            driver,
            (Filter)
                next ->
                    req -> {
                      if (req.getUri().contains("one.js")) {
                        req =
                            new HttpRequest(
                                HttpMethod.GET, req.getUri().replace("one.js", "two.js"));
                      }
                      completed.set(true);
                      return next.execute(req);
                    })) {
      driver.get("https://www.selenium.dev/selenium/web/devToolsRequestInterceptionTest.html");
      driver.findElement(By.tagName("button")).click();
    }

    Assertions.assertEquals("two", driver.findElement(By.id("result")).getText());
  }

  @Test
  public void performanceMetrics() {
    driver.get("https://www.selenium.dev/selenium/web/frameset.html");

    DevTools devTools = ((HasDevTools) driver).getDevTools();
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
  public void setCookie() {
    DevTools devTools = ((HasDevTools) driver).getDevTools();
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
  public void waitForDownload() {
    driver.get("https://www.selenium.dev/selenium/web/downloads/download.html");

    DevTools devTools = ((HasDevTools) driver).getDevTools();
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
