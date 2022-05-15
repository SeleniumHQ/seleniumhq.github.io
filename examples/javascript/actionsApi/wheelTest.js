const {Builder, By, Key, until} = require('selenium-webdriver');

(async function scrolling() {
  try {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.quit();
  } catch (error) {
    console.log(error)
  }
})();