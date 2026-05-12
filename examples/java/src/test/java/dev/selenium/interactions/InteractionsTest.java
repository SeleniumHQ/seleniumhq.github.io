package dev.selenium.interactions;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InteractionsTest extends BaseChromeTest {
    @Test
    public void getTitle() {
        driver.get("https://www.selenium.dev/");
        // get title
        String title = driver.getTitle();
        Assertions.assertEquals(title, "Selenium");
    }

    @Test
    public void getCurrentUrl() {
        driver.get("https://www.selenium.dev/");
        // get current url
        String url = driver.getCurrentUrl();
        Assertions.assertEquals(url, "https://www.selenium.dev/");
    }
}