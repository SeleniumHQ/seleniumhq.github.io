const {Builder } = require('selenium-webdriver');
const assert = require("node:assert");

describe('Interactions - Navigation', function () {
    let driver;

    before(async function () {
        driver = new Builder()
          .forBrowser('chrome')
          .build();
    });

    after(async () => await driver.quit());

    it('Browser navigation test', async function () {
        //Convenient
        await driver.get('https://www.selenium.dev');

        //Longer way
        await driver.navigate().to("https://www.selenium.dev/selenium/web/index.html");
        let title = await driver.getTitle();
        assert.equal(title, "Index of Available Pages");

        //Back
        await driver.navigate().back();
        title = await driver.getTitle();
        assert.equal(title, "Selenium");

        //Forward
        await driver.navigate().forward();
        title = await driver.getTitle();
        assert.equal(title, "Index of Available Pages");

        //Refresh
        await driver.navigate().refresh();
        title = await driver.getTitle();
        assert.equal(title, "Index of Available Pages");
    });
});