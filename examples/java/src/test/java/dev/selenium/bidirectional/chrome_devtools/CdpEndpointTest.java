package dev.selenium.bidirectional.chrome_devtools;

import com.google.common.collect.ImmutableMap;
import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.HasCdp;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdpEndpointTest extends BaseTest {
  @BeforeEach
  public void createSession() {
    driver = new ChromeDriver();
  }

  @Test
  public void setCookie() {
    Map<String, Object> cookie = new HashMap<>();
    cookie.put("name", "cheese");
    cookie.put("value", "gouda");
    cookie.put("domain", "www.selenium.dev");
    cookie.put("secure", true);

    ((HasCdp) driver).executeCdpCommand("Network.setCookie", cookie);

    driver.get("https://www.selenium.dev");
    Cookie cheese = driver.manage().getCookieNamed("cheese");
    Assertions.assertEquals("gouda", cheese.getValue());
  }

  @Test
  public void performanceMetrics() {
    driver.get("https://www.selenium.dev/selenium/web/frameset.html");

    ((HasCdp) driver).executeCdpCommand("Performance.enable", new HashMap<>());

    Map<String, Object> response =
        ((HasCdp) driver).executeCdpCommand("Performance.getMetrics", new HashMap<>());
    List<Map<String, Object>> metricList = (List<Map<String, Object>>) response.get("metrics");

    Map<String, Number> metrics = new HashMap<>();
    for (Map<String, Object> metric : metricList) {
      metrics.put((String) metric.get("name"), (Number) metric.get("value"));
    }

    Assertions.assertTrue(metrics.get("DevToolsCommandDuration").doubleValue() > 0);
    Assertions.assertEquals(12, metrics.get("Frames").intValue());
  }

  @Test
  public void basicAuth() {
    ((HasCdp) driver).executeCdpCommand("Network.enable", new HashMap<>());

    String encodedAuth = Base64.getEncoder().encodeToString("admin:admin".getBytes());
    Map<String, Object> headers =
        ImmutableMap.of("headers", ImmutableMap.of("authorization", "Basic " + encodedAuth));

    ((HasCdp) driver).executeCdpCommand("Network.setExtraHTTPHeaders", headers);

    driver.get("https://the-internet.herokuapp.com/basic_auth");

    Assertions.assertEquals(
        "Congratulations! You must have the proper credentials.",
        driver.findElement(By.tagName("p")).getText());
  }
}
