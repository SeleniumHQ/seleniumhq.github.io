const { By, Browser, Builder, until } = require('selenium-webdriver');
const assert = require('node:assert');

describe('Expected Conditions', function () {
    let driver;

    before(async function () {
        driver = new Builder()
          .forBrowser(Browser.CHROME)
          .build();
    });

    after(async () => await driver.quit());

    it('expected condition', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/dynamic.html');
        let revealed = await driver.findElement(By.id('revealed'));
        await driver.findElement(By.id('reveal')).click();

        await driver.wait(until.elementIsVisible(revealed), 2000);

        await revealed.sendKeys('Displayed');
        assert.equal(await revealed.getAttribute('value'), 'Displayed');
    });
});
