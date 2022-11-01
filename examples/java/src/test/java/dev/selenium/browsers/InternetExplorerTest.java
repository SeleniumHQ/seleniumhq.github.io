package dev.selenium.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

@EnabledOnOs(OS.WINDOWS)
public class InternetExplorerTest {
    public InternetExplorerDriver driver;

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.iedriver().setup();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void basicOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath(System.getenv("EDGE_PATH"));
        driver = new InternetExplorerDriver(options);
    }
}
