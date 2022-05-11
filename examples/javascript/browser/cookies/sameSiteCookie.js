const {Builder} = require('selenium-webdriver');

(async function sameSiteCookieExample() {
  let driver = new Builder()
    .forBrowser('chrome')
    .build();

  try {
    await driver.get('https://www.example.com');
    // set a cookie on the current domain with sameSite 'Strict' (or) 'Lax'
    await driver.manage().addCookie({name: 'key', value: 'value', sameSite: 'Strict'});
    await driver.manage().addCookie({name: 'key', value: 'value', sameSite: 'Lax'});
    await driver.quit();
  } catch (e) {
    console.log(e);
  }
})();