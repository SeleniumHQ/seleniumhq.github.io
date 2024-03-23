const { suite } = require('selenium-webdriver/testing');
const { By, Browser, until } = require('selenium-webdriver');
const assert = require("node:assert");

suite(function (env) {
    describe('Interactions - Alerts', function () {
        let driver;

        before(async function () {
            driver = await env.builder().build();
        });

        after(async () => await driver.quit());

        it('Should be able to getText from alert', async function () {
            await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
            await driver.findElement(By.id("alert")).click();
            await driver.wait(until.alertIsPresent());
            let alert = await driver.switchTo().alert();
            let alertText = await alert.getText();
            // Verify
            assert.equal(alertText, "cheese")
            //Press the OK button
            await alert.accept();
        });
    });
}, { browsers: [Browser.CHROME] });