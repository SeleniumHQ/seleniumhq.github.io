package dev.selenium.interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class browserNavigation {

    @Test
    public void navigateBrowser() {
        
        WebDriver driver = new ChromeDriver();
      
        //Convenient
        driver.get("https://selenium.dev");
            
        //Longer way
        driver.navigate().to("https://selenium.dev");
        String title = driver.getTitle();
        assertEquals(title, "Selenium");
        
        //Back
        driver.navigate().back();
        title = driver.getTitle();
        assertEquals(title, "Selenium");
        
        //Forward
        driver.navigate().forward();
        title = driver.getTitle();
        assertEquals(title, "Selenium");

        //Refresh
        driver.navigate().refresh();
        title = driver.getTitle();
        assertEquals(title, "Selenium");

        driver.quit();
    }
}
