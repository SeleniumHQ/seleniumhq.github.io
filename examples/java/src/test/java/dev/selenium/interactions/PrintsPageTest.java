package dev.selenium.interactions;

import org.openqa.selenium.Pdf;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.print.PageMargin;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.PrintsPage;
import dev.selenium.BaseTest;

public class PrintsPageTest extends BaseTest{

    @BeforeEach
    public void setup() {
        ChromeOptions options = getDefaultChromeOptions();
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }

    @Test
    public void PrintWithPrintsPageTest() 
    {
        driver.get("https://www.selenium.dev/");
        PrintsPage printer = (PrintsPage) driver;
        PrintOptions printOptions = new PrintOptions();
        Pdf printedPage = printer.print(printOptions);
        Assertions.assertNotNull(printedPage);
    }

    @Test
    public void PrintWithBrowsingContextTest() 
    {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        PrintOptions printOptions = new PrintOptions();
        String printPage = browsingContext.print(printOptions);
        Assertions.assertTrue(printPage.length() > 0);
    }
}
