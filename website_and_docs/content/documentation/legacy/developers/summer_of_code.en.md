---
title: "Google Summer of Code 2011"
linkTitle: "Summer of Code"
weight: 8
description: >
  Selenium encouraged users to take advantage of this program.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Google-Summer-Of-Code)

## What is Google Summer of Code?

Since 2005, Google has administered [Google Summer of Code Program](http://code.google.com/soc/) to encourage student participation in open source development. The program has several goals:

* Inspire young developers to begin participating in open source development
* Provide students in Computer Science and related fields the opportunity to do work related to their academic pursuits during the summer
* Give students more exposure to real-world software development scenarios (e.g., distributed development, software licensing questions, mailing-list etiquette, etc.)
* Get more open source code created and released for the benefit of all
* Help open source projects identify and bring in new developers and committers

Google will pay successful student contributors a $5000 USD stipend, enabling them to focus on their coding projects for three months.  The deadline for application is **April 8, 2011**.

When participating in the Selenium - Google Summer of Code program, students will learn that testing, and building automated testing tools, can be both fun and an integral part of delivering high quality software.  The collaborative effort with Selenium contributors can provide you with a new toolset to develop and document a set of components used by thousands of people.   You will gain valuable professional experience towards your career development and ultimately help drive higher quality web applications everywhere.

Please [Email](mailto:adam@element34.ca) questions to GSoC coordinator Adam Goucher

## Student Eligibility

* 18 years of age or older by April 26, 2010
* Currently enrolled in an accredited institution such as colleges, university, master programs, PhD programs and undergraduate programs
* Residents and nationals of countries other than Iran, Syria, Cuba, Sudan, North Korea and Myanmar (Burma) with whom we are prohibited by U.S. law from engaging in commerce
* Strong skills in some or more of the following: Web Application Development, JavaScript, Python, Flash, iPhone / Android
* Self-directed, resourceful, responsible, communicative
* Ability to work full-time from May 24  – August 20, 2010  (students residing in SF Bay Area may have an opportunity to work on-site from time to time with some of our mentors)
* More info on Student Eligibility can be found [here](http://socghop.appspot.com/document/show/gsoc_program/google/gsoc2010/faqs#student_eligibility)

If you meet the above requirements, we’d love to have you apply to Selenium for this year's Google Summer of Code.

## Next steps and deadlines

1. Read [Expectations](GoogleSummerOfCode#Expectations.md) to understand what is expected of you.
1. Read [Applications](GoogleSummerOfCode#Application.md) to find out what to put on your application.
1. Take a look at the [Project Ideas](GoogleSummerOfCode#Project_Ideas.md).  If any interest you, feel free to contact the proposer for details.  You can also discuss your own project ideas with the people mentioned or talk about them on our [developer mailing list](http://groups.google.com/group/selenium-developers/) or on IRC #selenium on freenode
1. [Submit your application directly to Google](http://www.google-melange.com/gsoc/profile/student/google/gsoc2011) before April 8, 2011.  You can modify your application with your mentors' feedback after your initial submission, the final version of your application is due April 23, 2011.
1. Selenium GSoC team will finish reviewing applications and match students with mentors by April 23, 2011.
1. Google announces accepted students on April 26, 2011.

Please [Email](mailto:adam@element34.ca) questions to GSoC coordinator Adam Goucher

## Project Ideas

These are project ideas proposed by mentors.  Please send a post to the  [developer mailing list](http://groups.google.com/group/selenium-developers/) if you are interested in it or [email](mailto:adam@element34.ca) GSoC coordinator Adam Goucher.

### A Scriptable Proxy

**Mentor**
Patrick Lightbody(?)

**Difficulty**


&lt;unknown&gt;



**Description**
Selenium is a browser control framework, but sometimes you want to do things to/with the traffic generates. The 'right' way to do this is to put a proxy in the middle and use its API to do get / fiddle with the traffic information. This project extends the BrowserMob proxy to add the APIs that users of Selenium would need.

**Tags** Se-RC, Se2

### Image Based Locators

**Mentor**


&lt;unknown&gt;



**Difficulty**


&lt;unknown&gt;



**Description**
Sikuli gets a lot of play for its ability to interact with items on the page based on Images. This project would add Image Based Locators to the list of available ones.

**Tags** Se-RC, Se2, Se-IDE

### Selenese Runner

**Mentor**
Adam Goucher

**Difficulty**


&lt;unknown&gt;



**Description**
It is possible to run Selenese scripts outside of Se-IDE with the -htmlSuite option on the server. There are a number of downsides to this, like the need to start/stop the server constantly. This project will create a standalone 'runner' for Selenese scripts to interact with the server -- and remove the related code from the server.

**Tags** Se-RC, Se, Se-IDE

## Perspective mentors

It's not too late to apply to be a mentor, if you are interested, please add your project idea here and discuss logistics with [Adam Goucher](mailto:adam@element34.ca)

## Expectations

### Summary

This page covers, in detail, the expectations for Google Summer of Code students in regards to communication. This is useful for Selenium projects which haven't codified their expectations--they can point to this document and use it as is.

The Google Summer of Code coding period is very short. On top of that, many students haven't done a lot of real-world development/engineering work previously; one of the primary purposes of the program is to introduce students to F/OSS and real-world development scenarios. On top of that, most mentors and students are in different locations--so face-to-face time is difficult. Because of this, it's vitally important to the success of the GSoC project for all expectations to be specified before students begin coding on May 26th. This should be the first step in a long series of frequent communication between student and mentor(s).

This document walks through various expectations for students and mentors, as well as addressing various ways to communicate effectively.

### 40 hour work week

Students are expected to work at least 40 hours a week on their GSoC project. This is essentially a full-time job.

The benefits for the GSoC project are huge:

* the chance to become part of a project community over the long term--this can lead to involvement with other projects, social network, good friends, valuable resources, ...
* the chance to work with real developers on a real project
* the end result of the student's project can be used for resume material that is available for all future employers to see

The final point is an important one for a beginning developer.  Employers greatly appreciate having a referenceable body of work when looking at potential employees. Your code says more about your abilities than any amount of algorithms on a whiteboard can.

And of course, the program will provide you with 5000 USD in income and a really cool t-shirt.

Some GsoC students have become prominent technology bloggers, committers to open source projects, speakers at conferences, mentors for other students, and more…


### Self-motivation and steady schedule

The student is expected to be self-motivated. The mentor may push the student to excel, but if the student is not self-motivated to work, then the student probably won't get much out of participating.  The student should schedule time to work on the project each day and keep to a regular schedule. It's not acceptable to fiddle around for days on end and then pull an all-nighter just before deadlines. It will show in your code.

### Regular Weekly Meeting and Frequent Communication with mentor

Regular weekly meeting with your mentor is a must.  The planned meeting should cover:

* what you're currently working on
* how far you've gotten
* how you're implementing it
* what you plan on working on next
* what issues have come up
* what you did to get around them
* what's blocking you if you're stuck
* code review, when applicable


The mentor is one of the most valuable resources for GSoC projects. The mentor is both a solid developer and a solid engineer. The mentor likely has worked on the project for long enough to know the history of decisions, how things are architected, the other people involved, the process for doing things, and all other cultural lore that will help the student be most successful.

Before the GSoC project starts, the mentor and student should iron out answers to the following questions:

1. When is the regular, scheduled communication scheduled?  Weekly?  Every two days? Mondays, Wednesdays, Fridays?
1. What is the best medium to use for regular, scheduled communication? VOIP? Telephone call? Face-to-face?
1. What is the best medium to use for non-scheduled communication? Email? Instant messenger?

DO:
* be considerate of your time and your mentor’s time and plan for your regular meeting
    * Consider emailing the answers to the above agenda ahead of time so you can spend your time productively on coming up with solutions, code reviews, and planning for the next milestones.
* talk to your mentors and developers on the mailing list / IRC frequently, outside of your planned meeting
    * your mentor is not the only person that can help you out and keep you stay on track, Selenium has a nice community and you will learn a lot from the other people as well.
* let your mentor know what your schedule is
    * Are you going on vacation, moving, writing papers for class? If your mentor doesn't know where you'll be or to expect a lag in your productivity, your mentor can't help you course correct or plan accordingly.

AVOID:
* going for more than a week without communicating with your mentor
    * The project timeline doesn't allow for unplanned gaps in communication.

### Version control

Students should be using version control for their project.

DO:
* commit-early/commit-often
    * This allows issues to be caught quickly and prevents the dreaded one-massive-commit-before-deadline.
* use quality commit messages

Bad examples:
Fixed a bug.  Tweaks.

Good examples:
Fixed a memory leak where the thingamajig wasn't getting freed after the parent doohicky was freed.  Fixed bug #902 (on Google Code) by changing the comparison used for duplicate removal.  Implemented Joe's good idea about rendering in a separate buffer and then swapping the buffer in after rendering is complete.  Improved HTML by simplifying tables.

* refer to specific bug numbers, links, and issues as much as possible
* The history in version control is frequently the best timeline log of what happened, why, and who did it.

AVOID:
* checking in multiple non-related changes in one big commit
    * If something is bad about one of the changes and someone needs to roll it back, it's more difficult to do so.
* checking in changes that haven't been tested

### Communication with project

Most F/OSS projects have mailing lists for project members and the community and/or have IRC channels to communicate. These communication channels allow the student to keep in touch with the other project members and are an incredibly valuable resource. Other members of the project may be better versed in various parts of the project, they may provide a fallback if the mentor isn't available, and they may be a good sounding board for figuring out the specific behavior for features. You are assigned a mentor, but the whole community is there to help you learn. Make use of all the resources at your disposal.


Shyness is a common problem for students who are new to open source development. At the beginning of the project, the student is encouraged to send a "Hello! I'm ... and I'm working on a GSoC project on ... and here's a link to the proposal." email to project mailing lists and encouraged to log in and say "hi" on IRC. Break the ice early--it makes the rest of the project easier. If you don't know where you announce yourself, ask your mentor.

#### Project mailing lists

Mailing lists are a great way to work out feature specifications and expected behavior.

Often mailing lists are archived and the archives are a rich source of information regarding prior discussions, decisions, and technical errata.


DO:

* search through the archives for answers before asking on the list
* be courteous at all times
* be specific
    * Cite data, references, and use links wherever possible when discussing technical things.
* be patient
    * Don't expect an answer within minutes or hours; people often read their mailing-list messages once per day.

AVOID:

* being rude
    * Since most mailing lists are archived or recorded, it's likely anything you say will be available for everyone to see forever; exercise good manners in all aspects of life.
* saying things with all capital letters and excessive punctuation
    * This is perceived as shouting
* getting into heated arguments
    * If someone insults you, it's best to ignore it.

#### IRC

Most F/OSS projects have an IRC channel and some have more than one. People from the project and its community "hang out" on these channels and talk about various things. Some projects have regularly scheduled meetings to cover the status of the project, how development is going, status of major blocking bugs, map out future plans, ...


If the project has an IRC channel, it's a good idea to hang out there. This allows the student to interact with the community and also a forum for working out problems and ideas in real time.


DO:
* **hang out on the project IRC channels when you're working on the project**
* **take time to interact with people who are on the IRC channel** This builds community and it's easier to get help from people who are familiar with you than people who aren't.

AVOID:
* **saying things with all capital letters and excessive punctuation** This is perceived as shouting.
* **poor grammar** It makes it harder for other people to understand what you're trying to say.
* **being rude**

We're all real people with real feelings and if you're rude it's likely people will interact with you and help you less; also it's not uncommon for IRC history to be recorded and archived for all to see forever.

See:
* http://www.linuxchix.org/irc-basics.html
* http://en.wikipedia.org/wiki/Internet_Relay_Chat

#### Design documents

It's a good idea for the student to maintain design documents during the course of the GSoC project. These design documents should cover:

1. the project plan, with additional detail to flesh out the original program application
1. deviations from the project plan and how and why the original design plan changed
1. any issues that could not be worked out or overcome
1. possible future directions
1. any resources used or relevant specifications

The student and mentor should work out what design documents should be maintained during the course of the GSoC.

One thing to note is that the student shouldn't spend all his/her time doing design documents. It's important to keep track of the design, but it's also important to get some code done. The mentor should be able to help the student strike a balance between these two goals.

#### Blogging
Students should get in the habit of blogging about about his/her work at least once every two weeks.  Historically, students who do learn much faster, are more productive, and develop a stronger tie to the community.   Some have gone on to become contributors, others have given talks / presentations at conferences.   How would you like to see your career grow?

## Application

### Evaluation Criteria

We recognized that very few students have exposures to Selenium during their studies and will therefore evaluate you based on your:

* ability to think, learn, and reason
* talents (what have you accomplished thus far, programming and otherwise)
* attitude, communications, ability to work well with the community and your mentor
* availability and commitment to succeeding in GSoC and potential for continuous involvement with community
* in a nutshell, what makes you a good person to lead that project initiative :-)


As long as you get your application in before April 9, you will have until April 18 to fine-tune your proposal with our mentors.

### Preparing Your Proposal
Here are some questions to get you started.  You don't have to follow it and your application will still be considered, but it's a good place to start.

Feel free to include anything else you feel is important.   One liner answers are not likely going to be considered.  In the meanwhile, do feel free to introduce yourself to the community and discuss your project proposal by writing to our developer mailing list.


**General Questions**:

1. Give us a short introduction of yourself.
1. Email address and phone number we can reach you at.
1. What are you studying? What year of study will you be in September 2010?
1. How much time can you devote to Summer of code?  What else are you doing this summer?

**Your Experience**:

1. How did you get started with programming?  How long have you been doing it?  Why do you love it?  Any personal projects you can show us? Have you participated in coding contests / taught / mentor other students?
1. What are your programming interests? Are you a C guy - do you like to get down and dirty with the linux kernel?  Do you know more about Java than your peers? Or are you more of a python/ruby person?  What about JavaScript?  You know, what's your style?
1. Have you worked for a sofware company as a programmer before?
1. Have you worked on an open source project before? Which ones? Describe your participation
1. Do you have a blog?  A resume?
1. What makes you a good person for Google Summer of Code?  What do you want to get out of it?

**Project Questions**:

1. What idea did you choose?
1. Elaborate on the idea and describe what you would like to accomplish during the summer. This question is especially important if you have your own idea instead of picking one from our list, as we want to have a good understanding of what you're proposing so we can help you take the idea forward.
1. Give us a brief time-line of the project for the things you'd like to accomplish.  It’s OK to include thinking time (“investigation”) in your work schedule. Work should include:
   * investigation
   * programming
   * documentation
   * dissemination
1. How do you plan to test your code?  What version control and build systems do you plan to use?
1. If your project is very successful, do you wish to contributing to it further once Google Summer of Code is complete?


### Sample Proposal Outline

A good proposal will have the following component:

* **Name and Contact Information**.  Include email, phone(s), IM, Skype, etc.
* **Title**.  One liner on the goal to your project.
* **Synopsis**.  Short summary, what would your project do?
* **Benefits to Community**. Why would Google and Selenium be proud to sponsor this work? How would open source or society as a whole benefit?
* **Deliverables**. We want to know that you have a plan and that at the end of the summer, something get delivered. :-)  Give a brief, clear work breakdown structure with milestones and deadlines. Make sure to label deliverables as optional or required. You may want plan to start by producing some kind of whitepaper, or planning the project in traditional Software Engineering style.  It’s OK to include thinking time (“investigation”) in your work schedule. Work should include:
    * investigation
    * programming
    * documentation
    * dissemination
* **Description**.  A small list of project details.  Your mentors can give you some guidance on this, but start by letting us know you are thinking :-)
    * rough architecture
    * links to parallel projects that you may get insights from
    * what version control and build system do you plan to use
    * how do you plan to test
    * best practices to get your code accepted, etc.
* **Bio**. Who are you? What makes you the best person to work on this project?
    * Summarize your education, work, and open source experience.
    * List your skills and give evidence of your qualifications.  Convince us that you can do the work.
    * Any published papers, successful open source projects, etc? Please tell us!
    * Please list any non-Summer-of-Code plans you have for the Summer, especially employment and class-taking. Be specific about schedules and time commitments.
