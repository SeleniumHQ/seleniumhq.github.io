const {suite} = require('selenium-webdriver/testing')
const assert = require("assert")
const firefox = require('selenium-webdriver/firefox')
const Network = require('selenium-webdriver/bidi/network')
const {until, By} = require('selenium-webdriver')
const {AddInterceptParameters} = require("selenium-webdriver/bidi/addInterceptParameters");
const {InterceptPhase} = require("selenium-webdriver/bidi/interceptPhase");

suite(function (env) {
    describe('Network commands', function () {
        let driver
        let network

        beforeEach(async function () {
            driver = await env
                .builder()
                .setFirefoxOptions(new firefox.Options().enableBidi())
                .build()

            network = await Network(driver)
        })

        afterEach(async function () {
            await network.close()
            await driver.quit()
        })

        xit('can add intercept', async function () {
            const intercept = await network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT))
            assert.notEqual(intercept, null)
        })

        xit('can remove intercept', async function () {
            const network = await Network(driver)
            const intercept = await network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT))
            assert.notEqual(intercept, null)

            await network.removeIntercept(intercept)
        })

        xit('can continue with auth credentials ', async function () {
            await network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED))

            await network.authRequired(async (event) => {
                await network.continueWithAuth(event.request.request, 'admin','admin')
            })
            await driver.get('https://the-internet.herokuapp.com/basic_auth')

            const successMessage = 'Congratulations! You must have the proper credentials.'

            let elementMessage = await driver.findElement(By.tagName('p')).getText()
            assert.equal(elementMessage, successMessage)
        })

        xit('can continue without auth credentials ', async function () {
            await network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED))

            await network.authRequired(async (event) => {
                await network.continueWithAuthNoCredentials(event.request.request)
            })

            await driver.get('https://the-internet.herokuapp.com/basic_auth')
            const alert = await driver.wait(until.alertIsPresent())
            await alert.dismiss()

            let source = await driver.getPageSource()
            assert.equal(source.includes('Not authorized'), true)
        })

        xit('can cancel auth ', async function () {
            await network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED))

            await network.authRequired(async (event) => {
                await network.cancelAuth(event.request.request)
            })

            await driver.get('https://the-internet.herokuapp.com/basic_auth')
            let source = await driver.getPageSource()
            assert.equal(source.includes('Not authorized'), true)
        })
    })
}, {browsers: ['firefox']})
