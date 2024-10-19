package dev.selenium.interactions;

import org.openqa.selenium.Pdf;
import org.openqa.selenium.BrowsingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.print.PageMargin;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.print.PrintsPage;
import dev.selenium.BaseChromeTest;

public class PrintsPageTest extends BaseChromeTest {

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
