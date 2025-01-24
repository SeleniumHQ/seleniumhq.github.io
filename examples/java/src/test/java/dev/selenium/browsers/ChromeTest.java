package dev.selenium.browsers;

import dev.selenium.BaseTest;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.service.DriverFinder;


public class ChromeTest extends BaseTest {
  @AfterEach
  public void clearProperties() {
    System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY);
    System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY);
  }

  @Test
  public void basicOptions() {
    ChromeOptions options = getDefaultChromeOptions();
    driver = new ChromeDriver(options);
  }

  @Test
  public void arguments() {
    ChromeOptions options = getDefaultChromeOptions();

    options.addArguments("--start-maximized");

    driver = new ChromeDriver(options);
  }

  @Test
  public void setBrowserLocation() {
    ChromeOptions options = getDefaultChromeOptions();

    options.setBinary(getChromeLocation());

    driver = new ChromeDriver(options);
  }

  @Test
  public void extensionOptions() {
    ChromeOptions options = getDefaultChromeOptions();
    Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
    File extensionFilePath = new File(path.toUri());

    options.addExtensions(extensionFilePath);

    driver = new ChromeDriver(options);
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
    Assertions.assertEquals(
        "Content injected by webextensions-selenium-example", injected.getText());
  }

  @Test
  public void excludeSwitches() {
    ChromeOptions options = getDefaultChromeOptions();

    options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

    driver = new ChromeDriver(options);
  }

  @Test
  public void loggingPreferences() {
    ChromeOptions options = getDefaultChromeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);

    driver = new ChromeDriver(options);
    driver.get("https://www.selenium.dev");

    LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
    Assertions.assertFalse(logEntries.getAll().isEmpty());
  }

  @Test
  public void logsToFile() throws IOException {
    File logLocation = getTempFile("logsToFile", ".log");
    ChromeDriverService service =
        new ChromeDriverService.Builder().withLogFile(logLocation).build();

    driver = new ChromeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
  }

  @Test
  public void logsToConsole() throws IOException {
    File logLocation = getTempFile("logsToConsole", ".log");
    System.setOut(new PrintStream(logLocation));

    ChromeDriverService service =
        new ChromeDriverService.Builder().withLogOutput(System.out).build();

    driver = new ChromeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
  }

  @Test
  public void logsWithLevel() throws IOException {
    File logLocation = getTempFile("logsWithLevel", ".log");
    System.setProperty(
        ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());

    ChromeDriverService service =
        new ChromeDriverService.Builder().withLogLevel(ChromiumDriverLogLevel.DEBUG).build();

    driver = new ChromeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("[DEBUG]:"));
  }

  @Test
  public void configureDriverLogs() throws IOException {
    File logLocation = getTempFile("configureDriverLogs", ".log");
    System.setProperty(
        ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
    System.setProperty(
        ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
        ChromiumDriverLogLevel.DEBUG.toString());

    ChromeDriverService service =
        new ChromeDriverService.Builder().withAppendLog(true).withReadableTimestamp(true).build();

    driver = new ChromeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Pattern pattern = Pattern.compile("\\[\\d\\d-\\d\\d-\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
    Assertions.assertTrue(pattern.matcher(fileContent).find());
  }

  @Test
  public void disableBuildChecks() throws IOException {
    File logLocation = getTempFile("disableBuildChecks", ".log");
    System.setProperty(
        ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
    System.setProperty(
        ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
        ChromiumDriverLogLevel.WARNING.toString());

    ChromeDriverService service =
        new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();

    driver = new ChromeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    String expected =
        "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
    Assertions.assertTrue(fileContent.contains(expected));
  }

  private File getChromeLocation() {
    ChromeOptions options = getDefaultChromeOptions();
    options.setBrowserVersion("stable");
    DriverFinder finder = new DriverFinder(ChromeDriverService.createDefaultService(), options);
    return new File(finder.getBrowserPath());
  }

  @Test
  public void setPermission() {
    ChromeDriver driver = new ChromeDriver();
    driver.get("https://www.selenium.dev");

    driver.setPermission("camera", "denied");

    // Verify the permission state is 'denied'
    String script = "return navigator.permissions.query({ name: 'camera' })" +
            "    .then(permissionStatus => permissionStatus.state);";
    String permissionState = (String) driver.executeScript(script);

    Assertions.assertEquals("denied", permissionState);
    driver.quit();
  }

  @Test
  public void setNetworkConditions() {
    driver = new ChromeDriver();

    ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
    networkConditions.setOffline(false);
    networkConditions.setLatency(java.time.Duration.ofMillis(20)); // 20 ms of latency
    networkConditions.setDownloadThroughput(2000 * 1024 / 8); // 2000 kbps
    networkConditions.setUploadThroughput(2000 * 1024 / 8);   // 2000 kbps

    ((ChromeDriver) driver).setNetworkConditions(networkConditions);

    driver.get("https://www.selenium.dev");

    // Assert the network conditions are set as expected
    ChromiumNetworkConditions actualConditions = ((ChromeDriver) driver).getNetworkConditions();
    Assertions.assertAll(
        () -> Assertions.assertEquals(networkConditions.getOffline(), actualConditions.getOffline()),
        () -> Assertions.assertEquals(networkConditions.getLatency(), actualConditions.getLatency()),
        () -> Assertions.assertEquals(networkConditions.getDownloadThroughput(), actualConditions.getDownloadThroughput()),
        () -> Assertions.assertEquals(networkConditions.getUploadThroughput(), actualConditions.getUploadThroughput())
    );
    ((ChromeDriver) driver).deleteNetworkConditions();
    driver.quit();
  }

  @Test
  public void castFeatures() {
    ChromeDriver driver = new ChromeDriver();

    List<Map<String, String>> sinks = driver.getCastSinks();
    if (!sinks.isEmpty()) {
      String sinkName = sinks.get(0).get("name");
      driver.startTabMirroring(sinkName);
      driver.stopCasting(sinkName);
    }

    driver.quit();
  }

  @Test
  public void getBrowserLogs() {
    ChromeDriver driver = new ChromeDriver();
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    WebElement consoleLogButton = driver.findElement(By.id("consoleError"));
    consoleLogButton.click();

    LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

    // Assert that at least one log contains the expected message
    boolean logFound = false;
    for (LogEntry log : logs) {
      if (log.getMessage().contains("I am console error")) {
        logFound = true;
        break;
      }
    }

    Assertions.assertTrue(logFound, "No matching log message found.");
    driver.quit();
  }
}
