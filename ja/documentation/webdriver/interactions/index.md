# ブラウザのインタラクション

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/

---

## ブラウザーの情報

### タイトルの取得

ブラウザーから現在のページタイトルを読むことができます。

**Java**

```java
String title = driver.getTitle();
```

**Python**

```python
title = driver.title
```

**CSharp**

```csharp
String title = driver.Title;
```

**Ruby**

```ruby
it 'gets the current title' do
```

**JavaScript**

```javascript
let title = await driver.getTitle();
```

**Kotlin**

```kotlin
driver.title
```

### 現在のURLを取得

ブラウザーのアドレスバーから現在のURLを読むには、次を使用します。

**Java**

```java
String url = driver.getCurrentUrl();
```

**Python**

```python
url = driver.current_url
```

**CSharp**

```csharp
String url = driver.Url;
```

**Ruby**

```ruby
it 'gets the current url' do
```

**JavaScript**

```javascript
let currentUrl = await driver.getCurrentUrl();
```

**Kotlin**

```kotlin
driver.currentUrl
```

---

Section pages:

- [ブラウザー ナビゲーション](https://www.selenium.dev/ja/documentation/webdriver/interactions/navigation/index.md)
- [JavaScript アラート、プロンプトおよび確認](https://www.selenium.dev/ja/documentation/webdriver/interactions/alerts/index.md)
- [クッキーの使用](https://www.selenium.dev/ja/documentation/webdriver/interactions/cookies/index.md)
- [IFrame と Frame の操作](https://www.selenium.dev/ja/documentation/webdriver/interactions/frames/index.md)
- [Print Page](https://www.selenium.dev/ja/documentation/webdriver/interactions/print_page/index.md)
- [ウィンドウとタブの操作](https://www.selenium.dev/ja/documentation/webdriver/interactions/windows/index.md)
- [Virtual Authenticator](https://www.selenium.dev/ja/documentation/webdriver/interactions/virtual_authenticator/index.md): A representation of the Web Authenticator model.
