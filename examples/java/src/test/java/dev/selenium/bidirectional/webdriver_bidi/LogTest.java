package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;

import java.time.Duration;
import java.util.concurrent.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.bidi.log.LogLevel;
import org.openqa.selenium.bidi.log.StackTrace;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LogTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    public void jsErrors() {
        CopyOnWriteArrayList<ConsoleLogEntry> logs = new CopyOnWriteArrayList<>();

        try (LogInspector logInspector = new LogInspector(driver)) {
            logInspector.onConsoleEntry(logs::add);
        }

        driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
        driver.findElement(By.id("consoleLog")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(_d -> !logs.isEmpty());
        Assertions.assertEquals("Hello, world!", logs.get(0).getText());
    }

    @Test
    void testListenToConsoleLog() throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
            logInspector.onConsoleEntry(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("consoleLog")).click();

            ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Hello, world!", logEntry.getText());
            Assertions.assertNull(logEntry.getRealm());
            Assertions.assertEquals(1, logEntry.getArgs().size());
            Assertions.assertEquals("console", logEntry.getType());
            Assertions.assertEquals("log", logEntry.getMethod());
            Assertions.assertNull(logEntry.getStackTrace());
        }
    }

    @Test
    void testListenToJavascriptLog()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptLog(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("jsException")).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Error: Not working", logEntry.getText());
            Assertions.assertEquals("javascript", logEntry.getType());
            Assertions.assertEquals(LogLevel.ERROR, logEntry.getLevel());
        }
    }

    @Test
    void testListenToJavascriptErrorLog()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptException(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("jsException")).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Error: Not working", logEntry.getText());
            Assertions.assertEquals("javascript", logEntry.getType());
        }
    }

    @Test
    void testRetrieveStacktraceForALog()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptException(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("logWithStacktrace")).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            StackTrace stackTrace = logEntry.getStackTrace();
            Assertions.assertNotNull(stackTrace);
            Assertions.assertEquals(4, stackTrace.getCallFrames().size());
        }
    }

    @Test
    void testListenToLogsWithMultipleConsumers()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<JavascriptLogEntry> completableFuture1 = new CompletableFuture<>();
            logInspector.onJavaScriptLog(completableFuture1::complete);

            CompletableFuture<JavascriptLogEntry> completableFuture2 = new CompletableFuture<>();
            logInspector.onJavaScriptLog(completableFuture2::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("jsException")).click();

            JavascriptLogEntry logEntry = completableFuture1.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Error: Not working", logEntry.getText());
            Assertions.assertEquals("javascript", logEntry.getType());

            logEntry = completableFuture2.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Error: Not working", logEntry.getText());
            Assertions.assertEquals("javascript", logEntry.getType());
        }
    }
}
