const { Browser, Builder, By } = require('selenium-webdriver')
const Chrome = require('selenium-webdriver/chrome')
const remote = require('selenium-webdriver/remote')
const assert = require('node:assert')
const fs = require('node:fs')
const os = require('node:os')
const path = require('node:path')
const { startGrid, stopGrid } = require('./gridServer')

async function waitForFile(filePath, timeout) {
  const deadline = Date.now() + timeout
  while (!fs.existsSync(filePath)) {
    if (Date.now() > deadline) {
      throw new Error(`Timed out waiting for ${filePath} to be written`)
    }
    await new Promise((resolve) => setTimeout(resolve, 100))
  }
}

describe('Remote WebDriver Test', function () {
  let driver
  let gridUrl
  let gridProcess

  beforeEach(async function () {
    const grid = await startGrid()
    gridUrl = grid.url
    gridProcess = grid.process
  })

  afterEach(async function () {
    try {
      if (driver) {
        await driver.quit()
        driver = null
      }
    } finally {
      stopGrid(gridProcess)
    }
  })

  it('Basic Example', async function () {
    const options = new Chrome.Options().addArguments('--no-sandbox')
    driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
  })

  it('Uploads', async function () {
    const options = new Chrome.Options().addArguments('--no-sandbox')
    driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
    await driver.get('https://the-internet.herokuapp.com/upload')
    const uploadFile = path.resolve('./test/resources/selenium-snapshot.png')

    await driver.setFileDetector(new remote.FileDetector())
    await driver.findElement(By.css('input[type=file]')).sendKeys(uploadFile)
    await driver.findElement(By.id('file-submit')).click()

    const fileName = await driver.findElement(By.id('uploaded-files')).getText()
    assert.strictEqual(fileName, 'selenium-snapshot.png')
  })

  it('Downloads', async function () {
    const options = new Chrome.Options().addArguments('--no-sandbox').enableDownloads()
    driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()

    const fileNames = ['file_1.txt', 'file_2.jpg']
    await driver.get('https://www.selenium.dev/selenium/web/downloads/download.html')
    await driver.findElement(By.id('file-1')).click()
    await driver.findElement(By.id('file-2')).click()
    await driver.wait(async (d) => {
      const downloaded = await d.getDownloadableFiles()
      return fileNames.every((name) => downloaded.includes(name))
    }, 5000)

    const files = await driver.getDownloadableFiles()
    assert.deepStrictEqual(files.sort(), fileNames.sort())

    const targetDirectory = fs.mkdtempSync(path.join(os.tmpdir(), 'downloads-'))
    const downloadableFile = files[0]
    await driver.downloadFile(downloadableFile, targetDirectory)

    const downloadedFilePath = path.join(targetDirectory, downloadableFile)
    await waitForFile(downloadedFilePath, 5000)
    const fileContent = fs.readFileSync(downloadedFilePath, 'utf8')
    assert.strictEqual(fileContent.trim(), 'Hello, World!')

    await driver.deleteDownloadableFiles()
    assert.deepStrictEqual(await driver.getDownloadableFiles(), [])
  })

  it('Browser specific functionalities', async function () {
    const options = new Chrome.Options().addArguments('--no-sandbox')
    driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()

    assert.ok(driver instanceof Chrome.Driver)
  })
})
