---
title: "サポートクラス"
weight: 5
---

WebDriverサポートクラスは、コードのメンテナンスを簡素化するために提供されています。
ドメインオブジェクトとしてのHTML要素のモデリングを容易にする優れた抽象化を提供し、そのようなオブジェクトの使用を推論しやすくするヘルパーメソッドも提供します。
ここでは下記について学びます。

* Locator Strategies
* Events
* LoadableComponent
* ThreadGuard
* etc.

さぁ、始めましょう。

## **ThreadGuard**
{{% notice info %}}
このクラスは、Javaバインディングでのみ使用可能です。
{{% /notice %}}
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
