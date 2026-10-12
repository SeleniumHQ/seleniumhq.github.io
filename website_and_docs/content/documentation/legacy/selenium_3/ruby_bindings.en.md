---
title: "Selenium 3 Ruby Bindings"
linkTitle: "Ruby Bindings"
weight: 12
description: >
    Usage examples for the Ruby bindings in the Selenium 3 era.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Ruby-Bindings) \
For current information, read the [WebDriver documentation]({{< ref "/documentation/webdriver" >}}).

## Introduction

The Ruby bindings for Selenium/WebDriver are available as the [selenium-webdriver](http://rubygems.org/gems/selenium-webdriver) gem. The web page explains how to install the selenium-webdriver gem. If you're looking for a slightly higher level API built on the same technology, you may want to check out [watir](http://watir.github.io/) or [capybara](https://github.com/teamcapybara/capybara).

The bindings support Ruby 2.1 through 2.4.

  * [API docs](http://seleniumhq.github.io/selenium/docs/api/rb/index.html)
  * [Changelog](https://github.com/SeleniumHQ/selenium/blob/master/rb/CHANGES)


## API Example

```ruby
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
driver.navigate.to "http://google.com"

element = driver.find_element(name: 'q')
element.send_keys "Hello WebDriver!"
element.submit

puts driver.title

driver.quit
```

Driver examples:

```ruby
# execute arbitrary javascript
puts driver.execute_script("return window.location.pathname")

# pass elements between Ruby and JavaScript
element = driver.execute_script("return document.body")
driver.execute_script("return arguments[0].tagName", element) #=> "BODY"

# wait for a specific element to show up
wait = Selenium::WebDriver::Wait.new(timeout: 10) # seconds
wait.until { driver.find_element(id: "foo") }

# switch to a frame
driver.switch_to.frame "some-frame" # id
driver.switch_to.frame driver.find_element(id: 'some-frame') # frame element

# switch back to the main document
driver.switch_to.default_content

# repositioning and resizing browser window:
driver.manage.window.move_to(300, 400)
driver.manage.window.resize_to(500, 800)
driver.manage.window.maximize
```

Element examples:

```ruby
# get an attribute
class_name = element.attribute("class")

# is the element visible on the page?
element.displayed?

# click the element
element.click

# get the element location
element.location

# scroll the element into view, then return its location
element.location_once_scrolled_into_view

# get the width and height of an element
element.size

# press space on an element - see Selenium::WebDriver::Keys for possible values
element.send_keys :space

# get the text of an element
element.text
```

Advanced user interactions (see [ActionBuilder](http://seleniumhq.github.io/selenium/docs/api/rb/Selenium/WebDriver/ActionBuilder.html)):

```ruby
driver.action.key_down(:shift).
              click(element).
              double_click(second_element).
              key_up(:shift).
              drag_and_drop(element, third_element).
              perform
```


### IE

Make sure that _Internet Options_ → _Security_ has the same _Protected Mode_ setting (on or off, it doesn't matter as long as it is the same value) for all zones.

### Chrome

#### Command line switches

For a list of switches, see [this list](http://peter.sh/experiments/chromium-command-line-switches/).

```ruby
options = Selenium::WebDriver::Chrome::Options.new
options.add_argument('--ignore-certificate-errors')
options.add_argument('--disable-popup-blocking')
options.add_argument('--disable-translate')
driver = Selenium::WebDriver.for :chrome, options: options
```

#### Tweaking profile preferences

```ruby
prefs = {
  prompt_for_download: false,
  default_directory: "/path/to/dir"
}
options = Selenium::WebDriver::Chrome::Options.new
options.add_preference(:download, prefs)
driver = Selenium::WebDriver.for :chrome, options: options
```

See [ChromeDriver documentation](https://sites.google.com/a/chromium.org/chromedriver/home).


### Remote

The RemoteWebDriver makes it easy to control a browser running on another machine. Download the Selenium
Standalone Server (from [Downloads](http://www.seleniumhq.org/download/)) and launch:

`java -jar selenium-server-standalone.jar`

Then connect to it from Ruby

```ruby
driver = Selenium::WebDriver.for :remote
```

By default, this connects to the server running on localhost:4444 and opens Chrome. To connect to another machine, use the `:url` option:

```ruby
driver = Selenium::WebDriver.for :remote, url: "http://myserver:4444/wd/hub"
```

To launch another browser with the default capabilities, use the :desired_capabilities option:

```ruby
driver = Selenium::WebDriver.for :remote, desired_capabilities: :firefox
```

You can also pass an instance of `Selenium::WebDriver::Remote::Capabilities`, e.g.:

```ruby
caps = Selenium::WebDriver::Remote::Capabilities.internet_explorer(native_events: false)
driver = Selenium::WebDriver.for :remote, desired_capabilities: caps
```

You can change arbitrary capabilities:

```ruby
caps = Selenium::WebDriver::Remote::Capabilities.internet_explorer
caps['enablePersistentHover'] = false

driver = Selenium::WebDriver.for :remote, desired_capabilities: caps
```

You may want to set the proxy settings of the remote browser (this currently only works for Firefox):

```ruby
caps = Selenium::WebDriver::Remote::Capabilities.firefox(proxy: Selenium::WebDriver::Proxy.new(http: "myproxyaddress:8080"))
driver = Selenium::WebDriver.for :remote, desired_capabilities: caps
```

Or if you have a proxy in front of the remote server:

```ruby
client = Selenium::WebDriver::Remote::Http::Default.new
client.proxy = Selenium::Proxy.new(http: "proxy.org:8080")

driver = Selenium::WebDriver.for :remote, http_client: client
```

See [Proxy documentation](http://seleniumhq.github.io/selenium/docs/api/rb/Selenium/WebDriver/Proxy.html) for more options.

For the remote Firefox driver you can configure the profile, see the section [Tweaking Firefox preferences](#tweaking-firefox-preferences).


### Firefox

Ruby currently includes support for Legacy FirefoxDriver (used in Firefox versions < 48), and the new [geckodriver](https://github.com/mozilla/geckodriver#readme). As of Selenium 3.x, Geckodriver is the default implementation. If you want to use the Legacy Driver with an older version of Firefox you can do:

```ruby
capabilities = Selenium::WebDriver::Remote::Capabilities.firefox(marionette: false)
Selenium::WebDriver.for :firefox, desired_capabilities: capabilities
```

Both implementations allow you configure the profile used. The examples below, however, are for GeckoDriver.

#### Adding an extension

```ruby
profile = Selenium::WebDriver::Firefox::Profile.new
profile.add_extension("/path/to/extension.xpi")
options = Selenium::WebDriver::Firefox::Options.new
options.profile = profile
driver = Selenium::WebDriver.for :firefox, options: options
```

#### Using an existing profile

You can use an existing profile as a template for the WebDriver profile by passing the profile name (see `firefox -ProfileManager` to set up custom profiles.)

```ruby
options = Selenium::WebDriver::Firefox::Options.new
options.profile = "my-existing-profile"
driver = Selenium::WebDriver.for :firefox, options: options
```

If you want to use your default profile, pass `profile = "default"`

You can also get a Profile instance for an existing profile and tweak its preferences. This does not modify the existing profile, only the one used by WebDriver.

```ruby
default_profile = Selenium::WebDriver::Firefox::Profile.from_name "default"
default_profile.assume_untrusted_certificate_issuer = false

options = Selenium::WebDriver::Firefox::Options.new(profile: default_profile)
driver = Selenium::WebDriver.for :firefox, options: options
```

#### Tweaking Firefox preferences

Use a proxy:

```ruby
profile = Selenium::WebDriver::Firefox::Profile.new
proxy = Selenium::WebDriver::Proxy.new(http: "proxy.org:8080")
profile.proxy = proxy
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
```

Automatically download files to a given folder:

```ruby
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
profile['browser.download.folderList'] = 2
profile['browser.helperApps.neverAsk.saveToDisk'] = "application/pdf"
profile['pdfjs.disabled'] = true
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
```

If you are using the remote driver you can still configure the Firefox profile:

```ruby
profile = Selenium::WebDriver::Firefox::Profile.new
profile['foo.bar'] = true

capabilities = Selenium::WebDriver::Remote::Capabilities.firefox(firefox_profile: profile)
driver = Selenium::WebDriver.for :remote, desired_capabilities: capabilities
```

For a list of possible preferences, see [this page](http://preferential.mozdev.org/preferences.html).

#### Custom Firefox path

If your Firefox executable is in a non-standard location:

```ruby
options = Selenium::WebDriver::Firefox::Options.new
options.binary = "/path/to/firefox"
driver = Selenium::WebDriver.for :firefox, options: options
```

#### SSL Certificates

The Legacy Firefox driver ignores invalid SSL certificates by default. If this is not the behavior you want, you can do:

```ruby
profile = Selenium::WebDriver::Firefox::Profile.new
profile.secure_ssl = true
driver = Selenium::WebDriver.for :firefox, profile: profile
```

geckodriver will not implicitly trust untrusted or self-signed TLS certificates on navigation. To override this you can do:

```ruby
capabilities = Selenium::WebDriver::Remote::Capabilities.firefox(accept_insecure_certs: true)
driver = Selenium::WebDriver.for :firefox, desired_capabilities: capabilities
```


### Safari

As of 3.0, the only supported safari driver is the one [maintained by Apple](https://webkit.org/blog/6900/webdriver-support-in-safari-10/). It requires Safari 10 or greater; support for earlier versions of Safari has been removed.

```ruby
driver = Selenium::WebDriver.for :safari
driver.navigate.to "http://apple.com"
```

#### Technology Preview

The latest code for the Safari Driver is bundled with the Safari Technology Preview. To use it:

```ruby
Selenium::WebDriver::Safari.driver_path = "/Applications/Safari\ Technology\ Preview.app/Contents/MacOS/safaridriver"
driver = Selenium::WebDriver.for :safari
```


### Timeouts

#### Implicit waits

WebDriver lets you configure implicit waits, so that a call to `#find_element` will wait for a specified amount of time before raising a `NoSuchElementError`:

```ruby
  driver = Selenium::WebDriver.for :firefox
  driver.manage.timeouts.implicit_wait = 3 # seconds
```

#### Explicit waits

Use the Wait class to explicitly wait for some condition:

```ruby
  wait = Selenium::WebDriver::Wait.new(timeout: 3)
  wait.until { driver.find_element(id: "cheese").displayed? }
```

#### Internal timeouts

Internally, WebDriver uses HTTP to communicate with a lot of the drivers (the JsonWireProtocol). By default, `Net::HTTP` from Ruby's standard library is used, which has a default timeout of 60 seconds. If you call e.g. `Driver#get`, `Driver#click` on a page that takes more than 60 seconds to load, you'll see a `Timeout::Error` raised from `Net::HTTP`. You can configure this timeout (before launching a browser) by doing:

```ruby
  client = Selenium::WebDriver::Remote::Http::Default.new
  client.read_timeout = 120 # seconds
  driver = Selenium::WebDriver.for :remote, http_client: client
```


### JavaScript dialogs

You can use WebDriver to handle Javascript `alert()`, `prompt()` and `confirm()` dialogs.
The API for all three is the same.

```ruby
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
driver.navigate.to "http://mysite.com/page_with_alert.html"

driver.find_element(name: 'element_with_alert_javascript').click
a = driver.switch_to.alert
if a.text == 'A value you are looking for'
  a.dismiss
else
  a.accept
end
```

### Using Curb or your own HTTP client

For internal HTTP communication, `Net::HTTP` is used by default. If you e.g. have the [Curb gem](https://rubygems.org/gems/curb) installed, you can switch to it by doing:

```ruby
require 'selenium/webdriver/remote/http/curb'

client = Selenium::WebDriver::Remote::Http::Curb.new
driver = Selenium::WebDriver.for :firefox, http_client: client
```

### Using persistent HTTP connections

If you have the [net-http-persistent gem](https://github.com/drbrain/net-http-persistent) installed, you can use it to get keep-alive connections. This will significantly reduce the ephemeral ports usage of WebDriver, which is useful in [some contexts]({{< ref "../selenium_2/parallel_execution" >}}). Keep-alive connections are also supported in ChromeDriver.

```ruby
require 'selenium/webdriver/remote/http/persistent'

client = Selenium::WebDriver::Remote::Http::Persistent.new
driver = Selenium::WebDriver.for :chrome, http_client: client
```

### Logging

There is built-in logger available, by default it prints only warning messages. If you want to enable more verbose logging, add this to your code:

```ruby
Selenium::WebDriver.logger.level = :info
```

If you want full logging enabled:

```ruby
Selenium::WebDriver.logger.level = :debug
```

By default, logger prints messages to standard output, but you can also write to a file:

```ruby
Selenium::WebDriver.logger.output = 'selenium.log'
```
