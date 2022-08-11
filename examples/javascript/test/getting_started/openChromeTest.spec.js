const { Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const chrome = require('selenium-webdriver/chrome');

suite(function(env) {
    describe('Open Chrome', function() {
        let driver;

        before(async function() {
            let options = new chrome.Options();
            driver = await new Builder()
                .setChromeOptions(options)
                .forBrowser('chrome')
                .build();
        });

        after(() => driver.quit());

        it('Basic Chrome test', async function() {
            await driver.get('https://www.google.com');
        });

    });
});