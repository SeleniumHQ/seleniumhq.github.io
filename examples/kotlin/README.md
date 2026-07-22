# Running all tests from Selenium kotlin example

Follow these steps to run all test examples from selenium kotlin

1. Clone this repository

```
git clone https://github.com/SeleniumHQ/seleniumhq.github.io.git
```

2. Navigate to `kotlin` directory

```
cd seleniumhq.github.io/examples/kotlin
```

3. Install dependencies (using maven)

```
mvn clean install
```

4. Run all tests

```
mvn test
```
> or to run all tests in a specific class you can run `mvn -Dtest=classname test`

# Execute a kotlin script

Use this command to run a specific Kotlin example and follow the first script example

```
mvn -Dtest=dev.selenium.getting_started.FirstScriptTest test
```