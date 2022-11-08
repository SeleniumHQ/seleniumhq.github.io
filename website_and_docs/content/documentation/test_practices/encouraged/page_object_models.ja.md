---
title: "ページオブジェクトモデル"
linkTitle: "ページオブジェクトモデル"
weight: 3
needsTranslation: true
aliases: [
"/documentation/ja/guidelines_and_recommendations/page_object_models/",
"/ja/documentation/guidelines/page_object_models/"
]
---

ページオブジェクトは、テストメンテナンスを強化し、コードの重複を減らすためのテスト自動化で一般的になったデザインパターンです。
ページオブジェクトは、AUT（テスト対象アプリケーション）のページへのインターフェイスとして機能するオブジェクト指向クラスです。
テストは、そのページのUIと対話する必要があるときは常に、このページオブジェクトクラスのメソッドを使用します。
利点は、ページのUIが変更された場合、テスト自体を変更する必要はなく、ページオブジェクト内のコードのみを変更する必要があることです。
その後、その新しいUIをサポートするためのすべての変更は1か所に配置されます。

ページオブジェクトデザインパターンには、次の利点があります。

* テストコードと、ロケーター（またはUIマップを使用している場合はロケーター）、レイアウトなどのページ固有のコードを明確に分離します。
* これらのサービスをテスト全体に分散させるのではなく、ページによって提供されるサービスまたは操作用の単一のリポジトリがあります。

どちらの場合でも、これにより、UIの変更により必要な変更をすべて1か所で行うことができます。
この'テストデザインパターン'が広く使用されるようになったため、この手法に関する有用な情報は多数のブログで見つけることができます。
詳細を知りたい読者には、このテーマに関するブログをインターネットで検索することをお勧めします。
多くの人がこの設計パターンについて書いており、このユーザーガイドの範囲を超えた有用なヒントを提供できます。
ただし、簡単に始めるために、ページオブジェクトを簡単な例で説明します。

最初に、ページオブジェクトを使用しないテスト自動化の典型的な例を考えてみましょう。

```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // fill login data on sign-in page
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign_in")).click();

    // verify h1 tag is "Hello userName" after login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

このアプローチには2つの問題があります。

* テスト方法とAUTのロケーター（この例ではID）の間に区別はありません。
どちらも単一のメソッドで絡み合っています。
AUTのUIが識別子、レイアウト、またはログインの入力および処理方法を変更する場合、テスト自体を変更する必要があります。
* IDロケーターは、このログインページを使用する必要があったすべてのテストで、複数のテストに分散されます。

ページオブジェクトの手法を適用すると、この例は、サインインページのページオブジェクトの次の例のように書き換えることができます。

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class SignInPage {
  protected WebDriver driver;

  // <input name="user_name" type="text" value="">
  private By usernameBy = By.name("user_name");
  // <input name="password" type="password" value="">
  private By passwordBy = By.name("password");
  // <input name="sign_in" type="submit" value="SignIn">
  private By signinBy = By.name("sign_in");

  public SignInPage(WebDriver driver){
    this.driver = driver;
    if (!driver.getTitle().equals("Home Page of logged in user")) {
      throw new IllegalStateException("This is not Home Page of logged in user," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Login as valid user
    *
    * @param userName
    * @param password
    * @return HomePage object
    */
  public HomePage loginValidUser(String userName, String password) {
    driver.findElement(usernameBy).sendKeys(userName);
    driver.findElement(passwordBy).sendKeys(password);
    driver.findElement(signinBy).click();
    return new HomePage(driver);
  }
}
```

そして、ホームページのページオブジェクトは次のようになります。

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Home Page
 */
public class HomePage {
  protected WebDriver driver;

  // <h1>Hello userName</h1>
  private By messageBy = By.tagName("h1");

  public HomePage(WebDriver driver){
    this.driver = driver;
    if (!driver.getTitle().equals("Home Page of logged in user")) {
      throw new IllegalStateException("This is not Home Page of logged in user," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Get message (h1 tag)
    *
    * @return String message text
    */
  public String getMessageText() {
    return driver.findElement(messageBy).getText();
  }

  public HomePage manageProfile() {
    // Page encapsulation to manage profile functionality
    return new HomePage(driver);
  }
  /* More methods offering the services represented by Home Page
  of Logged User. These methods in turn might return more Page Objects
  for example click on Compose mail button could return ComposeMail class object */
}
```

したがって、ログインテストでは、これら2つのページオブジェクトを次のように使用します。

```java
/***
 * Tests login feature
 */
public class TestLogin {

  @Test
  public void testLogin() {
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = signInPage.loginValidUser("userName", "password");
    assertThat(homePage.getMessageText(), is("Hello userName"));
  }

}
```

ページオブジェクトの設計方法には多くの柔軟性がありますが、テストコードの望ましい保守性を得るための基本的なルールがいくつかあります。

ページオブジェクト自体は、検証やアサーションを行うべきではありません。
これはテストの一部であり、常にページオブジェクトではなく、テストのコード内にある必要があります。
ページオブジェクトには、ページの表現と、ページがメソッドを介して提供するサービスが含まれますが、テスト対象に関連するコードはページオブジェクト内に存在しないようにします。

ページオブジェクト内に存在する可能性のある単一の検証があります。
これは、ページおよびページ上の重要な要素が正しく読み込まれたことを検証するためのものです。
この検証は、ページオブジェクトをインスタンス化する間に実行する必要があります。
上記の例では、SignInPageコンストラクターとHomePageコンストラクターの両方が期待するページを取得し、テストからの要求に対応できることを確認します。

ページオブジェクトは、必ずしもページ全体を表す必要はありません。
ページオブジェクトデザインパターンは、ページ上のコンポーネントを表すために使用できます。
AUTのページに複数のコンポーネントがある場合、コンポーネントごとに個別のページオブジェクトがあると、保守性が向上する場合があります。

また、テストで使用できる他のデザインパターンがあります。
ページファクトリを使用してページオブジェクトをインスタンス化するものもあります。
これらすべてについて議論することは、このユーザーガイドの範囲を超えています。
ここでは、読者にできることのいくつかを認識させるための概念を紹介したいだけです。
前述のように、多くの人がこのトピックについてブログを書いていますし、読者がこれらのトピックに関するブログを検索することをお勧めします。
