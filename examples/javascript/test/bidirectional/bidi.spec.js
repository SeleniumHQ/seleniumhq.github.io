const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const LogInspector = require('selenium-webdriver/bidi/logInspector');
const BrowsingContext = require('selenium-webdriver/bidi/browsingContext');
const {until} = require('selenium-webdriver');

suite(function (env) {
    describe('Integration Tests', function () {
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

        it('test navigate and listen to errors', async function () {
            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onJavascriptException(function (log) {
                logEntry = log
            })

            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            let info = await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

            assert.notEqual(browsingContext.id, null)
            assert.notEqual(info.navigationId, null)
            assert(info.url.includes('/bidi/logEntryAdded.html'))

            await driver.wait(until.urlIs('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'))
            await driver.findElement({id: 'jsException'}).click()

            assert.equal(logEntry.text, 'Error: Not working')
            assert.equal(logEntry.type, 'javascript')
            assert.equal(logEntry.level, 'error')

            await inspector.close()
            await browsingContext.close()
        })
    })
}, {browsers: ['firefox']})