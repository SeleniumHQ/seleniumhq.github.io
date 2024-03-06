const {By, Builder, Browser} = require('selenium-webdriver');
const assert = require("assert");

(async function firstTest() {
  let driver;
  
  try {
    driver = await new Builder().forBrowser(Browser.CHROME).build();
    await driver.get('https://www.selenium.dev/selenium/web/web-form.html');
  
    let title = await driver.getTitle();
    assert.equal("Web form", title);
  
    await driver.manage().setTimeouts({implicit: 500});
  
    let textBox = await driver.findElement(By.name('my-text'));
    let submitButton = await driver.findElement(By.css('button'));
  
    await textBox.sendKeys('Selenium');
    await submitButton.click();
  
    let message = await driver.findElement(By.id('message'));
    let value = await message.getText();
    assert.equal("Received!", value);
  } catch (e) {
    console.log(e)
  } finally {
    await driver.quit();
  }
}())
