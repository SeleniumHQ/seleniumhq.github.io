---
title: "Contribuindo com o Site e Documentação do Selenium"
linkTitle: "Contribuindo com o Site e Documentação do Selenium"
weight: 2
requiresTranslation: true
description: >-
    Information on improving documentation and code examples for Selenium
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

Usamos [Hugo](https://gohugo.io/) e [Docsy theme](https://www.docsy.dev/)
para criar e gerar o website. Você vai necessitar de usar a versão "extended"
Sass/SCSS do binário Hugo. Recomendamos a versão 0.101.0 ou superior.

Por favor siga as instruções do Docsy [Install Hugo](https://www.docsy.dev/docs/getting-started/#install-hugo) 


### Passo 2: Branch

Crie uma branch e comece a hackear:

```shell
% git checkout -b my-feature-branch
```

Praticamos o desenvolvimento baseado em HEAD, o que significa que todas as mudanças são aplicadas
diretamente no topo do `dev`.

### Passo 3: Faça mudanças

O repositório contém o website e a documentação. Antes de começar a alterar coisas, por favor
veja o resto dos passos para preparar as dependências e sub-módulos (veja os comandos abaixo).

Para fazer alterações ao website, trabalha na pasta `website_and_docs`. Para ver uma previsão
do aspecto do website, execute `hugo server` a partir da raíz do projecto.

```shell
% git submodule update --init --recursive
% cd website_and_docs
% hugo server
```

See [Style Guide]({{< ref "style.md" >}}) for more information on our conventions for contribution

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
% git fetch origin
% git rebase origin/trunk
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
