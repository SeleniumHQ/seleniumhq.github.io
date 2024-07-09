const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const BrowsingContextInspector = require("selenium-webdriver/bidi/browsingContextInspector");
const BrowsingContext = require("selenium-webdriver/bidi/browsingContext");

suite(function (env) {
    describe('Browsing Context Inspector', function () {
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

        it('can listen to window browsing context created event', async function () {
            let contextInfo = null
            const browsingContextInspector = await BrowsingContextInspector(driver)
            await browsingContextInspector.onBrowsingContextCreated((entry) => {
                contextInfo = entry
            })

            await driver.switchTo().newWindow('window')
            const windowHandle = await driver.getWindowHandle()
            assert.equal(contextInfo.id, windowHandle)
            assert.equal(contextInfo.url, 'about:blank')
            assert.equal(contextInfo.children, null)
            assert.equal(contextInfo.parentBrowsingContext, null)
        })

        it('can listen to tab browsing context created event', async function () {
            let contextInfo = null
            const browsingContextInspector = await BrowsingContextInspector(driver)
            await browsingContextInspector.onBrowsingContextCreated((entry) => {
                contextInfo = entry
            })

            await driver.switchTo().newWindow('tab')
            const tabHandle = await driver.getWindowHandle()

            assert.equal(contextInfo.id, tabHandle)
            assert.equal(contextInfo.url, 'about:blank')
            assert.equal(contextInfo.children, null)
            assert.equal(contextInfo.parentBrowsingContext, null)
        })

        it('can listen to dom content loaded event', async function () {
            const browsingContextInspector = await BrowsingContextInspector(driver)
            let navigationInfo = null
            await browsingContextInspector.onDomContentLoaded((entry) => {
                navigationInfo = entry
            })

            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: await driver.getWindowHandle(),
            })
            await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', 'complete')

            assert.equal(navigationInfo.browsingContextId, browsingContext.id)
            assert.strictEqual(navigationInfo.url.includes('/bidi/logEntryAdded.html'), true)
        })

        it('can listen to browsing context loaded event', async function () {
            let navigationInfo = null
            const browsingContextInspector = await BrowsingContextInspector(driver)

            await browsingContextInspector.onBrowsingContextLoaded((entry) => {
                navigationInfo = entry
            })
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: await driver.getWindowHandle(),
            })
            await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', 'complete')

            assert.equal(navigationInfo.browsingContextId, browsingContext.id)
            assert.strictEqual(navigationInfo.url.includes('/bidi/logEntryAdded.html'), true)
        })

        it('can listen to fragment navigated event', async function () {
            let navigationInfo = null
            const browsingContextInspector = await BrowsingContextInspector(driver)

            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: await driver.getWindowHandle(),
            })
            await browsingContext.navigate('https://www.selenium.dev/selenium/web/linked_image.html', 'complete')

            await browsingContextInspector.onFragmentNavigated((entry) => {
                navigationInfo = entry
            })

            await browsingContext.navigate('https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage', 'complete')

            assert.equal(navigationInfo.browsingContextId, browsingContext.id)
            assert.strictEqual(navigationInfo.url.includes('linkToAnchorOnThisPage'), true)
        })

        it('can listen to browsing context destroyed event', async function () {
            let contextInfo = null
            const browsingContextInspector = await BrowsingContextInspector(driver)
            await browsingContextInspector.onBrowsingContextDestroyed((entry) => {
                contextInfo = entry
            })

            await driver.switchTo().newWindow('window')

            const windowHandle = await driver.getWindowHandle()
            await driver.close()

            assert.equal(contextInfo.id, windowHandle)
            assert.equal(contextInfo.url, 'about:blank')
            assert.equal(contextInfo.children, null)
            assert.equal(contextInfo.parentBrowsingContext, null)
        })
    })
}, {browsers: ['firefox']})
