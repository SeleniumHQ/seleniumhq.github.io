package dev.selenium.interactions;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class SavingTest extends BaseChromeTest {
    @Test
    public void prints() throws IOException {
        driver.get("https://www.selenium.dev");

        String content = ((RemoteWebDriver) driver).print(new PrintOptions()).getContent();
        byte[] bytes = Base64.getDecoder().decode(content);
        Files.write(artifactsDir().resolve("selenium.pdf"), bytes);
    }
}
