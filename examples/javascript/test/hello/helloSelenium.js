const {Builder} = require('selenium-webdriver');
require("chromedriver");

(async function helloSelenium() {
  let driver = await new Builder().forBrowser('chrome').build();

  await driver.get('https://selenium.dev');

  await driver.quit();
})();