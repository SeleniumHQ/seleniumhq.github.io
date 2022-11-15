---
title: "Detalhes"
linkTitle: "Detalhes"
weight: 2
description: >
  Selenium é um projeto abrangente para uma gama de ferramentas e bibliotecas que permitem
  e suportam a automação de navegadores da web.
aliases: [
"/documentation/pt-br/introduction/the_selenium_project_and_tools/",
"/pt-br/documentation/overview/a_deeper_look"
]
---

### Selenium controla navegadores

_Selenium_ é muitas coisas
mas, em sua essência, é um conjunto de ferramentas para automação de navegador da web
que usa as melhores técnicas disponíveis
para controlar remotamente as instâncias do navegador
e emular a interação do usuário com o navegador.

Ele permite que os usuários simulem atividades comuns realizadas por usuários finais;
inserir texto em campos,
selecionando valores suspensos e caixas de seleção,
e clicar em links em documentos.
Ele também fornece muitos outros controles, como o movimento do mouse,
execução arbitrária de JavaScript e muito mais.

Embora seja usado principalmente para testes de front-end de sites,
Selenium é basicamente uma biblioteca de agente de usuário de navegador.
As interfaces são onipresentes em seus aplicativos,
o que incentiva a composição com outras bibliotecas para atender a sua finalidade.


### Uma interface para tudo

Um dos princípios norteadores do projeto
é oferecer suporte a uma interface comum para todas as tecnologias de navegador (principais).
Os navegadores da web são aplicativos incrivelmente complexos e altamente projetados,
realizando suas operações de maneiras completamente diferentes
mas que frequentemente têm a mesma aparência ao fazê-lo.
Mesmo que o texto seja renderizado com as mesmas fontes,
as imagens sejam exibidas no mesmo lugar
e os links levem você ao mesmo destino.
O que está acontecendo por baixo é tão diferente quanto noite e dia.
Selenium "abstrai" essas diferenças,
esconde seus detalhes e complexidades da pessoa que está escrevendo o código.
Isso permite que você escreva várias linhas de código para realizar um fluxo de trabalho complicado,
mas essas mesmas linhas serão executadas no Firefox,
Internet Explorer, Chrome e todos os outros navegadores compatíveis.


### Ferramentas e suporte

A abordagem de design minimalista do Selenium lhe dá a
versatilidade para ser incluído como um componente em aplicações maiores.
A infraestrutura circundante fornecida sob o Selenium
dá a você as ferramentas para montar
sua [Grid de navegadores]({{< ref "/grid.md" >}})
para que os testes possam ser executados em diferentes navegadores e sistemas operacionais
em uma variedade de máquinas.

Imagine um banco de computadores em sua sala de servidores ou data center
todos abrindo navegadores ao mesmo tempo
acessando links, formulários,
e tabelas &mdash; testando seu aplicativo 24 horas por dia.
Por meio da interface de programação simples
fornecido para os idiomas mais comuns,
esses testes serão executados incansavelmente em paralelo,
reportando de volta para você quando ocorrerem erros.

É o objetivo de ajudar a tornar isso uma realidade para você,
fornecendo aos usuários ferramentas e documentação não apenas para controlar os navegadores
mas para tornar mais fácil dimensionar e implantar essas grades.


### Quem usa Selenium

Muitas das empresas mais importantes do mundo
adotaram o Selenium para seus testes baseados em navegador,
frequentemente substituindo esforços de anos envolvendo outras ferramentas proprietárias.
À medida que sua popularidade cresceu, seus requisitos e desafios se multiplicaram.

Conforme a web se torna mais complicada
e novas tecnologias são adicionadas aos sites,
é a missão deste projeto acompanhá-los sempre que possível.
Sendo um projeto de código aberto,
este apoio é fornecido por meio da generosa doação de tempo de muitos voluntários,
cada um deles tem um “trabalho diurno”.

Outra missão do projeto é incentivar
mais voluntários para participar deste esforço,
e construir uma comunidade forte
para que o projeto possa continuar a acompanhar as tecnologias emergentes
e permanecer uma plataforma dominante para automação de teste funcional.

