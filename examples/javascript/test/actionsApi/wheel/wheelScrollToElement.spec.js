const { By, Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');

suite(function(env) {
    describe('Whell scroll to element', function() {
        let driver;

        before(async function() {
            driver = await new Builder().forBrowser('chrome').build();
        });

        after(() => driver.quit());

        it('Scroll to the element', async function() {
            // Navigate to the url
            await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');

            // Find close button element
            let element = driver.findElement(By.id('closepopup'));

            // Scroll to the element
            await driver.actions().scroll(0, 0, 0, 0, element).perform();
        });

    });
});