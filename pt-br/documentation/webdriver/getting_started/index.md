# Começando

> Se você é novo no Selenium, nós temos alguns recursos que podem te ajudar a se atualizar imediatamente.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/

---

Selenium suporta automação de todos os principais navegadores do mercado
por meio do uso do _WebDriver_.
WebDriver é uma API e protocolo que define uma interface de linguagem neutra
para controlar o comportamento dos navegadores da web.
Cada navegador é apoiado por uma implementação WebDriver específica, chamada de *driver*.
O driver é o componente responsável por delegar ao navegador,
e lida com a comunicação de e para o Selenium e o navegador.

Essa separação é parte de um esforço consciente para que os fornecedores de navegadores
assumam a responsabilidade pela implementação de seus navegadores.
Selenium faz uso desses drivers de terceiros sempre que possível,
mas também fornece seus próprios drivers mantidos pelo projeto
para os casos em que isso não é uma realidade.

A estrutura do Selenium une todas essas peças
por meio de uma interface voltada para o usuário que permite aos diferentes back-ends de navegador
serem usados de forma transparente,
permitindo a automação entre navegadores e plataformas cruzadas.

Selenium setup is quite different from the setup of other commercial tools.
Before you can start writing Selenium code, you have to 
install the language bindings libraries for your language of choice, the browser you
want to use, and the driver for that browser.

***Follow the links below to get up and going with Selenium WebDriver.***

If you wish to start with a low-code/record and playback tool, please check
[Selenium IDE](https://selenium.dev/selenium-ide)

Once you get things working, if you want to scale up your tests, check out the 
[Selenium Grid](https://www.selenium.dev/pt-br/documentation/grid/index.md).

---

Section pages:

- [Instalando bibliotecas do Selenium](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/install_library/index.md): Configurando a biblioteca Selenium para sua linguagem de programação favorita.
- [Programe o seu primeiro script Selenium](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/first_script/index.md): Instruções passo a passo para programar um script Selenium
- [Organizando e executando o código Selenium](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/using_selenium/index.md): Escalonamento da execução do Selenium com um IDE e uma biblioteca do Test Runner
