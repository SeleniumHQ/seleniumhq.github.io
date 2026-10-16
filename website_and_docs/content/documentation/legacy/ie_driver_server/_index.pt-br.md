---
title: "Servidor de drivers do IE"
linkTitle: "Servidor de drivers do IE"
weight: 8
description: >
    O Internet Explorer Driver é um servidor autónomo que implementa a especificação WebDriver.
---
Esta documentação estava anteriormente localizada [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver-Internals)

O `InternetExplorerDriver` é um servidor autónomo que implementa o protocolo wire do WebDriver.
Este driver foi testado com o IE 11 e no Windows 10. Ele pode funcionar com versões mais antigas
do IE e do Windows, mas isso não é suportado.

O controlador suporta a execução de versões de 32 e 64 bits do browser. A escolha de como 
determinar qual o "bit-ness" a utilizar no lançamento do browser depende de qual a versão do 
IEDriverServer.exe é lançada. Se a versão de 32 bits do `IEDriverServer.exe` for iniciada, 
a versão de 32 bits do IE será iniciada. Da mesma forma, se a versão de 64 bits do 
IEDriverServer.exe for iniciada, a versão de 64 bits do IE será iniciada.

## Instalação

Não é necessário executar um instalador antes de usar o `InternetExplorerDriver`, embora seja necessária alguma 
configuração seja necessária. O executável do servidor standalone deve ser baixado da página 
da página [Downloads](https://www.selenium.dev/downloads/) e colocado no seu 
[PATH](http://en.wikipedia.org/wiki/PATH_(variável)).

## Pros

* Funciona num browser real e suporta JavaScript

## Cons

* Obviamente, o InternetExplorerDriver só funciona no Windows!
* Comparativamente lento (embora ainda bastante rápido :)

## Command-Line Switches

Como um executável autónomo, o comportamento do controlador IE pode ser modificado através de vários 
argumentos de linha de comando. Para definir o valor destes argumentos da linha de comandos, deve 
consultar a documentação do language binding que está a utilizar. As opções de linha de comando 
suportadas são descritas na tabela abaixo. Todas as opções -`<switch>`, --`<switch>` 
e /`<switch>` são suportados.

| Switch | Significado |
|:-------|:--------|
| Especifica a porta na qual o servidor HTTP do driver IE escutará os comandos das associações de idioma. O padrão é 5555. |
| Especifica o endereço IP do adaptador de anfitrião no qual o servidor HTTP do controlador IE irá escutar os comandos das Language Bindings. O padrão é 127.0.0.1. |
| --log-level=`<logLevel>` | Especifica o nível em que as mensagens de registo são emitidas. Os valores válidos são FATAL, ERROR, WARN, INFO, DEBUG e TRACE. O padrão é FATAL. |
| --log-file=`<logFile>` | Especifica o caminho completo e o nome do arquivo de log. O padrão é stdout. |
| --extract-path=`<path>` | Especifica o caminho completo para o diretório usado para extrair arquivos de suporte usados pelo servidor. O padrão é o diretório TEMP se não for especificado. |
| --silent | Suprime a saída de diagnóstico quando o servidor é iniciado. |

## Propriedades importantes do sistema

As seguintes propriedades do sistema (lidas usando `System.getProperty()` e definidas usando
`System.setProperty()` no código Java ou o sinalizador de linha de comando "`-DpropertyName=value`") 
são utilizados pelo `InternetExplorerDriver`:

| **Propriedade** | **O que significa** |
|:-------------|:------------------|
| `webdriver.ie.driver` | A localização do binário do driver do IE. |
| Especifica o endereço IP do adaptador do host no qual o driver do IE escutará. |
| Especifica o nível em que as mensagens de registo são emitidas. Os valores válidos são FATAL, ERROR, WARN, INFO, DEBUG e TRACE. O padrão é FATAL. |
| Especifica o caminho completo e o nome do arquivo de log. |
| `webdriver.ie.driver.silent` | Suprime a saída de diagnóstico quando o driver do IE é iniciado. |
| Especifica o caminho completo para o diretório usado para extrair arquivos de suporte usados pelo servidor. O padrão é o diretório TEMP se não for especificado. |

## Configuração Necessária

* O executável `IEDriverServer` deve ser [descarregado](https://www.selenium.dev/downloads/) e colocado no seu [PATH](http://en.wikipedia.org/wiki/PATH_(variável)).
* No IE 7 ou superior no Windows Vista, Windows 7 ou Windows 10, você deve definir as configurações do Modo Protegido para cada zona com o mesmo valor. O valor pode ser ligado ou desligado, desde que seja o mesmo para todas as zonas. Para definir as definições do Modo Protegido, escolha "Opções da Internet..." no menu Ferramentas e clique no separador Segurança. Para cada zona, existe uma caixa de verificação na parte inferior do separador com a designação "Ativar Modo Protegido".
* Além disso, o "Modo Protegido Avançado" deve ser desativado para o IE 10 e superior. Esta opção encontra-se no separador Avançadas da caixa de diálogo Opções da Internet.
* O nível de zoom do navegador deve ser definido para 100% para que os eventos nativos do rato possam ser definidos para as coordenadas correctas.
* Para o Windows 10, também é necessário definir "Alterar o tamanho do texto, das aplicações e de outros itens" para 100% nas definições de visualização.
* Para o IE 11 _apenas_, terá de definir uma entrada de registo no computador de destino para que o controlador possa manter uma ligação à instância do Internet Explorer que cria. Para instalações Windows de 32 bits, a chave que deve examinar no editor de registo é `HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE`. Para instalações do Windows de 64 bits, a chave é `HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE`. Tenha em atenção que a subchave `FEATURE_BFCACHE` pode ou não estar presente, e deve ser criada se não estiver presente. **Importante:** Dentro desta chave, crie um valor DWORD chamado `iexplore.exe` com o valor 0.

## Eventos Nativos e Internet Explorer

Como o `InternetExplorerDriver` é apenas para Windows, ele tenta usar os chamados eventos "nativos", ou de nível de SO 
para executar operações de mouse e teclado no navegador. Isto está em contraste com o uso de 
eventos JavaScript simulados para as mesmas operações. A vantagem de usar eventos nativos é que 
não depende da sandbox do JavaScript e garante a propagação adequada de eventos JavaScript 
dentro do navegador. No entanto, existem atualmente alguns problemas com eventos de rato quando a janela do browser IE 
não tem foco, e quando se tenta passar o mouse sobre elementos.

### Foco no navegador

O desafio é que o próprio IE parece não respeitar totalmente as mensagens do Windows que enviamos para a 
janela do navegador IE (`WM\_MOUSEDOWN` e `WM\_MOUSEUP`) se a janela não tiver o foco. 
Especificamente, o elemento que está sendo clicado receberá uma janela de foco em torno dele, mas o clique 
não será processado pelo elemento. Provavelmente, nós não deveríamos estar enviando mensagens; ao invés disso, 
deveríamos estar usando a API `SendInput()`, mas essa API requer explicitamente que a janela tenha o 
foco. Nós temos dois objetivos conflitantes com o projeto WebDriver.

Primeiro, nós nos esforçamos para emular o usuário o mais próximo possível. Isso significa usar eventos nativos 
em vez de simular os eventos usando JavaScript.

Em segundo lugar, queremos não exigir o foco da janela do browser que está a ser automatizada. Isto significa que 
apenas forçar a janela do navegador para o primeiro plano é subótimo.

Uma consideração adicional é a possibilidade de várias instâncias do IE serem executadas em várias instâncias do 
instâncias do WebDriver, o que significa que qualquer solução do tipo "trazer a janela para o primeiro plano" terá 
terá que ser envolvida em algum tipo de construção de sincronização (mutex?) dentro do código C++ do driver do IE. 
código C++ do driver do IE. Mesmo assim, esse código ainda estará sujeito a condições de corrida, se, por exemplo, o 
utilizador colocar outra janela em primeiro plano entre o driver colocar o IE em primeiro plano 
e a execução do evento nativo.

A discussão sobre os requisitos do controlador e a forma de dar prioridade a estes dois objectivos 
objectivos contraditórios está em curso. A sabedoria predominante atual é dar prioridade ao primeiro em detrimento do segundo e documentar que a sua máquina 
o segundo, e documentar que sua máquina ficará indisponível para outras tarefas ao usar 
o driver do IE. No entanto, essa decisão está longe de ser finalizada, e o código para implementá-la é 
provavelmente será bastante complicado.

### Passar o rato sobre elementos

Quando você tenta passar o mouse sobre elementos e o cursor físico do mouse está dentro dos limites 
da janela do navegador IE, o hover não funcionará. Mais especificamente, o hover parecerá 
funcionar por uma fração de segundo, e então o elemento voltará ao seu estado anterior. 
anterior. A teoria prevalecente sobre o motivo pelo qual isso ocorre é que o IE está fazendo algum tipo de teste de acerto 
durante o seu ciclo de eventos, o que faz com que responda à posição física do rato quando o 
quando o cursor físico está dentro dos limites da janela. A equipa de desenvolvimento do WebDriver não conseguiu 
de descobrir uma solução alternativa para esse comportamento do IE.

### Clicando em elementos `<option>` ou enviando formulários e `alert()`

Há dois lugares onde o driver do IE não interage com elementos usando eventos nativos
Isso ocorre ao clicar em elementos `<option>` dentro de um elemento `<select>`. Sob circunstâncias normais,
o driver do IE calcula onde clicar com base na posição e tamanho do elemento, normalmente
conforme retornado pelo método JavaScript `getBoundingClientRect()`. No entanto, para elementos `<option>`,
`getBoundingClientRect()` retorna um retângulo com posição zero e tamanho zero. O driver do IE
lida com esse cenário usando o Automation Atom `click()`, que essencialmente define
a propriedade `.selected` do elemento e simula o evento `onChange` em JavaScript.
No entanto, isso significa que se o evento `onChange` do elemento `<select>` contiver JavaScript
código que chama `alert()`, `confirm()` ou `prompt()`, chamar o método `click()` do WebElement irá
aguarde até que a caixa de diálogo modal seja descartada manualmente. Não há solução alternativa conhecida para esse comportamento usando apenas o código WebDriver.

Da mesma forma, há alguns cenários em que a submissão de um formulário HTML através do método `submit()` do WebElement pode ter o mesmo efeito. 
do WebElement pode ter o mesmo efeito. Isso pode acontecer se o driver chamar a função JavaScript `submit()` 
do JavaScript no formulário, e houver um manipulador de eventos onSubmit que chame o método JavaScript `alert()`, 
`confirm()`, ou `prompt()` do JavaScript.

Esta restrição está registada como problema 3508 (no Google Code).

## Múltiplas instâncias do `InternetExplorerDriver`

Com a criação do `IEDriverServer.exe`, deve ser possível criar e utilizar múltiplas instâncias simultâneas do `InternetExplorerDriver`. 
instâncias simultâneas do `InternetExplorerDriver`. Entretanto, esta funcionalidade ainda não foi 
não foi testada, e pode haver problemas com cookies, foco de janela, e coisas do tipo. Se você tentar 
utilizar múltiplas instâncias do driver do IE, e se deparar com tais problemas, considere utilizar o 
`RemoteWebDriver` e máquinas virtuais.

Existem 2 soluções para o problema dos cookies (e outros itens de sessão) partilhados entre 
várias instâncias do InternetExplorer.

A primeira é iniciar o InternetExplorer em modo privado. Depois disso, o InternetExplorer será 
iniciado com dados de sessão limpos e não guardará os dados de sessão alterados ao sair. Para o fazer, é necessário 
precisa passar 2 capacidades específicas para o driver: `ie.forceCreateProcessApi` com valor `true 
e `ie.browserCommandLineSwitches` com o valor `private`. Note que isso só funcionará 
para o InternetExplorer 8 e mais recentes, e o Registo do Windows 
O caminho `HKLM_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main` deve conter a chave 
`TabProcGrowth` com o valor `0`.

A segunda é limpar a sessão durante a inicialização do InternetExplorer. Para isso é necessário passar 
capacidade específica `ie.ensureCleanSession` com valor `true` para o driver. Isso limpa o cache 
para todas as instâncias em execução do InternetExplorer, incluindo aquelas iniciadas manualmente.

## Executando o `IEDriverServer.exe` remotamente

O servidor HTTP iniciado pelo `IEDriverServer.exe` define uma lista de controlo de acesso para aceitar apenas 
conexões da máquina local, e não permite conexões de entrada de máquinas remotas. 
No momento, isso não pode ser alterado sem modificar o código fonte do `IEDriverServer.exe`. 
Para executar o driver do Internet Explorer em uma máquina remota, use o servidor remoto Java standalone 
em conexão com o equivalente do `RemoteWebDriver` da sua linguagem de ligação.

## Executando o `IEDriverServer.exe` em um serviço do Windows

A tentativa de usar o IEDriverServer.exe como parte de um aplicativo de serviço do Windows é expressamente 
não é suportado. Os processos de serviço, e os processos gerados por eles, têm requisitos muito diferentes 
do que aqueles que são executados num contexto de utilizador normal. O `IEDriverServer.exe` não é explicitamente testado nesse 
ambiente, e inclui chamadas da API do Windows que são documentadas como proibidas de serem usadas 
em processos de serviço. Embora seja possível fazer com que o driver do IE funcione durante a execução em um processo de serviço, os usuários que encontrarem problemas 
um processo de serviço, os utilizadores que encontrarem problemas nesse ambiente terão de procurar as suas 
suas próprias soluções.
