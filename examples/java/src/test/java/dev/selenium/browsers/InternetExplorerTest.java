package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

@EnabledOnOs(OS.WINDOWS)
public class InternetExplorerTest {
    public InternetExplorerDriver driver;
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

        driver.quit();
    }

    @Test
    public void basicOptionsWin10() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath(getEdgeLocation());
        driver = new InternetExplorerDriver(options);
    }

    @Test
    public void basicOptionsWin11() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(options);
    }

    @Test
    public void logsToFile() throws IOException {
        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogFile(getLogLocation())
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Started InternetExplorerDriver server"));
    }

    @Test
    public void logsToConsole() throws IOException {
        System.setOut(new PrintStream(getLogLocation()));

        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Started InternetExplorerDriver server"));
    }

    @Test
    public void logsWithLevel() throws IOException {
        System.setProperty(InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY,
                getLogLocation().getAbsolutePath());

        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withLogLevel(InternetExplorerDriverLogLevel.WARN)
                .build();

        driver = new InternetExplorerDriver(service);

        String fileContent = new String(Files.readAllBytes(getLogLocation().toPath()));
        Assertions.assertTrue(fileContent.contains("Invalid capability setting: timeouts is type null"));
    }

    @Test
    public void supportingFilesLocation() throws IOException {
        InternetExplorerDriverService service = new InternetExplorerDriverService.Builder()
                .withExtractPath(getTempDirectory())
                .build();

        driver = new InternetExplorerDriver(service);
        Assertions.assertTrue(new File(getTempDirectory() + "/IEDriver.tmp").exists());
    }

    private File getLogLocation() throws IOException {
        if (logLocation == null || !logLocation.exists()) {
            logLocation = File.createTempFile("iedriver-", ".log");
        }

        return logLocation;
    }

    private File getTempDirectory() throws IOException {
        if (tempDirectory == null || !tempDirectory.exists()) {
            tempDirectory = Files.createTempDirectory("supporting-").toFile();
        }

        return tempDirectory;
    }

    private String getEdgeLocation() {
        return System.getenv("EDGE_BIN");
    }
}
