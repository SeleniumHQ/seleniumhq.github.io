---
title: "Modelos de objetos de página"
linkTitle: "Modelos de objetos de página"
weight: 3
needsTranslation: true
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/page_object_models/",
"/pt-br/documentation/guidelines/page_object_models/"
]
---

Objeto de página é um padrão de design que se tornou popular na automação de teste para
melhorar a manutenção de teste e reduzir a duplicação de código. Um objeto de página é uma
classe orientada a objetos que serve como uma interface para uma página de seu AUT.
Os testes então usam os métodos desta classe de objeto de página sempre que precisam
interagir com a interface do usuário dessa página. O benefício é que, se a IU mudar para
a página, os próprios testes não precisam ser alterados, apenas o código dentro do
o objeto da página precisa ser alterado. Posteriormente, todas as alterações para oferecer suporte a essa nova IU
estão localizados em um só lugar.

O padrão de design do objeto de página oferece as seguintes vantagens:

* Há uma separação clara entre o código de teste e o código específico da página, como
  localizadores (ou seu uso se você estiver usando um mapa de interface do usuário) e layout.
* Existe um único repositório para os serviços ou operações oferecidos pela página
  em vez de ter esses serviços espalhados pelos testes.

Em ambos os casos, isso permite qualquer modificação necessária devido a mudanças na IU
ser feito em um só lugar. Informações úteis sobre esta técnica podem ser encontradas em
vários blogs, já que esse ‘padrão de design de teste’ está se tornando amplamente usado. Nós
incentivamos o leitor que deseja saber mais a pesquisar blogs na internet
nesse assunto. Muitos escreveram sobre este padrão de design e podem fornecer
dicas úteis que vão além do escopo deste guia do usuário. Para começar, no entanto,
vamos ilustrar objetos de página com um exemplo simples.

Primeiro, considere um exemplo, típico de automação de teste, que não usa um
objeto de página:

```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // preenche dados de login na página de entrada
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verifica que a tag h1 é "Hello userName" após o login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

Há dois problemas com esta abordagem.

* Não há separação entre o método de teste e os localizadores AUT (IDs neste exemplo);
ambos estão interligados em um único método. Se a IU da aplicação muda
seus identificadores, layout ou como um login é inserido e processado, o próprio teste
deve mudar.
* Os localizadores do ID estariam espalhados em vários testes, em todos os testes que precisassem
usar esta página de login.

Aplicando as técnicas de objeto de página, este exemplo poderia ser reescrito assim
no exemplo a seguir de um objeto de página para uma página de Sign-in.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsula a página de login.
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
    if (!driver.getTitle().equals("Sign In Page")) {
      throw new IllegalStateException("This is not Sign In Page," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Login como um usuário válido
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

e o objeto de página de uma página inicial pode ter a seguinte aparência.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsula a Home Page
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
    // Encapsulamento da página para gerenciar a funcionalidade do perfil
    return new HomePage(driver);
  }
  /* Mais métodos fornecendo o serviços representados pela Home Page
  do usuário logado. Esses métodos por sua vez podem retornar mais Page Objects
  por exemplo clicar no botão Compor Email poderia retornar um objeto ComposeMail */
}
```

Portanto, agora, o teste de login usaria esses dois objetos de página da seguinte maneira.

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

Há muita flexibilidade em como os objetos de página podem ser projetados, mas
existem algumas regras básicas para obter a manutenção desejada de seu
código de teste.

Os próprios objetos de página nunca devem fazer verificações ou afirmações. Isto é
parte do seu teste e deve estar sempre dentro do código do teste, nunca em um objeto de página.
O objeto da página conterá a representação da página, e o
serviços que a página fornece por meio de métodos, mas nenhum código relacionado ao que está sendo
testado deve estar dentro do objeto de página.

Há uma única verificação que pode e deve estar dentro do objeto de página e que é para verificar se a página
e, possivelmente, elementos críticos em a página, foram carregados corretamente.
Esta verificação deve ser feita enquanto instanciar o objeto de página.
Nos exemplos acima, ambos SignInPage e os construtores da HomePage verificam se a página
esperada está disponível e pronta para solicitações do teste.

Um objeto de página não precisa necessariamente representar todas as partes da página em si.
Os mesmos princípios usados para objetos de página podem ser usados para
criar "Objetos de _Componente_ de Página" que representam pedaços discretos da
página e podem ser incluídos em objetos de página. Esses objetos de componentes podem
fornecer referências aos elementos dentro desses blocos discretos, e
métodos para utilizar a funcionalidade fornecida por eles. Você também pode
aninhar objetos de componentes dentro de outros objetos de componentes para páginas mais complexas.
Se uma página na aplicação tem vários componentes, ou
componentes usados em todo o site (por exemplo, uma barra de navegação), então
pode melhorar a manutenção e reduzir a duplicação de código.

Existem outros padrões de design que também podem ser usados em testes. Alguns usam um
Page Factory para instanciar seus objetos de página. Discutir tudo isso é
além do escopo deste guia do usuário. Aqui, queremos apenas apresentar o
conceitos para tornar o leitor ciente de algumas coisas que podem ser feitas. Como
foi mencionado anteriormente, muitos escreveram sobre este tópico e nós encorajamos o
leitor para pesquisar blogs sobre esses tópicos.
