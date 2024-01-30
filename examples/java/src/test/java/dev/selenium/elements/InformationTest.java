package dev.selenium.elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InformationTest {

    @Test
    public void informationWithElements() {
    	
    	 WebDriver driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
         // Navigate to Url
         driver.get("https://www.selenium.dev/selenium/web/inputs.html");

    	// isDisplayed        
        // Get boolean value for is element display
        boolean isEmailVisible = driver.findElement(By.name("email_input")).isDisplayed();
        assertEquals(isEmailVisible,true);

        //isEnabled
       //returns true if element is enabled else returns false
        boolean isEnabledButton = driver.findElement(By.name("button_input")).isEnabled();
        assertEquals(isEnabledButton,true);

        //isSelected
        //returns true if element is checked else returns false
        boolean isSelectedCheck = driver.findElement(By.name("checkbox_input")).isSelected();
        assertEquals(isSelectedCheck,true); 

        //TagName
        //returns TagName of the element
        String tagNameInp = driver.findElement(By.name("email_input")).getTagName();
        assertEquals(tagNameInp,"input"); 

        //GetRect
        // Returns height, width, x and y coordinates referenced element
        Rectangle res =  driver.findElement(By.name("range_input")).getRect();
        // Rectangle class provides getX,getY, getWidth, getHeight methods
        assertEquals(res.getX(),10);
        
     
     // Retrieves the computed style property 'font-size' of field
     String cssValue = driver.findElement(By.name("color_input")).getCssValue("font-size");
     assertEquals(cssValue, "13.3333px");
        
        
        //GetText
       // Retrieves the text of the element
        String text = driver.findElement(By.tagName("h1")).getText();
        assertEquals(text, "Testing Inputs");
        
        
        //FetchAttributes
      //identify the email text box
      WebElement emailTxt = driver.findElement(By.name(("email_input")));
     //fetch the value property associated with the textbox
      String valueInfo = emailTxt.getAttribute("value");
      assertEquals(valueInfo,"admin@localhost");
        
        
        driver.quit();
    }

}