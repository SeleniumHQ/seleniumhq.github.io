package dev.selenium.actions_api;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.Collections;

public class MouseTest extends BaseTest {
    @Test
    public void clickAndHold() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .clickAndHold(clickable)
                .perform();

        Assertions.assertEquals("focused", driver.findElement(By.id("click-status")).getText());
    }

    @Test
    public void clickAndRelease() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement clickable = driver.findElement(By.id("click"));
        new Actions(driver)
                .click(clickable)
                .perform();

        Assertions.assertTrue(driver.getCurrentUrl().contains("resultPage.html"));
    }

    @Test
    public void rightClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .contextClick(clickable)
                .perform();

        Assertions.assertEquals("context-clicked", driver.findElement(By.id("click-status")).getText());
    }

    @Test
    public void backClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        driver.findElement(By.id("click")).click();
        Assertions.assertEquals(driver.getTitle(), "We Arrive Here");

        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));

        Assertions.assertEquals("BasicMouseInterfaceTest", driver.getTitle());
    }

    @Test
    public void forwardClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        driver.findElement(By.id("click")).click();
        driver.navigate().back();
        Assertions.assertEquals(driver.getTitle(), "BasicMouseInterfaceTest");

        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));

        Assertions.assertEquals("We Arrive Here", driver.getTitle());
    }

    @Test
    public void doubleClick() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .doubleClick(clickable)
                .perform();

        Assertions.assertEquals("double-clicked", driver.findElement(By.id("click-status")).getText());
    }

    @Test
    public void hovers() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement hoverable = driver.findElement(By.id("hover"));
        new Actions(driver)
                .moveToElement(hoverable)
                .perform();

        Assertions.assertEquals("hovered", driver.findElement(By.id("move-status")).getText());
    }

    @Test
    public void moveByOffsetFromElement() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        driver.manage().window().fullscreen();

        WebElement tracker = driver.findElement(By.id("mouse-tracker"));
        new Actions(driver)
                .moveToElement(tracker, 8, 0)
                .perform();

        String[] result = driver.findElement(By.id("relative-location")).getText().split(", ");
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 100 - 8) < 2);
    }

    @Test
    public void moveByOffsetFromViewport() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 12));

        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));

        String[] result = driver.findElement(By.id("absolute-location")).getText().split(", ");
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 8) < 2);
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[1]) - 12) < 2);
    }

    @Test
    public void moveByOffsetFromCurrentPointer() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

        Sequence actions = new Sequence(mouse, 0)
                .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 11));
        ((RemoteWebDriver) driver).perform(Collections.singletonList(actions));

        new Actions(driver)
                .moveByOffset(13, 15)
                .perform();

        String[] result = driver.findElement(By.id("absolute-location")).getText().split(", ");
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[0]) - 8 - 13) < 2);
        Assertions.assertTrue(Math.abs(Integer.parseInt(result[1]) - 11 - 15) < 2);
    }

    @Test
    public void dragsToElement() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();

        Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText());
    }

    @Test
    public void dragsByOffset() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement draggable = driver.findElement(By.id("draggable"));
        Rectangle start = draggable.getRect();
        Rectangle finish = driver.findElement(By.id("droppable")).getRect();
        new Actions(driver)
                .dragAndDropBy(draggable, finish.getX() - start.getX(), finish.getY() - start.getY())
                .perform();

        Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText());
    }
}
