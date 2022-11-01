package dev.selenium.support

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

class SelectListTest : BaseTest() {
  
  @BeforeEach
  fun navigate() {
    driver.get("https://www.selenium.dev/selenium/web/formPage.html")
  }
  
  @Test
  fun selectOption() {
    val selectElement = driver.findElement(By.name("selectomatic"))
    val select = Select(selectElement)
    
    val twoElement = driver.findElement(By.cssSelector("option[value=two]"))
    val fourElement = driver.findElement(By.cssSelector("option[value=four]"))
    val countElement = driver.findElement(By.cssSelector("option[value='still learning how to count, apparently']"))
    
    select.selectByVisibleText("Four")
    Assertions.assertTrue(fourElement.isSelected())

    select.selectByValue("two")
    Assertions.assertTrue(twoElement.isSelected())

    select.selectByIndex(3)
    Assertions.assertTrue(countElement.isSelected())
  }
  
  @Test
  fun selectMultipleOption() {
    val selectElement = driver.findElement(By.name("multi"))
    val select = Select(selectElement)
    
    val hamElement = driver.findElement(By.cssSelector("option[value=ham]"))
    val gravyElement = driver.findElement(By.cssSelector("option[value='onion gravy']"))
    val eggElement = driver.findElement(By.cssSelector("option[value=eggs]"))
    val sausageElement = driver.findElement(By.cssSelector("option[value='sausages']"))

    val optionElements = selectElement.findElements(By.tagName("option"))
    val optionList = select.getOptions()
    Assertions.assertEquals(optionElements, optionList)

    val selectedOptionList = select.getAllSelectedOptions()
    val expectedSelection = ArrayList<WebElement>() 
      expectedSelection.add(eggElement)
      expectedSelection.add(sausageElement)

    Assertions.assertEquals(expectedSelection, selectedOptionList)

    select.selectByValue("ham")
    select.selectByValue("onion gravy")
    Assertions.assertTrue(hamElement.isSelected())
    Assertions.assertTrue(gravyElement.isSelected())

    select.deselectByValue("eggs")
    select.deselectByValue("sausages")
    Assertions.assertFalse(eggElement.isSelected())
    Assertions.assertFalse(sausageElement.isSelected())
  }

  @Test
  fun disabledOption() {
    val selectElement = driver.findElement(By.name("single_disabled"))
    val select = Select(selectElement)
    
    Assertions.assertThrows(UnsupportedOperationException::class.java) {
      select.selectByValue("disabled")
    }
  }
}
