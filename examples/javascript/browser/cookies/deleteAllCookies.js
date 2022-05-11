const {Builder} = require('selenium-webdriver');

(async function example() {
  let driver = new Builder()
    .forBrowser('chrome')
    .build();

  try {
    await driver.get('https://www.example.com');
    // Add few cookies
    await driver.manage().addCookie({name: 'test1', value: 'cookie1'});
    await driver.manage().addCookie({name: 'test2', value: 'cookie2'});
    // Delete all cookies
    await driver.manage().deleteAllCookies();
    await driver.quit();
  } catch(e) {
    console.log(e)
  }
})();