package dev.selenium.bidirectional;

import com.google.common.io.Resources;
import com.google.common.net.MediaType;
import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.events.ConsoleEvent;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.remote.http.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.openqa.selenium.devtools.events.CdpEventTypes.consoleEvent;
import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

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
  public void pinScript() throws IOException {
    driver.get("https://www.selenium.dev/selenium/web/javascriptPage.html");
    WebElement visibleElement = driver.findElement(By.id("visibleSubElement"));
    WebElement hiddenElement = driver.findElement(By.id("hiddenlink"));

    URL resource = getClass().getResource("/org/openqa/selenium/remote/isDisplayed.js");
    String function = Resources.toString(Objects.requireNonNull(resource), StandardCharsets.UTF_8);
    String format = "/* isDisplayed */return (%s).apply(null, arguments);";
    String isDisplayedScript = String.format(format, function);

    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    ScriptKey displayedKey = jsExecutor.pin(isDisplayedScript);

    Object isVisibleDisplayed = jsExecutor.executeScript(displayedKey, visibleElement);
    Object isHiddenDisplayed = jsExecutor.executeScript(displayedKey, hiddenElement);

    Assertions.assertTrue((Boolean) isVisibleDisplayed);
    Assertions.assertFalse((Boolean) isHiddenDisplayed);
  }

  @Test
  public void mutationObservation() {
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

    CopyOnWriteArrayList<DomMutationEvent> mutations = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(domMutation(mutations::add));

    driver.findElement(By.id("reveal")).click();

    wait.until(_d -> !mutations.isEmpty());
    Assertions.assertEquals(mutations.get(0).getElement(), driver.findElement(By.id("revealed")));
  }

  @Test
  public void consoleLogs() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    CopyOnWriteArrayList<ConsoleEvent> logs = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(consoleEvent(logs::add));

    driver.findElement(By.id("consoleLog")).click();

    wait.until(_d -> !logs.isEmpty());
    Assertions.assertEquals("Hello, world!", logs.get(0).getMessages().get(0));
  }

  @Test
  public void consoleError() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    CopyOnWriteArrayList<ConsoleEvent> logs = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(consoleEvent(logs::add));

    driver.findElement(By.id("consoleError")).click();

    wait.until(_d -> !logs.isEmpty());
    Assertions.assertTrue(logs.get(0).getMessages().get(0).contains("I am console error"));
  }

  @Test
  public void jsErrors() {
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    CopyOnWriteArrayList<ConsoleEvent> logs = new CopyOnWriteArrayList<>();
    ((HasLogEvents) driver).onLogEvent(consoleEvent(logs::add));

    driver.findElement(By.id("jsException")).click();

    wait.until(_d -> !logs.isEmpty());
    Assertions.assertTrue(logs.get(0).getMessages().get(0).contains("I am console error"));
  }

  @Test
  public void interceptResponses() {
    new NetworkInterceptor(
        driver,
        Route.matching(req -> true)
            .to(
                () ->
                    req ->
                        new HttpResponse()
                            .setStatus(200)
                            .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                            .setContent(Contents.utf8String("Creamy, delicious cheese!"))));

    driver.get("https://www.selenium.dev/selenium/web/blank.html");

    WebElement body = driver.findElement(By.tagName("body"));
    Assertions.assertEquals("Creamy, delicious cheese!", body.getText());
  }

  @Test
  public void interceptRequests() {}
}
