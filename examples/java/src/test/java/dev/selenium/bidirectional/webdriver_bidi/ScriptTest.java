package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.Script;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.script.ChannelValue;
import org.openqa.selenium.bidi.script.EvaluateResult;
import org.openqa.selenium.bidi.script.EvaluateResultExceptionValue;
import org.openqa.selenium.bidi.script.EvaluateResultSuccess;
import org.openqa.selenium.bidi.script.LocalValue;
import org.openqa.selenium.bidi.script.ObjectLocalValue;
import org.openqa.selenium.bidi.script.PrimitiveProtocolValue;
import org.openqa.selenium.bidi.script.RealmInfo;
import org.openqa.selenium.bidi.script.RealmType;
import org.openqa.selenium.bidi.script.RemoteReference;
import org.openqa.selenium.bidi.script.ResultOwnership;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class ScriptTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @Test
    void canCallFunction() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            List<LocalValue> arguments = new ArrayList<>();
            arguments.add(PrimitiveProtocolValue.numberValue(22));

            Map<Object, LocalValue> value = new HashMap<>();
            value.put("some_property", LocalValue.numberValue(42));
            LocalValue thisParameter = LocalValue.objectValue(value);

            arguments.add(thisParameter);

            EvaluateResult result =
                    script.callFunctionInBrowsingContext(
                            id,
                            "function processWithPromise(argument) {\n"
                                    + "  return new Promise((resolve, reject) => {\n"
                                    + "    setTimeout(() => {\n"
                                    + "      resolve(argument + this.some_property);\n"
                                    + "    }, 1000)\n"
                                    + "  })\n"
                                    + "}",
                            true,
                            Optional.of(arguments),
                            Optional.of(thisParameter),
                            Optional.of(ResultOwnership.ROOT));

            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertEquals(64L, (Long) successResult.getResult().getValue().get());
        }
    }

    @Test
    void canCallFunctionWithDeclaration() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.callFunctionInBrowsingContext(
                            id, "()=>{return 1+2;}", false, Optional.empty(), Optional.empty(), Optional.empty());
            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertEquals(3L, (Long) successResult.getResult().getValue().get());
        }
    }

    @Test
    void canCallFunctionWithArguments() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            List<LocalValue> arguments = new ArrayList<>();
            LocalValue value1 = PrimitiveProtocolValue.stringValue("ARGUMENT_STRING_VALUE");
            LocalValue value2 = PrimitiveProtocolValue.numberValue(42);
            arguments.add(value1);
            arguments.add(value2);

            EvaluateResult result =
                    script.callFunctionInBrowsingContext(
                            id,
                            "(...args)=>{return args}",
                            false,
                            Optional.of(arguments),
                            Optional.empty(),
                            Optional.empty());

            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertEquals(2, ((List<Object>) successResult.getResult().getValue().get()).size());
        }
    }

    @Test
    void canCallFunctionWithAwaitPromise() {
        String id = driver.getWindowHandle();
    try (Script script = new Script(id, driver)) {
        EvaluateResult result =
                script.callFunctionInBrowsingContext(
                        id,
                        "async function() {{\n"
                                + "            await new Promise(r => setTimeout(() => r(), 0));\n"
                                + "            return \"SOME_DELAYED_RESULT\";\n"
                                + "          }}",
                        true,
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty());
        EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
        Assertions.assertEquals("SOME_DELAYED_RESULT", (String) successResult.getResult().getValue().get());
        }
    }

    @Test
    void canCallFunctionWithThisParameter() {
        String id = driver.getWindowHandle();
    try (Script script = new Script(id, driver)) {
        Map<Object, LocalValue> value = new HashMap<>();
        value.put("some_property", PrimitiveProtocolValue.numberValue(42));
        LocalValue thisParameter = LocalValue.objectValue(value);

        EvaluateResult result =
                script.callFunctionInBrowsingContext(
                        id,
                        "function(){return this.some_property}",
                        false,
                        Optional.empty(),
                        Optional.of(thisParameter),
                        Optional.empty());

        EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
        Assertions.assertEquals("number", successResult.getResult().getType());
        Assertions.assertEquals(42L, (Long) successResult.getResult().getValue().get());
        }
    }

    @Test
    void canCallFunctionWithOwnershipRoot() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.callFunctionInBrowsingContext(
                            id,
                            "async function(){return {a:1}}",
                            true,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.of(ResultOwnership.ROOT));

            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertTrue(successResult.getResult().getHandle().isPresent());
            }
        }

    @Test
    void canCallFunctionThatThrowsException() {
        String id = driver.getWindowHandle();
    try (Script script = new Script(id, driver)) {
        EvaluateResult result =
                script.callFunctionInBrowsingContext(
                        id,
                        "))) !!@@## some invalid JS script (((",
                        false,
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty());
        EvaluateResultExceptionValue exception = (EvaluateResultExceptionValue) result;
        Assertions.assertEquals("error", exception.getExceptionDetails().getException().getType());
        Assertions.assertEquals(
                "SyntaxError: expected expression, got ')'", exception.getExceptionDetails().getText());
        }
    }

    @Test
    void canCallFunctionInASandBox() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.callFunctionInBrowsingContext(
                            id,
                            "sandbox",
                            "() => window.foo",
                            true,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty());

        Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
        }
    }

    @Test
    void canCallFunctionInARealm() {
        String tab = driver.getWindowHandle();
        try (Script script = new Script(tab, driver)) {
            List<RealmInfo> realms = script.getAllRealms();
            String realmId = realms.get(0).getRealmId();

            EvaluateResult result = script.callFunctionInRealm(
                    realmId,
                    "() => { window.foo = 3; }",
                    true,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());

            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
        }
    }

    @Test
    void canEvaluateScript() {
        String id = driver.getWindowHandle();

        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(id, "1 + 2", true, Optional.empty());

            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
            Assertions.assertEquals(3L, (Long) successResult.getResult().getValue().get());
        }
    }

    @Test
    void canEvaluateScriptThatThrowsException() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            id, "))) !!@@## some invalid JS script (((", false, Optional.empty());

            EvaluateResultExceptionValue exception = (EvaluateResultExceptionValue) result;
            Assertions.assertEquals("error", exception.getExceptionDetails().getException().getType());
            Assertions.assertEquals(
                    "SyntaxError: expected expression, got ')'", exception.getExceptionDetails().getText());
        }
    }

    @Test
    void canEvaluateScriptWithResulWithOwnership() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            id, "Promise.resolve({a:1})", true, Optional.of(ResultOwnership.ROOT));

            EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
            Assertions.assertTrue(successResult.getResult().getHandle().isPresent());
        }
    }

    @Test
    void canEvaluateInASandBox() {
        String id = driver.getWindowHandle();
        try (Script script = new Script(id, driver)) {
            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            id, "sandbox", "window.foo", true, Optional.empty());


            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
        }
    }

    @Test
    void canEvaluateInARealm() {
        String tab = driver.getWindowHandle();
        try (Script script = new Script(tab, driver)) {
            List<RealmInfo> realms = script.getAllRealms();
            String realmId = realms.get(0).getRealmId();

            EvaluateResult result =
                    script.evaluateFunctionInRealm(
                            realmId, "window.foo", true, Optional.empty());

            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
        }
    }

    @Test
    void canDisownHandle() {
        String window = driver.getWindowHandle();
        try (Script script = new Script(window, driver)) {
            BrowsingContext context = new BrowsingContext(driver, window);

            context.navigate("https://www.selenium.dev/selenium/web/dynamic.html", ReadinessState.COMPLETE);

            driver.findElement(By.id("adder")).click();

            getLocatedElement(driver, By.id("box0"));

            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            window, "document.querySelector('.redbox');", false, Optional.of(ResultOwnership.ROOT));
            String boxId = ((EvaluateResultSuccess)result).getResult().getHandle().get();

            script.disownBrowsingContextScript(
                            window, List.of(boxId));

            LocalValue value =
                    LocalValue.remoteReference(
                            RemoteReference.Type.HANDLE, boxId);

            // Since the handle is now eligible for garbage collections, it is no longer available to be used.
            Assertions.assertThrows(WebDriverException.class, () -> script.callFunctionInBrowsingContext(
                    window,
                    "arg => arg.a",
                    false, Optional.of(List.of(value)),
                    Optional.empty(),
                    Optional.empty()));
        }
    }

    @Test
    void canDisownHandleInARealm() {
        String window = driver.getWindowHandle();
        try (Script script = new Script(window, driver)) {
            BrowsingContext context = new BrowsingContext(driver, window);

            context.navigate("https://www.selenium.dev/selenium/web/dynamic.html", ReadinessState.COMPLETE);

            driver.findElement(By.id("adder")).click();

            getLocatedElement(driver, By.id("box0"));

            List<RealmInfo> realms = script.getAllRealms();
            String realmId = realms.get(0).getRealmId();

            // Retrieve the handle for the element added to DOM
            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            window, "document.querySelector('.redbox');", false, Optional.of(ResultOwnership.ROOT));
            String boxId = ((EvaluateResultSuccess)result).getResult().getHandle().get();

            LocalValue value =
                    LocalValue.remoteReference(
                            RemoteReference.Type.HANDLE, boxId);

            EvaluateResult checkHandle = script.callFunctionInBrowsingContext(
                    window,
                    "arg => arg.a",
                    false, Optional.of(List.of(value)),
                    Optional.empty(),
                    Optional.empty());

            // The handle is present in memory, else it would result in exception
            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, checkHandle.getResultType());

            // Useful to memory management in a dynamic webpage where DOM mutations happen often
            script.disownRealmScript(realmId, List.of(boxId));

            // Since the handle is now eligible for garbage collections, it is no longer available to be used.
            Assertions.assertThrows(WebDriverException.class, () -> script.callFunctionInBrowsingContext(
                    window,
                    "arg => arg.a",
                    false, Optional.of(List.of(value)),
                    Optional.empty(),
                    Optional.empty()));
        }
    }

    @Test
    void canGetAllRealms() {
        String firstWindow = driver.getWindowHandle();
        String secondWindow = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();
        try (Script script = new Script(firstWindow, driver)) {
            List<RealmInfo> realms = script.getAllRealms();
            Assertions.assertEquals(2, realms.size());
        }
    }

    @Test
    void canGetRealmByType() {
        String firstWindow = driver.getWindowHandle();
        String secondWindow = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();
        try (Script script = new Script(firstWindow, driver)) {
            List<RealmInfo> realms = script.getRealmsByType(RealmType.WINDOW);
            Assertions.assertEquals(2, realms.size());
        }
    }

    @Test
    void canGetRealmInBrowsingContext() {
        String windowId = driver.getWindowHandle();
        String tabId = driver.switchTo().newWindow(WindowType.TAB).getWindowHandle();
        try (Script script = new Script(windowId, driver)) {
            List<RealmInfo> realms = script.getRealmsInBrowsingContext(tabId);
            Assertions.assertEquals(1, realms.size());
        }
    }

    @Test
    void canGetRealmInBrowsingContextByType() {
        String windowId = driver.getWindowHandle();
        String tabId = driver.switchTo().newWindow(WindowType.TAB).getWindowHandle();
        try (Script script = new Script(windowId, driver)) {
            List<RealmInfo> windowRealms =
                    script.getRealmsInBrowsingContextByType(windowId, RealmType.WINDOW);
            Assertions.assertEquals(1, windowRealms.size());
        }
    }

    @Test
    void canAddPreloadScript() throws ExecutionException, InterruptedException, TimeoutException {
        try (Script script = new Script(driver)) {
            String id =
                    script.addPreloadScript("() => {{ console.log('{preload_script_console_text}') }}");

            Assertions.assertNotNull(id);

            try (LogInspector logInspector = new LogInspector(driver)) {
                CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
                logInspector.onConsoleEntry(future::complete);

                driver.get("https://www.selenium.dev/selenium/blankPage");

                ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

                Assertions.assertEquals("{preload_script_console_text}", logEntry.getText());
            }
        }
    }

    @Test
    void canAddPreloadScriptWithArguments() {
        try (Script script = new Script(driver)) {
            String id =
                    script.addPreloadScript(
                            "(channel) => channel('will_be_send', 'will_be_ignored')",
                            List.of(new ChannelValue("channel_name")));
            Assertions.assertNotNull(id);
        }
    }

    @Test
    void canAddPreloadScriptInASandbox() {
        try (Script script = new Script(driver)) {
            String id = script.addPreloadScript("() => { window.bar=2; }", "sandbox");
            Assertions.assertNotNull(id);
            driver.get("https://www.selenium.dev/selenium/blankPage");

            EvaluateResult result =
                    script.evaluateFunctionInBrowsingContext(
                            driver.getWindowHandle(), "sandbox", "window.bar", true, Optional.empty());
            Assertions.assertEquals(EvaluateResult.Type.SUCCESS, result.getResultType());
        }
    }

    @Test
    void canRemovePreloadScript() throws ExecutionException, InterruptedException, TimeoutException {
        try (Script script = new Script(driver)) {
            String id =
                    script.addPreloadScript("() => {{ console.log('{preload_script_console_text}') }}");

            Assertions.assertNotNull(id);

            try (LogInspector logInspector = new LogInspector(driver)) {
                CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
                logInspector.onConsoleEntry(future::complete);

                script.removePreloadScript(id);

                driver.get("https://www.selenium.dev/selenium/blankPage");

                Assertions.assertThrows(TimeoutException.class, () -> future.get(5, TimeUnit.SECONDS));
            }
        }
    }
}
