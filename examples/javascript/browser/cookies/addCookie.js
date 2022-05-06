const {Builder} = require('selenium-webdriver');
(async function example() {
  let driver = new Builder()
    .forBrowser('chrome')
    .build();

  await driver.get('https://www.example.com');
  // set a cookie on the current domain
  await driver.manage().addCookie({name:'key', value: 'value'});
  await driver.quit();
})();