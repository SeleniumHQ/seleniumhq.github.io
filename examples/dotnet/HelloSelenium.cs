using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Hello;

public static class HelloSelenium
{
    public static void Main()
    {
        var driver = new ChromeDriver();
            
        driver.Navigate().GoToUrl("https://selenium.dev");
            
        driver.Quit();
    }
}