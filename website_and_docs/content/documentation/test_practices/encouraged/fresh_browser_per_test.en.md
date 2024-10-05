---
title: "Fresh browser per test"
linkTitle: "Fresh browser per test"
weight: 11
aliases: [
"/documentation/en/guidelines_and_recommendations/fresh_browser_per_test/",
"/documentation/guidelines/fresh_browser_per_test/"
]
---

Start each test from a clean, known state.
Ideally, spin up a new virtual machine for each test.
If spinning up a new virtual machine is not practical,
at least start a new WebDriver for each test.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.

A new browser per test can be achieved by using a test framework's "before each test" hook or fixture. This also implies using the "after each test" hook to close the browser.

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

A static WebDriver will cause multiple issues both with parallel test execution but also with keeping alignment with this approach of one browser per test. Aditionally this forces the code to deal with ThreadLocal type techniques that unneccesarily complicate the code.

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
