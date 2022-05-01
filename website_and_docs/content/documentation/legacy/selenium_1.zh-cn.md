---
title: "Selenium RC (Selenium 1)"
linkTitle: "Selenium 1"
weight: 2
description: >
    原始版本的Selenium
aliases: [
"/documentation/zh-cn/legacy_docs/selenium_rc/",
"/zh-cn/documentation/legacy/selenium_rc/"
]
---


## 介绍
在WebDriver / Selenium合并产生功能更强大的Selenium 2之前, 
Selenium RC一直是Selenium的主要项目. 
再次特意强调的是, Selenium 1不再享有技术支持.

## Selenium RC 的工作原理
首先, 我们将描述Selenium RC的组件如何运行
以及每个组件在运行测试脚本中所扮演的角色.

### RC 组件

Selenium RC 组件包括:

* Selenium 服务器, 用于启动并关闭浏览器, 
解释运行从测试程序传递来的Selenese命令, 
并充当 *HTTP代理* , 
拦截和验证在浏览器和AUT之间传递的HTTP消息. 
* 客户端库, 提供每种编程语言和
Selenium RC 服务器之间的接口. 

以下是一个简化的架构图:

![Architecture Diagram Simple](/images/documentation/legacy/selenium_rc_architecture_diagram_simple.png) 

该图显示了客户端库与服务器通信, 
并传递了用来执行的每个Selenium命令. 
然后, 服务器使用Selenium-Core的JavaScript命令
将Selenium命令传递到浏览器. 
浏览器使用其JavaScript解释器执行Selenium命令. 
这将运行您在测试脚本中指定的Selenese操作或验证行为.

### Selenium 服务器

Selenium 服务器从您的测试程序接收Selenium命令, 
对其进行解释, 
然后将运行这些测试的结果报告给您的程序.

RC服务器捆绑了Selenium Core并将其自动注入浏览器. 
当您的测试程序打开浏览器(使用客户端库API函数)时, 
会发生这种情况. 
Selenium-Core是一个JavaScript程序, 
实际上是一组JavaScript函数, 
这些函数使用浏览器的内置JavaScript解释器来解释
和执行Selenese命令.

服务器使用简单的HTTP GET / POST请求从您的测试程序接收Selenese命令. 
这意味着您可以使用任何可以发送HTTP请求的编程语言
来自动执行浏览器中的Selenium测试.

### 客户端库

客户端库提供了编程支持, 
使您可以从自己设计的程序中运行Selenium命令. 
每种受支持的语言都有一个不同的客户端库. 
Selenium客户端库提供了一个编程接口(API), 
即一组函数, 可从您自己的程序中运行Selenium命令. 
在每个界面中, 都有一个支持每个Selenese命令的编程功能.

客户端库接受Selenese命令, 
并将其传递给Selenium 服务器, 
以针对被测应用程序(AUT)处理特定操作或测试. 
客户端库还接收该命令的结果, 并将其传递回您的程序. 
您的程序可以接收结果并将其存储到程序变量中, 
并将其报告为成功或失败, 
或者如果是意外错误, 
则可以采取纠正措施. 

因此, 要创建测试程序, 
只需编写一个使用客户端库API运行一组Selenium命令的程序. 
并且, 可选地, 如果您已经在Selenium-IDE中创建了Selenese测试脚本, 
则可以 *生成Selenium RC代码* . 
Selenium-IDE可以(使用其"导出"菜单项)
将其Selenium命令转换为客户端驱动程序的API函数调用. 
有关从Selenium-IDE导出RC代码的详细信息, 
请参见Selenium-IDE章节.

## 安装

