---
title: "テスト自動化について"
linkTitle: "テスト自動化について"
weight: 1
aliases: [
"/documentation/ja/introduction/on_test_automation/",
"/ja/documentation/guidelines/on_test_automation/"
]
---


まず、本当にブラウザを使用する必要があるかどうかを自問することから始めます。
ある時点で複雑なWebアプリケーションで作業している場合、おそらくブラウザを開いて実際にテストする必要があるでしょう。

ただし、Seleniumテストなどの機能的なエンドユーザーテストの実行には費用がかかります。
さらに、それらは通常、効果的に実行するために適切なインフラストラクチャを配置する必要があります。
単体テストなどのより軽量なテストアプローチを使用して、または下位レベルのアプローチを使用して、テストすることを実行できるかどうかを常に自問するのは良いルールです。

Webブラウザーのテストビジネスに参加していることを確認し、Selenium環境でテストの記述を開始できるようになったら、通常は3つのステップを組み合わせて実行します。

* データを設定する
* 個別の一連のアクションを実行する
* 結果を評価する

これらの手順はできるだけ短くしてください。
ほとんどの場合、1つまたは2つの操作で十分です。
ブラウザの自動化は"不安定"であるという評判がありますが、実際には、ユーザーが頻繁に多くを求めることが多いためです。
後の章では、特にブラウザーとWebDriver間の[競合状態を克服する]({{< ref "/waits.md" >}})方法に関する、テストでの断続的な問題を軽減するために使用できる手法に戻ります。

テストを短くして、代替手段がまったくない場合にのみWebブラウザーを使用することで、不安定さを最小限にして多くのテストを実行できます。

Seleniumテストの明確な利点は、ユーザーの観点から、バックエンドからフロントエンドまで、アプリケーションのすべてのコンポーネントをテストする固有の機能です。
つまり、機能テストは実行に費用がかかる可能性がありますが、同時にビジネスに不可欠な大規模な部分も含まれます。

### テスト要件

前述のように、Seleniumテストの実行には費用がかかる場合があります。
どの程度までテストを実行しているブラウザーに依存しますが、歴史的にブラウザーの動作は非常に多様であるため、多くの場合、複数のブラウザーに対するクロステストの目標として述べられてきました。

Seleniumを使用すると、複数のオペレーティングシステム上の複数のブラウザーに対して同じ命令を実行できますが、すべての可能なブラウザー、それらの異なるバージョン、およびそれらが実行される多くのオペレーティングシステムの列挙はすぐに重要な作業になります。

### 例から始めましょう

ラリーは、ユーザーがカスタムユニコーンを注文できるWebサイトを作成しました。

一般的なワークフロー（"ハッピーパス"と呼ぶ）は次のようなものです。

* アカウントを作成する
* ユニコーンを設定する
* ショッピングカートにユニコーンを追加します
* チェックアウトしてお支払い
* ユニコーンについてフィードバックを送る

これらのすべての操作を実行するために1つの壮大なSeleniumスクリプトを作成するのは魅力的です。
**その誘惑に抵抗しましょう！** そうすると、  
a）時間がかかる  
b）ページレンダリングのタイミングの問題に関する一般的な問題が発生する  
c）失敗した場合、簡潔で"一目瞭然"にならない、何がうまくいかなかったかを診断する方法がない  
というテストになります。  

このシナリオをテストするための好ましい戦略は、一連の独立した迅速なテストに分割することです。
各テストには、1つの"理由"が存在します。

2番目のステップであるユニコーンの構成をテストしたいと思います。
次のアクションを実行します。

* アカウントを作成する
* ユニコーンを設定する

これらの手順の残りをスキップしていることに注意してください。
この手順を完了した後、他の小さな個別のテストケースで残りのワークフローをテストします。

開始するには、アカウントを作成する必要があります。
ここには、いくつかの選択があります。

* 既存のアカウントを使用しますか？
* 新しいアカウントを作成しますか？
* 設定を開始する前に考慮する必要があるそのようなユーザーの特別なプロパティはありますか？


この質問への回答方法に関係なく、テストの"データのセットアップ"部分の一部にすると解決します。
ラリーが、ユーザー（またはだれでも）がユーザーアカウントを作成および更新できるAPIを公開している場合は、それを使用してこの質問に回答してください。
可能であれば、資格情報を使用してログインできるユーザーが"手元に"いる場合にのみブラウザを起動します。

各ワークフローの各テストがユーザーアカウントの作成から始まる場合、各テストの実行に何秒も追加されます。
APIの呼び出しとデータベースとの対話は、ブラウザを開いたり、適切なページに移動したり、フォームをクリックして送信されるのを待つなどの高価なプロセスを必要としない、迅速な"ヘッドレス"操作です。

