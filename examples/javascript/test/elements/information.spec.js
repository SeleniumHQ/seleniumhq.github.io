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
  
  it('Should be able to fetch element size and position ', async function () {
    // Returns height, width, x and y position of the element
    let object = await driver.findElement(By.name('range_input')).getRect();
    
    assert.ok(object.height!==null)
    assert.ok(object.width!==null)
    assert.ok(object.y!==null)
    assert.ok(object.x!==null)
    
  });
  
  it('Should be able to fetch attributes and properties ', async function () {
    // identify the email text box
    const emailElement = await driver.findElement(By.xpath('//input[@name="email_input"]'));
    
    //fetch the attribute "name" associated with the textbox
    const nameAttribute = await emailElement.getAttribute("name");
  
    assert.equal(nameAttribute, "email_input")
  });
  
  after(async () => await driver.quit());
});


describe('Element Information Test', function () {
  let driver;
  
  before(async function () {
    driver = await new Builder().forBrowser('chrome').build();
  });
  
  it('Should return the css specified CSS value', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/colorPage.html');
    // Returns background color of the element
    let value = await driver.findElement(By.id('namedColor')).getCssValue('background-color');
    
    assert.equal(value, "rgba(0, 128, 0, 1)");
  });
  
  it('Should return the css specified CSS value', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/linked_image.html');
    // Returns text of the element
    let text = await driver.findElement(By.id('justanotherLink')).getText();
    
    assert.equal(text, "Just another link.");
  });
  
  after(async () => await driver.quit());
});