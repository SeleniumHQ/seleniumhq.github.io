
const { By, Browser, until, Builder} = require('selenium-webdriver');
const assert = require("node:assert");


describe('Waits', function () {
    let driver;

    before(async function () {
        driver = new Builder()
          .forBrowser(Browser.CHROME)
          .build();
    });

    after(async () => await driver.quit());

    it('fail', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/dynamic.html');
        await driver.findElement(By.id("adder")).click();

        await assert.rejects(async () => {
              await driver.findElement(By.id("box0"))
          },
          Error
        )
    });

    it('sleep', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/dynamic.html');
        await driver.findElement(By.id("adder")).click();

        await driver.sleep(2000);
        let added = await driver.findElement(By.id("box0"));

        assert.equal(await added.getAttribute('class'), "redbox")
    });

    it('implicit', async function () {
        await driver.manage().setTimeouts({ implicit: 2000 });
        await driver.get('https://www.selenium.dev/selenium/web/dynamic.html');
        await driver.findElement(By.id("adder")).click();

        let added = await driver.findElement(By.id("box0"));

        assert.equal(await added.getAttribute('class'), "redbox")
    });

    it('explicit', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/dynamic.html');
        let revealed = await driver.findElement(By.id("revealed"));
        await driver.findElement(By.id("reveal")).click();
        await driver.wait(until.elementIsVisible(revealed), 2000);
        await revealed.sendKeys("Displayed");
        assert.equal(await revealed.getAttribute("value"), "Displayed")
    })

    it('expected conditions', async function () {
        await driver.get('https://www.selenium.dev/selenium/web/web-form.html');
        await driver.findElement(By.id("my-text-id")).sendKeys("Selenium");
        await driver.findElement(By.css("button")).click();

        await driver.wait(until.titleIs("Web form - target page"), 2000);
        let message = await driver.wait(until.elementLocated(By.id("message")), 2000);
        await driver.wait(until.elementIsVisible(message), 2000);
        await driver.wait(until.elementTextIs(message, "Received!"), 2000);

        assert.equal(await message.getText(), "Received!")
    })
});