---
title: "Stealing focus from Firefox in Linux"
linkTitle: "Focus Stealing"
weight: 12
description: >
    How to work with Native Events in the Legacy Firefox extension.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Focus-Stealing-On-Linux)

This page describes an essential component of the native events implementation on Linux - focus maintaining. 
In order for native events to be processed in Firefox, it must always retain focus. 
In case the user decides to switch to another window (a thing which could be understood), 
Firefox must not know it lost focus.

### Solution overview

#### Basic idea

The basic idea is to get between the XLib (X-Windows client library) layer and the application. X-Windows notifies the application of events (user input, windows being destroyed, mouse movements) by asynchronous events. The events that indicate loss of focus [FocusOut](http://tronche.com/gui/x/xlib/events/input-focus/) are discarded. The idea is based on Jordan Sissel's implementation of a pre-loaded library that over-rides XNextEvent - see http://www.semicomplete.com/blog/geekery/xsendevent-xdotool-and-ld_preload.html.

#### Extension

This simple implementation works well as long as there's one browser window. When multiple windows are involved, several challenges arise:

* Even  though new windows may be opened, native events must continue to flow to the active window. However, most window managers will give focus to newly-opened windows.
* Window Switching: When wishing to switch to another window, the focus has to be moved. This requires cooperation between WebDriver's Firefox extension and this component.
* Closing windows: When a window is closed, focus must move to another window. By design, WebDriver does not guarantee anything if the active window is closed - until a new window is being switched to. In this situation, special care must be taken.

### Interaction with other components

The basic idea requires no interaction with other components of WebDriver. 
However, when multiple windows are involved - creating, switching or destroying, this component should be aware of it.
New window creation cannot be tracked - as it may happen as a side effect of many operations. 
Switching and closing can be tracked.

### Involved technologies

To understand this solution, one should be familiar with X-Windows and its events. 
Knowledge of the GDK event processing loop is also useful.

## Implementation Details

All of this describes the code in `firefox/src/cpp/linux-specific/x_ignore_nofocus.c`.

### The shared library

Hijacking the events is done by over-riding XNextEvent. 
A shared library containing a modified implementation of `XNextEvent` is loaded using `LD_PRELOAD`. 
The modified function opens `/usr/lib/libX11.so.6` and invokes the real function. 
Then the event that the real function returns (i.e. the real event) is inspected.

### Identifying events

Under the basic idea, `FocusOut` events will be simply discarded. However, window switching complicates matters.

#### Data structure

There's a global data structure that remembers the following information:
* The active window ID (if there is such one at the moment)
* The ID of a new window that's being created (again, if exists)
* If window switching is in progress.
* If window closing is in progress.
* Was the focus given to another window and should be stolen back to the active one?
* Was a `FocusIn` event already received by the active window?
* Did we set the currently active window as a result of a close operation?

#### Firefox starts up

`FocusIn` event arrives and the active window ID is 0. A new active window is set. Note that during the creation of the main window, another sub-window is created and a `FocusOut` event is sent to the active window. Fortunately, this `FocusOut` event indicates that the focus is going to move to a sub-window (identified by `NotifyInferior`) so it is allowed.

#### The user has switched to another window

This is indicated by a `FocusOut` event with a detail field that is neither `NotifyAncestor` nor `NotifyInferior`. This event is simply discarded and replaced with a `KeymapNotify` event, which is promptly discarded by GDK.

#### A new window is being created

This condition is identified by a `ReparentNotify` event. When this happens, the new\_window field will be set to the ID of the newly created window. Subsequent `FocusOut` events will be allowed - during the new window creation events will flow as usual (`FocusOut` event from the active window, `FocusIn` event to the new window, `FocusOut` to the new window and `FocusIn` to a sub-window of the new window). After the sub-window of the new window receives `FocusIn`, a call to `XSetInputFocus` will be issued to return the focus to the active window.

#### A window switch occurs

During a window switch events will flow as normal. A window switch is considered done when the sub-window of a window receives the `FocusIn` event. A window switch starts by identifying the file `/tmp/switch_window_started`. In this file, a `switch:` string following a window ID is written (the ID is just for debugging purpose). This will change the active window ID to 0 and the state to "during switch". During a switch (or when there's no active window) no events are discarded.

#### A window is being closed

Very similar to window switching (also identified by reading the file). However, it is indicated that the window is being closed - in case it was closed, no focus stealing will take place. In addition, the `DestroyNotify` event is being identified to find out when the active window is being closed (explicitly by the user or implicitly by some other operation that is not an explicit call to close). In this case, the active window  ID will be set to 0 as well.

## Important Links
* Jordan Sissel's original [XSendEvent hack](http://www.semicomplete.com/blog/geekery/xsendevent-xdotool-and-ld_preload.html)
* [XLib events](http://tronche.com/gui/x/xlib/events/structures.html) and the [XLib programming manual](http://www.sbin.org/doc/Xlib/)
* [The X programming manual / specification](http://www.x.org/docs/X11/xlib.pdf)
