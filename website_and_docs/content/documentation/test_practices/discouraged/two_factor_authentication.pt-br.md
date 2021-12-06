---
title: "Autenticação de Dois Fatores (2FA)"
linkTitle: "Autenticação de Dois Fatores (2FA)"
weight: 8
aliases: [
"/documentation/pt-br/worst_practices/two_factor_authentication/",
"/pt-br/documentation/worst_practices/two_factor_authentication/"
] 
---

A autenticação de dois fatores, conhecida como _2FA_, é um mecanismo de autorização
onde a senha de uso único (OTP) é gerada usando aplicativos móveis "Autenticadores",
como "Google Authenticator", "Microsoft Authenticator"
etc., ou por SMS, e-mail para autenticação. Automatizar isso perfeitamente
e consistentemente é um grande desafio no Selenium. Existem algumas maneiras
para automatizar este processo. Mas essa será outra camada em cima de nossos
testes Selenium e não protegidos também. Portanto, você pode evitar a automação do 2FA.

Existem algumas opções para contornar as verificações 2FA:

* Desative 2FA para determinados usuários no ambiente de teste, para que você possa
usar essas credenciais de usuário na automação.
* Desative 2FA em seu ambiente de teste.
* Desative 2FA se você fizer o login de determinados IPs. Dessa forma, podemos configurar nosso
teste os IPs da máquina para evitar isso.
