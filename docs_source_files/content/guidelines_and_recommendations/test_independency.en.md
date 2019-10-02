---
title: "Test independency"
weight: 7
---

Write each test as its own unit. Write the tests in a way that will not be
reliant on other tests to complete:

Let us say there is a content management system with which you can create
some custom content which then appears on your website as a module after 
publishing, and it may take some time to sync between the CMS and the 
application.

A wrong way of testing your module is that the content is created and 
published in one test, and then checking the module in another test. This 
is not feasible as the content may not be available immediately for the 
other test after publishing.

Instead, you can create a stub content which can be turned on and off 
within the affected test, and use that for validating the module. However,
for content creation, you can still have a separate test.
