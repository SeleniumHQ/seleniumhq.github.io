---
title: "iFrame と Frame の操作"
linkTitle: "フレーム"
weight: 6
aliases: [
"/ja/documentation/webdriver/browser/frames/"
]
---

Frameは、同じドメイン上の複数のドキュメントからサイトレイアウトを構築する非推奨の手段となりました。
HTML5以前のWebアプリを使用している場合を除き、frameを使用することはほとんどありません。
iFrameは、まったく異なるドメインからのドキュメントの挿入を許可し、今でも一般的に使用されています。

FrameまたはiFrameを使用する必要がある場合、Webdriverを使用して同じ方法で作業できます。 
iFrame内のボタンがある場合を考えてみましょう。ブラウザー開発ツールを使用して要素を検査すると、次のように表示される場合があります。

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

iFrameがなければ、次のようなボタンを使用してボタンをクリックします。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # This won't work
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

ただし、iFrameの外側にボタンがない場合は、代わりにno such elementエラーが発生する可能性があります。
これは、Seleniumがトップレベルのドキュメントの要素のみを認識するために発生します。
ボタンを操作するには、ウィンドウを切り替える方法と同様に、最初にFrameに切り替える必要があります。
WebDriverは、Frameに切り替える3つの方法を提供します。

## WebElementを使う

WebElementを使用した切り替えは、最も柔軟なオプションです。好みのセレクタを使用してFrameを見つけ、それに切り替えることができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

    # switch to selected iframe
driver.switch_to.frame(iframe)

    # Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

    # Switch to the frame
driver.switch_to.frame iframe

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## nameまたはIDを使う

FrameまたはiFrameにidまたはname属性がある場合、代わりにこれを使うことができます。
名前またはIDがページ上で一意でない場合、最初に見つかったものに切り替えます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Switch frame by id
driver.switch_to.frame('buttonframe')

    # Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch by ID
driver.switch_to.frame 'buttonframe'

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## インデックスを使う

JavaScriptの _window.frames_ を使用して照会できるように、Frameのインデックスを使用することもできます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch to the second frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switching to second iframe based on index
iframe = driver.find_elements(By.TAG_NAME,'iframe')[1]

    # switch to selected iframe
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


## Frameを終了する

iFrameまたはFrameセットを終了するには、次のようにデフォルトのコンテンツに切り替えます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switch back to default content
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Return to the top level
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}
