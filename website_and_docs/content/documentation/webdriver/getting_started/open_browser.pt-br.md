---
title: "Abra e feche um navegador de internet com o Selenium"
linkTitle: "Open Browser"
weight: 6
needsTranslation: false
description: >
  Exemplos de código para iniciar e parar uma sessão em cada navegador de internet.
---
Assim que você tiver a [biblioteca Selenium instalada]({{< ref "install_library.md" >}}),
e o [driver do seu navegador de internet preferido]({{< ref "install_drivers.md" >}}), você pode iniciar e parar uma sessão com o navegador de internet.

Normalmente, os navegadores são iniciados com opções específicas que descrevem quais recursos o navegador deve suportar e como o navegador deve se comportar durante a sessão. Alguns recursos são [compartilhados por todos os navegadores de internet]({{< ref "/documentation/webdriver/capabilities/shared.md" >}}), já 
outros serão exclusivos para o navegador de internet que está sendo usado. 
Esta página mostrará exemplos de como iniciar um navegador de internet com os recursos padrões.

Depois de você aprender como iniciar uma sessão, confira a próxima sessão sobre como [programar o seu primeiro script Selenium script]({{< ref "first_script.md" >}})

## Chrome

Por padrão, o Selenium 4 é compativel com o Chrome v75 e versões superiores. Observe que a versão do
navegador de internet Chrome e a versão do chromedriver precisam corresponder à versão principal.

