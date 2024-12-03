package dev.selenium.browsers;

import dev.selenium.BaseTest;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.service.DriverFinder;





public class FirefoxTest extends BaseTest {
  private FirefoxDriver driver;

  @AfterEach
  public void clearProperties() {
    System.clearProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY);
    System.clearProperty(GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY);driver.quit();
  }

  @Test
  public void basicOptions() {
    FirefoxOptions options = new FirefoxOptions();
    driver = new FirefoxDriver(options);
  }

  @Test
  public void arguments() {
    FirefoxOptions options = new FirefoxOptions();

    options.addArguments("-headless");

    driver = new FirefoxDriver(options);
  }

  @Test
  @DisabledOnOs(OS.WINDOWS)
  public void setBrowserLocation() {
    FirefoxOptions options = new FirefoxOptions();

    options.setBinary(getFirefoxLocation());

    driver = new FirefoxDriver(options);
  }

  @Test
  public void logsToFile() throws IOException {
    File logLocation = getTempFile("logsToFile", ".log");
    FirefoxDriverService service =
        new GeckoDriverService.Builder().withLogFile(logLocation).build();

    driver = new FirefoxDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
  }

  @Test
  public void logsToConsole() throws IOException {
    File logLocation = getTempFile("logsToConsole", ".log");
    System.setOut(new PrintStream(logLocation));

    FirefoxDriverService service =
        new GeckoDriverService.Builder().withLogOutput(System.out).build();

    driver = new FirefoxDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
  }

  @Test
  public void logsWithLevel() throws IOException {
    File logLocation = getTempFile("logsWithLevel", ".log");
    System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());

    FirefoxDriverService service =
        new GeckoDriverService.Builder().withLogLevel(FirefoxDriverLogLevel.DEBUG).build();

    driver = new FirefoxDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertTrue(fileContent.contains("Marionette\tDEBUG"));
  }

  @Test
  public void stopsTruncatingLogs() throws IOException {
    File logLocation = getTempFile("geckodriver-", "log");
    System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
    System.setProperty(
        GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY, FirefoxDriverLogLevel.DEBUG.toString());

    FirefoxDriverService service =
        new GeckoDriverService.Builder().withTruncatedLogs(false).build();

    driver = new FirefoxDriver(service);

    String fileContent = new String(Files.readAllBytes(logLocation.toPath()));
    Assertions.assertFalse(fileContent.contains(" ... "));
  }

  @Test
  public void setProfileLocation() {
    File profileDirectory = getTempDirectory("profile-");
    FirefoxDriverService service =
        new GeckoDriverService.Builder().withProfileRoot(profileDirectory).build();

    driver = new FirefoxDriver(service);

    String location = (String) driver.getCapabilities().getCapability("moz:profile");
    Assertions.assertTrue(location.contains(profileDirectory.getAbsolutePath()));
  }


  @Test
  public void installAddon() {
    driver = startFirefoxDriver();
    Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");

    driver.installExtension(xpiPath);

    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
    Assertions.assertEquals(
        "Content injected by webextensions-selenium-example", injected.getText());
  }


  @Test
  public void uninstallAddon() {
    driver = startFirefoxDriver();
    Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
    String id = driver.installExtension(xpiPath);

    driver.uninstallExtension(id);

    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    Assertions.assertEquals(driver.findElements(By.id("webextensions-selenium-example")).size(), 0);
  }


  @Test
  public void installUnsignedAddonPath() {
    driver = startFirefoxDriver();
    Path path = Paths.get("src/test/resources/extensions/selenium-example");

    driver.installExtension(path, true);

    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    WebElement injected = getLocatedElement(driver, By.id("webextensions-selenium-example"));
    Assertions.assertEquals(
        "Content injected by webextensions-selenium-example", injected.getText());
  }

  private Path getFirefoxLocation() {
    FirefoxOptions options = new FirefoxOptions();
    options.setBrowserVersion("stable");
    DriverFinder finder = new DriverFinder(GeckoDriverService.createDefaultService(), options);
    return Path.of(finder.getBrowserPath());
  }

  @Test
  public void fullPageScreenshot() throws Exception {
    driver = startFirefoxDriver();

    driver.get("https://www.selenium.dev");

    File screenshot = driver.getFullPageScreenshotAs(OutputType.FILE);

    File targetFile = new File("full_page_screenshot.png");
    Files.move(screenshot.toPath(), targetFile.toPath());

    // Verify the screenshot file exists
    Assertions.assertTrue(targetFile.exists(), "The full page screenshot file should exist");
    Files.deleteIfExists(targetFile.toPath());

    driver.quit();
  }

  @Test
  public void setContext() {
    driver = startFirefoxDriver();

    ((HasContext) driver).setContext(FirefoxCommandContext.CHROME);
    driver.executeScript("console.log('Inside Chrome context');");

    // Verify the context is back to "content"
    Assertions.assertEquals(
            FirefoxCommandContext.CHROME, ((HasContext) driver).getContext(),
            "The context should be 'chrome'"
    );

    driver.quit();
  }

  @Test
  public void firefoxProfile() {
    FirefoxProfile profile = new FirefoxProfile();
    FirefoxOptions options = new FirefoxOptions();
    profile.setPreference("javascript.enabled", "False");
    options.setProfile(profile);

    driver = new FirefoxDriver(options);

    driver.quit();
  }
}
