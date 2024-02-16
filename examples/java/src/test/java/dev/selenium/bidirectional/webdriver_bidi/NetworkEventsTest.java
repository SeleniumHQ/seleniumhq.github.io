package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.bidi.Network;
import org.openqa.selenium.bidi.network.BeforeRequestSent;
import org.openqa.selenium.bidi.network.ResponseDetails;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class NetworkEventsTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    void canListenToBeforeRequestSentEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Network network = new Network(driver)) {
            CompletableFuture<BeforeRequestSent> future = new CompletableFuture<>();
            network.onBeforeRequestSent(future::complete);
            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            BeforeRequestSent requestSent = future.get(5, TimeUnit.SECONDS);
            String windowHandle = driver.getWindowHandle();
            Assertions.assertEquals(windowHandle, requestSent.getBrowsingContextId());
            Assertions.assertEquals("get", requestSent.getRequest().getMethod().toLowerCase());
        }
    }

    @Test
    void canListenToResponseStartedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Network network = new Network(driver)) {
            CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
            network.onResponseStarted(future::complete);
            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            ResponseDetails response = future.get(5, TimeUnit.SECONDS);
            String windowHandle = driver.getWindowHandle();

            Assertions.assertEquals(windowHandle, response.getBrowsingContextId());
            Assertions.assertEquals("get", response.getRequest().getMethod().toLowerCase());
            Assertions.assertEquals(200L, response.getResponseData().getStatus());
        }
    }

    @Test
    void canListenToResponseCompletedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Network network = new Network(driver)) {
            CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
            network.onResponseCompleted(future::complete);
            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            ResponseDetails response = future.get(5, TimeUnit.SECONDS);
            String windowHandle = driver.getWindowHandle();

            Assertions.assertEquals(windowHandle, response.getBrowsingContextId());
            Assertions.assertEquals("get", response.getRequest().getMethod().toLowerCase());
            Assertions.assertEquals(200L, response.getResponseData().getStatus());
        }
    }

    @Test
    void canListenToResponseCompletedEventWithCookie()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Network network = new Network(driver)) {
            CompletableFuture<BeforeRequestSent> future = new CompletableFuture<>();

            driver.get("https://www.selenium.dev/selenium/web/blankPage");
            driver.manage().addCookie(new Cookie("foo", "bar"));
            network.onBeforeRequestSent(future::complete);
            driver.navigate().refresh();

            BeforeRequestSent requestSent = future.get(5, TimeUnit.SECONDS);
            String windowHandle = driver.getWindowHandle();

            Assertions.assertEquals(windowHandle, requestSent.getBrowsingContextId());
            Assertions.assertEquals("get", requestSent.getRequest().getMethod().toLowerCase());

            Assertions.assertEquals("foo", requestSent.getRequest().getCookies().get(0).getName());
            Assertions.assertEquals("bar", requestSent.getRequest().getCookies().get(0).getValue().getValue());
        }
    }

    @Test
    void canListenToOnAuthRequiredEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Network network = new Network(driver)) {
            CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
            network.onAuthRequired(future::complete);
            driver.get("https://the-internet.herokuapp.com/basic_auth");

            ResponseDetails response = future.get(5, TimeUnit.SECONDS);
            String windowHandle = driver.getWindowHandle();
            Assertions.assertEquals(windowHandle, response.getBrowsingContextId());
            Assertions.assertEquals("get", response.getRequest().getMethod().toLowerCase());
            Assertions.assertEquals(401L, response.getResponseData().getStatus());
        }
    }
}
