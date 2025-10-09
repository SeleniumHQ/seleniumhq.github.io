const fs = require('fs');
const os = require('os');
const path = require('path');
const Chrome = require('selenium-webdriver/chrome');
const {Browser, Builder} = require("selenium-webdriver");
const {getBinaryPaths} = require("selenium-webdriver/common/driverFinder");

describe('Service Test', function () {
  let driver;
  let userDataDir;
  let service;
  let options;
  let logFilePath;

  afterEach(async function () {
    if (driver) {
      await driver.quit();
      driver = null;
    }
    if (userDataDir) {
      fs.rmSync(userDataDir, { recursive: true, force: true });
      userDataDir = null;
    }
    if (logFilePath) {
      // Clean up the log file if it was created
      fs.rmSync(logFilePath, { force: true });
      logFilePath = null;
    }
  });

  it('Default service', async function () {
    // Test case 1: Start Chrome using the default service configuration.
    service = new Chrome.ServiceBuilder();
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });

  it('Set Driver Location', async function () {
    // Test case 2: Explicitly set the path to the ChromeDriver executable.
    options = new Chrome.Options();
    options.setBrowserVersion("stable");
    
    // Use the official utility to find binary paths (a robust approach)
    let paths = getBinaryPaths(options);
    let driverPath = paths.driverPath;
    let browserPath = paths.browserPath;
    
    options.setChromeBinaryPath(browserPath);
    userDataDir = fs.mkdtempSync(path.join(os.tmpdir(), 'chrome-profile-'));
    options.addArguments(`--user-data-dir=${userDataDir}`);
    options.addArguments('--no-sandbox');
    options.addArguments('--disable-dev-shm-usage');
    
    // Pass the driverPath to the ServiceBuilder
    service = new Chrome.ServiceBuilder(driverPath);
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });

  it('Set port', async function () {
    // Test case 3: Set a specific port for the ChromeDriver service to listen on.
    service = new Chrome.ServiceBuilder().setPort(1234);
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });

  it('Set Log Path and Verbosity', async function () {
    // Test case 4: Configure the service to output verbose logs to a specific file.
    logFilePath = path.join(os.tmpdir(), `chromedriver-log-${Date.now()}.log`);

    service = new Chrome.ServiceBuilder()
      .loggingTo(logFilePath)
      .enableVerboseLogging(); // Equivalent to setting --verbose argument

    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');

    // Optional: Verify the log file was created (we can't easily check content in this basic test)
    if (!fs.existsSync(logFilePath)) {
      throw new Error(`Log file was not created at: ${logFilePath}`);
    }
  });

  it('Add Service Arguments', async function () {
    // Test case 5: Add arbitrary command-line arguments to the ChromeDriver service executable.
    // Note: This is an example; ChromeDriver arguments are rarely needed outside of logging.
    service = new Chrome.ServiceBuilder()
      .addArguments('--disable-build-check', '--disable-features=RendererCodeIntegrity');

    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });
});
