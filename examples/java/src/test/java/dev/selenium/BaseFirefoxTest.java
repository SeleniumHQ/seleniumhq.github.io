package dev.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFirefoxTest extends BaseTest {

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
    }

}
