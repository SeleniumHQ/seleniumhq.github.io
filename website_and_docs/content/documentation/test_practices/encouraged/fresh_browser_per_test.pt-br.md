---
title: "Navegador novo por teste"
linkTitle: "Navegador novo por teste"
weight: 11
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/fresh_browser_per_test/",
"/pt-br/documentation/guidelines/fresh_browser_per_test/"
]
---

Comece cada teste a partir de um estado limpo conhecido.
Idealmente, ligue uma nova máquina virtual para cada teste.
Se ligar uma nova máquina virtual não for prático,
pelo menos inicie um novo WebDriver para cada teste.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.
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
