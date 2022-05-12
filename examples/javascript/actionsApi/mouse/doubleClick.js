const {Builder, By} = require('selenium-webdriver');

(async function doubleClick() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Sign in' button web element
    let signIn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform double-click action on the element
    await actions.doubleClick(signIn).perform();
  }
  finally {
    await driver.quit();
  }
})();