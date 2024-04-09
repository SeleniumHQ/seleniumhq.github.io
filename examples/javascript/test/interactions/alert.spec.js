
const { By, Builder, until } = require('selenium-webdriver');
const assert = require("node:assert");


describe('Interactions - Alerts', function () {
    let driver;

    before(async function () {
        driver = await new Builder().forBrowser('chrome').build();
    });

    after(async () => await driver.quit());

    it('Should be able to getText from alert and accept', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
        await driver.findElement(By.id("alert")).click();
        await driver.wait(until.alertIsPresent());
        let alert = await driver.switchTo().alert();
        let alertText = await alert.getText();
        await alert.accept();
        // Verify
        assert.equal(alertText, "cheese");
    });

    it('Should be able to getText from alert and dismiss', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
        await driver.findElement(By.id("confirm")).click();
        await driver.wait(until.alertIsPresent());
        let alert = await driver.switchTo().alert();
        let alertText = await alert.getText();
        await alert.dismiss();
        // Verify
        assert.equal(alertText, "Are you sure?");
    });

    it('Should be able to enter text in alert prompt', async function () {
        let text = 'Selenium';
        await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
        await driver.findElement(By.id("prompt")).click();
        await driver.wait(until.alertIsPresent());
        let alert = await driver.switchTo().alert();
        //Type your message
        await alert.sendKeys(text);
        await alert.accept();

        let enteredText = await driver.findElement(By.id('text'));
        assert.equal(await enteredText.getText(), text);
    });
});