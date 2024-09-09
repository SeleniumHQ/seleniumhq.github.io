package dev.selenium.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import dev.selenium.BaseTest;

public class LocatorsTest extends BaseTest {

    WebDriver driver = new ChromeDriver();
    // Navigate to Url
    driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");

    // Find element using ByChained
    // utilizes html described on page
    By example = new ByChained(By.id("parentDiv"), By.id("phoneNumber"));
    driver.findElements(example);

}


/* ByChained examples utilizes the following HTML located in locators.md

<html>
    <body>
        <div id="parentDiv">
            <input id="phoneNumber" class="el-input" type="text"/>
        </div>
        <input id="phoneNumber" class="el-input" type="number"/>
    </body>
</html>

*/