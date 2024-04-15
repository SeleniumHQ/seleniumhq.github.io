---
title: "每次测试都刷新浏览器"
linkTitle: "每次测试都刷新浏览器"
weight: 11
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/fresh_browser_per_test/",
"/zh-cn/documentation/guidelines/fresh_browser_per_test/"
]
---
 

每次测试都从一个干净的已知状态开始. 
理想情况下, 为每次测试打开一个新的虚拟机. 
如果打开新虚拟机不切实际, 则至少应为每次测试启动一个新的WebDriver. 
对于Firefox, 请使用您已知的配置文件去启动WebDriver.
大多数浏览器驱动器，像GeckoDriver和ChromeDriver那样，默认都会以干净的已知状态和一个新的用户配置文件开始。
```java
// Using a class variable
public abstract class BaseTest {
	protected WebDriver driver;
    ...

    // Before each test hook
    public void setupTest() {
        driver = new FirefoxDriver();
        ...
    }

    // After each test hook
    public void teardownTest() {
        ...
        driver.quit();
    }
}
```

```python
# Using python fixtures
@pytest.fixture(autouse=True, scope='function')
def driver(self, request, page: Page):
    # Create the driver
    driver = webdriver.Firefox()
    
    # Return control to the test 
    yield self.driver

    # Test ends driver quits
    driver.quit()
```

```java
# Using a static variable

# This forces the ThreadLocal<WebDriver> variable to call driver.get() every time the driver wants to be used.

# In general static variables in non-thread safe code can have unintended consequences and increase the maintanance effort in the code base.

public abstract class BaseTest {
	protected ThreadLocal<WebDriver> driver;
    ...
    // Before each test hook
    public void setupTest() {
        BaseTest.driver = ThreadLocal.withInitial(()->new FirefoxDriver());
        ...
    }
}
```
