const { By, Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const assert = require("assert");

suite(function(env) {
    describe('First script', function() {
        let driver;

        before(async function() {
            driver = await new Builder().forBrowser('chrome').build();
        });

        after(() => driver.quit());

        it('First Selenium script', async function() {
            await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');

            let title = await driver.getTitle();
            assert.ok(title.includes("Selenium"));

            await driver.manage().setTimeouts({ implicit: 5000 });

            let searchBox = await driver.findElement(By.name('text'));
            let searchButton = await driver.findElement(By.id('submitbtn'));

            await searchBox.sendKeys('Selenium');
            await searchButton.click();

            searchBox = await driver.findElement(By.id('form-results'));
            let value = await searchBox.getText();
            assert.ok(value.includes("Selenium"));
        });

    });
});