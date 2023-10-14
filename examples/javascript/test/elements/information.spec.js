const {By, Builder} = require('selenium-webdriver');
const assert = require("assert");

describe('Element Information Test', function () {
  let driver;
  
  before(async function () {
    driver = await new Builder().forBrowser('chrome').build();
  });
  
  beforeEach(async ()=> {
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');
  })
  
  it('Check if element is displayed', async function () {
    // Resolves Promise and returns boolean value
    let result =  await driver.findElement(By.name("email_input")).isDisplayed();
    
    assert.equal(result,true);
  });
  
  it('Check if button is enabled', async function () {
    // Resolves Promise and returns boolean value
    let element =  await driver.findElement(By.name("button_input")).isEnabled();
  
    assert.equal(element, true);
  });
  
  it('Check if checkbox is selected', async function () {
    // Returns true if element ins checked else returns false
    let isSelected = await driver.findElement(By.name("checkbox_input")).isSelected();
  
    assert.equal(isSelected, true);
  });
  
  it('Should return the tagname', async function () {
    // Returns TagName of the element
    let value = await driver.findElement(By.name('email_input')).getTagName();
  
    assert.equal(value, "input");
  });
  
  after(async () => await driver.quit());
});