---
title: "Selenium IDE Legado"
linkTitle: "Selenium IDE"
weight: 8
aliases: ["/documentation/pt-br/legacy_docs/selenium_ide/"]
---

## Introdução

A Selenium-IDE (Integrated Development Environment) é a ferramenta que você usa para
desenvolver seus casos de teste Selenium. É um plug-in do Firefox fácil de usar e é
geralmente a maneira mais eficiente de desenvolver casos de teste. Ela também contém um
menu de contexto que permite que você primeiro selecione um elemento de UI do navegador
atualmente exibido na página e, em seguida, selecione de uma lista de comandos Selenium com
parâmetros predefinidos de acordo com o contexto do elemento de UI selecionado.
Isso não é apenas uma economia de tempo, mas também uma excelente maneira de aprender a sintaxe do script Selenium.

Este capítulo é sobre a Selenium IDE e como usá-la efetivamente.

## Instalando a IDE

Usando o Firefox, primeiro, baixe a IDE da [página de downloads](https://selenium.dev/downloads) do SeleniumHQ.

O Firefox irá protegê-lo contra a instalação de complementos de locais desconhecidos, então
você precisará clicar em "Permitir" para prosseguir com a instalação, conforme mostrado
na imagem a seguir.

![Selenium IDE Installation 1](/images/documentation/legacy/selenium_ide_installation_1.png)

Ao fazer download do Firefox, você verá a seguinte janela.

![Selenium IDE Installation 2](/images/documentation/legacy/selenium_ide_installation_2.png)

Selecione Instalar Agora. A janela de complementos do Firefox aparece, mostrando primeiro uma barra de progresso,
e quando o download for concluído, exibe o seguinte.

![Selenium IDE Installation 3](/images/documentation/legacy/selenium_ide_installation_3.png)

Reinicie o Firefox. Após a reinicialização do Firefox, você encontrará a Selenium-IDE listada no menu Ferramentas do Firefox.

![Selenium IDE Installation 4](/images/documentation/legacy/selenium_ide_installation_4.png)

## Abrindo a IDE

Para executar a Selenium-IDE, simplesmente selecione-a no menu Ferramentas do Firefox. Ela abrirá
como segue com uma janela de edição de script vazia e um menu para carregar ou
criar novos casos de teste.

![Selenium IDE Open](/images/documentation/legacy/selenium_ide_open.png)

## Funcionalidades da IDE

### Barra de Menu

O menu Arquivo tem opções para Caso de Teste e Suíte de Testes (conjunto de casos de teste).
Usando isso, você pode adicionar um novo caso de teste, abrir um caso de teste, salvar um caso de teste, e
exportar um caso de teste em uma linguagem de sua escolha. Você também pode abrir o
caso de teste mais recente. Todas essas opções também estão disponíveis para a suíte de testes.

O menu Editar permite copiar, colar, excluir, desfazer e selecionar todas as operações para
editar os comandos em seu caso de teste. O menu Opções permite a mudança de
configurações. Você pode definir o valor de tempo limite para certos comandos,
extensões de usuário para o conjunto básico de comandos Selenium e especificar o formato
(linguagem) usado ao salvar seus casos de teste. O menu Ajuda é o padrão
Menu Ajuda do Firefox; há apenas um item neste menu - Documentação do elemento de UI - pertencente ao
Selenium-IDE.

### Barra de Ferramentas

A barra de ferramentas contém botões para controlar a execução de seus casos de teste,
incluindo um recurso de etapas para depurar seus casos de teste. O botão mais à direita,
aquele com o ponto vermelho, é o botão de gravação.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_1.png)
![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_2.png)

Controle de velocidade: controla a velocidade de execução do seu caso de teste.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_3.png)

Executar todos: executa todo a suíte de testes quando uma suíte de testes com vários casos de teste é carregado.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_4.png)

Executar: executa o teste atualmente selecionado. Quando apenas um único teste é carregado
este botão e o botão Executar todos têm o mesmo efeito.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_5.png)
![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_6.png)

Pausar/Continuar: permite interromper e reiniciar um caso de teste em execução.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_7.png)

Step: permite que você "avance" por um caso de teste, executando um comando de cada vez.
Use para depurar casos de teste.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_8.png)

Modo TestRunner: permite que você execute o caso de teste em um navegador carregado com o
Selenium-Core TestRunner. O TestRunner não é comumente usado agora e é provável
seja descontinuado. Este botão é para avaliar casos de teste para
compatibilidade com versões anteriores com o TestRunner.
A maioria dos usuários provavelmente não precisará desse botão.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_9.png)
 
Aplicar regras de Rollup: Este recurso avançado permite sequências repetitivas de
comandos do Selenium a serem agrupadas em uma única ação. A documentação detalhada sobre
as regras de rollup podem ser encontradas na documentação do Elemento de UI no menu Ajuda.

![Selenium IDE Features](/images/documentation/legacy/selenium_ide_features_10.png)


### Painel de casos de teste

Seu script é exibido no painel de casos de teste. Tem duas guias, uma para
exibir o comando e seus parâmetros em um formato de “tabela” legível.

![Selenium IDE Image Pane](/images/documentation/legacy/selenium_ide_image_pane.png)

A outra guia - Código Fonte exibe o caso de teste no formato nativo no qual o
arquivo será armazenado. Por padrão, é HTML, embora possa ser alterado para uma
linguagem de programação como Java ou C#, ou uma linguagem de script como Python.
Consulte o menu Opções para obter detalhes. A visualização do Código Fonte também permite editar o
caso de teste em sua forma bruta, incluindo operações de copiar, recortar e colar.

Os campos de entrada de Comando, Destino e Valor exibem o comando atualmente selecionado
junto com seus parâmetros. Estes são campos de entrada onde você pode modificar
o comando atualmente selecionado. O primeiro parâmetro especificado para um comando
na guia Referência do painel inferior sempre vai para o campo Destino. Se um
segundo parâmetro é especificado pela guia Referência, ele sempre vai no
campo Valor.

![Selenium IDE Entry Fields](/images/documentation/legacy/selenium_ide_entry_fields.png)

Se você começar a digitar no campo Comando,
uma lista suspensa será preenchida
com base nos primeiros caracteres que você digitar;
você pode então selecionar o comando que deseja
no menu suspenso.

### Painel de Log / Referência / Elemento de UI / Rollup

O painel inferior é usado para quatro funções diferentes - Log, Referência,
Elemento de UI e Rollup - dependendo da guia selecionada.

#### Log

Quando você executa seu caso de teste, mensagens de erro e mensagens de informação mostrando
o progresso são exibidas neste painel automaticamente, mesmo se você não
selecionar a guia Log primeiro. Essas mensagens geralmente são úteis para depuração de casos de teste.
Observe o botão Limpar para limpar o registro. Observe também que o botão Informações é um
drop-down permitindo a seleção de diferentes níveis de informação para registrar.

![Selenium IDE Bottom Box](/images/documentation/legacy/selenium_ide_bottom_box.png)


#### Referência

A guia Referência é a seleção padrão sempre que você entrar ou
modificar comandos e parâmetros Selenium no modo Tabela. No modo Tabela, o
painel de Referência exibirá a documentação do comando atual. Ao inserir
ou modificar comandos, seja do modo Tabela ou Código Fonte, é criticamente
importante garantir que os parâmetros especificados nos campos Destino e Valor
correspondem aos especificados na lista de parâmetros do painel Referência. O
número de parâmetros fornecidos deve corresponder ao número especificado, a ordem
dos parâmetros fornecidos deve corresponder à ordem especificada e os tipos de parâmetros
fornecidos devem corresponder aos tipos especificados.
Se houver uma incompatibilidade em qualquer uma dessas
três áreas, o comando não funcionará corretamente.

![Selenium IDE Bottom Box](/images/documentation/legacy/selenium_ide_bottom_box_ref.png)

Embora a guia Referência seja ótima como uma referência rápida, ainda é
necessário consultar a documentação de referência do Selenium.

#### Elemento de UI e Rollup

Informações detalhadas sobre esses dois painéis (que abrangem recursos avançados) podem ser
encontradas na documentação do Elemento de UI no menu Ajuda do Selenium-IDE.

## Construindo casos de teste

Existem três métodos principais para desenvolver casos de teste. Frequentemente,
um desenvolvedor de testes necessita de todas as três técnicas.

### Gravando

Muitos usuários de primeira viagem começam gravando um caso de teste de suas interações
com um site. Quando a Selenium-IDE é aberta pela primeira vez, o botão de gravação é ativado por
padrão. Se você não quiser que a Selenium-IDE comece a gravar automaticamente, você
pode desligar isso indo em Opções > Opções… e desmarcando “Iniciar
gravação imediatamente ao abrir.”

Durante a gravação, a Selenium-IDE irá inserir comandos automaticamente em seu
caso de teste com base em suas ações. Normalmente, isso incluirá:

* clicar em um link - comandos click ou clickAndWait
* inserir valores - comando type
* selecionar opções de uma caixa de listagem suspensa - comando select
* clicar em caixas de seleção ou botões de rádio - comando click

Aqui estão algumas “pegadinhas” para ficar atento:

* O comando type pode exigir o clique em alguma outra área da página da web para começar a gravar.
* Seguir um link geralmente registra um comando de clique. Frequentemente, você precisará mudar
isso para clickAndWait para garantir que seu caso de teste pause até que a nova página seja
completamente carregada. Caso contrário, seu caso de teste continuará executando comandos
antes que a página carregue todos os seus elementos de UI. Isso causará uma falha de teste inesperada.

### Adicionando verificações e asserções com o Menu de Contexto

Seus casos de teste também precisarão verificar as propriedades de uma página da web. Isto
requer comandos de asserção e verificação. Não descreveremos os detalhes desses
comandos aqui; que estão no capítulo sobre Comandos do Selenium - “Selenese”. Aqui
vamos simplesmente descrever como adicioná-los ao seu caso de teste.

