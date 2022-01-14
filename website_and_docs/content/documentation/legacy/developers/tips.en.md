---
title: "Developer Tips"
linkTitle: "Tips"
weight: 10
description: >
    Details on how to execute Selenium Test Suite with Crazy Fun.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Developer-Tips)

## Running an Individual Test

When developing WebDriver, it is common to want to run a single test rather than the entire test suite for a particular driver.

You can run all the tests in a given test class this way:
```
./go test_firefox onlyRun=CombinedInputActionsTest
```

You can also run a single test directly from the command line by typing:
```
./go test_firefox method=foo
```

## Not Halting On Errors Or Failures

The test suite will halt on errors and failures by default.  You can disable this behaviour by setting the `haltonerror` or `haltonfailure` environmental variables to `0`.

## Reviewing the Logs For the Tests

When you run the tests, the test results don't appear on the screen. They are written to the `./build/test\_logs' folder. A pair of files are written. Their names are relatively consistent and include the details of the tests which were run. The pair comprise a txt file and an xml file. The xml file contains more information about the runtime environment such as the path, Ant version, etc. These files are overwritten the next time the same test target is executed so you may want to archive results if they're important to you.

## Using Rake

Rake is very similar to using other build tools such as "make" or "ant". You can specify a "target" to run by adding it as a parameter, and you can add more than one target at a time. Note that since WebDriver does not rely on ruby being installed and uses JRuby, rake should **not** be involved directly - use the _go_ script instead. For example, in order to clean the build and then build and run the HtmlUnitDriver tests:

```
./go clean test_htmlunit
```

The default target that's used will compile the code and run all the tests. More interesting targets are:

| **Target** | **Description** |
|:-----------|:----------------|
| clean      | Delete the contents of the build directory, removing all compiled artifacts |
| test       | Compile the dependencies of and run all the tests for the HtmlUnitDriver, FirefoxDriver, and InternetExplorerDriver as well as the support library's tests |
| firefox    | Compile the FirefoxDriver |
| htmlunit   | Compile the HtmlUnitDriver |
| ie         | Compile the InternetExplorerDriver. This won't compile the C++ on a non-Windows system, but will always compile the Java, no matter which OS you happen to be using |
| support    | Guess what this does :) |
| test\_htmlunit | Compile the dependencies and then run the tests for the HtmlUnitDriver. The same "test\_x" pattern can be followed for all the compilation targets in this table. |

### Running a remote Debugger with Java tests
You can run the tests in debug mode and wait for a remote java listener (which one would setup in eclipse or intellij).
```
./go debug=true suspend=true test_firefox
```

## Debugging the Firefox Driver
### Getting output from the Firefox process itself
This is usually useful to debug issues with Firefox starting up. The Java system property `webdriver.firefox.logfile` will instruct the FirefoxDriver to redirect the output to a file:
```
java -Dwebdriver.firefox.logfile=/dev/stdout -cp selenium-2.jar <sometest>
```

### Outputting to the Error Console
A common technique used for debugging of the Firefox driver extension is debug statements. The two following methods can be used from almost any Javascript code inside the extension:
* `Logger.dumpn()` - Logs a string into console (and converts arguments to strings). For example: `Logger.dumpn("Found element: " + node)`.
* `Logger.dump()` - Gets a single argument, an object, and dumps its entire contents: implemented interfaces, data fields, methods, etc.

### Getting output from the error console to a file
To see output generated using the `Logger` utility, one has to open up Firefox's error console - difficult or simply impossible on remote machines. Fortunately, there's a way to get the contents of the output dumped to a file:
```
FirefoxProfile p = new FirefoxProfile();
p.setPreference("webdriver.log.file", "/tmp/firefox_console");
WebDriver driver = new FirefoxDriver(p);
...
```
The `webdriver.log.file` preference will instruct the `Logger` to dump all contents of the console to the specified file.
webdriver.log.file
### Getting even more output to the command line
When suspecting additional logging from Firefox could be beneficial, one can crank debugging level all the way up:
```
export NSPR_LOG_MODULES=all:3
```
Setting this environment variable will cause Firefox to log additional messages to the console. Use this environment variable together with `webdriver.firefox.logfile` to get a hold of Firefox's output to the console.

## Debugging the Internet Explorer Driver
In order to get detailed information from IEDriverServer.exe you can run tests with the option devMode=true, this option will set logging level to DEBUG and redirect log output to the file iedriver.log
```
./go test_ie devMode=true
```

## Adding a test
Most of WebDriver's test cases live under java/client/test/org/openqa/selenium. For example, to demonstrate an issue with clicking on elements, a test case should be added to  ClickTest. The test cases already have a driver instance - no need to create one.
The test use pages that are served by an in-process server, served from common/src/web. Their URLs are provided by the Pages class, so when adding a page and add it to the Pages class as well.

## Manually interacting with `RemoteWebDriverServer`
We can use a web browser or tools such as telnet to interact with a RemoteWebDriverServer e.g. to debug the JSON protocol. Here's a simple example of checking the status of a server installed on the local machine

In a web browser
```
http://localhost:8080/wd/hub/status/

```

In telnet
```
telnet localhost 8080

GET /wd/hub/status/ HTTP/1.0

```

On Macs and Unix in general try `curl`

```
curl  http://localhost:8080/wd/hub/status
```

And on linux `wget`
```
wget http://localhost:8080/wd/hub/status
```

In all these cases the RemoteWebDriverServer should respond with
```

{status:0} 

```
