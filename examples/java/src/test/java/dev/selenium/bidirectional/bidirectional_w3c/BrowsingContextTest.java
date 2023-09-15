package dev.selenium.bidirectional.bidirectional_w3c;

import dev.selenium.BaseTest;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.BiDiException;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContextInfo;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
}
