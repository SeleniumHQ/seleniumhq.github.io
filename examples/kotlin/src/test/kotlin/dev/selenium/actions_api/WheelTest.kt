package dev.selenium.actions_api

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.WheelInput

class WheelTest : BaseTest() {
    
    @Test
    fun shouldScrollToElement() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

        val iframe = driver.findElement(By.tagName("iframe"))
        Actions(driver)
                .scrollToElement(iframe)
                .perform()

        Assertions.assertTrue(inViewport(iframe))
    }
    
    @Test
    fun shouldScrollFromViewportByGivenAmount() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

        val footer = driver.findElement(By.tagName("footer"))
        val deltaY = footer.getRect().y
        Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform()

        Assertions.assertTrue(inViewport(footer))
    }   
    
    @Test
    fun shouldScrollFromElementByGivenAmount() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

        val iframe = driver.findElement(By.tagName("iframe"))
        val scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe)
        Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, 200)
                .perform()

        driver.switchTo().frame(iframe)
        val checkbox = driver.findElement(By.name("scroll_checkbox"))

        Assertions.assertTrue(inViewport(checkbox))
    }
    
    @Test
    fun shouldScrollFromElementByGivenAmountWithOffset() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

        val footer = driver.findElement(By.tagName("footer"))
        val scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50)
        Actions(driver)
                .scrollFromOrigin(scrollOrigin,0, 200)
                .perform()

        val iframe = driver.findElement(By.tagName("iframe"))
        driver.switchTo().frame(iframe)
        val checkbox = driver.findElement(By.name("scroll_checkbox"))
        Assertions.assertTrue(inViewport(checkbox))
    }    
    
    @Test
    fun shouldScrollFromViewportByGivenAmountFromOrigin() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html")

        val scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10)
        Actions(driver)
                .scrollFromOrigin(scrollOrigin, 0, 200)
                .perform()

        val iframe = driver.findElement(By.tagName("iframe"))
        driver.switchTo().frame(iframe)
        val checkbox = driver.findElement(By.name("scroll_checkbox"))

        Assertions.assertTrue(inViewport(checkbox))
    }
    
    fun inViewport(element: WebElement): Boolean {
        val script = "for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;\ne.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;\nreturn f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>\nwindow.pageYOffset&&t+o>window.pageXOffset"
        return (driver as JavascriptExecutor).executeScript(script, element) as Boolean 
    }
}
