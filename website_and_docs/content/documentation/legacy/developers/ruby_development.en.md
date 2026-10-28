---
title: "Ruby Development with Crazy Fun"
linkTitle: "Ruby Development"
weight: 11
description: >
    How to build and test the Ruby bindings with the Crazy Fun build.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Ruby-Development)

## Introduction

This page details how to build and test the Ruby code in Selenium.

### Details

The CrazyFunBuild runs on a bundled JRuby jar and is based on Rake. Use the 'go' Rake wrapper to run the targets. Unfortunately, rvm sets GEM\_HOME and causes trouble for our jruby-complete.jar. If you use rvm, you should disable it (`rvm use system`) before using the go script.

Since this is just a wrapper for Rake, familiar commands (like `go -T` to list targets) all work.

### Setting up

Tests will be run with your local Ruby installation, which should be a version >= 1.9.2.
Make sure you have bundler and our dependencies installed by running

  1. `gem install bundler`
  1. `./go //rb:bundle`

### Building

After making changes, you need to build the code (this is needed since we depend on other parts of the project):

  * `./go //rb:firefox`
  * `./go //rb:chrome`
  * etc.

Build results go in the `build/rb` directory. You can play with your changes in a REPL from there:

> `cd build/rb && bundle console`

Using/requiring the ruby code from `rb/lib` directly is not recommended.

You can build the gem with `./go //rb:gem:build`, which will place the gem in `build/`. See [Releasing Selenium](https://github.com/SeleniumHQ/selenium/wiki/Releasing-Selenium) for how to make a gem release.

### Testing

| Command | Description |
| --------------------- | ----------------------------- |
| `./go //rb:unit-test` | Run unit tests for WebDriver. |
| `./go //rb:firefox-test` | Run integration tests for Firefox - replace "firefox" with any driver. |
| `./go //rb:remote-firefox-test` | Run integration tests for Firefox on standalone server |

Try `./go -T | grep //rb` to see all the Ruby targets.

You can also run enable debug mode by passing `log=1` to the Rake target and run a single scenario by passing `example="full name of test"`.


### Dependencies

All Ruby dependencies are packaged in the repository. This means that you only need to have Ruby 2.0+ installed on your system to build and test Ruby bindings.

On the other hand, all the dependencies versions are locked in the repository, so from time to time we need to update them. To do that, you need to perform the following:

1. Switch to _minimum required Ruby version_ (see gemspec).
2. `BUNDLE_GEMFILE="rb/Gemfile" bundle update`.

### Contributing

  1. Make your feature addition or bug fix.
  1. Add tests for it. This is important so we don't break it in a future version unintentionally.
  1. Create a patch: `git diff > my-feature.patch`
  1. Create a [new issue](https://github.com/SeleniumHQ/selenium/issues/new), attach the patch and add the Lang-Ruby label.
