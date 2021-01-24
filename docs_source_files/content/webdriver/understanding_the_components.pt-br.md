---
title: "Entendendo os componentes"
weight: 1
---

Construir um conjunto de testes usando WebDriver exigirá que você entenda e
use efetivamente uma série de componentes diferentes. Como com tudo em
software, pessoas diferentes usam termos diferentes para a mesma ideia. Abaixo está
uma análise de como os termos são usados nesta descrição.

### Terminologia

* **API:** interface de programação de aplicativo. Este é o conjunto de "comandos" que
você usa para manipular o WebDriver.
* **Biblioteca:** um módulo de código que contém as APIs e o código necessário
para implementá-los. As bibliotecas são específicas para cada linguagem, por exemplo arquivos
.jar para Java, arquivos .dll para .NET, etc.
* **Driver:** responsável por controlar o navegador atual. A maioria dos drivers
são criados pelos próprios fornecedores de navegadores. Os drivers são geralmente
módulos executáveis ​​que são executados no sistema com o próprio navegador,
não no sistema que está executando o conjunto de testes. (Embora esses possam ser
mesmo sistema.) NOTA: _Algumas pessoas se referem aos drivers como proxies._
* **Framework:** uma biblioteca adicional usada como suporte para suítes do WebDriver.
* Essas estruturas podem ser estruturas de teste, como JUnit ou NUnit.
Eles também podem ser estruturas que suportam recursos de linguagem natural, como
como Cucumber ou Robotium. Frameworks também podem ser escritos e usados ​​para
coisas como manipulação ou configuração do sistema em teste,
criação de dados, oráculos de teste, etc.


### As partes e peças
No mínimo, o WebDriver se comunica com um navegador por meio de um driver. Comunicação
é bidirecional: o WebDriver passa comandos para o navegador por meio do driver e
recebe informações de volta pela mesma rota.

![Basic communication](/images/basic_comms.png?width=400px)

O driver é específico para o navegador, como ChromeDriver para Google
Chrome / Chromium, GeckoDriver para Mozilla Firefox, etc. O driver é
executado no mesmo sistema do navegador. Este pode, ou não ser, o mesmo sistema onde
os próprios testes estão sendo executados.

Este exemplo simples acima é comunicação _direta_. Comunicação para o
navegador também pode ser comunicação _remota_ através do servidor Selenium ou
RemoteWebDriver. RemoteWebDriver roda no mesmo sistema que o driver
e o navegador.

![Remote communication](/images/remote_comms.png?width=400px)

A comunicação remota também pode ocorrer usando Selenium Server ou Selenium
Grid, que, por sua vez, fala com o driver no sistema host

![Remote communication with Grid](/images/remote_comms_server.png?width=400px)

## Onde frameworks se encaixam

O WebDriver tem um trabalho e apenas um trabalho: comunicar-se com o navegador por meio de qualquer um
dos métodos acima. O WebDriver não sabe nada sobre testes: ele não
sabe como comparar coisas, afirmar passa ou falha, e certamente não sabe
uma coisa sobre relatórios ou gramática Dado / Quando / Então.

É aqui que vários frameworks entram em cena. No mínimo, você precisará de um framework de
teste que corresponde às linguagens, por exemplo, NUnit para .NET, JUnit
para Java, RSpec para Ruby, etc.

O framework de teste é responsável por rodar e executar seu WebDriver
e etapas relacionadas em seus testes. Como tal, você pode pensar nele parecendo a imagem seguinte.

![Estrutura de teste](/images/test_framework.png?Width=400px)

Frameworks/ferramentas de linguagem natural, como Cucumber, podem existir como parte desse
framework de teste na figura acima, ou eles podem envolver o framework de teste
inteiramente em sua própria implementação.
