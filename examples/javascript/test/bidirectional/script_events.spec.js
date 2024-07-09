const {suite} = require('selenium-webdriver/testing')
const assert = require("assert")
const firefox = require('selenium-webdriver/firefox')
const {ScriptManager, BrowsingContext} = require("selenium-webdriver")
const {ArgumentValue} = require("selenium-webdriver/bidi/argumentValue")
const {RealmType} = require("selenium-webdriver/bidi/realmInfo")
const {LocalValue, ChannelValue} = require("selenium-webdriver/bidi/protocolValue")
const {EvaluateResultType} = require("selenium-webdriver/bidi/evaluateResult");

suite(function (env) {
    describe('Script events', function () {
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

        it('can listen to channel message', async function () {
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
            assert.equal(result.resultType, EvaluateResultType.SUCCESS)
            assert.notEqual(message, null)
            assert.equal(message.channel, 'channel_name')
            assert.equal(message.data.type, 'string')
            assert.equal(message.data.value, 'foo')
        })

        it('can listen to realm created message', async function () {
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

            assert.notEqual(realmInfo, null)
            assert.notEqual(realmInfo.realmId, null)
            assert.equal(realmInfo.realmType, RealmType.WINDOW)
        })

        xit('can listen to realm destroyed message', async function () {
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

            assert.notEqual(realmInfo, null)
            assert.notEqual(realmInfo.realmId, null)
            assert.equal(realmInfo.realmType, RealmType.WINDOW)
        })
    })
}, {browsers: ['firefox']})
