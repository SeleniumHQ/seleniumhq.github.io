const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const {until} = require("selenium-webdriver");

suite(
    function (env) {
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

        function delay(ms) {
            return new Promise((resolve) => setTimeout(resolve, ms))
        }

        describe('BiDi Logging', function () {
            it('can listen to console log', async function () {
                let log = null
                const handler = await driver.script().addConsoleMessageHandler((logEntry) => {
                    log = logEntry
                })

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
                await driver.findElement({ id: 'consoleLog' }).click()

                await delay(3000)

                assert.equal(log.text, 'Hello, world!')
                await driver.script().removeConsoleMessageHandler(handler)
            })

            it('can remove console log handler', async function () {
                let log = null
                const handler = await driver.script().addConsoleMessageHandler((logEntry) => {
                    log = logEntry
                })

                await driver.script().removeConsoleMessageHandler(handler)

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
                await driver.findElement({ id: 'consoleLog' }).click()

                await delay(3000)

                assert.equal(log, null)
            })

            it('can listen to javascript error', async function () {
                let log = null
                const handler = await driver.script().addJavaScriptErrorHandler((logEntry) => {
                    log = logEntry
                })

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
                await driver.findElement({ id: 'jsException' }).click()

                await delay(3000)

                assert.equal(log.text, 'Error: Not working')
                assert.equal(log.type, 'javascript')
                assert.equal(log.level, 'error')

                await driver.script().removeJavaScriptErrorHandler(handler)
            })

            it('can remove to javascript error handler', async function () {
                let log = null
                const handler = await driver.script().addJavaScriptErrorHandler((logEntry) => {
                    log = logEntry
                })

                await driver.script().removeJavaScriptErrorHandler(handler)

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
                await driver.findElement({ id: 'jsException' }).click()

                await delay(3000)

                assert.equal(log, null)
            })

            it('can listen to dom mutations', async function () {
                let message = null
                await driver.script().addDomMutationHandler((m) => {
                    message = m
                })

                await driver.get('https://www.selenium.dev/selenium/web/dynamic')

                let element = driver.findElement({ id: 'reveal' })
                await element.click()
                let revealed = driver.findElement({ id: 'revealed' })
                await driver.wait(until.elementIsVisible(revealed), 5000)

                assert.strictEqual(message['attribute_name'], 'style')
                assert.strictEqual(message['current_value'], '')
                assert.strictEqual(message['old_value'], 'display:none;')
            })

            it('can remove to dom mutation handler', async function () {
                let message = null
                let id = await driver.script().addDomMutationHandler((m) => {
                    message = m
                })

                await driver.script().removeDomMutationHandler(id)

                await driver.get('https://www.selenium.dev/selenium/web/dynamic')

                let element = driver.findElement({ id: 'reveal' })
                await element.click()
                let revealed = driver.findElement({ id: 'revealed' })
                await driver.wait(until.elementIsVisible(revealed), 5000)

                assert.strictEqual(message, null)
            })

            it('can pin script', async function () {
                await driver.script().pin("() => { console.log('Hello!'); }")
                let log

                await driver.script().addConsoleMessageHandler((logEntry) => {
                    log = logEntry
                })

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

                await delay(3000)

                assert.equal(log.text, 'Hello!')
            })

            it('can unpin script', async function () {
                const id = await driver.script().pin("() => { console.log('Hello!'); }")

                let count = 0
                await driver.script().addConsoleMessageHandler((logEntry) => {
                    count++
                })

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

                await driver.script().unpin(id)

                await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

                assert.equal(count, 1)
            })
        })
    },
    { browsers: ['firefox'] },
)

