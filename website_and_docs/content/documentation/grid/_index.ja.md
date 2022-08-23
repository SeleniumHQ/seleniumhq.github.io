---
title: "Grid"
linkTitle: "Grid"
weight: 6
description: >
  複数のマシン間で並行してテストを実行したいですか？ その場合、グリッドはあなたのためになります。
aliases: 
        [
          "/documentation/ja/selenium_installation/installing_standalone_server/",
          "/documentation/ja/grid/",
          "/documentation/ja/grid/grid_4/",
          "/documentation/ja/grid/purposes_and_main_functionalities/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Japanese. 
   Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium Grid allows the execution of WebDriver scripts on remote machines 
by routing commands sent by the client to remote browser instances. 

Grid aims to:

* Provide an easy way to run tests in parallel on multiple machines
* Allow testing on different browser versions
* Enable cross platform testing

Interested? Go through the following sections to understand
how Grid works, and how to set up your own.