package dev.selenium.support;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

public class ColorsTest extends BaseTest {

    @Test
    public void colorFormats() {
        Color hexColour = Color.fromString("#2F7ED8");
        Color rgbColour = Color.fromString("rgb(255, 255, 255)");
        Color rgbColourPercent = Color.fromString("rgb(40%, 20%, 40%)");
        Color rgbaColour = Color.fromString("rgba(255, 255, 255, 0.5)");
        Color rgbaColourPercent = Color.fromString("rgba(40%, 20%, 40%, 0.5)");
        Color hslColour = Color.fromString("hsl(100, 0%, 50%)");
        Color hslaColour = Color.fromString("hsla(100, 0%, 50%, 0.5)");

        Assertions.assertEquals("#2f7ed8", hexColour.asHex());
        Assertions.assertEquals("rgb(255, 255, 255)", rgbColour.asRgb());
        Assertions.assertEquals("rgb(102, 51, 102)", rgbColourPercent.asRgb());
        Assertions.assertEquals("rgba(255, 255, 255, 0.5)", rgbaColour.asRgba());
        Assertions.assertEquals("rgba(102, 51, 102, 0.5)", rgbaColourPercent.asRgba());
        Assertions.assertEquals("rgb(128, 128, 128)", hslColour.asRgb());
        Assertions.assertEquals("rgba(128, 128, 128, 0.5)", hslaColour.asRgba());
    }

    @Test
    public void namedColors() {
        Color black = Color.fromString("black");
        Color chocolate = Color.fromString("chocolate");
        Color hotpink = Color.fromString("hotpink");

        Assertions.assertEquals("#000000", black.asHex());
        Assertions.assertEquals("#d2691e", chocolate.asHex());
        Assertions.assertEquals("#ff69b4", hotpink.asHex());
    }

    @Test
    public void transparentColor() {
        Color transparent = Color.fromString("transparent");

        Assertions.assertEquals("rgba(0, 0, 0, 0)", transparent.asRgba());
    }

    @Test
    public void elementColors() {
        startChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/colorPage.html");
        Color green = Color.fromString("green");

        Color elementColour = Color.fromString(driver.findElement(By.id("namedColor")).getCssValue("color"));
        Color elementBackgroundColour = Color.fromString(driver.findElement(By.id("namedColor")).getCssValue("background-color"));

        Assertions.assertEquals(Color.fromString("black"), elementColour);

        Assertions.assertEquals(green, elementBackgroundColour);

        Assertions.assertEquals("#008000", elementBackgroundColour.asHex());
        Assertions.assertEquals("rgba(0, 128, 0, 1)", elementBackgroundColour.asRgba());
        Assertions.assertEquals("rgb(0, 128, 0)", elementBackgroundColour.asRgb());
    }
}
