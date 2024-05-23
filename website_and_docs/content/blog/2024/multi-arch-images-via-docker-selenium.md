---
title: "Multi-Arch Images via Docker Selenium"
linkTitle: "Multi-Arch Images via Docker Selenium"
date: 2024-05-23
tags: [ "Grid", "Docker", "Kubernetes" ]
categories: [ "Docker" ]
author: Viet Nguyen Duc [@VietND96](https://github.com/VietND96)
description: >
  This blog post to announce the visibility of Multi-Arch Images for Selenium Grid Server on official Selenium Docker Hub registry.
---

We're very happy to announce the landing of Multi-Arch Images for Selenium Grid Server on
the [Selenium](https://hub.docker.com/r/selenium/) Docker Hub registry!

### Motivation

For experimental Docker container images, which is able to run on platforms such as the Apple M-series or Raspberry Pi,
the community-driven repository initiative hosted
at [SeleniumHQ-Community/docker-seleniarm](https://github.com/seleniumhq-community/docker-seleniarm). These images are
built for separate architectures: linux/arm64 (aarch64), linux/arm/v7 (armhf), and linux/amd64 and published
on [Seleniarm](https://hub.docker.com/u/seleniarm) Docker Hub registry.

In order to bring more awareness to the existence of the Multi-Arch Docker container images, provide more insight and
transparency on how the container images are built, as well as overcome challenges in building and maintaining them. We
have decided to merge the fork into the main project [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)
and
published multi-arch images on [Selenium](https://hub.docker.com/r/selenium/) Docker Hub registry.

### Overview

From image tag [releases](https://github.com/SeleniumHQ/docker-selenium/releases) `4.21.0` onwards, the architectures
supported by Docker Selenium as below

|       Architecture        | Operating System | Available |
|:-------------------------:|------------------|:---------:|
|    x86_64 (aka amd64)     | Ubuntu LTS       |     ✅     |
| aarch64 (aka arm64/armv8) | Ubuntu LTS       |     ✅     |
| armhf (aka arm32/armv7l)  | N/A              |     ❌     |

Based on the architecture of the host machine, Docker will automatically pull the correct image for the platform.

### Browser Binaries

Let's take a moment to look at the browser binaries which are available for various architectures

Google does not build Chrome (google-chrome) for Linux/ARM platforms. Hence, the Chrome (node and standalone) images are
only available for AMD64. Similarly, Microsoft does not build Edge (microsoft-edge) for Linux/ARM platforms.

Instead, the open source Chromium browser is used in place of Chrome and Edge. The `standalone-chromium` and `node-chromium`

```bash
$ docker run --rm -it -p 4444:4444 -p 5900:5900 -p 7900:7900 --shm-size 2g selenium/standalone-chromium:latest
```

Mozilla Firefox now is available for Linux/ARM64
via [Nightly](https://blog.nightly.mozilla.org/2024/04/19/firefox-nightly-now-available-for-linux-on-arm64/) channel.
The Firefox version in ARM64 image will be different with the AMD64 until the stable release is available.

| Image Name          | Operating System | amd64 | arm64 |
|---------------------|------------------|-------|-------|
| standalone-chromium | Ubuntu LTS       | ✅     | ✅     |
| node-chromium       | Ubuntu LTS       | ✅     | ✅     |
| standalone-firefox  | Ubuntu LTS       | ✅     | ✅     |
| node-firefox        | Ubuntu LTS       | ✅     | ✅     |
| standalone-edge     | Ubuntu LTS       | ✅     | ❌     |
| node-edge           | Ubuntu LTS       | ✅     | ❌     |
| standalone-chrome   | Ubuntu LTS       | ✅     | ❌     |
| node-chrome         | Ubuntu LTS       | ✅     | ❌     |

### Build, test, and distribute

We also would like to share something that has been done to keep the multi-arch images can be built, tested, and
distributed seamlessly.

- Utilize Bash scripts and Makefile to wrap the tasks as much as possible. It provides the transparency on how the
container images are built and proceed by single command.

- Utilize Arm VM's support on [CircleCI](https://app.circleci.com/pipelines/github/SeleniumHQ/docker-selenium) to build,
test, and deploy ARM64 images. Once GitHub Actions officially supports Arm-based hosted runners, those workflows can
easily be moved back to the same place. All the tests done for AMD64 images (including Docker, Docker Compose, and
deploy to Kubernetes) are used to verify ARM64 images.

- Utilize experimental feature [containerd image store](https://docs.docker.com/storage/containerd/) in Docker Engine to
build and distribute multi-arch images in a simple way.

Hopefully, this will make it easy for the community to find and use multi-arch images to simplify Selenium Grid Server
deployment on various platforms.

Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq)
or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy testing!
