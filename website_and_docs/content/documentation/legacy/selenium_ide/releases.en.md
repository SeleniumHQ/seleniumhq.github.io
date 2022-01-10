---
title: "Legacy Selenium IDE Release Notes"
linkTitle: "Releases"
weight: 4
description: >
    Selenium IDE was the original Firefox extension for Record and Playback.
    Version 2.x was updated to support WebDriver.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/SeIDE-Release-Notes)

## 2.9.1 - to be released

* Fix - Fixes https://github.com/SeleniumHQ/selenium/issues/396
* Fix - Changed Google code links to GitHub.
* Enh - Merged official language plugins into the main xpi eliminating the need for multi-xpi with the main xpi and multiple language plugin xpis.
* Fix - Fixes https://github.com/SeleniumHQ/selenium/issues/570

## 2.9.0

* Enh - Schedule tests for automatic playback at a certain time or periodic intervals. (http://blog.reallysimplethoughts.com/2015/03/09/selenium-ide-scheduler-has-arrived-part-1/)
* Enh - Allow submission of diagnostic information via a gist.
* Enh - Improved health logging, including alerts normally hidden.

## 2.8.0

* New - Added visual assist option to help users requiring stronger constrast in colors, turned off by default. Turn it on from the Options dialog. - Issue 7696 (on Google Code)
* New - Health Service to catch unhandled exceptions, statistics, metrics and diagnostics
* Enh - Added Search Issues menu item in Help menu to make it easier to search all issues so that we do not get so many duplicate reports of the same issue
* Fix - Fixed broken autocomplete - issue 7928 (on Google Code)
* Fix - Fixed cancelling of select button when page is reloaded - issue 7793 (on Google Code)
* Fix - Adding select button to the sidebar and reduced button size - issue 7815 (on Google Code)

## 2.7.0

* Fix - Fixed switching between tabs in the bottom info panel in FF32 - issue 7824 (on Google Code)
* Fix - Fixes for https://bugzilla.mozilla.org/show_bug.cgi?id=1016305
* Enh - Let comments (and commands) span the full width of the commands table
* Enh - Show the result of the test case in the log after it has been played
* Enh - Group items in the Action menu by function
* Enh - Collect more statistics about test case and suite including running time for reporting purposes
* Enh - Improved listboxes supporting drag and drop reordering
* Enh - Provide common utility function for plugin authors to deal with files
* Enh - Allow pressing tab in the command text box to accept the current autocomplete and move to the target text box
* Enh - Select an autocomplete match when typing in the command text box to speed up manual entry of commands
* Enh - Make promises implementation available via deferred.js for plugin developers
* Enh - Make simple http functions available for plugin developers
* Enh - Easier to use confirmations for internal use and for plugins
* Fix - Disable autocomplete when editing comments
* Fix - Fixed error TypeError: command.isRollup is not a function
* Fix - Fixed TypeError: debugContext.currentCommand is undefined
* Fix - Fixed TypeError: this.treebox is undefined treeView.js
* Fix - Various errors when selecting a comment (usually hidden from the user)
* Fix - Incorrect doctype in overlay
* Fix - Adding Selenium IDE item under Settings->Developer menu - issue 7268 (on Google Code)
* Fix - Ignore Firefox developer tools while recording

## 2.6.0

* Fix - Fixed broken autocompletion in FF31+ - issue 7645 (on Google Code)
* Fix - Fixed options validation on options reset - issue 1050 (on Google Code)
* Fix - Fixed C# code formatting for select elements

## 2.5.0

* Enh - Select an element for a command by clicking on the element in the browser window (http://blog.reallysimplethoughts.com/2014/01/05/manually-adding-and-updating-element-locators-the-easy-way/)
* Enh - Start playing a test suite from any test case (Using right click menu) - issue 1987 (on Google Code)
* Enh - Add a new test case using a keyboard shortcut (ctrl-N or cmd+N)
* Fix - Fixed delete test case through right click menu was sometimes disabled - issue 5003 (on Google Code)
* Fix - Fixed Selenium IDE icon is sometimes not visible - issue 5712 (on Google Code)
* Fix - Fixed selectWindow using a variable - issue 3270 (on Google Code)
* Some minor changes

## 2.4.0

* Enh - Base URL history, recent test cases and recent test suites can be cleared - issue 6135 (on Google Code)
* Enh - Special key now have shorter names (http://blog.reallysimplethoughts.com/2013/09/25/using-special-keys-in-selenium-ide-part-2/)
* Enh - Support for user extensions in Webdriver playback - issue 5675 (on Google Code)
* Fix - The recording of entering text in fields uses type instead of sendKeys.
* Enh - When developer tools are active, the last open test case or suite is automatically opened
* Fix - Fixed is`*` commands in Webdriver playback in Selenium IDE - issue 6118 (on Google Code)
* Enh - Adding ability to show commands as deprecated in Selenium IDE and smartness to show the correct alternative command
* Enh - Deprecating Selenium IDE commands `*`TextPresent, typeKeys, keyUp, keyDown and keyPress
* Enh - Import json library in exported Ruby Webdriver tests
* Enh - Adding support for waitFor`*` and waitForNot`*` commands in Webdriver playback - issue 5913 (on Google Code)

## 2.3.0

* New - Added support for HTML5 input fields recording - issue 3765 (on Google Code)
* New - Recording for sendKeys command
* Enh - Removal of deprecated `*`TextPresent commands from right click menu
* Fix - Dead object error in recording IDE tests - issue 4761 (on Google Code)
* Fix - Fixed could not continue in recording - issue 5820 (on Google Code)
* Enh - UTF-8 encoded user-extensions.js support - issue 1646 (on Google Code)
* New special keys support for sendKeys in Selenium IDE and webdriver playback - issue #6052 (on Google Code)
* New - Special keys support to sendKeys in all official formatters - issue 6053 (on Google Code) (http://blog.reallysimplethoughts.com/2013/09/25/using-special-keys-in-selenium-ide-part-1/)
* Enh - Plugin api enhancement for specifying formatter type + documentaton comments
* Fix - Invalid XPath error in Firefox 23 - issue 6055 (on Google Code)
* New - Added support for Firefox 23

## 2.2.0

* Fix - keyUp, keyDown, keyPress, typeKeys fixed on Firefox 22 - issue 5883 (on Google Code), issue 5884 (on Google Code)

## 2.1.0

* Enh - Plugin system changed (http://blog.reallysimplethoughts.com/2013/07/07/changes-to-selenium-ide-plugin-system/)
* New - Added support for Firefox 22 + 23 beta
* Fix - Click fixed for Firefox 22 - issue 5841 (on Google Code)

## 2.0.0

* New - WebDriver playback support (http://blog.reallysimplethoughts.com/2013/02/18/webdriver-playback-in-selenium-ide-is-here/)
* New - Added support for Firefox 19 & 20
* New - Selenium IDE icon on toolbar is added on first install

## 1.10.0

* New - Added support for Firefox 16 & 17
* New - Implemented formatting for alert handling commands
* Bug - Fixed options for Java 4 WebDriver formatter
* Bug - Processing locators before use in getCssCount and getXpathCount. Fixes  issue 4784 (on Google Code)

## 1.9.1

* New - Added support for Firefox 15
* New - Added support for assertTextPresent, verifyTextPresent, waitForTextPresent, assertTextNotPresent, verifyTextNotPresent, waitForTextNotPresent commands to WebDriver formatters. (http://blog.reallysimplethoughts.com/2012/08/26/selenium-ide-webdriver-formatters-updated-to-support-textpresent-commands/)
* New - Added the target and value parameters in comments when the WebDriver formatters do not support the command

## 1.9.0

* New - Added Selenese command sendKeys (http://blog.reallysimplethoughts.com/2012/07/19/new-selenese-command-sendkeys/)
* New - Better naming of formatters
* New - Added support for Firefox 14

## 1.8.1

* New - Added support for Firefox 13

## 1.8.0

* New - Added support for Firefox 12

## 1.7.2

* Bug - Fixed regression with typing into file input fields - issue 3549 (on Google Code)

## 1.7.1

* Bug - Fixed regression with stored variables - issue 3520 (on Google Code)

## 1.7.0

* New - Added additional useful menu items to the help menu
* New - Added support for Firefox 11
* Bug - Stored variables can safely contain consecutive dollar signs - issue 834 (on Google Code)
* Bug - Don't trim whitespace when decoding HTML testcases - issue 755 (on Google Code)
* New - Formatter menu items are now context sensitive - issue 3327 (on Google Code) and issue 3385 (on Google Code)
* Bug - Fixed Ruby WebDriver test suite export - issue 3243 (on Google Code)
* Bug - File extensions being added to all file pickers - issue 3336 (on Google Code)
* Bug - Record interactions with elements with an id of 'id' - issue 3273 (on Google Code)

## 1.6.0

* New - Added support for Firefox 10
* New - Added keyboard shortcuts to launch Selenium IDE - issue 3028 (on Google Code)
* Bug - Added break command to autocomplete list - issue 3046 (on Google Code)
* Bug - Incorrect tooltip displayed in sidebar - issue 3098 (on Google Code)
* Bug - Improved XPath locator recording when there are multiple matches - issue 3056 (on Google Code)
* Bug - Locators can now be reordered on Mac - issue 3267 (on Google Code)

## 1.5.0

* New - Added support for Firefox 9
* Bug - Changes to user extensions weren't being updated in Firefox 8 - issue 2801 (on Google Code)
* Bug - Security error was thrown when trying to type into file (upload) input fields in Firefox 8 - issue 2826 (on Google Code)
* Bug - Improved French locale - issue 1912 (on Google Code)
* Bug - break command was failing - issue 725 (on Google Code)
* Bug - source view is now fixed width (monospace) - issue 522 (on Google Code)
* New - Implemented 'select' formatting for WebDriver bindings (Java, C#, Python, Ruby)
* Bug - Fixed compile-time and run-time errors in the code formatted for WebDriverBackedSelenium
* Bug - Fixed 'baseUrl' and 'get' formatting errors in various formatters to handle relative and absolute URLs

## 1.4.1

* Bug - Apparently I shipped without switching all the version numbers correctly. (Adam)

## 1.4.0
* New - Firefox 8 support (again, just a version max version bump)


## 1.3.0

Was going to be just a quick release to get

* New - Firefox 7 support (again, just a version max version bump)

in, but then I got busy and didn't push it when I had planned and so now

* New - Order of locators can be controlled through a panel in options.

has leaked in. Most people will want to just leave this the way it is by default. This is brand-spanking-new and allows you to do visually what you could before using a somewhat arcane bit of JS in an extension.

## 1.2.0

Just a quick release primarily for

* New - Firefox 6 support (which really was just changing the max version number)

But we also snuck in

* Bug - Recorded CSS locator was not W3C clean wrt attributes
* Bug - Deleting of cookies works properly if the cookie name is escaped (such as will ASP sites)
* Bug - If the cookie value has an = in it, the whole cookie is now returned instead of just up to the =

You will also notice that the bundle now only has formatters for the officially supported languages of the project (Java, C#, Python, Ruby). If anyone from the Perl, Groovy or PHP camps wants to take on ownership of those formats we'll happily help you out.

## 1.1.0

Hey! Look at that! A slightly more significant version bump! Any why is that? Well...

* New - WebDriver exports for Ruby, Python, C# and Java

Which are the four supported languages of the Selenium project. This also means that Se-IDE is officially deprecating inclusion of the Groovy, Perl and PHP format plugins in the main release bundle. It would be outstanding if the community around those languages picks up their development and maintenance. Read more about the WebDriver exporters on [Samit's blog](http://blog.reallysimplethoughts.com/2011/07/08/selenium-ide-and-selenium-2-webdriver/).

Of course, format switching is still in Experimental purgatory for at least this release. Losing people's scripts because of bugs is not acceptable and we're working on it. To 'goal' is to have them back for the next release.

Also included in this release are

* New - setIndent(n) is now available to formats for greater control over formatting of export formats
* Bug - There was a performance regression in deep in some shared code that has been addressed.
* New - Rather than recording 'foo' for an element which and an id of 'foo' it is captured as 'id=foo' to be very specific as to which element would be interacted with
* New - Same with 'name'
* New - Popups (alerts, confirms, prompts) and new windows work again

## 1.0.12

This is a minor release with nothing too huge included. But because the last one didn't get pushed to the world, it is important to make a note of a big change introduced in 1.0.11.

We have marked the changing of formats as _Experimental_ due to a couple lose-all-your-data bugs. As a result it is disabled in the toolbar by default. To enable it, click the checkbox in the Options menu. And because we **really** don't want you to lose your data, when you switch formats you will get a big warning box. This too can be disabled in the Options menu. But if you do both of these things and your script gets sent to the abyss, you have been warned. :)

Changes in this release include the following:

* New - Firefox 5 support
* New - When upgrading Se-IDE, the release notes (these) are shown on first start
* Bug - some Java format changes
* Bug - some PHP format changes
* Bug - the 'Find' button works again
* Bug - generated CSS is standards compliant
* New - dropped support for FF 3.5 or older

## 1.0.11

It has been half a year since our last release of 1.0.10 and we have put a lot of effort to bring you this release. The summary of the contributions to this release is as follows:-

| 73% (22) | Samit Badle |
|:---------|:------------|
| 16%( 5)  | Adam Goucher |
| 6% (2)   | Dave Hunt   |
| 3% (1)   | Santiago Suarez Ordoñez |
| 3% (1)   | Simon Stewart |

Here is the list of changes excluding some minor fixes and code refactoring.

### Main Features:
* Firefox 4 support (Issue 1470 (on Google Code), Simon Stewart and Samit Badle)
* New CSS locator builder! Selenium IDE will now create locators using CSS when recording (Santiago Suarez Ordoñez)
* Added more power to the plugin developers through the new Util command builders support (Issue 442 (on Google Code), Samit Badle)
* New command getCssCount (Adam Goucher)

### Usability Improvements:
* Selenium IDE is now available from the Web developer menu in Firefox 4 (Issue 1467 (on Google Code), Samit Badle)
* Camel Case search in command text box has been improved allowing you to type vTP for verifyTextPresent command (Samit Badle with Dave Hunt)
* Most actions in Selenium IDE are now accessible through the new Actions menu (Issue 1266 (on Google Code), Samit Badle and Dave Hunt)
* Removed help menu items related to Firefox from Selenium IDE help menu (Issue 1704 (on Google Code), Samit Badle)
* Less prompting when saving test suite (Issue 967 (on Google Code), Samit Badle)
* A method to Reset IDE Window is now available through the Options menu for people having trouble when switching from multiple monitors (Issue 1249 (on Google Code), Samit Badle)
* Show the name of the test case in save dialog (Issue 984 (on Google Code), Samit Badle)
* The preferences for the current format will be automatically shown in options dialog (Samit Badle)
* The plugins pane in the Options dialog now has a splitter (Samit Badle)
* Default Timeout Value field in the Options dialog now mentions a unit (Issue 896 (on Google Code), Adam Goucher)
* Introduced experimental features option to hide some unstable features (Samit Badle)

### Bug Fixes:
* Format changing is now marked as experimental due to possible issues, you can turn it on from the options dialog (Samit Badle)
* Fixed the header issue on saving test case in another format (Issue 1164 (on Google Code), Samit Badle)
* Improved alert on changing the format (Issue 1244 (on Google Code), Samit Badle)
* Find button is back on Macs and uses a new way to highlight (Issue 1052 (on Google Code), Samit Badle)
* Recording is possible in the middle of a script again (Issue 968 (on Google Code), Samit Badle)
* Fixed the annoying skip over one command when recording in the middle of the script (Issue 745 (on Google Code), Samit Badle)
* While recording, "clickAndWait" command becomes "click" is now fixed (Issue 419 (on Google Code), Samit Badle)
* Selenium IDE bottom pane folding now works correctly (Issue 614 (on Google Code), Samit Badle)
* Changed the ID of Selenium IDE menu from generic name to avoid clashes with other addons. (Issue 969 (on Google Code), Samit Badle)

### Improvements/Fixes Related to Formatters:
* Fixed support for stored variables in PHP formatter (Issue 970 (on Google Code), Samit Badle)
* Allow formatters to customise how set`*` is handled (Adam Goucher)
* Some bug fixes in PHP formatter (Issue 1281 (on Google Code), Adam Goucher)
* Number type fix (Jeremy Herault)
* New Java formatter: Webdriver backed Junit 4 formatter
* New PHP formatter: Testing selenium formatter (Adam Goucher)

### Known Issues:
* Issue 1728 (on Google Code) - Firefox 4 eliminated support for the highlight. So the Find button has stopped working under Firefox 4 on Windows.
* Issue 1729 (on Google Code) - The Plugin pane in the Options dialog is not shownig any text in Firefox 4 on Windows 7.
* Issues have been reported in Selenium IDE on Ubuntu 11, which are not related to Selenium IDE. See comments on issue 1642 (on Google Code).


## 1.0.10

Another packaging problem broke the various things that used getText(). Which of course is one of the most commonly used bits of the API.

* BUG - properly including se-core atoms

As a result, we've started to rebuild the test suite for things. It's going to take awhile to get the coverage we're hoping for, but it'll be worth it if we can go at least 2 days after a release before becoming embarrassed.

Upgrade Notes:
* Due to the atoms being included properly, some of the behaviour around accessing boolean attributes has changed. See http://seleniumhq.wordpress.com/2010/12/09/atoms-have-come-to-selenium-ide/ for details.

## 1.0.9

What started out as a pretty major change in terms of packaging ended up including two significant bug fixes as well. Hopefully we avoid that sort of thing with the release. Not that I don't expect it. :)

* BUG - Sizzle CSS library not included
* BUG - Recording works with FF 4.0b7

What 1.0.9 was supposed to only have was...

* NEW - Formatters are **all** plugins. This effectively separates the development of an individual format from the development of the editor. Now, this means that when you install things for the first time you get a tonne of addons. That is ok. Don't panic. Oh, and it also means if you don't want them you have the option to. Not only does this mean fixes to formats get distributed sooner (PHP, I'm looking at you) but 3rd parties will be able to make better packaging choices by having the editor plus their formatters.

Other stuff

* BUG - the JUnit 4 formatter doesn't try to use a string as the port number
* BUG - the window when creating new formats properly closes now
* BUG - removed the 'find' button if on OSX since it doesn't do anything on this platform (its a FF bug)
* BUG - some hard coded strings have been internationalized
* NEW - autocomplete has been enhanced somewhat - see http://code.google.com/p/selenium/issues/detail?id=992
* BUG - when switching build systems, the icons for menus and such got left out of the package
* BUG - commands are trimmed of whitespace before executing which was sometimes a source of great confusion
* BUG - now preserves whitespace when displaying diffs in the log

## 1.0.8

This release is primarily to get FF4 support out into the wild since it is getting to the advanced beta phase, but there is also a fair bit of other bug fixes in there as well. About 75% of the fixes in the release are directly the work of Samit Badle and the vast remainder by Jérémy Hérault.

* BUG - There was an annoying bug where 'clickAndWait' would be saved as click, but has been fixed. see http://code.google.com/p/selenium/issues/detail?id=419
* NEW -This could arguably be considered a bug fix, but if you changed format from HTML to something else then made an edit and switched back again to HTML your script contents would be lost. At its heart, the HTML -> something conversion is one way and so there is now a warning about possibly losing your code. The warning only happens the first time though so you can still shoot yourself in the foot; its just harder
* BUG - element locator works for table rows. see http://code.google.com/p/selenium/issues/detail?id=485
* BUG - the default timeout setting of se-ide is now actually used. see http://code.google.com/p/selenium/issues/detail?id=552
* NEW - the 'run in the selenium testrunner' option has been removed. The supported methods in se-ide are the play single, play suite and if you need more there is always se-rc with a language binding or -htmlSuite
* BUG - the base url wouldn't change on occasion, much to the frustration of many
* NEW - a JUnit 4 formatter was added
* BUG - the RSpec formatter had some additional tweaks
* BUG - test suite html can now have tests from different folders
* BUG - test suite saving triggers got a bit of attention so add/delete/modify is a little more robust
* NEW - if you resize your se-ide and/or move it around your screen, the size and position are saved between sessions
* BUG - the logic around when to prompt for saving wasn't really that nice, but its been fixed
* NEW - uses 'browser atoms' like the rest of Selenium
* NEW - CSS locator execution is handled through Sizzle
* NEW - can now add multiple test cases to a suite at once
* NEW - addition to the se-ide plugin api to add se-ide extensions to manipulate how recording is done - http://reallysimplethings.wordpress.com/2010/10/11/the-selenium-ide-1-x-plugin-api-part-12-adding-locator-builders/
* NEW - the case of the missing log messages is now solved
* NEW - Firefox 4 support

## 1.0.7

Only a couple of things of note in this release to end-users which is somewhat silly since it is a month overdue, but that was due to some build changes that took a bit of work to get the kinks worked out. Should be ok now though.

* NEW - you can now drag-and-drop command around instead of the cut-insert-paste dance that you used to have to do (Jérémy Hérault)
* NEW - same thing with tests in the test suite panel (Jérémy Hérault)
* NEW - an new optional parameter when registering you se-ide plugin to allow for command exporting. see http://adam.goucher.ca/?p=1456 for details (Adam Goucher)
* NEW - Swedish locale sv-SE now has translations (Olle Jonsson)
* BUG - Some people were reporting an annoying popup when starting se-ide without any plugins installed (Adam oucher)

## 1.0.6

The big thing in this release is that the scary log message that was showing up on 'open' is fixed. The other big things are:

* BUG - The scary log message that was happening when you used 'open' has had its underlying cause fixed (Adam Goucher, Jérémy Hérault)
* BUG - fixed a build issue with FF 3.6 and type-ahead for commands (Jérémy Hérault)
* BUG - fixed some PHP export issues - see http://jira.openqa.org/browse/SIDE-346 and http://jira.openqa.org/browse/SIDE-183 (Adam Goucher)
* BUG - there was a packaging issue around user-extensions (Adam Goucher)
* BUG - ide won't put 'name=' as the Target when recording a selectWindow (David Burns)
* BUG - to avoid confusion, when viewing formatter source, if it is read-only the button says 'ok' and if it is editable then it is 'save' (Jérémy Hérault)
* NEW - you can now set a preference on whether you want record to be on or off when you start ide (Adam Goucher)
* NEW - se-ide plugin information is read from the plugin's install.rdf (most people won't care about this, but its pretty cool from a geek perspective)


## 1.0.5

One thing that does not really fit the BUG or NEW label is that the code for Se-IDE is now in the main repo rather than tucked away in a somewhat hidden location.

* BUG - user formats were not appearing in the list (Adam Goucher)
* BUG - constrained how iframes were loaded; which is why AMO was unhappy (Adam Goucher)
* BUG - a whole bunch of tweaks to the existing formats (Dave Hunt)
* BUG - a bunch of French translation fixes / additions (Jérémy Hérault)
* BUG - the reload user extensions button only shows up if you have the developer tool checkbox checked (Jérémy Hérault)
* BUG - labelling access keys on test runner (Olle Jonsson)
* BUG - cleaned up a bunch of references from OpenQA to SeleniumHQ (Olle Jonsson)
* BUG - had an = instead of == (Olle Jonsson)
* BUG - adding a bunch of ;'s to make jslint shut up (Olle Jonsson)
* BUG - getting rid of the 'setting something that only has a getter' message in Firefox 3.6 (Dan Fabulich)
* NEW - self hosting of updates to avoid delays at AMO (Adam Goucher)
* NEW - the version of se-ide is now in the title bar (Adam Goucher)
* NEW - added some Se-IDE specific icons here and there (Adam Goucher, Dave Hunt)
* NEW - preferences can now be Bool's as well (Adam Goucher)
* NEW - added addPlugin(id) to the plugin API (Adam Goucher)
* NEW - added a new panel to the Options screen around plugins. It doesn't do much now other than list the plugins that registered themselves through addPlugin, but should do more for 1.0.6 (Adam Goucher)

## 1.0.4

Selenium IDE 1.0.4 marks a resurgence in the project with releases planned for the middle of each month. Here are the changes that have happened between versions 1.0.2 and 1.0.4 of Selenium IDE. (Don't ask what happened to version 1.0.3)

* BUG - Supported Firefox version increased to include the 3.6 series (Santiago Suarez Ordoñez)
* BUG - Removed the Ruby formatter that was flagged as 'deprecated' (Adam Goucher)
* NEW - Ruby formatter updated to use the selenium-client gem ( http://selenium-client.rubyforge.org/ ) (Adam Goucher)
* NEW - Ability to add custom user-extensions to extend the Selenium API through plugins to Selenium IDE (Adam Goucher)
* NEW - Ability to add custom formatters to extend which languages are available to users through plugins to Selenium IDE (Adam Goucher)
* NEW - Can now load changes to user extensions without having to restart Selenium IDE (Jérémy Hérault)
* NEW - RSpec formatter

### Acknowledgements

Version 1.0.4 would not have happened without the following assistance
* Sauce Labs' sponsoring of Adam Goucher to work on it
* Jérémy Hérault and the SERLI team for their Helium plugin (which was the proof an API could / should be developed for Se-IDE)
* Dave Hunt for his feedback on pre-release versions

For issues with this release or features you would like to see in future releases, please log them in the Google Code Issue tracker (https://github.com/SeleniumHQ/selenium/issues) using the _ide_ label so they don't get lost.

-adam
