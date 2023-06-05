package dev.selenium.drivers;

import com.google.common.io.ByteStreams;
import dev.selenium.BaseTest;
import java.io.File;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;

public class ServiceTest extends BaseTest {
  private final File logLocation = new File("driver.log");
  private final File driverLocation = new File(System.getenv("CHROMEWEBDRIVER") + "/chromedriver");

  @AfterEach
  public void quit() {
    driver.quit();
  }

  @Test
  public void defaultService() {
    ChromeDriverService service = new ChromeDriverService.Builder().build();
    driver = new ChromeDriver(service);
  }

  @Test
  public void setDriverLocation() {
    ChromeDriverService service = new ChromeDriverService.Builder()
        .usingDriverExecutable(driverLocation)
        .build();

    driver = new ChromeDriver(service);
  }

    @Test
  public void setPort() {
    ChromeDriverService service = new ChromeDriverService.Builder()
        .usingPort(1234)
        .build();

    driver = new ChromeDriver(service);
  }

  @Test
  public void logsToFileWithLogOutput() {
    ChromeDriverService service = new ChromeDriverService.Builder()
        .withLogFile(logLocation)
        .build();

    driver = new ChromeDriver(service);
  }

  @Test
  public void logsToFileProperty() {
    System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
        logLocation.getAbsolutePath());

    driver = new ChromeDriver();
  }

  @Test
  public void logsToStdoutWithLogOutput() {
    ChromeDriverService service = new ChromeDriverService.Builder()
        .withLogOutput(System.out)
        .build();

    driver = new ChromeDriver(service);
  }

  @Test
  public void logsToStdoutProperty() {
    System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
        logLocation.getAbsolutePath());

    driver = new ChromeDriver();
  }
}
