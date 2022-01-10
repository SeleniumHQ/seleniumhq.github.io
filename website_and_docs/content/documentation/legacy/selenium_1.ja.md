---
title: "Selenium RC (Selenium 1)"
linkTitle: "Selenium 1"
weight: 2
description: >
    The original version of Selenium
aliases: [
"/documentation/ja/legacy_docs/selenium_rc/",
"/ja/documentation/legacy/selenium_rc/"
]
---


## 紹介
Selenium RCは長い間メインのSeleniumプロジェクトでしたが、WebDriver/Seleniumを併合したより強力なツールであるSelenium 2が登場しました。
Selenium 1はもうサポートされていないことを強調する価値があります。

## Selenium RCの仕組み
はじめに、Selenium RCのコンポーネントがどのように動作するか、およびテストスクリプトの実行でそれぞれが果たす役割について説明します。

### RCコンポーネント

SeleniumRCコンポーネントは、以下のとおりです。

* ブラウザを起動および終了し、テストプログラムから渡されたSeleneseコマンドを解釈および実行し、ブラウザとAUTの間で渡されるHTTPメッセージをインターセプトおよび検証するSeleniumサーバー
* 各プログラミング言語とSelenium RC Server間のインターフェイスを提供するクライアントライブラリ

これは簡略化されたアーキテクチャ図です。

![簡略化されたアーキテクチャ図](/images/documentation/legacy/selenium_rc_architecture_diagram_simple.png) 

この図は、クライアントライブラリが実行される各Seleniumコマンドを渡すサーバーと通信することを示しています。
次に、サーバーはSelenium-Core JavaScriptコマンドを使用してSeleniumコマンドをブラウザーに渡します。
ブラウザは、JavaScriptインタープリターを使用して、Seleniumコマンドを実行します。
これにより、テストスクリプトで指定したSeleneseアクションまたは検証が実行されます。

### Seleniumサーバー

Seleniumサーバーは、テストプログラムからSeleniumコマンドを受信して解釈し、それらのテストの実行結果をプログラムに報告します。

RCサーバーはSelenium Coreをバンドルし、ブラウザーに自動的に挿入します。
これは、テストプログラムがブラウザを開いたときに発生します（クライアントライブラリのAPI関数を使用）。
Selenium-CoreはJavaScriptプログラムです。
実際には、ブラウザの組み込みJavaScriptインタープリターを使用してSeleneseコマンドを解釈および実行するJavaScript関数のセットです。

サーバーは、単純なHTTP GET/POSTリクエストを使用して、テストプログラムからSeleneseコマンドを受け取ります。
これは、HTTPリクエストを送信できるプログラミング言語を使用して、ブラウザーでのSeleniumテストを自動化できることを意味します。

### クライアントライブラリ

クライアントライブラリは、独自の設計のプログラムからSeleniumコマンドを実行できるプログラミングサポートを提供します。
サポートされる言語ごとに異なるクライアントライブラリがあります。
Seleniumクライアントライブラリは、プログラミングインターフェイス（API）、つまり、独自のプログラムからSeleniumコマンドを実行する一連の関数を提供します。
各インターフェイス内には、各Seleneseコマンドをサポートするプログラミング関数があります。

クライアントライブラリは、Seleneseコマンドを受け取り、それをSeleniumサーバーに渡して、特定のアクションまたはテスト対象アプリケーション（AUT）に対するテストを処理します。
クライアントライブラリは、そのコマンドの結果も受け取り、プログラムに返します。
プログラムは結果を受け取ってプログラム変数に保存し、成功または失敗として報告するか、予期しないエラーの場合は修正アクションを実行できます。

したがって、テストプログラムを作成するには、クライアントライブラリAPIを使用して一連のSeleniumコマンドを実行するプログラムを作成するだけです。
また、オプションで、Selenium-IDEでSeleneseテストスクリプトを既に作成している場合は、Selenium RCコードを生成できます。
Selenium-IDEは、（エクスポートメニュー項目を使用して）SeleniumコマンドをクライアントドライバーのAPI関数呼び出しに変換できます。
Selenium-IDEからRCコードをエクスポートする詳細については、Selenium-IDEの章を参照してください。

## インストール

