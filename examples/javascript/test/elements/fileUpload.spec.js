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
      await driver.get('https://the-internet.herokuapp.com/upload');

      // Upload snapshot
      await driver.findElement(By.id("file-upload")).sendKeys("selenium-snapshot.jpg");

      await driver.findElement(By.id("file-submit")).submit();
    });

  });
}, { browsers: [Browser.CHROME, Browser.FIREFOX]});