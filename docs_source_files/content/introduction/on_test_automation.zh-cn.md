---
title: "关于测试自动化"
weight: 2
---

首先，问问自己是否真的需要使用浏览器。
在某些情况下，如果您正在开发一个复杂的 web 应用程序，
您需要打开一个浏览器并进行实际测试，这种可能性是很大的。

然而，诸如 Selenium 之类的功能性最终用户测试运行起来很昂贵。
此外，它们通常需要大量的基础设施才能有效运行。
经常问问自己，您想要测试的东西是否可以使用更轻量级的测试方法（如单元测试）完成，
还是使用较低级的方法完成，这是一个很好的规则。

一旦确定您正在进行Web浏览器测试业务，
并且您的 Selenium 环境已经准备好开始编写测试，
您通常会执行以下三个步骤的组合：

* 设置数据
* 执行一组离散的操作
* 评估结果

您需要尽可能缩短这些步骤;
一到两个操作在大多数时间内应该足够了。
浏览器自动化具有“脆弱”的美誉，
但实际上那是因为用户经常对它要求过高。
在后面的章节中，我们将回到您可以使用的技术，
为了缓解测试中明显的间歇性问题，
特别是如何克服 浏览器 和 WebDriver 之间的[竞争条件]({{< ref "/webdriver/waits.zh-cn.md" >}})。

通过保持测试简短并仅在您完全没有替代方案时使用Web浏览器，您可以用最小的代码片段来完成很多测试。

Selenium测试的一个显著优势是，它能够从用户的角度测试应用程序的所有组件（从后端到前端）。
因此，换句话说，虽然功能测试运行起来可能很昂贵，但它们同时也包含了大量关键业务部分。

### 测试要求

如前所述，Selenium 测试运行起来可能很昂贵。
在多大程度上取决于您正在运行测试的浏览器，
但历史上浏览器的行为变化太大，以至于通常是针对多个浏览器进行交叉测试的既定目标。

Selenium 允许您在多个操作系统上的多个浏览器上运行相同的指令，
但是对所有可能的浏览器、它们的不同版本以及它们所运行的许多操作系统的枚举将很快成为一项繁重的工作。

### 让我们从一个例子开始

Larry 写了一个网站，允许用户订购他们自己定制的独角兽。

一般的工作流程(我们称之为“幸福之路”)是这样的:

* 创建一个账户
* 配置他们的独角兽
* 添加到购物车
* 检验并付款
* 给出关于他们独角兽的反馈

编写一个宏大的 Selenium 脚本来执行所有这些操作是很诱人的 — 很多人都会尝试这样做。
**抵制诱惑！**
这样做会导致测试:
a) 需要很长时间;
b) 会受到一些与页面呈现时间问题有关的常见问题的影响;
c) 如果失败，它不会给出一个简洁的、“可检查”的方法来诊断出了什么问题。

测试此场景的首选策略是将其分解为一系列独立的、快速的测试，每个测试都有一个存在的“理由”。

假设您想测试第二步：
配置您的独角兽。
它将执行以下操作:

* 创建一个帐户
* 配置一个独角兽

请注意，我们跳过了这些步骤的其余部分，
在完成这一步之后，我们将在其他小的、离散的测试用例中测试工作流的其余部分。

首先，您需要创建一个帐户。在这里您可以做出一些选择：

* 您想使用现有帐户吗？
* 您想创建一个新帐户吗？
* 在配置开始之前，是否需要考虑有任何特殊属性的用户需要吗？

不管您如何回答这个问题，
解决方案是让它成为测试中“设置数据”部分的一部分 - 如果 Larry 公开了一个 API，
使您(或任何人)能够创建和更新用户帐户，
一定要用它来回答这个问题
请确保使用这个 API 来回答这个问题 — 如果可能的话，
您希望只有在您拥有一个用户之后才启动浏览器，您可以使用该用户的凭证进行登录。

如果每个工作流的每个测试都是从创建用户帐户开始的，那么每个测试的执行都会增加许多秒。
调用 API 并与数据库进行通信是快速、“无头”的操作，
不需要打开浏览器、导航到正确页面、点击并等待表单提交等昂贵的过程。

理想情况下，您可以在一行代码中处理这个设置阶段，这些代码将在任何浏览器启动之前执行:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = user_factory.create_common_user() #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.get_email(), user.get_password())
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
val user = UserFactory.createCommonUser() //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< / code-panel >}}
{{< / code-tab >}}

您可以想象，`UserFactory`可以扩展为提供诸如`createAdminUser()`、`createUserWithPayment()`的方法。
关键是，这两行代码不会分散您对此测试的最终目的的注意力：
配置独角兽。

[页面对象模型]({{< ref "/guidelines_and_recommendations/page_object_models.zh-cn.md" >}})
的复杂性将在后面的章节中讨论，但我们将在这里介绍这个概念：

您的测试应该由操作组成，从用户的角度出发，在站点的页面上下文中执行。
这些页面被存储为对象，
其中包含关于 web 页面如何组成以及如何执行操作的特定信息 — 作为测试人员，您应该很少关注这些信息。

您想要什么样的独角兽？
您可能想要粉红色，但不一定。
紫色最近很流行。
她需要太阳镜吗？
明星纹身？
这些选择虽然困难，但是作为测试人员，
您的主要关注点是 — 您需要确保您的订单履行中心将正确的独角兽发送给正确的人，而这就要从这些选择开始。

请注意，我们在该段落中没有讨论按钮，字段，下拉菜单，单选按钮或 Web 表单。
**您的测试也不应该！**
您希望像尝试解决问题的用户一样编写代码。
这是一种方法（从前面的例子继续）：

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here.
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we're already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn()

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
val addUnicornPage = accountPage.addUnicorn()

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< / code-panel >}}
{{< / code-tab >}}

既然您已经配置好了独角兽，
您需要进入第三步:确保它确实有效。

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
//CHECK Boris
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

请注意，测试人员在这段代码中除了谈论独角兽之外还没有做任何事情 — 没有按钮、定位器和浏览器控件。
这种对应用程序建模的方法允许您保持这些测试级别的命令不变，
即使 Larry 下周决定不再喜欢 Ruby-on-Rails，
并决定用最新的带有 Fortran 前端的 Haskell 绑定重新实现整个站点。

为了符合站点的重新设计，您的页面对象需要进行一些小的维护，但是这些测试将保持不变。
采用这一基本设计，您将希望继续使用尽可能少的面向浏览器的步骤来完成您的工作流。
您的下一个工作流程将包括在购物车中添加独角兽。
您可能需要多次迭代此测试，以确保购物车正确地保持其状态：
在开始之前，购物车中是否有多个独角兽？
购物车能装多少？
如果您创建多个具有相同名称或特性，它会崩溃吗？
它将只保留现有的一个还是添加另一个?

每次通过工作流时，您都希望尽量避免创建帐户、以用户身份登录和配置独角兽。
理想情况下，您将能够创建一个帐户，并通过 API 或数据库预先配置独角兽。
然后，您只需作为用户登录，找到 Sparkles，并将它添加到购物车中。
