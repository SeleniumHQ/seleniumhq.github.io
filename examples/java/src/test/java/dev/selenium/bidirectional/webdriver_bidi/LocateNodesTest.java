package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.Script;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.LocateNodeParameters;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.bidi.script.EvaluateResult;
import org.openqa.selenium.bidi.script.EvaluateResultSuccess;
import org.openqa.selenium.bidi.script.LocalValue;
import org.openqa.selenium.bidi.script.NodeProperties;
import org.openqa.selenium.bidi.script.RemoteReference;
import org.openqa.selenium.bidi.script.RemoteValue;
import org.openqa.selenium.bidi.script.ResultOwnership;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class LocateNodesTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    @Disabled
    void testCreateABrowsingContextForGivenId() {
        String id = driver.getWindowHandle();
        BrowsingContext browsingContext = new BrowsingContext(driver, id);
        Assertions.assertEquals(id, browsingContext.getId());
    }

    @Test
    @Disabled
    void canLocateNodes() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters = new LocateNodeParameters.Builder(Locator.css("div")).build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);
        Assertions.assertEquals(13, elements.size());
    }

    @Test
    @Disabled
    void canLocateNodesWithJustLocator() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        List<RemoteValue> elements = browsingContext.locateNodes(Locator.css("div"));
        Assertions.assertEquals(13, elements.size());
    }

    @Test
    @Disabled
    void canLocateNode() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        RemoteValue element = browsingContext.locateNode(Locator.css("div"));
        Assertions.assertEquals("node", element.getType());
    }

    @Test
    @Disabled
    void canLocateNodesWithCSSLocator() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("div.extraDiv, div.content"))
                        .setMaxNodeCount(1)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);

        RemoteValue value = elements.get(0);
        NodeProperties properties = (NodeProperties) value.getValue().get();
        Assertions.assertEquals("div", properties.getLocalName().get());
        Assertions.assertEquals("content", properties.getAttributes().get().get("class"));
    }

    @Test
    @Disabled
    void canLocateNodesWithXPathLocator() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.xpath("/html/body/div[2]"))
                        .setMaxNodeCount(1)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);

        RemoteValue value = elements.get(0);
        NodeProperties properties = (NodeProperties) value.getValue().get();
        Assertions.assertEquals("div", properties.getLocalName().get());
        Assertions.assertEquals("content", properties.getAttributes().get().get("class"));
    }

    @Test
    @Disabled
    void canLocateNodesWithInnerText() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.innerText("Spaced out"))
                        .setMaxNodeCount(1)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);

        RemoteValue value = elements.get(0);
        Assertions.assertEquals("node", value.getType());
    }

    @Test
    @Disabled
    void canLocateNodesWithMaxNodeCount() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("div")).setMaxNodeCount(4).build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);
        Assertions.assertEquals(4, elements.size());
    }

    @Test
    @Disabled
    void canLocateNodesWithNoneOwnershipParameter() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("div"))
                        .setOwnership(ResultOwnership.NONE)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);
        Assertions.assertFalse(elements.get(0).getHandle().isPresent());
    }

    @Test
    @Disabled
    void canLocateNodesWithRootOwnershipParameter() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("div"))
                        .setOwnership(ResultOwnership.ROOT)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);
        Assertions.assertTrue(elements.get(0).getHandle().isPresent());
    }

    @Test
    @Disabled
    void canLocateNodesGivenStartNodes() {
        String handle = driver.getWindowHandle();
        BrowsingContext browsingContext = new BrowsingContext(driver, handle);

        driver.get("https://www.selenium.dev/selenium/web/formPage.html");

        Script script = new Script(driver);
        EvaluateResult result =
                script.evaluateFunctionInBrowsingContext(
                        handle,
                        "document.querySelectorAll(\"form\")",
                        false,
                        Optional.of(ResultOwnership.ROOT));

        EvaluateResultSuccess resultSuccess = (EvaluateResultSuccess) result;
        List<RemoteReference> startNodes = new ArrayList<>();

        RemoteValue remoteValue = resultSuccess.getResult();
        List<RemoteValue> remoteValues = (List<RemoteValue>) remoteValue.getValue().get();

        remoteValues.forEach(
                value ->
                        startNodes.add(
                                new RemoteReference(RemoteReference.Type.SHARED_ID, value.getSharedId().get())));

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("input"))
                        .setStartNodes(startNodes)
                        .setMaxNodeCount(50)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);
        Assertions.assertEquals(35, elements.size());
    }

    @Test
    @Disabled
    void canLocateNodesInAGivenSandbox() {
        String sandbox = "sandbox";
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

        browsingContext.navigate("https://www.selenium.dev/selenium/web/xhtmlTest.html", ReadinessState.COMPLETE);

        LocateNodeParameters parameters =
                new LocateNodeParameters.Builder(Locator.css("div"))
                        .setSandbox(sandbox)
                        .setMaxNodeCount(1)
                        .build();

        List<RemoteValue> elements = browsingContext.locateNodes(parameters);

        String nodeId = elements.get(0).getSharedId().get();

        List<LocalValue> arguments = new ArrayList<>();

        LocalValue value = LocalValue.mapValue(Map.of("sharedId", LocalValue.stringValue(nodeId)));
        arguments.add(value);

        Script script = new Script(driver);

        // Since the node was present in the sandbox, the script run in the same sandbox should be able
        // to retrieve it
        EvaluateResult result =
                script.callFunctionInBrowsingContext(
                        driver.getWindowHandle(),
                        sandbox,
                        "function(){ return arguments[0]; }",
                        true,
                        Optional.of(arguments),
                        Optional.empty(),
                        Optional.empty());

        Map<String, Object> sharedIdMap =
                (Map<String, Object>) ((EvaluateResultSuccess) result).getResult().getValue().get();

        String sharedId = (String) ((RemoteValue) sharedIdMap.get("sharedId")).getValue().get();
        Assertions.assertEquals(nodeId, sharedId);
    }
}
