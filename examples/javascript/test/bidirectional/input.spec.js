const {suite} = require('selenium-webdriver/testing')
const assert = require("assert")
const firefox = require('selenium-webdriver/firefox')
const {By, Key} = require("selenium-webdriver")
const Input = require('selenium-webdriver/bidi/input')

suite(function (env) {
    describe('Input module', function () {
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

        it('can perform input action', async function () {
            const browsingContextId = await driver.getWindowHandle()
            const input = await Input(driver)
            await driver.get('https://www.selenium.dev/selenium/web/formSelectionPage.html')

            let options = await driver.findElements(By.tagName('option'))

            const actions = driver.actions().click(options[1]).keyDown(Key.SHIFT).click(options[3]).keyUp(Key.SHIFT).getSequences()

            await input.perform(browsingContextId, actions)

            let showButton = await driver.findElement(By.name('showselected'))
            showButton.click()

            let resultElement = await driver.findElement(By.id('result'))
            await resultElement.getText().then(function (text) {
                assert(text.includes('oquefort parmigiano cheddar'))
            })
        })

        it('can execute release in browsing context', async function () {
            const browsingContextId = await driver.getWindowHandle()
            const input = await Input(driver)
            await driver.get('https://www.selenium.dev/selenium/web/bidi/release_action.html')

            let inputTextBox = await driver.findElement(By.id('keys'))

            await driver.executeScript('arguments[0].focus()', inputTextBox)

            const actions = await driver.actions().keyDown('a').keyDown('b').getSequences()

            await input.perform(browsingContextId, actions)

            await driver.executeScript('resetEvents()')

            await input.release(browsingContextId)

            const events = await driver.executeScript('return allEvents.events')

            assert.strictEqual(events[0].code, 'KeyB')
            assert.strictEqual(events[1].code, 'KeyA')
        })
    })
}, {browsers: ['firefox']})
