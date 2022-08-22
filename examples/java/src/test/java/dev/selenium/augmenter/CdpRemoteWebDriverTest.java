package dev.selenium.augmenter;

import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;
import static org.openqa.selenium.remote.http.Contents.utf8String;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.logging.EventType;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import com.google.common.net.MediaType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CdpRemoteWebDriverTest {

  private static URL gridUrl;

  private WebDriver driver;

  @BeforeAll
  static void setDriver() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setup() throws MalformedURLException {
    int port = PortProber.findFreePort();
    WebDriverManager.chromedriver().setup();
    Main.main(new String[] { "standalone", "--port", String.valueOf(port) });

    gridUrl = new URL(String.format("http://localhost:%d/", port));
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(gridUrl, options);
  }

  @AfterEach
  public void quit() {
    driver.quit();
  }

  @Test
  public void testCreateCDPSession() {
    driver = new Augmenter().augment(driver);
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
  }

  @Test
  public void testBasicAuth() {
    AtomicReference<DevTools> devToolsAtomicReference = new AtomicReference<>();

    driver = new Augmenter().addDriverAugmentation("chrome",
                                                   HasAuthentication.class,
                                                   (caps, exec) -> (whenThisMatches, useTheseCredentials) -> {
                                                     devToolsAtomicReference.get()
                                                       .createSessionIfThereIsNotOne();
                                                     devToolsAtomicReference.get().getDomains()
                                                       .network()
                                                       .addAuthHandler(whenThisMatches,
                                                                       useTheseCredentials);

                                                   }).augment(driver);

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    devToolsAtomicReference.set(devTools);
    ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
    driver.get("https://the-internet.herokuapp.com/basic_auth");

    WebElement element = driver.findElement(By.tagName("p"));

    Assertions
      .assertEquals("Congratulations! You must have the proper credentials.", element.getText());
  }

  @Test
  public void testDomMutation() throws Exception {
    AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
    AtomicReference<WebDriver> augmentedDriver = new AtomicReference<>();
    CountDownLatch latch = new CountDownLatch(1);

    Augmenter augmenter = new Augmenter();

    driver = augmenter.
      addDriverAugmentation("chrome", HasLogEvents.class, (caps, exec) -> new HasLogEvents() {
        @Override
        public <X> void onLogEvent(EventType<X> kind) {
          kind.initializeListener(augmentedDriver.get());
        }
      }).augment(driver);

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    if (driver instanceof HasLogEvents) {
      augmentedDriver.set(driver);
    } else {
      throw new Exception("Not an instance of HasLogEvents");
    }

    ((HasLogEvents) driver).onLogEvent(domMutation(mutation -> {
      if ("cheese".equals(mutation.getAttributeName())) {
        seen.set(mutation);
        latch.countDown();
      }
    }));

    driver.get("https://www.google.com");
    WebElement span = driver.findElement(By.cssSelector("span"));

    ((JavascriptExecutor) driver)
      .executeScript("arguments[0].setAttribute('cheese', 'gouda');", span);

    Assertions.assertTrue(latch.await(10, TimeUnit.SECONDS));
    Assertions.assertEquals("cheese", seen.get().getAttributeName());
    Assertions.assertEquals("gouda", seen.get().getCurrentValue());
  }

  @Test
  public void testConsoleLogListener() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(4);
    driver = new Augmenter().augment(driver);
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    // Use as per Devtools version
    devTools.send(org.openqa.selenium.devtools.v85.runtime.Runtime.enable());
    devTools.send(Log.enable());

    // https://chromedevtools.github.io/devtools-protocol/tot/Console/ states that post deprecation either Runtime or Log domain is to be used
    // Depending on the implementation, events from either of the domains can be fired for console logs.

    devTools.addListener(Log.entryAdded(),
                         logEntry -> {
                           System.out.println("log: " + logEntry.getText());
                           System.out.println("level: " + logEntry.getLevel());
                           latch.countDown();
                         });

    // Use as per Devtools version
    devTools.addListener(org.openqa.selenium.devtools.v85.runtime.Runtime.consoleAPICalled(),
                         consoleLog -> System.out.println("Type: " + consoleLog.getType()));

    driver.get("https://the-internet.herokuapp.com/broken_images");

    Assertions.assertTrue(latch.await(10, TimeUnit.SECONDS));
  }

  @Test
  public void testJsExceptionListener() throws Exception {
    driver = new Augmenter().augment(driver);
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    List<JavascriptException> jsExceptionsList = new ArrayList<>();
    devTools.getDomains().events().addJavascriptExceptionListener(jsExceptionsList::add);
    CompletableFuture<JavascriptException> futureJsExc = new CompletableFuture<>();
    devTools.getDomains().events().addJavascriptExceptionListener(futureJsExc::complete);

    driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

    WebElement element = driver.findElement(By.tagName("button"));

    ((JavascriptExecutor) driver)
      .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                     element, "onclick", "throw new Error('Hello, world!')");
    element.click();

    futureJsExc.get(5, TimeUnit.SECONDS);
    Assertions.assertEquals(1, jsExceptionsList.size());
  }

  @Test
  public void testNetworkInterceptor() {
    driver = new Augmenter().augment(driver);
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    // Intercept and change response if the request uri contains "google"
    try (NetworkInterceptor interceptor = new NetworkInterceptor(
      driver,
      Route.matching(req -> req.getUri().contains("google"))
        .to(() -> req -> new HttpResponse()
          .setStatus(200)
          .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
          .setContent(utf8String("Creamy, delicious cheese!"))))) {

      driver.get("https://google.com");

      String source = driver.getPageSource();
      Assertions.assertTrue(source.contains("delicious cheese!"));
    }
  }
}
