---
title: "Selenium Ecosystem - BELLATRIX Test Automation Framework for C# and JAVA"
linkTitle: "Selenium Ecosystem - BELLATRIX Test Automation Framework for C# and JAVA"
date: 2022-09-01
tags: ["webdriver", "framework", "ecosystem", "java", "dotnet"]
categories: ["technical", "general"]
author: Anton Angelov ([@angelovstanton](https://www.linkedin.com/in/angelovstanton/))
description: >
  Customize and extend our cross-platform .NET 6 and JAVA test automation framework to perfectly fit your needs.
  Start on top of hundreds of best practice features and integrations.
---
Over the last decade, a large ecosystem of Open Source projects has sprouted up around Selenium. Selenium is often used for automating web applications for testing purposes, but it does not include a testing framework.
Nowadays, Selenium Ecosystem initiatives try to give popularity to popular open-source test automation frameworks maintained by people outside of core Selenium maintainers.
One of these frameworks is BELLATRIX, developed and maintained by [Automate The Planet](https://bellatrix.solutions/about/) and invented by [Anton Angelov](https://www.linkedin.com/in/angelovstanton/). It has two versions - C# and Java.
Before giving you more details about the framework, let's briefly define what a test automation framework is.

## What Is a Test Automation Framework? ##
### Definition Software Framework ###
A software framework (or just framework) is an abstraction in which common code providing generic functionality can be selectively overridden or specialized by user code providing specific functionality. Frameworks are a special case of software libraries in that they are reusable abstractions of code wrapped in a well-defined API, yet they contain some key distinguishing features that separate them from normal libraries.

Software frameworks have these distinguishing features that separate them from libraries:
- Inversion of Control - in a framework, unlike in libraries or normal user applications, the overall program’s flow of control is not dictated by the caller, but by the framework.
- Default Behavior a framework has a default behavior. This default behavior must actually be some useful behavior and not a series of no-ops.
- Extensibility a framework can be extended by the user usually by selective overriding or specialized by user code providing specific functionality.
- Non-modifiable Framework Code the framework code, in general, is not allowed to be modified. Users can extend the framework, but not modify its code.

### Definition Test Framework ###
A sub-type of frameworks providing generic code for testing different aspects of our applications- UI, API, security, performance and many other.

Many people refer to Selenium WebDriver as a test automation framework but cannot be more wrong. Here is the official definition - "*Selenium automates browsers. That’s it! What you do with that power is entirely up to you. Primarily, it is for automating web applications for testing purposes, but is certainly not limited to just that. Boring web-based administration tasks can (and should!) be automated as well.*" So, Selenium WebDriver is mostly known as the standard for web automation testing but is actually a tool for controlling browsers that we later use in our software libraries and frameworks to write automated tests.
A framework can be based on several libraries implementing several APIs, but unlike the normal use of an API, the access to the behavior built into the framework is mediated by extending its content with new classes plugged into the framework itself. Moreover, the overall program flow of control can be out of the control of the caller and in the hands of the framework by inversion of control or a similar mechanism.

#### Definition Test Automation Framework ###
Various guidelines, coding standards, concepts, processes, practices, project hierarchies, modularity, etc. to support automated testing. The user can follow these guidelines while automating application to take advantages of various productive results.

### BELLATRIX Test Automation Framework ###
The first version of **[BELLATRIX](https://bellatrix.solutions/)** appeared on 26 December 2017. It was available only for C# initially but written on new back then .NET Core, allowing the framework to be used on all major operating systems (cross-platform).
One huge advantage of BELLATRIX is cross-technology readiness. It allows you to write tests for different technologies such as Web, Mobile, Desktop, and API. For me, this also includes a similar API. In BELLATRIX, we strive the API for different modules to be as identical as possible. Here by API, I don't mean web API.

### Definition API ###
API or application programming interface is a set of functions and procedures that allow the creation of applications which access the features or data of an operating system, application, or other service. A good API makes it easier to develop a computer program by providing all the building blocks, which are then put together by the programmer.

###Multiple Test Environments Configuration###
Every aspect of the framework can be controlled via a rich JSON configuration designed to work for many test environments. Here is just a short part responsible for controlling web execution:

```json
"webSettings": {
  "isParallelExecutionEnabled": "false",
  "artificialDelayBeforeAction": "0",
  "automaticallyScrollToVisible": "false",
  "waitUntilReadyOnElementFound": "false",
  "waitForAngular": "false",
  "shouldHighlightElements": "true",
  "shouldCaptureHttpTraffic": "false",
  "pathToSslCertificate": "path",
  "shouldCheckForJavaScriptErrors": "false",
  "timeoutSettings": {
    "elementWaitTimeout": "30",
    "pageLoadTimeout": "30",
    "scriptTimeout": "1",
    "waitForAjaxTimeout": "30",
    "sleepInterval": "1",
    "waitUntilReadyTimeout": "30",
    "waitForJavaScriptAnimationsTimeout": "30",
    "waitForAngularTimeout": "30",
    "waitForPartialUrl": "30",
    "validationsTimeout": "30",
    "elementToBeVisibleTimeout": "30",
    "elementToExistTimeout": "30",
    "elementToNotExistTimeout": "30",
    "elementToBeClickableTimeout": "30",
    "elementNotToBeVisibleTimeout": "30",
    "elementToHaveContentTimeout": "15"
  },
```

###Customization###

One of the hardest things to develop is to allow these generic frameworks to be extendable and customizable. The whole point of creating a shared library is to be used by multiple teams across the company. However, the different teams work in different contexts. They may have to test a little bit different things. So, the library code as is may not be working out of the box for them. Thus, the engineers should be able to customize some parts to fit their needs.
Knowing how essential customization is, we built several ways to enable it. The major one is about [**writing your own plug-ins**](https://bellatrix.solutions/features/web/extend-the-framework-to-fit-your-needs/).

###Test Reliability###

One of the biggest problems in test automation is handling timeouts and performing actions on elements that may not be on the page right now. In the WebDriver world, you have to constantly worry about how to solve these problems with an implicit wait, explicit wait, or WebDriverWait class. BELLATRIX hides the complexity of searching and [**waiting for elements**](https://bellatrix.solutions/features/web/boost-test-reliability/). Furthermore, when you perform an action or assertion against an element, we guarantee that once returned is there.

A significant part of your tests is the assertions - checking whether some conditions are met. For example, this might be checking whether some text is shown or whether some button has been disabled. However, this often doesn’t happen immediately. In the era of web pages with heavy JavaScript usage, most things happen asynchronously.
To handle such scenarios, we created elements Validate methods. They internally handle the whole complexity of waiting for some condition to happen.

```csharp
updateCart.ValidateIsDisabled();
```
Waits for the message alert to get disabled.
```csharp
messageAlert.ValidateIsNotVisible();
```
Wait for message alert to disappear.
```csharp
totalSpan.ValidateInnerTextIs("120.00€", timeout: 30, sleepInterval: 2);
```
You can even fine-tune the timeouts.

###Complex Controls###

BELLATRIX gives you API for easing the work with **[HTML tables and grids](https://docs.bellatrix.solutions/web-automation/complex-components/)**. 
{{< figure src="grid-html-example.png" class="img-responsive w-50" >}}
Here is an example for asserting grid cells:

```csharp
[Test]
public void AssertGridCells()
{
    TestGrid.ForEachCell(cell => cell.AssertFontSize("14px"));
    TestGrid.GetCell("Firstname", 1).ValidateInnerTextIs("Mary");
    TestGrid.GetCell(0, 1).ValidateInnerTextIs("John");
    TestGrid.GetCell<Employee>(cell => cell.PersonalEmail, 1).ValidateInnerTextIs("mary@hotmail.com");
    ComponentsList<TableCell> matchingCells = TestGrid.GetCells<TableCell>(cell => cell.InnerText.StartsWith('J'));
    Assert.AreEqual(2, matchingCells.Count());
    TestGrid.GetCell("Email Business", 0).ValidateInnerTextIs("jdoe@corp.com");
    var firstRowEmail = TestGrid.GetRow(0).GetCell("Email Personal");
    TestGrid.GetCell("Actions", 0).As<Button>().Click();
    var firstRowEmailAfterDelete = TestGrid.GetRow(0).GetCell("Email Personal");
    Assert.AreNotEqual(firstRowEmail, firstRowEmailAfterDelete);
}
```
There is much more complex stuff that you can do with both components so check the **[official documentation](https://docs.bellatrix.solutions/web-automation/complex-components/)**.

## Integrations ##
Seamlessly integrate the framework with your existing tools and processes. Execute tests in the clouds, distribute and publish test results in reporting solutions. Maybe the most significant differentiators of the framework are its many integrations with popular tools such as Jira/qTest/Allure/ReportPortal and clouds such as AWS, Microsoft Azure, + many more. All these integrations leverage BELLATRIX plug-in architecture. For example, we have plug-ins for automatically generating/updating test cases based on our automated tests in qTest and AzureDevops or similarly create automatic bug reports with steps to reproduce in Jira or Azure.

###Dynamic Test Cases###

Dynamic test cases are a unique feature in BELLATRIX, where the framework automatically generates test cases in a popular test case management system based on your automated tests. It will populate the title, description, and other necessary properties automatically. Moreover, it will generate human-readable steps and expected results. The most significant benefit is that it will keep up to date with your auto-generated test cases over time, no matter what you change in your tests. It is an excellent functionality to allow non-technical people of your company to see what your tests are doing.

{{< figure src="qtest-dynamic-test-case.png" class="img-responsive w-50" >}}

```csharp
[TestFixture]
[DynamicTestCase(SuiteId = "8260474")]
public class PageObjectsTests : WebTest
{
   [Test]
   [DynamicTestCase(
        TestCaseId = "4d001440-bf6c-4a8b-b3e6-796cbad361e1", 
        Description = "Create a purchase of a rocket through the online rocket shop http://demos.bellatrix.solutions/")]
    public void PurchaseRocketWithPageObjects()
    {
		    App.TestCases.AddPrecondition($"Navigate to http://demos.bellatrix.solutions/");
        var homePage = App.GoTo<HomePage>();
        homePage.FilterProducts(ProductFilter.Popularity);
        homePage.AddProductById(28);
        homePage.ViewCartButton.Click();

        var cartPage = App.Create<CartPage>();
        cartPage.ApplyCoupon("happybirthday");
        cartPage.ProceedToCheckout.Click();

        var billingInfo = new BillingInfo
                                    {
                                        FirstName = "In",
                                        LastName = "Deepthought",
                                        Company = "Automate The Planet Ltd.",
                                        Country = "Bulgaria",
                                        Address1 = "bul. Yerusalim 5",
                                        Address2 = "bul. Yerusalim 6",
                                        City = "Sofia",
                                        State = "Sofia-Grad",
                                        Zip = "1000",
                                        Phone = "+00359894646464",
                                        Email = "info@bellatrix.solutions",
                                        ShouldCreateAccount = true,
                                        OrderCommentsTextArea = "cool product",
                                    };

        var checkoutPage = App.Create<CheckoutPage>();
        checkoutPage.FillBillingInfo(billingInfo);
        checkoutPage.CheckPaymentsRadioButton.Click();
    }
}
```
###AI Validation of PDFs and Image###
Azure Computer Vision is a service that can be used to extract printed and handwritten text from images and documents with mixed languages and writing styles. In contrast, Azure Form Recognizer is an AI-powered document extraction service that understands your document. 

{{< figure src="sampleinvoice.png" class="img-responsive w-50" >}}
You are not limited to PDFs only. You can use the same feature for extracting text from complex images. Similar to the one below.
{{< figure src="devPortalGraph1.png" class="img-responsive w-50" >}}

In BELLATRIX, we use it to validate the layout of complex PDF documents:
```csharp
[Test]
public void MakeTextExtractionFromPDF()
{
    var textSnippets = App.ComputerVision.ExtractOCRTextFromLocalFile("sampleinvoice.pdf");
    textSnippets.ForEach(Console.WriteLine);

    List<string> expectedTextSnippets = new List<string>()
    {
        "69653 1st Point, 45 Acker Driv",
        "Subtotal",
        "$84.00",
        "Total",
        "$136.00",
    };

    App.ComputerVision.ValidateText("sampleinvoice.pdf", "en", expectedTextSnippets);
}
```
BELLATRIX comes with similar functionality based on AWS cloud.

###Email Testing###
BELLATRIX offers a few utilities for email testing. There are a few scenarios where we need such integration. The first one is related to creating unique email inboxes and using the generated individual emails to submit various online forms. Later, we can read the emails via the services and check the content of the emails. It might be enough to verify the content via regular C#, or in some cases, we might need to interact with the email content in the browser. For both cases, we offer utilities.
```csharp
var testingInbox = mailslurpService.CreateInbox("mainTestingInbox");
mailslurpService.WaitForLatestEmail(testingInbox, DateTime.Now.AddMinutes(-5));
```

There is another service class which can read email’s HTML body and load it in the current test browser. 
```csharp
EmailService.LoadEmailBody("emailHTML");
```
There are tons of other integrations that we use on a daily basis in many big enterprise projects, such as cloud secrets management for securely storing credentials and other secrets.

## BELLATRIX Getting Started ##
For a few years now, BELLATRIX has been fully open-source. The project is maintained by Automate The Planet company, so it is not a "single-person" hobby/side project. Instead, our teams are using both C# and Java versions of the framework daily, writing high-quality automated tests for one of the world's biggest enterprises and most successful start-ups.
We are not advertising the framework to sell support or sell licenses. It is mainly to grow the community and help us improve the tool. Using it in your projects might save you months/years of work even if you have the required programming knowledge. This way, you can quickly focus on writing automated tests for your project.
Usually, there are 4-6 major releases each year, bringing all bug fixes and new features. Check our **[release notes history](https://bellatrix.solutions/roadmap/release-3-6-0-0-chamaeleon/)**. The framework is fully covered with over 4000 automated tests and offers rich documentation. We make sure to merge the new feature branches only when we are sure that everything is working. If anything comes up after a major release, it is quickly fixed within a few days. 
The usage is simple. We suggest cloning BELLATRIX as a GIT sub-module. Then, any customizations, tests, and project-specific plug-ins should be placed in a project outside the BELLATRIX cloned repository. This way, you can quickly update to the latest version.

[**BELLATRIX official website, download and releases info**](https://bellatrix.solutions/)
[**BELLATRIX official C# GitHub Page**](https://github.com/AutomateThePlanet/BELLATRIX)
[**BELLATRIX official Java GitHub Page**](https://github.com/AutomateThePlanet/BELLATRIX-Java)
[**BELLATRIX C# Documentation**](https://docs.bellatrix.solutions/overview/)
[**BELLATRIX Java Documentation**](https://docs.java.bellatrix.solutions/overview/)

For feature requests or bug reports, you can submit them to our GitHub repositories. If you believe that the functionality you developed can be added to the CORE framework or you have a bug fix, please submit a PR so we can discuss it and potentially merge it. For anything else, you can reach us via our **[contact us form](https://bellatrix.solutions/contact-us/)**.  