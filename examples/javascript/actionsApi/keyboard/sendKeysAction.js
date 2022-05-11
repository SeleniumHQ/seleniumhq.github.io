const {Builder, By, Key} = require('selenium-webdriver');

(async function sendKeysExample() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to the url
    await driver.get('https://google.com');
    // Find google search box element
    let search = driver.findElement(By.name('q'));
    // get focus on Search
    await driver.executeScript('arguments[0].focus()', search)
    // Send value by action class to the search box
    await driver.actions().sendKeys('Selenium').perform();
    // Perform Keyboard action by Action class
    await driver.actions().sendKeys(Key.ENTER).perform();
  }
  finally {
    await driver.quit();
  }
})();