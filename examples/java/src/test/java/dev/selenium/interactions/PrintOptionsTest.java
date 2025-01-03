package dev.selenium.interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.print.PageMargin;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.print.PageSize;
import dev.selenium.BaseChromeTest;

public class PrintOptionsTest extends BaseChromeTest {

    @Test
    public void TestOrientation() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setOrientation(PrintOptions.Orientation.LANDSCAPE);
        PrintOptions.Orientation current_orientation = printOptions.getOrientation();
    }

    @Test
    public void TestRange() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setPageRanges("1-2");
        String[] current_range = printOptions.getPageRanges();
    }

    @Test
    public void TestSize() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setPageSize(new PageSize(27.94, 21.59)); // A4 size in cm
        double currentHeight = printOptions.getPageSize().getHeight(); // use getWidth() to retrieve width
    }

    @Test
    public void TestMargins() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        PageMargin margins = new PageMargin(1.0,1.0,1.0,1.0);
        printOptions.setPageMargin(margins);
        double topMargin = margins.getTop();
        double bottomMargin = margins.getBottom();
        double leftMargin = margins.getLeft();
        double rightMargin = margins.getRight();
    }

    @Test
    public void TestScale() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setScale(.50);
        double current_scale = printOptions.getScale();
    }

    @Test
    public void TestBackground() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setBackground(true);
        boolean current_background = printOptions.getBackground();
    }

    @Test
    public void TestShrinkToFit() 
    {
        driver.get("https://www.selenium.dev/");
        PrintOptions printOptions = new PrintOptions();
        printOptions.setShrinkToFit(true);
        boolean current_shrink_to_fit = printOptions.getShrinkToFit();
    }
}
