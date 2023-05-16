---
title: "虚拟身份验证器"
linkTitle: "虚拟身份验证器"
weight: 16
description: >
    一种Web身份验证器模型的表示形式.
aliases: [
"/zh-cn/documentation/webdriver/virtual_authenticator/"
]
---

Web 应用程序可以启用基于公钥的身份验证机制（称为 Web 身份验证）以无密码方式对用户进行身份验证。
[Web 身份验证](https://www.w3.org/TR/webauthn-2/) 定义了允许用户创建公钥凭据并将其注册到身份验证器的 API。
身份验证器可以是硬件设备或软件实体，用于存储用户的公钥凭证并根据请求检索它们。

顾名思义，虚拟身份验证器模拟此类身份验证器进行测试。

## 虚拟身份验证器选项

虚拟身份验证器具有 [一组属性](https://www.w3.org/TR/webauthn-2/#sctn-automation-virtual-authenticators)。
这些属性在 Selenium 绑定中映射为 VirtualAuthenticatorOptions。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L55-L61" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L48-55" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticatorOptions.spec.js#L10-L16" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## 添加虚拟身份验证器

它使用提供的属性创建一个新的虚拟身份验证器。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L68-L73" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L63-71" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticator.spec.js#L51-L55" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 删除虚拟身份验证器

删除之前添加的虚拟身份验证器。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L86" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#80-86" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticator.spec.js#L62-L63" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 创建永久凭据

使用给定的所需凭据 [参数](https://w3c.github.io/webauthn/#sctn-automation-add-credential) 创建一个永久(有状态的)凭据。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L100-L103" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#103-107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 创建临时凭据

使用给定的所需凭据 [参数](https://w3c.github.io/webauthn/#sctn-automation-add-credential) 创建一个常驻(无状态)凭据。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L143-L145" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L145-148" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 添加凭据

向身份验证器注册凭据。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L137-L146" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L139-150" >}}   
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 获取凭据

返回身份验证者拥有的凭据列表。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L157-L171" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L162-178" >}}  
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## 删除凭据

根据传递的凭据ID从身份验证器中删除凭据。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L181-L190" >}}
{{< /tab >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L189-198" >}} 
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## 删除所有凭据

从身份验证器中删除所有凭据。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L196-L205" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L207-216" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## 设置用户验证状态

设置身份验证器是模拟用户验证成功还是失败。

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/virtual_authenticator/VirtualAuthenticatorTest.java#L211-L212" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/VirtualAuthentication/VirtualAuthenticatorTest.cs#L224-225" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}
