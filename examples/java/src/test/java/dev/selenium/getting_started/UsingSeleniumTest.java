package dev.selenium.getting_started;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsingSeleniumTest {

	WebDriver driver;

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
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
	}

	@Test
	public void eightComponents() {

		String title = driver.getTitle();
		assertEquals("Web form", title);

		WebElement textBox = driver.findElement(By.name("my-text"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		textBox.sendKeys("Selenium");
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
		String value = message.getText();
		assertEquals("Received!", value);

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
