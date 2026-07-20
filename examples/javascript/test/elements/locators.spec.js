const {By, Builder} = require('selenium-webdriver');
const assert = require("assert");

describe('Element Locator Test', function () {
  it('Check if element can be found by class name', async () => {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.className('information'));
    const tag = await element.getTagName();
    const id = await element.getAttribute("id");
    const value = await element.getAttribute("value");

    assert.equal(tag, "input");
    assert.equal(id, "fname");
    assert.equal(value, "Jane");
    await driver.quit();
  });

  it('Check if element can be found by css selector', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.css('#fname'));

    const tag = await element.getTagName();
    const id = await element.getAttribute("id");
    const value = await element.getAttribute("value");

    assert.equal(tag, "input");
    assert.equal(id, "fname");
    assert.equal(value, "Jane");
    await driver.quit();
  });

  it('Check if element can be found by id', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.id('lname'));

    const tag = await element.getTagName();
    const id = await element.getAttribute("id");
    const value = await element.getAttribute("value");

    assert.equal(tag, "input");
    assert.equal(id, "lname");
    assert.equal(value, "Doe");
    await driver.quit();
  });

  it('Check if element can be found by name', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.name("newsletter"));

    const tag = await element.getTagName();
    const type = await element.getAttribute("type");
    const value = await element.getAttribute("value");

    assert.equal(tag, "input");
    assert.equal(type, "checkbox");
    assert.equal(value, "1");
    await driver.quit();
  });



  it('Check if element can be found by link text', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.linkText("Selenium Official Page"));

    const tag = await element.getTagName();
    const href = await element.getAttribute("href");

    assert.equal(tag, "a");
    assert.equal(href, "https://www.selenium.dev/");
    await driver.quit();
  });

  it('Check if element can be found by partial link text', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.partialLinkText("Official Page"));

    const tag = await element.getTagName();
    const href = await element.getAttribute("href");

    assert.equal(tag, "a");
    assert.equal(href, "https://www.selenium.dev/");
    await driver.quit();
  });

  it('Check if element can be found by tag name', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.tagName("a"));

    const tag = await element.getTagName();
    const text = await element.getText();

    assert.equal(tag, "a");
    assert.equal(text, "Selenium Official Page");
    await driver.quit();
  });

  it('Check if element can be found by xpath', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
    const element = await driver.findElement(By.xpath('//input[@value="f"]'));

    const tag = await element.getTagName();
    const type = await element.getAttribute("type");
    const value = await element.getAttribute("value");

    assert.equal(tag, "input");
    assert.equal(type, "radio");
    assert.equal(value, "f");
    await driver.quit();
  });
});
