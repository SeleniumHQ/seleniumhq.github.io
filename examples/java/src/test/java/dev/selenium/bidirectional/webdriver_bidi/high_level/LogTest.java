package dev.selenium.bidirectional.webdriver_bidi.high_level;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import dev.selenium.BaseTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class LogTest extends BaseTest {

  @BeforeEach
  public void setup() {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("webSocketUrl", true);
    driver = new FirefoxDriver(options);
  }

  @Test
  void canAddConsoleMessageHandler()
      throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();

    long id = ((RemoteWebDriver) driver).script().addConsoleMessageHandler(future::complete);

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    driver.findElement(By.id("consoleLog")).click();

    ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

    Assertions.assertEquals("Hello, world!", logEntry.getText());

    ((RemoteWebDriver) driver).script().removeConsoleMessageHandler(id);
  }

  @Test
  void canRemoveConsoleMessageHandler() {
    CopyOnWriteArrayList<ConsoleLogEntry> logs = new CopyOnWriteArrayList<>();

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    long id = ((RemoteWebDriver) driver).script().addConsoleMessageHandler(logs::add);
    ((RemoteWebDriver) driver).script().removeConsoleMessageHandler(id);

    driver.findElement(By.id("consoleLog")).click();

    Assertions.assertEquals(0, logs.size());
  }

  @Test
  void canAddJsErrorHandler() throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();

    long id = ((RemoteWebDriver) driver).script().addJavaScriptErrorHandler(future::complete);

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    driver.findElement(By.id("jsException")).click();

    JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

    Assertions.assertEquals("Error: Not working", logEntry.getText());

    ((RemoteWebDriver) driver).script().removeJavaScriptErrorHandler(id);
  }

  @Test
  void canRemoveJsErrorHandler() {
    CopyOnWriteArrayList<JavascriptLogEntry> logs = new CopyOnWriteArrayList<>();

    long id = ((RemoteWebDriver) driver).script().addJavaScriptErrorHandler(logs::add);
    ((RemoteWebDriver) driver).script().removeJavaScriptErrorHandler(id);

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    driver.findElement(By.id("jsException")).click();

    Assertions.assertEquals(0, logs.size());
  }
}