インストールというのは、Seleniumの誤った呼び名です。
Seleniumには、選択したプログラミング言語で利用可能な一連のライブラリがあります。
[ダウンロードページ](https://selenium.dev/downloads/)からダウンロードできます。

使用する言語を選択したら、次のことを行う必要があります。

* Selenium RCサーバーをインストールします。
* 言語固有のクライアントドライバーを使用してプログラミングプロジェクトをセットアップします。

### Seleniumサーバーのインストール

Selenium RCサーバーは単なるJava jarファイル（selenium-server-standalone-&lt;version-number&gt;.jar）であり、特別なインストールは不要です。
zipファイルをダウンロードして、目的のディレクトリにサーバーを展開するだけで十分です。

### Seleniumサーバーを実行する

テストを開始する前に、サーバーを起動する必要があります。
Selenium RCのサーバーがあるディレクトリに移動し、コマンドラインコンソールから次を実行します。

```shell
    java -jar selenium-server-standalone-<version-number>.jar
```

これは、上記のコマンドを含むバッチまたはシェル実行可能ファイル（Windowsでは.bat、Linuxでは.sh）を作成することで簡素化できます。
次に、デスクトップ上でその実行可能ファイルへのショートカットを作成し、アイコンをダブルクリックしてサーバーを起動します。

サーバーを実行するには、Javaをインストールし、PATH環境変数をコンソールから実行するように正しく構成する必要があります。
コンソールで次を実行すると、Javaが正しくインストールされていることを確認できます。

```shell
       java -version
```

バージョン番号（1.5以降である必要があります）を取得したら、Selenium RCの使用を開始できます。

### Javaクライアントドライバーの使用

* SeleniumHQ[ダウンロードページ](https://selenium.dev/downloads/)からSelenium Javaクライアントドライバーのzipファイルをダウンロードします。
* selenium-java-&lt;version-number&gt; .jarファイルを解凍します。
* Open your desired Java IDE (Eclipse, NetBeans, IntelliJ, Netweaver, etc.)
* 希望するJava IDE（Eclipse、NetBeans、IntelliJ、Netweaverなど）を開きます。
* Javaプロジェクトを作成します。
* selenium-java-&lt;version-number&gt;.jarファイルをプロジェクトの参照先に追加します。
* selenium-java-&lt;version-number&gt;.jarファイルをプロジェクトのクラスパスに追加します。
* Selenium-IDEから、スクリプトをJavaファイルにエクスポートしてJavaプロジェクトに含めるか、selenium-java-client APIを使用してJavaでSeleniumテストを記述します。
APIについては、この章の後半で説明します。
JUnitまたはTestNgを使用してテストを実行するか、独自の単純なmain()プログラムを作成できます。
これらの概念については、このセクションの後半で説明します。
* コンソールからSeleniumサーバーを実行します。
* Java IDEまたはコマンドラインからテストを実行します。

Javaテストプロジェクトの設定詳細については、付録セクションの「EclipseでのSelenium RCの設定」および「IntellijでのSelenium RCの設定」を参照してください。

### Pythonクライアントドライバーの使用 

* SeleniumをPIP経由でインストールします。手順はSeleniumHQ[ダウンロードページ](https://selenium.dev/downloads/)にリンクされています。
* SeleniumテストをPythonで作成するか、Selenium-IDEからPythonファイルにスクリプトをエクスポートします。
* コンソールからSeleniumサーバーを実行します。
* コンソールまたはPython IDEからテストを実行します。

Pythonクライアントドライバーの設定詳細については、付録「Pythonクライアントドライバーの設定」を参照してください。

### .NETクライアントドライバーの使用

* SeleniumHQ[ダウンロードページ](https://selenium.dev/downloads/)からSelenium RCをダウンロードします。
* フォルダーを解凍します。
* [NUnit](https://nunit.org/download/)をダウンロードしてインストールします。
（注：テストエンジンとしてNUnitを使用できます。
NUnitにまだ慣れていない場合は、簡単なmain()関数を作成してテストを実行することもできますが、NUnitはテストエンジンとして非常に便利です。）
* 希望した.Net IDE（Visual Studio、SharpDevelop、MonoDevelop）を開きます。
* クラスライブラリ(.dll)を作成します。
* 次のDLLへの参照を追加します。
nmock.dll、nunit.core.dll、nunit、framework.dll、ThoughtWorks.Selenium.Core.dll、ThoughtWorks.Selenium.IntegrationTests.dll、ThoughtWorks.Selenium.UnitTests.dll
* Seleniumテストを.Net言語(C#、VB.Net)で記述するか、Selenium-IDEからC#ファイルにスクリプトをエクスポートし、このコードを作成したクラスファイルにコピーします。
* 独自の単純なmain()プログラムを作成するか、テストを実行するためにプロジェクトにNUnitを含めることができます。
これらの概念については、この章の後半で説明します。
* コンソールからSeleniumサーバーを実行します。
* IDE、NUnit GUI、またはコマンドラインからテストを実行します。

Visual Studioを使用した.NETクライアントドライバーの設定詳細については、付録の.NETクライアントドライバー設定を参照してください。

### Rubyクライアントドライバーの使用

* RubyGemsがまだない場合は、RubyForgeからインストールします。
* 次のコマンドを実行します。 ``gem install selenium-client``
* テストスクリプトの上部に次の行を追加します。 ``require "selenium/client"``
* Rubyテストハーネス(Test :: Unit、Mini :: Test、RSpecなど)を使用してテストスクリプトを記述します。
* コンソールからSeleniumサーバーを実行します。
* 他のRubyスクリプトを実行するのと同じ方法でテストを実行します。

Rubyクライアントドライバーの構成の詳細については、`Selenium-Clientのドキュメント` を参照してください。

## Seleneseからプログラムへ

Selenium RCを使用する主なタスクは、Seleneseをプログラミング言語に変換することです。
このセクションでは、言語固有の例をいくつか示します。

### サンプルテストスクリプト

Seleneseテストスクリプトの例から始めましょう。
Selenium-IDEで次のテストを記録することを想像してください。

|                    |                               |             |
| --------           | ----------------------------  | ----------- |
| open               | /                             |             |
| type               | q                             | selenium rc |
| clickAndWait       | btnG                          |             |
| assertTextPresent  | Results * for selenium rc     |             |

注：この例は、Google検索ページ http://www.google.com で機能します。

### プログラミングコードとしてのSelenese

サポートされている各プログラミング言語に(Selenium-IDE経由で)エクスポートされたテストスクリプトを次に示します。
オブジェクト指向プログラミング言語の少なくとも基本的な知識がある場合は、これらの例のいずれかを読むことで、SeleniumがSeleneseコマンドを実行する方法を理解できます。
特定の言語の例を表示するには、これらのボタンのいずれかを選択します。

#### CSharp
``` csharp

        using System;
        using System.Text;
        using System.Text.RegularExpressions;
        using System.Threading;
        using NUnit.Framework;
        using Selenium;

        namespace SeleniumTests
        {
            [TestFixture]
            public class NewTest
            {
                private ISelenium selenium;
                private StringBuilder verificationErrors;
                
                [SetUp]
                public void SetupTest()
                {
                    selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
                    selenium.Start();
                    verificationErrors = new StringBuilder();
                }
                
                [TearDown]
                public void TeardownTest()
                {
                    try
                    {
                        selenium.Stop();
                    }
                    catch (Exception)
                    {
                        // Ignore errors if unable to close the browser
                    }
                    Assert.AreEqual("", verificationErrors.ToString());
                }
                
                [Test]
                public void TheNewTest()
                {
                    selenium.Open("/");
                    selenium.Type("q", "selenium rc");
                    selenium.Click("btnG");
                    selenium.WaitForPageToLoad("30000");
                    Assert.AreEqual("selenium rc - Google Search", selenium.GetTitle());
                }
            }
        }

```

#### Java

```java 
      
	  /** Add JUnit framework to your classpath if not already there 
	   *  for this example to work
	  */
      package com.example.tests;

      import com.thoughtworks.selenium.*;
      import java.util.regex.Pattern;

      public class NewTest extends SeleneseTestCase {
          public void setUp() throws Exception {
              setUp("http://www.google.com/", "*firefox");
          }
            public void testNew() throws Exception {
                selenium.open("/");
                selenium.type("q", "selenium rc");
                selenium.click("btnG");
                selenium.waitForPageToLoad("30000");
                assertTrue(selenium.isTextPresent("Results * for selenium rc"));
          }
      }

```

#### Php

```php
      <?php

      require_once 'PHPUnit/Extensions/SeleniumTestCase.php';

      class Example extends PHPUnit_Extensions_SeleniumTestCase
      {
        function setUp()
        {
          $this->setBrowser("*firefox");
          $this->setBrowserUrl("http://www.google.com/");
        }

        function testMyTestCase()
        {
          $this->open("/");
          $this->type("q", "selenium rc");
          $this->click("btnG");
          $this->waitForPageToLoad("30000");
          $this->assertTrue($this->isTextPresent("Results * for selenium rc"));
        }
      }
      ?>

```

#### Python

```python

     from selenium import selenium
      import unittest, time, re

      class NewTest(unittest.TestCase):
          def setUp(self):
              self.verificationErrors = []
              self.selenium = selenium("localhost", 4444, "*firefox",
                      "http://www.google.com/")
              self.selenium.start()
         
          def test_new(self):
              sel = self.selenium
              sel.open("/")
              sel.type("q", "selenium rc")
              sel.click("btnG")
              sel.wait_for_page_to_load("30000")
              self.failUnless(sel.is_text_present("Results * for selenium rc"))
         
          def tearDown(self):
              self.selenium.stop()
              self.assertEqual([], self.verificationErrors)

```

#### Ruby

```ruby

      require "selenium/client"
      require "test/unit"

      class NewTest < Test::Unit::TestCase
        def setup
          @verification_errors = []
          if $selenium
            @selenium = $selenium
          else
            @selenium =  Selenium::Client::Driver.new("localhost", 4444, "*firefox", "http://www.google.com/", 60);
            @selenium.start
          end
          @selenium.set_context("test_new")
        end

        def teardown
          @selenium.stop unless $selenium
          assert_equal [], @verification_errors
        end

        def test_new
          @selenium.open "/"
          @selenium.type "q", "selenium rc"
          @selenium.click "btnG"
          @selenium.wait_for_page_to_load "30000"
          assert @selenium.is_text_present("Results * for selenium rc")
        end
      end

```

次のセクションでは、生成されたコードを使用してテストプログラムを構築する方法を説明します。

## テストをプログラミングする

次に、サポートされている各プログラミング言語の例を使用して、独自のテストをプログラミングする方法を説明します。
基本的に2つのタスクがあります。

* Selenium-IDEからスクリプトをプログラミング言語に生成し、必要に応じて結果を変更します。 
* 生成されたコードを実行する非常に単純なmainプログラムを記述します。

必要に応じて、JUnitまたはJava用のTestNG、またはこれらの言語のいずれかを使用している場合は.NET用のNUnitなどのテストエンジンプラットフォームを採用できます。

ここでは、言語固有の例を示します。
言語固有のAPIはそれぞれ異なっている傾向があるため、それぞれに個別の説明があります。

* Java
* C#
* Python
* Ruby
* Perl, PHP


### Java

Javaの場合、テストエンジンとしてJUnitまたはTestNGを使用します。
Eclipseは、プラグインを介してこれらを直接サポートしています。
これにより、さらに簡単になります。
JUnitまたはTestNGの指導はこのドキュメントの範囲外ですが、資料はオンラインで入手でき、利用可能な出版物があります。
すでに"java-shop"であれば、開発者がこれらのテストフレームワークのいずれかで既にある程度の経験を持っている可能性があります。

おそらく、テストクラスの名前を"NewTest"から独自の名前に変更する必要があります。
また、ステートメント内のブラウザを開くパラメーターを変更する必要があります。

```java
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
``` 

Selenium-IDEで生成されたコードは次のようになります。
この例では、わかりやすくするためにコメントを手動で追加しています。

```java
   package com.example.tests;
   // We specify the package of our tests

   import com.thoughtworks.selenium.*;
   // This is the driver's import. You'll use this for instantiating a
   // browser and making it do what you need.

   import java.util.regex.Pattern;
   // Selenium-IDE add the Pattern module because it's sometimes used for 
   // regex validations. You can remove the module if it's not used in your 
   // script.

   public class NewTest extends SeleneseTestCase {
   // We create our Selenium test case

         public void setUp() throws Exception {
           setUp("http://www.google.com/", "*firefox");
                // We instantiate and start the browser
         }

         public void testNew() throws Exception {
              selenium.open("/");
              selenium.type("q", "selenium rc");
              selenium.click("btnG");
              selenium.waitForPageToLoad("30000");
              assertTrue(selenium.isTextPresent("Results * for selenium rc"));
              // These are the real test steps
        }
   }
```


### `C#`

.NETクライアントドライバーはMicrosoft.NETで動作します。
NUnitやVisual Studio 2005 Team Systemなどの.NETテストフレームワークで利用できます。

Selenium-IDEは、テストフレームワークとしてNUnitを使用することを想定しています。
以下の生成コードでこれを確認できます。
NUnitの *using* ステートメントと、テストクラスの各メンバー関数の役割を識別する対応するNUnit属性が含まれています。

おそらく、テストクラスの名前を"NewTest"から独自の選択に変更する必要があります。
また、ステートメントのブラウザで開くパラメーターを変更する必要があります。

```csharp
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
```

生成されたコードは次のようになります。

```csharp

    using System;
    using System.Text;
    using System.Text.RegularExpressions;
    using System.Threading;
    using NUnit.Framework;
    using Selenium;
    
    namespace SeleniumTests

    {
        [TestFixture]

        public class NewTest

        {
        private ISelenium selenium;

        private StringBuilder verificationErrors;

        [SetUp]

        public void SetupTest()

        {
            selenium = new DefaultSelenium("localhost", 4444, "*iehta",
            "http://www.google.com/");

            selenium.Start();

            verificationErrors = new StringBuilder();
        }

        [TearDown]

        public void TeardownTest()
        {
            try
            {
            selenium.Stop();
            }

            catch (Exception)
            {
            // Ignore errors if unable to close the browser
            }

            Assert.AreEqual("", verificationErrors.ToString());
        }
        [Test]

        public void TheNewTest()
        {
            // Open Google search engine.        
            selenium.Open("http://www.google.com/"); 
            
            // Assert Title of page.
            Assert.AreEqual("Google", selenium.GetTitle());
            
            // Provide search term as "Selenium OpenQA"
            selenium.Type("q", "Selenium OpenQA");
            
            // Read the keyed search term and assert it.
            Assert.AreEqual("Selenium OpenQA", selenium.GetValue("q"));
            
            // Click on Search button.
            selenium.Click("btnG");
            
            // Wait for page to load.
            selenium.WaitForPageToLoad("5000");
            
            // Assert that "www.openqa.org" is available in search results.
            Assert.IsTrue(selenium.IsTextPresent("www.openqa.org"));
            
            // Assert that page title is - "Selenium OpenQA - Google Search"
            Assert.AreEqual("Selenium OpenQA - Google Search", 
                         selenium.GetTitle());
        }
        }
    }
```

NUnitにテストの実行を管理させることができます。
または、テストオブジェクトをインスタンス化し、`SetupTest()`、`TheNewTest()`、`TeardownTest()` の各メソッドを順番に実行する単純な `main()` プログラムを作成することもできます。

### Python

Pyunitは、Pythonで使用するテストフレームワークです。

基本的なテスト構造は次のとおりです。

```python

   from selenium import selenium
   # This is the driver's import.  You'll use this class for instantiating a
   # browser and making it do what you need.

   import unittest, time, re
   # This are the basic imports added by Selenium-IDE by default.
   # You can remove the modules if they are not used in your script.

   class NewTest(unittest.TestCase):
   # We create our unittest test case

       def setUp(self):
           self.verificationErrors = []
           # This is an empty array where we will store any verification errors
           # we find in our tests

           self.selenium = selenium("localhost", 4444, "*firefox",
                   "http://www.google.com/")
           self.selenium.start()
           # We instantiate and start the browser

       def test_new(self):
           # This is the test code.  Here you should put the actions you need
           # the browser to do during your test.
            
           sel = self.selenium
           # We assign the browser to the variable "sel" (just to save us from 
           # typing "self.selenium" each time we want to call the browser).
            
           sel.open("/")
           sel.type("q", "selenium rc")
           sel.click("btnG")
           sel.wait_for_page_to_load("30000")
           self.failUnless(sel.is_text_present("Results * for selenium rc"))
           # These are the real test steps

       def tearDown(self):
           self.selenium.stop()
           # we close the browser (I'd recommend you to comment this line while
           # you are creating and debugging your tests)

           self.assertEqual([], self.verificationErrors)
           # And make the test fail if we found that any verification errors
           # were found
```

### Ruby

Selenium-IDEの古い（2.0より前の）バージョンは、古いSelenium gemを必要とするRubyコードを生成します。
したがって、IDEによって生成されたRubyスクリプトを次のように更新することをお勧めします。

1. １行目を ``require "selenium"`` から ``require "selenium/client"`` に変更

2. 11行目を ``Selenium::SeleniumDriver.new`` から ``Selenium::Client::Driver.new`` に変更

クラス名を"Untitled"よりもわかりやすい名前に変更し、テストメソッドの名前を"test_untitled"以外の名前に変更することもできます。

上記のように、Selenium IDEによって生成されたRubyコードを変更して作成された簡単な例を次に示します。

```ruby

   # load the Selenium-Client gem
   require "selenium/client"

   # Load Test::Unit, Ruby's default test framework.
   # If you prefer RSpec, see the examples in the Selenium-Client
   # documentation.
   require "test/unit"

   class Untitled < Test::Unit::TestCase

     # The setup method is called before each test.
     def setup

       # This array is used to capture errors and display them at the
       # end of the test run.
       @verification_errors = []

       # Create a new instance of the Selenium-Client driver.
       @selenium = Selenium::Client::Driver.new \
         :host => "localhost",
         :port => 4444,
         :browser => "*chrome",
         :url => "http://www.google.com/",
         :timeout_in_second => 60

       # Start the browser session
       @selenium.start

       # Print a message in the browser-side log and status bar
       # (optional).
       @selenium.set_context("test_untitled")

     end

     # The teardown method is called after each test.
     def teardown

       # Stop the browser session.
       @selenium.stop

       # Print the array of error messages, if any.
       assert_equal [], @verification_errors
     end

     # This is the main body of your test.
     def test_untitled
     
       # Open the root of the site we specified when we created the
       # new driver instance, above.
       @selenium.open "/"

       # Type 'selenium rc' into the field named 'q'
       @selenium.type "q", "selenium rc"

       # Click the button named "btnG"
       @selenium.click "btnG"

       # Wait for the search results page to load.
       # Note that we don't need to set a timeout here, because that
       # was specified when we created the new driver instance, above.
       @selenium.wait_for_page_to_load

       begin

          # Test whether the search results contain the expected text.
	  # Notice that the star (*) is a wildcard that matches any
	  # number of characters.
	  assert @selenium.is_text_present("Results * for selenium rc")
	  
       rescue Test::Unit::AssertionFailedError
       
          # If the assertion fails, push it onto the array of errors.
	  @verification_errors << $!

       end
     end
   end

```

### Perl, PHP

ドキュメントチームのメンバーは、PerlまたはPHPでSelenium RCを使用していません。
これらの2つの言語のいずれかでSelenium RCを使用している場合は、ドキュメントチームに連絡してください（貢献に関する章を参照）。
PerlおよびPHPユーザーをサポートするために、あなたとあなたの経験からいくつかの例を含めたいと思います。

## APIを学ぶ

Selenium RC APIは、Seleneseを理解していると仮定すると、インターフェイスのほとんどが自明である命名規則を使用します。
ただし、ここでは、最も重要で、おそらくそれほど明白ではない側面について説明します。

### ブラウザーを起動する

#### CSharp
```csharp
      selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
      selenium.Start();
```

#### Java
```java

      setUp("http://www.google.com/", "*firefox");
```

#### Perl
```perl
      my $sel = Test::WWW::Selenium->new( host => "localhost", 
                                          port => 4444, 
                                          browser => "*firefox", 
                                          browser_url => "http://www.google.com/" );
```

#### Php
```php
      $this->setBrowser("*firefox");
      $this->setBrowserUrl("http://www.google.com/");
```

#### Python
```python
      self.selenium = selenium("localhost", 4444, "*firefox",
                               "http://www.google.com/")
      self.selenium.start()
```

#### Ruby
```ruby
      @selenium = Selenium::ClientDriver.new("localhost", 4444, "*firefox", "http://www.google.com/", 10000);
      @selenium.start
```

これらの各例はブラウザを開き、"ブラウザーインスタンス"をプログラム変数に割り当てることでそのブラウザを表します。
このプログラム変数は、ブラウザからメソッドを呼び出すために使用されます。
これらのメソッドは、Seleniumコマンドを実行します。
つまり、 *open* コマンドや *type* コマンド、 *verify* コマンドなどです。

ブラウザーインスタンスの作成時に必要なパラメーターは次のとおりです。

* **host**
    サーバーが配置されているコンピューターのIPアドレスを指定します。
    通常、これはクライアントが実行されているマシンと同じマシンであるため、この場合は *localhost* が渡されます。
    一部のクライアントでは、これは任意のパラメーターです。

* **port**
    サーバーがクライアントが接続を確立するのを待機しているTCP/IPソケットを指定します。
    これは、一部のクライアントドライバーでは任意です。
	
* **browser**
    テストを実行するブラウザー。
    これは必須パラメーターです。
	
* **url**
    テスト対象のアプリケーションのベースURL。
    これは、すべてのクライアントライブラリに必要であり、ブラウザプロキシAUT通信を開始するための不可欠な情報です。

一部のクライアントライブラリでは、 `start()` メソッドを呼び出してブラウザーを明示的に起動する必要があります。

### コマンドを実行する 

ブラウザを初期化して変数（一般的に"selenium"という名前）に割り当てたら、ブラウザ変数からそれぞれのメソッドを呼び出してSeleneseコマンドを実行させることができます。
たとえば、Seleniumオブジェクトの *type* メソッドを呼び出すには、以下のように記述します。

```
    selenium.type("field-id","string to type")
```

バックグラウンドで、ブラウザは、メソッド呼び出し中に指定したロケーターと文字列を使用して、ユーザーがブラウザーに入力を入力するのと本質的に同じタイプ操作を実際に実行します。

## 結果を報告する

Selenium RCには、結果を報告するための独自のメカニズムがありません。
むしろ、選択したプログラミング言語の機能を使用して、ニーズに合わせてカスタマイズしたレポートを作成できます。
それは素晴らしいことですが、すでにあなたのために行われている何かを簡単にしたい場合はどうでしょうか？
多くの場合、既存のライブラリまたはテストフレームワークは、独自のテストレポートコードを開発するよりも早くニーズを満たすことができます。

### テストフレームワークのレポートツール 

テストフレームワークは、多くのプログラミング言語で使用できます。
これらは、テストを実行するための柔軟なテストエンジンを提供する主な機能とともに、結果を報告するためのライブラリコードを含んでいます。
たとえば、Javaには一般的に使用される2つのテストフレームワーク、JUnitとTestNGがあります。
.NETには独自のNUnitもあります。

ここではフレームワーク自体を教えません。
これはこのユーザーガイドの範囲外です。
Seleniumに関連するフレームワーク機能と、適用可能ないくつかのテクニックを簡単に紹介します。
ただし、これらのテストフレームワークに関する優れた書籍は、インターネット上の情報とともに入手できます。

### テストレポートライブラリ 

選択したプログラミング言語でテスト結果を報告するために特別に作成されたサードパーティライブラリも利用できます。
これらは多くの場合、HTMLやPDFなどのさまざまな形式をサポートします。

### 最良のアプローチは何ですか？ 

テストフレームワークを初めて使用するほとんどの人は、フレームワークに組み込まれているレポート機能から始めます。
独自のライブラリを開発するよりも時間がかかりません。
Seleniumを使用し始めたら、進捗を報告するための独自の"印刷したステートメント"を入力し始めることは間違いありません。
それにより、ライブラリまたはテストフレームワークの使用と並行して、独自のレポートの開発に徐々につながる可能性があります。
とにかく、最初の、しかし短い学習曲線の後、あなたは自分の状況に最適なものを自然に開発します。

### テストレポートの例

説明のために、Seleniumでサポートされている他の言語のいくつかの特定のツールを紹介します。
ここにリストされているものは一般的に使用されており、このガイドの著者によって広く使用されています（したがって、推奨されています）。

#### Javaのテストレポート

* SeleniumテストケースがJUnitを使用して開発されている場合、JUnitレポートを使用してテストレポートを生成できます。

* TestNGを使用してSeleniumテストケースを開発する場合、テストレポートを生成するために外部タスクは必要ありません。
TestNGフレームワークは、テストの詳細をリストするHTMLレポートを生成します。

* ReportNGは、TestNGフレームワーク用のHTMLレポートプラグインです。
これは、デフォルトのTestNG HTMLレポートの代替として意図されています。
ReportNGは、テスト結果の色分けされたシンプルなビューを提供します。

##### Seleneseコマンドのロギング

* Seleniumのロギングを使用して、テスト内のすべてのSeleneseコマンドのレポートを、それぞれの成功または失敗とともに生成できます。
ロギングSeleniumはJavaクライアントドライバーを拡張して、このSeleneseロギング機能を追加します。

#### Pythonのテストレポート

* Pythonクライアントドライバーを使用する場合、HTMLTestRunnerを使用してテストレポートを生成できます。

#### Rubyのテストレポート

* RSpecフレームワークをRubyでのSeleniumテストケースの作成に使用する場合、そのHTMLレポートを使用してテストレポートを生成できます。

## テストにスパイスを追加する

次に、テストにプログラミングロジックを追加して、Selenium RCを使用するすべての理由を説明します。
他のプログラムと同じです。
プログラムフローは、条件ステートメントと反復を使用して制御されます。
さらに、I/Oを使用して進捗情報を報告できます。
このセクションでは、プログラミング言語の構成要素をSeleniumと組み合わせて、一般的なテストの問題を解決する方法の例をいくつか示します。

ページ要素の存在の単純なテストから、予想される結果を検証するためにプログラミングロジックを必要とする複数のWebページとさまざまなデータを含む動的機能のテストに移行するときにわかります。
基本的に、Selenium-IDEは反復および標準条件ステートメントをサポートしていません。
Seleneseパラメーターにjavascriptを埋め込むことでいくつかの条件を実行できますが、反復は不可能であり、プログラミング言語ではほとんどの条件がはるかに簡単になります。
さらに、エラー回復のために例外処理が必要になる場合があります。
これらの理由およびその他の理由により、自動テストでの"検証力"を高めるための一般的なプログラミング手法の使用を説明するために、このセクションを作成しました。

このセクションの例はC#とJavaで記述されていますが、コードはシンプルであり、サポートされている他の言語に簡単に適合させることができます。
オブジェクト指向プログラミング言語の基本的な知識があれば、このセクションを理解するのに困難はないはずです。

### 反復

反復は、テストで行う必要がある最も一般的なことの1つです。
たとえば、検索を複数回実行したい場合があります。
または、おそらくテスト結果を検証するために、データベースから返された"結果セット"を処理する必要があります。

前に使用したのと同じGoogle検索の例を使用して、Seleniumの検索結果を確認しましょう。
このテストではSeleneseを使用できます。

|                    |                               |               |
| --------           | ----------------------------  | ------------- |
| open               | /                             |               |
| type               | q                             | selenium rc   |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium rc     |               |
| type               | q                             | selenium ide  |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium ide    |               |
| type               | q                             | selenium grid |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium grid   |               |

同じ手順を3回実行するためにコードが繰り返されています。
ただし、同じコードのコピーを複数作成することは、維持する作業が増えるため、プログラムとしては適切ではありません。
プログラミング言語を使用することで、検索結果を反復処理して、より柔軟で保守可能なソリューションを実現できます。

#### `C#` の場合
   
```csharp
   // Collection of String values.
   String[] arr = {"ide", "rc", "grid"};    
        
   // Execute loop for each String in array 'arr'.
   foreach (String s in arr) {
       sel.open("/");
       sel.type("q", "selenium " +s);
       sel.click("btnG");
       sel.waitForPageToLoad("30000");
       assertTrue("Expected text: " +s+ " is missing on page."
       , sel.isTextPresent("Results * for selenium " + s));
    }
```

### 条件ステートメント

テストでの条件の使用を説明するために、例から始めます。
Seleniumテストの実行中に発生する一般的な問題は、ページで予期される要素が利用できない場合に発生します。
たとえば、次の行を実行する場合です。

```
   selenium.type("q", "selenium " +s);
```

要素 'q'がページにない場合、例外がスローされます。

```java
   com.thoughtworks.selenium.SeleniumException: ERROR: Element q not found
```

これにより、テストが中断する可能性があります。
いくつかのテストでは、それがあなたの望むものです。
しかし、多くの場合、テストスクリプトには実行する他の多くのテストがあるため、これは望ましくありません。

より良いアプローチは、まず要素が実際に存在するかどうかを検証し、次に存在しない場合に代替手段を取ることです。
Javaを使用してこれを見てみましょう。

```java
   // If element is available on page then perform type operation.
   if(selenium.isElementPresent("q")) {
       selenium.type("q", "Selenium rc");
   } else {
       System.out.printf("Element: " +q+ " is not available on page.")
   }
```
   
このアプローチの利点は、ページで一部のUI要素が利用できない場合でも、テストの実行を続行できることです。

### テストからJavaScriptを実行する

JavaScriptは、セレンによって直接サポートされていないアプリケーションを実行する際に非常に便利です。
Selenium APIの **getEval** メソッドを使用して、Selenium RCからJavaScriptを実行できます。

静的な識別子のないチェックボックスを持つアプリケーションを考えてください。
この場合、Selenium RCからJavaScriptを評価して、すべてのチェックボックスのIDを取得し、それらを実行できます。

```java
   public static String[] getAllCheckboxIds () { 
		String script = "var inputId  = new Array();";// Create array in java script.
		script += "var cnt = 0;"; // Counter for check box ids.  
		script += "var inputFields  = new Array();"; // Create array in java script.
		script += "inputFields = window.document.getElementsByTagName('input');"; // Collect input elements.
		script += "for(var i=0; i<inputFields.length; i++) {"; // Loop through the collected elements.
		script += "if(inputFields[i].id !=null " +
		"&& inputFields[i].id !='undefined' " +
		"&& inputFields[i].getAttribute('type') == 'checkbox') {"; // If input field is of type check box and input id is not null.
		script += "inputId[cnt]=inputFields[i].id ;" + // Save check box id to inputId array.
		"cnt++;" + // increment the counter.
		"}" + // end of if.
		"}"; // end of for.
		script += "inputId.toString();" ;// Convert array in to string.			
		String[] checkboxIds = selenium.getEval(script).split(","); // Split the string.
		return checkboxIds;
    }
```

ページ上の画像の数を数えるには、以下のとおりです。

```java   
   selenium.getEval("window.document.images.length;");
```

デフォルトでは、テストウィンドウではなくSeleniumウィンドウが参照されるため、DOM式の場合は必ずウィンドウオブジェクトを使用してください。

## サーバーオプション

サーバーの起動時に、コマンドラインオプションを使用してデフォルトのサーバーの動作を変更できます。

サーバーを起動するには、次を実行してください。

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar
``` 

オプションのリストを表示するには、 ``-h`` オプションを指定してサーバーを実行します。

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -h
``` 

サーバーで使用できるすべてのオプションのリストとそれぞれの簡単な説明が表示されます。
提供された説明では必ずしも十分ではないため、いくつかのより重要なオプションについて説明しました。

### プロキシ設定

AUTが認証を必要とするHTTPプロキシの後ろにある場合、次のコマンドを使用してhttp.proxyHost、http.proxyPort、http.proxyUserおよびhttp.proxyPasswordを設定する必要があります。

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -Dhttp.proxyHost=proxy.com -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password
``` 

### マルチウィンドウモード

Selenium 1.0を使用している場合は、マルチウィンドウモードがデフォルトの動作であるため、おそらくこのセクションをスキップできます。
ただし、バージョン1.0より前は、Seleniumはデフォルトで、ここに示すようにサブフレームでテスト対象のアプリケーションを実行していました。

![シングルウィンドウモード](/images/documentation/legacy/selenium_rc_single_window_mode.png)

一部のアプリケーションはサブフレームで正しく実行されず、ウィンドウの上部フレームにロードする必要がありました。
マルチウィンドウモードオプションにより、AUTはデフォルトフレームではなく別のウィンドウで実行でき、そこで必要なトップフレームを取得できました。

![マルチウィンドウモード](/images/documentation/legacy/selenium_rc_multi_window_mode.png)

Seleniumの古いバージョンでは、次のオプションで明示的にマルチウィンドウモードを指定する必要があります。

```bash   
   -multiwindow 
``` 

Selenium RC 1.0の時点で、単一のフレーム内でテストを実行する場合（つまり、以前のSeleniumバージョンの標準を使用する場合）、オプションを使用してこれをSelenium Serverに指定できます。

```bash   
   -singlewindow 
``` 

### Firefoxプロファイルの指定

Firefoxは、インスタンスごとに個別のプロファイルを指定しない限り、2つのインスタンスを同時に実行しません。
Selenium RC 1.0以降は個別のプロファイルで自動的に実行されるため、Selenium 1.0を使用している場合は、このセクションをスキップできます。
ただし、Seleniumの古いバージョンを使用している場合、またはテストに特定のプロファイルを使用する必要がある場合（https証明書の追加やアドオンのインストールなど）、プロファイルを明示的に指定する必要があります。

最初に、別のFirefoxプロファイルを作成するには、次の手順に従います。 Windowsのスタートメニューを開き、"実行"を選択して、次のいずれかを入力します。

```bash   
   firefox.exe -profilemanager 
``` 

```bash   
   firefox.exe -P 
``` 

ダイアログを使用して新しいプロファイルを作成します。
次に、Seleniumサーバーを実行するときに、サーバーのコマンドラインオプション *\-firefoxProfileTemplate* でこの新しいFirefoxプロファイルを使用し、ファイル名とディレクトリパスを使用してプロファイルへのパスを指定するように指示します。

```bash   
   -firefoxProfileTemplate "path to the profile" 
``` 

**警告**:  必ずデフォルトとは別の新しいフォルダーにプロファイルを入れてください!!!
Firefoxプロファイルマネージャーツールは、プロファイルを削除すると、プロファイルファイルであるかどうかに関係なく、フォルダー内のすべてのファイルを削除します。

Firefoxプロファイルの詳細については、[Mozillaのナレッジベース](http://support.mozilla.com/en/kb/Managing+profiles)をご覧ください。

### -htmlSuiteを使用してサーバー内でSeleneseを直接実行する

HTMLファイルをサーバーのコマンドラインに渡すことで、Selenese HTMLファイルをSelenium Server内で直接実行できます。 
例えば、

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -htmlSuite "*firefox" 
   "http://www.google.com" "c:\absolute\path\to\my\HTMLSuite.html" 
   "c:\absolute\path\to\my\results.html"
``` 

これにより、HTMLスイートが自動的に起動され、すべてのテストが実行され、結果とともにHTMLレポートが保存されます。

*注意:*  このオプションを使用すると、サーバーはテストを開始し、テストが完了するまで指定された秒数待機します。 その時間内にテストが完了しない場合、コマンドはゼロ以外の終了コードで終了し、結果ファイルは生成されません。

このコマンドラインは非常に長いため、入力するときは注意してください。
これには、単一のテストではなく、HTML Seleneseスイートを渡す必要があることに注意してください。
また、 -htmlSuite オプションは``-interactive``と互換性がないことに注意してください。
両方を同時に実行することはできません。

### Seleniumサーバーのログ

#### サーバー側のログ

Seleniumサーバーを起動するときに、 **-log** オプションを使用して、Seleniumサーバーによってレポートされた貴重なデバッグ情報をテキストファイルに記録できます。

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -log selenium.log
``` 
   
このログファイルは、標準のコンソールログよりも詳細です（DEBUGレベルのログメッセージが含まれます）。
ログファイルには、ロガー名、およびメッセージを記録したスレッドのID番号も含まれます。
例えば、

```bash   
   20:44:25 DEBUG [12] org.openqa.selenium.server.SeleniumDriverResourceHandler - 
   Browser 465828/:top frame1 posted START NEW
``` 
   
メッセージの形式は、以下のとおりです。

```bash   
   TIMESTAMP(HH:mm:ss) LEVEL [THREAD] LOGGER - MESSAGE
``` 
   
このメッセージは複数行の場合があります。

#### ブラウザ側のログ

ブラウザ側のJavaScript（Selenium Core）も重要なメッセージを記録します。
多くの場合、これらは通常のSeleniumサーバーログよりもエンドユーザーにとって有用です。
ブラウザ側のログにアクセスするには、 **-browserSideLog** 引数をSeleniumサーバーに渡します。


```bash   
   java -jar selenium-server-standalone-<version-number>.jar -browserSideLog
``` 

**-browserSideLog** を **-log** 引数と組み合わせて、browserSideLogs（および他のすべてのDEBUGレベルのログメッセージ）をファイルに記録する必要があります。


## 特定のブラウザへのパスを指定する

特定のブラウザーへのパスをSelenium RCに指定できます。
これは、同じブラウザーの異なるバージョンがあり、特定のブラウザーを使用する場合に便利です。
また、これは、Selenium RCで直接サポートされていないブラウザーに対してテストを実行できるようにするために使用されます。
実行モードを指定するときは、ブラウザの実行可能ファイルへのフルパスが後に続く \*custom 指定子を使用します。

```bash   
   *custom <path to browser> 
``` 

   
## Selenium RCアーキテクチャ
   
*注意:* このトピックでは、Selenium RCの背後にある技術的な実装について説明します。
        Seleniumユーザーがこれを知ることは基本的なことではありませんが、将来発生する可能性のある問題の一部を理解するのに役立ちます。

Selenium RC Serverがどのように機能し、プロキシインジェクションと高度な特権モードを使用する理由を詳細に理解するには、最初に `同一オリジンポリシー` を理解する必要があります。

### 同一オリジンポリシー

Seleniumが直面する主な制限は、同一オリジンポリシーです。
このセキュリティ制限は、市場のすべてのブラウザーによって適用され、その目的は、サイトのコンテンツが別のサイトのスクリプトによってアクセスされないようにすることです。
同一オリジンポリシーでは、ブラウザ内にロードされたコードはすべて、そのウェブサイトのドメイン内でのみ動作することが規定されています。
別のWebサイトで関数を実行することはできません。
たとえば、ブラウザが www.mysite.com を読み込むときにJavaScriptコードを読み込むと、それが別のサイトであっても、読み込まれたコードを www.mysite2.com に対して実行できません。
これが可能な場合、他のタブで口座ページを開いていれば、開いているウェブサイトに配置されたスクリプトは銀行口座の情報を読み取ることができます。
これはXSS（クロスサイトスクリプティング）と呼ばれます。

このポリシー内で機能するには、Selenium-Core（およびすべての魔法を発生させるJavaScriptコマンド）をテスト対象アプリケーション（同じURL）と同一オリジンに配置する必要があります。

歴史的に、Selenium-CoreはJavaScriptで実装されていたため、この問題によって制限されていました。
ただし、Selenium RCは同一オリジンポリシーによって制限されていません。
Seleniumサーバーをプロキシとして使用すると、この問題を回避できます。
基本的に、ブラウザがサーバーが提供する単一の"なりすまし"ウェブサイトで動作していることをブラウザに伝えます。

*注意:* このトピックに関する追加情報は、同一オリジンポリシーおよびXSSに関するWikipediaページで見つけることができます。

### プロキシインジェクション

同一オリジンポリシーを回避するためにSeleniumが使用した最初の方法は、プロキシインジェクションでした。
プロキシインジェクションモードでは、Selenium Serverはブラウザと テスト対象アプリケーション[^1] の間にあるクライアント設定の **HTTPプロキシ**[^2]として機能します。
次に、架空のURLでテスト対象アプリケーションをマスクします（Selenium-Coreと一連のテストを埋め込み、同一オリジンから来ているかのように配信します）。

[^1]: ブラウザーは、 localhost:4444 をHTTPプロキシーとして設定した構成プロファイルで起動されます。
これが、ブラウザーが行うHTTP要求がSeleniumサーバーを通過し、レスポンスが実サーバーからではなく通過する理由です。

[^2]: プロキシは、2つの部分の間でボールを渡す中間の第三者です。
AUTをブラウザに配信する"Webサーバー"として機能します。
プロキシであるため、Seleniumサーバーはテスト対象アプリケーションの実際のURLについて"嘘をつく"機能を提供します。

これがアーキテクチャ図です。

![これがアーキテクチャ図 1](/images/documentation/legacy/selenium_rc_architecture_diagram_1.png)

お気に入りの言語でテストスイートが開始されると、次のようになります。

1. クライアント/ドライバーは、selenium-RCサーバーとの接続を確立します。
2. Selenium RCサーバーは、Selenium-CoreのJavaScriptをブラウザーがロードしたWebページに挿入するURLを使用してブラウザーを起動します（または古いブラウザーを再利用します）。 
3. クライアントドライバーはSeleneseコマンドをサーバーに渡します。
4. サーバーはコマンドを解釈し、対応するJavaScript実行をトリガーして、ブラウザー内でそのコマンドを実行します。 Selenium-Coreは、ブラウザーに最初の命令に基づいて動作するよう指示し、通常はテスト対象アプリケーションのページを開きます。
5. ブラウザーはオープンリクエストを受信し、Selenium RCサーバー（使用するブラウザーのHTTPプロキシとして設定）からWebサイトのコンテンツを要求します。
6. Selenium RCサーバーはWebサーバーと通信してページを要求し、ページを受信すると、ブラウザーにページを送信し、オリジンをマスクしてページがSelenium-Coreと同じサーバーからのものであるように見えます（これにより、Selenium-Coreは 同一オリジンポリシーを使用）。
7. ブラウザーはWebページを受信し、そのページ用に予約されているフレーム/ウィンドウにレンダリングします。
   
### Heightened Privileges でブラウザーを起動する

この方法のこのワークフローは、プロキシインジェクションに非常に似ていますが、主な違いは、ブラウザが *Heightened Privileges* と呼ばれる特別なモードで起動されることです。
これにより、Webサイトは一般に許可されていないこと（SeleniumにXSSを実行したり、ファイルのアップロード入力を入力したり）を許可します。
これらのブラウザーモードを使用することで、Selenium CoreはAUT全体をSelenium RCサーバーに渡すことなく、テスト対象アプリケーションを直接開き、コンテンツを読み取り/操作できます。

これがアーキテクチャ図です。 

![アーキテクチャ図 1](/images/documentation/legacy/selenium_rc_architecture_diagram_2.png)

お気に入りの言語でテストスイートが開始されると、次のようになります。

1. クライアント/ドライバーは、selenium-RCサーバーとの接続を確立します。
2. Selenium RCサーバーは、WebページにSelenium-CoreをロードするURLを使用してブラウザーを起動します（または古いブラウザーを再利用します）。
3. Selenium-Coreは、クライアント/ドライバーから最初の命令を取得します（Selenium RCサーバーへの別のHTTP要求を介して）。
4. Selenium-Coreはその最初の命令に基づいて動作し、通常はテスト対象アプリケーションのページを開きます。
5. ブラウザはオープン要求を受信し、Webサーバーにページを要求します。
ブラウザがWebページを受信すると、そのページ用に予約されたフレーム/ウィンドウにレンダリングします。

## HTTPSおよびセキュリティポップアップの処理

多くのアプリケーションは、パスワードやクレジットカード情報などの暗号化された情報を送信する必要がある場合、HTTPからHTTPSに切り替えます。
これは、今日の多くのWebアプリケーションに共通しています。
Selenium RCはこれをサポートしています。

HTTPSサイトが本物であることを確認するには、ブラウザにセキュリティ証明書が必要です。
そうでない場合、ブラウザがHTTPSを使用してテスト対象アプリケーションにアクセスすると、アプリケーションが'信頼されていない'と見なされます。
これが発生すると、ブラウザにセキュリティポップアップが表示され、Selenium RCを使用してこれらのポップアップを閉じることはできません。

Selenium RCテストでHTTPSを扱う場合、これをサポートし、セキュリティ証明書を処理する実行モードを使用する必要があります。
テストプログラムでSeleniumを初期化するときに、実行モードを指定します。

Selenium RC 1.0ベータ2以降では、実行モードに* firefoxまたは* iexploreを使用します。
Selenium RC 1.0 beta 1を含む以前のバージョンでは、実行モードに\*chromeまたは \*iehtaを使用します。
これらの実行モードを使用すると、特別なセキュリティ証明書をインストールする必要はありません。
Selenium RCがそれを処理します。

バージョン1.0では、実行モード\*firefoxまたは\*iexploreが推奨されます。
ただし、\*iexploreproxyおよび\*firefoxproxyの追加の実行モードがあります。
これらは後方互換性のためにのみ提供されており、レガシーテストプログラムで必要でない限り使用しないでください。
アプリケーションが追加のブラウザウィンドウを開く場合、セキュリティ証明書の処理と複数のウィンドウの実行に制限があります。

Selenium RCの以前のバージョンでは、 \*chromeまたは\*iehtaは、HTTPSおよびセキュリティポップアップの処理をサポートする実行モードでした。
これらは'実験モード'と見なされましたが、非常に安定し、多くの人が使用していました。
Selenium 1.0を使用している場合、これらの古い実行モードは不要であり、使用すべきではありません。

### セキュリティ証明書の説明

通常、ブラウザは、既に所有しているセキュリティ証明書をインストールすることで、テストしているアプリケーションを信頼します。
ブラウザのオプションまたはインターネットのプロパティでこれを確認できます（テスト対象アプリケーションのセキュリティ証明書がわからない場合は、システム管理者に問い合わせてください）。
Seleniumがブラウザーをロードすると、ブラウザーとサーバー間のメッセージをインターセプトするコードを挿入します。
ブラウザーは、信頼されていないソフトウェアがアプリケーションのように見えると解釈するようになりました。
ポップアップメッセージで警告することで応答します。

これを回避するために、Selenium RC（これをサポートする実行モードを使用する場合）は、ブラウザーがアクセスできる場所でクライアントコンピューターに独自のセキュリティ証明書を一時的にインストールします。
これにより、ブラウザはテスト対象アプリケーションとは異なるサイトにアクセスしていると思わせ、ポップアップを効果的に抑制します。

Seleniumの以前のバージョンで使用された別の方法は、Seleniumのインストールで提供されるCybervilliansセキュリティ証明書をインストールすることでした。
ただし、ほとんどのユーザーはこれを行う必要がなくなります。
Selenium RCをプロキシインジェクションモードで実行している場合、このセキュリティ証明書を明示的にインストールする必要があるかもしれません。

## 追加のブラウザーとブラウザー構成のサポート

Selenium APIは、Internet ExplorerとMozilla Firefoxに加えて、複数のブラウザーに対する実行をサポートしています。
サポートされるブラウザーについては、 https://selenium.dev Webサイトを参照してください。
さらに、ブラウザーが直接サポートされていない場合でも、テストアプリケーションがブラウザーを起動する時に"\*custom"実行モード（すなわち、\*firefoxまたは\*iexploreの代わり）を使用して、選択したブラウザーに対してSeleniumテストを実行できます。
これにより、API呼び出し内で実行可能なブラウザーへのパスを渡します。
これは、対話モードのサーバーからも実行できます。

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\MyBrowser.exe&2=http://www.google.com
```

### 異なるブラウザー設定でテストを実行する

通常、Selenium RCはブラウザーを自動的に設定しますが、"\*custom" 実行モードを使用してブラウザーを起動する場合、自動設定を使用せずにSelenium RCにブラウザーをそのまま強制的に起動させることができます。

たとえば、次のようなカスタム設定でFirefoxを起動できます。

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\firefox.exe&2=http://www.google.com
```

この方法でブラウザーを起動する場合、Selenium Serverをプロキシとして使用するようにブラウザーを手動で設定する必要があることに注意してください。
通常、これはブラウザーの設定を開き、"localhost:4444"をHTTPプロキシとして指定することを意味しますが、この手順はブラウザーごとに根本的に異なる場合があります。
詳細については、ブラウザーのドキュメントを参照してください。

Mozillaブラウザは、起動と停止の方法が異なる場合があることに注意してください。
Mozillaブラウザの動作をもう少し予測可能にするために、MOZ_NO_REMOTE環境変数を設定する必要があるかもしれません。 
Unixユーザーは、シェルスクリプトを使用してブラウザを起動しないでください。
一般に、バイナリ実行可能ファイル（firefox-binなど）を直接使用することをお勧めします。
   
## 一般的な問題のトラブルシューティング

Selenium RCの使用を開始すると、一般的に発生する可能性のある問題がいくつかあります。
ここでそれらとその解決策を紹介します。

### サーバーに接続できません

テストプログラムがSeleniumサーバーに接続できない場合、Seleniumはテストプログラムで例外をスローします。
このメッセージまたは同様のメッセージが表示されるはずです。

```bash
    "Unable to connect to remote server (Inner Exception Message: 
	No connection could be made because the target machine actively 
	refused it )"
    
	(using .NET and XP Service Pack 2) 
```

このようなメッセージが表示された場合は、必ずSeleniumサーバーを起動してください。
その場合、SeleniumクライアントライブラリとSeleniumサーバー間の接続に問題があります。

Selenium RCを使用する場合、ほとんどの人は、同じマシンでテストプログラム（Seleniumクライアントライブラリを使用）とSeleniumサーバーを実行することから始めます。
これを行うには、接続パラメーターとして"localhost"を使用します。
開始する潜在的なネットワークの問題の影響を軽減するため、この方法で開始することをお勧めします。
オペレーティングシステムに一般的なネットワーク設定とTCP/IP設定があると仮定すると、ほとんど問題はありません。
実際、多くの人がこの方法でテストを実行することを選択します。

ただし、リモートマシンでSeleniumサーバーを実行する場合は、2台のマシン間に有効なTCP/IP接続があると仮定すると、接続は良好です。

接続に問題がある場合は、 *ping* 、*telnet* 、 *ifconfig(Unix)/ipconfig(Windows)* などの一般的なネットワークツールを使用して、有効なネットワーク接続を確保できます。
これらに不慣れな場合は、システム管理者が支援できます。

### ブラウザをロードできません 

わかりやすいエラーメッセージではありません。
申し訳ありませんが、Seleniumサーバーがブラウザをロードできない場合、このエラーが表示される可能性があります。

```bash
    (500) Internal Server Error
```

これは、下記が原因の可能性があります。

* Firefox（Selenium 1.0より前）は、ブラウザーが既に開いており、別のプロファイルを指定していないため、起動できません。
  サーバーオプションのFirefoxプロファイルのセクションを参照してください。
* 使用している実行モードは、マシン上のどのブラウザとも一致しません。
  プログラムでブラウザーを開いたときに、Seleniumに渡したパラメーターを確認してください。
* ブラウザーへのパスを明示的に指定しました（ "\*custom" を使用 - 上記を参照）が、パスが正しくありません。
  パスが正しいことを確認してください。
  また、ユーザーグループをチェックして、ブラウザーと "\*custom" パラメーターに既知の問題がないことを確認します。

### SeleniumはAUTを見つけることができません 

テストプログラムがブラウザを正常に起動したが、テストしているWebサイトがブラウザに表示されない場合、最も可能性の高い原因は、テストプログラムが正しいURLを使用していないことです。

これは簡単に起きます。
Selenium-IDEを使用してスクリプトをエクスポートすると、ダミーのURLが挿入されます。
アプリケーションをテストするには、URLを手動で正しいものに変更する必要があります。

### Firefoxはプロファイルの準備中にシャットダウンを拒否しました

これはほとんどの場合、Selenium RCテストプログラムをFirefoxに対して実行しますが、Firefoxブラウザーセッションが既に実行されており、Selenium Serverの起動時に別のプロファイルを指定しなかった場合に発生します。
テストプログラムからのエラーは次のようになります。

```bash
    Error:  java.lang.RuntimeException: Firefox refused shutdown while 
    preparing a profile 
```

サーバーからの完全なエラーメッセージを次に示します。

```bash
    16:20:03.919 INFO - Preparing Firefox profile... 
    16:20:27.822 WARN - GET /selenium-server/driver/?cmd=getNewBrowserSession&1=*fir 
    efox&2=http%3a%2f%2fsage-webapp1.qa.idc.com HTTP/1.1 
    java.lang.RuntimeException: Firefox refused shutdown while preparing a profile 
            at org.openqa.selenium.server.browserlaunchers.FirefoxCustomProfileLaunc 
    her.waitForFullProfileToBeCreated(FirefoxCustomProfileLauncher.java:277) 
    ... 
    Caused by: org.openqa.selenium.server.browserlaunchers.FirefoxCustomProfileLaunc 
    her$FileLockRemainedException: Lock file still present! C:\DOCUME~1\jsvec\LOCALS 
    ~1\Temp\customProfileDir203138\parent.lock 
```

これを解決するには、個別のFirefoxプロファイルの指定に関するセクションを参照してください。

### バージョン管理の問題 

Seleniumのバージョンがブラウザのバージョンをサポートしていることを確認してください。
たとえば、Selenium RC 0.92はFirefox 3をサポートしていません。
時には幸運かもしれません（私はそうでした）。
ただし、使用しているSeleniumのバージョンでサポートされているブラウザのバージョンを確認することを忘れないでください。
疑わしい場合は、ブラウザの最も広く使用されているバージョンでSeleniumの最新リリースバージョンを使用してください。

### サーバーの起動中のエラーメッセージ： "(Unsupported major.minor version 49.0)"

このエラーは、正しいバージョンのJavaを使用していないことを示しています。
Selenium ServerにはJava 1.5以降が必要です。

Javaバージョンを再確認するには、コマンドラインからこれを実行します。

```bash
   java -version
```

Javaバージョンを示すメッセージが表示されます。

```bash
   java version "1.5.0_07"
   Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_07-b03)
   Java HotSpot(TM) Client VM (build 1.5.0_07-b03, mixed mode)
```

低いバージョン番号が表示される場合は、JREを更新するか、単に更新したJREをPATH環境変数に追加する必要があります。

### getNewBrowserSessionコマンドの実行時の404エラー

"http://www.google.com/selenium-server/" でページを開こうとしているときに404エラーが表示される場合は、Seleniumサーバーがプロキシとして正しく構成されていないことが原因である必要があります。
"selenium-server" ディレクトリはgoogle.comには存在しません。
プロキシが適切に設定されている場合にのみ存在します。
プロキシ設定は、Firefox、iexplore、opera、またはカスタムでブラウザを起動する方法に大きく依存します。

* iexplore: \*iexplore を使用してブラウザを起動した場合、Internet Explorerのプロキシ設定に問題がある可能性があります。
Seleniumサーバーは、インターネットオプションコントロールパネルでグローバルプロキシ設定を構成しようとします。
Seleniumサーバーがブラウザーを起動するときに、これらが正しく構成されていることを確認する必要があります。
インターネットオプションコントロールパネルを見てみてください。
"接続"タブをクリックし、"LAN設定"をクリックします。
  * プロキシを使用してテストするアプリケーションにアクセスする必要がある場合は、"-Dhttp.proxyHost"でSeleniumサーバーを起動する必要があります。
    詳細については、`Proxy Configuration`_ を参照してください。
  * プロキシを手動で設定してから、 \*custom または \*iehta ブラウザーランチャーでブラウザーを起動することもできます。

* custom: \*customを使用する場合、プロキシを正しく（手動で）設定する必要があります。
  そうしないと、404エラーが発生します。
  プロキシ設定が正しく構成されていることを再確認してください。
  プロキシを正しく設定したかどうかを確認するには、意図的にブラウザを誤って設定しようとします。
  間違ったプロキシサーバーのホスト名または間違ったポートを使用するようにブラウザーを構成してください。
  ブラウザのプロキシ設定を正しく構成しなかった場合、ブラウザーはインターネットに接続できなくなります。
  これは、関連する設定を調整していることを確認する1つの方法です。

* 他のブラウザ（\*firefox、\*opera）では、プロキシが自動的にハードコード化されるため、この機能に関する既知の問題はありません。
  404エラーが発生し、このユーザーガイドに従っている場合は、ユーザーコミュニティからの助けを得るために、ユーザーグループに結果を慎重に投稿してください。
      
### パーミッション拒否エラー

このエラーの最も一般的な理由は、セッションがドメインの境界を越える（たとえば、
http://domain1 から、http://domain2 のページにアクセスします）かプロトコルを切り替える（http://domainX から https://domainX に移動する）ことで同一オリジンポリシーに違反しようとしていることです。

このエラーは、JavaScriptがまだ使用可能でないUIページ（ページが完全にロードされる前）または使用できなくなった（ページのアンロードが開始された後）UIオブジェクトを見つけようとした場合にも発生します。
これは、AJAXページで最も一般的に発生します。
AJAXページは、大きなページとは独立してロードおよび/またはリロードするページまたはサブフレームのセクションで動作します。

このエラーは断続的に発生する場合があります。
問題はデバッガーのオーバーヘッドがシステムに追加されたときに再現できない競合状態に起因するため、デバッガーで問題を再現することはできません。
パーミッションの問題については、チュートリアルで詳しく説明します。
`The Same Origin Policy`、`Proxy Injection`に関する章を注意深くお読みください。

### ブラウザーポップアップウィンドウの処理

Seleniumテスト中に取得できる"ポップアップ"にはいくつかの種類があります。
テスト対象アプリケーションではなくブラウザによって開始されたSeleniumコマンドを実行しても、これらのポップアップを閉じることができない場合があります。
これらの管理方法を知る必要があるかもしれません。
ポップアップの種類ごとに異なる方法で対処する必要があります。

* HTTP基本認証ダイアログ：これらのダイアログは、サイトにログインするためのユーザー名/パスワードの入力を求めます。
  HTTP基本認証を必要とするサイトにログインするには、次のように、`RFC 1738`で説明されているように、URLでユーザー名とパスワードを使用します。 open("http://myusername:myuserpassword@myexample.com/blah/blah/blah").

* SSL証明書の警告：Selenium RCは、SSL証明書がプロキシとして有効になっている場合、自動的になりすまそうとします。
  詳細については、HTTPSの章を参照してください。
  ブラウザーが正しく設定されている場合、SSL証明書の警告は表示されませんが、危険な"CyberVillains"SSL認証局を信頼するようにブラウザーを設定する必要があります。
  繰り返しますが、これを行う方法についてはHTTPSセクションを参照してください。

* モーダルJavaScriptアラート/確認/プロンプトダイアログ：Seleniumはそれらのダイアログを（window.alert、window.confirm、window.promptを置き換えることで）隠そうとするため、ページの実行が停止されません。
  アラートポップアップが表示されている場合は、ページの読み込みプロセス中に発生した可能性があります。
  通常、ページを保護するには早すぎます。
  Seleneseには、アラートと確認のポップアップをアサートまたは検証するためのコマンドが含まれています。
  第4章のこれらのトピックに関する章を参照してください。
      
### Linuxで、Firefoxブラウザーセッションが閉じないのはなぜですか？

Unix/Linuxでは、"firefox-bin"を直接呼び出す必要があるため、実行可能ファイルがパス上にあることを確認してください。
シェルスクリプトを介してFirefoxを実行している場合、ブラウザーを終了するときが来ると、Selenium RCはシェルスクリプトを終了し、ブラウザーを実行したままにします。
このように、firefox-binへのパスを直接指定できます。

```bash
   cmd=getNewBrowserSession&1=*firefox /usr/local/firefox/firefox-bin&2=http://www.google.com
```

### Firefox \*chrome はカスタムプロファイルでは機能しません

Firefoxプロファイルのフォルダー -> prefs.js -> user_pref("browser.startup.page", 0); を確認してください。
次の行を "//user_pref("browser.startup.page", 0);" のようにコメントアウトして、再度試してください。

### 親ページの読み込み中にカスタムポップアップを読み込むことはできますか（つまり、親ページのjavascript window.onload()関数が実行される前）？

いいえ。Seleniumはインターセプターに依存しており、ロード中のウィンドウ名を決定します。
これらのインターセプターは、ウィンドウがonload()関数の後にロードされた場合、新しいウィンドウをキャッチするのに最適に機能します。
Seleniumは、onload関数の前にロードされたウィンドウを認識しない場合があります。

### Linux上のFirefox 

Unix/Linuxでは、1.0より前のSeleniumのバージョンは "firefox-bin" を直接呼び出す必要があったため、以前のバージョンを使用している場合は、実際の実行可能ファイルがパス上にあることを確認してください。

ほとんどのLinuxディストリビューションでは、実際の *firefox-bin* は次の場所にあります。

```bash
   /usr/lib/firefox-x.x.x/ 
```

x.x.xは現在使用しているバージョン番号です。
そのため、そのパスをユーザーのパスに追加します。
以下を.bashrcファイルに追加する必要があります。

```bash
   export PATH="$PATH:/usr/lib/firefox-x.x.x/"
```

必要に応じて、次のようにテストで直接firefox-binへのパスを指定できます。

```bash
   "*firefox /usr/lib/firefox-x.x.x/firefox-bin"
```

### IEおよびスタイル属性

Internet Explorerでテストを実行していて、style属性を使用して要素を見つけられない場合、例えば、次のような場合があります。

```bash
    //td[@style="background-color:yellow"]
```

これはFirefox、Opera、またはSafariで完全に機能しますが、IEでは機能しません。
IEは `@style` のキーを大文字として解釈します。
したがって、ソースコードが小文字であっても、下記のように使用したほうがよいです。

```bash
    //td[@style="BACKGROUND-COLOR:yellow"]
```

テストが複数のブラウザーで動作することを意図している場合、これは問題ですが、簡単にテストをコーディングして状況を検出し、IEでのみ動作する代替ロケーターを試すことができます。

### エラーが発生しました-\*googlechromeブラウザーのシャットダウン時に"Cannot convert object to primitive value"

このエラーを回避するには、同一オリジンポリシーチェックを無効にするオプションでブラウザを起動する必要があります。

```bash
   selenium.start("commandLineFlags=--disable-web-security");
```

### IEでエラーが発生しました - "Couldn't open app window; is the pop-up blocker enabled?"

このエラーを回避するには、ブラウザを設定する必要があります。
ポップアップブロッカーを無効にし、ツール >> オプション >>セキュリティで'保護モードを有効にする'オプションをオフにします。
