package dev.selenium.browsers;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class EdgeTest {
    private EdgeDriver driver;
    private File logLocation;

    @AfterEach
    public void quit() {
        if (logLocation != null && logLocation.exists()) {
            logLocation.delete();
        }
        System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY);
        System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY);

        driver.quit();
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
        File extensionPath = new File(path.toUri());

        options.addExtensions(extensionPath);

        driver = new EdgeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
        Assertions.assertEquals("Content injected by webextensions-selenium-example", injected.getText());
    }

    @Test
    public void excludeSwitches() {
        EdgeOptions options = new EdgeOptions();

        options.setExperimentalOption("excludeSwitches", ImmutableList.of("disable-popup-blocking"));

        driver = new EdgeDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        EdgeDriverService service = new EdgeDriverService.Builder()
                .withLogFile(getLogLocation())
                .build();

        driver = new EdgeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Starting Microsoft Edge WebDriver"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(getLogLocation()));

        EdgeDriverService service = new EdgeDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new EdgeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Starting Microsoft Edge WebDriver"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());

        EdgeDriverService service = new EdgeDriverService.Builder()
            .withLoglevel(ChromiumDriverLogLevel.DEBUG)
            .build();

        driver = new EdgeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("[DEBUG]:"));
    }

    @Test
    public void configureDriverLogs() throws IOException {
        System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());
        System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.DEBUG.toString());

        EdgeDriverService service = new EdgeDriverService.Builder()
            .withAppendLog(true)
            .withReadableTimestamp(true)
            .build();

        driver = new EdgeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Pattern pattern = Pattern.compile("\\[\\d\\d-\\d\\d-\\d\\d\\d\\d", Pattern.CASE_INSENSITIVE);
        Assertions.assertTrue(pattern.matcher(fileContent).find());
    }

    @Test
    public void disableBuildChecks() throws IOException {
        System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY,
                getLogLocation().getAbsolutePath());
        System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
                ChromiumDriverLogLevel.WARNING.toString());

        EdgeDriverService service = new EdgeDriverService.Builder()
                .withBuildCheckDisabled(true)
                .build();

        driver = new EdgeDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        String expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
        Assertions.assertTrue(fileContent.contains(expected));
    }

    private File getLogLocation() throws IOException {
        if (logLocation == null || !logLocation.exists()) {
            logLocation = File.createTempFile("msedgedriver-", ".log");
        }

        return logLocation;
    }

    private File getEdgeLocation() {
        File location = new File(System.getenv("EDGE_BIN"));
        return location.exists() ? location : null;
    }
}
