---
title: "Manipulação do Browser"
weight: 3
---

<!-- #codeExamples -->
<!-- Remember to cover profile and extensions here -->

## Ruby

Ruby não está instalado por padrão no Windows. Baixe a [versão mais recente](//rubyinstaller.org/downloads)
e execute o instalador. Você podedeixar todas as configurações com os valores padrão, exceto na
tela _Installation Destination and Optional Tasks_ verifique onde você precisa marca a caixa de seleção
_Add Ruby executables to your PATH_. Para utilizar qualquer navegador,
você deve instalar a *gem* de Ruby `selenium-webdriver`. Para instalá-lo, abra o prompt de comando
e digite isto:

```shell
gem install selenium-webdriver
```

Ou, se você usa [Bundler](//bundler.io), adicione esta linha ao
Gemfile:

```ruby
gem "selenium-webdriver"
```

Em seguida, execute o seguinte comando no prompt de comando:

```shell
bundle install
```


## Internet Explorer

O Internet Explorer é instalado por padrão no Windows, portanto nenhuma instalação é
necessária. Para utilizar o Internet Explorer no Windows, você deve baixar o
[driver mais recente do Internet Explorer](https://selenium.dev/downloads/) e colocar o arquivo
em uma pasta que está no `PATH`. Para descobrir quais diretórios estão no `PATH`,
digite `echo% PATH%` no prompt de comando.

```bat
$ echo %PATH%
C:\Ruby200\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem
```

`C:\Ruby200\bin` parece um bom lugar. Descompacte o arquivo `IEDriverServer` e
mova `IEDriverServer.exe` para lá.

Isso deve abrir uma nova janela do Internet Explorer:

```ruby
require "selenium-webdriver"
driver = Selenium::WebDriver.for :internet_explorer
```

## Navegação

### Navegar para

A primeira coisa que você vai querer fazer depois de iniciar um navegador é
abrir o seu site. Isso pode ser feito em uma única linha:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Convenient
driver.get("https://selenium.dev");

//Longer way
driver.navigate().to("https://selenium.dev");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.get("https://selenium.dev")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Convenient way
driver.get 'https://selenium.dev'

# Longer Way
driver.navigate.to 'https://selenium.dev'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.get('https://selenium.dev');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")
  {{< / code-panel >}}
{{< / code-tab >}}

### Coletar a URL atual

Você pode ler a URL atual na barra de endereço do navegador usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getCurrentUrl();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.current_url{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Url;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.current_url{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getCurrentUrl();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.currentUrl{{< / code-panel >}}
{{< / code-tab >}}

### Voltar

Pressionando o botão Voltar do navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().back();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.back(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Back();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.back{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().back();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().back() {{< / code-panel >}}
{{< / code-tab >}}


### Avançar
Pressionando o botão Avançar do navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().forward();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.forward(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Forward();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.forward{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().forward();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().forward(){{< / code-panel >}}
{{< / code-tab >}}

### Atualizar

Atualizando a página atual:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().refresh();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.refresh(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Refresh();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.refresh{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().refresh();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().refresh(){{< / code-panel >}}
{{< / code-tab >}}

### Coletar título

Você pode ler o título da página atual no navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getTitle();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.title{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Title;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.title{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getTitle();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.title{{< / code-panel >}}
{{< / code-tab >}}


## Janelas e guias

### Pega o puxador da janela

O WebDriver não faz distinção entre janelas e guias. E se
seu site abre uma nova guia ou janela, o Selenium permitirá que você trabalhe
usando um puxador de janela. Cada janela tem um identificador único que permanece
persistente em uma única sessão. Você pode pegar o puxador da
janela atual usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getWindowHandle();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.current_window_handle{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.CurrentWindowHandle;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.window_handle{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getWindowHandle();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.windowHandle{{< / code-panel >}}
{{< / code-tab >}}

### Alternando janelas ou guias

Clicar em um link que abre em uma
<a href="https://seleniumhq.github.io" target="_blank"> nova janela </a>
focará a nova janela ou guia na tela, mas o WebDriver não saberá qual
janela que o sistema operacional considera ativa. Para trabalhar com a nova janela
você precisará mudar para ela. Se você tiver apenas duas guias ou janelas abertas,
e você sabe com qual janela você começa, pelo processo de eliminação
você pode percorrer as janelas ou guias que o WebDriver pode ver e alternar
para aquela que não é o original.

No entanto, o Selenium 4 fornece uma nova API
<a href="https://selenium.dev/documentation/en/webdriver/browser_manipulation/#create-new-window-or-new-tab-and-switch"> NewWindow </a>
que cria uma nova guia (ou) nova janela e muda automaticamente para ela.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Store the ID of the original window
String originalWindow = driver.getWindowHandle();

//Check we don't have other windows open already
assert driver.getWindowHandles().size() == 1;

//Click the link which opens in a new window
driver.findElement(By.linkText("new window")).click();

//Wait for the new window or tab
wait.until(numberOfWindowsToBe(2));

//Loop through until we find a new window handle
for (String windowHandle : driver.getWindowHandles()) {
    if(!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle);
        break;
    }
}

//Wait for the new tab to finish loading content
wait.until(titleIs("Selenium documentation"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# Start the driver
with webdriver.Firefox() as driver:
    # Open URL
    driver.get("https://seleniumhq.github.io")

    # Setup wait for later
    wait = WebDriverWait(driver, 10)

    # Store the ID of the original window
    original_window = driver.current_window_handle

    # Check we don't have other windows open already
    assert len(driver.window_handles) == 1

    # Click the link which opens in a new window
    driver.find_element(By.LINK_TEXT, "new window").click()

    # Wait for the new window or tab
    wait.until(EC.number_of_windows_to_be(2))

    # Loop through until we find a new window handle
    for window_handle in driver.window_handles:
        if window_handle != original_window:
            driver.switch_to.window(window_handle)
            break

    # Wait for the new tab to finish loading content
    wait.until(EC.title_is("SeleniumHQ Browser Automation"))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Store the ID of the original window
string originalWindow = driver.CurrentWindowHandle;

//Check we don't have other windows open already
Assert.AreEqual(driver.WindowHandles.Count, 1);

//Click the link which opens in a new window
driver.FindElement(By.LinkText("new window")).Click();

//Wait for the new window or tab
wait.Until(wd => wd.WindowHandles.Count == 2);

//Loop through until we find a new window handle
foreach(string window in driver.WindowHandles)
{
    if(originalWindow != window)
    {
        driver.SwitchTo().Window(window);
        break;
    }
}
//Wait for the new tab to finish loading content
wait.Until(wd => wd.Title == "Selenium documentation");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Store the ID of the original window
original_window = driver.window_handle

#Check we don't have other windows open already
assert(driver.window_handles.length == 1, 'Expected one window')

#Click the link which opens in a new window
driver.find_element(link: 'new window').click

#Wait for the new window or tab
wait.until { driver.window_handles.length == 2 }

#Loop through until we find a new window handle
driver.window_handles.each do |handle|
    if handle != original_window
        driver.switch_to.window handle
        break
    end
end

#Wait for the new tab to finish loading content
wait.until { driver.title == 'Selenium documentation'}
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Store the ID of the original window
const originalWindow = await driver.getWindowHandle();

//Check we don't have other windows open already
assert((await driver.getAllWindowHandles()).length === 1);

//Click the link which opens in a new window
await driver.findElement(By.linkText('new window')).click();

//Wait for the new window or tab
await driver.wait(
    async () => (await driver.getAllWindowHandles()).length === 2,
    10000
  );

//Loop through until we find a new window handle
const windows = await driver.getAllWindowHandles();
windows.forEach(async handle => {
  if (handle !== originalWindow) {
    await driver.switchTo().window(handle);
  }
});

//Wait for the new tab to finish loading content
await driver.wait(until.titleIs('Selenium documentation'), 10000);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Store the ID of the original window
val originalWindow = driver.getWindowHandle()

//Check we don't have other windows open already
assert(driver.getWindowHandles().size() === 1)

//Click the link which opens in a new window
driver.findElement(By.linkText("new window")).click()

//Wait for the new window or tab
wait.until(numberOfWindowsToBe(2))

//Loop through until we find a new window handle
for (windowHandle in driver.getWindowHandles()) {
    if (!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle)
        break
    }
}

//Wait for the new tab to finish loading content
wait.until(titleIs("Selenium documentation"))

  {{< / code-panel >}}
{{< / code-tab >}}

### Criar nova janela (ou) nova guia e alternar
Cria uma nova janela (ou) guia e focará a nova janela ou guia na tela.
Você não precisa mudar para trabalhar com a nova janela (ou) guia. Se você tiver mais de duas janelas
(ou) guias abertas diferentes da nova janela, você pode percorrer as janelas ou guias que o WebDriver pode ver
e mudar para aquela que não é a original.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB);

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Opens a new tab and switches to new tab
driver.switch_to.new_window('tab')

# Opens a new window and switches to new window
driver.switch_to.new_window('window')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Opens a new tab and switches to new tab
driver.SwitchTo().NewWindow(WindowType.Tab)

// Opens a new window and switches to new window
driver.SwitchTo().NewWindow(WindowType.Window)
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Note: The new_window in ruby only opens a new tab (or) Window and will not switch automatically
# The user has to switch to new tab (or) new window

# Opens a new tab and switches to new tab
driver.manage.new_window(:tab)

# Opens a new window and switches to new window
driver.manage.new_window(:window)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Opens a new tab and switches to new tab
await driver.switchTo().newWindow('tab');

// Opens a new window and switches to new window
await driver.switchTo().newWindow('window');

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB)

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< / code-panel >}}
{{< / code-tab >}}

### Fechando uma janela ou guia

Quando você terminar com uma janela ou guia _e_ não é a
última janela ou guia aberta em seu navegador, você deve fechá-la e alternar
de volta para a janela que você estava usando anteriormente. Supondo que você seguiu a
amostra de código na seção anterior, você terá o identificador da janela
anterior armazenado em uma variável. Junte isso e você obterá:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Close the tab or window
driver.close();

//Switch back to the old tab or window
driver.switchTo().window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Close the tab or window
driver.close()

#Switch back to the old tab or window
driver.switch_to.window(original_window)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Close the tab or window
driver.Close();

//Switch back to the old tab or window
driver.SwitchTo().Window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Close the tab or window
driver.close

#Switch back to the old tab or window
driver.switch_to.window original_window
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Close the tab or window
await driver.close();

//Switch back to the old tab or window
await driver.switchTo().window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Close the tab or window
driver.close()

//Switch back to the old tab or window
driver.switchTo().window(originalWindow)

  {{< / code-panel >}}
{{< / code-tab >}}

Esquecer de voltar para outro gerenciador de janela após fechar uma
janela deixará o WebDriver em execução na página agora fechada e
acionar uma **No Such Window Exception**. Você deve trocar
de volta para um identificador de janela válido para continuar a execução.

### Sair do navegador no final de uma sessão

Quando você terminar a sessão do navegador, você deve chamar quit,
em vez de fechar:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.quit();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.quit(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Quit();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.quit{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.quit();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.quit(){{< / code-panel >}}
{{< / code-tab >}}

* quit irá:
   * Fechar todas as janelas e guias associadas a essa sessão do WebDriver
   * Fechar o processo do navegador
   * Fechar o processo do driver em segundo plano
   * Notificar o Selenium Grid de que o navegador não está mais em uso para que possa
    ser usado por outra sessão (se você estiver usando Selenium Grid)

A falha em encerrar deixará processos e portas extras em segundo plano
rodando em sua máquina, o que pode causar problemas mais tarde.

Algumas estruturas de teste oferecem métodos e anotações em que você pode ligar para derrubar no final de um teste.

{{< code-tab >}}
  {{< code-panel language="java" >}}
/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# unittest teardown
# https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
/*
    Example using Visual Studio's UnitTesting
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# UnitTest Teardown
# https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
/**
 * Example using Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}

/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

Se não estiver executando o WebDriver em um contexto de teste, você pode considerar o uso do
`try/finally` que é oferecido pela maioria das linguagens para que uma exceção
ainda limpe a sessão do WebDriver.

{{< code-tab >}}
  {{< code-panel language="java" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
try:
    #WebDriver code here...
finally:
    driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
try {
    //WebDriver code here...
} finally {
    driver.Quit();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
begin
    #WebDriver code here...
ensure
    driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
try {
    //WebDriver code here...
} finally {
    await driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

O WebDriver do Python agora suporta o gerenciador de contexto python,
que ao usar a palavra-chave `with` pode encerrar automaticamente o
driver no fim da execução.

```python
with webdriver.Firefox() as driver:
  # WebDriver code here...

# WebDriver will automatically quit after indentation
```

## Frames e Iframes
Frames são um meio obsoleto de construir um layout de site a partir de
vários documentos no mesmo domínio. É improvável que você trabalhe com eles
a menos que você esteja trabalhando com um webapp pré-HTML5. Iframes permitem
a inserção de um documento de um domínio totalmente diferente, e são
ainda comumente usado.

Se você precisa trabalhar com frames ou iframes, o WebDriver permite que você
trabalhe com eles da mesma maneira. Considere um botão dentro de um iframe.
Se inspecionarmos o elemento usando as ferramentas de desenvolvimento do navegador, podemos
ver o seguinte:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

Se não fosse pelo iframe, esperaríamos clicar no botão
usando algo como:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# This won't work
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

No entanto, se não houver botões fora do iframe, você pode
em vez disso, obter um erro _no such element_. Isso acontece porque o Selenium é
ciente apenas dos elementos no documento de nível superior. Para interagir com
o botão, precisamos primeiro mudar para o quadro, de forma semelhante
a como alternamos janelas. WebDriver oferece três maneiras de mudar para
um frame.

### Usando um WebElement

Alternar usando um WebElement é a opção mais flexível. Você pode
encontrar o quadro usando seu seletor preferido e mudar para ele.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

# switch to selected iframe
driver.switch_to.frame(iframe)

# Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

# Switch to the frame
driver.switch_to.frame iframe

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

### Usando um *name* ou ID
Se o seu frame ou iframe tiver um atributo id ou name, ele pode ser
usado alternativamente. Se o name ou ID não for exclusivo na página, o
primeiro encontrado será utilizado.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Switch frame by id
driver.switch_to.frame('buttonframe')

# Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Switch by ID
driver.switch_to.frame 'buttonframe'

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

### Usando um índice

Também é possível usar o índice do frame, podendo ser
consultado usando _window.frames_ em JavaScript.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Switch to the second frame
driver.switch_to.frame(1)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# switching to second iframe based on index
iframe = driver.find_elements_by_tag_name('iframe')[1]

# switch to selected iframe
driver.switch_to.frame(iframe)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< / code-panel >}}
{{< / code-tab >}}


### Deixando um frame

Para deixar um iframe ou frameset, volte para o conteúdo padrão
como a seguir:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# switch back to default content
driver.switch_to.default_content()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Return to the top level
driver.switch_to.default_content
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< / code-panel >}}
{{< / code-tab >}}

## Gerenciamento de janelas
A resolução da tela pode impactar como seu aplicativo da web é renderizado, então
WebDriver fornece mecanismos para mover e redimensionar a janela do navegador.

### Coletar o tamanho da janela
Obtém o tamanho da janela do navegador em pixels.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Access each dimension individually
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//Or store the dimensions and query them later
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Access each dimension individually
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

# Or store the dimensions and query them later
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Access each dimension individually
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//Or store the dimensions and query them later
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Access each dimension individually
width = driver.manage.window.size.width
height = driver.manage.window.size.height

# Or store the dimensions and query them later
size = driver.manage.window.size
width1 = size.width
height1 = size.height
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Access each dimension individually
const { width, height } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const width1 = rect.width;
const height1 = rect.height;
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Access each dimension individually
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//Or store the dimensions and query them later
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
  {{< / code-panel >}}
{{< / code-tab >}}

### Definir o tamanho da janela

Restaura a janela e define o tamanho da janela.
{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.set_window_size(1024, 768){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.resize_to(1024,768){{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< / code-panel >}}
{{< / code-tab >}}

### Coletar posição da janela

Busca as coordenadas da coordenada superior esquerda da janela do navegador.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Access each dimension individually
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// Or store the dimensions and query them later
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Access each dimension individually
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

# Or store the dimensions and query them later
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Access each dimension individually
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//Or store the dimensions and query them later
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Access each dimension individually
x = driver.manage.window.position.x
y = driver.manage.window.position.y

# Or store the dimensions and query them later
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Access each dimension individually
const { x, y } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Access each dimension individually
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// Or store the dimensions and query them later
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

  {{< / code-panel >}}
{{< / code-tab >}}

## Definir posição da janela

Move a janela para a posição escolhida.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().setPosition(new Point(0, 0));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Move the window to the top left of the primary monitor
driver.set_window_position(0, 0)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Move the window to the top left of the primary monitor
driver.Manage().Window.Position = new Point(0, 0);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.manage.window.move_to(0,0)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Move the window to the top left of the primary monitor
await driver.manage().window().setRect({ x: 0, y: 0 });
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().position = Point(0,0)
    {{< / code-panel >}}
{{< / code-tab >}}

### Maximizar janela
Aumenta a janela. Para a maioria dos sistemas operacionais, a janela irá preencher
a tela, sem bloquear os próprios menus do sistema operacional e
barras de ferramentas.

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().maximize();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.maximize_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Maximize();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.maximize{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().maximize();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().maximize(){{< / code-panel >}}
{{< / code-tab >}}

### Minimizar janela
Minimiza a janela do contexto de navegação atual.
O comportamento exato deste comando é específico para
gerenciadores de janela individuais.
 
Minimizar Janela normalmente oculta a janela na bandeja do sistema.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().minimize();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.minimize_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Minimize();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.minimize{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().minimize();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().minimize(){{< / code-panel >}}
{{< / code-tab >}}

### Janela em tamanho cheio

Preenche a tela inteira, semelhante a pressionar F11 na maioria dos navegadores.

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().fullscreen();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.fullscreen_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.FullScreen();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.full_screen{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().fullscreen();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().fullscreen(){{< / code-panel >}}
{{< / code-tab >}}

### TakeScreenshot

Usado para capturar a tela do contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
retorna a captura de tela codificada no formato Base64.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import org.openqa.selenium.*;

public class SeleniumTakeScreenshot {
    public static void main(String args[]) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.example.com");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
        driver.quit();
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

# Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()

{{< / code-panel >}}
  {{< code-panel language="csharp" >}}
  using OpenQA.Selenium;
  using OpenQA.Selenium.Chrome;
  using OpenQA.Selenium.Support.UI;

  var driver = new ChromeDriver();
  driver.Navigate().GoToUrl("http://www.example.com");
  Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
  screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

  # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    await driver.get('https://www.example.com');
    // Returns base64 encoded string
    let encodedString = await driver.takeScreenshot();
    await fs.writeFileSync('./image.png', encodedString, 'base64');
    await driver.quit();
}())
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import com.oracle.tools.packager.IOUtils.copyFile
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File

fun main(){
    val driver =  ChromeDriver()
    driver.get("https://www.example.com")
    val scrFile = (driver as TakesScreenshot).getScreenshotAs<File>(OutputType.FILE)
    copyFile(scrFile, File("./image.png"))
    driver.quit()
}
   {{< / code-panel >}}
{{< / code-tab >}}

###  TakeElementScreenshot

Usado para capturar a imagem de um elemento para o contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
retorna a captura de tela codificada no formato Base64.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class SeleniumelementTakeScreenshot {
  public static void main(String args[]) throws IOException {
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.example.com");
    WebElement element = driver.findElement(By.cssSelector("h1"));
    File scrFile = element.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("./image.png"));
    driver.quit();
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

# Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;
    using OpenQA.Selenium.Support.UI;

    // Webdriver
    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("http://www.example.com");

    // Fetch element using FindElement
    var webElement = driver.FindElement(By.CssSelector("h1"));

    // Screenshot for the element
    var elementScreenshot = (webElement as ITakesScreenshot).GetScreenshot();
    elementScreenshot.SaveAsFile("screenshot_of_element.png");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

  # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
   let driver = await new Builder()
       .forBrowser('chrome')
       .build();

   await driver.get('https://www.example.com');
   let ele = await driver.findElement(By.css("h1"));
   // Captures the element screenshot
   let encodedString = await ele.takeScreenshot(true);
   await fs.writeFileSync('./image.png', encodedString, 'base64');
   await driver.quit();
}())
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.apache.commons.io.FileUtils
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.*
import java.io.File

fun main() {
    val driver = ChromeDriver()
    driver.get("https://www.example.com")
    val element = driver.findElement(By.cssSelector("h1"))
    val scrFile: File = element.getScreenshotAs(OutputType.FILE)
    FileUtils.copyFile(scrFile, File("./image.png"))
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}


### Executar Script

Executa o snippet de código JavaScript no
contexto atual de um frame ou janela selecionada.

{{< code-tab >}}
  {{< code-panel language="java" >}}
    //Creating the JavascriptExecutor interface object by Type casting
      JavascriptExecutor js = (JavascriptExecutor)driver;
    //Button Element
      WebElement button =driver.findElement(By.name("btnLogin"));
    //Executing JavaScript to click on element
      js.executeScript("arguments[0].click();", button);
    //Get return value from script
      String text = (String) js.executeScript("return arguments[0].innerText", button);
    //Executing JavaScript directly
      js.executeScript("console.log('hello world')");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
    # code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
    # code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

### Imprimir Página

Imprime a página atual dentro do navegador

_Nota: isto requer que navegadores Chromium estejam no modo sem cabeçalho_

{{< code-tab >}}
  {{< code-panel language="java" >}}
    import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder} = require('selenium-webdriver');
  const chrome = require('selenium-webdriver/chrome');
  let opts = new chrome.Options();
  let fs = require('fs');
  (async function example() {
  let driver = new Builder()
  .forBrowser('chrome')
  .setChromeOptions(opts.headless())
  .build();
  await driver.get('https://www.selenium.dev');
  try {
  let base64 = await driver.printPage({pageRanges:["1-2"]});
  await fs.writeFileSync('./test.pdf', base64, 'base64');
  } catch (e) {
  console.log(e)
  }
  await driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}
