---
title: "Classes de suporte"
weight: 5
---

As classes de suporte do WebDriver são fornecidas para simplificar a manutenção do seu código.
Eles fornecem uma boa abstração para fazer a modelagem de elemento(s) HTML como objetos de domínio mais fáceis, também fornecendo métodos auxiliares para tornar o uso de tais objetos fácil de se pensar. Vamos aprender sobre:

* Estratégias de localizador
* Eventos
* LoadableComponent
* ThreadGuard
* etc.

Vamos começar:


## **ThreadGuard**
{{% notice info %}}
Esta classe está disponível apenas no Java Binding
{{% /notice %}}
ThreadGuard verifica se um driver é chamado apenas da mesma thread que o criou.
Problemas de threading, especialmente durante a execução de testes em paralelo, podem ter erros misteriosos
e difíceis de diagnosticar. Usar este wrapper evita esta categoria de erros
e gerará uma exceção quando isso acontecer.

O exemplo a seguir simula um conflito de threads:

```java
public class DriverClash {
  //thread main (id 1) criou este driver
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver()); 

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }
  
  //Thread-1 (id 24) está chamando o mesmo driver causando o conflito
  Runnable r1 = () -> {protectedDriver.get("https://selenium.dev");};
  Thread thr1 = new Thread(r1);
   
  void runThreads(){
    thr1.start();
  }

  public static void main(String[] args) {
    new DriverClash().runThreads();
  }
}
```

O resultado mostrado abaixo:
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
Conforme visto no exemplo:

* `protectedDriver` será criado no tópico principal
* Usamos Java `Runnable` para ativar um novo processo e uma nova `Thread` para executar o processo
* Ambas as `Thread`s entrarão em conflito porque a thread principal não tem `protectedDriver` em sua memória.
* `ThreadGuard.protect` lançará uma exceção.
  
#### Nota:

Isso não substitui a necessidade de usar `ThreadLocal` para gerenciar drivers durante a execução em paralelo.
