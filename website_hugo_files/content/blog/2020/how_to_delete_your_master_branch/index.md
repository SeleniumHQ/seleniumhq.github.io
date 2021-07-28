---
title: "How to delete your master branch"
linkTitle: "How to delete your master branch"
date: 2020-08-27
tags: ["stories"]
categories: ["technical"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  How to move away from master as the default branch
resources:
- src: "**.{png,jpg}"
  title: "Image #:counter"
---



{{< figure src="featured_how_to_delete_your_master_branch_2.png" class="img-responsive text-center" >}}

At the Selenium project we practice 
[trunk based development](https://trunkbaseddevelopment.com/), in which `trunk` is the 
usual name of the default git branch of the repository. However, when the project was
moved to GitHub, the repository followed the traditional use of `master` as a name for 
the default git branch.

With the intention of making the Selenium project an even more inclusive place where 
everyone is welcome, a decision was made to use `trunk` as the default git branch and, 
after the switch, delete the `master` branch. This change created a few challenges. 
This blog post will point out a few things you should watch out if you want to make 
the same change in your GitHub repository.


#### Broken Links
It is common to link specific parts of the code in the documentation, and that link 
normally contains the branch name. Double-check your documentation for links pointing 
to files living on the `master` branch, as they could end up as broken links after 
the change.

#### Mentions to the branch name
Similarly, the branch name gets mentioned in different parts of the repository, 
such as code comments, contribution instructions, issue and pull request templates, 
and the readme. Don’t forget to check these places. 

#### GitHub repository badges
We all like to show off how our GitHub repository is doing by adding as many badges 
as we can. In many cases, those badges report a build status that depends on the branch 
where the build was executed. Make sure your Travis/CircleCI/GitHub Actions badge is 
pointing to the new branch. 

#### Continuous Integration setups
A good practice in open source is to have a continuous integration setup to run the 
builds, execute tests and potentially do automated releases. Nowadays, the CI 
configuration is done in files (e.g. .travis.yml for Travis), and one important 
piece of that configuration is the name of the branch where the build should be 
executed. Similar to the previous point, double check that your new branch name 
is property configured in your CI integration.

#### Build scripts
At the Selenium project, we have a few custom build scripts that get executed 
through the continuous integration setup. An example is the script that generates 
the docs for the Java, Ruby and Python bindings. This script needs to know the code’s 
branch name to generate the docs. If you have scripts with similar purposes, check 
them after changing the branch name.

#### Open pull requests
All of the tasks above could probably be achieved with a text editor and a massive, 
but careful, “search and replace” across all the files in the repository. In short, 
the process we followed to move to the new branch `trunk` was:

1. Create a new branch called `trunk`, based on the `master` branch.
1. Do all the items described in the previous points.
1. Commit and push those changes.
1. Delete the `master` branch.

Nevertheless, one thing happened that we did not expect: after deleting the `master` 
branch, all the open pull requests got closed. This made sense, since they were all 
targeting the `master` branch. Therefore, before deleting your `master` branch, double 
check and, if needed, edit the open pull requests so they target the new branch.

{{< figure src="how_to_delete_your_master_branch_1.png" class="img-responsive text-center" >}}

It goes without saying that the name of your new branch can be any name that works 
well for your context and environment. For example, the 
[repository](https://github.com/SeleniumHQ/seleniumhq.github.io) that has the 
contents of the Selenium [website](https://www.selenium.dev/) uses now `dev` as the
branch with the website source files and `publish` as the branch with the generated
static website that gets *published*.

These are our lessons learned during the process of deleting the `master` branch in 
all the major repositories under the [SeleniumHQ GitHub](https://github.com/seleniumhq/)
organization. I hope they are helpful if you decide to move from `master` as a name 
for your default branch. 


*This was originally posted at https://opensource.saucelabs.com/blog/how_to_delete_your_master_branch/*
