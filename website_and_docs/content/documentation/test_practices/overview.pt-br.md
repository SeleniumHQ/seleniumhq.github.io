---
title: "Sobre automação de testes"
linkTitle: "Sobre automação de testes"
weight: 2
aliases: [
"/documentation/pt-br/introduction/on_test_automation/",
"/pt-br/documentation/guidelines/on_test_automation/"
]
---

Primeiro, comece perguntando a si mesmo se você realmente precisa ou não de um navegador.
As probabilidades são de que, em algum ponto, se você estiver trabalhando em um aplicativo da web complexo,
você precisará abrir um navegador e realmente testá-lo.

No entanto, os testes funcionais do usuário final, como os testes Selenium, são caros para executar.
Além disso, eles normalmente exigem infraestrutura substancial
para ser executado de forma eficaz.
É uma boa regra sempre se perguntar se o que você deseja testar
pode ser feito usando abordagens de teste mais leves, como testes de unidade
ou com uma abordagem de nível inferior.

Depois de determinar que está no negócio de teste de navegador da web,
e você tem seu ambiente Selenium pronto para começar a escrever testes,
você geralmente executará alguma combinação de três etapas:

* Configurar os dados
* Executar um conjunto discreto de ações
* Avaliar os resultados

Você deve manter essas etapas o mais curtas possível;
uma ou duas operações devem ser suficientes na maioria das vezes.
A automação do navegador tem a reputação de ser "instável",
mas, na realidade, é porque os usuários freqüentemente exigem muito dele.
Em capítulos posteriores, retornaremos às técnicas que você pode usar
para mitigar problemas aparentemente intermitentes nos testes,
em particular sobre como [superar as condições de corrida]({{< ref "/waits.md" >}})
entre o navegador e o WebDriver.

Mantendo seus testes curtos
e usando o navegador da web apenas quando você não tiver absolutamente nenhuma alternativa,
você pode ter muitos testes com instabilidade mínima.

Uma vantagem distinta dos testes do Selenium
é sua capacidade inerente de testar todos os componentes do aplicativo,
de back-end para front-end, da perspectiva do usuário.
Em outras palavras, embora os testes funcionais possam ser caros para executar,
eles também abrangem grandes partes críticas para os negócios de uma só vez.


### Requerimentos de teste

Como mencionado antes, os testes do Selenium podem ser caros para serem executados.
Até que ponto depende do navegador em que você está executando os testes,
mas historicamente o comportamento dos navegadores tem variado tanto que muitas vezes
foi uma meta declarada testar cruzado contra vários navegadores.

Selenium permite que você execute as mesmas instruções em vários navegadores
em vários sistemas operacionais,
mas a enumeração de todos os navegadores possíveis,
suas diferentes versões e os muitos sistemas operacionais em que são executados
rapidamente se tornará uma tarefa não trivial.


### Vamos começar com um exemplo

Larry escreveu um site que permite aos usuários solicitarem seus
unicórnios personalizados.

O fluxo de trabalho geral (o que chamaremos de "caminho feliz") é algo
como isso:

* Criar uma conta
* Configurar o unicórnio
* Adicionar ao carrinho de compras
* Verificar e pagar
* Dar feedback sobre o unicórnio


Seria tentador escrever um grande roteiro do Selenium
para realizar todas essas operações - muitos tentarão.
**Resista à tentação!**
Isso resultará em um teste que
a) leva muito tempo,
b) estará sujeito a alguns problemas comuns em torno de problemas de tempo de renderização de página, e
c) se falhar,
não lhe dará um método conciso e “superficial” para diagnosticar o que deu errado.

A estratégia preferida para testar este cenário seria
dividi-lo em uma série de testes independentes e rápidos,
cada um dos quais tem uma “razão” de existir.

Vamos fingir que você deseja testar a segunda etapa:
Configure o unicórnio.
Ele executará as seguintes ações:

* Criar uma conta
* Configurar o unicórnio

Observe que estamos pulando o restante dessas etapas,
vamos testar o resto do fluxo de trabalho em outros casos de teste pequenos e discretos
depois de terminarmos com este.

