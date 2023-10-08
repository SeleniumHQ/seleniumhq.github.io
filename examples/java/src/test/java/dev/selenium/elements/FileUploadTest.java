package dev.selenium.elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class FileUploadTest {

    @Test
    public void fileUploadTest() {
        WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		driver.get("https://the-internet.herokuapp.com/upload");
		Path path = Paths.get("src/test/resources/selenium-snapshot.png");
        File imagePath = new File(path.toUri());

		//we want to import selenium-snapshot file.
		driver.findElement(By.id("file-upload")).sendKeys(imagePath.toString());
		driver.findElement(By.id("file-submit")).submit();
		if(driver.getPageSource().contains("File Uploaded!")) {
			System.out.println("file uploaded");
		}
		else{
				System.out.println("file not uploaded");
			}
		driver.quit();
    }
}