理想的には、1行のコードでこのセットアップフェーズに対処できます。
これは、ブラウザーが起動する前に実行されます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.CreateCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = LoginAs(user.Email, user.Password);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = UserFactory.create_common_user #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.email, user.password)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
var user = userFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
var accountPage = loginAs(user.email, user.password);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

ご想像のとおり、 `UserFactory` を拡張して `createAdminUser()` や `createUserWithPayment()` などのメソッドを提供できます。
重要なのは、これらの2行のコードは、このテストの最終目的であるユニコーンの構成からあなたをそらすものではないということです。

[ページオブジェクトモデル]({{< ref "/page_object_models.md" >}})の込み入った事柄については、後の章で説明しますが、ここで概念を紹介します。

テストは、サイトのページのコンテキスト内で、ユーザーの観点から実行されるアクションで構成される必要があります。
これらのページはオブジェクトとして保存され、Webページがどのように構成され、アクションがどのように実行されるかに関する特定の情報が含まれます。

どんなユニコーンが欲しいですか？
ピンクが必要かもしれませんが、必ずしもそうではありません。
紫は最近非常に人気があります。
彼女はサングラスが必要ですか？
スタータトゥー？
これらの選択は困難ですが、テスターとしての最大の関心事です。
発送センターが適切なユニコーンを適切な人に送信することを確認する必要があります。

この段落では、ボタン、フィールド、ドロップダウン、ラジオボタン、またはWebフォームについては説明していません。
**また、テストするべきではありません！** 
ユーザーが問題を解決しようとしているようにコードを書きたいと思います。
これを実行する1つの方法を次に示します（前の例から継続）

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.Purple, UnicornAccessories.Sunglasses, UnicornAdornments.StarTattoos);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.AddUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.CreateUnicorn(sparkles);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn.new('Sparkles', UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here.
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
var sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.

var addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
var unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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

  {{< /tab >}}
{{< /tabpane >}}

ユニコーンの設定が完了したら、ステップ3に進んで、ユニコーンが実際に機能することを確認する必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< /tab >}}
  {{< tab header="Python" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles should have been created, with all attributes intact");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles should have been created, with all attributes intact'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assert(unicornConfirmationPage.exists(sparkles), "Sparkles should have been created, with all attributes intact");

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< /tab >}}
{{< /tabpane >}}

テスターはまだこのコードでユニコーンについて話しているだけです。
ボタンもロケーターもブラウザーコントロールもありません。
ラリーが来週、Ruby-on-Railsが好きではなくなったと判断し、Fortranフロントエンドを使用して最新のHaskellバインディングでサイト全体を再実装することを決めた場合でも、アプリケーションを _モデル化する_ この方法により、これらのテストレベルのコマンドを所定の位置に変えずに維持できます。

ページオブジェクトは、サイトの再設計に準拠するために若干のメンテナンスが必要になりますが、これらのテストは同じままです。
この基本的な設計を採用することで、可能な限りブラウザに面した最小限の手順でワークフローを進めていきたいと思うでしょう。
次のワークフローでは、ユニコーンをショッピングカートに追加します。
カートの状態が適切に維持されていることを確認するために、おそらくこのテストを何度も繰り返す必要があります。
開始する前に、カートに複数のユニコーンがありますか？
ショッピングカートには何個収容できますか？
同じ名前や機能で複数作成すると、壊れますか？
既存のものを保持するだけですか、それとも別のものを追加しますか？

ワークフローを移動するたびに、アカウントを作成し、ユーザーとしてログインし、ユニコーンを設定する必要を避けたいと考えています。
理想的には、APIまたはデータベースを介してアカウントを作成し、ユニコーンを事前設定できるようになります。
その後、ユーザーとしてログインし、きらめきを見つけてカートに追加するだけです。

### 自動化するかしないか

自動化は常に有利ですか？
テストケースの自動化をいつ決定する必要がありますか？

テストケースを自動化することは必ずしも有利ではありません。
手動テストがより適切な場合があります。
たとえば、近い将来にアプリケーションのユーザーインターフェースが大幅に変更される場合は、自動化を書き換える必要があるかもしれません。
また、テストの自動化を構築する時間が足りない場合もあります。
短期的には、手動テストの方が効果的です。
アプリケーションの期限が非常に厳しい場合、現在利用できるテストの自動化はなく、その期間内にテストを実施することが不可欠です。
手動テストが最適なソリューションです。
