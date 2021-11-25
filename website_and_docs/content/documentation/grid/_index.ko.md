---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
aliases: 
        [
          "/documentation/ko/selenium_installation/installing_standalone_server/",
          "/documentation/ko/grid/",
          "/documentation/ko/grid/grid_4/",
          "/documentation/ko/grid/purposes_and_main_functionalities/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium Grid allows the execution of WebDriver scripts on remote machines (virtual
or real) by routing commands sent by the client to remote browser instances.
It aims to provide an easy way to run tests in parallel on multiple machines.

Selenium Grid allows us to run tests in parallel on multiple machines,
and to manage different browser versions and browser configurations centrally
(instead of in each individual test).

Selenium Grid is not a silver bullet.
It solves a subset of common delegation and distribution problems,
but will for example not manage your infrastructure,
and might not suit your specific needs.

## 목적 및 주요 기능

* 모든 테스트의 중앙 진입 지점
* 브라우저가 실행되는 환경과 노드의 관리 및 제어
* 스케일링
* 병렬로 테스트 실행
* 교차 플랫폼 테스트
* 부하분산


{{% alert title="Selenium Grid 4" color="primary" %}}
Grid 4 takes advantage of a number of new technologies in order
to facilitate scaling up while allowing local execution.

Selenium Grid 4 is a fresh implementation and does not share the codebase
the previous version had.

To get all the details of Grid 4 components, understand how it works, and how to set
up you own, please browse thorough the following sections.
{{% /alert %}}
