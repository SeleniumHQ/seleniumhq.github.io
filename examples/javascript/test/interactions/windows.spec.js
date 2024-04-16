const {Builder} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const assert = require("node:assert");
let opts = new chrome.Options();
opts.addArguments('--headless');

let startIndex = 0
let endIndex = 5
let pdfMagicNumber = 'JVBER'
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
});