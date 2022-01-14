---
title: "Internet Explorer Driver Internals"
linkTitle: "Internals"
weight: 2
needsTranslation: true
description: >
    More detailed information on the IE Driver.
---

## Client Code Into the Driver

We use the W3C WebDriver protocol to communicate with a local instance of an HTTP server. This greatly simplifies the implementation of the language-specific code, and minimzes the number of entry points into the C++ DLL that must be called using a native-code interop technology such as [JNA](https://jna.dev.java.net/), [ctypes](http://docs.python.org/library/ctypes.html), [pinvoke](http://msdn.microsoft.com/en-us/library/aa446536.aspx) or [DL](http://www.ruby-doc.org/stdlib/libdoc/dl/rdoc/index.html).

### Memory Management

The IE driver utilizes the Active Template Library (ATL) to take advantage of its implementation of smart pointers to COM objects. This makes reference counting and cleanup of COM objects much easier.

## Why Do We Require Protected Mode Settings Changes?

IE 7 on Windows Vista introduced the concept of Protected Mode, which allows for some measure of protection to the underlying Windows OS when browsing. The problem is that when you manipulate an instance of IE via COM, and you navigate to a page that would cause a transition into or out of Protected Mode, IE requires that another browser session be created. This will orphan the COM object of the previous session, not allowing you to control it any longer.

In IE 7, this will usually manifest itself as a new top-level browser window; in IE 8, a new IExplore.exe process will be created, but it will usually (not always!) seamlessly attach it to the existing IE top-level frame window. Any browser automation framework that drives IE externally (as opposed to using a WebBrowser control) will run into these problems.

In order to work around that problem, we dictate that to work with IE, all zones must have the same Protected Mode setting. As long as it's on for all zones, or off for all zones, we can prevent the transistions to different Protected Mode zones that would invalidate our browser object. It also allows users to continue to run with UAC turned on, and to run securely in the browser if they set Protected Mode "on" for all zones.

In earlier releases of the IE driver, if the user's Protected Mode settings were not correctly set, we would launch IE, and the process would simply hang until the HTTP request timed out. This was suboptimal, as it gave no indication what needed to be set. Erring on the side of caution, we do not modify the user's Protected Mode settings. Current versions, however check that the Protected Mode settings are properly set, and will return an error response if they are not.

## Keyboard and Mouse Input

Key files: [interactions.cpp](https://github.com/SeleniumHQ/selenium/blob/master/cpp/webdriver-interactions/interactions.cpp)

There are two ways that we could simulate keyboard and mouse input. The first way, which is used in parts of webdriver, is to synthesize events on the DOM. This has a number of drawbacks, since each browser (and version of a browser) has its own unique quirks; to model each of these is a demanding task, and impossible to get completely right (for example, it's hard to tell what `window.selection` should be and this is a read-only property on some browsers) The alternative approach is to synthesize keyboard and mouse input at the OS level, ideally without stealing focus from the user (who tends to be doing other things on their computer as long-running webdriver tests run)

The code for doing this is in [interactions.cpp](https://github.com/SeleniumHQ/selenium/blob/master/cpp/webdriver-interactions/interactions.cpp) The key thing to note here is that we use PostMessages to push window events on to the message queue of the IE instance. Typing, in particular, is interesting: we only send the "keydown" and "keyup" messages. The "keypress" event is created if necessary by IE's internal event processing. Because the key press event is not always generated (for example, not every character is printable, and if the default event bubbling is cancelled, listeners don't see the key press event) we send a "probe" event in after the key down. Once we see that this has been processed, we know that the key press event is on the stack of events to be processed, and that it is safe to send the key up event. If this was not done, it is possible for events to fire in the wrong order, which is definitely sub-optimal.

# Working On the InternetExplorerDriver

Currently, there are tests that will run for the InternetExplorerDriver in all languages (Java, C#, Python, and Ruby), so you should be able to test your changes to the native code no matter what language you're comfortable working in from the client side. For working on the C++ code, you'll need Visual Studio 2010 Professional or higher. Unfortunately, the C++ code of the driver uses ATL to ease the pain of working with COM objects, and ATL is not supplied with Visual C++ 2010 Express Edition.  If you're using Eclipse, the process for making and testing modifications is:

1. Edit the C++ code in VS.
1. Build the code to ensure that it compiles
1. Do a complete rebuild when you are ready to run a test. This will cause the created DLL to be copied to the right place to allow its use in Eclipse
1. Load Eclipse (or some other IDE, such as Idea)
1. Edit the `SingleTestSuite` so that it is `usingDriver(IE)`
1. Create a JUnit run configuration that uses the "webdriver-internet-explorer" project. If you don't do this, the test won't work at all, and there will be a somewhat cryptic error message on the console.

Once the basic setup is done, you can start working on the code pretty quickly. You can attach to the process you execute your code from using Visual Studio (from the Debug menu, select Attach to Process...).
