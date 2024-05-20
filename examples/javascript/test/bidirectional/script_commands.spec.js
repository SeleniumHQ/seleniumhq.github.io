const {suite} = require('selenium-webdriver/testing')
const assert = require("assert")
const firefox = require('selenium-webdriver/firefox')
const {By, until} = require("selenium-webdriver")
const ScriptManager = require('selenium-webdriver/bidi/scriptManager')
const {ResultOwnership} = require("selenium-webdriver/bidi/resultOwnership");
const {ArgumentValue} = require("selenium-webdriver/bidi/argumentValue");
const {LocalValue, RemoteReferenceType, ReferenceValue} = require("selenium-webdriver/bidi/protocolValue");
const {EvaluateResultType} = require("selenium-webdriver/bidi/evaluateResult");
const BrowsingContext = require("selenium-webdriver/bidi/browsingContext");
const {WebDriverError} = require("selenium-webdriver/lib/error");
const {RealmType} = require("selenium-webdriver/bidi/realmInfo");
const LogInspector = require("selenium-webdriver/bidi/logInspector");

suite(function (env) {
    describe('Script commands', function () {
        let driver

        beforeEach(async function () {
            driver = await env
                .builder()
                .setFirefoxOptions(new firefox.Options().enableBidi())
                .build()
        })

        afterEach(async function () {
            await driver.quit()
        })

        it('can call function', async function () {
            const id = await driver.getWindowHandle()
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
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 64)
        })

        it('can call function with declaration', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(id, '()=>{return 1+2;}', false)
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 3)
        })

        it('can call function to get element', async function () {
            await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded')
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                '() => document.getElementById("consoleLog")',
                false,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.notEqual(result.realmId, null)
            assert.equal(result.result.type, 'node')
            assert.notEqual(result.result.value, null)
            assert.notEqual(result.result.value.nodeType, null)
        })

        it('can call function with arguments', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            let argumentValues = []
            let value1 = new ArgumentValue(LocalValue.createStringValue('ARGUMENT_STRING_VALUE'))
            let value2 = new ArgumentValue(LocalValue.createNumberValue(42))
            argumentValues.push(value1)
            argumentValues.push(value2)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                '(...args)=>{return args}',
                false,
                argumentValues,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value.length, 2)
        })

        it('can call function with await promise', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                'async function() {{\n' +
                '            await new Promise(r => setTimeout(() => r(), 0));\n' +
                '            return "SOME_DELAYED_RESULT";\n' +
                '          }}',
                true,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 'SOME_DELAYED_RESULT')
        })

        it('can call function with await promise false', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                'async function() {{\n' +
                '            await new Promise(r => setTimeout(() => r(), 0));\n' +
                '            return "SOME_DELAYED_RESULT";\n' +
                '          }}',
                false,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.type, 'promise')
        })

        it('can call function with this parameter', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            let mapValue = {some_property: LocalValue.createNumberValue(42)}
            let thisParameter = new ArgumentValue(LocalValue.createObjectValue(mapValue)).asMap()

            const result = await manager.callFunctionInBrowsingContext(
                id,
                'function(){return this.some_property}',
                false,
                null,
                thisParameter,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 42)
        })

        it('can call function with ownership root', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                'async function(){return {a:1}}',
                true,
                null,
                null,
                ResultOwnership.ROOT,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
        })

        it('can call function with ownership none', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(
                id,
                'async function(){return {a:1}}',
                true,
                null,
                null,
                ResultOwnership.NONE,
            )
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.notEqual(result.realmId, null)
            assert.equal(result.result.handle, undefined)
            assert.notEqual(result.result.value, null)
        })

        it('can call function that throws exception', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.callFunctionInBrowsingContext(id, '))) !!@@## some invalid JS script (((', false)
            assert.equal(result.resultType, EvaluateResultType.EXCEPTION)

            assert.equal(result.exceptionDetails.exception.type, 'error')
            assert.equal(result.exceptionDetails.text, "SyntaxError: expected expression, got ')'")
            assert.equal(result.exceptionDetails.columnNumber, 39)
            assert.equal(result.exceptionDetails.stackTrace.callFrames.length, 0)
        })

        it('can call function in a sandbox', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            await manager.callFunctionInBrowsingContext(id, '() => { window.foo = 2; }', true, null, null, null, 'sandbox')

            const resultInSandbox = await manager.callFunctionInBrowsingContext(
                id,
                '() => window.foo',
                true,
                null,
                null,
                null,
                'sandbox',
            )

            assert.equal(resultInSandbox.resultType, EvaluateResultType.SUCCESS)
        })

        it('can call function in a realm', async function () {
            const firstTab = await driver.getWindowHandle()
            await driver.switchTo().newWindow('tab')
            const manager = await ScriptManager(firstTab, driver)

            const realms = await manager.getAllRealms()
            const realmId = realms[0].realmId

            await manager.callFunctionInRealm(realmId, '() => { window.foo = 3; }', true)

            const result = await manager.callFunctionInRealm(realmId, '() => window.foo', true)

            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 3)
        })

        it('can evaluate script', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.evaluateFunctionInBrowsingContext(id, '1 + 2', true)

            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 3)
        })

        it('can evaluate script that throws exception', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.evaluateFunctionInBrowsingContext(
                id,
                '))) !!@@## some invalid JS script (((',
                false,
            )

            assert.equal(result.resultType, EvaluateResultType.EXCEPTION)
            assert.equal(result.exceptionDetails.exception.type, 'error')
            assert.equal(result.exceptionDetails.text, "SyntaxError: expected expression, got ')'")
            assert.equal(result.exceptionDetails.columnNumber, 39)
            assert.equal(result.exceptionDetails.stackTrace.callFrames.length, 0)
        })

        it('can evaluate script with result ownership', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const result = await manager.evaluateFunctionInBrowsingContext(
                id,
                'Promise.resolve({a:1})',
                true,
                ResultOwnership.ROOT,
            )

            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.notEqual(result.result.handle, null)
        })

        it('can evaluate in a sandbox', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            await manager.evaluateFunctionInBrowsingContext(id, 'window.foo = 2', true, null, 'sandbox')

            const resultInSandbox = await manager.evaluateFunctionInBrowsingContext(id, 'window.foo', true, null, 'sandbox')

            assert.equal(resultInSandbox.resultType, EvaluateResultType.SUCCESS)
            assert.equal(resultInSandbox.result.value, 2)
        })

        it('can evaluate in a realm', async function () {
            const firstTab = await driver.getWindowHandle()
            await driver.switchTo().newWindow('tab')
            const manager = await ScriptManager(firstTab, driver)

            const realms = await manager.getAllRealms()
            const realmId = realms[0].realmId

            await manager.evaluateFunctionInRealm(realmId, 'window.foo = 3', true)

            const result = await manager.evaluateFunctionInRealm(realmId, 'window.foo', true)

            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.equal(result.result.value, 3)
        })

        it('can disown handles', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const browsingContext = await BrowsingContext(driver, {browsingContextId: id})

            const info = await browsingContext.navigate(
                'https://www.selenium.dev/selenium/web/dynamic.html',
                'complete'
            )

            await driver.findElement(By.id('adder')).click()

            await driver.wait(until.elementLocated(By.id('box0')), 10000)

            const evaluateResult = await manager.evaluateFunctionInBrowsingContext(
                id,
                "document.querySelector('.redbox');",
                false,
                ResultOwnership.ROOT,
            )

            assert.equal(evaluateResult.resultType, EvaluateResultType.SUCCESS)
            let boxId = evaluateResult.result.handle

            await manager.disownBrowsingContextScript(id, boxId)

            await manager.callFunctionInBrowsingContext(id, 'arg => arg.a', false).catch((error) => {
                assert(error instanceof WebDriverError)
            })
        })

        it('can disown handles in realm', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const browsingContext = await BrowsingContext(driver, {browsingContextId: id})

            const info = await browsingContext.navigate(
                'https://www.selenium.dev/selenium/web/dynamic.html',
                'complete'
            )

            await driver.findElement(By.id('adder')).click()

            await driver.wait(until.elementLocated(By.id('box0')), 10000)

            const realms = await manager.getAllRealms()
            const realmId = realms[0].realmId

            const evaluateResult = await manager.evaluateFunctionInBrowsingContext(
                id,
                "document.querySelector('.redbox');",
                false,
                ResultOwnership.ROOT,
            )

            assert.equal(evaluateResult.resultType, EvaluateResultType.SUCCESS)
            let boxId = evaluateResult.result.handle

            let argumentValues = []
            let value1 = new ArgumentValue(new ReferenceValue(RemoteReferenceType.HANDLE, boxId))
            argumentValues.push(value1)
            let checkHandle = await manager.callFunctionInBrowsingContext(id, 'arg => arg.a', false, argumentValues)

            assert.equal(checkHandle.resultType, EvaluateResultType.SUCCESS)

            await manager.disownRealmScript(realmId, boxId)

            await manager.callFunctionInBrowsingContext(id, 'arg => arg.a', false).catch((error) => {
                assert(error instanceof WebDriverError)
            })
        })

        it('can get all realms', async function () {
            const firstWindow = await driver.getWindowHandle()
            await driver.switchTo().newWindow('window')
            const secondWindow = await driver.getWindowHandle()
            const manager = await ScriptManager(firstWindow, driver)

            const realms = await manager.getAllRealms()
            assert.equal(realms.length, 2)
        })

        it('can get realm by type', async function () {
            const firstWindow = await driver.getWindowHandle()
            await driver.switchTo().newWindow('window')
            const secondWindow = await driver.getWindowHandle()
            const manager = await ScriptManager(firstWindow, driver)

            const realms = await manager.getRealmsByType(RealmType.WINDOW)
            assert.equal(realms.length, 2)
        })

        it('can get realm in browsing context', async function () {
            const windowId = await driver.getWindowHandle()
            await driver.switchTo().newWindow('tab')
            const tabId = await driver.getWindowHandle()
            const manager = await ScriptManager(windowId, driver)

            const realms = await manager.getRealmsInBrowsingContext(tabId)

            const tabRealm = realms[0]
            assert.equal(tabRealm.realmType, RealmType.WINDOW)
        })

        it('can get realm in browsing context by type', async function () {
            const windowId = await driver.getWindowHandle()
            await driver.switchTo().newWindow('tab')
            const manager = await ScriptManager(windowId, driver)

            const realms = await manager.getRealmsInBrowsingContextByType(windowId, RealmType.WINDOW)

            const windowRealm = realms[0]
            assert.equal(windowRealm.realmType, RealmType.WINDOW)
        })

        it('can add preload script', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const scriptId = await manager.addPreloadScript('() => {{ console.log(\'{preload_script_console_text}\') }}')

            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onConsoleEntry(function (log) {
                logEntry = log
            })

            await driver.get('https://www.selenium.dev/selenium/blank')

            assert.equal(logEntry.text, '{preload_script_console_text}')
        })

        it('can add preload script to sandbox', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            await manager.addPreloadScript('() => { window.bar = 2; }', undefined, 'sandbox')

            await driver.get('https://www.selenium.dev/selenium/blank')

            let result_in_sandbox = await manager.evaluateFunctionInBrowsingContext(
                id,
                'window.bar',
                true,
                null,
                'sandbox',
            )

            assert.equal(result_in_sandbox.result.type, 'number')
            assert.equal(result_in_sandbox.result.value, 2)
        })

        it('can remove preload script', async function () {
            const id = await driver.getWindowHandle()
            const manager = await ScriptManager(id, driver)

            const scriptId = await manager.addPreloadScript('() => {{ console.log(\'{preload_script_console_text}\') }}')

            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onConsoleEntry(function (log) {
                logEntry = log
            })

            await manager.removePreloadScript(scriptId)

            await driver.get('https://www.selenium.dev/selenium/blank')

            assert.equal(logEntry, null)
        })
    })
}, {browsers: ['firefox']})
