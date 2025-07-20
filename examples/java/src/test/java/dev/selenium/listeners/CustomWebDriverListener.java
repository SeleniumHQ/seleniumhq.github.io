package dev.selenium.listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomWebDriverListener implements WebDriverListener {

    private static final Logger logger = Logger.getLogger(CustomWebDriverListener.class.getName());
    // Link refe color https://gist.github.com/dainkaplan/4651352

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";

    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0)
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Object arg : args) {
            sb.append(arg).append(", ");
        }
        if (sb.length() > 1)
            sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    private String getDriverInfo(WebDriver driver) {
        if (driver instanceof RemoteWebDriver remoteDriver) {
            Capabilities caps = remoteDriver.getCapabilities();
            String sessionId = remoteDriver.getSessionId().toString();
            return String.format("Browser: %s, Version: %s, Platform: %s, SessionId: %s",
                    caps.getBrowserName(),
                    caps.getBrowserVersion(),
                    caps.getPlatformName(),
                    sessionId);
        } else {
            return "Unknown WebDriver instance";
        }
    }

    private String getElementInfo(WebElement element) {
        try {
            return element.toString().replaceAll(".*-> ", "").replaceAll("]", "");
        } catch (Exception e) {
            return "Unknown Element";
        }
    }

    // -- WebDriver call hooks --

    @Override
    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
        logger.info("BEFORE: Driver: " + GREEN + driver + RESET + ", Method: " + method.getName() + ", Args: "
                + formatArgs(args));
    }

    @Override
    public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
        logger.info("AFTER: Driver: " + GREEN + driver + RESET + ", Method: " + method.getName() +
                ", Args: " + formatArgs(args) + ", Result: " + result);
    }

    // -- Click hooks --

    @Override
    public void beforeClick(WebElement element) {
        logger.info("BEFORE click -> Element: " + getElementInfo(element));
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("AFTER click -> Element: " + getElementInfo(element));
    }

    // -- FindElement hooks --

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("BEFORE findElement -> Locator: " + locator);
        try {
            if (driver instanceof HasCapabilities) {
                List<WebElement> elements = driver.findElements(locator);
                logger.info("DEBUG: Number of elements matching '" + locator + "': " + elements.size());
            }
        } catch (Exception e) {
            logger.warning("Error while counting elements for locator " + locator + ": " + e.getMessage());
        }
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        logger.info("AFTER findElement -> Locator: " + locator + ", Result: " + getElementInfo(result));
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        logger.info("BEFORE findElements -> Locator: " + locator);
        try {
            if (driver instanceof HasCapabilities) {
                List<WebElement> elements = driver.findElements(locator);
                logger.info("DEBUG: Number of elements matching '" + locator + "': " + elements.size());
            }
        } catch (Exception e) {
            logger.warning("Error while counting elements for locator " + locator + ": " + e.getMessage());
        }
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> elements) {
        logger.info("AFTER findElements -> Locator: " + locator + ", Elements found: " + elements.size());
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.log(Level.SEVERE, "Exception in method '" + method.getName() + "': " + e.getCause(), e);
        takeScreenshotOnError(target, method.getName());
    }

    private void takeScreenshotOnError(Object target, String methodName) {
        if (target instanceof TakesScreenshot) {
            try {
                String directoryPath = "images";
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File screenshot = ((TakesScreenshot) target).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = directoryPath + File.separator + "screenshot_error_" + methodName + "_" + timestamp
                        + ".png";
                Files.copy(screenshot.toPath(), Paths.get(fileName));

                logger.info(GREEN + "Screenshot saved: " + fileName);
            } catch (IOException ioException) {
                logger.severe("Failed to save screenshot: " + ioException.getMessage());
            } catch (Exception ex) {
                logger.severe("Unexpected error during screenshot capture: " + ex.getMessage());
            }
        } else {
            logger.info("Target does not support screenshots.");
        }
    }
}
