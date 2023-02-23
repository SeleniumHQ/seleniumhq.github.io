package dev.selenium.bidirectional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class BiDiTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }

    @Test
    void testNavigateAndListenToErrors()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptException(future::complete);

            BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

            NavigationResult info =
                    browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            Assertions.assertNotNull(browsingContext.getId());
            Assertions.assertNull(info.getNavigationId());
            Assertions.assertTrue(info.getUrl().contains("/bidi/logEntryAdded.html"));

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("jsException"))).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals("Error: Not working", logEntry.getText());
            Assertions.assertEquals("javascript", logEntry.getType());
        }
    }
}
