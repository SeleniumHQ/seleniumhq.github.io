---
title: "Support classes"
weight: 5
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

WebDriver support classes are provided to simplify maintaining your code.
They provide a nice abstraction to make modeling HTML element(s) as domain
objects easier, also providing helper methods to make using such objects easy to
reason about. We will learn about:

* Locator Strategies
* Events
* LoadableComponent
* ThreadGuard
* etc.

Let's Start:


## **ThreadGuard**
{{% notice info %}}
This class is only available in the Java Binding
{{% /notice %}}
ThreadGuard checks that a driver is called only from the same thread that created it.
Threading issues especially when running tests in Parallel may have mysterious
and hard to diagnose errors. Using this wrapper prevents this category of errors
and will raise an exception when it happens.

The following example simulate a clash of threads:

```java
public class DriverClash {
  //thread main (id 1) created this driver
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver());

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }

  //Thread-1 (id 24) is calling the same driver causing the clash to happen
  Runnable r1 = () -> {protectedDriver.get("https://selenium.dev");};
  Thread thr1 = new Thread(r1);

  void runThreads(){
    thr1.start();
  }

  public static void main(String[] args) {
    new DriverClash().runThreads();
  }
}
```

The result shown below:
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
As seen in the example:

 * `protectedDriver` Will be created in Main thread
 *  We use Java `Runnable` to spin up a new process and a new `Thread` to run the process
 *  Both `Thread` will clash because the Main Thread does not have `protectedDriver` in it's memory.
 * `ThreadGuard.protect` will throw an exception.
 
#### Note:

This does not replace the need for using `ThreadLocal` to manage drivers when running parallel.

