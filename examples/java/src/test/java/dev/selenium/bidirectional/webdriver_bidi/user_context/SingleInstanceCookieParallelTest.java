package dev.selenium.bidirectional.webdriver_bidi.user_context;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.CreateContextParameters;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.bidi.script.NodeProperties;
import org.openqa.selenium.bidi.script.RemoteValue;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

class SingleInstanceCookieParallelTest {

  private static WebDriver driver;
  BrowsingContext context;

  @BeforeAll
  public static void beforeAll() {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("webSocketUrl", true);
    driver = new FirefoxDriver(options);

//    To use Grid uncomment the lines below

//    driver = new RemoteWebDriver(
//            new URL("http://localhost:4444"),
//            options, false);
//
//    Augmenter augmenter = new Augmenter();
//    driver = augmenter.augment(driver);
  }

  @BeforeEach
  public void setup() {
    Browser browser = new Browser(driver);
    String userContext = browser.createUserContext();

    CreateContextParameters parameters = new CreateContextParameters(WindowType.TAB);
    parameters.userContext(userContext);

    context = new BrowsingContext(driver, parameters);
  }

  @Test
  void canSwitchToBlue() {
    context.navigate("https://www.selenium.dev/selenium/web/cookie-background.html", ReadinessState.COMPLETE);

    RemoteValue value = context.locateNode(Locator.xpath("/html/body/button[1]"));

    Input inputModule = new Input(driver);
    Actions actions = new Actions(driver);

    RemoteWebElement element = new RemoteWebElement();
    element.setId(value.getSharedId().get());
    actions.moveToElement(element).click();

    inputModule.perform(context.getId(), actions.getSequences());

    value = context.locateNode(Locator.xpath("/html/body"));

    NodeProperties properties = (NodeProperties) value.getValue().get();
    String bgColor = properties.getAttributes().get().get("style");

    Assertions.assertEquals(bgColor, "background-color: lightblue;");
    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @Test
  void canSwitchToGreen() {
    context.navigate("https://www.selenium.dev/selenium/web/cookie-background.html", ReadinessState.COMPLETE);

    RemoteValue value = context.locateNode(Locator.xpath("/html/body"));

    NodeProperties properties = (NodeProperties) value.getValue().get();
    String bgColor = properties.getAttributes().get().get("style");

    Assertions.assertEquals(bgColor, "background-color: white;");

    value = context.locateNode(Locator.xpath("/html/body/button[2]"));

    Input inputModule = new Input(driver);
    Actions actions = new Actions(driver);

    RemoteWebElement element = new RemoteWebElement();
    element.setId(value.getSharedId().get());
    actions.moveToElement(element).click();

    inputModule.perform(context.getId(), actions.getSequences());

    value = context.locateNode(Locator.xpath("/html/body"));

    properties = (NodeProperties) value.getValue().get();
    bgColor = properties.getAttributes().get().get("style");

    Assertions.assertEquals(bgColor, "background-color: lightgreen;");
    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @Test
  void canHaveTheDefaultBackgroundColor() {
    context.navigate("https://www.selenium.dev/selenium/web/cookie-background.html", ReadinessState.COMPLETE);

    RemoteValue value = context.locateNode(Locator.xpath("/html/body"));

    NodeProperties properties = (NodeProperties) value.getValue().get();
    String bgColor = properties.getAttributes().get().get("style");

    Assertions.assertEquals(bgColor, "background-color: white;");
    System.out.println(
        Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + " => executed successfully");
  }

  @AfterAll
  public static void cleanup() {
    driver.quit();
  }
}
