package dev.selenium.troubleshooting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoggingTest extends BaseTest {

    @AfterEach
    public void loggingOff() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.INFO);
        });
    }

    @Test
    public void logging() throws IOException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.FINE);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.FINE);
        });

      Path output = artifactsDir().resolve("selenium.xml");
      Handler handler = new FileHandler(output.toString());
        logger.addHandler(handler);

        Logger.getLogger(RemoteWebDriver.class.getName()).setLevel(Level.FINEST);
        Logger.getLogger(SeleniumManager.class.getName()).setLevel(Level.SEVERE);

        Logger localLogger = Logger.getLogger(this.getClass().getName());
        localLogger.warning("this is a warning");
        localLogger.info("this is useful information");
        localLogger.fine("this is detailed debug information");

        byte[] bytes = Files.readAllBytes(output);
        String fileContent = new String(bytes);

        Assertions.assertTrue(fileContent.contains("this is a warning"));
        Assertions.assertTrue(fileContent.contains("this is useful information"));
        Assertions.assertTrue(fileContent.contains("this is detailed debug information"));
    }
}
