---
title: "Avoid sharing state"
linkTitle: "Avoid sharing state"
weight: 8
aliases: [
"/documentation/en/guidelines_and_recommendations/avoid_sharing_state/",
"/documentation/guidelines/avoid_sharing_state/"
]
---


Although mentioned in several places, it is worth mentioning again. 
We must ensure that the tests are isolated from one another.

* Do not share test data. Imagine several tests that each query the database 
for valid orders before picking one to perform an action on. Should two tests
pick up the same order you are likely to get unexpected behavior.

* Clean up stale data in the application that might be picked up by another 
test e.g. invalid order records.

* Create a new WebDriver instance per test. This helps ensure test isolation
and makes parallelization simpler.

    * If you choose [pytest](https://pytest.org/) as your test runner, this can be 
    easily done by yielding your driver in a global fixture. This way each test gets its own 
    driver instance, and you can ensure that drivers always quit after a test is finished 
    (pass or fail).
