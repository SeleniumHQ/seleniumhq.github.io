package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.BiDiException;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContextInfo;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.RemoteWebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

class BrowsingContextTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    void testCreateABrowsingContextForGivenId() {
        String id = driver.getWindowHandle();
        BrowsingContext browsingContext = new BrowsingContext(driver, id);
        Assertions.assertEquals(id, browsingContext.getId());
    }

    @Test
    void testCreateAWindow() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.WINDOW);
        Assertions.assertNotNull(browsingContext.getId());
    }

    @Test
    void testCreateAWindowWithAReferenceContext() {
        BrowsingContext
                browsingContext =
                new BrowsingContext(driver, WindowType.WINDOW, driver.getWindowHandle());
        Assertions.assertNotNull(browsingContext.getId());
    }

    @Test
    void testCreateATab() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);
        Assertions.assertNotNull(browsingContext.getId());
    }

    @Test
    void testCreateATabWithAReferenceContext() {
        BrowsingContext
                browsingContext =
                new BrowsingContext(driver, WindowType.TAB, driver.getWindowHandle());
        Assertions.assertNotNull(browsingContext.getId());
    }

    @Test
    void testNavigateToAUrl() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

        NavigationResult info = browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        Assertions.assertNotNull(browsingContext.getId());
        Assertions.assertNotNull(info.getNavigationId());
        Assertions.assertTrue(info.getUrl().contains("/bidi/logEntryAdded.html"));
    }

    @Test
    void testNavigateToAUrlWithReadinessState() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

        NavigationResult info = browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html",
                ReadinessState.COMPLETE);

        Assertions.assertNotNull(browsingContext.getId());
        Assertions.assertNotNull(info.getNavigationId());
        Assertions.assertTrue(info.getUrl().contains("/bidi/logEntryAdded.html"));
    }

    @Test
    void testGetTreeWithAChild() {
        String referenceContextId = driver.getWindowHandle();
        BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);

        parentWindow.navigate("https://www.selenium.dev/selenium/web/iframes.html", ReadinessState.COMPLETE);

        List<BrowsingContextInfo> contextInfoList = parentWindow.getTree();

        Assertions.assertEquals(1, contextInfoList.size());
        BrowsingContextInfo info = contextInfoList.get(0);
        Assertions.assertEquals(1, info.getChildren().size());
        Assertions.assertEquals(referenceContextId, info.getId());
        Assertions.assertTrue(info.getChildren().get(0).getUrl().contains("formPage.html"));
    }

    @Test
    void testGetTreeWithDepth() {
        String referenceContextId = driver.getWindowHandle();
        BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);

        parentWindow.navigate("https://www.selenium.dev/selenium/web/iframes.html", ReadinessState.COMPLETE);

        List<BrowsingContextInfo> contextInfoList = parentWindow.getTree(0);

        Assertions.assertEquals(1, contextInfoList.size());
        BrowsingContextInfo info = contextInfoList.get(0);
        Assertions.assertNull(info.getChildren()); // since depth is 0
        Assertions.assertEquals(referenceContextId, info.getId());
    }

    @Test
    void testGetAllTopLevelContexts() {
        BrowsingContext window1 = new BrowsingContext(driver, driver.getWindowHandle());
        BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

        List<BrowsingContextInfo> contextInfoList = window1.getTopLevelContexts();

        Assertions.assertEquals(2, contextInfoList.size());
    }

    @Test
    void testCloseAWindow() {
        BrowsingContext window1 = new BrowsingContext(driver, WindowType.WINDOW);
        BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

        window2.close();

        Assertions.assertThrows(BiDiException.class, window2::getTree);
    }

    @Test
    void testCloseATab() {
        BrowsingContext tab1 = new BrowsingContext(driver, WindowType.TAB);
        BrowsingContext tab2 = new BrowsingContext(driver, WindowType.TAB);

        tab2.close();

        Assertions.assertThrows(BiDiException.class, tab2::getTree);
    }

    @Test
    void testActivateABrowsingContext() {
        BrowsingContext window1 = new BrowsingContext(driver, driver.getWindowHandle());
        BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

        window1.activate();

        boolean isFocused = (boolean) ((JavascriptExecutor) driver).executeScript("return document.hasFocus();");

        Assertions.assertTrue(isFocused);
    }

    @Test
    void testReloadABrowsingContext() {
        BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

        browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

        NavigationResult reloadInfo = browsingContext.reload(ReadinessState.INTERACTIVE);

        Assertions.assertNotNull(reloadInfo.getNavigationId());
        Assertions.assertTrue(reloadInfo.getUrl().contains("/bidi/logEntryAdded.html"));
    }

    @Test
    void testHandleUserPrompt() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();

        browsingContext.handleUserPrompt();

        Assertions.assertTrue(driver.getPageSource().contains("Testing Alerts and Stuff"));
    }

    @Test
    void testAcceptUserPrompt() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();

        browsingContext.handleUserPrompt("true");

        Assertions.assertTrue(driver.getPageSource().contains("Testing Alerts and Stuff"));
    }

    @Test
    void testDismissUserPrompt() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();

        browsingContext.handleUserPrompt("true");

        Assertions.assertTrue(driver.getPageSource().contains("Testing Alerts and Stuff"));
    }

    @Test
    void testPassUserTextToUserPrompt() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("prompt-with-default")).click();

        String userText = "Selenium automates browsers";
        browsingContext.handleUserPrompt(true, userText);

        Assertions.assertTrue(driver.getPageSource().contains(userText));
    }

    @Test
    void testDismissUserPromptWithText() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("prompt-with-default")).click();

        String userText = "Selenium automates browsers";
        browsingContext.handleUserPrompt(false, userText);

        Assertions.assertFalse(driver.getPageSource().contains(userText));
    }

    @Test
    void textCaptureScreenshot() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        String screenshot = browsingContext.captureScreenshot();

        Assertions.assertTrue(screenshot.length() > 0);
    }

    @Test
    void textCaptureViewportScreenshot() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/coordinates_tests/simple_page.html");

        WebElement element = driver.findElement(By.id("box"));
        Rectangle elementRectangle = element.getRect();

        String screenshot =
                browsingContext.captureBoxScreenshot(
                        elementRectangle.getX(), elementRectangle.getY(), 5, 5);

        Assertions.assertTrue(screenshot.length() > 0);
    }

    @Test
    void textCaptureElementScreenshot() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        WebElement element = driver.findElement(By.id("checky"));

        String screenshot = browsingContext.captureElementScreenshot(((RemoteWebElement) element).getId());

        Assertions.assertTrue(screenshot.length() > 0);
    }

    @Test
    void textSetViewport() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");

        browsingContext.setViewport(250, 300);

        List<Long> newViewportSize =
                (List<Long>)
                        ((JavascriptExecutor) driver)
                                .executeScript("return [window.innerWidth, window.innerHeight];");

        Assertions.assertEquals(250, newViewportSize.get(0));
        Assertions.assertEquals(300, newViewportSize.get(1));
    }

    @Test
    @Disabled("Supported by Firefox Nightly 124")
    void textSetViewportWithDevicePixelRatio() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");

        browsingContext.setViewport(250, 300, 5);

        Long newDevicePixelRatio =
                (Long) ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio");

        Assertions.assertEquals(5, newDevicePixelRatio);
    }

    @Test
    void testPrintPage() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        PrintOptions printOptions = new PrintOptions();

        String printPage = browsingContext.print(printOptions);

        Assertions.assertTrue(printPage.length() > 0);
    }

    @Test
    @Disabled("Supported by Firefox Nightly 124")
    void testNavigateBackInTheBrowserHistory() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        browsingContext.navigate("https://www.selenium.dev/selenium/web/formPage.html", ReadinessState.COMPLETE);

        wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
        wait.until(titleIs("We Arrive Here"));

        browsingContext.back();
        Assertions.assertTrue(driver.getPageSource().contains("We Leave From Here"));
    }

    @Test
    @Disabled("Supported by Firefox Nightly 124")
    void canNavigateForwardInTheBrowserHistory() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        browsingContext.navigate("https://www.selenium.dev/selenium/web/formPage.html", ReadinessState.COMPLETE);

        wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
        wait.until(titleIs("We Arrive Here"));

        browsingContext.back();
        Assertions.assertTrue(driver.getPageSource().contains("We Leave From Here"));

        browsingContext.forward();
        wait.until(titleIs("We Arrive Here"));
    }

    @Test
    @Disabled("Supported by Firefox Nightly 124")
    void canTraverseBrowserHistory() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        browsingContext.navigate("https://www.selenium.dev/selenium/web/formPage.html", ReadinessState.COMPLETE);

        wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
        wait.until(titleIs("We Arrive Here"));

        browsingContext.traverseHistory(-1);
        Assertions.assertTrue(driver.getPageSource().contains("We Leave From Here"));
    }
}
