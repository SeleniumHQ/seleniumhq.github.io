---
title: "Manipulação do Browser"
linkTitle: "Manipulação do Browser"
weight: 6
aliases: [
"/documentation/pt-br/webdriver/browser_manipulation/",
"/pt-br/documentation/webdriver/browser_manipulation/"
]
---

## Ruby

Ruby não está instalado por padrão no Windows. Baixe a [versão mais recente](//rubyinstaller.org/downloads)
e execute o instalador. Você pode deixar todas as configurações com os valores padrão, exceto na
tela _Installation Destination and Optional Tasks_ verifique onde você precisa marca a caixa de seleção
_Add Ruby executables to your PATH_. Para utilizar qualquer navegador, você deve instalar a *gem* de Ruby `selenium-webdriver`. 
Para instalá-lo, abra o prompt de comando e digite isto:

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
Algo parecido com isto deve ser apresentado:
```bat
$ echo %PATH%
C:\Ruby200\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem
```

Encorajamos a criar o seguinte caminho `C:\Ruby200\bin`. Descompacte o arquivo `IEDriverServer` baixado e
mova `IEDriverServer.exe` para lá.

Isso deve abrir uma nova janela do Internet Explorer:

```ruby
require "selenium-webdriver"
driver = Selenium::WebDriver.for :internet_explorer
```

## Navegação

### Navegar para

A primeira coisa que você vai querer fazer depois de iniciar um navegador é
abrir o seu site. Isso pode ser feito em uma única linha, utilize o seguinte comando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Convenient
driver.get("https://selenium.dev");

//Longer way
driver.navigate().to("https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("https://selenium.dev")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Convenient way
driver.get 'https://selenium.dev'

# Longer Way
driver.navigate.to 'https://selenium.dev'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://selenium.dev');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")
  {{< /tab >}}
{{< /tabpane >}}

### Coletar a URL atual

Você pode ler a URL atual na barra de endereço do navegador usando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_url{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}

### Voltar

Pressionando o botão Voltar do navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().back();{{< /tab >}}
  {{< tab header="Python" >}}driver.back(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Back();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().back();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}


### Avançar
Pressionando o botão Avançar do navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().forward();{{< /tab >}}
  {{< tab header="Python" >}}driver.forward(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Forward();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().forward();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

### Atualizar

Atualizando a página atual:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Python" >}}driver.refresh(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Refresh();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}

### Coletar título

Você pode ler o título da página atual no navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


## Janelas e guias

### Pegue o idenficador da janela

O WebDriver não faz distinção entre janelas e guias. E se
seu site abre uma nova guia ou janela, o Selenium permitirá que você trabalhe
usando um identificador. Cada janela tem um identificador único que permanece
persistente em uma única sessão. Você pode pegar o identificador atual usando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_window_handle{{< /tab >}}
  {{< tab header="CSharp" >}}driver.CurrentWindowHandle;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.window_handle{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.windowHandle{{< /tab >}}
{{< /tabpane >}}

### Alternando janelas ou guias

Clicar em um link que abre em uma
<a href="https://seleniumhq.github.io" target="_blank"> nova janela </a>
focará a nova janela ou guia na tela, mas o WebDriver não saberá qual
janela que o sistema operacional considera ativa. Para trabalhar com a nova janela
você precisará mudar para ela. Se você tiver apenas duas guias ou janelas abertas,
e você sabe com qual janela você iniciou, pelo processo de eliminação
você pode percorrer as janelas ou guias que o WebDriver pode ver e alternar
para aquela que não é o original.

No entanto, o Selenium 4 fornece uma nova API [NewWindow](#criar-nova-janela-ou-nova-guia-e-alternar) 
que cria uma nova guia (ou) nova janela e muda automaticamente para ela.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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

  {{< /tab >}}
{{< /tabpane >}}

### Criar nova janela (ou) nova guia e alternar
Cria uma nova janela (ou) guia e focará a nova janela ou guia na tela.
Você não precisa mudar para trabalhar com a nova janela (ou) guia. Se você tiver mais de duas janelas
(ou) guias abertas diferentes da nova janela, você pode percorrer as janelas ou guias que o WebDriver pode ver
e mudar para aquela que não é a original.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB);

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW);
  {{< /tab >}}
  {{< tab header="Python" >}}
# Opens a new tab and switches to new tab
driver.switch_to.new_window('tab')

# Opens a new window and switches to new window
driver.switch_to.new_window('window')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Opens a new tab and switches to new tab
driver.SwitchTo().NewWindow(WindowType.Tab)

// Opens a new window and switches to new window
driver.SwitchTo().NewWindow(WindowType.Window)
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Note: The new_window in ruby only opens a new tab (or) Window and will not switch automatically
# The user has to switch to new tab (or) new window

# Opens a new tab and switches to new tab
driver.manage.new_window(:tab)

# Opens a new window and switches to new window
driver.manage.new_window(:window)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Opens a new tab and switches to new tab
await driver.switchTo().newWindow('tab');

// Opens a new window and switches to new window
await driver.switchTo().newWindow('window');

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB)

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< /tab >}}
{{< /tabpane >}}

