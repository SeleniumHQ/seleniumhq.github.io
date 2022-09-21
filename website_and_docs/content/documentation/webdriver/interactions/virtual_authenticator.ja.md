---
title: "Virtual Authenticator"
linkTitle: "Virtual Authenticator"
weight: 16
description: >
    A representation of the Web Authenticator model.
aliases: [
"/ja/documentation/webdriver/virtual_authenticator/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
    Page being translated from English to Japanese. 
    Do you speak Japanese? Help us to translate
    it by sending us pull requests!
</p>
{{% /pageinfo %}}

Web applications can enable a public key-based authentication mechanism known as Web Authentication to authenticate users in a passwordless manner. 
[Web Authentication](https://www.w3.org/TR/webauthn-2/) defines APIs that allows a user to create a public-key credential and register it with an authenticator. 
An authenticator can be a hardware device or a software entity that stores user's public-key credentials and retrieves them on request. 

As the name suggests, Virtual Authenticator emulates such authenticators for testing.

## Virtual Authenticator Options

A Virtual Authenticatior has a [set of properties](https://www.w3.org/TR/webauthn-2/#sctn-automation-virtual-authenticators).
These properties are mapped as VirtualAuthenticatorOptions in the Selenium bindings.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L72-78" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L48-55" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}


## Add Virtual Authenticator

It creates a new virtual authenticator with the provided properties.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L85-92" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L63-71" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Remove Virtual Authenticator

Removes the previously added virtual authenticator.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L101-105" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#80-86" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Create Resident Credential

Creates a resident (stateful) credential with the given required credential [parameters](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L120-123" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#103-107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Create Non-Resident Credential 

Creates a resident (stateless) credential with the given required credential [parameters](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L163-165" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L145-148" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Add Credential

Registers the credential with the authenticator. 

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L157-166" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L139-150" >}}   
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Get Credential

Returns the list of credentials owned by the authenticator.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L177-191" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L162-178" >}}  
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}


## Remove Credential

Removes a credential from the authenticator based on the passed credential id.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L201-210" >}}
{{< /tab >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L189-198" >}} 
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}


## Remove All Credentials

Removes all the credentials from the authenticator.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L216-225" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L207-216" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Set User Verified

Sets whether the authenticator will simulate success or fail on user verification.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L231-232" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L224-225" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}