---
title: "Selenium Community Live - Episode 1"
linkTitle: "Selenium Community Live - Episode 1"
date: 2024-12-25
tags: ["webinar", "meetup", "talks","community"]
categories: ["webinar"]
author: <a href="https://www.linkedin.com/in/rpallavisharma/" target="_blank">Pallavi Sharma</a>
images:
description: >
  Selenium Community Live - Episode 1
---

At the eve of celebration of 20 years of Selenium, the current **<a href="https://www.selenium.dev/project/structure/#plc" target="_blank">Project Leadership Committee of Selenium</a>**  decided on starting Selenium Community Live event, an idea helmed by **<a href="https://www.linkedin.com/in/maaret/" target="_blank">Maaret Pyhäjärvi</a>** . 
The first episode happened on Dec 18th, 2024: **<a href="https://www.youtube.com/watch?v=Y4tZOXGQGRQ" target="_blank">Selenium Community Live - Episode 1</a>**

**Meet the Speakers:**

1. **<a href="https://www.linkedin.com/in/jimevansmusic/" target="_blank">Jim Evans</a>** 

2. **<a href="https://www.linkedin.com/in/manoj9788/" target="_blank">Manoj Kumar</a>** 


**Selenium Community Live - Episode 1**

The Selenium project recently celebrated a significant milestone - 20 years of browser automation. To mark this occasion, we hosted our first-ever Selenium Community Live event, featuring an in-depth conversation between Jim Evans, Principal Technical Advisor, Sales Force, one of Selenium's longest-standing Committer, and TLC Committee member and Manoj Kumar, Practice Leader at Cognizant, Committer to Selenium Project and PLC Committee member.

## The Evolution from Selenium to WebDriver

The distinction between Selenium and WebDriver remains a source of confusion in the community. Jim Evans clarified this important historical context: Selenium began as a browser automation project 20 years ago, while WebDriver was a separate competing project developed by Simon Stewart at Google around 15 years ago.

The breakthrough came when the founders of both projects - Jason Huggins for Selenium and Simon Stewart for WebDriver - decided to join forces. This merger kept the Selenium name while introducing WebDriver's approach under the Selenium umbrella. The project developed a standardized wire protocol that allowed one set of APIs to interact with multiple browsers using the best automation approach for each browser.

## WebDriver Becomes a W3C Standard

One of Selenium's most significant achievements was establishing WebDriver as a W3C standard. The process involved creating the Browser Testing and Tools Working Group within the W3C, bringing together major browser vendors including Google, Microsoft, Apple, and Mozilla.

The standardization process requires consensus among all working group members and mandates at least two independent implementations before a specification can advance to candidate recommendation status. Throughout this process, all discussions happen in the open, with public meetings, published minutes, and open GitHub repositories where anyone can file issues or participate in discussions.

## W3C Working Group Meetings and Collaboration

The Browser Testing and Tools Working Group conducts its work through multiple types of meetings throughout the year. The group holds regular face-to-face meetings where members from different time zones can collaborate more effectively than through remote participation alone. These in-person sessions typically occur about twice per year, allowing for more immediate feedback and intensive working sessions on complex specification issues.

Additionally, the W3C hosts an annual major gathering called TPAC (Technical Plenary and Advisory Committee sessions) every fall, where all working groups convene in one location. This event serves multiple purposes: individual working groups can conduct intensive sessions, the W3C Advisory Committee meets to handle governance matters, and cross-working group collaboration can occur. Recent TPAC meetings have been held in locations like Anaheim, California, with future meetings planned for international venues such as Japan.

For those who cannot attend in person, remote participation is available, though time zone coordination remains challenging given the global nature of the W3C membership. The meeting minutes from all sessions are meticulously recorded and published publicly, ensuring transparency and allowing the broader community to follow the progress of standards development.

This meeting structure enables the consensus-driven approach that has been fundamental to WebDriver's development, where every feature must gain agreement from all major browser vendors before inclusion in the specification.

## Addressing Common Misconceptions

### "Selenium is Outdated and Slow"

This perception stems from several factors, but it's largely a misunderstanding of Selenium's architecture. The original WebDriver protocol operates on a request-response model using HTTP, which can lead to challenges when dealing with dynamic web pages that modify the DOM at runtime.