Com a gravação da Selenium-IDE, vá para o navegador exibindo sua aplicação de teste
e clique com o botão direito em qualquer lugar da página. Você verá um menu de contexto mostrando
comandos verificar e/ou declarar.

Na primeira vez que você usa o Selenium, pode haver apenas um comando Selenium listado.
Ao usar a IDE, no entanto, você encontrará comandos adicionais que serão rapidamente
adicionados a este menu. A Selenium-IDE tentará prever qual comando, junto
com os parâmetros, você precisará para um elemento de interface selecionado na atual
página da web.

Vamos ver como isso funciona. Abra uma página da web de sua escolha e selecione um bloco
de texto na página. Um parágrafo ou título funcionará bem. Agora, clique com o botão direito
no texto selecionado. O menu de contexto deve fornecer um comando verifyTextPresent
e o parâmetro sugerido deve ser o próprio texto.

Além disso, observe a opção Mostrar Todos os Comandos Disponíveis. Isso mostra muitos, muitos
mais comandos, novamente, junto com parâmetros sugeridos, para testar seu
elemento de UI atualmente selecionado.

Experimente mais alguns elementos de UI. Tente clicar com o botão direito em uma imagem ou em um controle de usuário, como
um botão ou uma caixa de seleção. Você pode precisar usar Mostrar Todos os Comandos Disponíveis para ver
opções diferentes de verifyTextPresent. Depois de selecionar essas outras opções,
os mais usados aparecerão no menu de contexto principal. Por exemplo,
selecionar verifyElementPresent para uma imagem deve posteriormente fazer com que esse comando
esteja disponível no menu de contexto principal na próxima vez que você selecionar uma imagem e
clicar com o botão direito.

Novamente, esses comandos serão explicados em detalhes no capítulo sobre comandos Selenium. Por enquanto, fique à vontade para usar a IDE para gravar e selecionar
comandos em um caso de teste e, em seguida, execute-o. Você pode aprender muito sobre os
comandos do Selenium simplesmente experimentando com a IDE.

### Editando

#### Inserir comando

##### Visualização Tabela

Selecione o ponto em seu caso de teste onde deseja inserir o comando. No painel de caso de teste,
clique com o botão esquerdo na linha onde deseja inserir um
novo comando. Clique com o botão direito e selecione Inserir Comando;
a IDE irá adicionar um espaço em branco imediatamente à frente da linha que você selecionou.
Agora use os campos de edição de texto para inserir seu novo comando e seus parâmetros.

##### Visualização Código Fonte

Selecione o ponto em seu caso de teste onde deseja inserir o comando. No painel do caso de teste,
clique com o botão esquerdo entre os comandos onde você deseja
insira um novo comando e insira as tags HTML necessárias para criar uma linha de 3 colunas
contendo o Comando, primeiro parâmetro (se for exigido pelo Comando),
e segundo parâmetro (novamente, se for necessário para localizar um elemento) e terceiro
parâmetro (novamente, se for necessário ter um valor). Exemplo:

```html
<tr>
    <td>Command</td>
    <td>target (locator)</td>
    <td>Value</td>
</tr>
```

#### Inserir comentário

Comentários podem ser adicionados para tornar seu caso de teste mais legível. Esses comentários são
ignorados quando o caso de teste é executado.

Os comentários também podem ser usados para adicionar espaço em branco vertical (uma ou mais linhas em branco)
em seus testes; apenas crie comentários vazios. Um comando vazio causará um erro
durante a execução; um comentário vazio, não.

##### Visualização Tabela

Selecione a linha em seu caso de teste onde deseja inserir o comentário.
Clique com o botão direito e selecione Inserir Comentário. Agora use o campo Comando para inserir o
comentário. Seu comentário aparecerá em roxo.

##### Visualização Código Fonte

Selecione o ponto em seu caso de teste onde deseja inserir o comentário. Adicione um
comentário no estilo HTML, ou seja, `<!-- seu comentário aqui -->`

#### Editar um comando ou comentário

##### Visualização Tabela

Basta selecionar a linha a ser alterada e editá-la usando os campos de comando, destino,
e valor.

##### Visualização Código Fonte

Uma vez que a visualização do Código Fonte fornece o equivalente a um editor WYSIWYG (What You See Is What You Get), simplesmente modifique a linha que você deseja - comando, parâmetro ou comentário.

### Abrindo e salvando um caso de teste

Como a maioria dos programas, existem comandos Salvar e Abrir no menu Arquivo.
No entanto, o Selenium distingue entre casos de teste e suítes de teste. Para salvar
seus testes Selenium-IDE para uso posterior, você pode salvar os casos de teste individualmente
ou salvar a suíte de testes. Se os casos de teste de sua suíte de testes não
foram salvos, você será solicitado a salvá-los antes de salvar a suíte.

Quando você abre um caso de teste ou suíte existente, a Selenium-IDE exibe seu
comandos do Selenium no painel de caso de teste.

## Executando casos de teste

A IDE fornece muitas opções para executar seu caso de teste. Você pode executar um caso de teste
inteiro de uma vez, parar e iniciar, executar uma linha de cada vez, executar um único comando
que você está desenvolvendo atualmente e pode fazer uma execução em lote de uma suíte de testes.
A execução de casos de teste é muito flexível na IDE.

**Executar um caso de teste**

Clique no botão Executar para executar o caso de teste mostrado.

**Executar uma suíte de testes**

Clique no botão Executar Todos para executar todos os testes dentro da suíte de testes

**Parar e Continuar**

O botão de Pausa pode ser utilizado para parar o caso de teste no meio da sua execução.
O ícone do botão então muda para indicar que você pode Continuar. Para continuar, clique nele.

**Parar no meio**

Você pode definir um ponto de interrupção (breakpoint) no caso de teste para que ele pare em um comando específico.
Isto é útil para depurar seu teste. Para definir um ponto de interrupção, selecione um comando, clique com
o botão direito e a partir do Menu de Contexto selecione Alternar ponto de interrupção.

**Começar do meio**

Você pode preferir que a IDE comece a executar a partir de um comando específico
no meio do caso de teste. Isto também pode ser usado para depuração.
Para definir um ponto de começo, selecione o comando, clique com o botão direito
e a partir do Menu de Contexto selecione Set/Clear Start Point.

**Execute um comando isolado**

De um duplo-clique em qualquer comando para executá-lo. Isto é útil
quando você está escrevendo um único comando. Permite testar imediatamente
o comando sendo construído, quando não tem certeza se ele está certo.
Você pode dar um duplo-clique para ver se o comando é executado corretamente.
Isto também está disponível no Menu de Contexto.

## Usando uma URL base para executar casos de teste em diferentes domínios

O campo URL base na parte superior da janela da Selenium-IDE é muito útil para
permitir que os casos de teste sejam executados em diferentes domínios.
Suponha que um site chamado http://news.portal.com tenha um site beta
interno chamado http://beta.news.portal.com. Quaisquer casos de teste
para esses sites que começam com um comando *open* devem especificar uma
URL relativa como o argumento para abrir, em vez de uma URL absoluta
(começando com um protocolo como http: ou https:). A Selenium-IDE irá
então criar uma URL absoluta anexando o argumento do comando *open* no
final do valor da URL base. Por exemplo, o caso de teste
abaixo seria executado em http://news.portal.com/about.html:

![Selenium IDE Prod URL](/images/documentation/legacy/selenium_ide_base_url_prod.png)

Este mesmo caso de teste com uma configuração de URL base modificada seria executado em
http://beta.news.portal.com/about.html:

![Selenium IDE Beta URL](/images/documentation/legacy/selenium_ide_base_url_beta.png)

## Comandos Selenium – “Selenese”

Os comandos do Selenium, muitas vezes chamados de Selenese,
são o conjunto de comandos que executam o seu testes.
Uma sequência desses comandos é um script de teste.
Aqui nós explicamos esses comandos em detalhes,
e apresentamos as diversas opções que você tem ao testar a sua
aplicação web usando o Selenium.

Selenium fornece um conjunto rico de comandos para testar totalmente sua
aplicação web quase de qualquer maneira que você possa imaginar.
O conjunto de comandos é frequentemente chamado de Selenese.
Esses comandos criam essencialmente uma linguagem de teste.

Em Selenese, pode-se testar a existência de elementos de UI com base em suas tags HTML,
testar a existência de um conteúdo específico, testar a existência de links quebrados, campos de entrada,
opções de lista de seleção, envio de formulários e dados de tabela, entre outras coisas. Além do mais
os comandos do Selenium suportam testes de tamanho de janela, posição do mouse, alertas,
funcionalidade Ajax, janelas pop-up, tratamento de eventos
e muitas outras características de aplicativos da web.
A Referência de Comandos lista todos os comandos disponíveis.

Um comando diz ao Selenium o que fazer. Os comandos do Selenium vêm em três "sabores":
**Ações**, **Acessores** e **Asserções**.

* **Ações** são comandos que geralmente manipulam o estado do aplicativo.
Elas fazem coisas como “clicar neste link” e “selecionar essa opção”. Se uma ação
falhar ou tiver um erro, a execução do teste atual é interrompida.

    Muitas ações podem ser chamadas com o sufixo "AndWait", por ex. “ClickAndWait”. Este
    sufixo diz ao Selenium que a ação fará com que o navegador faça uma chamada para
    o servidor, e que o Selenium deve aguardar o carregamento de uma nova página.

* **Acessores** examinam o estado do aplicativo e armazenam os resultados em
variáveis, por exemplo “StoreTitle”. Eles também são usados para gerar Asserções automaticamente.

* **Asserções** são como Acessores, mas verificam se o estado da
aplicação está em conformidade com o que é esperado. Os exemplos incluem
“certifique-se de que o título da página é X”
e “verifique se esta caixa de seleção está marcada”.

Todas as asserções do Selenium podem ser usadas em 3 modos: "assert", "verify"
e "wait for". Por exemplo, você pode usar “assertText”, “verifyText” e “waitForText”.
Quando uma asserção falha, o teste é abortado. Quando uma verificação falha, o teste
continuará a execução, registrando a falha. Isso permite uma única asserção para
certificar-se de que o aplicativo está na página correta, seguido por um monte de
verificações para testar os valores dos campos do formulário, rótulos, etc.