Para começar, você precisa criar uma conta.
Aqui você tem algumas escolhas a fazer:

* Deseja usar uma conta existente?
* Você deseja criar uma nova conta?
* Existem propriedades especiais de tal usuário que precisam ser
  levadas em consideração antes do início da configuração?

Independentemente de como você responde a esta pergunta,
a solução é torná-la parte da etapa de "configurar os dados" do teste.
Se Larry expôs uma API que permite a você (ou qualquer pessoa)
criar e atualizar contas de usuário,
certifique-se de usar isso para responder a esta pergunta.
Se possível, você deseja iniciar o navegador somente depois de ter um usuário "em mãos",
cujas credenciais você pode usar para fazer login.

Se cada teste para cada fluxo de trabalho começar com a criação de uma conta de usuário,
muitos segundos serão adicionados à execução de cada teste.
Chamar uma API e falar com um banco de dados são operações rápidas,
"sem cabeçalho" que não requerem o processo caro de
abrir um navegador, navegar para as páginas certas,
clicando e aguardando o envio dos formulários, etc.

Idealmente, você pode abordar esta fase de configuração em uma linha de código,
que será executado antes que qualquer navegador seja iniciado:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
// mas eles não têm informações de pagamento configuradas, nem têm
// privilégios administrativos. No momento em que o usuário é criado, seu endereço
// de e-mail e senha são gerados aleatoriamente - você nem precisa
// conhecê-los.
User user = UserFactory.createCommonUser(); //Este método está definido em algum outro lugar.

// Faça login como este usuário.
// O login neste site leva você à sua página pessoal "Minha conta", e então
// o objeto AccountPage é retornado pelo método loginAs, permitindo que você
// execute ações da AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< /tab >}}
  {{< tab header="Python" >}}
# Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
# mas eles não têm informações de pagamento configuradas, nem têm
# privilégios administrativos. No momento em que o usuário é criado, seu endereço
# de e-mail e senha são gerados aleatoriamente - você nem precisa
# conhecê-los.
user = user_factory.create_common_user() #This method is defined elsewhere.

# Faça login como este usuário.
# O login neste site leva você à sua página pessoal "Minha conta", e então
# o objeto AccountPage é retornado pelo método loginAs, permitindo que você
# execute ações da AccountPage.
account_page = login_as(user.get_email(), user.get_password())
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
// mas eles não têm informações de pagamento configuradas, nem têm
// privilégios administrativos. No momento em que o usuário é criado, seu endereço
// de e-mail e senha são gerados aleatoriamente - você nem precisa
// conhecê-los.
User user = UserFactory.CreateCommonUser(); //This method is defined elsewhere.

// Faça login como este usuário.
// O login neste site leva você à sua página pessoal "Minha conta", e então
// o objeto AccountPage é retornado pelo método loginAs, permitindo que você
// execute ações da AccountPage.
AccountPage accountPage = LoginAs(user.Email, user.Password);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
# mas eles não têm informações de pagamento configuradas, nem têm
# privilégios administrativos. No momento em que o usuário é criado, seu endereço
# de e-mail e senha são gerados aleatoriamente - você nem precisa
# conhecê-los.
user = UserFactory.create_common_user #This method is defined elsewhere.

# Faça login como este usuário.
# O login neste site leva você à sua página pessoal "Minha conta", e então
# o objeto AccountPage é retornado pelo método loginAs, permitindo que você
# execute ações da AccountPage.
account_page = login_as(user.email, user.password)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
// mas eles não têm informações de pagamento configuradas, nem têm
// privilégios administrativos. No momento em que o usuário é criado, seu endereço
// de e-mail e senha são gerados aleatoriamente - você nem precisa
// conhecê-los.
var user = userFactory.createCommonUser(); //This method is defined elsewhere.

// Faça login como este usuário.
// O login neste site leva você à sua página pessoal "Minha conta", e então
// o objeto AccountPage é retornado pelo método loginAs, permitindo que você
// execute ações da AccountPage.
var accountPage = loginAs(user.email, user.password);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Crie um usuário que tenha permissões somente leitura - eles podem configurar um unicórnio,
// mas eles não têm informações de pagamento configuradas, nem têm
// privilégios administrativos. No momento em que o usuário é criado, seu endereço
// de e-mail e senha são gerados aleatoriamente - você nem precisa
// conhecê-los.
val user = UserFactory.createCommonUser() //This method is defined elsewhere.

