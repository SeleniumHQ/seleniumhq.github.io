# Fresh browser per test

URL: https://www.selenium.dev/documentation/test_practices/encouraged/fresh_browser_per_test/
Site index: https://www.selenium.dev/llms.txt

---

Start each test from a clean, known state.
Ideally, spin up a new virtual machine for each test.
If spinning up a new virtual machine is not practical,
at least start a new WebDriver for each test.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.
```java
WebDriver driver = new FirefoxDriver();
```
