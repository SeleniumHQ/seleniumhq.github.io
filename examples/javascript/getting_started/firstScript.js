const {Builder, By} = require('selenium-webdriver');
const assert = require('assert');

(async function firstScript() {
  try {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://www.google.com');

    await driver.getTitle();

    await driver.manage().setTimeouts({implicit: 1000})

    let searchBox = await driver.findElement(By.name('q'));
    let searchButton = await driver.findElement(By.name('btnK'));

    await searchBox.sendKeys('Selenium');
    await searchButton.click();

    let value = await searchBox.getAttribute("value");
    assert.deepStrictEqual(value, "Selenium")

    await driver.quit();
  } catch (error) {
    console.log(error)
  }
})();