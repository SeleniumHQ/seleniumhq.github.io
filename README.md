[![GitHub Actions](https://github.com/seleniumhq/seleniumhq.github.io/workflows/Publish%20Selenium%20Site/badge.svg)](https://github.com/SeleniumHQ/seleniumhq.github.io/actions?query=workflow%3A%22Publish+Selenium+Site%22)

<a href="https://selenium.dev"><img src="https://selenium.dev/images/selenium_logo_square_green.png" width="200" alt="Selenium"/></a>

# Selenium Site and Documentation

This is the repository used to build and publish the official Selenium [website](https://selenium.dev).

## Quick start

We use [Hugo](https://gohugo.io/) to build and render the site and docs,
which are two separate Hugo projects.

After cloning the repository, if you want to make changes to the
site, work on the `site_source_files` directory. To make changes to the docs,
switch to the `docs_source_files` directory. To see a live preview of your
changes, run `hugo server` on each directory.

```shell
% cd site_source_files/docs_source_files
% hugo server
```

A full contribution guideline can be seen at [contributing](https://selenium.dev/documentation/en/contributing/)

## How to get involved?

Please check all the information available at https://selenium.dev/getinvolved/

## For Selenium Site and Documentation maintainers

### How does the site and docs get build?

GitHub actions runs for every commit on each PR and protected branch. The regular CI execution will just
build the site with Hugo to verify that the commit works. The description of these steps can be seen
at the actions configuration file, [one for testing a PR](./.github/workflows/test.yml), and 
[one for deploying the site](./.github/workflows/deploy.yml)

### How are the site and docs deployed?

After each CI execution that happens in the `dev` branch, the script [build-site.sh](./build-site.sh) 
is executed for deployment. This script checks for the string `[deploy site]` in the commit message.

If the commit message contains that string, and the commit is in `dev`, a 
[GitHub action](./.github/workflows/deploy.yml) is triggered to build and deploy the site. 
The site and docs will be built, and the changes will
be committed to the branch `publish` by the user [Selenium-CI](https://github.com/selenium-ci/).

*What is important to take into account is that the source files for the site are in the `dev`
branch, and the files that get deployed are pushed to the `publish` branch.*

The site and docs are deployed using GitHub pages, and the configuration for this can be seen at the
repo [settings](https://github.com/SeleniumHQ/seleniumhq.github.io/settings) (if you are a maintainer
you should be able to access the link).

The selenium.dev domain is managed at https://www.gandi.net/en, if you need access to it, reach out to
any of the [PLC](https://www.selenium.dev/structure/#plc) or [TLC](https://www.selenium.dev/structure/#tlc)
members, who can help you with that.

If for any reason, you need to setup the domain redirection again,
we followed this [guide](http://spector.io/how-to-set-up-github-pages-with-a-custom-domain-on-gandi/),
but any tutorial/guide showing how to redirect a domain to GitHub pages should do.   

## (WIP) Moving to Hugo [Docsy theme](https://www.docsy.dev/)

Files are living under [website_hugo_files](website_hugo_files).
 
Steps needed to have this working locally and work on it:

- Follow the [Install Hugo](https://www.docsy.dev/docs/getting-started/#install-hugo) instructions from Docsy
- Clone this repository
- Run `git submodule update --init --recursive`
- `cd website_hugo_files`
- `npm install`


