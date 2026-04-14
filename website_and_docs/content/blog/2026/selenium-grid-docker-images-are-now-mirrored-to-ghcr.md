---
title: "Selenium Grid Docker Images Are Now Mirrored to GHCR"
linkTitle: "Selenium Grid Docker Images Are Now Mirrored to GHCR"
date: 2026-04-04
tags: [ "Docker", "Grid" ]
categories: [ "Docker", "Grid" ]
author: Viet Nguyen Duc [@VietND96](https://github.com/VietND96)
images:
  - "/images/blog/2026/selenium-grid-docker-images-are-now-mirrored-to-ghcr.jpg"
description: >
  Official Selenium Grid Docker images are now mirrored to GitHub Container Registry, giving teams an official alternative to Docker Hub.
---

The request started with a simple question in [issue #2939](https://github.com/SeleniumHQ/docker-selenium/issues/2939): can we publish Selenium Grid Docker images to GitHub Container Registry as well as Docker Hub?

The answer is now yes.

Official Selenium Grid Docker images are now available from **GitHub Container Registry (GHCR)** under:

```
ghcr.io/seleniumhq
```

This is a mirror, not a replacement. Docker Hub remains available, and existing users do not need to change anything. But if your environment prefers GHCR, blocks Docker Hub, or simply wants another official source for the same images, you now have one.

## Why this matters

The original issue highlighted a problem many teams have run into over the last year: relying on a single public registry creates unnecessary friction.

For some users, Docker Hub rate limits are the main concern. For others, the issue is policy. Some companies no longer allow pulls from Docker Hub in CI or on developer machines. Others want to stay closer to GitHub-hosted infrastructure because their build pipelines already run in GitHub Actions.

Publishing to GHCR improves that situation in a few practical ways:

- It gives users an official alternative registry for the same Selenium Grid images.
- It reduces dependency on a single distribution channel.
- It makes adoption easier for teams that already standardize on GitHub-hosted packages.
- It improves resilience when one registry is slow, restricted, or temporarily unavailable.

This was not just a theoretical request. The discussion on the issue included examples from users who needed GHCR for local development, fork-based GitHub Actions workflows, and enterprise environments where Docker Hub access is restricted.

## What is available

The GHCR namespace for Selenium Grid images is:

```text
ghcr.io/seleniumhq
```

That namespace is important because the original feature request used example paths under `ghcr.io/selenium/...`, while the final published location is **`ghcr.io/seleniumhq/...`**.

From the rollout tracked in issue `#2939` and the current repository state:

- `nightly` tags are available on GHCR for all images.
- Release images are available on GHCR, with the first full release called out in the issue as [`4.42.0-20260303`](https://github.com/SeleniumHQ/docker-selenium/releases/tag/4.42.0-20260303).
- Recent CI updates in the repository also extend GHCR mirroring for Dev/Beta browser images.

You can browse the published packages here:

- https://github.com/orgs/SeleniumHQ/packages?repo_name=docker-selenium

## How to pull from GHCR

If you already use Selenium Grid images from Docker Hub, switching to GHCR is mostly a matter of replacing the registry prefix.

For example:

```bash
docker pull ghcr.io/seleniumhq/standalone-chrome:latest

docker run -d -p 4444:4444 \
  ghcr.io/seleniumhq/standalone-chrome:latest
```

If you want a pinned release instead of `latest`:

```bash
docker pull ghcr.io/seleniumhq/standalone-firefox:4.42.0-20260303
```

If you are testing against the bleeding edge:

```bash
docker pull ghcr.io/seleniumhq/node-chrome:nightly
```

The naming model stays familiar. The main change is the registry hostname and namespace.

## What is not changing

This rollout is intentionally additive.

- Docker Hub images are still part of the Selenium Grid distribution story.
- Existing commands that use `selenium/<image>:<tag>` continue to work.
- Users can adopt GHCR at their own pace.

That matters because the goal here is flexibility, not churn. Teams that are happy with Docker Hub can stay where they are. Teams that need GHCR can move immediately without waiting for custom mirrors or maintaining internal rebuilds of Selenium Grid images.

## From request to delivery

Issue [#2939](https://github.com/SeleniumHQ/docker-selenium/issues/2939) was opened on **August 27, 2025** to propose dual publishing to Docker Hub and GHCR. The request was driven by Docker Hub rate limits, but the conversation quickly broadened into a more durable point: official images should be available from more than one registry.

That feedback held up. Contributors and users pointed to several real-world reasons for supporting GHCR:

- GitHub Actions workflows, especially from forks
- enterprise environments that disallow Docker Hub
- local development without Docker Hub authentication
- better resilience during registry outages or service limits

The result is a practical improvement to how Selenium Grid images are distributed: one image set, one familiar naming scheme, and more than one official place to pull from.

## Thank you

Thanks to everyone who raised the need, added concrete use cases, and helped push the rollout forward. Community feedback in issue [#2939](https://github.com/SeleniumHQ/docker-selenium/issues/2939) helped move this from a feature request into delivered infrastructure.

If you are already using the new mirror, or plan to switch CI workloads over to GHCR, feedback on image coverage and pull experience will help guide the next refinements.

Happy testing!
