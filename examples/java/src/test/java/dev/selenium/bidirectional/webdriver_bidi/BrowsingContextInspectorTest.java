package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.BrowsingContextInspector;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContextInfo;
import org.openqa.selenium.bidi.browsingcontext.NavigationInfo;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.bidi.browsingcontext.UserPromptClosed;
import org.openqa.selenium.bidi.browsingcontext.UserPromptOpened;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class BrowsingContextInspectorTest extends BaseTest {
    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    void canListenToWindowBrowsingContextCreatedEvent()
        throws ExecutionException, InterruptedException, TimeoutException {
    try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
        CompletableFuture<BrowsingContextInfo> future = new CompletableFuture<>();

        inspector.onBrowsingContextCreated(future::complete);

        String windowHandle = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();

        BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);

        Assertions.assertEquals(windowHandle, browsingContextInfo.getId());
        }
    }

    @Test
    void canListenToTabBrowsingContextCreatedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<BrowsingContextInfo> future = new CompletableFuture<>();
            inspector.onBrowsingContextCreated(future::complete);

            String windowHandle = driver.switchTo().newWindow(WindowType.TAB).getWindowHandle();

            BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals(windowHandle, browsingContextInfo.getId());
        }
    }

    @Test
    void canListenToDomContentLoadedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
            inspector.onDomContentLoaded(future::complete);

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            context.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

            NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertTrue(navigationInfo.getUrl().contains("bidi/logEntryAdded"));
        }
    }

    @Test
    void canListenToBrowsingContextLoadedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
            inspector.onBrowsingContextLoaded(future::complete);

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            context.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

            NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertTrue(navigationInfo.getUrl().contains("bidi/logEntryAdded"));
        }
    }

    @Test
    void canListenToNavigationStartedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
            inspector.onNavigationStarted(future::complete);

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            context.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

            NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertTrue(navigationInfo.getUrl().contains("bidi/logEntryAdded"));
        }
    }

    @Test
    void canListenToFragmentNavigatedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<NavigationInfo> future = new CompletableFuture<>();

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            context.navigate("https://www.selenium.dev/selenium/web/linked_image.html", ReadinessState.COMPLETE);

            inspector.onFragmentNavigated(future::complete);

            context.navigate("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", ReadinessState.COMPLETE);

            NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertTrue(navigationInfo.getUrl().contains("linkToAnchorOnThisPage"));
        }
    }

    @Test
    void canListenToUserPromptOpenedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<UserPromptOpened> future = new CompletableFuture<>();

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            inspector.onUserPromptOpened(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/alerts.html");

            driver.findElement(By.id("alert")).click();

            UserPromptOpened userPromptOpened = future.get(5, TimeUnit.SECONDS);
            Assertions.assertEquals(context.getId(), userPromptOpened.getBrowsingContextId());
        }
    }

    @Test
    void canListenToUserPromptClosedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<UserPromptClosed> future = new CompletableFuture<>();

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
            inspector.onUserPromptClosed(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/alerts.html");

            driver.findElement(By.id("prompt")).click();

            context.handleUserPrompt(true, "selenium");

            UserPromptClosed userPromptClosed = future.get(5, TimeUnit.SECONDS);
            Assertions.assertEquals(context.getId(), userPromptClosed.getBrowsingContextId());
        }
    }

    @Test
    void canListenToBrowsingContextDestroyedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
            CompletableFuture<BrowsingContextInfo> future = new CompletableFuture<>();

            inspector.onBrowsingContextDestroyed(future::complete);

            String windowHandle = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();

            driver.close();

            BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);

            Assertions.assertEquals(windowHandle, browsingContextInfo.getId());
            Assertions.assertTrue(browsingContextInfo.getUrl().contains("about:blank"));
        }
    }
}
