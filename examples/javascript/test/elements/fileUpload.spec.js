const { suite } = require('selenium-webdriver/testing');
const {Browser, By, until} = require("selenium-webdriver");
const path = require("path");
const assert = require('node:assert');

suite(function(env) {
  describe('File Upload Test', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    it('Should be able to upload a file successfully', async function() {
      const image = path.resolve('./test/resources/selenium-snapshot.png')

      await driver.manage().setTimeouts({implicit: 5000});

      // Navigate to URL
      await driver.get('https://the-internet.herokuapp.com/upload');
      // Upload snapshot
      await driver.findElement(By.id("file-upload")).sendKeys(image);
      await driver.findElement(By.id("file-submit")).submit();
      
      const revealed = await driver.findElement(By.id('uploaded-files'))
      await driver.wait(until.elementIsVisible(revealed), 2000);
      const data = await driver.findElement(By.css('h3'));
      
      assert.equal(await data.getText(), `File Uploaded!`);
    });

  });
}, { browsers: [Browser.CHROME, Browser.FIREFOX]});