### Fechando uma janela ou guia

Quando você fechar uma janela ou guia _e_ que não é a
última janela ou guia aberta em seu navegador, você deve fechá-la e alternar
de volta para a janela que você estava usando anteriormente. Supondo que você seguiu a
amostra de código na seção anterior, você terá o identificador da janela
anterior armazenado em uma variável. Junte isso e você obterá:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Close the tab or window
driver.close();

//Switch back to the old tab or window
driver.switchTo().window(originalWindow);
  {{< /tab >}}
  {{< tab header="Python" >}}
#Close the tab or window
driver.close()

#Switch back to the old tab or window
driver.switch_to.window(original_window)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Close the tab or window
driver.Close();

//Switch back to the old tab or window
driver.SwitchTo().Window(originalWindow);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
#Close the tab or window
driver.close

#Switch back to the old tab or window
driver.switch_to.window original_window
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Close the tab or window
await driver.close();

//Switch back to the old tab or window
await driver.switchTo().window(originalWindow);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Close the tab or window
driver.close()

//Switch back to the old tab or window
driver.switchTo().window(originalWindow)

  {{< /tab >}}
{{< /tabpane >}}

Esquecer de voltar para outro gerenciador de janela após fechar uma
janela deixará o WebDriver em execução na página agora fechada e
acionara uma **No Such Window Exception**. Você deve trocar
de volta para um identificador de janela válido para continuar a execução.

### Sair do navegador no final de uma sessão

