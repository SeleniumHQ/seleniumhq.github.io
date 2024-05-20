const {Builder } = require('selenium-webdriver');
const assert = require("node:assert");

describe('Interactions', function () {
  let driver;

  before(async function () {
    driver = new Builder()
      .forBrowser('chrome')
      .build();
  });

  after(async () => await driver.quit());

  it('Should be able to get title and current url', async function () {
    const url = 'https://www.selenium.dev/';
    await driver.get(url);

    //Get Current title
    let title = await driver.getTitle();
    assert.equal(title, "Selenium");

    //Get Current url
    let currentUrl = await driver.getCurrentUrl();
    assert.equal(currentUrl, url);
  });
});