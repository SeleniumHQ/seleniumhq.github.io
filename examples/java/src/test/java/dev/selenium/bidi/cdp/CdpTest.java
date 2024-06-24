package dev.selenium.bidi.cdp;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.HasCdp;

import java.util.HashMap;
import java.util.Map;

public class CdpTest extends BaseTest {
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
}
