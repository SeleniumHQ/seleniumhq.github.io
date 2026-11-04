# 虚拟身份验证器

> 一种Web身份验证器模型的表示形式.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/virtual_authenticator/

---

Web 应用程序可以启用基于公钥的身份验证机制（称为 Web 身份验证）以无密码方式对用户进行身份验证。
[Web 身份验证](https://www.w3.org/TR/webauthn-2/) 定义了允许用户创建公钥凭据并将其注册到身份验证器的 API。
身份验证器可以是硬件设备或软件实体，用于存储用户的公钥凭证并根据请求检索它们。

顾名思义，虚拟身份验证器模拟此类身份验证器进行测试。

## 虚拟身份验证器选项

虚拟身份验证器具有 [一组属性](https://www.w3.org/TR/webauthn-2/#sctn-automation-virtual-authenticators)。
这些属性在 Selenium 绑定中映射为 VirtualAuthenticatorOptions。

**Java**

```java
public void testVirtualOptions() {
  VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .setIsUserVerified(true)
    .setHasUserVerification(true)
    .setIsUserConsenting(true)
    .setTransport(VirtualAuthenticatorOptions.Transport.USB)
    .setProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
```

**CSharp**

```csharp
// Create virtual authenticator options
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetIsUserVerified(true)
    .SetHasUserVerification(true)
    .SetIsUserConsenting(true)
    .SetTransport(VirtualAuthenticatorOptions.Transport.USB)
    .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .SetHasResidentKey(false);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
options = VirtualAuthenticatorOptions()
options.is_user_verified = True
options.has_user_verification = True
options.is_user_consenting = True
options.transport = VirtualAuthenticatorOptions.Transport.USB
options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
options.has_resident_key = False
```

**JavaScript**

```javascript
options.setHasUserVerification(true);
options.setIsUserConsenting(true);
options.setTransport(Transport['USB']);
options.setProtocol(Protocol['U2F']);
options.setHasResidentKey(false);

assert(Object.keys(options).length === 6);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 添加虚拟身份验证器

它使用提供的属性创建一个新的虚拟身份验证器。

**Java**

```java
public void testCreateAuthenticator() {
  VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .setProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .setHasResidentKey(false);

  VirtualAuthenticator authenticator =
```

**CSharp**

```csharp
// Create virtual authenticator options
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .SetHasResidentKey(false);

// Register a virtual authenticator
((WebDriver)driver).AddVirtualAuthenticator(options);

List<Credential> credentialList = ((WebDriver)driver).GetCredentials();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
options = VirtualAuthenticatorOptions()
options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
options.has_resident_key = False

# Register a virtual authenticator
driver.add_virtual_authenticator(options)
```

**JavaScript**

```javascript
options.setProtocol(Protocol['U2F']);
options.setHasResidentKey(false);

// Register a virtual authenticator
await driver.addVirtualAuthenticator(options);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 删除虚拟身份验证器

删除之前添加的虚拟身份验证器。

**Java**

```java

```

**CSharp**

```csharp
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .SetHasResidentKey(false);

String virtualAuthenticatorId = ((WebDriver)driver).AddVirtualAuthenticator(options);

((WebDriver)driver).RemoveVirtualAuthenticator(virtualAuthenticatorId);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
options = VirtualAuthenticatorOptions()

# Register a virtual authenticator
driver.add_virtual_authenticator(options)

# Remove virtual authenticator
driver.remove_virtual_authenticator()
```

**JavaScript**

```javascript
await driver.addVirtualAuthenticator(options);
await driver.removeVirtualAuthenticator();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 创建永久凭据

使用给定的所需凭据 [参数](https://w3c.github.io/webauthn/#sctn-automation-add-credential) 创建一个永久(有状态的)凭据。

**Java**

```java

byte[] credentialId = {1, 2, 3, 4};
byte[] userHandle = {1};
Credential residentCredential = Credential.createResidentCredential(
```

**CSharp**

```csharp
byte[] credentialId = { 1, 2, 3, 4 };
byte[] userHandle = { 1 };

Credential residentCredential = Credential.CreateResidentCredential(
  credentialId, "localhost", base64EncodedPK, userHandle, 0);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
# parameters for Resident Credential
credential_id = bytearray({1, 2, 3, 4})
rp_id = "localhost"
user_handle = bytearray({1})
privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
sign_count = 0

# create a resident credential using above parameters
resident_credential = Credential.create_resident_credential(credential_id, rp_id, user_handle, privatekey, sign_count)
```

**JavaScript**

```javascript
options.setProtocol(Protocol['CTAP2']);
options.setHasResidentKey(true);
options.setHasUserVerification(true);
options.setIsUserVerified(true);

await driver.addVirtualAuthenticator(options);

let residentCredential = new Credential().createResidentCredential(
  new Uint8Array([1, 2, 3, 4]),
  'localhost',
  new Uint8Array([1]),
  Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
  0);

await driver.addCredential(residentCredential);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 创建临时凭据

使用给定的所需凭据 [参数](https://w3c.github.io/webauthn/#sctn-automation-add-credential) 创建一个常驻(无状态)凭据。

**Java**

```java

byte[] credentialId = {1, 2, 3, 4};
Credential nonResidentCredential = Credential.createNonResidentCredential(
```

**CSharp**

```csharp
byte[] credentialId = { 1, 2, 3, 4 };

Credential nonResidentCredential = Credential.CreateNonResidentCredential(
  credentialId, "localhost", base64EncodedEC256PK, 0);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
# parameters for Non Resident Credential
credential_id = bytearray({1, 2, 3, 4})
rp_id = "localhost"
privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
sign_count = 0

# create a non resident credential using above parameters
credential = Credential.create_non_resident_credential(credential_id, rp_id, privatekey, sign_count)
```

**JavaScript**

```javascript
let nonResidentCredential = new Credential().createNonResidentCredential(
  new Uint8Array([1, 2, 3, 4]),
  'localhost',
  Buffer.from(base64EncodedPK, 'base64').toString('binary'),
  0);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 添加凭据

向身份验证器注册凭据。

**Java**

```java
public void testCreateAndAddNonResidentialKey() {
  VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .setProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .setHasResidentKey(false);

  VirtualAuthenticator authenticator = ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(options);

  byte[] credentialId = {1, 2, 3, 4};
  Credential nonResidentCredential = Credential.createNonResidentCredential(
    credentialId, "localhost", ec256PrivateKey, /*signCount=*/0);
```

**CSharp**

```csharp
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
    .SetHasResidentKey(false);

((WebDriver)driver).AddVirtualAuthenticator(options);

byte[] credentialId = { 1, 2, 3, 4 };

Credential nonResidentCredential = Credential.CreateNonResidentCredential(
  credentialId, "localhost", base64EncodedEC256PK, 0);

((WebDriver)driver).AddCredential(nonResidentCredential);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
driver.add_credential(credential)
```

**JavaScript**

```javascript
options.setProtocol(Protocol['U2F']);
options.setHasResidentKey(false);

await driver.addVirtualAuthenticator(options);

let nonResidentCredential = new Credential().createNonResidentCredential(
  new Uint8Array([1, 2, 3, 4]),
  'localhost',
  Buffer.from(base64EncodedPK, 'base64').toString('binary'),
  0);

await driver.addCredential(nonResidentCredential);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 获取凭据

返回身份验证者拥有的凭据列表。

**Java**

```java
public void testGetCredential() {
  VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .setProtocol(VirtualAuthenticatorOptions.Protocol.CTAP2)
    .setHasResidentKey(true)
    .setHasUserVerification(true)
    .setIsUserVerified(true);
  VirtualAuthenticator authenticator = ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(options);

  byte[] credentialId = {1, 2, 3, 4};
  byte[] userHandle = {1};
  Credential residentCredential = Credential.createResidentCredential(
    credentialId, "localhost", rsaPrivateKey, userHandle, /*signCount=*/0);

  authenticator.addCredential(residentCredential);
```

**CSharp**

```csharp
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetProtocol(Protocol.CTAP2)
    .SetHasResidentKey(true)
    .SetHasUserVerification(true)
    .SetIsUserVerified(true);

((WebDriver)driver).AddVirtualAuthenticator(options);

byte[] credentialId = { 1, 2, 3, 4 };
byte[] userHandle = { 1 };

Credential residentCredential = Credential.CreateResidentCredential(
  credentialId, "localhost", base64EncodedPK, userHandle, 0);

((WebDriver)driver).AddCredential(residentCredential);

List<Credential> credentialList = ((WebDriver)driver).GetCredentials();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
credential_list = driver.get_credentials()
```

**JavaScript**

```javascript
options.setProtocol(Protocol['CTAP2']);
options.setHasResidentKey(true);
options.setHasUserVerification(true);
options.setIsUserVerified(true);

await driver.addVirtualAuthenticator(options);

let residentCredential = new Credential().createResidentCredential(
  new Uint8Array([1, 2, 3, 4]),
  'localhost',
  new Uint8Array([1]),
  Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
  0);

await driver.addCredential(residentCredential);

let credentialList = await driver.getCredentials();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 删除凭据

根据传递的凭据ID从身份验证器中删除凭据。

```csharp
((WebDriver)driver).AddVirtualAuthenticator(new VirtualAuthenticatorOptions());

byte[] credentialId = { 1, 2, 3, 4 };

Credential nonResidentCredential = Credential.CreateNonResidentCredential(
  credentialId, "localhost", base64EncodedEC256PK, 0);

((WebDriver)driver).AddCredential(nonResidentCredential);

((WebDriver)driver).RemoveCredential(credentialId);
```

**Java**

```java
public void testRemoveCredential() {
  VirtualAuthenticator authenticator =
    ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(new VirtualAuthenticatorOptions());

  byte[] credentialId = {1, 2, 3, 4};
  Credential credential = Credential.createNonResidentCredential(
    credentialId, "localhost", rsaPrivateKey, 0);

  authenticator.addCredential(credential);
```

**CSharp**

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
driver.remove_credential(credential.id)
```

**JavaScript**

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 删除所有凭据

从身份验证器中删除所有凭据。

**Java**

```java
public void testRemoveAllCredentials() {
  VirtualAuthenticator authenticator =
    ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(new VirtualAuthenticatorOptions());

  byte[] credentialId = {1, 2, 3, 4};
  Credential residentCredential = Credential.createNonResidentCredential(
    credentialId, "localhost", rsaPrivateKey, /*signCount=*/0);

  authenticator.addCredential(residentCredential);
```

**CSharp**

```csharp
((WebDriver)driver).AddVirtualAuthenticator(new VirtualAuthenticatorOptions());

byte[] credentialId = { 1, 2, 3, 4 };

Credential nonResidentCredential = Credential.CreateNonResidentCredential(
  credentialId, "localhost", base64EncodedEC256PK, 0);

((WebDriver)driver).AddCredential(nonResidentCredential);

((WebDriver)driver).RemoveAllCredentials();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
driver.remove_all_credentials()
```

**JavaScript**

```javascript
await driver.addVirtualAuthenticator(options);

let nonResidentCredential = new Credential().createNonResidentCredential(
  new Uint8Array([1, 2, 3, 4]),
  'localhost',
  Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
  0);

await driver.addCredential(nonResidentCredential);
await driver.removeAllCredentials();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 设置用户验证状态

设置身份验证器是模拟用户验证成功还是失败。

**Java**

```java
public void testSetUserVerified() {
  VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
```

**CSharp**

```csharp
VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
    .SetIsUserVerified(true);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
# Create virtual authenticator options
options = VirtualAuthenticatorOptions()
options.is_user_verified = True
```

**JavaScript**

```javascript
options.setIsUserVerified(true);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
