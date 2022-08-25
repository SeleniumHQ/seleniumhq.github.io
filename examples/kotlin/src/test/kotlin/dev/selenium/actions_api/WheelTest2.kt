package dev.selenium.actions_api;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

class WheelTest : BaseTest() {
    
    @Test
    fun shouldScrollToElement() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html");

        val iframe = driver.findElement(By.tagName("iframe"));
        Actions(driver)
                .scrollToElement(iframe)
                .perform();

        Assertions.assertTrue(inViewport(iframe))
    }

    fun inViewport(element: WebElement): Boolean {
        val script = "for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;\ne.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;\nreturn f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>\nwindow.pageYOffset&&t+o>window.pageXOffset";
        return (driver as JavascriptExecutor).executeScript(script, element) as Boolean 
    }
}
