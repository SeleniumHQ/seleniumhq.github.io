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
		String version = "latest";
		chromeOptions.setBrowserVersion(version);
		assert version.equals(chromeOptions.getBrowserVersion());
	}

	@Test
	public void setPlatformName() {
		ChromeOptions chromeOptions = new ChromeOptions();
		String platform = "OS X 10.6";
		chromeOptions.setPlatformName(platform);
		assert platform.equals(chromeOptions.getPlatformName());
	}

	@Test
	public void setScriptTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration duration = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setScriptTimeout(duration);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getScriptTimeout();
			assert timeout.equals(duration);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void setPageLoadTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration duration = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setPageLoadTimeout(duration);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getPageLoadTimeout();
			assert timeout.equals(duration);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void setImplicitWaitTimeout() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Duration duration = Duration.of(5, ChronoUnit.SECONDS);
		chromeOptions.setImplicitWaitTimeout(duration);

		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
			assert timeout.equals(duration);
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

