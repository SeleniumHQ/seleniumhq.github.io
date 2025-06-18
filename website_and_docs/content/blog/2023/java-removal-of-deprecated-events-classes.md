---
title: Removal of AbstractEventListener + EventFiringWebDriver + WebDriverEventListener
date: 2023-12-08
tags: [java, events, webdriver]
categories: [general]
author: Oscar Devora ([@oscardevora](https://twitter.com/Yo_Oscarr))
description: >
  This blog will go over some examples on how to transition code that uses the aforementioned classes.
---

### Upgrading to WebDriverListener and EventFiringDecorator
Decorating the webdriver

```java
new EventFiringWebDriver(driver); // Old approach
new EventFiringDecorator().decorate(driver); // New approach
```
### Implementing method wrappers
One may find the need to have their own custom implementations be used for underlying decorated method calls. An example may be wanting to use your own findElement implementation to store metadata from web elements. One can go down a deep rabbit hole of decorators ( extending WebDriverDecorator and such ), so to keep things simple we will extend EventFiringDecorator since we want a single decorator to handle all our listener events.

```java
public class WebDriverWrapper implements WebDriver {
    private final WebDriver driver;
    WebDriverWrapper(WebDriver driver) {
        this.driver = driver;
    }
    // custom implementation goes here
    @Override
    public WebElement findElement(final By by) {
        // custom implementation goes here
        return driver.findElement(by);
    }
}

public class testDecorator extends EventFiringDecorator<WebDriver> {

    @Override
    public Object call(Decorated<?> target, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("findElement".equals(methodName)) {
            WebDriverWrapper newDriver = new WebDriverWrapper((WebDriver) target.getOriginal());
            return newDriver.findElement((By) args[0]);
        }
        return super.call(target, method, args);
    }
}
```
Some notes about the above example, we are only overriding the ‘general’ call method and checking the method name against every call made. 
Without going too deep decorators one can also override calls made by class instances to offer a more targeted approach. 
Just to expose some more functionality, let's modify our example. 
We can modify WebElement context since we might care about child elements and elements found by WebDriver ( WebDriver and WebElement both extend the SearchContext ).

```java
public class WebElementWrapper implements WebElement {
    private final WebElement element;
    WebElementWrapper(WebElement element) {
        this.element = element;
    }
    @Override
    public WebElement findElement(final By by) {
        // custom implementation goes here
        return element.findElement(by);
    }
}

public class WebElementDecorator extends EventFiringDecorator<WebDriver> {
    @Override
    public Decorated<WebElement> createDecorated(WebElement original) {
        return new DefaultDecorated<>(original, this) {
            @Override
            public Object call(Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if ("findElement".equals(methodName)) {
                    // custom implementation goes here
                    WebElementWrapper element = new WebElementWrapper(getOriginal());
                    return element.findElement((By) args[0]);
                }
                return super.call(method, args);
            }
        };
    }
}
```
In the sample above, we are still doing a very similar approach of overriding the call method but now we are also targeting WebElement instances.
### Registering Listeners

```java
new EventFiringWebDriver(driver).register(listener1).register(listener2); // Old approach
new EventFiringDecorator(listener1, listener2); // New approach
```
### Listening to Events
A quality of life change that is featured in WebDriverListener class is the use of ‘default’. 
In Java, the `default` keyword, when used in the context of an interface method, indicates that the method has a default implementation. 
If a class implementing the interface chooses not to override the method, it will inherit the default implementation. 
This change allows for splitting up listeners without needing to implement the unnecessary methods you don't need or care about.

#### Listening to specific events using before/after methods call
```java
// Old approach
public class AlertListener implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(final WebDriver driver) {
        // custom implementation goes here
    }
// implement every method in interface
}

// New approach
public class AlertListener implements WebDriverListener {
    @Override
    public void beforeAccept(Alert alert) {
        // custom implementation goes here
    }
// does not need to implement every method in interface
}
```
#### Listening to Generic Events
A change that was brought on is the ability to listen to generic events. 
One use case is logging information in a parallelized test suite.
Rather than create a listener and override every method to add a simple log statement, there is now a simpler alternative of overriding one method call. 
Below I override beforeAnyCall, but afterAnyCall exists as well which also has the results of the call to the decorated method.

```java
public class Listener implements WebDriverEventListener {
    private static final Logger LOGGER = Logger.getLogger(Listener.class.getName());

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        logger.debug("Thread: " + Thread.currentThread().getName() +
                " | Method Name: " + method.getName() +
                " | Method Args: " + Arrays.toString(args));
    }
}
```

There also was an addition listening to more specific generic events. 
Going back to the logging example, beforeAnyCall is a good method for debugging information or tracking the actions of a thread but might generate too much noise. 
In the same use case we might only care about WebDriver or WebElement calls. 
One can override instances of WebDriver and derived objects( WebElement, Alert, etc.) for before/after events.

```java
public class Listener implements WebDriverEventListener {
    private static final Logger LOGGER = Logger.getLogger(Listener.class.getName());

    @Override
    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
        logger.debug("Thread: " + Thread.currentThread().getName() +
                " | Method Name: " + method.getName() +
                " | Method Args: " + Arrays.toString(args));
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        logger.debug("Thread: " + Thread.currentThread().getName() +
                " | Method Name: " + method.getName() +
                " | Method Args: " + Arrays.toString(args));
    }
}
```

So that's some general examples on how to transition your code!
Happy testing!


