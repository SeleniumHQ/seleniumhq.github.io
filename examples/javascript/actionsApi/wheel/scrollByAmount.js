const { Builder, By } = require('selenium-webdriver');

(async function scrollByGivenAmountFromElement() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.manage().window().setRect({ width: 500, height: 400 });
    // Navigate to url
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    // Store element
    let element = await driver.findElement(By.linkText('Go To Page 2'));
    // Scroll to element by 300
    await driver.actions().scroll(0, 0, 0, 300, element).perform();
  }
  finally {
    await driver.quit();
  }
})();