The perceived slowness often results from improper implementation of waiting strategies. Many developers resort to hard-coded sleeps instead of using more sophisticated polling wait patterns or WebDriverWait mechanisms. When elements become stale or unavailable, Selenium raises exceptions by design - expecting developers to understand and handle the state of the page they're automating.

### "Selenium Tests are Flaky"

Test flakiness is typically not a Selenium issue but rather stems from poorly designed automation infrastructure and inadequate handling of timing-related challenges. Modern web applications with single-page architecture and JavaScript-heavy frontends require proper waiting strategies and robust automation frameworks that handle retries and polling automatically.

## Modern Frontend Development and WebDriver BiDi

Recognizing the evolution of web development, the W3C working group is actively developing WebDriver BiDi (Bidirectional), a next-generation specification that addresses many limitations of the current protocol.

WebDriver BiDi uses JSON messaging over WebSocket instead of HTTP, enabling true bidirectional communication. This allows not only the traditional command-response pattern but also real-time event notifications from the browser. For example, instead of polling to detect when a new browser window opens, applications can receive immediate notifications.

Several major projects are already showing interest in WebDriver BiDi:
- Puppeteer has committed to migrating from Chrome DevTools Protocol (CDP) to WebDriver BiDi
- Other automation tools are actively evaluating or beginning integration
- Browser vendors (Chromium, Firefox) have started implementing the specification

## The Advantage of Standards-Based Automation

Using CDP for automation limits you to Chromium-based browsers, while WebDriver BiDi is designed specifically for cross-browser automation. Browser vendors themselves maintain the automation technology, ensuring it works optimally with their browsers.

Apple consistently participates in W3C standards processes, including WebDriver and WebDriver BiDi specifications, suggesting future Safari support for the new standard.

## Future Directions for Selenium

The Selenium project is considering a "batteries included" approach, providing more built-in features while continuing to promote ecosystem projects that add value on top of Selenium's core browser automation capabilities.

The project has already introduced capabilities like network traffic capture and console log access in Selenium 4, though these features haven't received widespread attention yet. Future development will focus on better promoting existing features and expanding baseline capabilities.

While the project won't venture into machine learning or AI generation directly, the community encourages the development of complementary tools and frameworks that build upon Selenium's solid foundation.

## Getting Involved

The Selenium community maintains its welcoming and open culture that has sustained it for two decades. Community members are encouraged to participate through:

- IRC and Slack channels for discussions
- GitHub issues for contributing code and reporting bugs  
- W3C working group processes for standards development
- Community events and conferences

The project's success depends on community involvement as Jim Evans noted, "when we all pull on the oars together, we go a lot faster."

## Looking Forward

Selenium's 20-year journey from a single browser automation tool to the foundation of modern web testing demonstrates the power of open standards and community collaboration. With WebDriver BiDi on the horizon and continued innovation in the browser automation space, Selenium remains positioned to serve the testing community for decades to come.

The project's commitment to standards-based, cross-browser automation ensures that as web technologies evolve, Selenium will continue to provide reliable, vendor-neutral automation capabilities that serve the entire web development ecosystem.

---

## Links for the various projects which were discussed in the event - 


**<a href="https://www.w3.org/news-events/w3c-tpac/" target="_blank">TPAC</a>** 

**<a href="https://www.w3.org/groups/wg/browser-tools-testing/" target="_blank">W3C Browser Testing Working Group</a>** 

**<a href="https://github.com/w3c/webdriver/" target="_blank">WebDriver</a>** 

**<a href="https://github.com/w3c/webdriver-bidi/" target="_blank">WebDriver Bi-Di</a>** 

**<a href="https://github.com/webdriverbidi-net/webdriverbidi-net/" target="_blank">WebDriver Bi-Di .net implementation by Jim Evans </a>** 


## Watch the Recording the first Episode of Selenium Community Live

Couldn’t join us live? Watch the entire episode here -
📹 Recording Link: [Watch the Event Recording on YouTube](https://www.youtube.com/watch?v=Y4tZOXGQGRQ)


Stay tuned as we bring the next! **<a href="https://www.youtube.com/@SeleniumHQProject/" target="_blank">Subscribe here to the Selenium HQ Official YouTube Channel.</a>**  