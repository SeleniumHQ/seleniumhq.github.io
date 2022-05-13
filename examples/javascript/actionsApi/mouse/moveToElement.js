const {Builder, By} = require('selenium-webdriver');

(async function moveToElement() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Gmail' anchor web element
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    const actions = driver.actions({async: true});
    // Performs mouse move action onto the element
    await actions.move({origin:gmailLink}).perform();
  } finally {
    await driver.quit();
  }
})();