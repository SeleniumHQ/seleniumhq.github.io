---
title: "Contribuindo com o Site e Documentação do Selenium"
linkTitle: "Contribuindo com o Site e Documentação do Selenium"
weight: 2
requiresTranslation: true
aliases: 
        [
          "/documentation/pt-br/contributing/",
          "/documentation/pt-br/front_matter/typographical_conventions/"
        ]
---

Selenium é um grande projeto de software, seu site e documentação são fundamentais
para entender como as coisas funcionam e aprender maneiras eficazes de explorar
seu potencial.

Este projeto contém o site e a documentação do Selenium. Isto é
um esforço contínuo (não direcionado a nenhuma versão específica) para fornecer
informações atualizadas sobre como usar o Selenium de forma eficaz, como se
envolver e como contribuir para o Selenium.

As contribuições para o site e documentação seguem o processo descrito na seção abaixo sobre contribuições. 

---

O projeto Selenium recebe contribuições de todos. Há um
várias maneiras de ajudar:

## Reportar um problema

Ao relatar um novo problema ou comentar sobre problemas existentes, por favor
certifique-se de que as discussões estão relacionadas a questões técnicas concretas sobre o
software Selenium, seu site e/ou documentação.

Todos os componentes do Selenium mudam bastante rápido ao longo do tempo, então este
pode fazer com que a documentação fique desatualizada. Se você observar que este é
o caso, como mencionado, não hesite em criar um problema para isso.
Também pode ser possível que você saiba como atualizar a
documentação, então, envie-nos um Pull Request com a
alteração.

Se você não tem certeza se o que encontrou é um problema ou não,
pergunte através dos canais de comunicação descritos em
https://selenium.dev/support.

## Contribuições

O projeto Selenium dá as boas-vindas a novos contribuidores. Indivíduos fazendo
contribuições significativas e valiosas ao longo do tempo são transformados em _Committers_
e recebem acesso de commit ao projeto.

Este guia irá guiá-lo através do processo de contribuição.

### Passo 1: Fork

