package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.Script;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.script.LocalValue;
import org.openqa.selenium.bidi.script.Message;
import org.openqa.selenium.bidi.script.RealmInfo;
import org.openqa.selenium.bidi.script.RealmType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class ScriptEventsTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }
    
    @Test
    void canListenToChannelMessage()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Script script = new Script(driver)) {
            CompletableFuture<Message> future = new CompletableFuture<>();
            script.onMessage(future::complete);

            script.callFunctionInBrowsingContext(
                    driver.getWindowHandle(),
                    "(channel) => channel('foo')",
                    false,
                    Optional.of(List.of(LocalValue.channelValue("channel_name"))),
                    Optional.empty(),
                    Optional.empty());

            Message message = future.get(5, TimeUnit.SECONDS);
            Assertions.assertEquals("channel_name", message.getChannel());
        }
    }

    @Test
    void canListenToRealmCreatedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Script script = new Script(driver)) {
            CompletableFuture<RealmInfo> future = new CompletableFuture<>();
            script.onRealmCreated(future::complete);

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());

            context.navigate("https://www.selenium.dev/selenium/blankPage");
            RealmInfo realmInfo = future.get(5, TimeUnit.SECONDS);
            Assertions.assertNotNull(realmInfo.getRealmId());
            Assertions.assertEquals(RealmType.WINDOW, realmInfo.getRealmType());
        }
    }

    @Test
    @Disabled
    void canListenToRealmDestroyedEvent()
            throws ExecutionException, InterruptedException, TimeoutException {
        try (Script script = new Script(driver)) {
            CompletableFuture<RealmInfo> future = new CompletableFuture<>();
            script.onRealmDestroyed(future::complete);

            BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());

            context.close();
            RealmInfo realmInfo = future.get(5, TimeUnit.SECONDS);
            Assertions.assertNotNull(realmInfo.getRealmId());
            Assertions.assertEquals(RealmType.WINDOW, realmInfo.getRealmType());
        }
    }
}
