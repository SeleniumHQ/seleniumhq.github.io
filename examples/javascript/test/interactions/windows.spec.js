const {Builder, By} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const assert = require("node:assert");
let opts = new chrome.Options();
opts.addArguments('--headless');
let startIndex = 0
let endIndex = 5
let pdfMagicNumber = 'JVBER'
let imgMagicNumber = 'iVBOR'
let base64Code

describe('Interactions - Windows', function () {
  let driver;
  before(async function () {
    driver = await new Builder().forBrowser('chrome').setChromeOptions(opts).build();
  });

  after(async () => await driver.quit());

  it('Should be able to print page to pdf', async function () {

    await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
    let base64 = await driver.printPage({pageRanges: ["1-2"]});
    // page can be saved as a PDF as below
    // await fs.writeFileSync('./test.pdf', base64, 'base64');

    base64Code = base64.slice(startIndex, endIndex)
    assert.strictEqual(base64Code, pdfMagicNumber)
  });

  it('Should be able to get text using executeScript', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/javascriptPage.html');
    // Stores the header element
    let header = await driver.findElement(By.css('h1'));

    // Executing JavaScript to capture innerText of header element
    let text = await driver.executeScript('return arguments[0].innerText', header);
    assert.strictEqual(text, `Type Stuff`)
  });

  it('Should be able to take Element Screenshot', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/javascriptPage.html');

    let header = await driver.findElement(By.css('h1'));
    // Captures the element screenshot
    let encodedString = await header.takeScreenshot(true);
    // save screenshot as below
    // await fs.writeFileSync('./image.png', encodedString, 'base64');
    base64Code = encodedString.slice(startIndex, endIndex)
    assert.strictEqual(base64Code, imgMagicNumber)
  });

  it('Should be able to takeScreenshot', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/javascriptPage.html');

    // Captures the screenshot
    let encodedString = await driver.takeScreenshot();
    // save screenshot as below
    // await fs.writeFileSync('./image.png', encodedString, 'base64');
    base64Code = encodedString.slice(startIndex, endIndex)
    assert.strictEqual(base64Code, imgMagicNumber)
  });

  it('Should be able to switch to newWindow and newTab and close', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/');
    const initialWindow = await driver.getAllWindowHandles();
    assert.strictEqual(initialWindow.length, 1)

    // Opens a new tab and switches to new tab
    await driver.switchTo().newWindow('tab');
    const browserTabs = await driver.getAllWindowHandles();
    assert.strictEqual(browserTabs.length, 2)

    // Opens a new window and switches to new window
    await driver.switchTo().newWindow('window');
    const windows = await driver.getAllWindowHandles();
    assert.strictEqual(windows.length, 3)

    //Close the tab or window
    await driver.close();

    //Switch back to the old tab or window
    await driver.switchTo().window(windows[1]);

    const windowsAfterClose = await driver.getAllWindowHandles();
    assert.strictEqual(windowsAfterClose.length, 2);
  });

  it('Should be able to getWindow Size', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/');

    // Access each dimension individually
    const { width, height } = await driver.manage().window().getRect();

    // Or store the dimensions and query them later
    const rect = await driver.manage().window().getRect();
    const windowWidth = rect.width;
    const windowHeight = rect.height;

    assert.ok(windowWidth>0);
    assert.ok(windowHeight>0);
  });

  it('Should be able to getWindow position', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/');

    // Access each dimension individually
    const { x, y } = await driver.manage().window().getRect();

    // Or store the dimensions and query them later
    const rect = await driver.manage().window().getRect();
    const x1 = rect.x;
    const y1 = rect.y;

    assert.ok(x1>=0);
    assert.ok(y1>=0);
  });
});