const {By, Builder} = require('selenium-webdriver');
const assert = require('node:assert');

describe('Interactions - Frames', function () {
  let driver;
  const framesPage = 'https://www.selenium.dev/selenium/web/iframes.html';

  before(async function () {
    driver = await new Builder().forBrowser('chrome').build();
  });

  after(async () => await driver.quit());

  it('switches to a frame using a web element', async function () {
    await driver.get(framesPage);

    // Store the web element
    const iframe = await driver.findElement(By.id('iframe1'));

    // Switch to the frame
    await driver.switchTo().frame(iframe);

    // Now we can type text into the email field
    const email = await driver.findElement(By.id('email'));
    await email.sendKeys('admin@selenium.dev');

    assert.equal(await email.getAttribute('value'), 'admin@selenium.dev');
    assert.ok((await driver.getPageSource()).includes('We Leave From Here'));
  });

  it('switches to a frame using its name or id', async function () {
    await driver.get(framesPage);

    // Using the ID
    await driver.switchTo().frame('iframe1');
    await driver.switchTo().defaultContent();

    // Or using the name instead
    await driver.switchTo().frame('iframe1-name');

    // Now we can type text into the email field
    const email = await driver.findElement(By.id('email'));
    await email.sendKeys('admin@selenium.dev');

    assert.equal(await email.getAttribute('value'), 'admin@selenium.dev');
    assert.ok((await driver.getPageSource()).includes('We Leave From Here'));
  });

  it('switches to a frame using an index', async function () {
    await driver.get(framesPage);

    // Switches to the first frame
    await driver.switchTo().frame(0);

    assert.ok((await driver.getPageSource()).includes('We Leave From Here'));
  });

  it('leaves a frame', async function () {
    await driver.get(framesPage);
    await driver.switchTo().frame(0);

    // Return to the top level
    await driver.switchTo().defaultContent();

    assert.ok((await driver.getPageSource()).includes('This page has iframes'));
  });
});
