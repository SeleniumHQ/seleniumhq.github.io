const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const LogInspector = require('selenium-webdriver/bidi/logInspector');

suite(function (env) {
    describe('Log Inspector', function () {
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

        it('test listen to console log', async function () {
            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onConsoleEntry(function (log) {
                logEntry = log
            })

            await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
            await driver.findElement({id: 'consoleLog'}).click()

            assert.equal(logEntry.text, 'Hello, world!')
            assert.equal(logEntry.realm, null)
            assert.equal(logEntry.type, 'console')
            assert.equal(logEntry.level, 'info')
            assert.equal(logEntry.method, 'log')
            assert.equal(logEntry.stackTrace, null)
            assert.equal(logEntry.args.length, 1)

            await inspector.close()
        })

        it('test listen to javascript error log', async function () {
            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onJavascriptException(function (log) {
                logEntry = log
            })

            await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
            await driver.findElement({id: 'jsException'}).click()

            assert.equal(logEntry.text, 'Error: Not working')
            assert.equal(logEntry.type, 'javascript')
            assert.equal(logEntry.level, 'error')

            await inspector.close()
        })

        it('test retrieve stack trace for a log', async function () {
            let logEntry = null
            const inspector = await LogInspector(driver)
            await inspector.onJavascriptException(function (log) {
                logEntry = log
            })

            await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
            await driver.findElement({id: 'jsException'}).click()

            const stackTrace = logEntry.stackTrace
            assert.notEqual(stackTrace, null)
            assert.equal(stackTrace.callFrames.length, 3)

            await inspector.close()
        })

        it('test listen to logs with multiple consumers', async function () {
            let logEntry1 = null
            let logEntry2 = null
            const inspector = await LogInspector(driver)
            await inspector.onJavascriptException(function (log) {
                logEntry1 = log
            })
            await inspector.onJavascriptException(function (log) {
                logEntry2 = log
            })

            await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
            await driver.findElement({id: 'jsException'}).click()

            assert.equal(logEntry1.text, 'Error: Not working')
            assert.equal(logEntry1.type, 'javascript')
            assert.equal(logEntry1.level, 'error')

            assert.equal(logEntry2.text, 'Error: Not working')
            assert.equal(logEntry2.type, 'javascript')
            assert.equal(logEntry2.level, 'error')

            await inspector.close()
        })
    })
}, {browsers: ['firefox']})
