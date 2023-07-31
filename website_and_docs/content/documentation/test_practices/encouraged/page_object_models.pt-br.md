---
title: "Modelos de objetos de página"
linkTitle: "Modelos de objetos de página"
weight: 3
needsTranslation: false
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/page_object_models/",
"/pt-br/documentation/guidelines/page_object_models/"
]
---

Nota: esta página reuniu conteúdos de várias fontes, incluindo
o [Selenium wiki](https://github.com/SeleniumHQ/selenium/wiki/PageObjects) 

## Visão geral

Dentro da interface de usuário (UI) do seu aplicativo web, existem áreas com as quais seus testes interagem. O Page Object modela apenas essas áreas como objetos dentro do código de teste. Isso reduz a quantidade de código duplicado e significa que, se a UI mudar, a correção precisará ser aplicada apenas em um lugar.

Page Object é um padrão de design (Design Pattern) que se tornou popular na automação de testes para melhorar a manutenção de testes e reduzir a duplicação de código. Page Object é uma classe orientada a objetos que serve como interface para uma página do seu AUT (Aplicativo Sob Teste). Os testes usam então os métodos desta classe de Page Object sempre que precisam interagir com a UI dessa página. A vantagem é que, se a UI da página mudar, os próprios testes não precisam mudar, apenas o código dentro do Page Object precisa mudar. Subsequentemente, todas as mudanças para suportar essa nova UI estão localizadas em um lugar.

### Vantagens

* Existe uma separação bem definida entre o código do teste e o código da página especifica.
* Existe um repositório único para os serviços ou operações que a página oferece, em vez de ter esses serviços espalhados pelos testes.

Em ambos os casos, isso permite que quaisquer modificações necessárias devido a mudanças na UI sejam feitas em um lugar somente. Informações úteis sobre esta técnica podem ser encontradas em vários blogs, pois este 'padrão de design de teste (test design pattern)' está se tornando amplamente utilizado. Encorajamos os leitores que desejam saber mais a pesquisar na internet por blogs sobre este assunto. Muitos já escreveram sobre este padrão de design e podem fornecer dicas úteis além do escopo deste guia do usuário. Para começar, vamos ilustrar Page Object com um exemplo simples.

### Exemplos
Primeiro, considere um exemplo, típico da automação de testes, que não usa um objeto de página:

```java
/***
 * Testes da funcionalidade de login
 */
public class Login {

  public void testLogin() {
    // preencha os dados de login na página de entrada
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verifique se a tag h1 é "Hello userName" após o login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

Existem dois problemas com essa abordagem.

* Não há separação entre o método de teste e os localizadores do aplicativo em teste (IDs neste exemplo); ambos estão entrelaçados em um único método. Se a UI do aplicativo em teste muda seus identificadores, layout ou como um login é inserido e processado, o próprio teste deve mudar.
* Os localizadores ID estariam espalhados em vários testes, em todos os testes que tivessem que usar esta página de login.

Aplicando as técnicas de Page Object, este exemplo poderia ser reescrito da seguinte forma no exemplo para uma página de login.

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
    * Faz login como um usuário válido
    *
    * @param userName
    * @param password
    * @return pbjeto da Pagina Inicial
    */
  public HomePage loginValidUser(String userName, String password) {
    driver.findElement(usernameBy).sendKeys(userName);
    driver.findElement(passwordBy).sendKeys(password);
    driver.findElement(signinBy).click();
    return new HomePage(driver);
  }
}
```

E o objeto de página para uma página inicial poderia parecer assim.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsula a Página Inicial
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
    * Obtém a mensagem (tag h1)
    *
    * @return String da mensagem de texto
    */
  public String getMessageText() {
    return driver.findElement(messageBy).getText();
  }

  public HomePage manageProfile() {
    // Encapsulamento da página para gerenciar a funcionalidade do perfil
    return new HomePage(driver);
  }
  /* Mais métodos que oferecem os serviços representados pela Página inicial do usuário logado. Estes métodos por sua vez podem retornar mais Page Object, por exemplo, clicar no botão "Compor email" pode retornar um objeto da classe ComposeMail */
}
```

Então agora, o teste de login usaria esses dois objetos de página da seguinte maneira.

```java
/***
 * Testes da funcionalidade de login
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

Há muita flexibilidade em como o Page Object pode ser projetado, mas existem algumas regras básicas para obter a manutenibilidade desejada do seu código de teste.

## Afirmações em Page Objects
Os Page Objects em si nunca devem fazer verificações ou afirmações. Isso faz parte do seu teste e sempre deve estar dentro do código do teste, nunca em um objeto de página. O objeto de página conterá a representação da página e os serviços que a página fornece por meio de métodos, mas nenhum código relacionado ao que está sendo testado deve estar dentro do objeto de página.

Há uma única verificação que pode e deve estar dentro do objeto de página, e isso é para verificar se a página e possivelmente elementos críticos na página, foram carregados corretamente. Essa verificação deve ser feita ao instanciar o objeto de página. Nos exemplos acima, tanto os construtores SignInPage quanto HomePage verificam se a página esperada está disponível e pronta para solicitações do teste.

## Objetos Componentes de Página (Page Component Object)
Page Object não precisa necessariamente representar todas as partes de uma página. Isso foi [notado por Martin Fowler](https://martinfowler.com/bliki/PageObject.html#footnote-panel-object) nos primeiros dias, enquanto cunhava o termo "objetos de painel (panel objects)".

Os mesmos princípios usados para objetos de página podem ser usados para criar "Objetos Componente de Página", como foi chamado mais tarde, que representam partes discretas da página e podem ser incluídos em Page Object. Esses objetos de componente podem fornecer referências aos elementos dentro dessas partes discretas e métodos para aproveitar a funcionalidade ou comportamento fornecidos por eles.

Por exemplo, uma página de Produtos tem vários produtos.

```html
<!-- Página de Produtos -->
<div class="header_container">
    <span class="title">Products</span>
</div>

<div class="inventory_list">
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
</div>
```

Cada produto é um componente da página de Produtos.

```html
<!-- Inventory Item -->
<div class="inventory_item">
    <div class="inventory_item_name">Backpack</div>
    <div class="pricebar">
        <div class="inventory_item_price">$29.99</div>
        <button id="add-to-cart-backpack">Add to cart</button>
    </div>
</div>
```

A página de Produtos "TEM-UMA (HAS-A)" lista de produtos. This object relationship is called Composition. Essa relação de objeto é chamada de Composição. Em termos mais simples, algo é _composto de_ outra coisa.

```java
public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}

// Page Object
public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
        // Sem afirmações, lança uma exceção se o elemento não for carregado
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(d -> d.findElement(By.className​("header_container")));
    }

    // Retornar uma lista de produtos é um serviço da página
    public List<Product> getProducts() {
        return driver.findElements(By.className​("inventory_item"))
            .stream()
            .map(e -> new Product(e)) // Mapeia WebElement para um componente do produto
            .toList();
    }

    // Retorna um produto específico usando uma função booleana (predicado)
    // Este é o padrão de estratégia comportamental do GoF
    public Product getProduct(Predicate<Product> condition) {
        return getProducts()
            .stream()
            .filter(condition) // Filtra por nome de produto ou preço
            .findFirst()
            .orElseThrow();
    }
}
```

O objeto do componente Produto é usado dentro do objeto de página Produtos.

```java
public abstract class BaseComponent {
    protected WebElement root;

    public BaseComponent(WebElement root) {
        this.root = root;
    }
}

// Objeto Componente da Página (Page Component Object)
public class Product extends BaseComponent {
    // O elemento raiz contém todo o componente
    public Product(WebElement root) {
        super(root); // inventory_item
    }

    public String getName() {
        // A localização de um elemento começa na raiz do componente
        return root.findElement(By.className("inventory_item_name")).getText();
    }

    public BigDecimal getPrice() {
        return new BigDecimal(
                root.findElement(By.className("inventory_item_price"))
                    .getText()
                    .replace("$", "")
            ).setScale(2, RoundingMode.UNNECESSARY); // Higienização e formatação
    }

    public void addToCart() {
        root.findElement(By.id("add-to-cart-backpack")).click();
    }
}
```

Agora, o teste dos produtos usaria o Page Objecto e o Page Component Obeject da seguinte maneira.

```java
public class ProductsTest {
    @Test
    public void testProductInventory() {
        var productsPage = new ProductsPage(driver); // page object
        var products = productsPage.getProducts();
        assertEquals(6, products.size()); // esperado, atual
    }
    
    @Test
    public void testProductPrices() {
        var productsPage = new ProductsPage(driver);

        // Passa uma expressão lambda (predicado) para filtrar a lista de produtos
        // O predicado ou "estratégia" é o comportamento passado como parâmetro
        var backpack = productsPage.getProduct(p -> p.getName().equals("Backpack")); // page component object
        var bikeLight = productsPage.getProduct(p -> p.getName().equals("Bike Light"));

        assertEquals(new BigDecimal("29.99"), backpack.getPrice());
        assertEquals(new BigDecimal("9.99"), bikeLight.getPrice());
    }
}
```

A página e o componente são representados por seus próprios objetos. Ambos os objetos têm apenas métodos para os **serviços** que oferecem, o que corresponde à aplicação do mundo real na programação orientada a objetos.

Você pode até aninhar objetos de componentes dentro de outros objetos de componentes para páginas mais complexas. Se uma página na AUT tiver vários componentes, ou componentes comuns usados em todo o site (por exemplo, uma barra de navegação), então isso pode melhorar a manutenibilidade e reduzir a duplicação de código.

## Outros Padrões de Projeto (Design Patterns) Usados em Testes
Existem outros padrões de projeto que também podem ser usados em testes. Discutir todos esses está além do escopo deste guia do usuário. Aqui, apenas queremos introduzir os conceitos para tornar o leitor ciente de algumas das coisas que podem ser feitas. Como foi mencionado anteriormente, muitos escreveram sobre este tópico e encorajamos o leitor a procurar blogs sobre esses tópicos.

## Notas de Implementação

Page Objects podem ser pensados como se estivessem voltados para duas direções simultaneamente. Voltado para o desenvolvedor de um teste, eles representam os **serviços** oferecidos por uma página específica. Virado para longe do desenvolvedor, eles devem ser a única coisa que tem um conhecimento profundo da estrutura do HTML de uma página (ou parte de uma página). É mais simples pensar nos métodos de um Page Object como oferecendo os "serviços" que uma página oferece, em vez de expor os detalhes e a mecânica da página. Como exemplo, pense na caixa de entrada de qualquer sistema de email baseado na web. Entre os serviços que oferece estão a capacidade de compor um novo e-mail, escolher ler um único e-mail e listar as linhas de assunto dos e-mails na caixa de entrada. Como esses são implementados não deve importar para o teste.

Porque estamos encorajando o desenvolvedor de um teste a tentar pensar sobre os serviços com os quais estão interagindo em vez da implementação, os Page Objects raramente devem expor a instância subjacente do WebDriver. Para facilitar isso, os métodos no Page Object devem retornar outros Page Objects. Isso significa que podemos efetivamente modelar a jornada do usuário em nosso aplicativo. Também significa que se a maneira como as páginas se relacionam entre si mudar (como quando a página de login pede ao usuário para alterar sua senha na primeira vez que eles entram em um serviço quando antes não fazia isso), simplesmente mudando a assinatura do método apropriado fará com que os testes falhem em compilação. Colocando de outra forma; podemos dizer quais testes falhariam sem precisar executá-los quando mudamos a relação entre as páginas e refletimos isso nos PageObjects.

Uma consequência dessa abordagem é que pode ser necessário modelar (por exemplo) tanto um login bem-sucedido quanto um mal-sucedido; ou um clique poderia ter um resultado diferente dependendo do estado do aplicativo. Quando isso acontece, é comum ter vários métodos no PageObject:

```java
public class LoginPage {
    public HomePage loginAs(String username, String password) {
        // ... mágica inteligente acontece aqui
    }
    
    public LoginPage loginAsExpectingError(String username, String password) {
        //  ... falha no login aqui, talvez porque o nome de usuário e/ou a senha estão incorretos
    }
    
    public String getErrorMessage() {
        // Para que possamos verificar se o erro correto é mostrado
    }
}
```

O código apresentado acima mostra um ponto importante: os testes, não os Page Objects, devem ser responsáveis por fazer asserções sobre o estado de uma página. Por exemplo:

```java
public void testMessagesAreReadOrUnread() {
    Inbox inbox = new Inbox(driver);
    inbox.assertMessageWithSubjectIsUnread("I like cheese");
    inbox.assertMessageWithSubjectIsNotUnread("I'm not fond of tofu");
}
```

could be re-written as:

```java
public void testMessagesAreReadOrUnread() {
    Inbox inbox = new Inbox(driver);
    assertTrue(inbox.isMessageWithSubjectIsUnread("I like cheese"));
    assertFalse(inbox.isMessageWithSubjectIsUnread("I'm not fond of tofu"));
}
```

Claro, como em toda diretriz, existem exceções, e uma que é comumente vista com Page Objects é verificar se o WebDriver está na página correta quando instanciamos o Page Object. Isso é feito no exemplo abaixo.

Finalmente, um Page Object não precisa representar uma página inteira. Pode representar uma seção que aparece com frequência dentro de um site ou página, como a navegação do site. O princípio essencial é que há apenas um lugar em sua suíte de testes com conhecimento da estrutura do HTML de uma determinada (parte de uma) página.

## Resumo

* Os métodos públicos representam os serviços que a página oferece
* Tente não expor as entranhas da página
* Geralmente não faça asserções
* Métodos retornam outros PageObjects
*Não precisa representar uma página inteira
* Resultados diferentes para a mesma ação são modelados como métodos diferentes

## Example

```java
public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Verifica se estamos na página correta.
        if (!"Login".equals(driver.getTitle())) {
            // Alternativamente, poderíamos navegar para a página de login, talvez fazendo logout primeiro
            throw new IllegalStateException("This is not the login page");
        }
    }

    // A página de login contém vários elementos HTML que serão representados como WebElements.
    // Os localizadores para esses elementos devem ser definidos apenas uma vez.
        By usernameLocator = By.id("username");
        By passwordLocator = By.id("passwd");
        By loginButtonLocator = By.id("login");

    // A página de login permite que o usuário digite seu nome de usuário no campo de nome de usuário
    public LoginPage typeUsername(String username) {
        // Este é o único lugar que "sabe" como entrar com um nome de usuário
        driver.findElement(usernameLocator).sendKeys(username);

        // Retorna o objeto de página atual, já que esta ação não navega para uma página representada por outro Page Object
        return this;	
    }
Este é o único lugar que "sabe" como entrar com uma senha
    // A página de login permite que o usuário digite sua senha no campo de senha
    public LoginPage typePassword(String password) {
        // Este é o único lugar que "sabe" como entrar com uma senha
        driver.findElement(passwordLocator).sendKeys(password);

        // Retorna o objeto de página atual, já que esta ação não navega para uma página representada por outro Page Object
        return this;	
    }

    // A página de login permite que o usuário envie o formulário de login
    public HomePage submitLogin() {
        // Este é o único lugar que envia o formulário de login e espera que o destino seja a página inicial.
        // Um método separado deve ser criado para a instância de clicar em login enquanto espera uma falha de login.
        driver.findElement(loginButtonLocator).submit();

        // Retorna um novo objeto de página representando o destino. Caso a página de login vá para algum outro lugar (por exemplo, um aviso legal),
        // então a alteração da assinatura do método para este método significará que todos os testes que dependem deste comportamento não serão compilados.
        return new HomePage(driver);	
    }

    // A página de login permite que o usuário envie o formulário de login sabendo que um nome de usuário inválido e/ou senha foram inseridos
    public LoginPage submitLoginExpectingFailure() {
        // Este é o único lugar que envia o formulário de login e espera que o destino seja a página de login devido à falha no login.
        driver.findElement(loginButtonLocator).submit();

        // Retorna um novo objeto de página representando o destino. Caso o usuário seja navegado para a página inicial depois de enviar um login com credenciais
        // que se espera falhar no login, o script falhará quando tentar instanciar o PageObject LoginPage.
        return new LoginPage(driver);	
    }

    // Conceitualmente, a página de login oferece ao usuário o serviço de ser capaz de "entrar"
    // no aplicativo usando um nome de usuário e senha. 
    public HomePage loginAs(String username, String password) {
        // Os métodos PageObject que inserem nome de usuário, senha e enviam login já foram definidos e não devem ser repetidos aqui.
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}
```
