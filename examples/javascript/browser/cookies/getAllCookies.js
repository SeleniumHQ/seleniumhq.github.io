const {Builder} = require('selenium-webdriver');

(async function getAllCookies() {
  let driver = new Builder()
    .forBrowser('chrome')
    .build();

  await driver.get('https://www.example.com');
  // Add few cookies
  await driver.manage().addCookie({name:'test1', value:'cookie1'});
  await driver.manage().addCookie({name:'test2', value:'cookie2'});
  // Get all Available cookies
  await driver.manage().getCookies().then(function (cookies) {
    console.log('cookie details => ', cookies);
  });
  await driver.quit()
})();