Quando você terminar a sessão do navegador, você deve chamar a função _quit()_,
em vez de fechar:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.quit();{{< /tab >}}
  {{< tab header="Python" >}}driver.quit(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Quit();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.quit{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.quit();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.quit(){{< /tab >}}
{{< /tabpane >}}

* _quit()_ irá:
   * Fechar todas as janelas e guias associadas a essa sessão do WebDriver
   * Fechar o processo do navegador
   * Fechar o processo do driver em segundo plano
   * Notificar o Selenium Grid de que o navegador não está mais em uso para que possa
    ser usado por outra sessão (se você estiver usando Selenium Grid)

A falha em encerrar deixará processos e portas extras em segundo plano
rodando em sua máquina, o que pode causar problemas mais tarde.

Algumas estruturas de teste oferecem métodos e anotações em que você pode ligar para derrubar no final de um teste.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
# unittest teardown
# https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
/*
    Example using Visual Studio's UnitTesting
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# UnitTest Teardown
# https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
/**
 * Example using Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

Se não estiver executando o WebDriver em um contexto de teste, você pode considerar o uso do
`try/finally` que é oferecido pela maioria das linguagens para que uma exceção
ainda limpe a sessão do WebDriver.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
try:
    #WebDriver code here...
finally:
    driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
try {
    //WebDriver code here...
} finally {
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
begin
    #WebDriver code here...
ensure
    driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
try {
    //WebDriver code here...
} finally {
    await driver.quit();
}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# This won't work
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

No entanto, se não houver botões fora do iframe, você pode
em vez disso, obter um erro _no such element_. Isso acontece porque o Selenium é
ciente apenas dos elementos no documento de nível superior. Para interagir com
o botão, precisamos primeiro mudar para o quadro, de forma semelhante
a como alternamos janelas. WebDriver oferece três maneiras de mudar para
um frame.

### Usando um WebElement

Alternar usando um WebElement é a opção mais flexível. Você pode
encontrar o quadro usando seu seletor preferido e mudar para ele.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

# switch to selected iframe
driver.switch_to.frame(iframe)

# Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

# Switch to the frame
driver.switch_to.frame iframe

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### Usando um *name* ou ID
Se o seu frame ou iframe tiver um atributo id ou name, ele pode ser
usado alternativamente. Se o name ou ID não for exclusivo na página, o
primeiro encontrado será utilizado.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Switch frame by id
driver.switch_to.frame('buttonframe')

# Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Switch by ID
driver.switch_to.frame 'buttonframe'

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### Usando um índice

Também é possível usar o índice do frame, podendo ser
consultado usando _window.frames_ em JavaScript.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Switch to the second frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
# switching to second iframe based on index
iframe = driver.find_elements_by_tag_name('iframe')[1]

# switch to selected iframe
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


### Deixando um frame

Para deixar um iframe ou frameset, volte para o conteúdo padrão
como a seguir:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
# switch back to default content
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Return to the top level
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}

## Gerenciamento de janelas
A resolução da tela pode impactar como seu aplicativo da web é renderizado, então
WebDriver fornece mecanismos para mover e redimensionar a janela do navegador.

### Coletar o tamanho da janela
Obtém o tamanho da janela do navegador em pixels.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Access each dimension individually
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//Or store the dimensions and query them later
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Access each dimension individually
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

# Or store the dimensions and query them later
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Access each dimension individually
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//Or store the dimensions and query them later
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Access each dimension individually
width = driver.manage.window.size.width
height = driver.manage.window.size.height

# Or store the dimensions and query them later
size = driver.manage.window.size
width1 = size.width
height1 = size.height
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Access each dimension individually
const { width, height } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const width1 = rect.width;
const height1 = rect.height;
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Access each dimension individually
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//Or store the dimensions and query them later
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
  {{< /tab >}}
{{< /tabpane >}}

### Definir o tamanho da janela

Restaura a janela e define o tamanho da janela.
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< /tab >}}
  {{< tab header="Python" >}}driver.set_window_size(1024, 768){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.resize_to(1024,768){{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< /tab >}}
{{< /tabpane >}}

### Coletar posição da janela

Busca as coordenadas da coordenada superior esquerda da janela do navegador.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Access each dimension individually
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// Or store the dimensions and query them later
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Access each dimension individually
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

# Or store the dimensions and query them later
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Access each dimension individually
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//Or store the dimensions and query them later
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
#Access each dimension individually
x = driver.manage.window.position.x
y = driver.manage.window.position.y

# Or store the dimensions and query them later
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Access each dimension individually
const { x, y } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Access each dimension individually
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// Or store the dimensions and query them later
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

  {{< /tab >}}
{{< /tabpane >}}

## Definir posição da janela

Move a janela para a posição escolhida.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().setPosition(new Point(0, 0));
  {{< /tab >}}
  {{< tab header="Python" >}}
# Move the window to the top left of the primary monitor
driver.set_window_position(0, 0)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Move the window to the top left of the primary monitor
driver.Manage().Window.Position = new Point(0, 0);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.manage.window.move_to(0,0)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Move the window to the top left of the primary monitor
await driver.manage().window().setRect({ x: 0, y: 0 });
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().position = Point(0,0)
    {{< /tab >}}
{{< /tabpane >}}

### Maximizar janela
Aumenta a janela. Para a maioria dos sistemas operacionais, a janela irá preencher
a tela, sem bloquear os próprios menus do sistema operacional e
barras de ferramentas.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Python" >}}driver.maximize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Maximize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.maximize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().maximize(){{< /tab >}}
{{< /tabpane >}}

### Minimizar janela
Minimiza a janela do contexto de navegação atual.
O comportamento exato deste comando é específico para
gerenciadores de janela individuais.
 
Minimizar Janela normalmente oculta a janela na bandeja do sistema.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Python" >}}driver.minimize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Minimize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.minimize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().minimize(){{< /tab >}}
{{< /tabpane >}}

### Janela em tamanho cheio

Preenche a tela inteira, semelhante a pressionar F11 na maioria dos navegadores.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Python" >}}driver.fullscreen_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.FullScreen();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.full_screen{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().fullscreen(){{< /tab >}}
{{< /tabpane >}}

### TakeScreenshot

Usado para capturar a tela do contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
retorna a captura de tela codificada no formato Base64.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

# Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()

{{< /tab >}}
  {{< tab header="CSharp" >}}
  using OpenQA.Selenium;
  using OpenQA.Selenium.Chrome;
  using OpenQA.Selenium.Support.UI;

  var driver = new ChromeDriver();
  driver.Navigate().GoToUrl("http://www.example.com");
  Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
  screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

  # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
   {{< /tab >}}
{{< /tabpane >}}

###  TakeElementScreenshot

Usado para capturar a imagem de um elemento para o contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
retorna a captura de tela codificada no formato Base64.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

# Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

  # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}


### Executar Script

Executa o snippet de código JavaScript no
contexto atual de um frame ou janela selecionada.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
# Stores the header element
header = driver.find_element(By.CSS_SELECTOR, "h1")

# Executing JavaScript to capture innerText of header element
driver.execute_script('return arguments[0].innerText', header)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
   //creating Chromedriver instance
	IWebDriver driver = new ChromeDriver();
	//Creating the JavascriptExecutor interface object by Type casting
	IJavaScriptExecutor js = (IJavaScriptExecutor) driver;
	//Button Element
	IWebElement button = driver.FindElement(By.Name("btnLogin"));
	//Executing JavaScript to click on element
	js.ExecuteScript("arguments[0].click();", button);
	//Get return value from script
	String text = (String)js.ExecuteScript("return arguments[0].innerText", button);
	//Executing JavaScript directly
	js.ExecuteScript("console.log('hello world')");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Stores the header element
header = driver.find_element(css: 'h1')

# Get return value from script
result = driver.execute_script("return arguments[0].innerText", header)

# Executing JavaScript directly
driver.execute_script("alert('hello world')")
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Stores the header element
val header = driver.findElement(By.cssSelector("h1"))

// Get return value from script
val result = driver.executeScript("return arguments[0].innerText", header)

// Executing JavaScript directly
driver.executeScript("alert('hello world')")
  {{< /tab >}}
{{< /tabpane >}}

### Imprimir Página

Imprime a página atual dentro do navegador

_Nota: isto requer que navegadores Chromium estejam no modo sem cabeçalho_

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    // code sample not available please raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    driver.get("https://www.selenium.dev")
    val printer = driver as PrintsPage

    val printOptions = PrintOptions()
    printOptions.setPageRanges("1-2")
    
    val pdf: Pdf = printer.print(printOptions)
    val content = pdf.content
  {{< /tab >}}
{{< /tabpane >}}
