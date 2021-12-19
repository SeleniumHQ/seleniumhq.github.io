---
title: "Firefox固有のCapabiliy"
linkTitle: "Firefox"
weight: 6
needsTranslation: true
description: >-
    これらのCapabiliyはFirefox固有のものです。
---

### `FirefoxOptions` を使用してCapabilitiesを定義する

`FirefoxOptions` は、Firefoxブラウザの機能を定義する新しい方法であり、通常はDesiredCapabilitiesよりも優先して使用する必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxOptions options = new FirefoxOptions();
options.addPreference("network.proxy.type", 0);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
options = Options()
options.headless = True
driver = webdriver.Firefox(options=options)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
options.Proxy.Kind = ProxyKind.Direct;
var driver = new FirefoxDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
opts = Selenium::WebDriver::Firefox::Options.new(args: ['-headless'])
driver = Selenium::WebDriver.for(:firefox, options: opts)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
options.headless();
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = new FirefoxOptions()
options.addPreference("network.proxy.type", 0)
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}


### カスタムプロファイルを設定する

以下に示すように、Firefoxのカスタムプロファイルを作成することができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}
