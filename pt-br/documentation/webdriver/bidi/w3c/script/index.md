# Script

URL: https://www.selenium.dev/pt-br/documentation/webdriver/bidi/w3c/script/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Portuguese. Do you speak Portuguese? Help us to translate
>    it by sending us pull requests!
> 

## Commands
This section contains the APIs related to script commands. 

### Call function in a browsing context

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
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
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(id, driver)

let argumentValues = []
let value = new ArgumentValue(LocalValue.createNumberValue(22))
argumentValues.push(value)

let mapValue = {some_property: LocalValue.createNumberValue(42)}
let thisParameter = new ArgumentValue(LocalValue.createObjectValue(mapValue)).asMap()

const result = await manager.callFunctionInBrowsingContext(
  id,
  'function processWithPromise(argument) {' +
  'return new Promise((resolve, reject) => {' +
  'setTimeout(() => {' +
  'resolve(argument + this.some_property);' +
  '}, 1000)' +
  '})' +
  '}',
  true,
  argumentValues,
  thisParameter,
  ResultOwnership.ROOT)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Call function in a sandbox

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
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
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(id, driver)

await manager.callFunctionInBrowsingContext(id, '() => { window.foo = 2; }', true, null, null, null, 'sandbox')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Call function in a realm

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
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
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(firstTab, driver)

const realms = await manager.getAllRealms()
const realmId = realms[0].realmId

await manager.callFunctionInRealm(realmId, '() => { window.foo = 3; }', true)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Evaluate script in a browsing context

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(id, driver)) {
    EvaluateResult result =
            script.evaluateFunctionInBrowsingContext(id, "1 + 2", true, Optional.empty());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(id, driver)

const result = await manager.evaluateFunctionInBrowsingContext(id, '1 + 2', true)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Evaluate script in a sandbox

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(id, driver)) {
    EvaluateResult result =
            script.evaluateFunctionInBrowsingContext(
                    id, "sandbox", "window.foo", true, Optional.empty());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(id, driver)

await manager.evaluateFunctionInBrowsingContext(id, 'window.foo = 2', true, null, 'sandbox')

const resultInSandbox = await manager.evaluateFunctionInBrowsingContext(id, 'window.foo', true, null, 'sandbox')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Evaluate script in a realm

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(tab, driver)) {
    List<RealmInfo> realms = script.getAllRealms();
    String realmId = realms.get(0).getRealmId();

    EvaluateResult result =
            script.evaluateFunctionInRealm(
                    realmId, "window.foo", true, Optional.empty());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(firstTab, driver)

const realms = await manager.getAllRealms()
const realmId = realms[0].realmId

await manager.evaluateFunctionInRealm(realmId, 'window.foo = 3', true)

const result = await manager.evaluateFunctionInRealm(realmId, 'window.foo', true)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Disown handles in a browsing context

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
script.disownBrowsingContextScript(
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
await manager.disownBrowsingContextScript(id, boxId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Disown handles in a realm

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
script.disownRealmScript(realmId, List.of(boxId));
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
await manager.disownRealmScript(realmId, boxId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get all realms

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(firstWindow, driver)) {
    List<RealmInfo> realms = script.getAllRealms();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(firstWindow, driver)

const realms = await manager.getAllRealms()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get realm by type

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(firstWindow, driver)) {
    List<RealmInfo> realms = script.getRealmsByType(RealmType.WINDOW);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(firstWindow, driver)

const realms = await manager.getRealmsByType(RealmType.WINDOW)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get browsing context realms

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Script script = new Script(windowId, driver)) {
    List<RealmInfo> realms = script.getRealmsInBrowsingContext(tabId);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const manager = await ScriptManager(windowId, driver)

const realms = await manager.getRealmsInBrowsingContext(tabId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get browsing context realms by type

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
List<RealmInfo> windowRealms =
        script.getRealmsInBrowsingContextByType(windowId, RealmType.WINDOW);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```javascript
const realms = await manager.getRealmsInBrowsingContextByType(windowId, RealmType.WINDOW)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Preload a script 

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
String id = script.addPreloadScript("() => { window.bar=2; }", "sandbox");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```javascript
const manager = await ScriptManager(id, driver)

const scriptId = await manager.addPreloadScript('() => {{ console.log(\'{preload_script_console_text}\') }}')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Remove a preloaded script 

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
script.removePreloadScript(id);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```javascript
await manager.removePreloadScript(scriptId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Events
This section contains the APIs related to script events. 

### Message

**Java**

_Requires [Selenium v4.16](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0) or later._

```java
try (Script script = new Script(driver)) {
    CompletableFuture<Message> future = new CompletableFuture<>();
    script.onMessage(future::complete);

    script.callFunctionInBrowsingContext(
            driver.getWindowHandle(),
            "(channel) => channel('foo')",
            false,
            Optional.of(List.of(LocalValue.channelValue("channel_name"))),
            Optional.empty(),
            Optional.empty());

    Message message = future.get(5, TimeUnit.SECONDS);
    Assertions.assertEquals("channel_name", message.getChannel());
}
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
const manager = await ScriptManager(undefined, driver)

let message = null

await manager.onMessage((m) => {
  message = m
})

let argumentValues = []
let value = new ArgumentValue(LocalValue.createChannelValue(new ChannelValue('channel_name')))
argumentValues.push(value)

const result = await manager.callFunctionInBrowsingContext(
  await driver.getWindowHandle(),
  '(channel) => channel("foo")',
  false,
  argumentValues,
)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Realm Created 

**Java**

_Requires [Selenium v4.16](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0) or later._

```java
try (Script script = new Script(driver)) {
    CompletableFuture<RealmInfo> future = new CompletableFuture<>();
    script.onRealmCreated(future::complete);

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());

    context.navigate("https://www.selenium.dev/selenium/blankPage");
    RealmInfo realmInfo = future.get(5, TimeUnit.SECONDS);
    Assertions.assertNotNull(realmInfo.getRealmId());
    Assertions.assertEquals(RealmType.WINDOW, realmInfo.getRealmType());
}
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
const manager = await ScriptManager(undefined, driver)

let realmInfo = null

await manager.onRealmCreated((result) => {
  realmInfo = result
})

const id = await driver.getWindowHandle()
const browsingContext = await BrowsingContext(driver, {
  browsingContextId: id,
})

await browsingContext.navigate('https://www.selenium.dev/selenium/web/blank', 'complete')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Realm Destroyed

**Java**

_Requires [Selenium v4.16](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0) or later._

```java
try (Script script = new Script(driver)) {
    CompletableFuture<RealmInfo> future = new CompletableFuture<>();
    script.onRealmDestroyed(future::complete);

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());

    context.close();
    RealmInfo realmInfo = future.get(5, TimeUnit.SECONDS);
    Assertions.assertNotNull(realmInfo.getRealmId());
    Assertions.assertEquals(RealmType.WINDOW, realmInfo.getRealmType());
}
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.19](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.19.0) or later._

```javascript
const manager = await ScriptManager(undefined, driver)

let realmInfo = null

await manager.onRealmDestroyed((result) => {
  realmInfo = result
})

const id = await driver.getWindowHandle()
const browsingContext = await BrowsingContext(driver, {
  browsingContextId: id,
})

await browsingContext.close()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
