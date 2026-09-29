---
title: "Linguagem específica de domínio (DSL)"
linkTitle: "Linguagem específica de domínio (DSL)"
weight: 4
aliases: [
"/documentation/guidelines/domain_specific_language/"
]
---

Uma linguagem específica de domínio (DSL) é um sistema que fornece ao usuário
um meio expressivo de resolver um problema. Ele permite a um usuário
interagir com o sistema em seus termos - não apenas na linguagem do programador.

Seus usuários, em geral, não se importam com a aparência do seu site. Eles não
preocupam-se com a decoração, animações ou gráficos. Eles
deseja usar seu sistema para empurrar seus novos funcionários através do
processo com dificuldade mínima; eles querem reservar uma viagem para o Alasca;
eles querem configurar e comprar unicórnios com desconto. Seu trabalho como
testador deve chegar o mais perto possível de “capturar” essa mentalidade.
Com isso em mente, começamos a "modelar" o aplicativo que você está
trabalhando, de modo que os scripts de teste (o único proxy de pré-lançamento do usuário) “fala a linguagem” e representa o usuário.

Com Selenium, DSL é geralmente representado por métodos, escritos para fazer
a API simples e legível - eles permitem um relatório entre o
desenvolvedores e as partes interessadas (usuários, proprietários de produtos, negócios
especialistas em inteligência, etc.).

## Benefícios

* **Legível:** As partes interessadas da empresa podem entendê-lo.
* **Gravável:** Fácil de escrever, evita duplicações desnecessárias.
* **Extensível:** Funcionalidade pode (razoavelmente) ser adicionada
   sem quebrar contratos e funcionalidades existentes.
* **Manutenção:** Deixando os detalhes de implementação fora do teste
   casos, você está bem isolado contra alterações no AUT *.


## Java

Aqui está um exemplo de um método DSL razoável em Java.
Por questão de brevidade, ele assume que o objeto `driver` é pré-definido
e está disponível para o método.

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/DomainSpecificLanguageExample.java#L21-L43" >}}

Este método abstrai completamente os conceitos de campos de entrada,
botões, cliques e até páginas do seu código de teste. Usando este
abordagem, tudo o que o testador precisa fazer é chamar esse método. Isto dá
uma vantagem de manutenção: se os campos de login mudaram, você
teria apenas que alterar esse método - não seus testes.

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

    // Agora que estamos logados, fazemos alguma outra coisa--como usamos uma DSL para suportar
    // nossos testadores, é apenas escolher um dos métodos disponíveis.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Algo deveria ter sido feito!", something.wasDone());

    // Note que ainda não nos referimos a nenhum botão ou web control nesse
    // script...
}
```

Vale a pena repetir: um de seus principais objetivos deve ser escrever um
API que permite que seus testes resolvam **o problema em questão, e NÃO
o problema da IU**. A IU é uma preocupação secundária para o seu
usuários - eles não se importam com a interface do usuário, eles apenas querem fazer seu trabalho
feito. Seus scripts de teste devem ser lidos como uma lista de itens sujos que o usuário deseja FAZER e as coisas que deseja SABER. Os testes
não devem se preocupar com COMO a interface do usuário exige que você vá
sobre isso.

***AUT**: Application under test

