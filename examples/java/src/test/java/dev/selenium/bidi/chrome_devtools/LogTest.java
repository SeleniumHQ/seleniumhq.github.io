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

}
