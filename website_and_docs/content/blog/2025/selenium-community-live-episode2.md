---
title: "Selenium Community Live - Episode 2"
linkTitle: "Selenium Community Live - Episode 2"
date: 2025-01-21
tags: ["webinar", "meetup", "talks","community"]
categories: ["webinar"]
author: <a href="https://www.linkedin.com/in/rpallavisharma/" target="_blank">Pallavi Sharma</a>
images:
description: >
  Selenium Community Live - Episode 2
---

The second episode of Selenium Community Live happened on Jan 21st, 2025, with David Burns, Selenium Project Leadership Member, Chair W3C Browser Testing and Tools Workgroup, and Head Open source and Developer Advocacy at BrowserStack. The topic of the event was Browsers, Browser Engines and why they are not the same. 

You can watch the episode here-  **<a href="https://www.youtube.com/watch?v=0W_rYPxVIgA" target="_blank">Selenium Community Live - Episode 2</a>**

**Meet the Speakers:**

1. **<a href="https://www.linkedin.com/in/theautomatedtester/" target="_blank">David Burns</a>** 


# Browser Engines vs Real Browsers: Why They're Not Equal

The Selenium community recently hosted an enlightening session with David Burns, who, shared crucial insights about browser testing that every automation engineer should understand.

## The Foundation: Web Standards Matter

David began by emphasizing the importance of web specifications, particularly the work done by the W3C Browser Testing and Tools Working Group. This group maintains three critical specifications:

- **WebDriver Classic/HTTP**: The standard WebDriver protocol we use daily
- **WebDriver BiDi**: A bidirectional protocol enabling event-driven APIs for network interception and DOM mutations
- **AT Driver**: Built on WebDriver BiDi for driving accessibility tools like screen readers

The key takeaway being, that Standards create a level playing field, but the devil is in the details. The difference between "MUST" and "SHOULD" in specifications can create significant bugs across different browser implementations.

## Real User Testing: Beyond Surface-Level Automation

One of David's most compelling points centered on the concept of "real user testing." When Selenium executes a click, it goes through the browser's backend, creating trusted events that the browser recognizes as legitimate user interactions. This is crucial for:

- Banking iframes
- Third-party authentication (like Okta)
- Any security-sensitive operations

Tools that execute events through the document (frontend) create synthetic events marked as `isTrusted: false`, which security-conscious applications will reject.

## The Headless vs Headful Reality Check

David revealed a startling discovery made by Mathias Bynens (Engineering Manager at Google): for years, Chrome's headless mode wasn't actually using the same rendering engine (Blink) as regular Chrome. It was essentially a different browser altogether.

This revelation led to the creation of **Chrome for Testing**, providing a stable, consistent testing environment that actually matches what users experience.

> "Headless and headful is not necessarily the same... it is literally apples to oranges."

## Browser Engines vs Real Browsers: The Critical Difference

Using Chromium instead of actual browsers like Chrome, Edge, Brave, or Opera might seem equivalent, but David highlighted crucial differences:

### Third-Party Cookie Handling
Different browsers handle cookies differently. Brave's privacy-focused approach differs significantly from Chrome's implementation, affecting:
- Session management
- Login/logout flows
- Cross-site functionality

### Real-World Example: Safari's IndexedDB Bug
A particularly illustrative case was Safari's IndexedDB bug that affected desktop Safari but not:
- WebKit (the engine)
- iOS Safari
- Safari Tech Preview

Testing with WebKit alone would have missed this critical bug that could break login functionality for real users.

## Mobile vs Desktop: More Than Just Screen Size

Simply resizing a desktop browser to mobile dimensions doesn't replicate mobile browsing:

### Operating System Differences
- Mobile and desktop use different operating systems
- Display rendering works differently
- Resource constraints affect performance

### Device Pixel Ratio Issues
Mobile devices have different pixel density requirements that can't be accurately simulated by browser resizing, leading to rendering inconsistencies in graphics-intensive applications.

## Risk Management: Making Informed Decisions

David's presentation wasn't about mandating specific tools but about understanding trade-offs:

### Low Risk Scenarios
- Simple web forms
- Basic functionality testing
- Limited third-party integrations

### High Risk Scenarios
- Canvas/graphics-heavy applications
- Complex authentication flows
- Mobile-specific interactions
- Security-sensitive operations

## Practical Recommendations

1. **Understand Your User Base**: Test where your users actually are
2. **Know Your Risk Profile**: Complex applications require more realistic testing environments
3. **Choose Tools Wisely**: Understand what your testing framework actually provides
4. **Stay Informed**: Browser differences evolve constantly

## Looking Forward: Selenium 5

David shared insights about Selenium's future direction:
- Continued focus on WebDriver BiDi implementation
- More "batteries included" features like Selenium Manager
- Enhanced APIs for network interception and advanced automation

The Selenium team remains committed to conservative changes, prioritizing stability while adding powerful new capabilities.

## Conclusion

David's presentation reminds us that effective testing requires understanding the nuances of web browsers and making informed decisions about our testing strategies. While convenience tools have their place, knowing when and how to test with real browsers can be the difference between catching critical bugs and shipping broken experiences to users.

The key message is clear: there's no one-size-fits-all solution, but with proper knowledge of the risks and differences between testing approaches, teams can make intelligent choices that balance practicality with coverage.

---
## Watch the Recording

Couldn’t join us live? Watch the entire episode here -
📹 Recording Link: [Watch the Event Recording on YouTube](https://www.youtube.com/watch?v=0W_rYPxVIgA)

David also runs a blog, and if you are interested in knowing internals of Selenium explore the link - 
**<a href="https://www.theautomatedtester.co.uk/blog/how-selenium-works-transport/" target="_blank">Blog By David</a>** 

Stay tuned as we bring the next! **<a href="https://www.youtube.com/@SeleniumHQProject/" target="_blank">Subscribe here to the Selenium HQ Official YouTube Channel.</a>**  