// Faça login como este usuário.
// O login neste site leva você à sua página pessoal "Minha conta", e então
// o objeto AccountPage é retornado pelo método loginAs, permitindo que você
// execute ações da AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< /tab >}}
{{< /tabpane >}}

Como você pode imaginar, a `UserFactory` pode ser estendida
para fornecer métodos como `createAdminUser ()` e `createUserWithPayment ()`.
A questão é que essas duas linhas de código não o distraem do objetivo final deste teste:
configurando um unicórnio.

Os detalhes do [modelo de objeto de página]({{< relref "/page_object_models.md" >}})
será discutido em capítulos posteriores, mas vamos apresentar o conceito aqui:

Seus testes devem ser compostos de ações,
realizadas do ponto de vista do usuário,
dentro do contexto das páginas do site.
Essas páginas são armazenadas como objetos,
que conterão informações específicas sobre como a página da web é composta
e como as ações são realizadas -
muito pouco disso deve preocupar você como testador.

Que tipo de unicórnio você quer?
Você pode querer rosa, mas não necessariamente.
Roxo tem sido bastante popular ultimamente.
Ela precisa de óculos escuros? Tatuagens de estrelas?
Essas escolhas, embora difíceis, são sua principal preocupação como testador -
você precisa garantir que seu centro de atendimento de pedidos
envia o unicórnio certo para a pessoa certa,
e isso começa com essas escolhas.

Observe que em nenhum lugar desse parágrafo falamos sobre botões,
campos, menus suspensos, botões de opção ou formulários da web.
**Nem deveriam seus testes!**
Você deseja escrever seu código como o usuário tentando resolver seu problema.
Aqui está uma maneira de fazer isso (continuando do exemplo anterior):

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
// Isso armazena apenas os valores; não preenche formulários da web nem interage
// com o navegador de qualquer forma.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
// lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
// nos leva lá.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
// o método createUnicorn(). Este método pegará os atributos do Sparkles,
// preencher o formulário e clicar em enviar.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< /tab >}}
  {{< tab header="Python" >}}
# O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
# Isso armazena apenas os valores; não preenche formulários da web nem interage
# com o navegador de qualquer forma.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
# lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
# nos leva lá.
add_unicorn_page = account_page.add_unicorn()

# Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
# o método createUnicorn(). Este método pegará os atributos do Sparkles,
# preencher o formulário e clicar em enviar.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
// Isso armazena apenas os valores; não preenche formulários da web nem interage
// com o navegador de qualquer forma.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.Purple, UnicornAccessories.Sunglasses, UnicornAdornments.StarTattoos);

// Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
// lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
// nos leva lá.
AddUnicornPage addUnicornPage = accountPage.AddUnicorn();

// Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
// o método createUnicorn(). Este método pegará os atributos do Sparkles,
// preencher o formulário e clicar em enviar.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.CreateUnicorn(sparkles);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
# Isso armazena apenas os valores; não preenche formulários da web nem interage
# com o navegador de qualquer forma.
sparkles = Unicorn.new('Sparkles', UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
# lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
# nos leva lá.
add_unicorn_page = account_page.add_unicorn

# Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
# o método createUnicorn(). Este método pegará os atributos do Sparkles,
# preencher o formulário e clicar em enviar.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
// Isso armazena apenas os valores; não preenche formulários da web nem interage
// com o navegador de qualquer forma.
var sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
// lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
// nos leva lá.
var addUnicornPage = accountPage.addUnicorn();

// Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
// o método createUnicorn(). Este método pegará os atributos do Sparkles,
// preencher o formulário e clicar em enviar.
var unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// O Unicórnio é um objeto de nível superior - ele possui atributos, que são definidos aqui.
// Isso armazena apenas os valores; não preenche formulários da web nem interage
// com o navegador de qualquer forma.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Uma vez que já estamos "na" página da conta, temos que usá-la para chegar ao
// lugar real onde você configura os unicórnios. Chamar o método "Add Unicorn"
// nos leva lá.
val addUnicornPage = accountPage.addUnicorn()

// Agora que estamos na AddUnicornPage, passaremos o objeto "sparkles" para
// o método createUnicorn(). Este método pegará os atributos do Sparkles,
// preencher o formulário e clicar em enviar.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< /tab >}}
{{< /tabpane >}}

Agora que você configurou seu unicórnio,
você precisa passar para a etapa 3: certifique-se de que realmente funcionou.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// O método exists() de UnicornConfirmationPage pegará o objeto
// Sparkles - uma especificação dos atributos que você deseja ver e compará-los
// com os campos na página
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< /tab >}}
  {{< tab header="Python" >}}
