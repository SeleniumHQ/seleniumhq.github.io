package dev.selenium.drivers;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class OptionsTest extends BaseTest {

  @Test
  public void setPageLoadStrategyNormal() {
    ChromeOptions chromeOptions = new ChromeOptions();
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
    ChromeOptions chromeOptions = new ChromeOptions();
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
    ChromeOptions chromeOptions = new ChromeOptions();
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
    ChromeOptions chromeOptions = new ChromeOptions();
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
		ChromeOptions chromeOptions = new ChromeOptions();
		String name = chromeOptions.getBrowserName();
		assert !name.isEmpty();
	}

	@Test
	public void setBrowserVersion() {
		ChromeOptions chromeOptions = new ChromeOptions();
		String expected = "latest";
		chromeOptions.setBrowserVersion(expected);
		assert expected.equals(chromeOptions.getBrowserVersion());
	}

	@Test
	public void setPlatformName() {
		ChromeOptions chromeOptions = new ChromeOptions();
		String expected = "OS X 10.6";
		chromeOptions.setPlatformName(expected);
		Platform name = chromeOptions.getPlatformName();
		assert name.toString().equals(expected);
	}

	@Test
	public void setScriptTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration expected = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setScriptTimeout(expected);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getScriptTimeout();
			assert timeout.equals(expected);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void setPageLoadTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration expected = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setPageLoadTimeout(expected);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getPageLoadTimeout();
			assert timeout.equals(expected);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void setImplicitWaitTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration expected = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setImplicitWaitTimeout(expected);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
			assert timeout.equals(expected);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void setUnhandledPromptBehaviour() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		assert chromeOptions.getCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR).equals(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
	}

	@Test
  	public void setWindowRect() {
    	ChromeOptions chromeOptions = new ChromeOptions();
    	chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
    	assert chromeOptions.getCapability(CapabilityType.SET_WINDOW_RECT).equals(true);
  	}
	
	@Test
	public void setStrictFileInteractability() {
	    ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
	    assert chromeOptions.getCapability(CapabilityType.STRICT_FILE_INTERACTABILITY).equals(true);
	}
}

