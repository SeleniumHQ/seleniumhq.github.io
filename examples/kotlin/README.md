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

## Run a single Kotlin example

Run only the first script example test:

```
mvn -Dtest=dev.selenium.getting_started.FirstScriptTest test
```