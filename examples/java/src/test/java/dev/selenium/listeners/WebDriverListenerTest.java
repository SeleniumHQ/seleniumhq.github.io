package dev.selenium.listeners;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebDriverListenerTest {

    private WebDriver driver;

    @Test
    @Order(1)
    public void testWebDriverListener() {
        WebDriverListener listener = new CustomWebDriverListener();
        driver = new EventFiringDecorator<>(listener).decorate(new ChromeDriver());

        driver.get("https://www.selenium.dev/");
        driver.manage().window().maximize();

        WebElement documentation = driver.findElement(By.cssSelector("a[href='/documentation']"));

        documentation.click();

        driver.quit();

    }

    @Test
    @Order(2)
    public void testWebDriverListenerOnError() {
        WebDriverListener listener = new CustomWebDriverListener();
        driver = new EventFiringDecorator<>(listener).decorate(new ChromeDriver());

        try {
            driver.get(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        driver.quit();

    }

}
