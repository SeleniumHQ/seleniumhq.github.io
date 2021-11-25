---
title: "ThreadGuard"
linkTitle: "ThreadGuard"
weight: 6
aliases: ["/documentation/de/webdriver/support_classes/"]
---

{{% pageinfo color="info" %}}
<p class="lead">
  Diese Klasse ist nur in den Java-Bindings enthalten
</p>
{{% /pageinfo %}}

ThreadGuard prüft ob alle Driver Aufrufe nur von dem Thread getätigt werden, der
diesen Driver instanziert hat. Probleme mit Threading können, vor allem bei der
parallelen Ausführung, zu mysteriösen Problemen führen, die schwer zu 
diagnostizieren sind. Die Verwendung dieses Wrappers verhindert Fehler dieser 
Kategorie und wirft eine Exception falls das Problem auftritt.

Das folgende Beispiel provoziert einen solchen Fehler:

```java
public class DriverClash {
  //thread main (id 1) created this driver
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver()); 

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }
  
  //Thread-1 (id 24) is calling the same driver causing the clash to happen
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

Das Ergebnis sieht wie folgt aus:
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```

Im Beispiel ist zu sehen:

* `protectedDriver` wird im Main-Thread erzeugt
* Es wird ein Javaobjekt vom Typ `Runnable` verwendet um einen neuen 
  Prozess zu erzeugen und ein neuer `Thread` gestartet
* Beide `Threads` stürzen ab, weil der Main-Thread keinen 
  `protectedDriver` in seinem Arbeitsspeicher hat.
* `ThreadGruard.protect` wirft eine Exception.

#### Bemerkung:

Das ersetzt nicht die Notwendigkeit `ThreadLocal` zu verwenden um mehrere Drivers 
zu koordinieren wenn diese parallel laufen sollen.

