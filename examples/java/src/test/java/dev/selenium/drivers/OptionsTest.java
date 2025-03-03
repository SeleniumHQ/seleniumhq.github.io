package dev.selenium.drivers;

import dev.selenium.BaseTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;

public class OptionsTest extends BaseTest {

  @Test
  public void setPageLoadStrategyNormal() {
    ChromeOptions chromeOptions = getDefaultChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setPageLoadStrategyEager() {
    ChromeOptions chromeOptions = getDefaultChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setPageLoadStrategyNone() {
    ChromeOptions chromeOptions = getDefaultChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void setAcceptInsecureCerts() {
    ChromeOptions chromeOptions = getDefaultChromeOptions();
    chromeOptions.setAcceptInsecureCerts(true);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://selenium.dev");
    } finally {
      driver.quit();
    }
  }

  @Test
  public void getBrowserName() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	String name = chromeOptions.getBrowserName();
	Assertions.assertFalse(name.isEmpty(), "Browser name should not be empty");
  }

  @Test
  public void setBrowserVersion() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	String version = "latest";
	chromeOptions.setBrowserVersion(version);
	Assertions.assertEquals(version, chromeOptions.getBrowserVersion());
  }

  @Test
  public void setPlatformName() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	String platform = "OS X 10.6";
	chromeOptions.setPlatformName(platform);
	Assertions.assertEquals(platform, chromeOptions.getPlatformName().toString());
  }

  @Test
  public void setScriptTimeout() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	Duration duration = Duration.of(5, ChronoUnit.SECONDS);
	chromeOptions.setScriptTimeout(duration);

	WebDriver driver = new ChromeDriver(chromeOptions);
	try {
	  Duration timeout = driver.manage().timeouts().getScriptTimeout();
	  Assertions.assertEquals(timeout, duration, "The script timeout should be set to 5 seconds.");
	} finally {
	  driver.quit();
	}
  }

  @Test
  public void setPageLoadTimeout() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	Duration duration = Duration.of(5, ChronoUnit.SECONDS);
	chromeOptions.setPageLoadTimeout(duration);

	WebDriver driver = new ChromeDriver(chromeOptions);
	try {
	  Duration timeout = driver.manage().timeouts().getPageLoadTimeout();
	  Assertions.assertEquals(timeout, duration, "The page load timeout should be set to 5 seconds.");
	} finally {
	  driver.quit();
	}
  }

  @Test
  public void setImplicitWaitTimeout() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	Duration duration = Duration.of(5, ChronoUnit.SECONDS);
	chromeOptions.setImplicitWaitTimeout(duration);

	WebDriver driver = new ChromeDriver(chromeOptions);
	try {
	  Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
	  Assertions.assertEquals(timeout, duration, "The implicit wait timeout should be set to 5 seconds.");
	} finally {
	  driver.quit();
	}
  }

  @Test
  public void setUnhandledPromptBehaviour() {
	ChromeOptions chromeOptions = getDefaultChromeOptions();
	chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
	//verify the capability object is not null
	Object capabilityObject = chromeOptions.getCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR);
	Assertions.assertNotNull(capabilityObject, "Capability UNHANDLED_PROMPT_BEHAVIOUR should not be null.");
	Assertions.assertEquals(capabilityObject.toString(), UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY.toString());
  }

  @Test
  public void setWindowRect() {
   	ChromeOptions chromeOptions = getDefaultChromeOptions();
   	chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
   	//verify the capability object is not null
   	Object capabilityObject = chromeOptions.getCapability(CapabilityType.SET_WINDOW_RECT);
    Assertions.assertNotNull(capabilityObject, "Capability SET_WINDOW_RECT should not be null.");

    Boolean capability = (Boolean) capabilityObject;
    Assertions.assertTrue(capability, "The capability SET_WINDOW_RECT should be set to true.");
  }
	
  @Test
  public void setStrictFileInteractability() {
    ChromeOptions chromeOptions = getDefaultChromeOptions();
    chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
	//verify the capability object is not null
    Object capabilityObject = chromeOptions.getCapability(CapabilityType.STRICT_FILE_INTERACTABILITY);
    Assertions.assertNotNull(capabilityObject, "Capability STRICT_FILE_INTERACTABILITY should not be null.");

    Boolean capability = (Boolean) capabilityObject;
    Assertions.assertTrue(capability, "The capability STRICT_FILE_INTERACTABILITY should be set to true.");
  }
}

