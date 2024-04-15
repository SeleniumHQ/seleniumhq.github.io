package dev.selenium.getting_started;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetupAndTeardownTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private By projectsLink = By.xpath("//a/span[text()='Projects']");
	private By searchFieldSpan = By.xpath("//span[text()='Search']");
	private By searchField = By.id("docsearch-input");
	private By searchResultsFirstItem = By.cssSelector("ul[role='listbox'] li");

	@BeforeAll
	public static void beforeAll() {
		System.out.println("This method will be executed only once before all the tests.");
	}

	@BeforeEach
	public void setup() {
		System.out.println(
				"  This method will be executed before each test, so here write the common code that has to be executed.");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.selenium.dev/documentation/webdriver/getting_started/using_selenium/");
	}

	@Test
	public void projectsAreDisplayed_whenNavigatedToProjectsPage() {
		driver.findElement(projectsLink).click();
		String expectedUrl = "https://www.selenium.dev/projects/";
		String actualUrl = driver.getCurrentUrl();
		assertEquals(expectedUrl, actualUrl);
	}

	@Test
	public void searchFieldTest() {
		driver.findElement(searchFieldSpan).click();
		driver.findElement(searchField).sendKeys("Test Practices");

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsFirstItem));

		driver.findElement(searchResultsFirstItem).click();

		String expectedUrl = "https://www.selenium.dev/documentation/test_practices/";
		String actualUrl = driver.getCurrentUrl();
		assertEquals(expectedUrl, actualUrl);

		assertTrue(driver.findElement(By.xpath("//h1[.='Test Practices']")).isDisplayed());
	}

	@AfterEach
	public void teardown() {
		System.out.println("  This method will be executed after each test, so here write the clean up code.");
		driver.quit();
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("This method will be executed only once after all the tests.");
	}

}