Os comandos "waitFor" aguardam até que alguma condição se torne verdadeira (o que pode ser útil
para testar aplicativos Ajax). Eles terão sucesso imediatamente se a condição
já é verdadeira. No entanto, eles falharão e interromperão o teste se a condição
não se tornar verdadeira dentro da configuração de timeout atual (veja o setTimeout
ação abaixo).

## Sintaxe do Script

Os comandos do Selenium são simples, consistem no comando e em dois parâmetros.
Por exemplo:

|                    |                               |             |
| --------           | ----------------------------  | ----------- |
| verifyText         | //div//a[2]                   | Login       |

Os parâmetros nem sempre são necessários, depende do comando. Em alguns
casos ambos são necessários, em outros um parâmetro é necessário, e ainda em
outros, o comando pode não ter nenhum parâmetro. Aqui estão mais alguns
exemplos:

|                    |                               |                               |
| --------           | ----------------------------  | ----------------------------- |
| goBackAndWait      |                               |                               |
| verifyTextPresent  |                               | Welcome to My Home Page       |
| type               | id=phone                      | (555) 666-7066                |
| type               | id=address1                   | ${myVariableAddress}          |

A referência de comandos descreve os requisitos de parâmetro para cada comando.

Os parâmetros variam, mas normalmente são:

* um localizador para identificar um elemento de UI em uma página.
* um padrão de texto para verificar ou fazer uma asserção do conteúdo esperado da página
* um padrão de texto ou uma variável Selenium para inserir texto em um campo de entrada ou
para selecionar uma opção de uma lista de opções.

Localizadores, padrões de texto, variáveis Selenium e os próprios comandos são
descritos em bastante detalhe na seção sobre Comandos do Selenium.

Os scripts do Selenium que serão executados a partir da Selenium-IDE serão
armazenados em um arquivo de texto HTML. Isso consiste em uma tabela HTML com três colunas.
A primeira coluna identifica o comando Selenium, a segunda é um alvo e a última
coluna contém um valor. A segunda e terceira colunas podem não exigir valores
dependendo do comando Selenium escolhido, mas elas devem estar presentes.
Cada linha da tabela representa um novo comando Selenium. Aqui está um exemplo de um teste que
abre uma página, faz um asserção no título da página e, em seguida, verifica algum conteúdo na página:

```html
<table>
    <tr><td>open</td><td>/download/</td><td></td></tr>
    <tr><td>assertTitle</td><td></td><td>Downloads</td></tr>
    <tr><td>verifyText</td><td>//h2</td><td>Downloads</td></tr>
</table>
```

Renderizado como uma tabela em um navegador, seria assim:

|                    |                               |                               |
| --------           | ----------------------------  | ----------------------------- |
| open               | /download/                    |                               |
| assertTitle        |                               | Downloads                     |
| verifyText         | //h2                          | Downloads                     |

A sintaxe HTML Selenese pode ser usada para escrever e executar testes sem exigir
conhecimento de uma linguagem de programação. Com um conhecimento básico de Selenese e
Selenium-IDE você pode produzir e executar casos de teste rapidamente.

## Suítes de Teste

Uma suíte de testes é uma coleção de testes. Frequentemente, você executará todos os testes em uma
suite de teste como um trabalho em lote contínuo.

Ao usar a Selenium-IDE, as suítes de testes também podem ser definidas usando um arquivo HTML simples.
A sintaxe novamente é simples. Uma tabela HTML define uma lista de testes onde
cada linha define o caminho do sistema de arquivos para cada teste. Um exemplo diz tudo.

```html
<html>
<head>
<title>Test Suite Function Tests - Priority 1</title>
</head>
<body>
<table>
  <tr><td><b>Suite Of Tests</b></td></tr>
  <tr><td><a href="./Login.html">Login</a></td></tr>
  <tr><td><a href="./SearchValues.html">Test Searching for Values</a></td></tr>
  <tr><td><a href="./SaveValues.html">Test Save</a></td></tr>
</table>
</body>
</html>
```

Um arquivo semelhante a este permitiria executar todos os testes de uma vez, um após o
outro, a partir da Selenium-IDE.

As suítes de testes também podem ser mantidas ao usar o Selenium-RC. Isso é feito via
programação de várias maneiras. Normalmente Junit é usado para
manter um conjunto de testes se estiver usando Selenium-RC com Java. Além disso, se
C# é a linguagem escolhida, o Nunit pode ser utilizado. Se estiver usando uma linguagem interpretada
como Python com Selenium-RC, então alguma programação simples seria
envolvida na configuração de uma suíte. Uma vez que o motivo de usar
Selenium-RC é se aproveitar da lógica de programação para o seu teste, geralmente
não é um problema.

## Comandos Selenium usados com frequencia

Para concluir nossa introdução ao Selenium, mostraremos alguns
comandos típicos. Estes são provavelmente os comandos mais comumente usados para construir
testes.

**open**

abre uma página usando a URL.

**click/clickAndWait**

realiza um clique e opcionalmente aguarda o carregamento de uma nova página.

**verifyTitle/assertTitle**

verifica se o título da página é o esperado.

**verifyTextPresent**

verifica se o texto esperado está em algum lugar da página.

**verifyElementPresent**

verifica se o elemento de UI esperado, definido pela tag HTML, está em algum lugar da página.

**verifyText**

verficia se o texto esperado e a tag HTML correspondente estão presentes na página.

**verifyTable**

verifica se o conteúdo da tabela é o esperado.

**waitForPageToLoad**

pausa a execução até que a nova página carregue. Chamado automaticamente quando
clickAndWait é utilizado.

**waitForElementPresent**

pausa a execução até que um elemento HTML, definido por sua tag HTML, esteja
presenta na página.

## Verificando elementos da página

Verificar os elementos de UI em uma página da web é provavelmente o recurso mais comum dos seus
testes automatizados. Selenese permite várias maneiras de verificar os elementos de UI.
É importante que você entenda esses métodos diferentes porque eles
definem o que você está realmente testando.

Por exemplo, você vai testar se...

1. um elemento está presente em algum lugar da página?
1. um texto específico está em algum lugar da página?
1. um texto específico está em um local específico na página?

Por exemplo, se você estiver testando um título de texto, o texto
e sua posição na parte superior da página provavelmente são relevantes para o seu teste.
Se, no entanto, você está testando a existência de uma imagem na página inicial,
e os web designers frequentemente alteram o arquivo de imagem específico
junto com sua posição na página,
então você só quer testar se uma imagem (em oposição à um arquivo de imagem específico)
existe em algum lugar.

## Asserção ou Verificação?

Escolher entre "assert" e "verify" se resume à conveniência e gerenciamento
de falhas. Não vale a pena verificar se o primeiro parágrafo da
página é correto se o seu teste já falhou ao verificar se o
navegador está exibindo a página esperada. Se você não estiver na página correta,
você provavelmente vai querer abortar seu caso de teste para poder investigar a
causa e corrigir o(s) problema(s) imediatamente. Por outro lado, você pode querer verificar
muitos atributos de uma página sem abortar o caso de teste na primeira falha
pois isso permitirá que você analise todas as falhas na página e tome a
ação apropriada. Efetivamente, um "assert" irá falhar no teste e abortar o
caso de teste atual, enquanto um "verify" irá falhar no teste e continuar a executar
o caso de teste.

O melhor uso desse recurso é agrupar logicamente seus comandos de teste e
iniciar cada grupo com um "assert" seguido por um ou mais comandos de "verify".
Segue um exemplo:

| Command            | Target                        | Value                         |
| --------           | ----------------------------  | ----------------------------- |
| open               | /download/                    |                               |
| assertTitle        |                               | Downloads                     |
| verifyText         | //h2                          | Downloads                     |
| assertTable        | 1.2.1                         | Selenium IDE                  |
| verifyTable        | 1.2.2                         | June 3, 2008                  |
| verifyTable        | 1.2.3                         | 1.0 beta 2                    |

O exemplo acima primeiro abre uma página e, em seguida, faz uma asserção para saber se a página correta
é carregada comparando o título com o valor esperado. Só se passar,
o seguinte comando será executado e verificará se o texto está presente na
localização esperada. O caso de teste, então, faz uma asserção para saber se a primeira coluna na segunda
linha da primeira tabela contém o valor esperado, e somente se este for aprovado
as células restantes nessa linha serão “verificadas”.

### **verifyTextPresent**

O comando `verifyTextPresent` é usado para verificar se existe um texto específico em algum lugar
na página. Leva um único argumento - o texto a ser verificado. Por
exemplo:

| Command            | Target                        | Value                         |
| --------           | ----------------------------  | ----------------------------- |
| verifyTextPresent  | Marketing Analysis            |                               |

Isso faria com que o Selenium procurasse e verificasse que a string de texto
“Marketing Analysis” aparece em algum lugar na página que está sendo testada. Use
verifyTextPresent quando você está interessado apenas no próprio texto estar presente
na página. Não use isso quando você também precisa testar onde o texto está
na página.

### **verifyElementPresent**

Use este comando quando precisar testar a presença de um elemento de UI específico,
em vez de seu conteúdo. Esta verificação não verifica o texto, apenas a
tag HTML. Um uso comum é verificar a presença de uma imagem.

| Command               | Target                        | Value                         |
| --------              | ----------------------------  | ----------------------------- |
| verifyElementPresent  | //div/p/img                   |                               |

Este comando verifica se uma imagem, especificada pela existência de uma tag HTML `<img>`,
está presente na página e aparece após uma tag `<div>` e uma tag `<p>`.
O primeiro (e único) parâmetro é um localizador para informar o
comando Selenese de como encontrar o elemento.
Os localizadores são explicados na próxima seção.

`verifyElementPresent` pode ser usado para verificar a existência de qualquer tag HTML dentro
da página. Você pode verificar a existência de links, parágrafos, divisões `<div>`,
etc. Aqui estão mais alguns exemplos.