Além dos [recursos compartilhados]({{< ref "/documentation/webdriver/capabilities/shared.md" >}}),
existem [recursos Chrome]({{< ref "/documentation/webdriver/capabilities/chromium.md" >}}) exclusivos 
que podem ser utilizados.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L27-L30" >}}
  ChromeOptions options = new ChromeOptions();
  driver = new ChromeDriver(options);
  
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L9-L12" >}}
  options = ChromeOptions()
  driver = webdriver.Chrome(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L18-L21" >}}
  var options = new ChromeOptions();
  var driver = new ChromeDriver(options);

  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L6-L9" >}}
  options = Selenium::WebDriver::Options.chrome
  driver = Selenium::WebDriver.for :chrome, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openChromeTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = ChromeOptions()
  val driver = ChromeDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Edge

O Microsoft Edge é implementado com o Chromium, com a versão mais antiga com suporte da v79. Semelhante ao Chrome,
o número da versão principal do edgedriver deve corresponder à versão principal do navegador Edge

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L35-L38">}}
  EdgeOptions options = new EdgeOptions();
  driver = new EdgeDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L16-L19" >}}
  options = EdgeOptions()
  driver = webdriver.Edge(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L27-L30" >}}
  var options = new EdgeOptions();
  var driver = new EdgeDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L13-L16" >}}
  options = Selenium::WebDriver::Options.edge
  driver = Selenium::WebDriver.for :edge, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openEdgeTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = EdgeOptions()
  val driver = EdgeDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Firefox

O Selenium 4 requer o Firefox 78 ou superior. Recomenda-se sempre usar a versão mais recente do geckodriver.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L43-L46">}}
  FirefoxOptions options = new FirefoxOptions();
  driver = new FirefoxDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L23-L26" >}}
  options = FirefoxOptions()
  driver = webdriver.Firefox(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L36-L39" >}}
  var options = new FirefoxOptions();
  var driver = new FirefoxDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L20-L23" >}}
  options = Selenium::WebDriver::Options.firefox
  driver = Selenium::WebDriver.for :firefox, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openFirefoxTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = FirefoxOptions()
  val driver = FirefoxDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Internet Explorer

O Driver IE é o único driver mantido diretamente pelo Projeto Selenium.
Apesar dos binários para ambas as versões de 32 bits e 64 bits do Internet Explorer estarem disponiveis, existem algumas 
[limitações](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html) com o driver de 64 bits. Portanto, é recomendável usar o driver de 32 bits.

### Legado 
O projeto Selenium tem como objetivo suportar os mesmos lançamentos que a
[Microsoft considera como atual](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Versões mais antigas podem até funcionar, mas não serão suportadas. Observe que o Internet Explorer 11 encerrará o suporte para determinados
sistemas operacionais, incluindo o Windows 10 em 15 de junho de 2022.

Deve-se notar que, devido a no Internet Explorer
preferências serem salvas na conta do usuário conectado, algumas configurações adicionais são necessárias.

Informações adicionais sobre como usar o Internet Explorer podem ser encontradas
na [wiki do Selenium](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver)

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L52-L55">}}
  InternetExplorerOptions options = new InternetExplorerOptions();
  driver = new InternetExplorerDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L31-L34" >}}
  options = IEOptions()
  driver = webdriver.Ie(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L46-L49" >}}
  var options = new InternetExplorerOptions();
  var driver = new InternetExplorerDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L29-L32" >}}
  options = Selenium::WebDriver::Options.ie
  driver = Selenium::WebDriver.for :ie, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const ie = require('selenium-webdriver/ie');

  let options = new ie.Options();
  let driver = await new Builder()
    .forBrowser('internetExplorer')
    .setIeOptions(options)
    .build();

  await driver.quit();
 {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = InternetExplorerOptions()
  val driver = InternetExplorerDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### Modo de compatibilidade
O Microsoft Edge pode ser usado no modo de compatibilidade do IE usando o Driver do IE.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L61-L67">}}
  InternetExplorerOptions options = new InternetExplorerOptions();
  options.attachToEdgeChrome();
  options.withEdgeExecutablePath("/path/to/edge/browser");
  
  driver = new InternetExplorerDriver(options);
  
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L39-L44" >}}
  options = IEOptions()
  options.attach_to_edge_chrome = True
  options.edge_executable_path = "/path/to/edge/browser"
  driver = webdriver.Ie(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L56-L63" >}}
  var options = new InternetExplorerOptions
  {
    AttachToEdgeChrome = true,
    EdgeExecutablePath = "/path/to/edge/browser"
  };
  var driver = new InternetExplorerDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L38-L43" >}}
  options = Selenium::WebDriver::Options.ie
  options.attach_to_edge_chrome = true
  options.edge_executable_path = "/path/to/edge/browser"
  driver = Selenium::WebDriver.for :ie, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  let options = new ie.Options();
  options.setEdgeChromium(true);
  options.setEdgePath("/path/to/edge/browser);

  let driver = await new Builder()
    .forBrowser('internet explorer')
    .setIEOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = InternetExplorerOptions()
  options.attachToEdgeChrome()
  options.withEdgeExecutablePath("/path/to/edge/browser")
  val driver = InternetExplorerDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Opera

Porque o driver opera não define W3C como a sintaxe padrão, mas é baseado no Chromium, é recomendado
utilizar o navegador Opera com o chromedriver. Assim como todas as implementações do Chromium,
certifique-se de que a versão do navegador corresponda à versão do driver.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L73-L84">}}
  //    Set webdriver.chrome.driver with Opera Driver
  System.setProperty("webdriver.chrome.driver", "OPERA_DRIVER_PATH");
  //    Create ChromeOptions Instance
  ChromeOptions chromeOptions = new ChromeOptions();
  //    Set W3C Dialect
  chromeOptions.setExperimentalOption("w3c", true);
  //    Create ChromeDriver Instance
  WebDriver driver = new ChromeDriver(chromeOptions);
  //    Open Target Website
  driver.get("https://www.selenium.dev");
  //    Quit
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L49-L53" >}}
  options = ChromeOptions()
  options.binary_location = "path/to/opera/browser"
  driver = webdriver.Chrome(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L70-L76" >}}
  var options = new ChromeOptions
  {
    BinaryLocation = "/path/to/opera/browser"
  };
  var driver = new ChromeDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L49-L53" >}}
  options = Selenium::WebDriver::Options.chrome
  options.binary = '/path/to/opera/browser'
  driver = Selenium::WebDriver.for :chrome, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const chrome = require('selenium-webdriver/chrome');

  let options = new chrome.Options();
  options.setChromeBinaryPath("/path/to/opera/browser");

  let driver = await new Builder()
    .forBrowser('chrome')
    .setChromeOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = ChromeOptions()
  options.setBinary("/path/to/opera/browser")
  val driver = ChromeDriver(options)
  
  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Safari

### Desktop
Ao contrário dos drivers Chromium e Firefox, o safaridriver é instalado com o sistema operacional. 
Para habilitar a automação no Safari, execute o seguinte comando no terminal:

```shell
safaridriver --enable
```

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L90-L94">}}
  SafariOptions options = new SafariOptions();
  driver = new SafariDriver(options);
  
  driver.quit();
{{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L58-L60" >}}
  driver = webdriver.Safari()

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L83-L86" >}}
  var options = new SafariOptions();
  var driver = new SafariDriver(options);

  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L59-L62" >}}
  options = Selenium::WebDriver::Options.safari
  driver = Selenium::WebDriver.for :safari, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const safari = require('selenium-webdriver/safari');

  let options = new safari.Options();
  let driver = await new Builder()
    .forBrowser('safari')
    .setSafariOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = SafariOptions()
  val driver = SafariDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### Mobile
Aqueles interessados em automatizar o Safari no iOS devem procurar o [projeto Appium](//appium.io/).

## Próximo Passo
[Programando o seu primeiro script Selenium]({{< ref "first_script.md" >}})
