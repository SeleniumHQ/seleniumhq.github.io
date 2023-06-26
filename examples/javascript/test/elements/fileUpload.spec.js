const { suite } = require('selenium-webdriver/testing');
const {Browser, By} = require("selenium-webdriver");

suite(function(env) {
  describe('File Upload Test', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    xit('Should be able to upload a file successfully', async function() {
      await driver.manage().setTimeouts({implicit: 5000});

      // Navigate to URL
      await driver.get('https://www.selenium.dev/selenium/web/upload.html');

      // Upload snapshot
      await driver.findElement(By.id("upload")).sendKeys("selenium-snapshot.jpg");

      await driver.findElement(By.id("go")).submit();
    });

  });
}, { browsers: [Browser.CHROME, Browser.FIREFOX]});