| Command               | Target                        | Value                         |
| --------              | ----------------------------  | ----------------------------- |
| verifyElementPresent  | //div/p                       |                               |
| verifyElementPresent  | //div/a                       |                               |
| verifyElementPresent  | id=Login                      |                               |
| verifyElementPresent  | link=Go to Marketing Research |                               |
| verifyElementPresent  | //a[2]                        |                               |
| verifyElementPresent  | //head/title                  |                               |

Esses exemplos ilustram a variedade de maneiras pelas quais um elemento de UI pode ser testado. Novamente,
os localizadores são explicados na próxima seção.

### **verifyText**

Use `verifyText` quando o texto e seu elemento de UI devem ser testados. verifyText
deve usar um localizador. Se você escolher um localizador _XPath_ ou _DOM_, você pode verificar se um
texto específico aparece em um local específico na página em relação a outro componente na página.

| Command            | Target                        | Value                                                               |
| --------           | ----------------------------  | ------------------------------------------------------------------- |
| verifyText         | //table/tr/td/div/p           | This is my text and it occurs right after the div inside the table. |


## Localizando elementos

Para muitos comandos do Selenium, um alvo é necessário. Este alvo identifica um
elemento no conteúdo do aplicativo da web, e consiste na estratégia de localização seguida pela localização no formato `locatorType = location`.
O tipo de localizador pode ser omitido em muitos casos. Os vários tipos de localizadores são
explicados abaixo com exemplos para cada um.

### Localizando pelo Identificador

Este é provavelmente o método mais comum de localização de elementos e é o
padrão quando nenhum tipo de localizador reconhecido é usado. Com esta estratégia,
o primeiro elemento com o valor do atributo id correspondente ao local será usado. E se
nenhum elemento tem um atributo *id* correspondente, então o primeiro elemento com um
atributo *name* correspondente ao local será usado.

Por exemplo, o código fonte da sua página pode ter atributos id e name
do seguinte modo:

```html
  <html>
   <body>
    <form id="loginForm">
     <input name="username" type="text" />
     <input name="password" type="password" />
     <input name="continue" type="submit" value="Login" />
    </form>
   </body>
  <html>
```           

As seguintes estratégias de localização retornariam os elementos do HTML acima indicado pelo número da linha:

- ``identifier=loginForm`` (3)
- ``identifier=password`` (5)
- ``identifier=continue`` (6)
- ``continue`` (6)

Como o tipo de localizador ``identifier`` é o padrão, o ``identifier =``
nos primeiros três exemplos acima não é necessário.

### Localizando pelo id

Este tipo de localizador é mais limitado do que o tipo Localizador por Identificador, mas
também mais explícito. Use isto quando você souber o atributo *id* de um elemento.

```html
   <html>
    <body>
     <form id="loginForm">
      <input name="username" type="text" />
      <input name="password" type="password" />
      <input name="continue" type="submit" value="Login" />
      <input name="continue" type="button" value="Clear" />
     </form>
    </body>
   <html>
```

- ``id=loginForm`` (3)

### Localizando pelo *name*
 
O tipo Localizador de Nome irá localizar o primeiro elemento com um atributo *name* correspondente.
Se vários elementos tiverem o mesmo valor para um atributo *name*, então
você pode usar filtros para refinar ainda mais sua estratégia de localização.
O tipo de filtro padrão é *value* (correspondendo ao atributo *value*). 

```html
   <html>
    <body>
     <form id="loginForm">
      <input name="username" type="text" />
      <input name="password" type="password" />
      <input name="continue" type="submit" value="Login" />
      <input name="continue" type="button" value="Clear" />
     </form>
   </body>
   <html>
```  

- ``name=username`` (4)
- ``name=continue value=Clear`` (7)
- ``name=continue Clear`` (7)
- ``name=continue type=button`` (7)

Nota: Ao contrário de alguns tipos de localizadores XPath e DOM, os três
    tipos de localizadores acima permitem que o Selenium teste um elemento de UI independente
    de sua localização em
    a página. Portanto, se a estrutura e a organização da página forem alteradas, o teste
    ainda vai passar. Você pode ou não querer também testar se a página
    tem mudanças de estrutura. No caso em que os web designers frequentemente alteram a
    página, mas sua funcionalidade deve ser testada por regressão, testando via *id* e
    atributos de nome, ou realmente através de qualquer propriedade HTML, torna-se muito importante.

### Localizando pelo XPath

XPath é a linguagem usada para localizar nós em um documento XML. Como o HTML pode
ser uma implementação de XML (XHTML), os usuários do Selenium podem aproveitar esta poderosa
linguagem para encontrar elementos em seus aplicativos da web. XPath vai além (bem como apoia)
os métodos simples de localização por atributos *id* ou *name*
e abre todos os tipos de novas possibilidades, como localizar a
terceira caixa de seleção na página.

Uma das principais razões para usar XPath é quando você não tem um *id* adequado
ou atributo de nome para o elemento que você deseja localizar. Você pode usar XPath para
localizar o elemento em termos absolutos (não recomendado) ou em relação a um
elemento que possui um atributo *id* ou name. Localizadores XPath também podem ser
usados para especificar elementos por meio de atributos diferentes de *id* e *name*.

Os XPaths absolutos contêm a localização de todos os elementos da raiz (html) e
como resultado, é provável que falhe com apenas o menor ajuste na
aplicação. Ao encontrar um elemento próximo com um atributo *id* ou *name* (de preferência
um elemento pai), você pode localizar seu elemento de destino com base no relacionamento.
É muito menos provável que isso mude e pode tornar seus testes mais robustos.

Uma vez que apenas os localizadores ``xpath`` começam com "//", não é necessário incluir
o rótulo ``xpath=`` ao especificar um localizador XPath.

```html
   <html>
    <body>
     <form id="loginForm">
      <input name="username" type="text" />
      <input name="password" type="password" />
      <input name="continue" type="submit" value="Login" />
      <input name="continue" type="button" value="Clear" />
     </form>
   </body>
   <html>
```

- ``xpath=/html/body/form[1]`` (3) - *Caminho absoluto (seria quebrado se o HTML sofresse
   qualquer pequena mudança)*
- ``//form[1]`` (3) - *Primeiro elemento `<form>` no HTML*
- ``xpath=//form[@id='loginForm']`` (3) - *O elemento `<form>` com o atributo 'id' e o valor 'loginForm'*
- ``xpath=//form[input/@name='username']`` (3) - *Primeiro elemento `<form>` com um elemento filho `<input>`
   com o atributo 'name' e o valor 'username'*
- ``//input[@name='username']`` (4) - *Primeiro elemento `<input>` com o atributo 'name' e o valor
   'username'*
- ``//form[@id='loginForm']/input[1]`` (4) - *Primeiro elemento filho `<input>` do
   elemento `<form>` com o atributo 'id' e o valor 'loginForm'*
- ``//input[@name='continue'][@type='button']`` (7) - *`<input>` com o atributo 'name' e o valor 'continue'
   e o atributo 'type' e o valor 'button'*
- ``//form[@id='loginForm']/input[4]`` (7) - *Quarto elemento filho `<input>` do
   elemento `<form>` com atributo 'id' e valor 'loginForm'*

Esses exemplos cobrem alguns princípios básicos, mas para aprender mais,
as seguintes referências são recomendadas:

