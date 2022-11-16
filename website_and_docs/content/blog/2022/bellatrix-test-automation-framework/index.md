---
title: "BELLATRIX Test Automation Framework for C# and JAVA"
linkTitle: "BELLATRIX Test Automation Framework for C# and JAVA"
date: 2022-11-16
tags: ["webdriver", "framework", "ecosystem", "java", "dotnet"]
categories: ["technical", "general"]
author: Anton Angelov ([@angelovstanton](https://www.linkedin.com/in/angelovstanton/))
description: >
  Customize and extend BELLATRIX, a cross-platform .NET 6 and JAVA test automation framework to perfectly fit your needs.
  Start on top of hundreds of best practices features and integrations.
---
Over the last decade, a large ecosystem of Open Source projects has sprouted up around Selenium. Selenium is often used for automating web applications for testing purposes, but it does not include a testing framework.
Nowadays, Selenium Ecosystem initiatives try to give popularity to popular open-source test automation frameworks maintained by people outside of the core Selenium maintainers.
One of these frameworks is BELLATRIX, invented by [Anton Angelov](https://www.linkedin.com/in/angelovstanton/). It has two versions - C# and Java.
A testing framework is an abstraction in which common code provides generic functionality (which can be selectively overridden) for testing different aspects of our applications- UI, API, security, performance, and many others.

## BELLATRIX Test Automation Framework ##
The first version of **[BELLATRIX](https://bellatrix.solutions/)** appeared on 26 December 2017. It was available only for C# initially, but written on the new back then .NET Core, allowing the framework to be used on all major operating systems (cross-platform).
One huge advantage of BELLATRIX is its cross-technology readiness. It allows you to write tests for different technologies such as Web, Mobile, Desktop, and API. In BELLATRIX, we strive for the API for all modules to be as identical as possible.

The usage is simple. We suggest cloning BELLATRIX as a GIT sub-module. Then, any customizations, tests, and project-specific plug-ins should be placed in a project outside the BELLATRIX cloned repository. This way, you can quickly update to the latest version.

[**BELLATRIX official website, download and releases info**](https://bellatrix.solutions/)

[**BELLATRIX official C# GitHub Page**](https://github.com/AutomateThePlanet/BELLATRIX)

[**BELLATRIX official Java GitHub Page**](https://github.com/AutomateThePlanet/BELLATRIX-Java)

[**BELLATRIX C# Documentation**](https://docs.bellatrix.solutions/overview/)

[**BELLATRIX Java Documentation**](https://docs.java.bellatrix.solutions/overview/)

Let's investigate how easy it is to create your first test with BELLATRIX in 15 minutes. The sample will showcase how to create a very basic test login into a website:
{{< figure src="login-form.png" class="img-responsive w-50" >}}

1. Open the **BellatrixTestFramework.sln**

{{< figure src="open_the_sln.png" class="img-responsive w-50" >}}

2. Under the **starthere** folder, find the project you prefer: web, mobile, desktop, API

{{< figure src="bellatrix-projects-structure.png" class="img-responsive w-50" >}}

3. Open the **BellatrixLoginTest.cs** file. There you will find a sample test automating the login. 

```csharp
[TestClass]
public class LoginTestsMSTest : MSTest.WebTest
{
    public override void TestInit()
    {
       App.Navigation.Navigate("http://demos.bellatrix.solutions/my-account/");
    }

    [TestMethod]
    public void SuccessfullyLoginToMyAccount()
    {
        var userNameField = App.Components.CreateById<TextField>("username");
        var passwordField = App.Components.CreateById<Password>("password");
        var loginButton = App.Components.CreateByXpath<Button>("//button[@name='login']");

        userNameField.SetText("info@yourverybusywebsite.com");
        passwordField.SetPassword("yourverysecretp4ssw0rd$");
        loginButton.Click();

        var myAccountContentDiv = App.Components.CreateByClass<Div>("woocommerce-MyAccount-content");
        myAccountContentDiv.ValidateInnerTextContains("Hello John");

        var logoutLink = App.Components.CreateByInnerTextContaining<Anchor>("Log out");

        logoutLink.ValidateIsVisible();
        logoutLink.Click();
    }
}
```
All available services are available through the main **App** class. The **Components** property provides various **Create** methods for finding elements. They are generic, so you need to mention the type of the searched element. We have different elements because, for each of them, BELLATRIX offers various additional methods and assertions on top of native WebDriver methods.
The sample code uses MSTest as the default test framework, but by changing the attributes, it will also work out of the box for NUnit. Of course, you need to change the base class namespace too.

## Why BELLATRIX? ##
Let's quickly list some of the essential things the framework brings to the table:

### Multiple Test Environments Configuration
Every aspect of the framework can be controlled via a rich JSON configuration designed to work for many test environments. [**Web Project Configuration**](https://docs.bellatrix.solutions/web-automation/control-browser/#configuration).

### Customization

One of the hardest things to develop is to allow these generic frameworks to be extendable and customizable. Knowing how essential customization is, we utilize different ways to achieve it. The major one is about [**writing your own plug-ins**](https://bellatrix.solutions/features/web/extend-the-framework-to-fit-your-needs/).

### Test Reliability

One of the biggest problems in test automation is handling timeouts and performing actions on elements that may not be on the page right now. BELLATRIX hides the complexity of searching and [**waiting for elements**](https://bellatrix.solutions/features/web/boost-test-reliability/). Furthermore, when you perform an action or assertion against an element, we guarantee that once returned, it will be present.

A significant part of your tests are the assertions - checking whether some conditions are met. To handle such scenarios, we created elements **Validate** methods. They internally handle the whole complexity of waiting for some condition to happen.

```csharp
updateCart.ValidateIsDisabled();
totalSpan.ValidateInnerTextIs("120.00€", timeout: 30, sleepInterval: 2);
messageAlert.ValidateIsNotVisible();
```

### Complex Controls

BELLATRIX provides API that makes handling HTML tables and grids much easier **[HTML tables and grids](https://docs.bellatrix.solutions/web-automation/complex-components/)**. 
{{< figure src="grid-html-example.png" class="img-responsive w-50" >}}
Here is an example for asserting grid cells:

```csharp
TestGrid.ForEachCell(cell => cell.AssertFontSize("14px"));
TestGrid.GetCell("Firstname", 1).ValidateInnerTextIs("Mary");
TestGrid.GetCell(0, 1).ValidateInnerTextIs("John");
TestGrid.GetCell<Employee>(cell => cell.PersonalEmail, 1).ValidateInnerTextIs("mary@hotmail.com");
```
There is much more complex stuff that you can do with both components so check the **[official documentation](https://docs.bellatrix.solutions/web-automation/complex-components/)**.

## Integrations ##
Seamlessly integrate the framework with your existing tools and processes. Execute tests in the clouds, distribute and publish test results in reporting solutions. Maybe the most significant differentiators of the framework are its many integrations with popular tools such as Jira/qTest/Allure/ReportPortal and clouds such as AWS, Microsoft Azure, + many more. All these integrations leverage BELLATRIX plug-in architecture. For example, we have plug-ins for automatically generating/updating test cases based on our automated tests in qTest and AzureDevops or similarly creating automatic bug reports with steps to reproduce in Jira or Azure.

### Dynamic Test Cases

Dynamic test cases are a unique feature in BELLATRIX, where the framework automatically generates test cases in a popular test case management system based on your automated tests. It will populate the title, description, and other necessary properties automatically. Moreover, it will generate human-readable steps and expected results. The most significant benefit is that it will keep up to date with your auto-generated test cases over time, no matter what you change in your tests. It is an excellent functionality which allows the  non-technical people of your company to see what your tests are doing.

### AI Validation of PDFs and Image
Azure Computer Vision is a service that can be used to extract printed and handwritten text from images and documents with mixed languages and writing styles. In contrast, Azure Form Recognizer is an AI-powered document extraction service that understands your document. 
You are not limited to PDFs only. You can use the same feature for extracting text from complex images. BELLATRIX comes with similar functionality based on the AWS cloud.

### Email Testing
BELLATRIX offers a few utilities for email testing. There are a few scenarios where we need such integration. The first one is related to creating unique email inboxes and using  them to submit various online forms. Later, we can read the emails via the services and check the content of the emails. It might be enough to verify the content via regular C#, or in some cases, we might need to interact with the email content in the browser.

There are tons of other integrations that we use on a daily basis in many big enterprise projects, such as cloud secrets management for securely storing credentials and other secrets. 
**[All BELLATRIX Integrations](https://docs.bellatrix.solutions/product-integrations/)**

Using BELLATRIX in your projects might save months/years of effort even if you have the required programming knowledge. This way, you can quickly focus on writing automated tests for your project.
Usually, there are 4-6 major releases each year, including all bug fixes and new features. Check our **[release notes history](https://bellatrix.solutions/roadmap/release-3-6-0-0-chamaeleon/)**. The framework is fully covered with over 4000 automated tests and offers rich documentation. We make sure to merge the new feature branches only when we are sure that everything is working. If some issue emerges after a major release, it is quickly fixed within a few days.

For feature requests or bug reports, you can submit them to our GitHub repositories. If you believe that the functionality you developed can be added to the CORE framework or you have a bug fix, please submit a PR so we can discuss it and potentially merge it. For anything else, you can reach us via our **[contact us form](https://bellatrix.solutions/contact-us/)**.