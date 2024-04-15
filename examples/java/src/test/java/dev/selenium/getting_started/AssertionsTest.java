package dev.selenium.getting_started;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssertionsTest {

	@Test
	public void assertionTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.selenium.dev/documentation/webdriver/getting_started/using_selenium/");

		String expectedUrl = "https://www.selenium.dev/documentation/webdriver/getting_started/using_selenium/";
		String actualUrl = driver.getCurrentUrl();
		assertEquals(expectedUrl, actualUrl);

		String expectedTitle = "Organizing and Executing Selenium Code | Selenium";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);

		boolean isLinkDisplayed = driver.findElement(By.xpath("//a/span[text()='Using Selenium']")).isDisplayed();
		assertTrue(isLinkDisplayed);

		driver.quit();
	}

}
