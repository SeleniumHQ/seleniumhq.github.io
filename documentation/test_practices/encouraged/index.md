# Encouraged behaviors

> Some guidelines and recommendations on testing from the Selenium project.

URL: https://www.selenium.dev/documentation/test_practices/encouraged/
Site index: https://www.selenium.dev/llms.txt

---

A note on "Best Practices": We've intentionally avoided the phrase "Best
Practices" in this documentation. No one approach works for all situations.
We prefer the idea of "Guidelines and Recommendations". We encourage
you to read through these and thoughtfully decide what approaches
will work for you in your particular environment.

Functional testing is difficult to get right for many reasons.
As if application state, complexity, and dependencies do not make testing difficult enough,
dealing with browsers (especially with cross-browser incompatibilities)
makes writing good tests a challenge.

Selenium provides tools to make functional user interaction easier,
but does not help you write well-architected test suites.
In this chapter we offer advice, guidelines, and recommendations
on how to approach functional web page automation.

This chapter records software design patterns popular
amongst many of the users of Selenium
that have proven successful over the years.

---

Section pages:

- [Page object models](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/index.md)
- [Domain specific language](https://www.selenium.dev/documentation/test_practices/encouraged/domain_specific_language/index.md)
- [Generating application state](https://www.selenium.dev/documentation/test_practices/encouraged/generating_application_state/index.md)
- [Mock external services](https://www.selenium.dev/documentation/test_practices/encouraged/mock_external_services/index.md)
- [Improved reporting](https://www.selenium.dev/documentation/test_practices/encouraged/improved_reporting/index.md)
- [Avoid sharing state](https://www.selenium.dev/documentation/test_practices/encouraged/avoid_sharing_state/index.md)
- [Tips on working with locators](https://www.selenium.dev/documentation/test_practices/encouraged/locators/index.md): When to use which locators and how best to manage them in your code.
- [Test independency](https://www.selenium.dev/documentation/test_practices/encouraged/test_independency/index.md)
- [Consider using a fluent API](https://www.selenium.dev/documentation/test_practices/encouraged/consider_using_a_fluent_api/index.md)
- [Fresh browser per test](https://www.selenium.dev/documentation/test_practices/encouraged/fresh_browser_per_test/index.md)
