---
title: "ThreadGuard"
linkTitle: "ThreadGuard"
weight: 6
aliases: [
"/documentation/ja/support_classes/",
"/ja/documentation/support_packages/thread_guard/",
"/ja/documentation/webdriver/additional_features/thread_guard/"
]
---

{{% pageinfo color="info" %}}
<p class="lead">
  このクラスは、Javaバインディングでのみ使用可能です。
</p>
{{% /pageinfo %}}

ThreadGuardは、ドライバーが、それを作成した同じスレッドからのみ呼び出されることを確認します。
特に並行してテストを実行する場合のスレッドの問題は、不可解でエラーの診断が難しい場合があります。
このラッパーを使用すると、このカテゴリのエラーが防止され、発生時に例外が発生します。

次の例は、スレッドの衝突をシミュレートします。
```java
public class DriverClash {
  //thread main (id 1) created this driver
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver());

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }

  //Thread-1 (id 24) is calling the same driver causing the clash to happen
  Runnable r1 = () -> {protectedDriver.get("https://selenium.dev");};
  Thread thr1 = new Thread(r1);

  void runThreads(){
    thr1.start();
  }

  public static void main(String[] args) {
    new DriverClash().runThreads();
  }
}
```

結果は以下のとおりです。
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
下記例を参照してください。

 * `protectedDriver` はメインスレッドで作成されます
 *  Java `Runnable`を使用して新しいプロセスを起動し、新しい`スレッド`を使用してプロセスを実行します
 *  メインスレッドのメモリに`protectedDriver`がないため、両方の`スレッド`が衝突します。
 * `ThreadGuard.protect`は例外をスローします。

#### 注意:

これは、並列実行時にドライバーを管理するために `ThreadLocal`を使用する必要性を置き換えるものではありません。
