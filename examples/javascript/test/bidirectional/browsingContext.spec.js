const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const BrowsingContext = require('selenium-webdriver/bidi/browsingContext');
const {By, until} = require("selenium-webdriver");

suite(function (env) {
    describe('Browsing Context', function () {
        let driver
        let startIndex = 0
        let endIndex = 5
        let pdfMagicNumber = 'JVBER'
        let pngMagicNumber = 'iVBOR'

        beforeEach(async function () {
            driver = await env
                .builder()
                .setFirefoxOptions(new firefox.Options().enableBidi())
                .build()
        })

        afterEach(async function () {
            await driver.quit()
        })

        it('test create a browsing context for given id', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })
            assert.equal(browsingContext.id, id)
        })

        it('test create a window', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'window',
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a window with a reference context', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'window',
                referenceContext: await driver.getWindowHandle(),
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a tab', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'tab',
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a tab with a reference context', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'tab',
                referenceContext: await driver.getWindowHandle(),
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test navigate to a url', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'tab',
            })

            let info = await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

            assert.notEqual(browsingContext.id, null)
            assert.notEqual(info.navigationId, null)
            assert(info.url.includes('/bidi/logEntryAdded.html'))
        })

        it('test navigate to a url with readiness state', async function () {
            const browsingContext = await BrowsingContext(driver, {
                type: 'tab',
            })

            const info = await browsingContext.navigate(
                'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
                'complete'
            )

            assert.notEqual(browsingContext.id, null)
            assert.notEqual(info.navigationId, null)
            assert(info.url.includes('/bidi/logEntryAdded.html'))
        })

        it('test get tree with a child', async function () {
            const browsingContextId = await driver.getWindowHandle()
            const parentWindow = await BrowsingContext(driver, {
                browsingContextId: browsingContextId,
            })
            await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')

            const contextInfo = await parentWindow.getTree()
            assert.equal(contextInfo.children.length, 1)
            assert.equal(contextInfo.id, browsingContextId)
            assert(contextInfo.children[0]['url'].includes('formPage.html'))
        })

        it('test get tree with depth', async function () {
            const browsingContextId = await driver.getWindowHandle()
            const parentWindow = await BrowsingContext(driver, {
                browsingContextId: browsingContextId,
            })
            await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')

            const contextInfo = await parentWindow.getTree(0)
            assert.equal(contextInfo.children, null)
            assert.equal(contextInfo.id, browsingContextId)
        })

        it('test close a window', async function () {
            const window1 = await BrowsingContext(driver, {type: 'window'})
            const window2 = await BrowsingContext(driver, {type: 'window'})

            await window2.close()

            assert.doesNotThrow(async function () {
                await window1.getTree()
            })
            await assert.rejects(window2.getTree(), {message: 'no such frame'})
        })

        it('test close a tab', async function () {
            const tab1 = await BrowsingContext(driver, {type: 'tab'})
            const tab2 = await BrowsingContext(driver, {type: 'tab'})

            await tab2.close()

            assert.doesNotThrow(async function () {
                await tab1.getTree()
            })
            await assert.rejects(tab2.getTree(), {message: 'no such frame'})
        })

        it('can print PDF with all valid parameters', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/printPage.html")
            const result = await browsingContext.printPage({
                orientation: 'landscape',
                scale: 1,
                background: true,
                width: 30,
                height: 30,
                top: 1,
                bottom: 1,
                left: 1,
                right: 1,
                shrinkToFit: true,
                pageRanges: ['1-2'],
            })

            let base64Code = result.data.slice(0, 5)
            assert.strictEqual(base64Code, 'JVBER')
        })

        it('can take box screenshot', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            const response = await browsingContext.captureBoxScreenshot(5, 5, 10, 10)

            const base64code = response.slice(0, 5)
            assert.equal(base64code, 'iVBOR')
        })

        it('can take element screenshot', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/formPage.html")
            const element = await driver.findElement(By.id('checky'))
            const elementId = await element.getId()
            const response = await browsingContext.captureElementScreenshot(elementId)

            const base64code = response.slice(0, 5)
            assert.equal(base64code, 'iVBOR')
        })

        it('can activate a browsing context', async function () {
            const id = await driver.getWindowHandle()
            const window1 = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await BrowsingContext(driver, {
                type: 'window',
            })

            const result = await driver.executeScript('return document.hasFocus();')

            assert.equal(result, false)

            await window1.activate()
            const result2 = await driver.executeScript('return document.hasFocus();')

            assert.equal(result2, true)
        })

        it('can handle user prompt', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('alert')).click()

            await driver.wait(until.alertIsPresent())

            await browsingContext.handleUserPrompt()

            const result = await driver.getTitle()

            assert.equal(result, 'Testing Alerts')
        })

        it('can accept user prompt', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('alert')).click()

            await driver.wait(until.alertIsPresent())

            await browsingContext.handleUserPrompt(true)

            const result = await driver.getTitle()

            assert.equal(result, 'Testing Alerts')
        })

        it('can dismiss user prompt', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('alert')).click()

            await driver.wait(until.alertIsPresent())

            await browsingContext.handleUserPrompt(false)

            const result = await driver.getTitle()

            assert.equal(result, 'Testing Alerts')
        })

        it('can pass user text to user prompt', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('prompt')).click()

            await driver.wait(until.alertIsPresent())

            const userText = 'Selenium automates browsers'

            await browsingContext.handleUserPrompt(undefined, userText)

            const result = await driver.getPageSource()
            assert.equal(result.includes(userText), true)
        })

        it('can accept user prompt with user text', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('prompt')).click()

            await driver.wait(until.alertIsPresent())

            const userText = 'Selenium automates browsers'

            await browsingContext.handleUserPrompt(true, userText)

            const result = await driver.getPageSource()
            assert.equal(result.includes(userText), true)
        })

        it('can dismiss user prompt with user text', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/alerts.html")

            await driver.findElement(By.id('alert')).click()

            await driver.wait(until.alertIsPresent())

            const userText = 'Selenium automates browsers'

            await browsingContext.handleUserPrompt(false, userText)

            const result = await driver.getPageSource()
            assert.equal(result.includes(userText), false)
        })

        it('can set viewport', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await driver.get("https://www.selenium.dev/selenium/web/blank.html")

            await browsingContext.setViewport(250, 300)

            const result = await driver.executeScript('return [window.innerWidth, window.innerHeight];')
            assert.equal(result[0], 250)
            assert.equal(result[1], 300)
        })

        it('can reload a browsing context', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            const result = await browsingContext.navigate(
                'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', 'complete')

            await browsingContext.reload(undefined, 'complete')
            assert.notEqual(result.navigationId, null)
            assert(result.url.includes('/bidi/logEntryAdded.html'))
        })

        it('can take screenshot', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            const response = await browsingContext.captureScreenshot()
            const base64code = response.slice(startIndex, endIndex)
            assert.equal(base64code, pngMagicNumber)
        })

        it('can traverse browser history', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await browsingContext.navigate('https://www.selenium.dev/selenium/web/formPage.html', 'complete')

            await driver.wait(until.elementLocated(By.id('imageButton'))).submit()
            await driver.wait(until.titleIs('We Arrive Here'), 2500)

            await browsingContext.traverseHistory(-1)

            const source = await driver.getPageSource()

            assert.equal(source.includes('We Leave From Here'), true)
        })

        it('can navigate back in browser history', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await browsingContext.navigate('https://www.selenium.dev/selenium/web/formPage.html', 'complete')

            await driver.wait(until.elementLocated(By.id('imageButton'))).submit()
            await driver.wait(until.titleIs('We Arrive Here'), 2500)

            await browsingContext.back()

            const source = await driver.getPageSource()

            assert.equal(source.includes('We Leave From Here'), true)
        })

        it('can navigate forward in browser history', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
                browsingContextId: id,
            })

            await browsingContext.navigate('https://www.selenium.dev/selenium/web/formPage.html', 'complete')

            await driver.wait(until.elementLocated(By.id('imageButton'))).submit()
            await driver.wait(until.titleIs('We Arrive Here'), 2500)

            await browsingContext.back()

            const source = await driver.getPageSource()

            assert.equal(source.includes('We Leave From Here'), true)

            await browsingContext.forward()

            await driver.wait(until.titleIs('We Arrive Here'), 2500)
        })
    })
}, {browsers: ['firefox']})
