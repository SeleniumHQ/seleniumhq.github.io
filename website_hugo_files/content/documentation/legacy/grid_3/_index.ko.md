---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 6
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}

# Grid 3

_Selenium Grid_ 는 Selenium 테스트가 명령을 원격 웹 브라우저 인스턴스로 보낼 수 있도록 하는 스마트 프록시 서버입니다.
이것의 목적은 여러 기계에서 병렬로 테스트를 실행할 수 있는 쉬운 방법을 제공하는 것입니다.

_Selenium Grid_ 에서 한 서버는 JSON 형식 테스트 명령을 하나 이상의 등록된 Grid 노드로 보내는 허브로서의 역할을 합니다.
테스트를 허브에 연결하여 원격 브라우저 인스턴스에 대한 액세스 권한을 얻으십시오.
허브에는 이러한 인스턴스에 대한 액세스 권한을 제공하고 제어를 허용하는 등록된 서버 목록이 있습니다.

_Selenium Grid_ 는 여러 컴퓨터에서 동시에 테스트를 실행할 수 있도록 하며,
(개별 테스트 대신에) 서로 다른 브라우저 버전과 브라우저 구성을 중앙에서 관리할 수 있도록 합니다.

_Selenium Grid_ 는 묘책이 아닙니다. 일반적인 위임 및 배포 문제의 부분 집합을 해결하지만,
예를 들어 당신의 인프라를 관리하지 않으며 당신의 특정한 요구에 적합하지 않을 수 있습니다.
