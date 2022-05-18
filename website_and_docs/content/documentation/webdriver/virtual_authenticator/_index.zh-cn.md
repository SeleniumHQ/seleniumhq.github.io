---
title: "Virtual Authenticator"
linkTitle: "Virtual Authenticator"
weight: 16
description: >
    A representation of the Web Authenticator model.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i>
   Page being translated from
   English to Chinese. Do you speak Chinese? Help us to translate
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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions();

options.setProtocol(protocol);
options.setTransport(transport);
options.setIsUserConsenting(true);
options.setHasResidentKey(true);
options.setHasUserVerification(true);
options.setIsUserVerified(true);
  {{< /tab >}}
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


## Add Virtual Authenticator

It creates a new virtual authenticator with the provided properties.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions();
VirtualAuthenticator authenticator =
  ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(options);
  {{< /tab >}}
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

## Remove Virtual Authenticator

Removes the previously added virtual authenticator.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
((HasVirtualAuthenticator) driver).removeVirtualAuthenticator(authenticator);
  {{< /tab >}}
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

## Create Resident Credential

Creates a resident (stateful) credential with the given required credential [parameters](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
Credential credential = Credential.createResidentCredential(
    credentialId, rpId, privateKey, userHandle, signCount);
  {{< /tab >}}
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

## Create Non-Resident Credential 

Creates a resident (stateless) credential with the given required credential [parameters](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
Credential credential = Credential.createNonResidentCredential(
    credentialId, rpId, privateKey, signCount);
  {{< /tab >}}
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

## Add Credential

Registers the credential with the authenticator. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions();
VirtualAuthenticator authenticator =
    ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(options);

Credential credential = Credential.createNonResidentCredential(
  credentialId, rpId, privateKey, signCount);

authenticator.addCredential(credential);
  {{< /tab >}}
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

## Get Credential

Returns the list of credentials owned by the authenticator.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
  List<Credential> credentials = authenticator.getCredentials();
  {{< /tab >}}
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


## Remove Credential

 Removes a credential from the authenticator based on the passed credential id.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
authenticator.removeCredential(credentialId);
  {{< /tab >}}
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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
  authenticator.removeAllCredentials();
  {{< /tab >}}
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

## Set User Verified

Sets whether the authenticator will simulate success or fail on user verification.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
  // Disable user verification.
  authenticator.setUserVerified(false);

  // Enable user verification.
  authenticator.setUserVerified(true);
  {{< /tab >}}
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