Faça um fork do projeto [no Github](https://github.com/seleniumhq/seleniumhq.github.io)
e faça checkout na sua cópia localmente.

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### Dependências: Hugo

We use [Hugo](https://gohugo.io/) and the [Docsy theme](https://www.docsy.dev/)
to build and render the site. You will need the “extended” 
Sass/SCSS version of the Hugo binary to work on this site. We recommend
to use Hugo 0.83.1 or higher.

Please follow the [Install Hugo](https://www.docsy.dev/docs/getting-started/#install-hugo) 
instructions from Docsy.

### Passo 2: Branch

Crie uma branch e comece a hackear:

```shell
% git checkout -b my-feature-branch
```

Praticamos o desenvolvimento baseado em HEAD, o que significa que todas as mudanças são aplicadas
diretamente no topo do `dev`.

### Passo 3: Faça mudanças

The repository contains the site and docs. Before jumping into
making changes, please initialize the submodules and install the
needed dependencies (see commands below). To make changes to the site, 
work on the `website_and_docs` directory. To see a live preview of 
your changes, run `hugo server` on the site's root directory.

```shell
% git submodule update --init --recursive
% cd website_and_docs
% hugo server
```

#### Capitalização de títulos

Deve-se evitar a capitalização do título,
como _Um Título Muito Estiloso_,
e em vez disso, use _Um título muito estiloso_.
Letras maiúsculas gratuitas, ou caixa do título,
muitas vezes mostram um mal-entendido - ou um desprezo por -
convenções ortográficas.
Preferimos o que é conhecido como _sentence case_,
com uma única inicial maiúscula para iniciar cabeçalhos.

#### Comprimento da linha

Ao editar o código fonte da documentação,
que é escrito em HTML puro,
limite o comprimento das linhas a cerca de 72 caracteres.

Alguns de nós dão um passo adiante
e usam o que é chamado de
[_linefeeds semânticos_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
que é uma técnica pela qual as linhas de origem HTML,
que não são lidos pelo público,
são divididas em "intervalos naturais" na prosa.
Em outras palavras, as frases são divididas
em quebras naturais entre as orações.
Em vez de se preocupar com as linhas de cada parágrafo
de modo que todos terminem perto da margem direita,
os feeds de linha podem ser adicionados em qualquer lugar
que existe uma ruptura entre as ideias.

Isso pode tornar as diffs muito fáceis de ler
ao colaborar por meio do git,
mas não é algo que obrigamos os colaboradores a usar.

#### Traducciones

A documentação é traduzida para vários idiomas e as traduções são baseadas no conteúdo em inglês. Ao alterar um arquivo, **certifique-se** de realizar a
mudanças em todos os outros arquivos traduzidos também. Isso pode ser diferente dependendo
sobre a mudança, por exemplo:
 
* Se você adicionar um exemplo de código ao arquivo `browser_manipulation.en.md`,
também adicione-o a `browser_manipulation.es.md`,` browser_manipulation.ef.md`,
`browser_manipulation.ja.md`, e todos os outros arquivos traduzidos.
* Se você encontrar uma tradução que possa ser melhorada, altere apenas o arquivo traduzido.
* Se você estiver adicionando uma nova tradução de idioma, adicione os novos arquivos com o
sufixo apropriado. Não há necessidade de traduzir tudo para enviar um
PR, pode ser feito iterativamente. Não se esqueça de verificar algumas configurações necessárias de
valores no arquivo `config.toml`.
* Se você fizer alterações de texto na versão em inglês, substitua a mesma seção em
os arquivos traduzidos com sua alteração (sim, em inglês), e adicione o seguinte
observe na parte superior do arquivo.
 

```
{{%/* pageinfo color="warning" */%}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
   it by sending us pull requests!
</p>
{{%/* /pageinfo */%}}
```

### Passo 4: Commit

Primeiro, certifique-se de que o git saiba seu nome e endereço de e-mail:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Escrever boas mensagens de commit é importante.** Uma mensagem de confirmação
deve descrever o que mudou, por que e conter referência de problemas corrigidos (se
houver). Siga estas diretrizes ao escrever um:

1. A primeira linha deve ter cerca de 50 caracteres ou menos e conter uma
     breve da descrição da mudança.
2. Mantenha a segunda linha em branco.
3. Quebra todas as outras linhas em 72 colunas.
4. Incluir `Fixes # N`, onde _N_ é o número do problema que o commit corrige
    se houver.

Uma boa mensagem de confirmação pode ter a seguinte aparência:

```text
explain commit normatively in one line

Body of commit message is a few lines of text, explaining things
in more detail, possibly giving some background about the issue
being fixed, etc.

The body of the commit message can be several paragraphs, and
please do proper word-wrap and keep columns shorter than about
72 characters or so. That way `git log` will show things
nicely even when it is indented.

Fixes #141
```

A primeira linha deve ser significativa, pois é o que as pessoas veem quando
executam `git shortlog` ou` git log --oneline`.

### Passo 5: Rebase

Use `git rebase` (não `git merge`) para sincronizar seu trabalho de tempos em tempos.

```shell
% git fetch upstream
% git rebase upstream/trunk
```

### Passo 6: Teste

Lembre-se sempre de [executar o servidor local](https://gohugo.io/getting-started/usage/#livereload),
com isso, você pode ter certeza de que suas alterações não prejudicaram nada.

### Passo 7: Push

```shell
% git push origin my-feature-branch
```

Acesse https://github.com/yourusername/seleniumhq.github.io.git e
clique em _Pull Request_ e preencha o formulário. **Por favor indique
que você assinou o CLA** (consulte a Etapa 7).

Os Pull Requests geralmente são revisados em alguns dias. Se houver
comentários a abordar, aplique suas alterações em novos commits (de preferência
[fixups](http://git-scm.com/docs/git-commit)) e envie para a mesma
branch.

### Passo 8: Integração

Quando a revisão do código for concluída, um committer integrará seu PR no branch de tronco do repositório. Porque gostamos de manter um
histórico linear no `trunk`, nós normalmente iremos dar Squash & Rebase no histórico da sua branch.

## Comunicação

Todos os detalhes sobre como se comunicar com os colaboradores do projeto
e a comunidade em geral podem ser encontrados em https://selenium.dev/support
