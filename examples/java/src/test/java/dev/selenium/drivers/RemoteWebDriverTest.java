package dev.selenium.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.Test;

import dev.selenium.BaseTest;

public class RemoteWebDriverTest extends BaseTest {
	
	@Test
	public void fireFoxOptionsTest() throws MalformedURLException
	{	
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		WebDriver driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
		driver.get("https://www.google.com");
		driver.quit();
	}
	

	
	@Test
	public void chromeoptionsTest() throws MalformedURLException, InterruptedException
	{
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability("browserVersion", "117.0.5938.149");
		chromeOptions.setCapability("platformName", "Windows");
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
		driver.get("https://www.google.com");
		driver.quit();
		
	}
	
	
	@Test
	public void localFileDetector()
	{
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.setCapability("browserVersion", "117.0.5938.149");
	chromeOptions.setCapability("platformName", "Windows");
	WebDriver driver = new RemoteWebDriver(chromeOptions);
	((ChromiumDriver) driver).setFileDetector(new LocalFileDetector());		
	driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
	WebElement upload = driver.findElement(By.id("myfile"));
	upload.sendKeys("C:\\Users\\Minakshi\\darkbulb.jpg");
	}
	
}
