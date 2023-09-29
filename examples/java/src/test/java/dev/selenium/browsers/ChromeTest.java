package dev.selenium.browsers;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ChromeTest {
    private ChromeDriver driver;
    private File logLocation;

    @AfterEach
    public void quit() {
        if (logLocation != null && logLocation.exists()) {
            logLocation.delete();
        }
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY);
        System.clearProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY);

        driver.quit();
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
        File extensionPath = new File(path.toUri());

        options.addExtensions(extensionPath);

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void excludeSwitches() {
        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("excludeSwitches", ImmutableList.of("disable-popup-blocking"));

        driver = new ChromeDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogFile(getLogLocation())
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(getLogLocation()));

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Starting ChromeDriver"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());

        ChromeDriverService service = new ChromeDriverService.Builder()
            .withLogLevel(ChromiumDriverLogLevel.DEBUG)
            .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("[DEBUG]:"));
    }

    @Test
    public void configureDriverLogs() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.DEBUG.toString());

        ChromeDriverService service = new ChromeDriverService.Builder()
            .withAppendLog(true)
            .withReadableTimestamp(true)
            .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Pattern pattern = Pattern.compile("\\[\\d\\d-\\d\\d-\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
        Assertions.assertTrue(pattern.matcher(fileContent).find());
    }

    @Test
    public void disableBuildChecks() throws IOException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.WARNING.toString());

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withBuildCheckDisabled(true)
                .build();

        driver = new ChromeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        String expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
        Assertions.assertTrue(fileContent.contains(expected));
    }

    private File getLogLocation() throws IOException {
        if (logLocation == null || !logLocation.exists()) {
            logLocation = File.createTempFile("chromedriver-", ".log");
        }

        return logLocation;
    }

    private File getChromeLocation() {
        File location = new File(System.getenv("CHROME_BIN"));
        return location.exists() ? location : null;
    }
}