# O método exists() de UnicornConfirmationPage pegará o objeto
# Sparkles - uma especificação dos atributos que você deseja ver e compará-los
# com os campos na página
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// O método exists() de UnicornConfirmationPage pegará o objeto
// Sparkles - uma especificação dos atributos que você deseja ver e compará-los
// com os campos na página
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles should have been created, with all attributes intact");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# O método exists() de UnicornConfirmationPage pegará o objeto
# Sparkles - uma especificação dos atributos que você deseja ver e compará-los
# com os campos na página
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles should have been created, with all attributes intact'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// O método exists() de UnicornConfirmationPage pegará o objeto
// Sparkles - uma especificação dos atributos que você deseja ver e compará-los
// com os campos na página
assert(unicornConfirmationPage.exists(sparkles), "Sparkles should have been created, with all attributes intact");

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// O método exists() de UnicornConfirmationPage pegará o objeto
// Sparkles - uma especificação dos atributos que você deseja ver e compará-los
// com os campos na página
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< /tab >}}
{{< /tabpane >}}

Observe que o testador ainda não fez nada além de falar sobre unicórnios neste código–
sem botões, sem localizadores, sem controles do navegador.
Este método de _modelagem_ do aplicativo
permite que você mantenha esses comandos de nível de teste no lugar e imutáveis,
mesmo se Larry decidir na próxima semana que não gosta mais de Ruby-on-Rails
e decidir reimplementar todo o site
em Haskell com um front-end Fortran.

Seus objetos de página exigirão alguma pequena manutenção para
estar conformidade com o redesenho do site,
mas esses testes permanecerão os mesmos.
Pegando esse design básico,
você desejará continuar seus fluxos de trabalho com o menor número possível de etapas voltadas para o navegador.
Seu próximo fluxo de trabalho envolverá adicionar um unicórnio ao carrinho de compras.
Provavelmente, você desejará muitas iterações deste teste para ter certeza de que o carrinho está mantendo o estado adequado:
Existe mais de um unicórnio no carrinho antes de você começar?
Quantos cabem no carrinho de compras?
Se você criar mais de um com o mesmo nome e / ou recursos, ele falhará?
Manterá apenas o existente ou acrescentará outro?

Cada vez que você passa pelo fluxo de trabalho,
você deseja evitar ter que criar uma conta,
fazer login como o usuário e configurar o unicórnio.
Idealmente, você será capaz de criar uma conta
e pré-configurar um unicórnio por meio da API ou banco de dados.
Em seguida, tudo que você precisa fazer é fazer login como o usuário, localizar Sparkles,
e adicioná-lo ao carrinho.


### Automatizar ou não automatizar?

A automação é sempre vantajosa? Quando se deve decidir automatizar os casos de teste?

Nem sempre é vantajoso automatizar casos de teste. Tem vezes que
o teste manual pode ser mais apropriado.
Por exemplo, se a interface do aplicativo mudará consideravelmente em um futuro próximo,
então qualquer automação pode precisar ser reescrita de qualquer maneira.
Além disso, às vezes simplesmente não há tempo suficiente para construir automação de testes.
A curto prazo, o teste manual pode ser mais eficaz.
Se um aplicativo tem um prazo muito curto, atualmente não há
automação de teste disponível, e é imperativo que o teste seja feito dentro
nesse período, o teste manual é a melhor solução.
