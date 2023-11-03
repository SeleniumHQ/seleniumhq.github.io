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
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.manager.SeleniumManagerOutput;
import org.openqa.selenium.remote.service.DriverFinder;

public class EdgeTest extends BaseTest {
  @AfterEach
  public void clearProperties() {
    System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY);
    System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY);
  }

  @Test
  public void basicOptions() {
    EdgeOptions options = new EdgeOptions();
    driver = new EdgeDriver(options);
  }

  @Test
  public void arguments() {
    EdgeOptions options = new EdgeOptions();

    options.addArguments("--start-maximized");

    driver = new EdgeDriver(options);
  }

  @Test
  public void setBrowserLocation() {
    EdgeOptions options = new EdgeOptions();

    options.setBinary(getEdgeLocation());

    driver = new EdgeDriver(options);
  }

  @Test
  public void extensionOptions() {
    EdgeOptions options = new EdgeOptions();
    Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
    File extensionFilePath = new File(path.toUri());

    options.addExtensions(extensionFilePath);

    driver = new EdgeDriver(options);
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
    Assertions.assertEquals(
        "Content injected by webextensions-selenium-example", injected.getText());
  }

  @Test
  public void excludeSwitches() {
    EdgeOptions options = new EdgeOptions();

    options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

    driver = new EdgeDriver(options);
  }

  @Test
  public void loggingPreferences() {
    EdgeOptions options = new EdgeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability(EdgeOptions.LOGGING_PREFS, logPrefs);

    driver = new EdgeDriver(options);
    driver.get("https://www.selenium.dev");

    LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
    Assertions.assertFalse(logEntries.getAll().isEmpty());
  }

  @Test
  public void logsToFile() throws IOException {
    File logLocation = getTempFile("logsToFile", ".log");
    EdgeDriverService service = new EdgeDriverService.Builder().withLogFile(logLocation).build();

    driver = new EdgeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("Starting Microsoft Edge WebDriver"));
  }

  @Test
  public void logsToConsole() throws IOException {
    File logLocation = getTempFile("logsToConsole", ".log");
    System.setOut(new PrintStream(logLocation));

    EdgeDriverService service = new EdgeDriverService.Builder().withLogOutput(System.out).build();

    driver = new EdgeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("Starting Microsoft Edge WebDriver"));
  }

  @Test
  public void logsWithLevel() throws IOException {
    File logLocation = getTempFile("logsWithLevel", ".log");
    System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());

    EdgeDriverService service =
        new EdgeDriverService.Builder().withLoglevel(ChromiumDriverLogLevel.DEBUG).build();

    driver = new EdgeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("[DEBUG]:"));
  }

  @Test
  public void configureDriverLogs() throws IOException {
    File logLocation = getTempFile("configureDriverLogs", ".log");
    System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
    System.setProperty(
        EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY, ChromiumDriverLogLevel.DEBUG.toString());

    EdgeDriverService service =
        new EdgeDriverService.Builder().withAppendLog(true).withReadableTimestamp(true).build();

    driver = new EdgeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Pattern pattern = Pattern.compile("\\[\\d\\d-\\d\\d-\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
    Assertions.assertTrue(pattern.matcher(fileContent).find());
  }

  @Test
  public void disableBuildChecks() throws IOException {
    File logLocation = getTempFile("disableBuildChecks", ".log");
    System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
    System.setProperty(
        EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
        ChromiumDriverLogLevel.WARNING.toString());

    EdgeDriverService service =
        new EdgeDriverService.Builder().withBuildCheckDisabled(true).build();

    driver = new EdgeDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    String expected =
        "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
    Assertions.assertTrue(fileContent.contains(expected));
  }

  private File getEdgeLocation() {
    EdgeOptions options = new EdgeOptions();
    options.setBrowserVersion("stable");
    SeleniumManagerOutput.Result output =
        DriverFinder.getPath(EdgeDriverService.createDefaultService(), options);
    return new File(output.getBrowserPath());
  }
}
