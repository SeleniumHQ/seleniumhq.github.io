package dev.selenium.bidirectional.chrome_devtools;

import static org.openqa.selenium.devtools.events.CdpEventTypes.consoleEvent;
import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

import com.google.common.net.MediaType;
import dev.selenium.BaseTest;
import java.net.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.remote.http.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BidiApiTest extends BaseTest {

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
  public void pinScript() {
    driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");
    WebElement element = driver.findElement(By.id("id1"));

    ScriptKey key = ((JavascriptExecutor) driver).pin("return arguments;");
    List<Object> arguments =
        (List<Object>) ((JavascriptExecutor) driver).executeScript(key, 1, true, element);

    Assertions.assertEquals(List.of(1L, true, element), arguments);
  }

  @Test
  public void mutatedElements() {
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

    CopyOnWriteArrayList<WebElement> mutations = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(domMutation(e -> mutations.add(e.getElement())));

    driver.findElement(By.id("reveal")).click();

    wait.until(_d -> !mutations.isEmpty());
    Assertions.assertEquals(mutations.get(0), driver.findElement(By.id("revealed")));
  }

  @Test
  public void consoleLogs() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(consoleEvent(e -> messages.add(e.getMessages().get(0))));

    driver.findElement(By.id("consoleLog")).click();
    driver.findElement(By.id("consoleError")).click();

    wait.until(_d -> messages.size() > 1);
    Assertions.assertTrue(messages.contains("Hello, world!"));
    Assertions.assertTrue(messages.contains("I am console error"));
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
  @Disabled("Not working yet")
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
}
