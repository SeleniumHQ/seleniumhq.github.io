---
title: "Migrando do RC para WebDriver"
linkTitle: "Migrando do RC para WebDriver"
weight: 2
aliases: [
"/documentation/pt-br/legacy_docs/migrating_from_rc_to_webdriver/",
"/pt-br/documentation/legacy/migrating_from_rc_to_webdriver/"
]
---


## Como migrar para o Selenium WebDriver


Uma pergunta comum ao adotar o Selenium 2 é qual é a coisa certa a fazer
ao adicionar novos testes a um conjunto existente de testes? Usuários que são novos no
framework podem começar usando as novas APIs WebDriver para escrever seus testes.
Mas e os usuários que já possuem suítes de testes existentes? Este guia é
projetado para demonstrar como migrar seus testes existentes para as novas APIs,
permitindo que todos os novos testes sejam escritos usando os novos recursos oferecidos pelo WebDriver.

O método apresentado aqui descreve uma migração gradativa para as APIs WebDriver sem precisar refazer tudo em um push massivo. Isso significa
que você pode permitir mais tempo para migrar seus testes existentes, que
pode tornar mais fácil para você decidir onde investir seu esforço.

Este guia foi escrito em Java, porque tem o melhor suporte para
fazer a migração. À medida que fornecemos ferramentas melhores para outras linguagens,
este guia deve ser expandido para incluir essas linguagens.


## Porque migrar para o WebDriver


Mover um conjunto de testes de uma API para outra requer uma enorme
quantidade de esforço. Por que você e sua equipe considerariam fazer essa mudança?
Aqui estão alguns motivos pelos quais você deve considerar a migração de seus testes Selenium
para usar o WebDriver.

* API menor e compacta. A API do WebDriver é mais orientada a objetos do que o
Selenium RC API original. Isso pode facilitar o trabalho.
* Melhor emulação das interações do usuário. Sempre que possível, o WebDriver faz
uso de eventos nativos para interagir com uma página da web. Imita melhor a maneira como seus usuários trabalham com seu site e aplicativos. Além do que, o
WebDriver oferece APIs de interações de usuário avançadas que permitem que você
modele interações complexas com seu site.
* Suporte de fornecedores de navegadores. Opera, Mozilla e Google são todos participantes ativos
do desenvolvimento do WebDriver, e cada um tem engenheiros trabalhando
para melhorar a estrutura. Frequentemente, isso significa que o suporte para WebDriver
está embutido no próprio navegador: seus testes são executados tão rápidos e estáveis quanto
possível.


## Antes de começar


A fim de tornar o processo de migração o mais indolor possível,
certifique-se de que todos os seus testes sejam executados corretamente com a versão mais recente do Selenium.
Isso pode parecer óbvio, mas é melhor que seja dito!


## Começando


A primeira etapa ao iniciar a migração é mudar a forma como você obtém
sua instância Selenium. Ao usar Selenium RC, isso é feito assim:

```java
Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.yoursite.com");
selenium.start();
```

Isso deve ser substituído assim:

```java
WebDriver driver = new FirefoxDriver();
Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.yoursite.com");
```

## Próximos passos


Depois que seus testes forem executados sem erros, a próxima etapa é migrar
o código de teste real para usar as APIs WebDriver. Dependendo de quão bem você
abstrair o seu código, pode ser um processo curto ou longo.
Em ambos os casos, a abordagem é a mesma e pode ser resumida simplesmente:
modifique o código para usar a nova API quando for editá-lo.

Se você precisar extrair a implementação WebDriver subjacente
da instância Selenium, você pode simplesmente fazer um cast para WrapsDriver:

```java
WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
```

Isso permite que você continue passando a instância Selenium como
normal, mas desembrulhar a instância do WebDriver conforme necessário.

Em algum ponto, sua base de código usará principalmente as APIs mais recentes.
Neste ponto, você pode inverter o relacionamento, usando WebDriver em tudo
e instanciar uma instância do Selenium sob demanda:

```java
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
```

## Problemas comuns


Felizmente, você não é a primeira pessoa a passar por essa migração,
então, aqui estão alguns problemas comuns que outras pessoas viram e como resolvê-los.


### Clicar e digitar são mais completos


Um padrão comum em um teste de Selenium RC é ver algo como:

```java
selenium.type("name", "exciting tex");
selenium.keyDown("name", "t");
selenium.keyPress("name", "t");
selenium.keyUp("name", "t");
```
    
Isso se baseia no fato de que o "tipo" simplesmente substitui o conteúdo do
elemento identificado sem também disparar todos os eventos que normalmente
seriam disparados se um usuário interagir com a página. As invocações diretas finais
de "key*" faz com que os manipuladores JS sejam acionados conforme o esperado.

Ao usar o WebDriverBackedSelenium, o resultado do preenchimento do campo do formulário seria "exciting texttt": não o que você esperaria!
O motivo disso é que o WebDriver emula com mais precisão o comportamento do usuário, e assim terá disparado eventos o tempo todo.

