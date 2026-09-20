const {Builder, By} = require('selenium-webdriver');
const assert = require('assert');

const LOCATORS_PAGE = 'https://www.selenium.dev/selenium/web/locators_tests/locators.html';

describe('Finders', function () {
  it('finds the first matching element', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const firstInput = await driver.findElement(By.className('information'));

    assert.equal(await firstInput.getAttribute('id'), 'fname');
    await driver.quit();
  });

  it('finds an element within a subset of the DOM', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const form = await driver.findElement(By.tagName('form'));
    const input = await form.findElement(By.className('information'));

    assert.equal(await input.getAttribute('id'), 'fname');
    await driver.quit();
  });

  it('uses an optimized locator', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const input = await driver.findElement(By.css('form .information'));

    assert.equal(await input.getAttribute('id'), 'fname');
    await driver.quit();
  });

  it('finds all matching elements', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const inputs = await driver.findElements(By.tagName('input'));

    assert.ok(inputs.length > 1);
    await driver.quit();
  });

  it('gets an element from a collection', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const elements = await driver.findElements(By.tagName('p'));
    for (const element of elements) {
      console.log('Paragraph text:' + await element.getText());
    }

    assert.ok(elements.length > 0);
    await driver.quit();
  });

  it('finds elements from an element', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    const form = await driver.findElement(By.css('form'));
    const elements = await form.findElements(By.css('input'));
    for (const e of elements) {
      console.log(await e.getAttribute('value'));
    }

    assert.ok(elements.length > 0);
    await driver.quit();
  });

  it('gets the active element', async function () {
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get(LOCATORS_PAGE);
    await driver.findElement(By.css('#fname')).sendKeys('webElement');
    const attr = await driver.switchTo().activeElement().getAttribute('name');

    assert.equal(attr, 'fname');
    await driver.quit();
  });
});
