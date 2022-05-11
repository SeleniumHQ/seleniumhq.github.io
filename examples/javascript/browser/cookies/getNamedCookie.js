const {Builder} = require('selenium-webdriver');

(async function getCookieByName() {
  let driver = new Builder()
    .forBrowser('chrome')
    .build();

  await driver.get('https://www.example.com');
  // set a cookie on the current domain
  await driver.manage().addCookie({name:'foo', value: 'bar'});
  // Get cookie details with named cookie 'foo'
  await driver.manage().getCookie('foo').then(function (cookie) {
    console.log('cookie details => ', cookie);
  });
  await driver.quit();
})();