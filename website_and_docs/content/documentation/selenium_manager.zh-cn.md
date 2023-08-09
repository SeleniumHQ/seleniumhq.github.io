---
title: "Selenium Manager (Beta)"
linkTitle: "Selenium Manager"
weight: 11
description: >
    The Selenium Manager is a binary generated with Rust that manages driver installation.
---

## Applicability

***Selenium bindings use this tool by default, so you do not need to download it or
add anything to your code or do anything else to use it.***

If, however, you need to access drivers with `curl` or a REST client,
use one of the unofficial Selenium implementations,
have special network requirements, or just want to better
understand what the tool is doing, please continue reading.

## Usage

The Selenium bindings use the Manager automatically so long as the following conditions are met:
* The driver location isn't specified in a `Service` class
* A 3rd party driver manager is not installed
* No drivers exist in directories included in the `PATH` Environment Variable

To use it with Selenium Grid:
* Start the grid with this additional argument: `--selenium-manager true`

## Status

This tool is still in beta, and Selenium is slowly adding support for its features.
The current implementation is a "fall-back" option, which means it should only get used
if the code execution would otherwise fail. So long as you specify the location of the
driver in the appropriate [Service class]({{< ref "webdriver/drivers/service.md" >}})
(or using System Properties in Java), the Selenium Manager will not be used.

### History

The first implementation of Selenium Manager was released in Selenium 4.6. We
made an announcement about it [on our blog](https://www.selenium.dev/blog/2022/introducing-selenium-manager/).

* Selenium 4.7 added support for IE Driver and improved error logging
* Selenium 4.8 added support for beta and development versions of browsers
* Selenium 4.9 added support for locating drivers based on provided location of browser binary 

### Future

We are tracking the various features as a [GitHub Project](https://github.com/orgs/SeleniumHQ/projects/5/views/1).
The major features we are looking to support:
* Proxies
* Warning/fixing problems with drivers on `PATH`
* Browser downloads based on `browserVersion` provided in capabilities

## Configuration

Specific values can be overridden by specifying environment variables or by using a config file 
located by default at `~/.cache/selenium/selenium-manager-config.toml`.

| CLI                              | Env Variable                                       | Config File                                         |
|----------------------------------|----------------------------------------------------|-----------------------------------------------------|
| --browser chrome                 | SE_BROWSER=chrome                                  | browser = "chrome"                                  |
| --driver chromedriver            | SE_DRIVER=chromedriver                             | driver = "chromedriver"                             |
| --browser-version 106            | SE_BROWSER_VERSION=106                             | browser-version = "106"                             |
| --driver-version 106.05249.61    | SE_DRIVER_VERSION=106.0.5249.61                    | driver-version = "106.0.5249.61"                    |
| --browser-path /path/to/chromium | SE_BROWSER_PATH=/path/to/chromium                  | browser-path = "/path/to/chromium"                  |
|                                  | SE_OS=macos                                        | os = "macos"                                        |
|                                  | SE_ARCH=x64                                        | arch = "x64"                                        |
| --proxy user@pass:myproxy:8080   | SE_PROXY=user@pass:myproxy:8080                    | proxy = "user@pass:myproxy:8080"                    |
| --browser-ttl 0                  | SE_BROWSER_TTL=0                                   | browser-ttl = 0                                     |
| --driver-ttl 86400               | SE_DRIVER_TTL=86400                                | driver-ttl = 86400                                  |
| --clear-cache                    |                                                    |                                                     |
| --clear-metadata                 |                                                    |                                                     |
| --debug                          | SE_DEBUG=true                                      | debug true                                          |
| --trace                          | SE_TRACE=true                                      |                                                     |

## Development

Selenium Manager is written in Rust. Find the source code for it in the [Selenium GitHub repository](https://github.com/SeleniumHQ/selenium/tree/trunk/rust).

## Build and Release

Selenium Manager is compiled using GitHub Actions workflows. The workflows create binaries for Windows, Linux, and MacOS. These binaries work in both x86 and ARM architectures, so these 3 binaries should be enough for almost all use cases. You can find the build job definitions [here](https://github.com/SeleniumHQ/selenium/actions/workflows/build-selenium-manager.yml).

The build artifacts are manually checked into the selenium repo and stored under the [common folder](https://github.com/SeleniumHQ/selenium/tree/trunk/common/manager). The [bazel build tasks](https://github.com/SeleniumHQ/selenium/blob/trunk/README.md#bazel) then copy these pre-built binaries for packaging the binary within Selenium releases.
