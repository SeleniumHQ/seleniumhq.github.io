package dev.selenium.bidirectional;

import static org.openqa.selenium.remote.http.Contents.utf8String;

import com.google.common.net.MediaType;
import dev.selenium.BaseChromeTest;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.events.CdpEventTypes;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

public class BidiApiTest extends BaseChromeTest {
  @Test
  public void registerBasicAuth() {
    Predicate<URI> uriPredicate = uri -> uri.getHost().contains("the-internet.herokuapp.com");
    ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "admin"));

    driver.get("https://the-internet.herokuapp.com/basic_auth");

    Assertions.assertTrue(driver.getPageSource().contains("Basic Auth"));
  }

  @Test
  public void domMutation() throws InterruptedException {
    driver.get("https://www.google.com");
    WebElement span = driver.findElement(By.cssSelector("span"));

    AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
    CountDownLatch latch = new CountDownLatch(1);
    ((HasLogEvents) driver).onLogEvent(CdpEventTypes.domMutation(mutation -> {
      seen.set(mutation);
      latch.countDown();
    }));

    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('cheese', 'gouda');",
        span);

    Assertions.assertTrue(latch.await(10, TimeUnit.SECONDS));
    Assertions.assertEquals("cheese", seen.get().getAttributeName());
    Assertions.assertEquals("gouda", seen.get().getCurrentValue());
  }

  @Test
  public void interceptNetworkResponse() {
    NetworkInterceptor interceptor = new NetworkInterceptor(
        driver, Route.matching(req -> true)
        .to(() -> resp -> new HttpResponse()
            .setStatus(200)
            .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
            .setContent(utf8String("Creamy, delicious cheese!"))));
    driver.get("https://www.selenium.dev");
    String source = driver.getPageSource();

    Assertions.assertTrue(source.contains("delicious cheese"));
  }
}
