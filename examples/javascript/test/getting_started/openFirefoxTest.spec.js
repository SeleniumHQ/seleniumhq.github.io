const { Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const firefox = require('selenium-webdriver/firefox');

suite(function(env) {
    describe('Open Firefox', function() {
        let driver;

        before(async function() {
            let options = new firefox.Options();
            driver = await new Builder()
                .setFirefoxOptions(options)
                .forBrowser('firefox')
                .build();
        });

        after(() => driver.quit());

        it('Basic Firefox test', async function() {
            await driver.get('https://www.google.com');
        });

    });
});