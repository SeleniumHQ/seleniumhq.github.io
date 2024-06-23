# Running all tests from Selenium Java example

Follow these steps to run all test example from selenium Java

1. Clone this repository

```
git clone https://github.com/SeleniumHQ/seleniumhq.github.io.git
```

2. Navigate to `java` directory

```
cd seleniumhq.github.io/examples/java
```

3. Install dependencies using maven

```
mvn compile
```

4. Run all tests

```
mvn test
```

> Please keep some patience - If you are doing it for the first time, it will take a little while to verify and download the browser drivers

# Execute a first Java example

Use this command to run first Java Example

```
mvn exec:java -D"exec.mainClass"="dev.selenium.getting_started.FirstScript" -D"exec.classpathScope"=test
```