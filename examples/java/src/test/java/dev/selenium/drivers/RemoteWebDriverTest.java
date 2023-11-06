package dev.selenium.drivers;

import dev.selenium.BaseTest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasDownloads;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.HasCasting;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoteWebDriverTest extends BaseTest {
  URL gridUrl;

  @BeforeEach
  public void startGrid() {
    gridUrl = startStandaloneGrid();
  }

  @Test
  public void runRemote() {
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(gridUrl, options);
  }

  @Test
  public void uploads() {
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(gridUrl, options);
    driver.get("https://the-internet.herokuapp.com/upload");
    File uploadFile = new File("src/test/resources/selenium-snapshot.png");

    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
    WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
    fileInput.sendKeys(uploadFile.getAbsolutePath());
    driver.findElement(By.id("file-submit")).click();

    WebElement fileName = driver.findElement(By.id("uploaded-files"));
    Assertions.assertEquals("selenium-snapshot.png", fileName.getText());
  }

  @Test
  public void downloads() throws IOException {
    ChromeOptions options = new ChromeOptions();
    options.setEnableDownloads(true);
    driver = new RemoteWebDriver(gridUrl, options);

    List<String> fileNames = new ArrayList<>();
    fileNames.add("file_1.txt");
    fileNames.add("file_2.jpg");
    driver.get("https://www.selenium.dev/selenium/web/downloads/download.html");
    driver.findElement(By.id("file-1")).click();
    driver.findElement(By.id("file-2")).click();
    new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(d -> ((HasDownloads) d).getDownloadableFiles().contains("file_2.jpg"));

    List<String> files = ((HasDownloads) driver).getDownloadableFiles();

    Assertions.assertEquals(fileNames, files);
    String downloadableFile = files.get(0);
    Path targetDirectory = Files.createTempDirectory("download");

    ((HasDownloads) driver).downloadFile(downloadableFile, targetDirectory);

    String fileContent = String.join("", Files.readAllLines(targetDirectory.resolve(downloadableFile)));
    Assertions.assertEquals("Hello, World!", fileContent);

    ((HasDownloads) driver).deleteDownloadableFiles();

    Assertions.assertTrue(((HasDownloads) driver).getDownloadableFiles().isEmpty());
  }

  @Test
  public void augment() {
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(gridUrl, options);

    driver = new Augmenter().augment(driver);

    Assertions.assertTrue(driver instanceof HasCasting);
  }

  @Test
  public void remoteWebDriverBuilder() {
    driver =
        RemoteWebDriver.builder()
            .address(gridUrl)
            .oneOf(new ChromeOptions())
            .setCapability("ext:options", Map.of("key", "value"))
            .config(ClientConfig.defaultConfig())
            .build();

    Assertions.assertTrue(driver instanceof HasCasting);
  }
}
