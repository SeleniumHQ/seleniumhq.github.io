const {Builder, By} = require('selenium-webdriver');

(async function sendKeysExample() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to the url
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    // Find close button element
    let element = driver.findElement(By.id('closepopup'));
    // Scroll to the element
    await driver.actions().scroll(0, 0, 0, 0, element).perform();
  }
  finally {
    await driver.quit();
  }
})();