安装对于Selenium来说是谬称. 
Selenium具有一组可用您选择的编程语言提供的库. 您可以从 [下载页面](https://selenium.dev/downloads/)下载它们.

选择了要使用的语言后, 您只需要:

* 安装Selenium RC服务器.
* 使用特定语言的客户端驱动程序设置编程项目.

### 安装Selenium服务器

Selenium RC服务器只是一个Java *jar* 文件
( *selenium-server-standalone-<version-number>.jar* ), 
不需要任何特殊的安装. 
只需下载zip文件并将服务器提取到所需目录中就足够了. 

### 运行Selenium服务器

在开始任何测试之前, 您必须启动服务器. 
转到Selenium RC服务器所在的目录, 
然后从命令行控制台运行以下命令.

```shell
    java -jar selenium-server-standalone-<version-number>.jar
```

通过创建包含上述命令的批处理或Shell可执行文件
(Windows上为.bat, Linux上为.sh), 
可以简化此操作. 
然后在桌面上对该可执行文件创建快捷方式, 
只需双击该图标即可启动服务器.

为了使服务器运行, 
您需要安装Java并正确配置PATH环境变量才能从控制台运行它. 
您可以通过在控制台上运行以下命令来检查是否正确安装了Java.

```shell
       java -version
```

如果您查看到预期的版本号(必须为1.5或更高版本), 
就可以开始使用Selenium RC了. 

### 使用Java客户端驱动程序

* 从SeleniumHQ [下载页面](https://selenium.dev/downloads/) 下载Selenium Java客户端驱动程序zip
* 解压 selenium-java-<version-number>.jar 文件
* 打开所需的Java IDE(Eclipse, NetBeans, IntelliJ, Netweaver等)
* 创建一个Java项目.
* 将selenium-java-.jar文件添加到您的项目中作为依赖.
* 将文件selenium-java-.jar添加到您的项目类路径中.
* 从Selenium-IDE中, 将脚本导出到Java文件并将其包含在Java项目中, 
或者使用selenium-java-client API用Java编写Selenium测试. 
该API将在本章稍后介绍. 
您可以使用JUnit或TestNg来运行测试, 
也可以编写自己的简单main()程序. 
这些概念将在本节稍后说明. 
* 从控制台运行Selenium服务器.
* 从Java IDE或命令行执行测试.

有关Java测试项目配置的详细信息, 
请参阅附录部分. 
使用Eclipse配置Selenium RC和使用Intellij配置Selenium RC. 

### 使用Python客户端驱动程序

* 通过PIP安装Selenium, 说明链接在SeleniumHQ [下载页面](https://selenium.dev/downloads/) 
* 用Python编写Selenium测试
或将脚本从Selenium-IDE导出到python文件.
* 从控制台运行Selenium服务器
* 从控制台或Python IDE执行测试 

有关Python客户端驱动程序配置的详细信息, 
请参见附录Python客户端驱动程序配置.

### 使用.NET客户端驱动程序

* 从SeleniumHQ [下载页面](https://selenium.dev/downloads/) 下载Selenium RC
* 解压缩文件夹
* 下载并安装[NUnit](https://nunit.org/download/)
(注意：您可以将NUnit用作测试引擎. 
如果您还不熟悉NUnit, 
还可以编写一个简单的main()函数来运行测试；
但是NUnit作为测试引擎非常有用)
* 打开所需的.Net IDE(Visual Studio, SharpDevelop, MonoDevelop)
* 创建一个类库(.dll)
* 添加对以下DLL的引用：
nmock.dll, 
nunit.core.dll, 
nunit.framework.dll, 
ThoughtWorks.Selenium.Core.dll, 
ThoughtWorks.Selenium.IntegrationTests.dll
和ThoughtWorks.Selenium.UnitTests.dll
* 用.Net语言(C＃, VB.Net)编写Selenium测试, 
或将脚本从Selenium-IDE导出到C＃文件, 
然后将此代码复制到刚创建的类文件中.
* 编写自己的简单main()程序, 
或者可以在项目中包含NUnit来运行测试. 
这些概念将在本章稍后说明. 
* 从控制台运行Selenium服务器
* 从IDE, NUnit GUI或命令行运行测试

有关使用Visual Studio配置.NET客户端驱动程序的详细信息, 
请参阅附录.NET客户端驱动程序配置. 

### 使用Ruby客户端驱动程序

* 如果您还没有RubyGems, 请从RubyForge安装它.
* 运行 ``gem install selenium-client``
* 在测试脚本的顶部, 添加 ``require "selenium/client"``
* 使用任何Ruby测试工具
(例如Test::Unit, Mini::Test或RSpec)编写测试脚本.
* 从控制台运行Selenium RC服务器.
* 以与运行其他任何Ruby脚本相同的方式执行测试.


有关Ruby客户端驱动程序配置的详细信息, 请参见 `Selenium-Client documentation`_

## 从 Selenese 到程序

使用Selenium RC的主要任务
是将您的Selenese转换为编程语言. 
在本节中, 我们提供了几种不同的特定于语言的示例. 

### 示例测试脚本

让我们从一个Selenese测试脚本示例开始. 试想用Selenium-IDE录制以下测试.

|                    |                               |             |
| --------           | ----------------------------  | ----------- |
| open               | /                             |             |
| type               | q                             | selenium rc |
| clickAndWait       | btnG                          |             |
| assertTextPresent  | Results * for selenium rc     |             |


注意：此示例适用于Google搜索页面 http://www.google.com

### Selenese作为编程代码

这是(通过Selenium-IDE)导出到每种支持的编程语言的测试脚本. 
如果您具有基本的面向对象编程语言的基础知识, 
则可以通过阅读以下示例之一
来了解Selenium如何运行Selenese命令. 
要查看特定语言的示例, 
请选择以下按钮之一. 

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

在下一节中, 
我们将说明如何使用生成的代码来构建测试程序.

## 编写测试程序

现在, 我们将使用每种受支持的编程语言的示例
来说明如何对自己的测试进行编程. 
本质上有两个任务:

* 从Selenium-IDE将脚本生成为代码, 
可以选择修改结果.  
* 编写一个非常简单的执行生成代码的主程序.  

(可选)您可以采用测试引擎平台, 
例如JUnit或Java的TestNG, 
或使用NUnit的.NET
(如果使用的是其中一种语言).

在这里, 我们显示特定于语言的示例. 
特定语言的API彼此之间往往有所不同, 
因此您会为每个API找到单独的解释

* Java
* C#
* Python
* Ruby
* Perl, PHP


### Java

对于Java, 人们使用JUnit或TestNG作为测试引擎.  
某些开发环境(如Eclipse)通过插件直接支持这些环境. 
这使它变得更加容易. 
讲授JUnit或TestNG不在本文档的范围之内, 
但是可以在网上找到资料, 
并且可以找到出版物. 
如果您恰巧熟悉Java全家桶, 
那么您的开发人员将已经具有使用某种测试框架的经验. 

您可能需要将测试类从" NewTest"重命名为您自己选择的名称. 
另外, 您将需要在语句中更改浏览器打开参数:

```java
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
``` 

Selenium-IDE生成的代码将如下所示. 
此示例具有手动添加的注释, 
以提高清晰度.

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

.NET客户端驱动程序可与Microsoft.NET一起使用. 
它可以与任何.NET测试框架(
如NUnit或Visual Studio 2005 Team System)一起使用.

Selenium-IDE假定您将使用NUnit作为测试框架. 
您可以在下面的生成的代码中看到这一点. 
它包含NUnit的using语句以及相应的NUnit属性, 
这些属性标识测试类的每个成员函数的角色.  

您可能必须将测试类从" NewTest"重命名为您自己选择的名称. 
另外, 您将需要在语句中更改浏览器打开参数:

```csharp
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
```

生成的代码将类似于此.

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

您可以允许NUnit管理测试的执行. 
或者, 您可以编写一个简单的 `main()` 程序, 
该程序实例化测试对象并依次运行三个方法 `SetupTest()`, 
`TheNewTest()`和 `TeardownTest()` .


### Python

Pyunit是用于Python的测试框架.

基本测试结构是:

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


Selenium-IDE的旧版本(2.0之前的版本)生成
需要旧Selenium gem的Ruby代码. 
因此, 建议如下更新IDE生成的所有Ruby脚本:

1. 在第一行, 修改 ``require "selenium"`` 为 ``require
"selenium/client"``

2. 在第十一行, 修改 ``Selenium::SeleniumDriver.new`` 为
``Selenium::Client::Driver.new``

您可能还希望将类名更改为比"无标题"更具信息性的名称, 
并将测试方法的名称更改为
"test_untitled"以外的名称. 

这是一个通过修改Selenium IDE
生成的Ruby代码创建的简单示例, 
如上所述.

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

文档团队的成员尚未将Selenium RC与Perl或PHP一起使用. 
如果您将Selenium RC和这两种语言一起使用, 
请联系文档团队(请参阅贡献一章). 
我们很乐意提供一些您的经验和示例, 以支持Perl和PHP用户.


## 学习 API

Selenium RC API使用命名约定, 
假设您了解Selenese, 
则大部分接口将是不言自明的. 
但是, 在这里, 
我们解释了最关键且可能不太明显的方面.

### 启动浏览器

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

这些示例中的每一个都打开浏览器
并通过为程序变量分配"浏览器实例"来表示该浏览器. 
然后, 该程序变量用于从浏览器调用方法. 
这些方法执行Selenium命令, 
即 *open* 或 *type* 或 *verify* 命令.

创建浏览器实例时所需的参数是: 

* **host**
    指定服务器所在计算机的IP地址. 
    通常, 这是与客户端运行所在的计算机相同的主机, 
    因此在这种情况下, 
    将传递本地主机. 
    在某些客户端中, 
    这是可选参数	
    
* **port**
    指定服务器正在侦听的TCP/IP套接字, 
    以等待客户端建立连接. 
    在某些客户端驱动程序中这也是可选的.
	
* **browser**
    您要在其中运行测试的浏览器. 
    这是一个必需的参数.
	
* **url**
    被测应用程序的基本URL. 
    这是所有客户端库所必需的, 
    并且是启动浏览器-代理-AUT通信所必需的信息

请注意, 某些客户端库要求通过调用浏览器的 `start()` 方法
来明确启动浏览器.

### 运行命令

将浏览器初始化并分配给变量(通常称为"Selenium")后, 
可以通过从浏览器变量调用相应的方法来使其运行Selenese命令. 
例如, 调用Selenium对象的 *type* 方法:

```
    selenium.type("field-id","string to type")
```

在后台, 浏览器实际上将执行 *type* 操作, 
该操作基本上与用户通过以下方式在浏览器中键入输入相同：
使用定位符和您在方法调用期间指定的字符串.

## 报告结果

Selenium RC没有自己的报告结果机制. 
相反, 它允许您使用所选编程语言的功能来构建根据需要定制的报告. 
太好了, 但是如果您只是想快速地为您完成某件事, 该怎么办？
与开发自己的测试报告代码相比, 
现有的库或测试框架通常可以更快地满足您的需求.

### 测试框架报告工具

测试框架可用于许多编程语言. 
这些功能以及提供用于执行测试的灵活测试引擎的主要功能, 
包括用于报告结果的库代码. 
例如, Java有两个常用的测试框架, 
即JUnit和TestNG. 
.NET也有自己的NUnit

我们不会在这里教框架本身；
这超出了本用户指南的范围. 
我们将简单介绍与Selenium相关的框架功能以及您可以应用的一些技术. 
这些测试框架上都有不错的书籍, 
但是互联网上的信息也很多.

### 测试报告库

还提供了专门创建的第三方库, 
用于以您选择的编程语言报告测试结果. 
这些通常支持多种格式, 例如HTML或PDF.

### 最好的方法是什么? 

大多数测试框架的新手都将从框架的内置报告功能开始. 
从那里开始, 大多数人会检查任何可用的库, 
因为与开发自己的库相比, 这样做所花的时间更少. 
毫无疑问, 当您开始使用Selenium时, 
您将开始输入自己的"打印报表"来报告进度. 
这可能会逐渐导致您开发自己的报告, 
这可能与使用库或测试框架同时进行. 
无论如何, 经过最初但短暂的学习后, 
您自然会开发出最适合自己情况的方法.

### 测试报告示例

为了说明, 我们将指导您使用Selenium支持的其他一些语言的一些特定工具. 
此处列出的是常用的, 
并且由本指南的作者广泛使用(因此建议使用).

#### Java测试报告

* 如果使用JUnit开发了Selenium测试用例, 
则可以使用JUnit报告生成测试报告. 

* 如果使用TestNG开发了Selenium测试用例, 
则无需外部任务即可生成测试报告. 
TestNG框架生成一个HTML报告, 
其中列出了测试的详细信息. 

* ReportNG是用于TestNG框架的HTML报告插件. 
它旨在替代默认的TestNG HTML报告. 
ReportNG提供了简单的, 以颜色编码的测试结果视图. 
  
##### 记录Selenese命令

* 记录Selenium可以用于生成测试中所有Selenese命令
以及每个命令的成功或失败的报告. 
记录Selenium扩展了Java客户端驱动程序
以添加此Selenese记录功能.

#### Python测试报告

* 使用Python客户端驱动程序时, 
可以使用HTMLTestRunner生成测试报告.

#### Ruby测试报告

* 如果RSpec框架用于在Ruby中编写Selenium测试用例, 
则其HTML报告可用于生成测试报告.


## 为测试加料

现在, 我们将介绍使用Selenium RC的全部原因, 
并在测试中添加编程逻辑. 
与任何程序相同. 
程序流使用条件语句和迭代进行控制. 
另外, 您可以使用I/O报告进度信息. 
在本节中, 我们将展示一些示例, 
说明如何将编程语言构造与Selenium结合以解决常见的测试问题. 

当您从对页面元素存在的简单测试
过渡到涉及多个网页和变化数据的动态功能测试时, 
您将需要编程逻辑来验证预期结果. 
基本上, Selenium-IDE不支持迭代和标准条件语句. 
您可以通过将Javascript嵌入
Selenese参数中来执行某些条件, 
但是迭代是不可能的, 并且大多数条件在编程语言. 
此外, 您可能需要进行异常处理才能恢复错误. 
出于这些原因及其他原因, 
我们在本节中进行了说明, 
以说明如何使用常见的编程技术为您的自动化测试提供更大的"验证能力".

本节中的示例使用C＃和Java编写, 
尽管代码很简单并且可以轻松地适应其他受支持的语言. 
如果您对面向对象的编程语言有一些基本的了解, 
那么本部分将不难理解.

### 迭代

迭代是人们在测试中需要做的最常见的事情之一. 
例如, 您可能要多次执行搜索. 
或者, 可能是为了验证测试结果, 
您需要处理从数据库返回的"结果集".

使用我们之前使用的相同Google搜索示例, 
让我们检查Selenium搜索结果. 
该测试可以使用Selenese:

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


该代码已重复执行3次相同的步骤. 
但是, 同一代码的多个副本不是良好的编程习惯, 
因为维护起来需要做更多的工作. 
通过使用编程语言, 
我们可以遍历搜索结果以提供更灵活和可维护的解决方案. 

#### 在 `C#` 中  
   
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

### 条件陈述

为了说明在测试中的使用条件, 
我们将从一个示例开始. 
当页面上没有预期的元素时, 
会发生运行Selenium测试时遇到的常见问题. 
例如, 当运行以下行时:

```
   selenium.type("q", "selenium " +s);
```
   
如果页面上没有元素"q", 
则抛出异常:

```java
   com.thoughtworks.selenium.SeleniumException: ERROR: Element q not found
```

这可能会导致测试中止. 
对于某些测试, 这就是您想要的. 
但是通常这是不希望的, 
因为您的测试脚本还有许多其他后续测试需要执行.

更好的方法是先验证元素是否确实存在, 
然后在元素不存在时采取替代方法. 
让我们用Java看一下

```java
   // If element is available on page then perform type operation.
   if(selenium.isElementPresent("q")) {
       selenium.type("q", "Selenium rc");
   } else {
       System.out.printf("Element: " +q+ " is not available on page.")
   }
```
   
这种方法的优点是即使页面上某些UI元素不可用, 
也可以继续执行测试. 


### 从测试中执行JavaScript

在执行Selenium不直接支持的应用程序时, 
JavaScript非常方便. 
Selenium API的 **getEval** 方法. 

考虑具有复选框的应用程序, 
该复选框没有静态标识符. 
在这种情况下, 
可以评估Selenium RC中的JavaScript以获取所有复选框的ID, 
然后执行它们. 

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

计算页面上的图像数量:

```java   
   selenium.getEval("window.document.images.length;");
```

记住要在DOM表达式的情况下使用window对象, 
因为默认情况下是指Selenium窗口, 
而不是测试窗口.

## 服务器选项

启动服务器时, 
命令行选项可用于更改默认服务器行为.

回想一下, 通过运行以下命令来启动服务器.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar
``` 

要查看选项列表, 
请使用 ``-h`` 选项运行服务器.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -h
``` 

您会看到服务器可以使用的所有选项的列表
以及每个选项的简要说明. 
提供的描述并不总是足够的, 
因此我们提供了一些更重要选项的解释.


### 代理配置

如果您的AUT在需要身份验证的HTTP代理后面, 
则应使用以下命令配置http.proxyHost, 
http.proxyPort, 
http.proxyUser
和http.proxyPassword. 

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -Dhttp.proxyHost=proxy.com -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password
``` 

### 多窗口模式

如果您使用的是Selenium 1.0, 
则可能会跳过本节, 因为多窗口模式是默认行为. 
但是, 在版本1.0之前, 
Selenium默认在子frame中运行测试中的应用程序, 
如下所示.

![单窗口模式](/images/documentation/legacy/selenium_rc_single_window_mode.png)

某些应用程序无法在子框架中正常运行, 
需要将其加载到窗口的顶部框架中. 
多窗口模式选项允许AUT在单独的窗口中运行, 
而不是在默认帧中运行, 
然后在默认帧中可以拥有所需的顶部帧.

![多窗口模式](/images/documentation/legacy/selenium_rc_multi_window_mode.png)

对于旧版本的Selenium, 
您必须使用以下选项明确指定多窗口模式:

```bash   
   -multiwindow 
``` 

从Selenium RC 1.0开始, 
如果您想在一个框架内运行测试(即使用早期Selenium版本的标准), 
则可以使用选项将此状态指定给Selenium服务器

```bash   
   -singlewindow 
``` 

### 指定Firefox配置文件

除非您为每个实例分别指定一个配置文件, 
否则Firefox将不会同时运行两个实例. 
Selenium RC 1.0和更高版本会自动在单独的配置文件中运行, 
因此, 如果您使用的是Selenium 1.0, 则可以跳过本节. 
但是, 如果您使用的是旧版本的Selenium, 
或者需要使用特定的配置文件进行测试
(例如添加https证书或安装了一些插件), 
则需要明确指定配置文件. 

首先, 要创建一个单独的Firefox配置文件, 
请遵循以下步骤. 
打开Windows"开始"菜单, 选择"运行", 
然后键入并输入以下内容之一:

```bash   
   firefox.exe -profilemanager 
``` 

```bash   
   firefox.exe -P 
``` 

使用对话框创建新的配置文件. 
然后, 当您运行Selenium服务器时, 
告诉它使用带有服务器命令行选项 *\-firefoxProfileTemplate* 的
新Firefox配置文件, 
并使用其文件名和目录路径指定配置文件的路径.

```bash   
   -firefoxProfileTemplate "path to the profile" 
``` 

**注意**:  确保将您的个人资料放在默认文件夹之外的新文件夹中！！！！
如果删除配置文件, 
则Firefox配置文件管理器工具将删除文件夹中的所有文件, 
无论它们是否是配置文件. 
   
有关Firefox配置文件的更多信息, 
请参见 [Mozilla的知识库](http://support.mozilla.com/en/kb/Managing+profiles)

### 使用-htmlSuite在服务器内直接运行Selenese

通过将html文件传递到服务器的命令行, 
可以直接在Selenium 服务器中运行Selenese html文件. 例如:

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -htmlSuite "*firefox" 
   "http://www.google.com" "c:\absolute\path\to\my\HTMLSuite.html" 
   "c:\absolute\path\to\my\results.html"
``` 

这将自动启动您的HTML套件, 
运行所有测试并保存带有结果的漂亮HTML报告.

*注意:* 使用此选项时, 
服务器将启动测试并等待指定的秒数以完成测试；
否则, 服务器将停止测试. 
如果在这段时间内未完成测试, 
则该命令将以非零的退出代码退出, 
并且不会生成任何结果文件.

此命令行很长, 因此在键入时要小心. 
请注意, 这要求您传递HTML Selenese套件, 
而不是单个测试. 
另请注意-htmlSuite选项与 ``-interactive``不兼容, 
您不能同时运行两者e.

### Selenium 服务器日志

#### 服务端日志

启动Selenium服务器时,  **-log** 选项可用于
将Selenium服务器报告的有价值的调试信息
记录到文本文件中.

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -log selenium.log
``` 
   
该日志文件比标准控制台日志更详细
(它包含DEBUG级别的日志消息). 
日志文件还包括记录器名称和记录消息的线程的ID号. 例如:   

```bash   
   20:44:25 DEBUG [12] org.openqa.selenium.server.SeleniumDriverResourceHandler - 
   Browser 465828/:top frame1 posted START NEW
``` 
   
消息格式为

```bash   
   TIMESTAMP(HH:mm:ss) LEVEL [THREAD] LOGGER - MESSAGE
``` 
   
此消息可能是多行.

#### 浏览器端日志

浏览器端的JavaScript(Selenium Core)也记录重要消息. 
在许多情况下, 这些对于最终用户比常规的Selenium 服务器日志更有用. 
要访问浏览器端日志, 
请将 **-browserSideLog**参数传递给Selenium服务器.


```bash   
   java -jar selenium-server-standalone-<version-number>.jar -browserSideLog
``` 
   
**-browserSideLog** 必须与 **-log** 参数结合使用, 
以将browserSideLogs(以及所有其他DEBUG级别的日志记录消息)记录到文件中.


## 指定特定浏览器的路径

您可以为Selenium RC指定到特定浏览器的路径. 
如果您使用同一浏览器的不同版本, 
并且希望使用特定的浏览器, 这将很有用. 
另外, 它用于允许您的测试在Selenium RC不直接支持的浏览器上运行. 
在指定运行模式时, 
请使用*custom说明符, 
后跟浏览器可执行文件的完整路径:

```bash   
   *custom <path to browser> 
``` 

   
## Selenium RC 架构

*注意:* 本主题试图说明Selenium RC背后的技术实现. 
Selenium用户了解这一点并不是基本知识, 
但对于理解您将来可能会发现的一些问题可能很有用.
   
要详细了解Selenium RC Server的工作原理
以及为什么使用代理注入和增强特权模式, 
您必须首先了解 `同源政策`. 
   
### 同源策略

Selenium面临的主要限制是同源策略. 
此安全限制适用于市场上的每个浏览器, 
其目的是确保一个站点的内容永远不会被另一个站点的脚本访问. 
同源策略规定, 浏览器中加载的任何代码只能在该网站的域内运行. 
它无法在其他网站上执行功能. 
因此, 例如, 如果浏览器在加载www.mysite.com时加载了JavaScript代码, 
则即使该网站是您的另一个网站, 
也无法针对www.mysite2.com运行该加载的代码. 
如果可能的话, 如果您在其他标签上打开了帐户页面, 
则在您打开的任何网站上放置的脚本都将能够读取银行帐户上的信息. 
这称为XSS(跨站点脚本).

要在此政策下工作, 
必须将Selenium-Core(及其实现所有魔术的JavaScript命令)
放置在与被测应用程序相同的来源(相同的URL)中. 

从历史上看, Selenium-Core受此问题限制, 
因为它是用JavaScript实现的. 
但是, Selenium RC不受"同一来源"政策的限制. 
它使用Selenium服务器作为代理可以避免此问题. 
从本质上讲, 它告诉浏览器该浏览器正在服务器提供的单个"欺骗"网站上工作. 

*注意:* 您可以在Wikipedia页面上找到有关此主题的其他信息, 
这些信息与同源策略和XSS有关. 


### 代理注入

Selenium用来避免"同源策略"的第一种方法是代理注入. 
在代理注入模式下, 
Selenium 服务器充当客户端配置的 **HTTP proxy** [^1]
位于浏览器和Test[^2]中. 
然后, 它在虚构的URL下掩盖AUT
(嵌入Selenium-Core和测试集, 并像它们来自同一来源一样进行交付).    


[^1]: 代理人是中间的第三人, 两人之间传球. 它充当将AUT传送到浏览器的"网络服务器". 作为代理, Selenium服务器可以"说谎"AUT的真实URL.      
   
[^2]: 启动浏览器时使用的配置文件将localhost:4444设置为HTTP代理, 这就是为什么浏览器执行的任何HTTP请求都将通过Selenium服务器并且响应将通过它而不是真实服务器通过的原因.    

以下是架构图. 

![架构图 1](/images/documentation/legacy/selenium_rc_architecture_diagram_1.png)

当测试套件以您喜欢的语言开始时, 会发生以下情况:

1. 客户端/驱动程序与Selenium-RC服务器建立连接.
2. Selenium RC服务器启动带有URL的浏览器(或重用旧的浏览器), 
该URL将Selenium-Core的JavaScript注入浏览器加载的网页中.
3. 客户端驱动程序将Selenese命令传递到服务器.
4. 服务器解释命令, 然后触发相应的JavaScript执行以在浏览器中执行该命令. 
Selenium-Core指示浏览器按照第一条指令操作, 
通常会打开AUT的页面.
5. 浏览器收到打开的请求, 
并从Selenium RC服务器
(设置为供浏览器使用的HTTP代理)
中请求网站的内容.
6. Selenium RC服务器与Web服务器进行通信以请求页面, 
并在收到页面后将页面发送给浏览器, 
以掩盖其来源, 以使页面看起来与Selenium-Core来自同一服务器
(这使Selenium-Core可以遵从使用同源策略).
7. 浏览器接收网页并将其呈现在为其保留的框架/窗口中.
   
### 特权浏览器

此方法中的此工作流程与代理注入非常相似, 
但主要区别在于浏览器以一种称为 *增强特权* 的特殊模式启动, 
该模式允许网站执行通常不允许的操作
(例如XSS_或填充文件上传输入)和对Selenium非常有用的东西). 
通过使用这些浏览器模式, 
Selenium Core能够直接打开AUT并与其内容进行读取/交互, 
而不必将整个AUT传递给Selenium RC服务器.

以下是架构图. 

![架构图 1](/images/documentation/legacy/selenium_rc_architecture_diagram_2.png)

当测试套件以您喜欢的语言开始时, 
会发生以下情况:

1. 客户端/驱动程序与Selenium-RC服务器建立连接.
2. Selenium RC服务器启动一个带有URL的浏览器(或重用旧的浏览器), 
该URL将在网页中加载Selenium-Core.
3. Selenium-Core从客户端/驱动程序获取第一条指令
(通过向Selenium RC服务器发出的另一个HTTP请求).
4. Selenium-Core对该第一条指令起作用, 
通常会打开AUT的页面.
5. 浏览器收到打开请求, 
并向Web服务器询问该页面. 
浏览器收到网页后, 将其呈现在为其保留的框架/窗口中.

## 处理HTTPS和安全弹出窗口

当许多应用程序需要发送加密的信息(例如密码或信用卡信息)时, 
它们从使用HTTP切换到HTTPS. 
这在当今的许多网络应用程序中很常见. 
Selenium RC支持这一点. 

为了确保HTTPS站点是真实的, 浏览器将需要安全证书. 
否则, 当浏览器使用HTTPS访问AUT时, 
将假定该应用程序不被"信任". 
发生这种情况时, 浏览器将显示安全弹出窗口, 
并且无法使用Selenium RC关闭这些弹出窗口

在Selenium RC测试中处理HTTPS时, 
必须使用支持该模式并为您处理安全证书的运行模式. 
您在测试程序初始化Selenium时指定运行模式. 

在Selenium RC 1.0 beta 2和更高版本中, 
将\*firefox或\*iexplore用于运行模式. 
在早期版本中, 包括Selenium RC 1.0 beta 1, 
在运行模式中使用\*chrome或\*iehta. 
使用这些运行模式, 您将不需要安装任何特殊的安全证书. 
Selenium RC会为您处理.

在1.0版中, 建议使用运行模式\*firefox或\*iexplore. 
但是, 还有\*iexploreproxy和\*firefoxproxy的其他运行模式. 
提供这些仅是为了向后兼容, 
除非传统测试程序要求, 否则不应使用它们. 
如果您的应用程序打开其他浏览器窗口, 
则它们的使用将对安全证书处理和多个窗口的运行产生限制. 

在早期版本的Selenium RC中, 
\*chrome或\*iehta是支持HTTPS和安全弹出窗口处理的运行模式. 
尽管它们变得相当稳定并且许多人使用了它们, 
但这些被认为是"实验模式". 
如果使用的是Selenium 1.0, 
则不需要, 也不应使用这些较早的运行模式.

### 安全证书说明

通常, 浏览器将通过安装您已经拥有的安全证书来信任您正在测试的应用程序. 
您可以在浏览器的选项或Internet属性中进行检查
(如果您不知道AUT的安全证书, 请咨询系统管理员). 
当Selenium加载浏览器时, 
它将注入代码以拦截浏览器和服务器之间的消息. 
浏览器现在认为不受信任的软件正试图看起来像您的应用程序. 
它通过弹出消息提醒您. 

为解决此问题, Selenium RC(再次使用支持此功能的运行模式时)
将在浏览器可以访问它的位置临时将其自己的安全证书安装到客户端计算机上. 
这会欺骗浏览器以使其正在访问的网站与您的AUT不同, 
从而有效地抑制了弹出窗口. 

Selenium早期版本使用的另一种方法是安装Selenium安装随附的Cybervillians安全证书. 
但是, 大多数用户不再需要这样做. 
如果您以代理注入模式运行Selenium RC, 
则可能需要显式安装此安全证书. 


## 支持其他浏览器和浏览器配置

除了Internet Explorer和Mozilla Firefox外, 
Selenium API还支持在多种浏览器上运行. 
请访问https://selenium.dev网站以获取受支持的浏览器. 
另外, 当不直接支持浏览器时, 
您仍然可以在测试应用程序启动时通过使用
"\*custom"运行模式(即代替\*firefox或\*iexplore)
对所选浏览器运行Selenium测试. 
这样, 您可以将路径传递到API调用内的浏览器可执行文件. 
也可以从服务器以交互方式完成此操作.

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\MyBrowser.exe&2=http://www.google.com
```


### 使用不同的浏览器配置运行测试

通常, Selenium RC自动配置浏览器, 
但是如果您使用"\*custom"运行模式启动浏览器, 
则可以强制Selenium RC照原样启动浏览器, 
而无需使用自动配置.

例如, 您可以使用这样的自定义配置启动Firefox:

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\firefox.exe&2=http://www.google.com
```

请注意, 以这种方式启动浏览器时, 
必须手动配置浏览器以将Selenium服务器用作代理. 
通常, 这仅意味着打开浏览器首选项并将"localhost:4444"指定为HTTP代理, 
但是不同浏览器的说明可能会有根本不同. 
有关详细信息, 请查阅浏览器的文档.

请注意, Mozilla浏览器的启动和停止方式可能会有所不同. 
可能需要设置MOZ_NO_REMOTE环境变量, 
以使Mozilla浏览器的行为更加可预测. 
Unix用户应避免使用Shell脚本启动浏览器. 
通常最好直接使用二进制可执行文件(例如firefox-bin).

   
## 解决常见问题

在开始使用Selenium RC时, 
通常会遇到一些潜在的问题. 
我们在这里介绍它们及其解决方案. 

### 无法连接到服务器

当您的测试程序无法连接到Selenium 服务器时, 
Selenium会在您的测试程序中引发异常. 
它应该显示此消息或类似的消息:

```bash
    "Unable to connect to remote server (Inner Exception Message: 
	No connection could be made because the target machine actively 
	refused it )"
    
	(using .NET and XP Service Pack 2) 
```

如果看到这样的消息, 请确保已启动Selenium服务器. 
如果是这样, 则Selenium客户端库和Selenium服务器之间的连接存在问题. 

从Selenium RC开始时, 
大多数人开始是在同一台计算机上运行测试程序(带有Selenium客户端库)
和Selenium服务器. 
为此, 请使用"localhost"作为连接参数. 
我们建议您以这种方式开始, 
因为它可以减少您入门时可能出现的网络问题的影响. 
假设您的操作系统具有典型的网络和TCP/IP设置, 
那么您应该没有什么困难. 
实际上, 许多人选择以这种方式运行测试.  

但是, 如果您确实想在远程计算机上运行Selenium服务器, 
则假设两台计算机之间具有有效的TCP/IP连接, 
则连接应该很好.    

如果连接困难, 可以使用常用的网络工具, 
例如*ping*,
  *telnet*, *ifconfig(Unix)/ipconfig* (Windows) 等, 
以确保您具有有效的网络连接. 
如果不熟悉这些, 则系统管理员可以为您提供帮助.
 
### 无法加载浏览器

好的, 这并非友好的错误消息, 
很抱歉, 但是如果Selenium服务器无法加载浏览器, 
您可能会看到此错误.

```bash
    (500) Internal Server Error
```

这可能是由于

* Firefox(Selenium 1.0之前的版本)无法启动, 
因为浏览器已经打开, 
并且您未指定单独的配置文件. 
请参阅"服务器选项"下有关Firefox配置文件的部分
* 您使用的运行模式与您计算机上的任何浏览器都不匹配. 
程序打开浏览器时检查您传递给Selenium的参数. 
* 您已明确指定浏览器的路径(使用"\*custom" –参见上文), 
但该路径不正确. 检查以确保路径正确. 
还要检查用户组, 以确保浏览器和"\*custom"参数没有已知问题.

### Selenium 找不到AUT

如果您的测试程序成功启动了浏览器, 
但是浏览器未显示您正在测试的网站, 
则最可能的原因是您的测试程序未使用正确的URL. 

这很容易发生. 
当您使用Selenium-IDE导出脚本时, 
它会插入一个虚拟URL. 
您必须手动将URL更改为正确的URL才能测试您的应用程序. 

### Firefox在准备配置文件时拒绝关闭

最常见的情况是在Firefox上运行Selenium RC测试程序, 
但是已经运行了Firefox浏览器会话, 
并且在启动Selenium服务器时未指定单独的配置文件. 
测试程序中的错误如下所示:

```bash
    Error:  java.lang.RuntimeException: Firefox refused shutdown while 
    preparing a profile 
```

以下是来自服务器的完整错误消息:

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

要解决此问题, 
请参阅"指定单独的Firefox配置文件"部分

### 版本问题

确保您的Selenium版本支持您的浏览器版本. 
例如, Selenium RC 0.92不支持Firefox3. 
有时您可能很幸运(例如我). 
但不要忘记检查您使用的Selenium版本支持哪些浏览器版本. 
如有疑问, 请使用最新版本的Selenium和浏览器使用最广泛的版本.

### 启动服务器时出现错误消息: "(不支持的major.minor版本49.0)"

此错误表明您使用的Java版本不正确. 
Selenium服务器需要Java 1.5或更高版本. 

要检查您的Java版本, 请从命令行运行.

```bash
   java -version
```

您应该看到一条消息, 显示Java版本.

```bash
   java version "1.5.0_07"
   Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_07-b03)
   Java HotSpot(TM) Client VM (build 1.5.0_07-b03, mixed mode)
```

如果看到较低的版本号, 则可能需要更新JRE, 
或者只需将其添加到PATH环境变量中即可.


### 404 error when running the getNewBrowserSession command

如果尝试打开" http://www.google.com/selenium-server/"上的页面时遇到404错误, 
则一定是因为Selenium服务器未正确配置为代理. 
Selenium-server"目录在google.com上不存在；
仅当正确配置了代理后, 该目录才存在. 
代理配置在很大程度上取决于如何使用firefox, 
iexplore, opera或custom启动浏览器.

* iexplore：如果使用\*iexplore启动浏览器, 
则可能是Internet Explorer的代理设置有问题. 
Selenium服务器尝试在Internet选项控制面板中配置全局代理设置. 
您必须确保在Selenium服务器启动浏览器时正确配置了这些配置. 
尝试查看" Internet选项"控制面板. 
单击"连接"选项卡, 然后单击"局域网设置".     
  * 如果您需要使用代理来访问要测试的应用程序, 
  则需要使用" -Dhttp.proxyHost"启动Selenium服务器. 
  有关更多详细信息, 请参见 `代理配置` .
  * 您也可以尝试手动配置代理, 然后使用\*custom或\*iehta浏览器启动器启动浏览器.
      	   
* custom: 使用\*custom时, 必须正确(手动)配置代理, 
否则会出现404错误. 
仔细检查您是否正确配置了代理设置. 
要检查您是否正确配置了代理, 
则是试图故意不正确地配置浏览器. 
尝试将浏览器配置为使用错误的代理服务器主机名或错误的端口. 
如果您错误地成功配置了浏览器的代理设置, 
则浏览器将无法连接到Internet, 
这是一种确保正在调整相关设置的方法.
  
* 对于其他浏览器(\*firefox, \*opera), 
我们会自动为您硬编码代理, 
因此此功能没有已知问题. 
如果您遇到404错误, 
并已按照本用户指南进行操作, 
请仔细将结果发布到用户组, 
以获取用户社区的帮助.
      
### 权限被拒绝错误

发生此错误的最常见原因是您的会话试图跨域
(例如, 从http://domain1访问页面, 
然后从http://domain2访问页面)来违反同源策略. 
协议(从http://domainX移至https://domainX).

当JavaScript尝试查找尚不可用
(在页面完全加载之前)或不再可用
(在页面开始卸载之后)的UI对象时, 
也会发生此错误. 
最常见的情况是AJAX页面正在处理页面的某些部分
或独立于较大页面而加载和/或重新加载的子frame. 

该错误可能是间歇性的. 
通常, 用调试器不可能重现该问题, 
因为问题是由于竞争条件引起的, 
当将调试器的开销添加到系统中时, 
这些竞争条件是无法再现的. 
许可问题在本教程中进行了详细介绍. 
仔细阅读有关`同源策略`, `代理注入`的部分. 


### 处理浏览器弹出窗口

在Selenium测试中可以得到几种"弹出窗口". 
如果Selenium命令是由浏览器而不是AUT发起的, 
则可能无法通过运行Selenium命令来关闭这些弹出窗口. 
您可能需要知道如何进行管理. 
每种类型的弹出窗口都需要以不同的方式处理.

* HTTP基本身份验证对话框：
这些对话框提示输入用户名/密码登录到站点. 
要登录到需要HTTP基本身份验证的站点, 
请使用 `RFC 1738`中所述的URL中的用户名和密码, 
如下所示：open
(" http//myusername:myuserpassword@myexample.com/blah/blah/blah"). 

* SSL证书警告：
当Selenium RC启用为代理时, 
它会自动尝试欺骗SSL证书. 
请参阅HTTPS部分中的更多内容. 
如果您的浏览器配置正确, 您将永远不会看到SSL证书警告, 
但是您可能需要将浏览器配置为信任我们危险的"CyberVillains" SSL证书颁发机构. 
再次, 请参阅HTTPS部分以了解如何执行此操作.

* 模态JavaScript警报/确认/提示对话框：
Selenium试图向您隐藏这些对话框
(通过替换window.alert, window.confirm和window.prompt), 
以便它们不会停止您页面的执行. 
如果您看到警告弹出窗口, 
可能是因为它是在页面加载过程中触发的, 
对于我们而言, 保护该页面通常为时过早. 
Selenese包含用于断言或验证警报和确认弹出窗口的命令. 
请参阅第4章中有关这些主题的部分.

      
### 在Linux上, 为什么我的Firefox浏览器会话没有关闭?

在Unix/Linux上, 您必须直接调用"firefox-bin", 
因此请确保路径中包含可执行文件. 
如果通过外壳程序脚本执行Firefox, 则该终止浏览器了. 
SeleniumRC将终止该外壳程序脚本, 
使浏览器保持运行状态. 
您可以像这样直接指定firefox-bin的路径.
      
```bash
   cmd=getNewBrowserSession&1=*firefox /usr/local/firefox/firefox-bin&2=http://www.google.com
```

### Firefox \*chrome不适用于自定义配置文件

检查Firefox配置文件文件夹
-> prefs.js->  "//user_pref("browser.startup.page", 0);" 
像这样注释此行："//user_pref("browser.startup.page", 0);" 
然后再试一次. 


### 是否可以在加载父页面时加载自定义弹出窗口(即, 在父页面的javascript window.onload()函数运行之前)

否. Selenium依靠拦截器来确定正在加载的窗口名称. 
如果在onload()函数之后加载窗口, 
则这些拦截器最适合捕获新窗口. 
Selenium可能无法识别在onload功能之前加载的Windows.
  
### Linux上的Firefox

在Unix/Linux上, Selenium 1.0之前的版本需要直接调用"firefox-bin", 
因此, 如果您使用的是以前的版本, 
请确保路径中包含真正的可执行文件. 

在大多数Linux发行版中, 真正的 *firefox-bin* 位于:

```bash
   /usr/lib/firefox-x.x.x/ 
```

其中x.x.x是您当前拥有的版本号. 
因此, 要将该路径添加到用户的路径. 
您将必须将以下内容添加到您的.bashrc文件中:

```bash
   export PATH="$PATH:/usr/lib/firefox-x.x.x/"
```

如有必要, 您可以在测试中直接指定firefox-bin的路径, 如下所示:

```bash
   "*firefox /usr/lib/firefox-x.x.x/firefox-bin"
```

### IE和样式属性

如果您在Internet Explorer上运行测试, 
则无法使用其 `style` 属性来查找元素. 例如:

```bash
    //td[@style="background-color:yellow"]
```

这将在Firefox, Opera或Safari中完美运行, 但不适用于IE. 
IE将  `@style` 中的键解释为大写. 
因此, 即使源代码是小写的, 您也应该使用:

```bash
    //td[@style="BACKGROUND-COLOR:yellow"]
```

如果您的测试打算在多个浏览器上运行, 
那么这将是一个问题, 
但是您可以轻松地对测试进行编码以检测情况
并尝试仅在IE中运行的替代定位器.

### 遇到错误-随着\*googlechrome浏览器的关闭, "无法将对象转换为原始值"

为避免此错误, 您必须使用禁用
同源策略检查的选项启动浏览器: 

```bash
   selenium.start("commandLineFlags=--disable-web-security");
```
   

### IE中遇到的错误-"无法打开应用程序窗口；弹出窗口阻止程序已启用？"

为避免此错误, 您必须配置浏览器：
禁用弹出窗口阻止程序, 
并取消选中工具>>选项>>安全中的"启用保护模式"选项.
