---
title: "Two Factor Authentication"
linkTitle: "Two Factor Authentication"
weight: 8
aliases: [
"/documentation/en/worst_practices/two_factor_authentication/",
"/documentation/worst_practices/two_factor_authentication/"
] 
---

Two Factor Authentication (2FA) is an authorization 
mechanism where a One Time Password (OTP) is generated using "Authenticator" 
mobile apps such as "Google Authenticator", "Microsoft Authenticator" 
etc., or by SMS, e-mail to authenticate. Automating this seamlessly 
and consistently is a big challenge in Selenium. There are some ways 
to automate this process. But that will be another layer on top of our 
Selenium tests and not as secure. So, you should avoid automating 2FA.

There are few options to get around 2FA checks:

* If you want the functionality to still be tested, one option is to ask your team to create a "special token" that will work in test environment. That won't require usage of a mobile device, and will ensure the test journey is covered. 
* Disable 2FA for certain Users in the test environment, so that you can 
use those user credentials in the automation.
* Disable 2FA in your test environment.
* Disable 2FA if you login from certain IPs. That way we can configure our 
test machine IPs to avoid this.
