package dev.selenium.elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InteractionTest {

    @Test
    public void interactWithElements() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/inputs.html");

	    // Click on the element 
        WebElement checkInput=driver.findElement(By.name("checkbox_input"));
        checkInput.click();
        Boolean isChecked=checkInput.isSelected();
        assertEquals(isChecked,false);

        //SendKeys
        // Clear field to empty it from any previous data
        WebElement emailInput=driver.findElement(By.name("email_input"));
        emailInput.clear();
	    //Enter Text
        String email="admin@localhost.dev";
	    emailInput.sendKeys(email);
        //Verify
        String data=emailInput.getAttribute("value");
        assertEquals(data,email);


        //Clear Element
        // Clear field to empty it from any previous data
        emailInput.clear();
        data=emailInput.getAttribute("value");
        assertEquals(data, ""); 

        driver.quit();
    }

}