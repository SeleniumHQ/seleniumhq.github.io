package dev.selenium.browsers;

import dev.selenium.BaseTest;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.manager.SeleniumManagerOutput;
import org.openqa.selenium.remote.service.DriverFinder;

public class ChromeTest extends BaseTest {
  @AfterEach
  public void clearProperties() {
    System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY);
    System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY);
  }

  @Test
  public void basicOptions() {
    ChromeOptions options = new ChromeOptions();
    driver = new ChromeDriver(options);
  }

  @Test
  public void arguments() {
    ChromeOptions options = new ChromeOptions();

    options.addArguments("--start-maximized");

    driver = new ChromeDriver(options);
  }

  @Test
  public void setBrowserLocation() {
    ChromeOptions options = new ChromeOptions();

    options.setBinary(getChromeLocation());

    driver = new ChromeDriver(options);
  }

  @Test
  public void extensionOptions() {
    ChromeOptions options = new ChromeOptions();
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
    ChromeOptions options = new ChromeOptions();

    options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

    driver = new ChromeDriver(options);
  }

  @Test
  public void loggingPreferences() {
    ChromeOptions options = new ChromeOptions();
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
    ChromeOptions options = new ChromeOptions();
    options.setBrowserVersion("stable");
    SeleniumManagerOutput.Result output =
        DriverFinder.getPath(ChromeDriverService.createDefaultService(), options);
    return new File(output.getBrowserPath());
  }
}
