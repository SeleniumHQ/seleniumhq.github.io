package dev.selenium.browsers;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class FirefoxTest extends BaseTest {
    private FirefoxDriver driver;
    private File logLocation;
    private File tempDirectory;

    @AfterEach
    public void quit() {
        if (logLocation != null && logLocation.exists()) {
            logLocation.delete();
        }
        if (tempDirectory  != null && tempDirectory.exists()) {
            tempDirectory.delete();
        }
        System.clearProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY);
        System.clearProperty(GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY);

        driver.quit();
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
        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogFile(getLogLocation())
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(getLogLocation()));

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("geckodriver	INFO	Listening on"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogLevel(FirefoxDriverLogLevel.DEBUG)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Marionette\tDEBUG"));
    }

    @Test
    public void stopsTruncatingLogs() throws IOException {
        System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());
        System.setProperty(GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY,
                FirefoxDriverLogLevel.DEBUG.toString());

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withTruncatedLogs(false)
                .build();

        driver = new FirefoxDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertFalse(fileContent.contains(" ... "));
    }

    @Test
    public void setProfileLocation() throws IOException {
        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withProfileRoot(getTempDirectory())
                .build();

        driver = new FirefoxDriver(service);

        String location = (String) driver.getCapabilities().getCapability("moz:profile");
        Assertions.assertTrue(location.contains(getTempDirectory().getAbsolutePath()));
    }

    @Test
    public void installAddon() {
        driver = startDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");

        driver.installExtension(xpiPath);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void uninstallAddon() {
        driver = startDriver();
        Path xpiPath = Paths.get("src/test/resources/extensions/selenium-example.xpi");
        String id = driver.installExtension(xpiPath);

        driver.uninstallExtension(id);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        Assertions.assertEquals(driver.findElements(By.id("webextensions-selenium-example")).size(), 0);
    }

    @Test
    public void installUnsignedAddonPath() {
        driver = startDriver();
        Path path = Paths.get("src/test/resources/extensions/selenium-example");

        driver.installExtension(path, true);

        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = getLocatedElement(driver, By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    private File getLogLocation() throws IOException {
        if (logLocation == null || !logLocation.exists()) {
            logLocation = File.createTempFile("geckodriver-", ".log");
        }

        return logLocation;
    }

    private File getTempDirectory() throws IOException {
        if (tempDirectory == null || !tempDirectory.exists()) {
            tempDirectory = Files.createTempDirectory("profile-").toFile();
        }
        
        return tempDirectory;
    }

    private FirefoxDriver startDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        return new FirefoxDriver(options);
    }

    private String getFirefoxLocation() {
        return System.getenv("FF_BIN");
    }
}