* [W3Schools XPath Tutorial](http://www.w3schools.com/xml/xpath_intro.asp>) 
* [W3C XPath Recommendation](http://www.w3.org/TR/xpath)

Existem também alguns complementos do Firefox muito úteis que podem ajudar a
descobrir o XPath de um elemento:

* [XPath Checker](https://addons.mozilla.org/en-US/firefox/addon/1095?id=1095) - Pode
  ser usado para testar os resultados do XPath.
* [Firebug](https://addons.mozilla.org/en-US/firefox/addon/1843) - Sugestões de XPath
  é apenas um dos muitos recursos poderosos deste complemento muito útil.

### Localizando hyperlinks pelo texto do link
 
Este é um método simples de localizar um hiperlink em sua página da web usando o
texto do link. Se dois links com o mesmo texto estiverem presentes, então a primeira
correspondência será usada.

```html
  <html>
   <body>
    <p>Are you sure you want to do this?</p>
    <a href="continue.html">Continue</a> 
    <a href="cancel.html">Cancel</a>
  </body>
  <html>
```

- ``link=Continue`` (4)
- ``link=Cancel`` (5)

### Localizando pelo DOM

O Document Object Model representa um documento HTML e pode ser acessado
usando JavaScript. Esta estratégia de localização usa um JavaScript que representa
um elemento na página, que pode ser simplesmente a localização do elemento usando a
notação hierárquica.

Uma vez que apenas os localizadores ``dom`` começam com "document", não é necessário incluir
o rótulo ``dom=`` ao especificar um localizador DOM.

```html
   <html>
    <body>
     <form id="loginForm">
      <input name="username" type="text" />
      <input name="password" type="password" />
      <input name="continue" type="submit" value="Login" />
      <input name="continue" type="button" value="Clear" />
     </form>
   </body>
   <html>
```

- ``dom=document.getElementById('loginForm')`` (3)
- ``dom=document.forms['loginForm']`` (3)
- ``dom=document.forms[0]`` (3)
- ``document.forms[0].username`` (4)
- ``document.forms[0].elements['username']`` (4)
- ``document.forms[0].elements[0]`` (4)
- ``document.forms[0].elements[3]`` (7)

Você pode usar o próprio Selenium, bem como outros sites e extensões para explorar
o DOM do seu aplicativo da web. Uma boa referência é a [W3Schools](http://www.w3schools.com/js/js_htmldom.asp). 

### Localizando pelo CSS

CSS (Cascading Style Sheets) é uma linguagem para descrever a renderização de HTML
e documentos XML. CSS usa seletores para vincular propriedades de estilo a elementos
no documento. Esses seletores podem ser usados pelo Selenium como outra estratégia de localização.

```html
   <html>
    <body>
     <form id="loginForm">
      <input class="required" name="username" type="text" />
      <input class="required passfield" name="password" type="password" />
      <input name="continue" type="submit" value="Login" />
      <input name="continue" type="button" value="Clear" />
     </form>
   </body>
   <html>
```

- ``css=form#loginForm`` (3)
- ``css=input[name="username"]`` (4)
- ``css=input.required[type="text"]`` (4)
- ``css=input.passfield`` (5)
- ``css=#loginForm input[type="button"]`` (7)
- ``css=#loginForm input:nth-child(2)`` (5)

Para obter mais informações sobre seletores CSS, o melhor lugar para ir é [a
publicação do W3C](http://www.w3.org/TR/css3-selectors/). Você encontrará todas as
referências lá.

### Localizadores implícitos

Você pode optar por omitir o tipo de localizador nas seguintes situações:

  - Localizadores sem uma estratégia de localização explicitamente definida utilizará a estratégia de localização padrão. Veja _`Localizando pelo Identificador`_.
   
  - Localizadores começando com "//" usarão a estratégia de localização XPath.
    Veja _`Localizando pelo XPath`_.
 
  - Os localizadores que começam com "document" usarão a estratégia do localização DOM.
    Veja _`Localizando pelo DOM`_


## Padrões de texto

Como os localizadores, *padrões* são um tipo de parâmetro frequentemente exigido pelos comandos Selenese. Exemplos de comandos que exigem padrões são **verifyTextPresent**,
**verifyTitle**, **verifyAlert**, **assertConfirmation**, **verifyText**, e
**verifyPrompt**. E como foi mencionado acima, os localizadores de link podem utilizar
um padrão. Os padrões permitem que você *descreva*, por meio do uso de caracteres especiais,
qual texto é esperado em vez de precisar especificar esse texto exatamente.

Existem três tipos de padrões: *globbing*, *expressões regulares* e *exato*.

### Padrão de Globbing

A maioria das pessoas está familiarizada com o uso de globbing em
expansão de nome de arquivo em uma linha de comando DOS ou Unix / Linux como ``ls * .c``.
Neste caso, globbing é usado para exibir todos os arquivos no diretório atual
que terminam com uma extensão ``.c``. Globbing é bastante limitado.
Apenas dois caracteres especiais são suportados na implementação do Selenium:

`*` que é traduzido como "corresponder a qualquer coisa", ou seja, nada, um único caractere ou muitos caracteres.

`[ ]` (*classe de caracteres*) que é traduzido como "corresponder a qualquer caractere dentro dos colchetes."
Um travessão (hífen) pode ser usado como uma abreviação para especificar um intervalo de caracteres
(que são contíguos no conjunto ASCII).
Alguns exemplos tornarão clara a funcionalidade de uma classe de caracteres:

``[aeiou]`` corresponde a qualquer vogal minúscula

``[0-9]`` corresponde a qualquer dígito

``[a-zA-Z0-9]`` corresponde a qualquer caractere alfanumérico

Na maioria dos outros contextos, globbing inclui um terceiro caractere especial, o **?**.
No entanto, os padrões de globbing do Selenium suportam apenas o asterisco e a classe de caracteres.

Para especificar um parâmetro de padrão globbing para um comando Selenese, você pode
prefixar o padrão com um rótulo **glob:**. No entanto, já que o padrão globbing é o padrão,
você também pode omitir o rótulo e especificar apenas o padrão em si.

Abaixo está um exemplo de dois comandos que usam padrões globbing. O
texto real do link na página que está sendo testada
foi "Film/Television Department"; usando um padrão
em vez do texto exato, o comando **click** funcionará mesmo se o
o texto do link for alterado para "Film & Television Department" ou "Film and Television
Department". O asterisco do padrão glob corresponderá a "qualquer coisa ou nada"
entre a palavra "Film" e a palavra "Television".

| Command            | Target                                 | Value                                                              |
| --------           | -------------------------------------- | ------------------------------------------------------------------ |
| click              | link=glob:Film*Television Department   |                                                                    |
| verifyTitle        | glob:\*Film\*Television\*              |                                                                    |


O título real da página acessada clicando no link era "De Anza Film And
Television Department - Menu". Usando um padrão em vez do texto exato,
o ``verifyTitle`` vai passar enquanto as duas palavras "Film" e "Television" aparecerem
(nessa ordem) em qualquer lugar no título da página. Por exemplo, se
o proprietário da página encurtar
o título apenas para "Film & Television Department", o teste ainda seria aprovado.
Usar um padrão para um link e um teste simples de que o link funcionou (como
o ``verifyTitle`` acima faz) pode reduzir bastante a manutenção de tais
casos de teste.

#### Padrão de Expressões Regulares

Os padrões de *expressão regular* são os mais poderosos dos três tipos
de padrões que o Selenese suporta. Expressões regulares
também são suportados pela maioria das linguagens de programação de alto nível, muitos editores de texto
e uma série de ferramentas, incluindo utilitários **grep**, **sed** e **awk** da linha de comando Linux / Unix. Em Selenese,
padrões de expressão regular permitem que um usuário execute muitas tarefas que iriam
ser muito difíceis de outra forma. Por exemplo, suponha que seu teste precise garantir que uma determinada célula da tabela contivesse nada além de um número.
``regexp:[0-9]+`` é um padrão simples que corresponderá a um número decimal de qualquer comprimento.

Enquanto os padrões de Globbing do Selenese suportam apenas o **\***
e **[ ]** (classe de caracteres), os padrões de expressão regular Selenese oferecem a mesma
ampla gama de caracteres especiais que existem em JavaScript. Abaixo
está um subconjunto desses caracteres especiais:

|    PATTERN    |        MATCH                                                                |
| ------------- | --------------------------------------------------------------------------- |
|   .           |   qualquer caractere isolado                                                |
|   [ ]         |   classe de caracteres: qualquer caractere definido dentros dos colchetes   | 
|   \*          |   quantificação: 0 ou mais do caractere anterior (ou grupo)                 |
|   \+          |   quantificação: 1 ou mais do caractere anterior (ou grupo)                 |
|   ?           |   quantificação: 0 ou 1 do caractere anterior (ou grupo)                    |
|   {1,5}       |   quantificação: 1 até 5 repetições do caractere anterior (ou grupo)        |
|   \|          |   alternação: o caractere/grupo na esquerda OU o caractere/grupo na direita |
|   ( )         |   agrupamento: normalmente usado com alternação e/ou quantificação          |

Os padrões de expressão regular em Selenese precisam ser prefixados com ``regexp:`` ou ``regexpi:``.
O primeiro é sensível a maiúsculas e minúsculas;
o último não faz distinção entre maiúsculas e minúsculas.

Alguns exemplos ajudarão a esclarecer como os padrões de expressão regular podem
ser usados com comandos Selenese. O primeiro usa o que é provavelmente
o padrão de expressão regular mais comumente usado - **.\*** ("ponto estrela"). Esta
sequência de dois caracteres pode ser traduzida como "0 ou mais ocorrências de
qualquer caractere" ou, mais simplesmente, "qualquer coisa ou nada." É o
equivalente do padrão globbing de um caractere **\*** (um único asterisco).

| Command            | Target                                 | Value                                                              |
| --------           | -------------------------------------- | ------------------------------------------------------------------ |
| click              | link=glob:Film*Television Department   |                                                                    |
| verifyTitle        | regexp:.\*Film.\*Television.\*         |                                                                    |


O exemplo acima é funcionalmente equivalente ao exemplo anterior
que usou padrões de globbing para este mesmo teste. As únicas diferenças
são o prefixo (**regexp:** em vez de **glob:**) e o padrão "qualquer coisa
ou nada" (**.\*** em vez de apenas **\***).

O exemplo mais complexo abaixo testa que a página de clima do Yahoo!
para Anchorage, Alasca, contém informações sobre o horário do nascer do sol:

| Command            | Target                                            | Value                                             |
| --------           | ------------------------------------------------- | ------------------------------------------------- |
| open               | http://weather.yahoo.com/forecast/USAK0012.html   |                                                   |
| verifyTextPresent  | regexp:Sunrise: \*[0-9]{1,2}:[0-9]{2} [ap]m       |                                                   |


Vamos examinar a expressão regular acima em partes:

|                            |                                                                                    |
| -------------------------- | ---------------------------------------------------------------------------------  |
|   ``Sunrise: *``           |   A string **Sunrise:** seguida por 0 ou mais espaços                              |
|   ``[0-9]{1,2}``           |   1 ou 2 dígitos (para a hora do dia)                                              |
|   ``:``                    |   O caractere **:** (sem caracteres especiais envolvidos)                          |
|   ``[0-9]{2}``             |   2 dígitos (para os minutos) seguidos de um espaço                                |
|   ``[ap]m``                |   "a" ou "p" seguido por "m" (am ou pm)                                            |


#### Padrão Exato

O tipo de padrão **exato** do Selenium é de utilidade marginal.
Ele não usa nenhum caractere especial. Então, se você precisasse procurar
um caractere de asterisco real (que é especial para globbing e
padrões de expressão regular), o padrão **exato** seria uma maneira
fazer isso. Por exemplo, se você quiser selecionar um item rotulado
"Real\*" em uma lista suspensa, o código a seguir pode funcionar ou não.
O asterisco no padrão ``glob:Real*`` irá corresponder a qualquer coisa ou a nada.
Portanto, se houvesse uma opção de seleção anterior rotulada "Números reais",
ser a opção selecionada em vez da opção "Real\*".

| Command            | Target                                            | Value                                             |
| --------           | ------------------------------------------------- | ------------------------------------------------- |
| select             | //select                                          |  glob:Real \*                                     |

A fim de garantir que o item "Real\*" seja selecionado, o prefixo ``exact:`` pode ser usado para criar um padrão **exato** conforme mostrado abaixo:

| Command            | Target                                            | Value                                             |
| --------           | ------------------------------------------------- | ------------------------------------------------- |
| select             | //select                                          |  exact:Real \*                                    |

Mas o mesmo efeito pode ser alcançado escapando o asterisco em um
padrão de expressão regular:

| Command            | Target                                            | Value                                             |
| --------           | ------------------------------------------------- | ------------------------------------------------- |
| select             | //select                                          |  regexp:Real \\\*                                 |
 
É bastante improvável que a maioria dos testadores precise procurar
um asterisco ou um conjunto de colchetes com caracteres dentro deles (a
classe de caracteres para padrões globbing). Assim, os padrões de globbing e
os padrões de expressão regular são suficientes para a grande maioria de nós.


## Os comandos "AndWait" 

A diferença entre um comando e sua alternativa *AndWait* é que o comando
regular (por exemplo, *click*) fará a ação e
continuará com o seguinte comando o mais rápido possível,
enquanto a alternativa *AndWait* (por exemplo, *clickAndWait*)
diz ao Selenium para **esperar** que a página
carregue após a ação ter sido realizada.

A alternativa *AndWait* é sempre usada quando a ação faz com que o navegador
navegue para outra página ou recarregue a atual.

Esteja ciente, se você usar um comando *AndWait* para uma ação que
não aciona uma navegação/atualização, seu teste falhará. Isto acontece
porque o Selenium alcançará o timeout de *AndWait* sem ver nenhuma
navegação ou atualização sendo feita, fazendo com que o Selenium lance uma exceção de timeout.

## Os comandos waitFor em aplicações Ajax

Em aplicações web orientadas a AJAX, os dados são recuperados do servidor sem
atualização da página. Usar os comandos *AndWait* não funcionará porque a página não é
realmente atualizada. Pausar a execução do teste por um determinado período de tempo
também não é uma boa abordagem, pois o elemento da web pode aparecer mais tarde ou antes do
período estipulado dependendo da capacidade de resposta do sistema, carga ou outros
fatores descontrolados do momento, levando a falhas de teste. A melhor abordagem
seria esperar pelo elemento necessário em um período dinâmico e então continuar
a execução assim que o elemento for encontrado.

Isso é feito usando comandos *waitFor*, como *waitForElementPresent* ou
*waitForVisible*, que espera dinamicamente, verificando a condição desejada
a cada segundo e continuando para o próximo comando no script assim que a
condição for atendida.

## Sequências de avaliação e controle de fluxo

Quando um script é executado, ele simplesmente é executado em sequência, um comando após o outro.

Selenese, por si só, não suporta declarações de condição (if-else, etc.) ou
iteração (for, while, etc.). Muitos testes úteis podem ser realizados sem fluxo
de controle. No entanto, para um teste funcional de conteúdo dinâmico, possivelmente envolvendo
múltiplas páginas, a lógica de programação é frequentemente necessária.

Quando o controle de fluxo é necessário, existem três opções:

a) Execute o script usando Selenium-RC e uma biblioteca cliente, como Java ou
   PHP para utilizar os recursos de controle de fluxo da linguagem de programação.

b) Execute um pequeno fragmento de JavaScript de dentro do script usando o comando storeEval.

c) Instale a extensão `goto_sel_ide.js`.

A maioria dos testadores exportará o script de teste para um arquivo de linguagem de programação que usa a
API Selenium-RC (consulte o capítulo Selenium-IDE). No entanto, algumas organizações preferem executar seus scripts a partir do Selenium-IDE sempre que possível (por exemplo, quando eles têm
muitas pessoas de nível júnior executando testes para eles, ou quando as habilidades de programação estão
em falta). Se este for o seu caso, considere um snippet de JavaScript ou a extensão goto_sel_ide.js. 


## Comandos de armazenamento e variáveis Selenium

Você pode usar variáveis Selenium para armazenar constantes no
início de um script. Além disso, quando combinado com um design de teste baseado em dados
(discutido em uma seção posterior), as variáveis Selenium podem ser usadas para armazenar valores
passados para o seu programa de teste da linha de comando, de outro programa ou de
um arquivo.
 
O comando *store* é o mais básico dos muitos comandos de armazenamento e pode ser usado
para simplesmente armazenar um valor constante em uma variável Selenium. Leva dois
parâmetros, o valor do texto a ser armazenado e uma variável Selenium. Use as
convenções de nomenclatura de variável padrão de apenas caracteres alfanuméricos quando
escolher um nome para sua variável.

| Command            | Target                                            | Value                              |
| --------           | ------------------------------------------------- | ---------------------------------- |
| store              | paul@mysite.org                                   |                                    |


Posteriormente em seu script, você desejará usar o valor armazenado de sua
variável. Para acessar o valor de uma variável, coloque a variável em
colchetes ({}) e preceda-a com um cifrão como a seguir.

| Command            | Target                                            | Value                              |
| --------           | ------------------------------------------------- | ---------------------------------- |
| verifyText         | //div/p                                           | \\${userName}                      |

Um uso comum de variáveis é armazenar a entrada para um campo input.

| Command            | Target                                            | Value                              |
| --------           | ------------------------------------------------- | ---------------------------------- |
| type               | id=login                                          | \\${userName}                      |


Variáveis Selenium podem ser usadas no primeiro ou segundo parâmetro e
são interpretadas pelo Selenium antes de quaisquer outras operações realizadas pelo
comando. Uma variável Selenium também pode ser usada em uma expressão de localização.

Existe um comando de armazenamento equivalente para cada comando de verificação e asserção. Aqui
são alguns comandos de armazenamento mais comumente usados.

### storeElementPresent 

Isso corresponde a verifyElementPresent. Ele simplesmente armazena um valor booleano - "true"
ou "false" - dependendo se o elemento de UI for encontrado.

### storeText 

StoreText corresponde a verifyText. Ele usa um localizador para identificar um texto específico
na página. O texto, se encontrado, é armazenado na variável. StoreText pode ser
usado para extrair texto da página que está sendo testada.

### storeEval 

Este comando leva um script como seu
primeiro parâmetro. A incorporação de JavaScript no Selenese é abordada na próxima seção.
StoreEval permite que o teste armazene o resultado da execução do script em uma variável.


## JavaScript e parâmetros Selenese

JavaScript pode ser usado com dois tipos de parâmetros Selenese: script
e não-script (geralmente expressões). Na maioria dos casos, você deseja acessar
e/ou manipular uma variável de caso de teste dentro do snippet JavaScript usado como
um parâmetro Selenese. Todas as variáveis criadas em seu caso de teste são armazenadas em
um *array associativo* JavaScript. Uma matriz associativa tem índices de string
em vez de índices numéricos sequenciais. A matriz associativa contendo
as variáveis do seu caso de teste é chamada **storedVars**. Sempre que você quiser
acessar ou manipular uma variável em um snippet de JavaScript, você deve consultá-la como **storedVars['yourVariableName']**.

### Usando JavaScript com parâmetros de script  

Vários comandos Selenese especificam um parâmetro **script** incluindo
**assertEval**, **verifyEval**, **storeEval** e **waitForEval**.
Esses parâmetros não requerem sintaxe especial.
Um usuário da Selenium-IDE simplesmente colocaria um snippet de código JavaScript
no campo apropriado, normalmente o campo **Target** (porque
um parâmetro **script** é normalmente o primeiro ou único parâmetro).

O exemplo abaixo ilustra como um snippet de JavaScript
pode ser usado para realizar um cálculo numérico simples:

| Command            | Target                                            | Value                              |
| --------           | ------------------------------------------------- | ---------------------------------- |
| store              | 10                                                | hits                               |
| storeXpathCount    | //blockquote                                      | blockquotes                        |
| storeEval          | storedVars['hits'].storedVars['blockquotes']      | paragraphs                         |

Este próximo exemplo ilustra como um snippet de JavaScript pode incluir chamadas para
métodos, neste caso, os métodos ``toUpperCase`` e ``toLowerCase``do objeto JavaScript String. 

| Command            | Target                                            | Value                              |
| --------           | ------------------------------------------------- | ---------------------------------- |
| store              | Edith Wharton                                     | name                               |
| storeEval          | storedVars['name'].toUpperCase()                  | uc                                 |
| storeEval          | storedVars['name'].toUpperCase()                  | lc                                 |


#### Usando JavaScript com parâmetros não-script

JavaScript também pode ser usado para ajudar a gerar valores para parâmetros, mesmo
quando o parâmetro não é especificado para ser do tipo **script**.
No entanto, neste caso, uma sintaxe especial é necessária - o parâmetro *inteiro*
deve ser prefixado por ``javascript{`` com um ``}`` final, que envolve o snippet JavaScript,
como em ``javascript{*yourCodeHere*}``.
Abaixo está um exemplo em que o segundo parâmetro do comando ``type``
- ``value`` - é gerado através do código JavaScript usando esta sintaxe especial:

| Command            | Target                                            | Value                                                |
| --------           | ------------------------------------------------- | ---------------------------------------------------- |
| store              | league of nations                                 | searchString                                         |
| type               | q                                                 | javascript{storedVars['searchString'].toUpperCase()} |


## *echo* - O comando de print do Selenese

Selenese tem um comando simples que permite imprimir texto para a saída do seu teste.
Isso é útil para fornecer notas de progresso informativas em seu
teste que são exibidas no console durante a execução. Essas notas também podem ser
usadas para fornecer contexto em seus relatórios de resultados de teste, o que pode ser útil
para descobrir onde existe um defeito em uma página, caso seu teste encontre um
problema. Finalmente, declarações echo podem ser usadas para imprimir o conteúdo de
variáveis Selenium.


| Command         | Target                                            | Value                                                |
| --------        | ------------------------------------------------- | ---------------------------------------------------- |
| echo            | Testing page footer now.                          |                                                      |
| echo            | Username is \\${userName}                         |                                                      |


## Alertas, Popups e Múltiplas Janelas

Suponha que você esteja testando uma página semelhante a esta.

```html
  <!DOCTYPE HTML>
  <html>
  <head>
    <script type="text/javascript">
      function output(resultText){
        document.getElementById('output').childNodes[0].nodeValue=resultText;
      }

      function show_confirm(){
        var confirmation=confirm("Chose an option.");
        if (confirmation==true){
          output("Confirmed.");
        }
        else{
          output("Rejected!");
        }
      }
      
      function show_alert(){
        alert("I'm blocking!");
        output("Alert is gone.");
      }
      function show_prompt(){
        var response = prompt("What's the best web QA tool?","Selenium");
        output(response);
      }
      function open_window(windowName){
        window.open("newWindow.html",windowName);
      }
      </script>
  </head>
  <body>

    <input type="button" id="btnConfirm" onclick="show_confirm()" value="Show confirm box" />
    <input type="button" id="btnAlert" onclick="show_alert()" value="Show alert" />
    <input type="button" id="btnPrompt" onclick="show_prompt()" value="Show prompt" />
    <a href="newWindow.html" id="lnkNewWindow" target="_blank">New Window Link</a>
    <input type="button" id="btnNewNamelessWindow" onclick="open_window()" value="Open Nameless Window" />
    <input type="button" id="btnNewNamedWindow" onclick="open_window('Mike')" value="Open Named Window" />

    <br />
    <span id="output">
    </span>
  </body>
  </html>
```

O usuário deve responder às caixas de alerta / confirmação, bem como mover o foco para as novas
janelas pop-up abertas. Felizmente, o Selenium pode cobrir pop-ups de JavaScript.

Mas antes de começarmos a abordar alertas / confirmações / solicitações em detalhes individuais, é
útil compreender a semelhança entre eles. Alertas, caixas de confirmação
e todos os prompts têm variações do seguinte

|Command|Description|
|--- |--- |
|assertFoo(pattern)|gera erro se o padrão não corresponder ao texto do pop-up|
|assertFooPresent|gera erro se o pop-up estiver presente|
|assertFooNotPresent|gera um erro se algum pop-up não estiver presente|
|storeFoo(variable)|armazena o texto do pop-up em uma variável|
|storeFooPresent(variable)|armazena o texto do pop-up em uma variável e retorna verdadeiro ou falso|


Ao executar no Selenium, pop-ups de JavaScript não aparecerão. Isto é porque
as chamadas de função são realmente substituídas em tempo de execução pelo próprio JavaScript do Selenium.
No entanto, só porque você não pode ver o pop-up, não significa que você não
tem que lidar com isso. Para lidar com um pop-up, você deve chamar sua função ``assertFoo(padrão)``.
Se você falhar em fazer a asserção da presença de um pop-up, seu próximo comando será
bloqueado e você obterá um erro semelhante ao seguinte ``[error] Error: There
was an unexpected Confirmation! [Chose an option.]``

### Alertas

Vamos começar com alertas porque eles são os pop-ups mais simples de lidar. Para começar,
abra o exemplo de HTML acima em um navegador e clique no botão "Show alert". Você vai
observar que, depois de fechar o alerta, o texto "Alert is gone." é exibido na
página. Agora execute as mesmas etapas com a gravação da Selenium IDE e verifique que
o texto é adicionado após fechar o alerta. Seu teste será parecido com
este:

|Command|Target|Value|
|--- |--- |--- |
|open|/||
|click|btnAlert||
|assertAlert|I’m blocking!||
|verifyTextPresent|Alert is gone.||

Você pode estar pensando: "Isso é estranho, nunca tentei fazer uma asserção nesse alerta." Mas isso é a
Selenium-IDE manipulando e fechando o alerta para você. Se você remover essa etapa e repetir
o teste você obterá o seguinte erro ``[error] Error: There was an unexpected
Alert! [I'm blocking!]``. Você deve incluir uma asserção do alerta para reconhecer
sua presença.
 
Se você apenas deseja verificar que um alerta está presente, mas não sabe ou não se importa
o texto que ele contém, você pode usar ``assertAlertPresent``. Isso retornará verdadeiro ou falso,
sendo que falso faz o teste parar.

### Confirmações

As confirmações se comportam da mesma forma que os alertas, com ``assertConfirmation`` e
``assertConfirmationPresent`` oferecendo as mesmas características de suas contrapartes de alerta.
No entanto, por padrão, o Selenium selecionará OK quando uma confirmação for exibida. Tente gravar
clicando no botão "Show confirm box" na página de amostra, mas clique no botão "Cancel"
no pop-up e, em seguida, confirme o texto de saída. Seu teste pode ser semelhante a este:

|Command|Target|Value|
|--- |--- |--- |
|open|/||
|click|btnConfirm||
|chooseCancelOnNextConfirmation|||
|assertConfirmation|Choose an option.||
|verifyTextPresent|Rejected||

A função ``chooseCancelOnNextConfirmation`` diz ao Selenium que todas as seguintes
confirmações devem retornar falso. Ela pode ser redefinido chamando chooseOkOnNextConfirmation.

Você vai notar que não pode repetir este teste, porque o Selenium reclama que há
uma confirmação não tratada. Isso ocorre porque a ordem dos registros de eventos do Selenium-IDE
faz com que o clique e chooseCancelOnNextConfirmation sejam colocados na ordem errada (faz sentido
se você pensar sobre isso, o Selenium não pode saber que você está cancelando antes de abrir uma confirmação).
Simplesmente troque esses dois comandos e seu teste funcionará bem.

### Prompts

Os prompts se comportam da mesma forma que os alertas, com ``assertPrompt`` e ``assertPromptPresent``
oferecendo as mesmas características que suas contrapartes de alerta. Por padrão, o Selenium irá esperar
você inserir dados quando o prompt for exibido. Tente gravar clicando no botão "Show prompt"
na página de amostra e digite "Selenium" no prompt. Seu teste pode ser semelhante a este:

|Command|Target|Value|
|--- |--- |--- |
|open|/||
|answerOnNextPrompt|Selenium!||
|click|id=btnPrompt||
|assertPrompt|What’s the best web QA tool?||
|verifyTextPresent|Selenium!||

Se você escolher "Cancel" no prompt, poderá observar que answerOnNextPrompt simplesmente mostrará um
alvo em branco. Selenium trata o cancelamento e uma entrada em branco no prompt basicamente como a mesma coisa.

## Depuração 

Depurar significa encontrar e corrigir erros em seu caso de teste. Isso é normal
 e parte do desenvolvimento.
  
Não vamos ensinar depuração aqui, pois a maioria dos novos usuários do Selenium já terá
alguma experiência básica com depuração. Se isso for novo para você, recomendamos
que você pergunte a um dos desenvolvedores em sua organização. 
  
### Pontos de interrupção e pontos de começo 

O Sel-IDE suporta a configuração de pontos de interrupção e a capacidade de iniciar e
interromper a execução de um caso de teste, de qualquer ponto dele. Ou seja, você
pode executar até um comando específico no meio do caso de teste e inspecionar como
o caso de teste se comporta nesse ponto. Para fazer isso, defina um ponto de interrupção no
comando imediatamente antes daquele a ser examinado.
  
Para definir um ponto de interrupção, selecione um comando, clique com o botão direito e no menu de contexto
selecione *Alternar ponto de interrupção (Toggle Breakpoint, em inglês)*.
Em seguida, clique no botão Executar para executar seu caso de teste
do início ao ponto de interrupção.
  
Às vezes também é útil executar um caso de teste de algum lugar no meio para
o final ou até um ponto de interrupção após o ponto de partida.
Por exemplo, suponha que seu caso de teste primeiro faz login no site e depois
executa uma série de testes e você está tentando depurar um desses testes.
No entanto, você só precisa fazer o login uma vez, mas precisa continuar executando novamente o seu
teste conforme você o desenvolve. Você pode fazer o login uma vez e, em seguida, executar seu caso de teste
de um ponto de início colocado após a parte de login do seu caso de teste. Isso vai
evitar que você tenha que fazer logout manualmente sempre que executar novamente.
  
Para definir um ponto de partida, selecione um comando, clique com o botão direito e do contexto
no menu selecione *Definir / Limpar Ponto Inicial (Set/Clear Start Point, em inglês)*.
Em seguida, clique no botão Executar para executar o
caso de teste começando naquele ponto inicial. 
  
### Avançando por etapas em um caso de teste

Para executar um caso de teste, um comando de cada vez ("percorrê-lo"), siga estes
passos:

1. Inicie o caso de teste em executando com o botão Executar na barra de ferramentas.

2. Pare imediatamente o caso de teste em execução com o botão Pausar.

3. Selecione repetidamente o botão Etapa.

### Botão Localizar

O botão Localizar é usado para ver qual elemento da interface do usuário atualmente exibido
página da web (no navegador) é usado no comando Selenium atualmente selecionado.
Isso é útil ao construir um localizador para o primeiro parâmetro de um comando (consulte a
seção sobre: ref:`locators <locators-section>` no capítulo Comandos do Selenium).
Ele pode ser usado com qualquer comando que identifica um elemento de UI em uma página da web,
ou seja, *click*, *clickAndWait*, *type* e certos comandos *assert* e *verify*,
entre outros.
  
Na visualização de Tabela, selecione qualquer comando que tenha um parâmetro localizador.
Clique no botão Localizar.
Agora olhe na página da web: deve haver um retângulo verde brilhante
envolvendo o elemento especificado pelo parâmetro localizador.

### Código Fonte da página para depuração

Muitas vezes, ao depurar um caso de teste, você simplesmente deve olhar para o código fonte da página (o
HTML da página da web que você está tentando testar) para determinar um problema. O Firefox
torna isso mais fácil. Simplesmente clique com o botão direito na página da web e selecione 'Exibir-> Código-fonte da página.
O HTML é aberto em uma janela separada. Use seu recurso de pesquisa (Editar => Encontrar)
para procurar uma palavra-chave para encontrar o HTML do elemento de UI que você está tentando
testar.

Como alternativa, selecione apenas a parte da página da web para a qual deseja
ver o código fonte. Em seguida, clique com o botão direito na página da web e
selecione Exibir Código Fonte da Seleção. Neste caso, a janela HTML separada conterá apenas uma pequena
quantidade de código fonte, com destaque na parte que representa a sua
seleção.

### Assistência de localizador

Sempre que a Selenium-IDE registra um argumento do tipo localizador, ela armazena
informações adicionais que permitem ao usuário visualizar outros possíveis
argumentos do tipo localizador que podem ser usados em seu lugar. Este recurso pode ser
muito útil para aprender mais sobre localizadores e muitas vezes é necessário para ajudar
a construir um tipo de localizador diferente do tipo que foi registrado.

Esta assistência do localizador é apresentada na janela Selenium-IDE
como um menu suspenso acessível na extremidade direita do campo Destino (Target, em inglês)
(somente quando o campo Destino contém um argumento do tipo localizador registrado).
Abaixo está uma captura de tela mostrando o conteúdo desse menu suspenso para um comando.
Observe que a primeira coluna do menu suspenso fornece localizadores alternativos,
enquanto a segunda coluna indica o tipo de cada alternativa.

![Selenium Locator Assistance](/images/documentation/legacy/selenium_ide_locator_assistance.png)


## Programando uma suíte de testes

Uma suíte de testes é uma coleção de casos de teste que é exibida no
painel mais à esquerda na IDE.
O painel da suíte de testes pode ser aberto ou fechado manualmente selecionando um pequeno ponto
no meio da borda direita do painel (que é a borda esquerda da
janela inteira da Selenium-IDE se o painel estiver fechado).

O painel da suíte de testes será aberto automaticamente quando uma suíte de testes existente
é aberta *ou* quando o usuário seleciona o item Novo Caso de Teste (New Test Case, em inglês) no
menu Arquivo. Neste último caso, o novo caso de teste aparecerá imediatamente
abaixo do caso de teste anterior.

A Selenium-IDE também suporta o carregamento de casos de teste pré-existentes usando Arquivo
-> Adicionar Caso de Teste. Isso permite que você adicione casos de teste existentes a
um novo conjunto de testes.

Um arquivo de suíte de testes é um arquivo HTML que contém uma tabela de uma coluna. Cada
célula de cada linha na seção <tbody> contém um link para um caso de teste.
O exemplo abaixo é de um conjunto de testes contendo quatro casos de teste:

```html
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample Selenium Test Suite</title>
    </head>
    <body>
        <table cellpadding="1" cellspacing="1" border="1">
            <thead>
                <tr><td>Test Cases for De Anza A-Z Directory Links</td></tr>
            </thead>
        <tbody>
            <tr><td><a href="./a.html">A Links</a></td></tr>
            <tr><td><a href="./b.html">B Links</a></td></tr>
            <tr><td><a href="./c.html">C Links</a></td></tr>
            <tr><td><a href="./d.html">D Links</a></td></tr>
        </tbody>
        </table>
    </body>
</html>
```
	
Observação: os arquivos do caso de teste não devem ser colocados no mesmo local do arquivo do conjunto de testes
    que os invoca. E em sistemas Mac OS e Linux, esse é realmente o
    caso. No entanto, no momento em que este livro foi escrito, um bug impedia os usuários do Windows
    de ser capaz de colocar os casos de teste em outro lugar que não com o conjunto de testes
    que os invoca.

## Extensões de usuário

As extensões de usuário são arquivos JavaScript que permitem criar as suas próprias
personalizações e recursos para adicionar funcionalidade adicional. Frequentemente, isso está 
na forma de comandos personalizados, embora esta extensibilidade não se limite a
comandos adicionais.
  
Existem várias extensões úteis criadas por usuários.

*IMPORTANTE: ESTA SEÇÃO ESTÁ DESATUALIZADA - REVISAREMOS EM BREVE.*

* extensões: http://wiki.openqa.org/display/SEL/Contributed+User-Extensions

[Extensão goto_sel_ide.js](http://wiki.openqa.org/download/attachments/379/goto_sel_ide.js):

Talvez a mais popular de todas as extensões da Selenium-IDE
é aquela que fornece controle de fluxo na forma de loops while e
condicionais primitivas. Esta extensão é a goto_sel_ide.js_. Para um exemplo
de como usar a funcionalidade fornecida por esta extensão, veja a
página criada pelo autor.

Para instalar esta extensão, coloque o nome do caminho da extensão em seu
computador no campo **Selenium Core extensions** da Selenium-IDE
Opções => Opções => Geral.

![Selenium IDE Extensions Install](/images/documentation/legacy/selenium_ide_extensions_install.png)

Depois de selecionar o botão **OK**, você deve fechar e reabrir a Selenium-IDE
para que o arquivo de extensões seja lido. Qualquer mudança que você fizer em uma
extensão também exigirá que você feche e reabra a Selenium-IDE.

Informações sobre como escrever suas próprias extensões podem ser encontradas perto da
parte inferior do documento Selenium Reference.

Às vezes, pode ser muito útil depurar passo a passo a Selenium IDE e sua
Extensão do usuário. O único depurador que parece capaz de depurar
as extensões baseadas em XUL / Chrome é o Venkman, que é suportada no Firefox até a versão 32 (incluída).
A depuração passo a passo foi verificada para funcionar com Firefox 32 e Selenium IDE 2.9.0.  

## Formato

Formato, no menu Opções, permite que você selecione uma linguagem para salvar
e exibir o caso de teste. O padrão é HTML.
  
Se você for usar Selenium-RC para executar seus casos de teste, este recurso é usado
para traduzir seu caso de teste em uma linguagem de programação. Selecione a
linguagem, por exemplo Java ou PHP, que você usará com Selenium-RC para o desenvolvimento
dos seus programas de teste. Em seguida, simplesmente salve o caso de teste usando Arquivo => Exportar Caso de Teste Como.
Seu caso de teste será traduzido para uma série de funções na linguagem que você
escolher. Essencialmente, o código do programa que suporta o seu teste é gerado para você
por Selenium-IDE.
  
Além disso, observe que se o código gerado não atender às suas necessidades, você pode alterar
editando um arquivo de configuração que define o processo de geração.
Cada linguagem com suporte possui definições de configuração que podem ser editadas. Isto
está em Opções => Opções => Formatos.  
  
## Executando testes da Selenium-IDE em diferentes navegadores

Embora o Selenium-IDE só possa executar testes no Firefox, os testes
desenvolvidos com Selenium-IDE podem ser executados em outros navegadores, usando uma
interface de linha de comando simples que invoca o servidor Selenium-RC. Este tópico
é abordado na seção: ref: `Executar testes Selenese <html-suite>` no capítulo
Selenium-RC. A opção de linha de comando *-htmlSuite* é o recurso específico de interesse.

## Solução de problemas

Abaixo está uma lista de pares de imagem / explicação que descrevem
fontes de problemas com Selenium-IDE:

*Table view is not available with this format.*

Esta mensagem pode ser exibida ocasionalmente na guia Tabela quando a Selenium IDE é
lançada. A solução alternativa é fechar e reabrir a Selenium IDE. Veja
a [issue 1008](http://code.google.com/p/selenium/issues/detail?id=1008).
Para maiores informações. Se você é capaz de reproduzir isso de forma confiável, por favor
forneça detalhes para que possamos trabalhar em uma correção.

------------------

*error loading test case: no command found*

Você usou **File => Open** para tentar abrir um arquivo de suíte de testes. Use **File => Open
Test Suite** em vez disso.

Uma solicitação de aprimoramento foi levantada para melhorar esta mensagem de erro. Veja
a [issue 1010](http://code.google.com/p/selenium/issues/detail?id=1010).

------------------

![Selenium IDE Trouble Timing](/images/documentation/legacy/selenium_ide_trouble_timing.png)

Este tipo de **erro** pode indicar um problema de tempo, ou seja, o elemento
especificado por um localizador em seu comando não foi totalmente carregado quando o comando
foi executado. Tente colocar um **pause 5000** antes do comando para determinar
se o problema está realmente relacionado ao tempo. Em caso afirmativo, investigue usando um
comando **waitFor\*** ou **\*AndWait** apropriado antes do comando com falha.

------------------

![Selenium IDE Trouble Param](/images/documentation/legacy/selenium_ide_trouble_param.png)

Sempre que sua tentativa de usar a substituição de variável falha, como é o
caso para o comando **open** acima, isso indica
que você não criou realmente a variável cujo valor você está
tentando acessar. Isto é
às vezes devido a colocar a variável no campo **Valor** quando
deve estar no campo **Destino** ou vice-versa. No exemplo acima,
os dois parâmetros para o comando **store** foram erroneamente
colocados na ordem inversa do que é necessário.
Para qualquer comando Selenese, o primeiro parâmetro obrigatório deve ir
no campo **Destino** e o segundo parâmetro obrigatório (se houver)
deve ir no campo **Valor**.  

----------

*error loading test case: [Exception... "Component returned failure code:
0x80520012 (NS_ERROR_FILE_NOT_FOUND) [nsIFileInputStream.init]" nresult:
"0x80520012 (NS_ERROR_FILE_NOT_FOUND)" location: "JS frame ::
chrome://selenium-ide/content/file-utils.js :: anonymous :: line 48" data: no]*

Um dos casos de teste em seu conjunto de testes não pode ser encontrado. Certifique-se de que
o caso de teste está realmente localizado onde o conjunto de testes indica que ele está localizado. Além disso,
certifique-se de que seus arquivos de caso de teste tenham a extensão .html em
seus nomes de arquivo e no arquivo de suíte de testes onde são referenciados.

Uma solicitação de aprimoramento foi levantada para melhorar esta mensagem de erro. Veja
a [issue 1011](http://code.google.com/p/selenium/issues/detail?id=1011).

----------

![Selenium IDE Trouble Extension](/images/documentation/legacy/selenium_ide_trouble_extension.png)

O conteúdo do seu arquivo de extensão não foi lido pela Selenium-IDE.
Certifique-se de ter especificado o nome do caminho adequado para o arquivo de extensões via
**Options => Options => General** no campo **Selenium Core extensions**.
Além disso, a Selenium-IDE deve ser reiniciada após qualquer alteração em um
arquivo de extensões *ou* no conteúdo do campo **Selenium Core extensions**.