Esse mesmo fato às vezes pode fazer com que o carregamento da página seja disparado antes do que aconteceria em um teste de Selenium 1.
Você pode dizer que isso aconteceu se uma "StaleElementException" é lançada pelo WebDriver.


### WaitForPageToLoad retorna muito cedo

Descobrir quando o carregamento de uma página está completo é uma tarefa complicada. Queremos dizer
"quando o evento de carregamento dispara", "quando todas as solicitações AJAX são concluídas", "quando
não há tráfego de rede "," quando document.readyState mudou" ou
outra coisa completamente diferente?

WebDriver tenta simular o comportamento original do Selenium, mas isso não
sempre funciona perfeitamente por vários motivos. O motivo mais comum é que é
difícil dizer a diferença entre um carregamento de página que ainda não começou e um
carregamento da página concluído entre as chamadas de método. Isso às vezes significa que
o controle é devolvido ao seu teste antes que a página termine (ou mesmo comece!)
o carregamento.

A solução para isso é esperar por algo específico. Normalmente, isso pode ser o elemento com o qual deseja interagir a seguir, ou para alguma variável Javascript
a ser definida com um valor específico. Um exemplo seria:

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement element= wait.until(visibilityOfElementLocated(By.id("some_id")));
```
    
Onde "visibilityOfElementLocated" é implementado como:

```java
public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
  return new ExpectedCondition<WebElement>() {
    public WebElement apply(WebDriver driver) {
      WebElement toReturn = driver.findElement(locator);
      if (toReturn.isDisplayed()) {
        return toReturn;
      }
      return null;
    }
  };
}
```
 
Isso pode parecer complexo, mas é quase todo um código padrão. O único interessante é que a "condição esperada" será avaliada repetidamente
até que o método "apply" retorne algo que não seja "null"
nem Boolean.FALSE.

Claro, adicionar todas essas chamadas de "wait" pode confundir seu código. E se
esse é o caso, e suas necessidades são simples, considere usar as esperas implícitas:

```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

Ao fazer isso, toda vez que um elemento é localizado, se o elemento não estiver presente,
o local é tentado novamente até que esteja presente ou até 30 segundos
passados.

### Encontrar por seletores XPath ou CSS nem sempre funciona, mas funciona no Selenium 1

No Selenium 1, era comum para o xpath usar uma biblioteca agrupada em vez de
os recursos do próprio navegador. WebDriver sempre usará
os métodos nativos do navegador, a menos que não haja alternativa. Isso significa que expressões xpath complexas
podem falhar em alguns navegadores.

Os seletores CSS no Selenium 1 foram implementados usando a biblioteca Sizzle. Esta biblioteca
implementa um superconjunto da CSS Selector Spec, e nem sempre é claro onde
você cruzou a linha. Se você estiver usando o WebDriverBackedSelenium e usar um
Localizador Sizzle em vez de um Seletor CSS para encontrar elementos, um aviso
ser registrado no console. Vale a pena procurar por eles,
particularmente se os testes estão falhando por não ser capaz de encontrar os elementos.

### Não há nenhum Browserbot

O Selenium RC era baseado no Selenium Core e, portanto, quando você executava
Javascript, você podia acessar bits do Selenium Core para tornar as coisas mais fáceis.
Como o WebDriver não é baseado no Selenium Core, isso não é mais possível.
Como você pode saber se está usando Selenium Core? Simples! Basta olhar para ver
se o seu "getEval" ou chamadas semelhantes usam "selenium" ou "browserbot"
no Javascript avaliado.

Você pode estar usando o browserbot para obter um identificador para a janela atual
ou documento do teste. Felizmente, o WebDriver sempre avalia JS no
contexto da janela atual, então você pode usar "window" ou "document" diretamente.

Como alternativa, você pode usar o browserbot para localizar elementos.
No WebDriver, o idioma para fazer isso é primeiro localizar o elemento,
e então passe isso como um argumento para o Javascript. Portanto:

```java
String name = selenium.getEval(
    "selenium.browserbot.findElement('id=foo', browserbot.getCurrentWindow()).tagName");
```

se torna:

```java
WebElement element = driver.findElement(By.id("foo"));
String name = (String) ((JavascriptExecutor) driver).executeScript(
    "return arguments[0].tagName", element);
```
        
Observe como a variável "element" passada aparece como o primeiro item
na array de "arguments" padrão do JS.       


### A execução de Javascript não retorna nada


O JavascriptExecutor do WebDriver envolverá todo o JS e o avaliará como uma expressão anônima. Isso significa que você precisa usar a palavra-chave "return":

```java
String title = selenium.getEval("browserbot.getCurrentWindow().document.title");
```

se torna:

```java
((JavascriptExecutor) driver).executeScript("return document.title;");
```
    
