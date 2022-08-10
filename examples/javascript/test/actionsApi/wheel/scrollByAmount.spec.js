const { By, Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');

suite(function(env) {
    describe('Scroll by given amount from element', function() {
        let driver;

        before(async function() {
            driver = await new Builder().forBrowser('chrome').build();
        });

        after(() => driver.quit());

        it('Scroll to element by 300', async function() {
            await driver.manage().window().setRect({ width: 500, height: 400 });

            // Navigate to url
            await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');

            // Store element
            let element = await driver.findElement(By.linkText('Go To Page 2'));

            // Scroll to element by 300
            await driver.actions().scroll(0, 0, 0, 300, element).perform();
        });

    });
});