package dev.selenium.support;

import dev.selenium.BaseChromeTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class ListenersTest extends BaseChromeTest {

  // The decorator calls the listener through reflection, so the class must be public
  public static class RecordingListener implements WebDriverListener {
    final List<String> events = new ArrayList<>();

    @Override
    public void afterGet(WebDriver driver, String url) {
      events.add("navigated to " + url);
    }

    @Override
    public void beforeClick(WebElement element) {
      events.add("clicking " + element.getDomAttribute("id"));
    }
  }

  @Test
  public void listener() {
    RecordingListener listener = new RecordingListener();
    WebDriver eventDriver = new EventFiringDecorator<>(listener).decorate(driver);

    eventDriver.get("https://www.selenium.dev/selenium/web/formPage.html");
    eventDriver.findElement(By.id("checky")).click();

    Assertions.assertEquals(
        List.of("navigated to https://www.selenium.dev/selenium/web/formPage.html", "clicking checky"),
        listener.events);
  }
}
