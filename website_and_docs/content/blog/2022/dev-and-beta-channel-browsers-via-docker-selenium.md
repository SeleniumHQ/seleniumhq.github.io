---
title: "Dev and Beta Channel Browsers via Docker Selenium"
linkTitle: "Dev and Beta Channel Browsers via Docker Selenium"
date: 2022-08-23
tags: ["webdriver", "docker", "browsers", "chrome", "firefox", "edge"]
categories: ["general", "browsers", "docker"]
author: James Mortensen ([@jmort253](https://twitter.com/jmort253))
description: >
  Now you can test on Google Chrome, Mozilla Firefox, 
  and Microsoft Edge on the Dev and Beta channels using Docker Selenium
---

The Docker Selenium browser Beta and Dev channel releases are now regularly published to [Docker Hub](https://hub.docker.com/u/selenium) and updated every two days. This enables testers and developers to test their applications on pre-release versions of Google Chrome, Mozilla Firefox, and Microsoft Edge before their official releases, using container tools, such as Docker. This empowers teams to stay ahead of the curve and catch potential showstoppers in their CI environment _before_ those issues have an impact on their users.

### What are "release channels"?


Here's some background on release channels: Google, Mozilla, and Microsoft all maintain different "channels" for their respective browser releases. Most end users use the "Stable" channel. The Stable channel releases have passed rigorous testing, and these versions are considered "production-ready". Up until now, this was the only option for a tester or developer when using Docker Selenium. Today, we're pleased to announce teams can also test on versions of these browsers yet to be released to the general population. These are the Beta and Dev channels.

For clarity, here is Google's description of these 3 channels from [their "Chrome Release Channels" page](https://www.chromium.org/getting-involved/dev-channel/):

> **Stable channel:** This channel has gotten the full testing and blessing of the Chrome test team, and is the best bet to avoid crashes and other issues. It's updated roughly every two-three weeks for minor releases, and every 6 weeks for major releases.

> **Beta channel:** If you are interested in seeing what's next, with minimal risk, Beta channel is the place to be. It's updated every week roughly, with major updates coming every six weeks, more than a month before the Stable channel will get them.

> **Dev channel:** If you want to see what's happening quickly, then you want the Dev channel. The Dev channel gets updated once or twice weekly, and it shows what we're working on right now. There's no lag between major versions, whatever code we've got, you will get. While this build does get tested, it is still subject to bugs, as we want people to see what's new as soon as possible.


### Standalone Usage:

To use these new Docker Selenium container images in Standalone mode, run the following commands:

**Chrome Beta:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-chrome:beta
```

**Chrome Dev:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-chrome:dev
```

**Firefox Beta:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-firefox:beta
```

**Firefox Dev:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-firefox:dev
```

**Edge Beta:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-edge:beta
```

**Edge Dev:**

```bash
$ docker run --rm -it -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-edge:dev
```

### Selenium Grid

To run these container images in a Selenium Grid, we'll use docker-compose. For instance, this example shows how to run a Selenium Grid with Chrome, Firefox, and Edge on the Beta channel:

**docker-compose-v3-beta-channel.yml:**
```bash
# To execute this docker-compose yml file use `docker-compose -f docker-compose-v3-beta-channel.yml up`
# Add the `-d` flag at the end for detached execution
# To stop the execution, hit Ctrl+C, and then `docker-compose -f docker-compose-v3-beta-channel.yml down`
version: "3"
services:
  chrome:
    image: selenium/node-chrome:beta
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  edge:
    image: selenium/node-edge:beta
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox:
    image: selenium/node-firefox:beta
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  selenium-hub:
    image: selenium/hub:latest
    container_name: selenium-hub
    ports:
      - "4442:4442"
      - "4443:4443"
      - "4444:4444"
```

To run on the more cutting edge -- and hence potentially more buggy -- Dev channel, simply replace the "beta" tags with "dev".

### Support

These Beta and Dev images represent pre-release browsers and WebDrivers, and they're not guaranteed to be 100% stable. Should you run into any issues with these images, support from the Selenium community is limited and is dependent on the browser vendors. However, if you believe the issue is related to Selenium, please contact us in the [various support channels](/support/).
