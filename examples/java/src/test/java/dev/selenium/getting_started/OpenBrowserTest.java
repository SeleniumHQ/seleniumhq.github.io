package dev.selenium.getting_started;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class OpenBrowserTest {
    public WebDriver driver;

    @Test
    public void chromeSession() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.quit();
    }

    @Test
    public void edgeSession() {
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);

        driver.quit();
    }

    @Test
    public void firefoxSession() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.quit();
    }

    @Disabled("Only runs on Windows")
    @Test
    public void internetExplorerSession() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(options);

        driver.quit();
    }

    @Disabled("Only runs on Windows")
    @Test
    public void internetExplorerCompatibilitySession() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath("/path/to/edge/browser");

        driver = new InternetExplorerDriver(options);

        driver.quit();
    }

    @Disabled("Requires non-standard browser")
    @Test
    public void operaSession() {
        //    Set webdriver.chrome.driver with Opera Driver
        System.setProperty("webdriver.chrome.driver", "OPERA_DRIVER_PATH");
        //    Create ChromeOptions Instance
        ChromeOptions chromeOptions = new ChromeOptions();
        //    Set W3C Dialect
        chromeOptions.setExperimentalOption("w3c", true);
        //    Create ChromeDriver Instance
        driver = new ChromeDriver(chromeOptions);
        //    Open Target Website
        driver.get("https://www.selenium.dev");
        //    Quit
        driver.quit();
    }

    @Disabled("Only runs on Mac")
    @Test
    public void safariSession() {
        SafariOptions options = new SafariOptions();

        driver = new SafariDriver(options);

        driver.quit();
    }
}
