const {Builder, By, Key, until} = require('selenium-webdriver');

(async function firstScript() {
  try {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://www.google.com');
    await driver.getTitle();

    driver.manage().setTimeouts({implicit: 1000})
    driver.manage().window().maximize();

    let searchBox = await driver.findElement(By.name('q'));
    let searchButton = await driver.findElement(By.name('btnK'));

    await searchBox.sendKeys('Selenium');
    await searchButton.click();

    await driver.findElement(By.name('q')).getAttribute("value");

    await driver.quit();
  } catch (error) {
    console.log(error)
  }
})();