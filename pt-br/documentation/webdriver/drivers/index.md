# Sessões de Driver

URL: https://www.selenium.dev/pt-br/documentation/webdriver/drivers/

---

Iniciar e encerrar uma sessão serve para abrir e fechar um navegador.

## Criando Sessões

Criar uma nova sessão corresponde ao comando W3C para [Nova sessão](https://w3c.github.io/webdriver/#new-session)

A sessão é criada automaticamente ao inicializar um novo objeto da classe Driver. 

Cada linguagem permite que uma sessão seja criada com argumentos de uma dessas classes (ou equivalentes):

* [Options](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md) to describe the kind of session you want; default values are used for local, but
  this is required for remote
* Alguma forma de [configuração do cliente HTTP](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/http_client/index.md) (a implementação varia entre as linguagens)
* [Ouvintes](https://www.selenium.dev/pt-br/documentation/webdriver/support_features/listeners/index.md)

### Local Driver

O principal argumento exclusivo para iniciar um driver local inclui informações sobre a inicialização do serviço de driver necessário na máquina local.

* Objeto de [Serviço](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/service/index.md) se aplica apenas a drivers locais e fornece informações sobre o driver do navegador.

**Java**

```java
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options)
  .setChromeService(service)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Driver Remoto

O principal argumento exclusivo para iniciar um driver remoto inclui informações sobre onde executar o código. Leia os detalhes na [seção Driver Remoto](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/remote_webdriver/index.md)

## Encerrando Sessões

Encerrar uma sessão corresponde ao comando W3C para [Excluir uma Sessão](https://w3c.github.io/webdriver/#delete-session).

Nota importante: o método `quit` é diferente do método `close`, e é recomendável sempre usar `quit` para finalizar a sessão.

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

---

Section pages:

- [Opções do navegador](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md): Esses recursos são compartilhados por todos os navegadores.
- [Configuração do Cliente HTTP](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/http_client/index.md)
- [Classe de Serviço do Driver](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/service/index.md)
- [WebDriver Remoto](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/remote_webdriver/index.md)
