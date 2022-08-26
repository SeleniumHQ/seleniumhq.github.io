package dev.selenium.actions_api

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.Rectangle
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.remote.RemoteWebDriver

import java.time.Duration
import java.util.Collections

class MouseTest : BaseTest() {
        
    @Test
    fun clickAndHold() { 
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val clickable = driver.findElement(By.id("clickable"))
        Actions(driver)
                .clickAndHold(clickable)
                .perform()

        Assertions.assertEquals("focused", driver.findElement(By.id("click-status")).getText())
    } 

    @Test
    fun clickAndRelease() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val clickable = driver.findElement(By.id("click"))
        Actions(driver)
                .click(clickable)
                .perform()

        Assertions.assertTrue(driver.getCurrentUrl().contains("resultPage.html"))
    }
  
    @Test
    fun rightClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val clickable = driver.findElement(By.id("clickable"))
        Actions(driver)
                .contextClick(clickable)
                .perform()

        Assertions.assertEquals("context-clicked", driver.findElement(By.id("click-status")).getText())
    }
      
    @Test
    fun backClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")
        driver.findElement(By.id("click")).click()
        Assertions.assertEquals(driver.getTitle(), "We Arrive Here")

        val mouse = PointerInput(PointerInput.Kind.MOUSE, "default mouse")

        val actions = Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()))

        (driver as RemoteWebDriver).perform(Collections.singletonList(actions))

        Assertions.assertEquals("BasicMouseInterfaceTest", driver.getTitle())
    }
     
    @Test
    fun forwardClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")
        driver.findElement(By.id("click")).click()
        driver.navigate().back()
        Assertions.assertEquals(driver.getTitle(), "BasicMouseInterfaceTest")

        val mouse = PointerInput(PointerInput.Kind.MOUSE, "default mouse")

        val actions = Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()))

        (driver as RemoteWebDriver).perform(Collections.singletonList(actions))

        Assertions.assertEquals("We Arrive Here", driver.getTitle())
    }
 
    @Test
    fun doubleClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val clickable = driver.findElement(By.id("clickable"))
        Actions(driver)
                .doubleClick(clickable)
                .perform()

        Assertions.assertEquals("double-clicked", driver.findElement(By.id("click-status")).getText())
    }

    @Test
    fun hovers() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val hoverable = driver.findElement(By.id("hover"))
        Actions(driver)
                .moveToElement(hoverable)
                .perform()

        Assertions.assertEquals("hovered", driver.findElement(By.id("move-status")).getText())
    }

    @Test
    fun moveByOffsetFromElement() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")
        driver.manage().window().fullscreen()

        val tracker = driver.findElement(By.id("mouse-tracker"))
        Actions(driver)
                .moveToElement(tracker, 8, 0)
                .perform()

        val result = driver.findElement(By.id("relative-location")).getText().split(", ")
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 100 - 8) < 2)
    }

    @Test
    fun moveByOffsetFromViewport() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val mouse = PointerInput(PointerInput.Kind.MOUSE, "default mouse")

        val actions = Sequence(mouse, 0)
                .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 12))

        (driver as RemoteWebDriver).perform(Collections.singletonList(actions))

        val result = driver.findElement(By.id("absolute-location")).getText().split(", ")
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 8) < 2)
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[1]) - 12) < 2)
    }

    @Test
    fun moveByOffsetFromCurrentPointer() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val mouse = PointerInput(PointerInput.Kind.MOUSE, "default mouse")

        val actions = Sequence(mouse, 0)
                .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 11))
        (driver as RemoteWebDriver).perform(Collections.singletonList(actions))

        Actions(driver)
                .moveByOffset(13, 15)
                .perform()

        val result = driver.findElement(By.id("absolute-location")).getText().split(", ") 
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 8 - 13) < 2)
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[1]) - 11 - 15) < 2)
    }
    
    @Test
    fun dragsToElement() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val draggable = driver.findElement(By.id("draggable"))
        val droppable = driver.findElement(By.id("droppable"))
        Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform()

        Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText())
    }
        
    @Test
    fun dragsByOffset() { 
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html")

        val draggable = driver.findElement(By.id("draggable"))
        val start = draggable.getRect()
        val finish = driver.findElement(By.id("droppable")).getRect()
        Actions(driver)
                .dragAndDropBy(draggable, finish.getX() - start.getX(), finish.getY() - start.getY())
                .perform()

        Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText())
    }
}
